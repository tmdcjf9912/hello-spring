package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller//스프링빈 등록: 컴포넌트 스캔방식
public class MemberController {
    private final MemberService memberService;

    @Autowired  //membercontroller 가 생성될때 스프링빈에 등록되어있는 멤버서비스를 가져다가 넣어줌 DI(디펜던시 인젝션):의존관계 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
