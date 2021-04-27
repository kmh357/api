package com.rest.api.advice;

import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.model.result.CommonResult;
import com.rest.api.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;
    private final MessageSource messageSource;

    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e){
        return responseService.getFailResult(Integer.parseInt(getMessage("userNotFound.code")), getMessage("userNotFound.msg"));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultExceptionHandler(HttpServletRequest request, Exception e){
        e.printStackTrace();
        return responseService.getFailResult(Integer.parseInt(getMessage("unKnown.code")), getMessage("unKnown.msg"));
    }



    private String getMessage(String code){
        return getMessage(code, null);
    }

    private String getMessage(String code, Object[] args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
