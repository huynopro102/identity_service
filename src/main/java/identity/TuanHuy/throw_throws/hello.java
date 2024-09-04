package identity.TuanHuy.throw_throws;

import ch.qos.logback.core.encoder.EchoEncoder;
import jakarta.transaction.SystemException;

import java.util.Scanner;

public class hello {
    public static void main(String[] args) throws my_exception {
        System.out.println("hello");
        Scanner sc = new Scanner(System.in);
        System.out.println("nhap so nguyen a");
        int a = sc.nextInt();
        System.out.println("nhap so nguyen b");
        int b = sc.nextInt();
        try {
            System.out.println("nhap so nguyen c " + a/b);
        }catch(my_exception e){
            System.out.println(e.getHuy_error("b khac 0"));
        }
    }
}
