package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        //의존관계를 주입해주는 appConfig를 통해 구현체를 가져옴
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = "+ member.getName());
        System.out.println("find Member = "+ findMember.getName());
    }
}
