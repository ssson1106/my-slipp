package net.slipp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing//entity 기본 설정 하려면 이거 해줘야 한다고함
public class MySlippApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySlippApplication.class, args);
	}
	
}
