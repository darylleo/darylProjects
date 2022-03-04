package com.daryl.customtemplate.annotation;


import com.daryl.domain.dtos.ResponseResult;
import com.daryl.customtemplate.MyException;
import org.springframework.http.HttpEntity;

import java.lang.annotation.*;

/**
 * @author wl
 * @create 2022-01-21
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Error {
    String rspMsg() default "操作失败";

    String rspCode() default "999999";

    Class<? extends Exception> exception() default MyException.class;

    Class<?>[] ignore() default {HttpEntity.class, ResponseResult.class};
}
