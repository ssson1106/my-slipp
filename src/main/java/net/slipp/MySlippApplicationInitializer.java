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
	
	/* tomcat 배포 방법
	 * 1. 서버에 톰켓 설치하고(zip, tar?)
	 * 2. bin 폴더 안에 ./startup.sh 하면 톰켓 구동 되고 localhost:port 로 정상적으로 톰켓화면 들어가는지 확인
	 * 3. 다시 킬하고 
	 * 4. 변경된 소스코드 서버에 다운
	 * 5. ./mvnw clean package 명령으로 war 파일로 패키징한다.
	 * 6. 소스의 target 폴더 안에 있는 패키징한 버전의 폴더 또는 war 파일을 배포한다.
	 * 7. tomcat/webapps/ 폴더 안에다가 우리의 패키지를 넣어준다.
	 * 8. 폴더 안의 ROOT 폴더가 기본 폴더라고 한다.
	 * 9. ROOT 폴더 삭제하고 우리의 폴더를 복사해서 넣고 ROOT 폴더로 만들어준다.
	 * 10. 만약 ROOT 폴더로 안하면 url:8080/패키지이름/ 이런식으로 해야하는 듯하다.
	 * 11. 하나의 톰켓에 여러개의 웹 어플리케이션 서비스가 가능하다.
	 * 12. tomcat/logs/catalina.out 에 이클립스 콘솔에서 보던 정보를 확인할수 있다.
	 * 
	 * 13. 서버 shutdown 하고 싶으면 tomcat/bin 안에 shutdown.sh 쓰면 됨  
	 * 14. jar로 하면 정적 파일들에서 문제점 생길 수도 있다고 한다. 왜 그런지는 검색해보면 될듯
	 * */
}
