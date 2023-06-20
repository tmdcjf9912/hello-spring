package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//@Service , @Controller, @Repository 는 모두 @Component : 같은 패키지안에서만 찾아서 연결해줌
//그리고 기본적으로 싱글톤으로 등록함 (딱하나만 등록) 따라서 같은 스프링 빈이면 모두 같은 인스턴스이다.
//@Service//스프링빈 등록: 컴포넌트 스캔방식 // 자바코드로 직접 스프링 빈 등록하기위해서 주석처리
public class MemberService {
    private final MemberRepository memberRepository;

//    @Autowired //memberservice 가 생성될때 스프링빈에 등록되어있는 멤버리파지토리를 가져다가 넣어줌 DI(디펜던시 인젝션):의존관계 주입
    //@Autowired 가 @Service , @Controller, @Repository 를 묶어줌
// 자바코드로 직접 스프링 빈 등록하기위해서 주석처리
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; //직접 new 하지않고 외부에서 memberRepository 넣어줌, 이런것을 DI(디펜던시 인젝션)이라고 함
    }


    //회원가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회
     */
    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복회원은 안됨
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
