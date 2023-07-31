package aspect;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * @Aspect 애너테이션을 적용한 클래스는 Advice와 pointcut을 함께 제공.
 * @author seo
 *
 */
@Aspect
@Order(2)
public class ExeTimeAspect {
    
    
    /**
     * @Pointcut 공통 기능을 적용할 대상 설정
     * 여기서는 chap07 패키지와 그 하위 패키지에 위치한 타입의 public 메서드를 Pointcut으로 설정함.
     */
//    @Pointcut("execution (public * chap07..*(..))")
//    public void publicTarget() {
//        
//    }
    
    /**
     * @Around 속성값이 publicTarget(): Pointcut에 공통 기능을 적용하는 것을 의미.
     *                  chap07 패키지, 그 하위 패키지에 속한 Bean 객체의 public 메서드에 @Around가 붙은 measure() 메서드 적용.
     * @param joinPoint ProceedingJoinPoint 타입, 프록시 대상 객체의 메서드를 호출할 때 사용.
     * @return
     * @throws Throwable
     */
    @Around("CommonPointcut.commonTarget()")
    public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        
        try {
            Object result = joinPoint.proceed();
            // 실제 대상 객체의 메서드 호출.
            // 이 메서드를 호출하면 대상 객체의 메서드가 실행되므로 이 코드 이전과 이후에 공통 기능을 위한 코드를 위치.
            return result;
        } finally {
            long finish = System.nanoTime();
            Signature sig = joinPoint.getSignature();
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n", joinPoint.getTarget().getClass().getSimpleName(), sig.getName(), Arrays.toString(joinPoint.getArgs()), (finish - start));
            // getSignature(): 호출한 메서드의 시그니처(메서드 이름 + 파라미터)를 구함.
                // 자바에서는 메서드의 리턴 타입이나 Exception 타입은 시그니처에 포함하지 않음.
            // getTarget(): 대상 객체를 구함.
            // getArgs(): 인자 목록을 구함.
            // 위 메서드를 사용해서 대상 객체의 클래스 이름과 메서드 이름 출력.
        }
    }
}
