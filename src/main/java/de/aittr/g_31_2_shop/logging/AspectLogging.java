package de.aittr.g_31_2_shop.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AspectLogging {

    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);

//    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getAllActiveProducts(..))")
//    public void getProducts() {}
//
//    @Before("getProducts()")
//    public void beforeGetProducts() {
//        logger.info("Вызван метод getAllActiveProducts.");
//    }
//
//    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.restoreById(int))")
//    public void restoreProduct() {}
//
//    @After("restoreProduct()")
//    public void afterRestoreProduct(JoinPoint joinPoint) {
//        Object[] args = joinPoint.getArgs();
//
//        logger.info("Вызван метод restoreById с идентификатором {}.", args[0]);
//    }
//
//    public void testAdvice(JoinPoint joinPoint) {
//        // (String name, int id) -> ["petya", 6] Вызван метод с параметрами, petya, 6
//        // (int id) -> [7] Вызван метод с параметрами 7
//        // () -> []
//        // (double price, String name, Cat cat, Product product)
//        Object[] args = joinPoint.getArgs();
//        StringBuilder builder = new StringBuilder("Вызван метод с параметрами: ");
//        for (Object arg : args) {
//            builder.append(arg).append(", ");
//        }
//        // Вызван метод с параметрами: 7, Petya, 4.56, cat,
//        builder.setLength(builder.length() - 2);
//        builder.append(".");
//        logger.info(builder.toString());
//    }
//
//    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getActiveProductById(int))")
//    public void getActiveProductById() {}
//
//    @AfterReturning(
//            pointcut = "getActiveProductById()",
//            returning = "result"
//    )
//    public void afterReturningProduct(JoinPoint joinPoint, Object result) {
//        Object id = joinPoint.getArgs()[0];
//        logger.info("Метод getActiveProductById вызван с параметром {} " +
//                "и успешно вернул результат: {}.", id, result);
//    }
//
//    @AfterThrowing(
//            pointcut = "getActiveProductById()",
//            throwing = "e"
//    )
//    public void throwingExceptionWhileReturningProduct(JoinPoint joinPoint, Exception e) {
//        Object id = joinPoint.getArgs()[0];
//        logger.warn("Метод getActiveProductById вызван с параметром {} " +
//                "и выбросил ошибку: {}.", id, e.getMessage());
//    }
//
//    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.getActiveProductCount(..))")
//    public void getActiveProductCount() {}
//
//    @Around("getActiveProductCount()")
//    public Object aroundGettingProductCount(ProceedingJoinPoint joinPoint) {
//        // Код, выполняющийся до исходного метода
//        logger.info("Вызван метод getActiveProductCount.");
//        long start = System.currentTimeMillis();
//
//        Object result;
//
//        try {
//            result = joinPoint.proceed();
//        } catch (Throwable e) {
//            throw new RuntimeException(e);
//        }
//
//        // Код, выполняющийся после исходного метода
//        long time = System.currentTimeMillis() - start;
//        logger.info("Метод getActiveProductCount отработал " +
//                "за {} миллисекунд с результатом {}.", time, result);
//
//        logger.info("Подменяем действительный результат на своё значение - 500.");
//        return 500;
//    }

    /*
    Домашнее задание 17
    2. При помощи АОП сделать логирование всех методов сервиса продуктов (при помощи одного Pointcut).
    Для задания Pointcut использовать JpaProductService.*(..). В лог должно выводиться:
    А. Какой метод и с какими параметрами вызван.
    Б. Какой метод завершил работу.
     */

//    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa.JpaProductService.*(..))")
//    public void productServiceMethods() {}
//
//    @Before("productServiceMethods()")
//    public void beforeProductServiceMethods(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        Object[] args = joinPoint.getArgs();
//        logger.info("Method {} of the class JpaProductService called with arguments {}",
//                methodName, Arrays.toString(args));
//    }
//
//    @After("productServiceMethods()")
//    public void afterProductServiceMethods(JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().getName();
//        logger.info("Method {} of the class JpaProductService finished its work", methodName);
//    }

    /*
    Домашнее задание 17
    3. При помощи АОП сделать логирование всех сервисов (при помощи одного Pointcut). В лог должно выводиться:
    А. Какой метод какого класса и с какими параметрами вызван.
    Б. Какой метод какого класса завершил работу.
    В. Какой метод какого класса успешно вернул результат.
    Г. Какой метод какого класса вызвал ошибку.
     */

    @Pointcut("execution(* de.aittr.g_31_2_shop.services.jpa..*(..))")
    public void allServicesMethods() {
    }

    @Before("allServicesMethods()")
    public void beforeAllServicesMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        Object[] args = joinPoint.getArgs();
        logger.info("Method {} of the class {} called with arguments {}",
                methodName, className, Arrays.toString(args));
    }

    @After("allServicesMethods()")
    public void afterAllServicesMethods(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        logger.info("Method {} of the class {} finished its work",
                methodName, className);
    }

    @AfterReturning(
            pointcut = "allServicesMethods()",
            returning = "result"
    )
    public void afterAllServicesMethods(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        logger.info("Method {} of the class {} successfully returned result: {}",
                methodName, className, result);
    }

    @AfterThrowing(
            pointcut = "allServicesMethods()",
            throwing = "e"
    )
    public void afterAllServicesMethods(JoinPoint joinPoint, Throwable e) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        logger.error("Method {} of the class {} produced an error: {}",
                methodName, className, e.getMessage());
    }
}