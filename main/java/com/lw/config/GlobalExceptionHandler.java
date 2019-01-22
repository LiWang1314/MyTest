package com.lw.config;
/*@ControllerAdvice 是 controller 的一个辅助类，最常用的就是作为全局异常处理的切面类
@ControllerAdvice 可以指定扫描范围
@ControllerAdvice 约定了几种可行的返回值，如果是直接返回 model 类的话，需要使用 @ResponseBody 进行 json 转换
o返回 String，表示跳到某个 view
o返回 modelAndView
o返回 model + @ResponseBody*/
/**
 * @Auther: 李旺
 * @Date: 2018/9/27 0027
 * @Description: 全局异常捕获
 */
import com.lw.util.CommonException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ExceptionHandler(value = CommonException.class)
    @ResponseBody
    public Map myErrorHandler(HttpServletRequest req, CommonException ex) {
        logger.error("自定义异常");
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("message", ex.getMessage());
        return map;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map errorHandler(HttpServletRequest req, Exception ex) {
        logger.error("系统异常");
        Map map = new HashMap();
        map.put("message", ex.getMessage());
        map.put("url", req.getRequestURL());
        map.put("params", req.getParameterMap());
        return map;
    }

}

