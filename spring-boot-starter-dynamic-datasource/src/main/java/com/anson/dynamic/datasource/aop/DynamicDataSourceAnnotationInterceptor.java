package com.anson.dynamic.datasource.aop;

import com.anson.dynamic.datasource.annotation.DS;
import com.anson.dynamic.datasource.util.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源AOP核心拦截器
 *
 * @author Anson.pei
 * @since 1.2.0
 */
@Slf4j
public class DynamicDataSourceAnnotationInterceptor implements MethodInterceptor {

    /**
     * 缓存方法注解值
     */
    private static final Map<Method, String> METHOD_CACHE = new HashMap<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            log.info("在方法{}执行之前进行执行数据源绑定", invocation.getMethod().getName());
            String datasource = determineDatasource(invocation);
            DynamicDataSourceContextHolder.setDataSourceLookupKey(datasource);
            return invocation.proceed();
        } finally {
            log.info("在方法{}执行之后进行执行数据源清除", invocation.getMethod().getName());
            DynamicDataSourceContextHolder.clearDataSourceLookupKey();
        }
    }

    private String determineDatasource(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        if (METHOD_CACHE.containsKey(method)) {
            return METHOD_CACHE.get(method);
        } else {
            DS ds = method.isAnnotationPresent(DS.class) ? method.getAnnotation(DS.class)
                    : AnnotationUtils.findAnnotation(method.getDeclaringClass(), DS.class);
            if (ds.value().isEmpty()) {
                throw new RuntimeException("2.0版本必须配置每一个value");
            }
            METHOD_CACHE.put(method, ds.value());
            return ds.value();
        }
    }

}