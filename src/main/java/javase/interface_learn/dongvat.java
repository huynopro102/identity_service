package identity.TuanHuy.interface_learn;

public interface dongvat {
    String MAU_LONG = "vang";
    void hanh_dong();
    default void trien_khai(){
        System.out.println("trien khai");
    }
}
