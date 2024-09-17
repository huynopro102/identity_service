package identity.TuanHuy.supper_add_add;

public class MainSieuNhan {
    public static void main(String[] args) {
        SieuNhanDo sieuNhanDo = new SieuNhanDo("do","kiem","khanh","binh duong");
        sieuNhanDo.thongTin();
        int a = 0;
        int b = 1 + ++a;
        System.out.println(a);
        // ++a hậu tố toán tử , được thực hiện trước bất kỳ câu lênh nào khác trong cùng 1 đoạn code đc thực hiện
        // a++ tiền tố : ngược lại
        System.out.println(b);
    }
}
