package de.aittr.g_31_2_shop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class AspectLogging {

    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getAllActiveProducts(..))")
    public void getProducts() {
    }

    @Before("getProducts()")
    public void beforeGetProducts() {
        logger.info("Вызван метод getAllActiveProducts.");
    }

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.restoreById(int))")
    public void restoreProduct() {
    }

    @After("restoreProduct()")
    public void afterRestoreProduct(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        logger.info("Вызван метод restoreById с идентификатором {}.", args[0]);
    }

    public void testAdvice(JoinPoint joinPoint) {
        // (String name, int id) -> ["petya", 6] Вызван метод с параметрами, petya, 6
        // (int id) -> [7] Вызван метод с параметрами 7
        // () -> []
        // (double price, String name, Cat cat, Product product)
        Object[] args = joinPoint.getArgs();
        StringBuilder builder = new StringBuilder("Вызван метод с параметрами: ");
        for (Object arg : args) {
            builder.append(arg).append(", ");
        }
        // Вызван метод с параметрами: 7, Petya, 4.56, cat,
        builder.setLength(builder.length() - 2);
        builder.append(".");
        logger.info(builder.toString());
    }

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getActiveProductById(int))")
    public void getActiveProductById() {
    }

    @AfterReturning(
            pointcut = "getActiveProductById()",
            returning = "result"
    )
    public void afterReturningProduct(JoinPoint joinPoint, Object result) {
        Object id = joinPoint.getArgs()[0];
        logger.info("Метод getActiveProductById вызван с параметром {} " +
                "и успешно вернул результат: {}.", id, result);
    }

    @AfterThrowing(
            pointcut = "getActiveProductById()",
            throwing = "e"
    )
    public void throwingExceptionWhileReturningProduct(JoinPoint joinPoint, Exception e) {
        Object id = joinPoint.getArgs()[0];
        logger.warn("Метод getActiveProductById вызван с параметром {} " +
                "и выбросил ошибку: {}.", id, e.getMessage());
    }

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getActiveProductCount(..))")
    public void getActiveProductCount() {
    }


    @Around("getActiveProductCount()")
    public Object aroundGettingProductCount(ProceedingJoinPoint joinPoint) {
        // Код, выполняющийся до исходного метода
        logger.info("Вызван метод getActiveProductCount.");
        long start = System.currentTimeMillis();

        Object result;

        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        // Код, выполняющийся после исходного метода
        long time = System.currentTimeMillis() - start;
        logger.info("Метод getActiveProductCount отработал " +
                "за {} миллисекунд с результатом {}.", time, result);

        logger.info("Подменяем действительный результат на своё значение - 500.");
        return result;
    }

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.*(..))")
    public void logAllProductEvents() {
    }

    @Before("logAllProductEvents()")
    public void beforeReturningProductEventLogger(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getDeclaringType().getSimpleName();
        Object[] arguments = joinPoint.getArgs();

        if (arguments.length > 0 ) {
            logger.info("Метод {} вызван с параметрами {} ", method, arguments);
        } else {
            logger.info("Метод {} вызван без параметров ", method);
        }

    }

    @After("logAllProductEvents()")
    public void afterReturningProductEventLogger(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getDeclaringType().getSimpleName();
        logger.info("Метод {} завершен ", method);

    }


    @AfterReturning(
        pointcut = "logAllProductEvents()",
        returning = "result"
    )
    public void afterReturningProductEventLogger1(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().getDeclaringType().getSimpleName();
        logger.info("Метод {} завершен и успешно вернул результат: {}.", method, result);
    }

    @AfterThrowing(
        pointcut = "logAllProductEvents()",
        throwing = "e"
    )
    public void afterReturningProductEventLogger2(JoinPoint joinPoint, Exception e) {
        String method = joinPoint.getSignature().getDeclaringType().getSimpleName();
        logger.info("Метод {} завершен и выбросил ошибку: {}.", method, e.getMessage());
    }


    @Pointcut("execution(* de.aittr.g_31_2_shop.services.*.*.*(..))")
    public void logAllServiceEvents() {
    }

    @Before("logAllServiceEvents()")
    public void beforeAnyServiceEventLogger(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getClass().getName();
        String method = signature.getMethod().getName();

        Object[] arguments = joinPoint.getArgs();

        if (arguments.length > 0 ) {
            Object id = joinPoint.getArgs()[0];
            logger.info("Метод {} класса {} вызван с параметром {} ", method, className, id);
        } else {
            logger.info("Метод {} класса {} вызван без параметров ", method, className);
        }

    }

    @After("logAllServiceEvents()")
    public void afterAnyServiceEventLogger(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getClass().getName();
        String method = signature.getMethod().getName();
        logger.info("Метод {} класса {} завершён.", method, className);

    }

}
