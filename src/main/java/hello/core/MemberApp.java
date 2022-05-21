package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
 //       AppConfig appConfig = new AppConfig();
 //       MemberService memberService = appConfig.memberService();

        // 스프링 컨테이너 생성
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // ApplicationContext는 인터페이스
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);   // 메서드 이름으로 찾아옴

        Member member = new Member(1L, "memberA", Grade.VIP); // ctrl alt v
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
