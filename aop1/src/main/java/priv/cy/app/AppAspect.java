package priv.cy.app;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import priv.cy.dao.Dao;
import priv.cy.dao.IndexDao;

/**
 * 这个类就是一个切面
 * 切面就是切点+通知
 * 所在在这个类里面还需要定义切点和通知
 */
@Component
@Aspect("perthis(this(com.chenss.dao.IndexDaoImpl))")
@Scope("prototype")
public class AppAspect {

    /**
     * 将@Pointcut注解作用于一个方法，这就定义了一个切点，注解中表示的是priv.cy.dao包下面的所有类的所有任意修饰符的方法都是连接点(方法括号里面的..表示的是任意参数)
     * 将这些连接点集中到一起的集合就是切点
     *
     * 方法名任意，以后这个方法名就代表这个切点
     */
    @Pointcut("execution(* priv.cy.dao.*.*(..))")
    public void pointCutExecution() {
    }

    @Pointcut("within(priv.cy.dao.*)")
    public void pointCutWithin() {
    }

    @Pointcut("args(java.lang.String)")
    public void pointCutArgs() {
    }

    @Pointcut("@annotation(priv.cy.anno.TAT)")
    public void pointCutAnnotation() {
    }

    @Pointcut("this(priv.cy.dao.IndexDao)")
    public void pointCutThis() {
    }

    @Pointcut("target(priv.cy.dao.IndexDao)")
    public void pointCutTarget() {
    }

    @DeclareParents(value = "priv.cy.dao.*", defaultImpl = IndexDao.class)
    public static Dao dao;



    /**
     * 下面来定义advice(通知)
     * 通知包括 通知的代码逻辑以及要将通知织入到什么位置
     */

    /**
     * 这就是一个advice
     * advice=代码逻辑+织入位置
     * 织入位置就是通过开头的注解表示的，将上面定义的代表切点的方法作为参数传入注解，下面这个注解表示，插入到上面pointCut()方法所代表的切点中所有的连接点的前面
     * 连接点就是一个方法，也就是说要插入到方法前
     *
     * 要插入的内容（代码逻辑）就是写道这个注解标识的方法中，方法名任意
     */
    @Around("pointCutTarget()")
    public void around(ProceedingJoinPoint joinPoint) {
        Object args[] = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                args[i] += " hahaha";
            }
        }

        System.out.println("a");
        try {
            joinPoint.proceed(args);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("b");

    }

    @After("pointCutTarget()")
    public void after(JoinPoint joinPoint) {
        // 所在的类
        // 目标对象
        // 代理对象
        // 方法参数
        // 方法返回类型
    }


}
