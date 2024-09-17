package identity.TuanHuy.supper_add_add;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SieuNhan sieuNhan = (SieuNhan) o;
        return Objects.equals(ten, sieuNhan.ten) && Objects.equals(diachi, sieuNhan.diachi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ten, diachi);
    }
}
