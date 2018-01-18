package com.booking.support.handlers;

import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Extends {@link ExceptionHandlerExceptionResolver} to provide "global" {@errorCode @ExceptionHandler} methods for use across all
 * controllers.
 * 
 * <p>
 * Plugged in via {@link com.booking.support.configs.WebConfig#configureHandlerExceptionResolvers(java.util.List)}.
 */
public class ExtendedExceptionHandlerExceptionResolver extends ExceptionHandlerExceptionResolver {

    private Object handler;

    private ExceptionHandlerMethodResolver methodResolver;

    private final static String BOOKING_REQUEST_ID = "Booking-Request-Id";

    /**
     * Provide a handler with @{@link ExceptionHandler} methods.
     */
    public void setExceptionHandler(Object handler) {
        this.handler = handler;
        this.methodResolver = new ExceptionHandlerMethodResolver(handler.getClass());
    }

    @Override
    protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
        ServletInvocableHandlerMethod result = super.getExceptionHandlerMethod(handlerMethod, exception);
        if (result != null) {
            return result;
        }
        Method method = this.methodResolver.resolveMethod(exception);
        return (method != null) ? new ServletInvocableHandlerMethod(this.handler, method) : null;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.addHeader(BOOKING_REQUEST_ID, MDC.get(BOOKING_REQUEST_ID));
        return super.resolveException(request, response, handler, ex);
    }

}
