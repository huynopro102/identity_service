package identity.TuanHuy;

public class Client {

    private emailService service = new emailService();

    public void processMessage(String message){
        service.sendMessage(message);
    }
}
