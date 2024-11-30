package javase.object_oriented_programing.abstract_learn;
import jdk.jfr.Event;

interface DONGVATCASI{
    void hat();
}

abstract class DONGVAT{
    abstract void noi();
    public void ngu(){
        System.out.println("động vật đang ngủ");
    }
}

class MEO extends DONGVAT {
    @Override
    public void noi(){
        System.out.println("meo meo");
    }
}

class HOAMI extends DONGVAT implements DONGVATCASI{
    @Override
    void noi() {

    }

    @Override
    public void hat() {

    }
}

public class main {
    public static void main(String[] args){

    }
}
