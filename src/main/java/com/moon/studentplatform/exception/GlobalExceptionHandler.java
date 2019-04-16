package com.moon.studentplatform.exception;

import com.moon.studentplatform.dto.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Moon
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error/exception";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView();
        ErrorInfo<String> errorInfo = new ErrorInfo<>();
        errorInfo.setCode(ErrorInfo.ERROR)
                .setMessage(e.getMessage())
                .setUrl(request.getRequestURL().toString())
                .setData(e.toString());

        //暂时在控制台输出 ，项目完善logger打印
        e.printStackTrace();
        mav.addObject("errorInfo",errorInfo);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

}
