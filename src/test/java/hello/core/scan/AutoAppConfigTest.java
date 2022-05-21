package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @CompononetScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다.
 * 이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다.
 * 스프링빈 이름 직접 지정도 가능하다.
 *
 * 생성자에 @Autowired를 지정하면 스프링 컨테이너가 자동으로 해당 스프링빈을 찾아서 주입한다.
 * 이때 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다.
 * 생성자에 파라미터가 많아도 다 찾아서 자동으로 주입한다.
 */
public class AutoAppConfigTest {
    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
