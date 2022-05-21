package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 컴포넌트 스캔을 사용하면 @Configuration이 붙은 설정 정보도 자동으로 등록되기 때문에 excludefilters로 스캔 대상에서 제외
// @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
// 패키지 위치를 지정하지 않고 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것을 권장함
// @SpringBootApplication 안에 @ComponentScan이 들어있다.
// @Component 스캔의 기본 대상
// - @Component , @Controller, @Service, @Repository, @Configuration
// 애노테이션 안에 애노테이션이 들어있다는것을 인식하는 것은 자바가 지원하는게 아니라 스프링이 지원하는 기능
// userDefaultFilters는 기본으로 켜져있음. 끄면 기본 스캔 대상들이 제외된다.
// @Controller : 스프링 MVC 컨트롤러로 인식
// @Repository : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
// @Configuration : 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
// @Service : 특별한 처리를 하지 않는다. 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나라고 비즈니스 계층을 인식하는데 도움이 된다.
@Configuration
@ComponentScan(
        basePackages = "hello.core", // 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 중복이지만 수동 빈이 자동 빈보다 우선순위를 가짐 - 스트링부트는 에러나도록 바뀜
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memoryMemberRepository() {
//        return new MemoryMemberRepository();
//    }
}
