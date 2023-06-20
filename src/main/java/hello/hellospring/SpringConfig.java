package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//자바코드로 직접 스프링빈을 등록하는 방법
@Configuration  // 이것을 읽고 스프링빈에 등록하라고 인식
public class SpringConfig {

    @Bean
    public MemberService memberService(){ //이 로직을 스프링빈에 등록해줌
        return new MemberService(memberRepository()); //memberRepository()을 엮어줌
    }

    @Bean
    public MemberRepository memberRepository(){  //memberRepository() 을 위에다가
        return new MemoryMemberRepository();
    }
}
