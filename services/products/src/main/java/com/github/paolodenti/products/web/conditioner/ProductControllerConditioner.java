package com.github.paolodenti.products.web.conditioner;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Network conditioner, to simulate slow responsiveness from ProductRepository
 */

@Aspect
@Component
public class ProductControllerConditioner {

    final private Logger logger = LoggerFactory.getLogger(ProductControllerConditioner.class);

    private long delay = 0L;

    public void setMillisDelay(long delay) {
        this.delay = delay;
    }

    @Before(value = "execution(* com.github.paolodenti.products.web.ProductController.products())")
    public void beforeAdvice(JoinPoint joinPoint) {
        logger.debug("Before method: {}", joinPoint.getSignature());
        if (delay > 0L) {
            logger.warn("Conditioner is on, sleeping for {} milliseconds", delay);
            try {
                Thread.sleep(5000);
                logger.warn("back from sleeping");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
