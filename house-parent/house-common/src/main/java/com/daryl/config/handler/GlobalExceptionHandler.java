package com.daryl.config.handler;


import com.daryl.domain.dtos.ResponseResult;
import com.daryl.domain.enums.HttpCodeEnum;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author wl
 * @create 2022-01-21
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 用来处理bean validation异常
     *
     * @param ex ConstraintViolationException
     * @return Response
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseResult<?> resolveConstraintViolationException(ConstraintViolationException ex) {
        ResponseResult<Object> responseResult = new ResponseResult<>(HttpCodeEnum.PARAM_ERROR.getMsg());
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                msgBuilder.append(constraintViolation.getMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            responseResult.setRspMsg(errorMessage);
            return responseResult;
        }
        responseResult.setRspMsg(ex.getMessage());
        return responseResult;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult<?> resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ResponseResult<Object> responseResult = new ResponseResult<>(HttpCodeEnum.PARAM_ERROR.getMsg());
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
            String errorMessage = msgBuilder.toString();
            if (errorMessage.length() > 1) {
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
            }
            responseResult.setRspMsg(errorMessage);
            return responseResult;
        }
        responseResult.setRspMsg(ex.getMessage());
        return responseResult;
    }
}
