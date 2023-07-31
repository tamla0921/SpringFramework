package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class CacheAspect {

    private Map<Long, Object> cache = new HashMap<>();
    
    // @Pointcut()
    // public void cacheTarget() {}
    
    // 같은 패키지일 경우, 패키지 이름이 없어도 무방.
    // @Around(ExeTimeAspect.publicTarget()")
    // @Around("ExeTimeAspect.publicTarget()")
    @Around("CommonPointcut.commonTarget()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Long num = (Long) joinPoint.getArgs()[0];
        if (cache.containsKey(num)) {   // key 값이 cache에 존재하면 key에 해당하는 값을 구해서 리턴.
            System.out.printf("CacheAspect: Cache에서 구함[%d]\n", num);
            return cache.get(num);
        }
        
        Object result = joinPoint.proceed(); // key 값이 cache에 존재하지 않으면 프록시 대상 객체 실행.
        cache.put(num, result); //프록시 대상 객체를 실행한 결과를 cache에 추가.
        System.out.printf("CacheAspect: Cache에 추가[%d]\n", num);
        return result; // 프록시 대상 객체의 실행 결과 리턴.
    }
}
