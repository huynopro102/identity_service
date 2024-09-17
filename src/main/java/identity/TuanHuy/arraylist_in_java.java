package identity.TuanHuy;

import identity.TuanHuy.supper_add_add.SieuNhan;
import identity.TuanHuy.supper_add_add.SieuNhanDo;

import java.util.ArrayList;

public class arraylist_in_java {
    public static String chuoi = "fjdklsa";
    public String[] trai_cay = {"c1","c2","c3"};
    public static void main(String[] args) {
        int a = 0;
        Integer vien = 3;
        String a1= "123";
        String a2 = new String("123");


        ArrayList<SieuNhan> ArrayListSieuNhan = new ArrayList<>();
        ArrayListSieuNhan.add(new SieuNhan("huy 1","binh duong 1"));
        ArrayListSieuNhan.add(new SieuNhan("huy 2","binh duong 2"));
        ArrayListSieuNhan.add(new SieuNhan("huy 3","binh duong 3"));

        ArrayListSieuNhan.set(0,new SieuNhan("huy r","binh duong 4"));


        ArrayListSieuNhan.remove(1);

        ArrayListSieuNhan.remove(new SieuNhan("huy 3","binh duong 3"));
        System.out.println(new SieuNhanDo().du_lieu_cong_khai);
        System.out.println(ArrayListSieuNhan.contains(new SieuNhan("huy r","binh duong 4")));

        for(int i=0;i<ArrayListSieuNhan.size();i++) {
            ArrayListSieuNhan.get(i).thongTin();
        }

    }



}
