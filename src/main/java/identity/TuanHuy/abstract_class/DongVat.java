package identity.TuanHuy.abstract_class;

public abstract class DongVat {
    public abstract void noi();
    public void ngu(){
        System.out.println("dong vat di ngu");
    }

}
class MEO extends DongVat{
    @Override
    public void noi(){
        System.out.println("meo");
    }
}
