package javase.object_oriented_programing.interface_learn;

import java.util.ArrayList;

interface DONGVAT{
    public static final String mau_long = "YELLOW";
    public abstract void hanh_dong();
    default void keu(){
        System.out.println("động vật keu");
    }
}

class MEO implements DONGVAT{
    @Override
    public void hanh_dong(){
        System.out.println("mỹ diệu đang ngồi ... ");
    }
}

class CUN implements DONGVAT {
    @Override
    public void hanh_dong(){
        System.out.println("nguyễn văn dúi đang chạy ... ");
    }
}

public class Main {
    public static void main(String[] args){
        ArrayList<DONGVAT> arrayList_dv = new ArrayList<>();
        arrayList_dv.add(new CUN());
        arrayList_dv.add(new MEO());
        for (DONGVAT idol: arrayList_dv){
            idol.hanh_dong();
        }
    }
}
