package mytest;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by Dliu on 9/29/2016.
 */
public class TestBOM {
    public static void skip(Reader reader) throws IOException
    {
        reader.mark(1);
        char[] possibleBOM = new char[1];
        if(reader.ready()){
            reader.read(possibleBOM);
        }

        if (possibleBOM[0] != '\ufeff' && possibleBOM[0] != '\u2007') {
            reader.reset();
        }else {
            System.out.println("BOM skipper find FEFF");
        }
    }


    public static void main(String[] args){
        File file = new File("C:\\Users\\dliu\\Downloads\\SOL1V100_sample_fromUSTEST.txt");
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\dliu\\Downloads\\SOL1V100_sample_fromUSTEST.txt");
            BufferedReader rd = new BufferedReader(new InputStreamReader(fis));

            skip(rd);

            StringBuilder partBuffer = new StringBuilder();
            String headerline;
            headerline = rd.readLine();
            if(headerline != null){
                //headerline.trim();
                if(headerline.indexOf("\uFEFF")>0){
                    System.out.println("FEFF found");
                    headerline = StringUtils.remove(headerline,  "\uFEFF");
                }
//                if(headerline.indexOf("\uEFBBBF")>0){
//                    System.out.println("EFBBBF found");
//                    headerline = StringUtils.remove(headerline,   "\uEFBBBF");
//                }

                partBuffer.append(headerline.trim());
            }

            while ((headerline = rd.readLine()) != null) {
               // System.out.println(headerline);
                partBuffer.append(headerline.trim()).append("\r\n"); // needed /r/n to keep the Multipart format !!

//                if(!StringUtils.contains(headerline, "<U+FEEFF>")){
//                    partBuffer.append(headerline.trim()).append("\r\n"); // needed /r/n to keep the Multipart format !!
//                }else{
//                    System.out.println("find the FEEFF TAG");
//                }
            }

            String requestAsString = partBuffer.toString();
            System.out.println("is request start with white space? " + requestAsString.startsWith(" "));
            System.out.println("Reqeust = \n " +  requestAsString);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ie){}
    }
}
