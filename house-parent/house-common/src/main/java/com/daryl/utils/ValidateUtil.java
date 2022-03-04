package com.daryl.utils;



import com.daryl.domain.dtos.ResponseResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author wl
 * @create 2022-01-13
 */
public class ValidateUtil {
    private ValidateUtil() {
        throw new IllegalStateException("Utility Class");
    }
    /**
     * 参数校验
     *
     * @param o      参数
     * @param groups 参数组
     */
    public static ResponseResult<?> validate(Object o, Class<?>... groups) {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> set = validator.validate(o, groups);
        for (ConstraintViolation<Object> constraintViolation : set) {
            if (constraintViolation != null) {
                return ResponseResult.errorResult(constraintViolation.getMessage());
            }
        }
        return null;
    }
}
