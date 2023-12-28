package web_run.hellospring.service;



import org.junit.jupiter.api.AfterEach;
import web_run.hellospring.domain.Member;
import web_run.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링을 테스트할때 이것을 가장먼저 사용한다.
@Transactional // DB 테스트하지만 DB에 데이터를 반영시키지 않아 여러번의 테스트 케이스 동작을 수행할 수 있다.
class MemberServiceTest {
/* 테스트는 가장 끝단에 있다는 때문에 가장 편한방법으로 작성하는게 좋다.  */
   @Autowired MemberService memberService; // spring 컨테이너에게 구현체를 받아온다..
   @Autowired MemberRepository memberRepository; // " "


    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");
//When
        Long saveId = memberService.join(member);
//Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
//Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
//When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다. assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }



}