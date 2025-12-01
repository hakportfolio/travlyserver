package com.study.travly.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Member 엔티티를 위한 Repository 인터페이스입니다. Spring Data JPA의 JpaRepository를 상속받아 기본적인
 * CRUD 기능을 제공받습니다.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	// Member 엔티티와 그 기본 키 타입(Long)을 지정합니다.

	// 추가적인 쿼리 메서드가 필요하다면 이곳에 정의할 수 있습니다.
	// 예: 이메일로 회원을 찾는 메서드
	// Optional<Member> findByEmail(String email);

}