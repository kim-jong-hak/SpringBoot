package web_run.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web_run.hellospring.domain.Member;
import web_run.hellospring.service.MemberService;

import java.util.List;

@Controller
public class MemberController { // 이것은 생성자 주입 (가장 권장)
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());


        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
/*@Controller
public class MemberController { // 이것은 필드 주입 (필드 주입을 별로 안좋다)
    @Autowired
    private  MemberService memberService;

}*/

/*@Controller
public class MemberController { // 이것은 셋터 주입 (누군가 컨트롤를 호출했을때 public으로 열려 있어야 하기 때문에 딱히 좋은 방법은 아닌거 같다.)

    private  MemberService memberService;
        @Autowired
    public void setMemberService(MemverService memberService)
    {
        this.memberService=memberService;
    }

}*/