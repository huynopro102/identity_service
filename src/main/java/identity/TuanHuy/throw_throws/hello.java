package identity.TuanHuy.throw_throws;
import identity.TuanHuy.static_learning.hinhvuong;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class hello {
    public static void main(String[] args) throws my_exception , FileNotFoundException {
        String chuoi ="null";
        Scanner sc = new Scanner(System.in);
        System.out.println("nhap so nguyen a");
        int a = sc.nextInt();
        System.out.println("nhap so nguyen b");
        int b = sc.nextInt();
        hinhvuong hv = new hinhvuong(3);
        hinhvuong.printName("huy r ");

        try {
            System.out.println("nhap so nguyen c " + a/b);
        }catch(my_exception e){
            System.out.println(e.getHuy_error("b khac 0"));
        }
        dodaiString(chuoi);
        Docfile("huyr");
    }


    public static void dodaiString(String chuoi){
        if(chuoi == null){
            throw new NullPointerException("chuoi ko dc rong");
        }
        System.out.println(chuoi.length());
    }
    public static void Docfile(String tenFile) throws FileNotFoundException {
        FileReader reader = new FileReader(tenFile);
    }

}
