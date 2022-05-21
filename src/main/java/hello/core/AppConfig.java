package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AppConfig처럼 객체를 생성하고 관리하면서 의존관계를 연결해주는 것을 IoC컨테이너 또는 DI 컨테이너라고 한다.
 * 실제 동작에 필요한 구현 객체를 생성한다.
 * 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
 * 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전(IoC)이라 한다.
 */

@Configuration
public class AppConfig {
    // ctrl E 과거 히스토리 다 뜸

    // 빈 이름은 항상 다른 이름을 부여해야 한다.
    // 빈 조회 - 부모 타입으로 조회하면 자식 타입도 함께 조회된다.

    // BeanFactory : 스프링 컨테이너의 최상위 인터페이스이다.
    // 스프링 빈을 관리하고 조회하는 역할을 담당한다.

    // ApplicationContext : BeanFactory 기능을 모두 상속받아서 제공한다.
    // 애플리케이션을 개발할 때 빈을 관리하고 조회하는 기능은 물론이고 수많은 부가기능이 필요하다. (BeanFactory와 다른점)
    // 메시지소스를 활용한 국제화 기능 (한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력)
    // 환경변수 : 로컬, 개발, 운영 등을 구분해서 처리
    // 애플리케이션 이벤트 : 이벤트를 발행하고 구독하는 모델을 편리하게 지원
    // 편리한 리소스 조회 : 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회

    // BeanDefinition 추상화 - 역할과 구현을 개념적으로 나눈 것
    // 자바 코드를 읽어서 BeanDefinition을 만들면 된다.
    // 스프링 컨테이너는 자바코드인지, xml인지 몰라도 된다. 오직 BeanDefinition만 알면 된다.
    // BeanDifinition을 빈 설정 메타정보라 한다.
    // @Bean, <bean>당 각각 하나씩 메타 정보가 생성된다.

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository(), new RateDiscountPolicy()
    //-> 싱글톤이 깨질것인가 -> no, 동일한 인스턴스임 --> @Configuration 덕분
    //-> @Bean이 붙은 메서드마다 이미 스프링 빈에 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로
    // 등록하고 반환하는 코드가 동적으로 만들어진다. -> 싱글톤 보장


    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    @Bean   // 스프링 컨테이너에 등록됨
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // ctrl alt M
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("call AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }
}
