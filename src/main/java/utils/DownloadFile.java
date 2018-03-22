package utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dliu on 11/11/2016.
 */
public class DownloadFile {
    private static final String OUTPUT_FOLDER = "C:\\DawnStuff\\fa\\results\\";
    private static final String INPUT_FILE = "C:\\DawnStuff\\fa\\historyLinks.txt";
    private static final String EXTENTION =".csv";

    public void download(String fileName, String fileUrl) throws IOException {
        URL website = new URL(fileUrl);
        ReadableByteChannel rbc = null;
        FileOutputStream fos = null;
        try{
            rbc = Channels.newChannel(website.openStream());
            fos = new FileOutputStream(OUTPUT_FOLDER + fileName);
            fos.getChannel().transferFrom(rbc, 0, 1<<24 );
            System.out.println("end of download");

        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(rbc != null){
                rbc.close();
            }
            if(fos != null){
                fos.close();
            }
        }
    }

    public void checkFromFile() throws IOException{
        List<String> list = new ArrayList();
        LineIterator it = FileUtils.lineIterator(new File(INPUT_FILE), "UTF-8");
        try{
            while(it.hasNext()){
                String line = it.nextLine();
                if(StringUtils.isNotBlank(line)){
                    String[] pair = line.split(",");
                    download(pair[0] + EXTENTION, pair[1]);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        String fileUrl = "http://www.google.ca/finance/historical?cid=113717368908899&startdate=Aug+27%2C+2007&enddate=Sep+21%2C+21166&output=csv";

        try {
            //new DownloadFile().download(fileUrl, "dgaz"+EXTENTION );
            new DownloadFile().checkFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
