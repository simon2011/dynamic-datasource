package com.anson.dynamic.datasource.strategy;


import javax.sql.DataSource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 负载均衡策略
 *
 * @author Anson.pei
 * @since 1.0.0
 */
public class LoadBalanceDynamicDataSourceStrategy implements DynamicDataSourceStrategy {

    private AtomicInteger index = new AtomicInteger(0);

    @Override
    public DataSource determineDataSource(List<DataSource> dataSources) {
        return dataSources.get(Math.abs(index.getAndAdd(1)) % dataSources.size());
    }

}
