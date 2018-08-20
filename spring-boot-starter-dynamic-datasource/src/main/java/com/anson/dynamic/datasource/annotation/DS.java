package com.anson.dynamic.datasource.annotation;

import java.lang.annotation.*;

/**
 * 注解在类上或方法上来切换数据源
 *
 * @author Anson.pei
 * @since 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DS {

    /**
     * 组名或者某一个数据源名称
     *
     * @return 数据源名称
     */
    String value();

}