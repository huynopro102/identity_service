package identity.TuanHuy.static_learning;
public class hinhvuong {
    public static int dem = 0;
    public int chieudai ;
    public hinhvuong(int chieudai) {
            this.chieudai = chieudai;
            dem = dem + 1;
    }
    public int GetChieudai(){
        return chieudai;
    }
    public void SetChieudai(int chieudai){
        this.chieudai = chieudai;
    }
    public static int dientich(int do_dai_canh){
        int a = dem;
        return do_dai_canh*do_dai_canh;
    }
    public static String printName(String name){
        int dientich_hinh = dientich(12);
        return name;
    }
}