package web_run.hellospring.service;

import org.springframework.stereotype.Service;
import web_run.hellospring.domain.Member;
import web_run.hellospring.repository.MemberRepository;
import web_run.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository=memberRepository;
    }
    /*
     * 회원가입
     */
    public Long join(Member member) // 임의로 long은 id를 반환하기 위해 되어 있는 것이다.
    {
        validateDuplicateMember(member); // 중복회원 검증 .
        memberRepository.save(member);
        return member.getID();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByname(member.getName()).
        ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */

    public List<Member> findMembers()// 회원 전부 출력
    {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) // (추정) 아이디를 통하여 회원추
    {
        return memberRepository.findById(memberId);
    }
}
