package net.slipp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	//만든 api로 데이터베이스와의 연결을 처리하겠다.
	//JpaRepository<연결할 객체 VO, key값 데이터 타입>
	
	//내부에 기본적으로 selectone insert update 기본적으로 다있다 그거 그냥 쓰는듯
}
