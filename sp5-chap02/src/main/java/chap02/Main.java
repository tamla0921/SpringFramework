package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext; // 자바 설정에서 정보를 읽어와 Bean 객체를 생성하고 관리.

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);  // 작성한 AppContext 클래스를 생성자 파라미터로 전달.
        // AppContext에 정의한 @Bean 애너테이션 설정 정보를 읽어와 Greeter 객체를 생성하고 초기화.
        Greeter g = ctx.getBean("greeter", Greeter.class); // (@Bean 애너테이션의 메서드 이름인 Bean 객체의 이름, 검색할 빈 객체의 타입)
        String msg = g.greet("스프링"); // "스프링, 안녕하세요!"
        System.out.println(msg);
        ctx.close();
    }
}
