package identity.TuanHuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EntityScan(basePackages = "identity.TuanHuy.UI.entity")
public class TuanHuyApplication {
	public static void main(String[] args) {
		SpringApplication.run(TuanHuyApplication.class, args);
		System.out.println("hello world");
	}

}
