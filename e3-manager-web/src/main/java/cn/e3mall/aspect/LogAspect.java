package cn.e3mall.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * FileName: LogAspect
 * DESCRIPTION: 日志切面类
 *
 * @author: Liyou Shen
 * @create: 2019/1/13
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * cn.e3mall.controller.*.*(..))")
    public void log() {};

    @Before("log()")
    public void requestLog(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\r请求开始");

        // 记录请求URL
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        sb.append("\n\r请求URL: " + request.getRequestURL());

        // 记录请求方式
        sb.append("\n\r请求方式: " + request.getMethod());

        // 记录请求参数
        Object[] args = joinPoint.getArgs();
        sb.append("\n\r请求参数: ");
        if (args.length > 0) {
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest == false) {
                    sb.append("\n\r " + JSONObject.toJSONString(arg, true));
                }
            }
        }

        if (args.length == 1 && args[0] instanceof HttpServletRequest) {
            sb.append("无");
        }


        log.info(sb.toString());
    }

    @AfterReturning(pointcut = "log()", returning = "result")
    public void respLog(Object result) {
        log.info("\n\r请求结束, 响应参数:\n\r" + JSONObject.toJSONString(result, true));
    }


}
