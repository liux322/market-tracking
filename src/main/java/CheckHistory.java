import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import datamodel.TransactionRecord;
import datamodel.Trend;
import utils.CommonCSVFileLoader;

/**
 * Created by Dliu on 9/12/2016.
 */
public class CheckHistory {

    private static final String SIMBOL_FILE = "dgaz.csv";
    private static final String INPUT_FILE = "C:\\DawnStuff\\fa\\checklist.txt";
    private static final String OUTPUT_FILE = "C:\\DawnStuff\\fa\\checkResults\\historyResult.txt";
    private static final String EXTENTION =".csv";
    private static final String CSV_FOLDER = "C:\\DawnStuff\\fa\\results\\";
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final double HALF_PERCENT = 0.005;
    private static final int INTERESTED_NUMBER_OF_DAYS = 4;
    static Comparator<TransactionRecord> byDate = (r1, r2) -> r1.getTranactionDate().compareTo(r2.getTranactionDate());


    public static void main(String[] args) throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream(OUTPUT_FILE));
        System.setOut(out);

        checkFromFile();
     //   check(SIMBOL_FILE, false);
    }

    private static void checkFromFile() throws IOException{
        List<String> fileList = new ArrayList();
        //fileList.add("tna.csv");
        LineIterator it = FileUtils.lineIterator(new File(INPUT_FILE), "UTF-8");
        try{
            while(it.hasNext()){
                String line = it.nextLine();
                if(StringUtils.isNotBlank(line)){
                    fileList.add(line.trim()+EXTENTION);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        LocalDate today = LocalDate.now();
        LocalDate firstDayOfTheMonth = today.withDayOfMonth(1);

        System.out.println("first day of the month? " + today.isEqual(firstDayOfTheMonth));
        fileList.stream().forEach(file -> check(file,today.isEqual(firstDayOfTheMonth) ));
    }

    public static void check(List<TransactionRecord> records, boolean checkMonth) {
        Collections.sort(records, byDate);
        //System.out.println("firstDate = " + records.get(0).getTranactionDate());

        int maxUpDays = 0;
        int maxDownDays = 0;
        Double previousPrice = records.get(0).getOpenPrice();
        boolean up = records.get(0).getClosePrice() >= records.get(0).getOpenPrice();
        boolean down = records.get(0).getClosePrice() < records.get(0).getOpenPrice();
        boolean turned = false;
        Trend upTrend = new Trend();
        Trend downTrend = new Trend();
        List<Trend> upTrends = new ArrayList();
        List<Trend> downTrends = new ArrayList();
        Map<String, Map<Integer, List<Trend>>> trendMap = new HashMap();
        double change_percent = 0.0;
        // TransactionRecord previousRecord = records.get(0);

        for(TransactionRecord record : records) {
            change_percent = previousPrice==0.0? Double.MIN_NORMAL : ((record.getClosePrice()-previousPrice)/previousPrice);
            //if(record.getClosePrice() > previousPrice) {
            if((record.getClosePrice()-previousPrice)/previousPrice > HALF_PERCENT) {
                //UP
                maxUpDays++;
                turned = !up;
                if(turned){
                    upTrend = new Trend();
                    upTrend.setStartDate(record.getTranactionDate());
                    upTrend.setFirstDayPrice(record.getClosePrice());

                    downTrend.setLastDayPrice(previousPrice);
                    downTrend.setEndDate(record.getTranactionDate());
                    downTrend.setFirstDayPriceOfTheNextDay(record.getClosePrice());
                    downTrend.setDaysInTrend(maxDownDays);
                    downTrends.add(downTrend);
                    if(trendMap.containsKey(DOWN)){
                        if(trendMap.get(DOWN).containsKey(maxDownDays)){
                            List<Trend> list = trendMap.get(DOWN).get(maxDownDays);
                            list.add(downTrend);
                        }else{
                            List<Trend> mylist = new ArrayList();
                            mylist.add(downTrend);
                            trendMap.get(DOWN).put(maxDownDays, mylist);
                        }
                    }else{
                        Map<Integer, List<Trend>> newMap = new HashMap<Integer, List<Trend>>();
                        List<Trend> newList = new ArrayList();
                        newList.add(downTrend);
                        newMap.put(new Integer(maxDownDays), newList);
                        trendMap.put(DOWN, newMap);
                    }

                    maxDownDays = 0;
                    up = true;
                    down = false;
                }else{
                    upTrend.setEndDate(record.getTranactionDate());
                }
            }else if((record.getClosePrice()-previousPrice)/previousPrice < (-1.0 * HALF_PERCENT)){
                //DOWN
                maxDownDays++;
                turned = !down;
                if(turned){
                    downTrend = new Trend();
                    downTrend.setStartDate(record.getTranactionDate());
                    downTrend.setFirstDayPrice(record.getClosePrice());

                    upTrend.setLastDayPrice(previousPrice);
                    upTrend.setEndDate(record.getTranactionDate());
                    upTrend.setFirstDayPriceOfTheNextDay(record.getClosePrice());
                    upTrend.setDaysInTrend(maxUpDays);
                    upTrends.add(upTrend);
                    if(trendMap.containsKey(UP)){
                        if(trendMap.get(UP).containsKey(maxUpDays)){
                            trendMap.get(UP).get(new Integer(maxUpDays)).add(upTrend);
                        }else{
                            List<Trend> newList = new ArrayList();
                            newList.add(upTrend);
                            trendMap.get(UP).put(maxUpDays, newList);
                        }
                    }else{
                        Map<Integer, List<Trend>> newMap = new HashMap();
                        List<Trend> newList = new ArrayList();
                        newList.add(upTrend);
                        newMap.put(maxUpDays, newList);
                        trendMap.put(UP, newMap);
                    }
                    maxUpDays = 0;
                    up = false;
                    down = true;
                }else{
                    downTrend.setEndDate(record.getTranactionDate());
                }
            } else {
                if(up){
                    maxUpDays++;
                }else{
                    maxDownDays++;
                }
            }
            previousPrice = record.getClosePrice();
        }



        System.out.println("What is the trend yesterday? " + (up? "UP":"Down") +"; " +  "What is the day in the trend yesterday? " + (up? maxUpDays:maxDownDays));
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2);
        System.out.println("Yesterday's change = " + percentFormat.format(change_percent));

        Map<Integer, List<Trend>> upMap = trendMap.get(UP);
        List<Integer> upTimes = new ArrayList<>();
        upMap.forEach((k,v) -> {
            if(k>INTERESTED_NUMBER_OF_DAYS){
                upTimes.add(v.size());
            }
        });

        System.out.println("UP days > " + INTERESTED_NUMBER_OF_DAYS + ": " + upTimes.stream().mapToInt(Integer::intValue).sum());

        Map<Integer, List<Trend>> downMap = trendMap.get(DOWN);
        List<Integer> downTimes = new ArrayList<>();
        downMap.forEach((k,v) -> {
            if(k>INTERESTED_NUMBER_OF_DAYS){
                downTimes.add(v.size());
            }
        });
        System.out.println("DOWN days > " + INTERESTED_NUMBER_OF_DAYS + ": " + downTimes.stream().mapToInt(Integer::intValue).sum());

//comment out start here
//        System.out.println("Number of Up trends:");
//
//        upMap.forEach((k,v)-> {
//            System.out.println("Days: " +k);
//            System.out.println("repeats: " + v.size());
//            if(k>INTERESTED_NUMBER_OF_DAYS){
//                upTimes.add(k);
//            }
//        });
//
//        System.out.println();
//
//
//        System.out.println("Number of down trends:");
//
//        downMap.forEach((k,v) -> {
//            System.out.println("Days: " +k);
//            System.out.println("repeats: " + v.size());
//        });
// comment out end here
        Comparator<Trend> byDaysInTrend = (t1, t2) -> Integer.compare(
                t2.getDaysInTrend(),
                t1.getDaysInTrend());

        Collections.sort(upTrends, byDaysInTrend);
        Collections.sort(downTrends, byDaysInTrend);


//comment out start here
//        if(checkMonth){
//            System.out.println("SHOW DETAILS... ");
//            System.out.println("^^^^^^^^^^^^^^^^^^^^ Up trends: ^^^^^^^^^^^^^^^^^^^^ ");
//            upTrends.stream().filter(t-> t.getDaysInTrend()>2).forEach(t -> System.out.println(t));
//            //System.out.println(upTrends);
//
//            System.out.println("vvvvvvvvvvvvvvvvvvvvv Down trends: vvvvvvvvvvvvvvvvvvvvv ");
//            downTrends.stream().filter(t-> t.getDaysInTrend()>2).forEach(t -> System.out.println(t));
//            //System.out.println(downTrends);
//        }

//comment out end here
    }

    public static void check(String fileName, boolean checkMonth){
        try{
            //String filepath = "C:\\Users\\dliu\\Downloads\\FA\\" + fileName;
            String filepath = CSV_FOLDER + fileName;
            CommonCSVFileLoader loader = new CommonCSVFileLoader(filepath);
            List<TransactionRecord> records = loader.readCSVFile();
            System.out.println("------------------------------------------");
            System.out.println(fileName + ", size = " + records.size());

            LocalDate now = LocalDate.now();
            LocalDate early30Days = now.minusDays(365);
            List<TransactionRecord> lastMonthRecord = records.stream().filter(r -> r.getTranactionDate().isAfter(early30Days)).collect(
                    Collectors.toList());


            // Comparator<TransactionRecord> byDate = (r1, r2) -> (r1==null || r1.getTranactionDate()==null)? -1: (r2==null || r2.getTranactionDate()==null)? 1: r1.getTranactionDate().compareTo(r2.getTranactionDate());
            check(lastMonthRecord, true);
            System.out.println();
// comment out start here
//            System.out.println("HISTORY TRANSACTION");
//            check(records, checkMonth);
//            check(records, true);
// comment out end here
        }catch(Exception ex){}
    }

}
