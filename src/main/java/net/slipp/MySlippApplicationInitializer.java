package net.slipp;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class MySlippApplicationInitializer extends SpringBootServletInitializer	{
	
	/* 메인 함수를 호출하는 클레스 초기화? 
	 * 배포시 내부 톰켓 사용 안할때는 이 부분이 선언되어야 작동한다.*/
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MySlippApplication.class);
	}
}
