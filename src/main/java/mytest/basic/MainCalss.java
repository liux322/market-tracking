package mytest.basic;


import java.util.Arrays;
import java.util.List;

class parent {

    private static void display() {
        System.out.println("Static or class method from Base");
    }

    public void print() {
        System.out.println("Non-static or instance method from Base");
        display();
    }
}
    class child extends parent {

        private static void display() {
            System.out.println("Static or class method from Derived");
        }

        public void print() {
            System.out.println("Non-static or instance method from Derived");
            display();
        }
    }


public class MainCalss {

    public static void main(String[] args){
        parent test = new child();
        test.print();

        String str = "dfdfd";
        char[] array = str.toCharArray();
        List<char[]> list = Arrays.asList(array);

        Integer[] spam = new Integer[] { 1, 2, 3 };
        List<Integer> intList  = Arrays.asList(spam);

        String[] arr = list.toArray(new String[list.size()]);

//        int[] spam1 = new int[] { 1, 2, 3 };
//        Arrays.stream(spam)
//                .boxed()
//                .collect(Collectors.toList());

    }
}
