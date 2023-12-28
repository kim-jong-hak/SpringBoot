package web_run.hellospring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import web_run.hellospring.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
// test 코드의 장점은 단위별로 test를 진행 시켜볼 수 있다. 메서드,객체, 전체 등등ㅇ\
// 테스트 코드는 객체 단위로 한꺼번에 실해알때 메서드들의 순서를 보장하면서 동작하지 않는다. 이러한 이유로 오류가 날 수 있기 때문에
// 메서드가 끝나면 데이터를 모두 지워줘야 한다.
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void reset()
    {
       repository.clearStore();
    }
    @Test // 테스트 코드임을 컴파일러에게 알려주는거 같다.
    public void save() // 저장 기능을 확인하는 테스트 코드이다.
    {
            Member member=new Member();
            member.setName("String");

            repository.save(member);

            Member result=repository.findById(member.getID()).get();
            /*System.out.println("result "+(result==member));
               이렇게 확인하는 코드로 확인한다면 result true 이런식으로 확인힐 수 있지만
               계속 확인하기에는 번거롭다는 단점이 있다.
               따라서 이러한 문제를 보완하고자 assertions 라는 기능이 있다.
             */


      /*  Assertions.assertEquals(member,result);
         성공한다면 출력이 없고 정상적으로 동작했다는 표시만 뜬다.
           만약 실패 했다면?
            Assertions.assertEquals(member,null);
            expected: <web_run.hellospring.domain.Member@353352b6> but was: <null>
            Expected :web_run.hellospring.domain.Member@353352b6
            Actual   :null

            이런식으로 출력되게 된다,
         */

        // 요즘 실무에서는 이걸 많이 사용한다.
        //Assertions.assertThat(member).isEqualTo(result);
        // 에기에서 "option"+"enter"를 같이 누르먄
        assertThat(member).isEqualTo(result);
        //이렇게 줄일 수 있다. 이렇게 하면 동시에 import static org.assertj.core.api.Assertions.*; 이것도 생긴다.



    }

    @Test
    public void findByName()
    {
        Member member1 =new Member();
        member1.setName("String1");
        repository.save(member1);

        Member member2 =new Member();
        member2.setName("String2");
        repository.save(member2);


        Member result=repository.findByname("String1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll()
    {
        Member member1 =new Member();
        member1.setName("String1");
        repository.save(member1);

        Member member2 =new Member();
        member2.setName("String2");
        repository.save(member2);

        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);


    }


}
