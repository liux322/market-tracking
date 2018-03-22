import java.io.File;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dliu on 9/15/2016.
 */
public class MyTest {
    public static void main(String[] args){
        String tempfileName = "c:/test/test/\1473962603163_4466.tmp";
        String firstDigit = tempfileName.substring(tempfileName.lastIndexOf(
                File.separator) + 1, tempfileName.lastIndexOf("."));
        //String firstDigit = "\1473962603163_4466";
        Matcher matcher = Pattern.compile("\\d+").matcher(firstDigit);
        if(matcher.find()){
            firstDigit = matcher.group(0);
        }
        System.out.println(firstDigit);
        Date date =new Date(Long.parseLong(firstDigit));
        System.out.println(date);

        String refinancecPattern = "Refinance.*";
        String refinanceHelocPattern = "Refinance-Heloc.*";
        String purchasePattern = "Purchase.*";
        System.out.println("Refinance-Heloc match refinanceHelocPattern pattern? " + "Refinance-Heloc".matches(refinanceHelocPattern));
        System.out.println("Refinance-Heloc match refinancecPattern pattern? " + "Refinance-Heloc".matches(refinancecPattern));
        System.out.println("Refinance-Heloc match purchasePattern pattern? " + "Refinance-Heloc".matches(purchasePattern));

        System.out.println("Refinance-amc match refinanceHelocPattern pattern? " + "Refinance-amc".matches(refinanceHelocPattern));
        System.out.println("Refinance-amc match refinancecPattern pattern? " + "Refinance-amc".matches(refinancecPattern));
        System.out.println("Refinance-amc match purchasePattern pattern? " + "Refinance-amc".matches(purchasePattern));

        System.out.println("Refinance-Helocfdafdfsdafasd match pattern? " + "Refinance-amc".matches(refinanceHelocPattern));
    }
}
