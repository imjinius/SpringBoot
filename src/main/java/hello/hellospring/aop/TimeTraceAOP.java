package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAOP {

    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+joinPoint.toString());
        try{
            // 스프링에서 프록시 객체를 생성해서 참조하다가 proceed() 실행되면 실제 객체를 바라보게함
            // 예를 들면, 멤버 컨트롤러에서 멤버서비스 객체를 의존하고 있는 관계라면 AOP를 설정하면
            // 멤버 컨트롤러가 실제 멤버 서비스 객체가 아닌 가짜 즉 프록시 멤버 서비스 객체를 의존하고 있다가
            // proceed()가 실행되면 실제 객체를 바라보게 되는 것.
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString()+" "+timeMs+"ms");
        }
    }
}
