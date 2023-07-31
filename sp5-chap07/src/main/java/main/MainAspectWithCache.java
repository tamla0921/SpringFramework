package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import config.AppCtxWithCache;

public class MainAspectWithCache {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxWithCache.class);
        
        Calculator cal = ctx.getBean("calculator", Calculator.class);
        
        // 두 Aspect에서 설정한 Pointcut 모두 Calculator 타입의 factorial() 메서드에 적용.
        cal.factorial(7);
        cal.factorial(7);
        cal.factorial(5);
        cal.factorial(5);

        ctx.close();

    }

}
