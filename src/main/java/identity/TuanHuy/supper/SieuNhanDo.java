package identity.TuanHuy.supper;

public class SieuNhanDo extends SieuNhan{
    private String mau;
    private String vukhi;

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
