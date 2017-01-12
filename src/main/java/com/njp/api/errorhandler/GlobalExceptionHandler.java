package com.njp.api.errorhandler;

import com.njp.api.model.ErrorResult;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * Created by niejinping on 2017/1/12.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    public ErrorResult noHandlerFoundException(HttpServletRequest req, Exception ex) {
        ErrorResult result = new ErrorResult();
        result.code=200;
        result.message=ex.getMessage();
        result.url = req.getRequestURL().toString();
        return result;
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    public ErrorResult constraintViolationException(HttpServletRequest req, ConstraintViolationException ex) {
        ErrorResult result = new ErrorResult();
        result.code=200;
        result.message=ex.getMessage();
        result.url = req.getRequestURL().toString();
        return result;
    }

    @ExceptionHandler(value = { TypeMismatchException.class })
    public ErrorResult typeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
        ErrorResult result = new ErrorResult();
        result.code=200;
        result.message=ex.getMessage();
        result.url = req.getRequestURL().toString();
        return result;
    }


    @ExceptionHandler(value = Exception.class)
    public ErrorResult defaultErrorHandler(HttpServletRequest req, Exception ex) {
        ErrorResult result = new ErrorResult();
        result.code=200;
        result.message=ex.getMessage();
        result.url = req.getRequestURL().toString();
        return result;
    }

    @ExceptionHandler(value = MyException.class)
    public ErrorResult handleBadRequest(HttpServletRequest req, MyException ex) {
        ErrorResult result = new ErrorResult();
        result.code=200;
        result.message=ex.getMessage();
        result.url = req.getRequestURL().toString();
        return result;
    }

    @ExceptionHandler(value = ConversionNotSupportedException.class)
    public ErrorResult handleBadRequest4(HttpServletRequest req, ConversionNotSupportedException ex) {
        ErrorResult result = new ErrorResult();
        result.code=200;
        result.message="TypeMismatchException";
        result.error="TypeMismatchException";
        result.url = req.getRequestURL().toString();
        return result;
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ErrorResult handleBadRequest5(HttpServletRequest req, HttpMessageNotWritableException ex) {
        ErrorResult result = new ErrorResult();
        result.code=200;
        result.message="HttpMessageNotWritableException";
        result.error="HttpMessageNotWritableException";
        result.url = req.getRequestURL().toString();
        return result;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorResult handleBadRequest3(HttpServletRequest req, MissingServletRequestParameterException ex) {
        ErrorResult result = new ErrorResult();
        result.code=200;
        result.message="MissingServletRequestParameterException";
        result.error="MissingServletRequestParameterException";
        result.url = req.getRequestURL().toString();
        return result;
    }



    @ExceptionHandler(NumberFormatException.class)
    public ErrorResult numberFormatException(HttpServletRequest req, NumberFormatException ex) {
        ErrorResult result = new ErrorResult();
        result.code=200;
        result.message="NumberFormatException";
        result.error="NumberFormatException";
        result.url = req.getRequestURL().toString();
        return result;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResult methodArgumentTypeMismatchException(HttpServletRequest req, MethodArgumentTypeMismatchException ex) {
        ErrorResult result = new ErrorResult();
        result.code=200;
        result.message="MethodArgumentTypeMismatchException";
        result.error="MethodArgumentTypeMismatchException";
        result.url = req.getRequestURL().toString();
        return result;
    }
}