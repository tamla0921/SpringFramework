package aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcut {
        
    // Bean으로 등록할 필요 없음.
    // @Around 애너테이션에서 해당 클래스에 접근 가능하면 해당 Pointcut을 사용할 수 있음.
    
    @Pointcut("execution(public * chap07..*(..))")
    public void commonTarget() {}
}
