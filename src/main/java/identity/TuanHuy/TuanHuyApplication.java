package identity.TuanHuy;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class TuanHuyApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TuanHuyApplication.class, args);
		TuanHuyApplication app = context.getBean(TuanHuyApplication.class);

		System.out.println("hello world");


	}

}
