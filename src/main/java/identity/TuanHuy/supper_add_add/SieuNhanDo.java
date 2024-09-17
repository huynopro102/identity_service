package identity.TuanHuy.supper_add_add;

public class SieuNhanDo extends SieuNhan{
    private String mau;
    private String vukhi;
    private String a = new String("fjdkslafa");
    public String du_lieu_cong_khai = "cong khai";

    public SieuNhanDo(String mau, String vukhi , String ten , String diachi) {
        super(ten, diachi);
        this.mau = mau;
        this.vukhi = vukhi;
    }
    public SieuNhanDo(){

    }

    @Override
    public void thongTin(){
        super.thongTin();
        System.out.println("mau "+this.mau);
        System.out.println("vukhi "+this.vukhi);
    }


}
