package com.mkl.gmall.admin.aop;

import com.mkl.gmall.to.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @author 马锴梁
 * @version 1.0
 * @date 2019/12/1 14:41
 */
@Slf4j
@Aspect
@Component
public class DataValidAspect {
    @Around("execution(* com.mkl.gmall.admin..*Controller.*(..))")
    public Object validAround(ProceedingJoinPoint point) throws Throwable {
        Object proceed=null;
//        log.debug()
        Object[] args = point.getArgs();
        for(Object object:args){
            if(object instanceof BindingResult){
                BindingResult object1 = (BindingResult) object;
                if(object1.getErrorCount()>0){
                    return new CommonResult().validateFailed(object1);
                }
            }
        }
        proceed =point.proceed(point.getArgs());
        log.debug("切面放行{}",proceed);
        return proceed;
    }
}
