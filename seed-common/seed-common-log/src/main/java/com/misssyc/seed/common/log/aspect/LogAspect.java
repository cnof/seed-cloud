package com.misssyc.seed.common.log.aspect;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.misssyc.seed.api.admin.entity.SysLog;
import com.misssyc.seed.common.core.utils.IpUtils;
import com.misssyc.seed.common.core.utils.StringUtils;
import com.misssyc.seed.common.log.annotation.Log;
import com.misssyc.seed.common.log.service.AsyncSaveLog;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * @author 33992
 * @since 2024/2/21
 **/
@Slf4j
@Aspect
@Component
public class LogAspect {

    ThreadLocal<SysLog> logThreadLocal = new ThreadLocal<>();

    @Resource
    AsyncSaveLog asyncSaveLog;

    @Before("@annotation(com.misssyc.seed.common.log.annotation.Log)")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Log aLog = methodSignature.getMethod().getDeclaredAnnotation(Log.class);
        String title = aLog.title();
        if (StringUtils.isEmpty(title)) {
            ApiOperation apiOperation = methodSignature.getMethod().getDeclaredAnnotation(ApiOperation.class);
            if (apiOperation != null) {
                title = apiOperation.value();
            } else {
                title = methodSignature.getName();
            }
        }
        SysLog sysLog = new SysLog();
        sysLog.setReqUri(request.getRequestURI());
        sysLog.setReqType(request.getMethod());
        sysLog.setReqParam(getParams(joinPoint.getArgs()));
        sysLog.setOperType(aLog.operateType().toString());
        sysLog.setOperIp(IpUtils.getRemoteIP(request));
        sysLog.setModule(aLog.module());
        sysLog.setTitle(title);
        sysLog.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
        logThreadLocal.set(sysLog);
    }

    /**
     * 获取请求参数
     *
     * @param paramsArray 获取请求参数
     */
    private String getParams(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null) {
            for (Object obj : paramsArray) {
                if (null == obj || obj instanceof Map && ((Map<?, ?>) obj).isEmpty() || obj instanceof HttpServletResponse) {
                    continue;
                }
                if (obj instanceof String) {
                    params.append(",").append(obj);
                    continue;
                }
                if (obj instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest) obj;
                    obj = request.getParameterMap();
                }
                try {
                    params.append(",").append(JSON.toJSONString(obj));
                } catch (Exception ex) {
                    log.error("参数转json出错", ex);
                    params.append(obj.toString());
                }
            }
        }
        if (StringUtils.isEmpty(params.toString())) {
            return params.toString();
        }
        return params.substring(1);
    }

    @AfterReturning(value = "@annotation(com.misssyc.seed.common.log.annotation.Log)", returning = "returnValue")
    public void doAfterReturning(Object returnValue) {
        setReturn(0, JSON.toJSONString(returnValue));
    }

    @AfterThrowing(value = "@annotation(com.misssyc.seed.common.log.annotation.Log)", throwing = "e")
    public void doAfterThrowing(Throwable e) {
        StackTraceElement[] elements = e.getStackTrace();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", e.toString());
        if (elements.length > 0) {
            jsonObject.put("stack", elements[0].toString());
        }
        setReturn(1, jsonObject.toJSONString());
    }

    private void setReturn(int state, String remark) {
        SysLog sysLog = logThreadLocal.get();
        if (sysLog == null) {
            return;
        }
        sysLog.setCreateBy("");
        sysLog.setCreateAt(LocalDateTime.now());
        sysLog.setUpdateBy("");
        sysLog.setUpdateAt(LocalDateTime.now());
        sysLog.setOperStatus(state);
        sysLog.setRemark(remark);
        asyncSaveLog.saveLog(sysLog);
    }
}
