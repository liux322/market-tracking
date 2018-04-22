package mytest.lc670;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
//    public int maximumSwap(int num) {
//        String numStr = ""+num;
//        char[] charArray = numStr.toCharArray();
//        char maxDigit = charArray[0];
//        int indexOfMax = 0;
//        int indexOfSwitch = 0;
//        int pre = charArray[0];
//
//
//        StringBuilder stringBuilder = new StringBuilder();
//        boolean needSwitch = false;
//        int i =0;
//        while(i<le) {
//            if(charArray[i] >= maxDigit){
//                indexOfMax = i;
//                maxDigit = charArray[i];
//            }
//            if(charArray[i] > pre){
//                needSwitch = true;
//                indexOfSwitch = i-1;
//                break;
//            }
//            pre=charArray[i];
//        }
//
//        if(!needSwitch){
//            return num;
//        }else{
//            int indexOfMax2 = 0;
//            char maxDigit2 =0;
//            for(int i= indexOfSwitch; i<charArray.length; i++){
//                if(charArray[i] >= maxDigit2){
//                    indexOfMax2 = i;
//                    maxDigit2 = charArray[i];
//                }
//            }
//            if(maxDigit2> maxDigit){
//                if(indexOfMax>0){
//                    char temp = charArray[0];
//                    charArray[0] = maxDigit2;
//                    charArray[indexOfMax2] = temp;
//                }else{
//                    charArray[indexOfMax] = maxDigit2;
//                    charArray[indexOfMax2] = maxDigit;
//                }
//            }else if(maxDigit2 > charArray[indexOfSwitch]){
//                for(int i = indexOfMax; i<indexOfSwitch; i++){
//                    if(maxDigit2 > charArray[i]){
//                        char temp = charArray[i];
//                        charArray[i] = maxDigit2;
//                        charArray[indexOfMax2] = temp;
//                        break;
//                    }
//                }
//            }else{
//                char temp = charArray[indexOfSwitch];
//                char temp1 = charArray[indexOfSwitch-1];
//                charArray[indexOfSwitch] = temp1;
//                charArray[indexOfSwitch-1] = temp;
//            }
//        }
//
//        String newString = stringBuilder.append(charArray).toString();
//
//        return Integer.parseInt(newString);
//
//    }
public int maximumSwap(int num) {
    char[] digits = Integer.toString(num).toCharArray();

    int[] buckets = new int[10];
    for (int i = 0; i < digits.length; i++) {
        buckets[digits[i] - '0'] = i;
    }

    for (int i = 0; i < digits.length; i++) {
        for (int k = 9; k > digits[i] - '0'; k--) {
            if (buckets[k] > i) {
                char tmp = digits[i];
                digits[i] = digits[buckets[k]];
                digits[buckets[k]] = tmp;
                return Integer.valueOf(new String(digits));
            }
        }
    }

    return num;
}
}

public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int num = Integer.parseInt(line);

            int ret = new Solution().maximumSwap(num);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
