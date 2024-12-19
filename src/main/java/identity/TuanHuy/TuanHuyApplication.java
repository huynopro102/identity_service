package identity.TuanHuy;

import identity.TuanHuy.configuration.CloudinaryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class TuanHuyApplication {

	@Value("${cloudinary.cloud_name}")
	private String cloudName;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TuanHuyApplication.class, args);
		TuanHuyApplication app = context.getBean(TuanHuyApplication.class);

		System.out.println("hello world");
		CloudinaryConfig c = context.getBean(CloudinaryConfig.class);
		CloudinaryConfig c1 = context.getBean(CloudinaryConfig.class);

		System.out.println(c);
		System.out.println(c1);
		System.out.println(app.getCloudName());
	}

	public String getCloudName() {
		return cloudName;
	}
}
