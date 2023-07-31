package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;

/**
 * @EnableAspectJAutoProxy @Aspect 애너테이션을 붙인 클래스를 공통 기능으로 적용하기 위해 붙임.
 *                                              스프링은 @Aspect 애너테이션이 붙은 Bean 객체를 찾아서 Bean 객체의 @Pointcut 설정, @Around 설정을 사용.
 * @author seo
 *
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppCtx {
    
    @Bean
    public ExeTimeAspect exeTimeAspect() {
        return new ExeTimeAspect();
    }
    
    /**
     * Calculator 타입이 chap07에 속하므로 calculator Bean에 ExeTimeAspect 클래스에 정의한 공통 기능인 measure() 메서드를 적용.
     * @return
     */
    @Bean
    public Calculator calculator() {
        return new RecCalculator();
    }
}
