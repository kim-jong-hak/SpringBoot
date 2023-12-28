package web_run.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import web_run.hellospring.repository.JDBCMamberRepository;
import web_run.hellospring.repository.JdbcTemplateMemberRepository;
import web_run.hellospring.repository.MemberRepository;
import web_run.hellospring.repository.MemoryMemberRepository;
import web_run.hellospring.service.MemberService;

import javax.sql.DataSource;


@Configuration // 스프링이 이것을 읽고 스프링에 등록하라는 뜻으로 알아 듣는다 .
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) { // 실제 스프링 컨테이너에 데이터 소스를 받아오는 것이다.
        this.dataSource = dataSource;
    }


    @Bean //스프링 빈을 내가 등록할꺼야
    public MemberService memberService()// 멤버 서비스 로직을 읽고 생성된 객체를 스프링에 등록해준다.
    {
        return new MemberService(memberRepository());// 보니까 생성자로 뭘 넣어 줘야 한다. "command+p"를 눌러보니 멤버 리포지토리를 넣으라고 한다.

    }


    // 이렇게 직접 빈을 작업하게 된다면 큰 장점이 하나 생기는데
    //바로 db가 바뀌어도 빈 코드 하나만 잘 바꿔주면 아무 문제없이 교체가 가능하기 때문이다. 자세한건 11:32참고
    @Bean
    public MemberRepository memberRepository()
    {
       // return new MemoryMemberRepository();// 그냥 MemberRepository는 추상객체니까 이것을 상속받은 것을 객체로 생성한다.
     //  return new JDBCMamberRepository(dataSource); //<- 데이터 소스를 받아와 생성자에게 데이터 소스를 넘겨준다.
      // return new JdbcTemplateMemberRepository(dataSource);

        return new MemoryMemberRepository();
    }

}
