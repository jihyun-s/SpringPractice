package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value = "request") // http 요청당 하나씩 생성되고 http 요청이 끝나는 시점에 소멸된다.

// CGLIB 라이브러리로 내 클래스를 상속받은 가짜 프록시 객체를 만들어서 주입한다.
// 가짜 프록시 객체는 요청이 오면 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다.
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 가짜 만들기
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]"  + " request scope been create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "]"  + " request scope been close:" + this);
    }
}
