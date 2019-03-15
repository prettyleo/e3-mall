package cn.e3mall.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FileName: GlobalExceptionHandler
 * DESCRIPTION: 全局异常处理器
 *
 * @author: SLY
 * @create: 2019/2/3
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        log.error("系统异常");
        ModelAndView model = new ModelAndView();
        model.setViewName("error/exception");
        return model;
    }
}
