package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링컨테이너와 테스트를 함께 실행한다
@Transactional //테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고,
// 테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지않는다
    //테스트케이스 이외에 어노테이션을 사용하면 롤백하지않고 정상적으로 돈다.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;



//    @AfterEach  //테스트 케이스를 한번 실행하고 난 뒤에 공용 데이터들을 깨끗하게 싹 지워줌 //위에 @Transactional 로 대체
//    public void afterEach(){
//        memberRepository.clearStore();
//    }
    @Test
//    @Commit
    void 회원가입() {
        //given 이런게 주어졌어
        Member member = new Member();
        member.setName("spring100");

        //when 이걸 실행했을때
        Long saveId = memberService.join(member);

        //then 그때 결과가 이게 나와야돼
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }


//    @Test
//    void findMembers() {
//    }
//
//    @Test
//    void findOne() {
//    }
}