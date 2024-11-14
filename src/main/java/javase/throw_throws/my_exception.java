package identity.TuanHuy.throw_throws;

public class my_exception extends RuntimeException{
    private String huy_error;
    public my_exception(String huy_error) {
        super();
        this.huy_error = huy_error;
    }
    public my_exception() {
        super();
        this.huy_error = "rong";
    }

    public String getHuy_error(String s) {
        return huy_error;
    }

}
