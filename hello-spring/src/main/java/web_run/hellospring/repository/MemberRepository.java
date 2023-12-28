package web_run.hellospring.repository;
import web_run.hellospring.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    Member save(Member member);// 어떤건지 진짜 모르겠다. 검색해도 잘안나오는듯 하다.,
    Optional<Member> findById(Long id);
    Optional<Member> findByname(String name);
    List<Member> findAll(); // 모든걸 출력



}
