package hong.jpapost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpapostApplication {




	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setData("lombok");
		String data = hello.getData();
		System.out.println("data = " + data);

		SpringApplication.run(JpapostApplication.class, args);
	}

}
