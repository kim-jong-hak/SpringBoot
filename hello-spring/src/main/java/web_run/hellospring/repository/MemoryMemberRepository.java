package web_run.hellospring.repository;
import org.springframework.stereotype.Repository;
import web_run.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store=new HashMap<>();
    private static long sequence=0L;

    @Override
    public Member save(Member member) {
                member.setID(++sequence);
                store.put(member.getID(),member);
                return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));// Optional를 하는 이유가 store.get를 하여 null이 나올 수 있기 때문이다.
                                                 // 때문에 Optional를 하면 클라이언트에서 뭔가를 할 수 있다는데... 모르겠다.
    }

    @Override
    public Optional<Member> findByname(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());

    }




    public void clearStore()
    {
        store.clear();
    }
}
