package com.ozan.exchange.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class ErrorLogging {

    private static final Logger LOG = LogManager.getLogger(ErrorLogging.class);

    @AfterThrowing(value="execution(* com.ozan.exchange.controller.*.*(..))",
            throwing="e")
    public void doRecoveryActions(JoinPoint point, Exception e) {
        LOG.info(point.getSignature().getName() + " at " + new Date() + " and threw " + e.getMessage());
    }
}
