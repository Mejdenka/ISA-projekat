package JV20.isapsw;

import JV20.isapsw.model.AdministratorKlinickogCentra;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IsapswApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsapswApplication.class, args);
	}

}
