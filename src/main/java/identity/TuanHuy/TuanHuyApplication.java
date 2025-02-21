package identity.TuanHuy;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@SpringBootApplication // this is an annotation automatically scans classes with @Service , @Repository , @Component,@Controller
public class TuanHuyApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TuanHuyApplication.class, args);
		TuanHuyApplication app = context.getBean(TuanHuyApplication.class);

		System.out.println("hello world");
		Client client = new Client();
		client.processMessage("client send");

	}

}
