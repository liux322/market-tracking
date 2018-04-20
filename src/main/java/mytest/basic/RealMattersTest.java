package mytest.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class ReportMistake {
    public String description;
    public double rate; // the percent of orders where this mistake has occurred
}

public class RealMattersTest {

    public static ReportMistake[] kWorstMistakes(ReportMistake[] mistakes, int k) {
        List<ReportMistake> list = Arrays.asList(mistakes);
        Collections.sort(list, new Comparator<ReportMistake>() {
            @Override
            public int compare(ReportMistake o1, ReportMistake o2) {
                return o1.rate>o2.rate ? 1: ( (o1.rate== o2.rate)? 0: -1 );
            }
        });

        ReportMistake[] ret = new ReportMistake[k];
        for(int i=0; i<k; i++){
            ret[i] = list.get(i);
        }

        return ret;
    }

    public static void wobbleFish(int data) {
        if(data%9==0 && data%5 ==0 ){
            System.out.println("WobbleFishRocks");
        }else if(data%9==0){
            System.out.println("WobbleRocks");
        }else if(data%5==0) {
            System.out.println("fish");
        }else if(data%3==0){
            System.out.println("wobble");
        }else{
            System.out.println("" + data);
        }

    }
//    public static void wobbleFish(int data) {
//        if(data%3==0 && data%5 ==0 ){
//            System.out.println("wobblefish");
//        }else if(data%3==0){
//            System.out.println("wobble");
//        }else if(data%5==0) {
//            System.out.println("fish");
//        }else{
//            System.out.println("" + data);
//        }
//
//    }

    public static void main(String[] args){
        for(int i=0; i<100; i++){
            wobbleFish(i);
        }
    }

}
