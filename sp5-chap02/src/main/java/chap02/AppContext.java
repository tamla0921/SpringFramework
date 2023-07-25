package chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 해당 클래스를 스프링 설정 클래스로 지정?
// xml파일 대체하는 역할

@Configuration
public class AppContext {

    // 일종의 xml 내 태그 역할
    // 객체를 한 개 생성하고 초기화하는 설정.
    // 스프링이 생성하는 객체를 빈(Bean) 객체라고 부름.
    // 메서드에 붙이면 해당 메서드가 생성한 객체를 스프링이 관리하는 빈 객체로 등록.
    // 메서드 이름은 bean 객체를 참조할 때 사용함.
    
    @Bean
    public Greeter greeter() {
        Greeter g = new Greeter();
        // @bean 애너테이션을 붙인 메서드는 객체를 생성하고 초기화해야 함.
        g.setFormat("%s, 안녕하세요");
        return g;
    }
}
