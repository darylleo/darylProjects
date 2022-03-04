package com.daryl.customtemplate.aop;

import com.daryl.domain.dtos.ResponseResult;
import com.daryl.domain.enums.HttpCodeEnum;
import com.daryl.customtemplate.MyException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author wl
 * @create 2022-01-21
 */
@Component
@Aspect
@Slf4j
public class MyExceptionHandler {
    @Around("@annotation(com.daryl.customtemplate.annotation.Error)  || @within(com.daryl.customtemplate.annotation.Error)")
    public Object serviceExceptionHandler(ProceedingJoinPoint proceedingJoinPoint) {
        Object returnMsg;
        //ResponseResult returnMsg;
        try {
            //returnMsg = proceedingJoinPoint.proceed();
            returnMsg =  ResponseResult.okResult(proceedingJoinPoint.proceed());
            log.info("OPERATE SUCCESS  ✿✿✿✿✿✿ ✿✿✿✿✿✿ ✿✿✿✿✿✿");
        } catch (Throwable throwable) {
            //单独处理缺少参数异常
            if(throwable instanceof MyException) {
                returnMsg = ResponseResult.errorResult(999999,throwable.getMessage());
            }else{
                //其他正常返回
                returnMsg = ResponseResult.errorResult(HttpCodeEnum.FAILED);
                log.error("SOMETHING----------------> GOES---------------->  WRONG", throwable);
            }
        }
        return returnMsg;
    }
}
