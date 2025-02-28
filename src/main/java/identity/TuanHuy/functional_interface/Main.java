package identity.TuanHuy.functional_interface;

import identity.TuanHuy.configuration.DatabaseConnection;

public class Main {
    public static void main(String[] args){


        Box box = new Box("hello");
        System.out.println(box.getValue());

        Box box1 = new Box("hello");
        System.out.println(box1.getValue());


        int[] mang = {3,4,1,5,1};
//        var a = twoSum(()-> "hello world");
    }
//
//    static public<X> X twoSum(functional_interface<X> ex){
//
//        return null;
//    }


}

