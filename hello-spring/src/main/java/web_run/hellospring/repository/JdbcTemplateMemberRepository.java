package web_run.hellospring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import web_run.hellospring.domain.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate; // 템플릿을 사용하려고 할때 이것을 선언한다.


    public JdbcTemplateMemberRepository(DataSource dataSource) // 템플릿은 인젝션을 받는것이 아니기 때문에 데이터 소스가 필요하다.
    {
        jdbcTemplate =new JdbcTemplate(dataSource);
    }


    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert=new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withSchemaName("member").usingGeneratedKeyColumns("id");

        Map<String, String> paraeters=new HashMap<>();
        paraeters.put("name",member.getName());


        Number key=jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(paraeters));
        member.setID(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member",memberRowMapper());
    }

    @Override
    public Optional<Member> findByname(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),name);
        return result.stream().findAny();
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
           Member member = new Member();
           member.setID(rs.getLong("id"));
           member.setName(rs.getString("name"));
           return member;
        };
    }

}
