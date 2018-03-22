import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import datamodel.SecurityInfo;
import utils.HtmlParse;

/**
 * Created by Dliu on 11/10/2016.
 */
public class CheckCurrent {

    private static final String LINK = "https://www.google.com/finance?q=";
    private static final String INPUT_FILE = "C:\\DawnStuff\\fa\\checklist.txt";
    private static final String OUTPUT_FILE = "C:\\DawnStuff\\fa\\checkResults\\checkResult.txt";

    public static void parsePage(SecurityInfo securityInfo) throws IOException {
        BufferedReader source = null;
        if (securityInfo != null) {
            try {
                URL address = new URL(securityInfo.getUrl());
                // Open the address and create a BufferedReader with the source code.
                InputStreamReader pageInput = new InputStreamReader(address.openStream());
                source = new BufferedReader(pageInput);

                HtmlParse parser = new HtmlParse();

                parser.parseInfo(securityInfo.getTicker(), source, securityInfo);
                System.out.println(securityInfo);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (source != null) {
                    source.close();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream(OUTPUT_FILE));
        System.setOut(out);
        checkFromFile();
        //checkSingle("JE");
    }

    private static void checkSingle(String s){
        SecurityInfo info = new SecurityInfo();
        info.setTicker(s);
        info.setUrl(LINK + s);
        try {
            parsePage(info);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void  checkFromFile() throws IOException{
        List<String> list = new ArrayList();
        LineIterator it = FileUtils.lineIterator(new File(INPUT_FILE), "UTF-8");
        try{
            while(it.hasNext()){
                String line = it.nextLine();
                if(StringUtils.isNotBlank(line)){
                    list.add(line.trim());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        list.stream().forEach(s -> checkSingle(s));
    }

}
