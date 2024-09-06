package identity.TuanHuy.supper;

public class SieuNhan {
    private String ten;
    private String diachi;

    public SieuNhan(String ten, String diachi) {
        this.ten = ten;
        this.diachi = diachi;
    }

    public SieuNhan(){

    }
    public void thongTin(){
        System.out.println("ten sieu nhan "+ten);
        System.out.println("dia chi sieu nhan "+diachi);
    }
}
