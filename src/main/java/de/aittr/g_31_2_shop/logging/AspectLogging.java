package de.aittr.g_31_2_shop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {

    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getAllActiveProducts(..))")
    public void getProducts() {}

    @Before("getProducts()")
    public void beforeGetProducts() {
        logger.info("Вызван метод getAllActiveProducts.");
    }

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.restoreById(int))")
    public void restoreProduct() {}

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
    public void getActiveProductById() {}

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
    public void getActiveProductCount() {}

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
        return 500;
    }
}