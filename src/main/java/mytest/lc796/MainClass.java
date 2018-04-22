package mytest.lc796;

import java.io.IOException;

class Solution {
    public boolean rotateString(String A, String B) {
        if(A==null && B==null){
            return true;
        }
        if("".equals(A) && "".equals(B)) {
            return true;
        }
        if(A !=null && B!=null && A.length() !=B.length()){
            return false;
        }
        if((A == null && B !=null) || (B==null && A!=null) ){
            return false;
        }

        char bF = B.charAt(0);

        int bFIndexA = A.indexOf(bF);

        if(bFIndexA<0) {
            return false;
        } else {
            String doubleA = A + A;
            if(doubleA.indexOf(B)==-1){
                return false;
            }else {
                String subA = doubleA.substring(0, doubleA.indexOf(B))+ doubleA.substring( doubleA.indexOf(B)+B.length(), doubleA.length());
                return subA.equals(A);
            }

        }

    }
}

public class MainClass {
//    public static String stringToString(String input) {
//        if (input == null) {
//            return "null";
//        }
//        return Json.value(input).toString();
//    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        String A = "abcde";

        String B ="cdeba";

        boolean ret = new Solution().rotateString(A, B);

        String out = booleanToString(ret);

        System.out.print(out);
    }
}
