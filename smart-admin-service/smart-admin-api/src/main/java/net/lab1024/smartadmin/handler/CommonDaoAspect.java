package net.lab1024.smartadmin.handler;

import net.lab1024.smartadmin.module.system.employee.domain.bo.EmployeeBO;
import net.lab1024.smartadmin.util.SmartRequestTokenUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年09月13日 15:00
 * @Description:
 */
// @Aspect:切面类，作用是把当前类标识为一个切面供容器读取
@Aspect
@Component
@Configuration
public class CommonDaoAspect {

    private static final String createUser = "createUser";

    private static final String createTime = "createTime";

    private static final String updateUser = "updateUser";

    private static final String updataTime = "updateTime";

    // Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。方法签名必须是 public及void型。
    // 可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。
    // 因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
    // 表示在dao目录下的任何文件中的以update开头的方法
    @Pointcut("execution(* net.lab1024.smartadmin.module.*.*.dao.update*(..))")
    public void daoUpdate() {
    }

    @Pointcut("execution(* net.lab1024.smartadmin.module.*.*.dao.insert*(..))")
    public void daoInsert() {
    }

    // @Around：环绕增强，相当于MethodInterceptor
    @Around("daoUpdate()")
    public Object doDaoUpdate(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return pjp.proceed();
        }
        EmployeeBO user = SmartRequestTokenUtil.getRequestUser().getEmployeeBO();
        if (user.getId() != null) {
            // 访问目标方法的参数
            Object[] objects = pjp.getArgs();
            if (ArrayUtils.isNotEmpty(objects)) {
                for (Object arg : objects) {
                    BeanUtils.setProperty(arg, updateUser, user.getId());
                    BeanUtils.setProperty(arg, updataTime, new Date());
                }
            }
        }
        Object object = pjp.proceed();
        return object;
    }

    /*
        @AfterReturning：后置增强，相当于AfterReturningAdvice，方法正常退出时执行
        @Before：标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
        @AfterThrowing：异常抛出增强，相当于ThrowsAdvice
        @After: final增强，不管是抛出异常或者正常退出都会执行
    */
}
