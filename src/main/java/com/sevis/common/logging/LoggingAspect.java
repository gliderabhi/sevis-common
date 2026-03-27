package com.sevis.common.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerLayer() {}

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceLayer() {}

    @Around("controllerLayer() || serviceLayer()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());
        String method = pjp.getSignature().getName();
        long start = System.currentTimeMillis();

        log.info("→ {}()", method);

        try {
            Object result = pjp.proceed();
            log.info("← {}() completed in {}ms", method, System.currentTimeMillis() - start);
            return result;
        } catch (Exception ex) {
            log.error("✗ {}() threw {}: {}", method, ex.getClass().getSimpleName(), ex.getMessage(), ex);
            throw ex;
        }
    }
}
