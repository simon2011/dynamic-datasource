package com.anson.dynamic.datasource.autoconfigure;

import com.anson.dynamic.datasource.strategy.DynamicDataSourceStrategy;
import com.anson.dynamic.datasource.strategy.LoadBalanceDynamicDataSourceStrategy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * DynamicDataSourceProperties
 *
 * @author Anson.pei
 * @see DataSourceProperties
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource.dynamic")
public class DynamicDataSourceProperties {

    /**
     * 必须设置默认的库,默认master
     */
    private String primary = "master";

    /**
     * 每一个数据源
     */
    private Map<String, DynamicDataSource> datasource = new LinkedHashMap<>();

    /**
     * 多数据源选择算法clazz，默认负载均衡算法
     */
    private Class<? extends DynamicDataSourceStrategy> strategy = LoadBalanceDynamicDataSourceStrategy.class;

}
