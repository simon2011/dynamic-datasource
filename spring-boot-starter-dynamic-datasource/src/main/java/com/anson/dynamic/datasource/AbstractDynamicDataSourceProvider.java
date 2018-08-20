package com.anson.dynamic.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.anson.dynamic.datasource.autoconfigure.DynamicDataSource;
import com.anson.dynamic.datasource.autoconfigure.druid.DruidDataSourceProperties;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 抽象数据源，提供创建常用数据源的方法
 *
 * @author Anson.pei
 * @since 1.1.0
 */
@Slf4j
public abstract class AbstractDynamicDataSourceProvider implements DynamicDataSourceProvider {

    public static final String DRUID_DATASOURCE = "com.alibaba.druid.pool.DruidDataSource";

    protected DataSource createDataSource(DynamicDataSource properties) {
        Class<? extends DataSource> type = properties.getType();
        if (type == null) {
            try {
                Class.forName(DRUID_DATASOURCE);
                return createDruidDataSource(properties);
            } catch (ClassNotFoundException e) {
            }
        } else if (DRUID_DATASOURCE.equals(type.getName())) {
            return createDruidDataSource(properties);
        }
        return properties.initDataSource();
    }

    private DataSource createDruidDataSource(DynamicDataSource properties) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setDriverClassName(properties.getDriverClassName());

        DruidDataSourceProperties druidProperties = properties.getDruid();

        druidDataSource.setInitialSize(druidProperties.getInitialSize());
        druidDataSource.setMaxActive(druidProperties.getMaxActive());
        druidDataSource.setMinIdle(druidProperties.getMinIdle());
        druidDataSource.setMaxWait(druidProperties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setMaxEvictableIdleTimeMillis(druidProperties.getMaxEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidProperties.getValidationQuery());
        druidDataSource.setValidationQueryTimeout(druidProperties.getValidationQueryTimeout());
        druidDataSource.setTestOnBorrow(druidProperties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(druidProperties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
        druidDataSource.setMaxOpenPreparedStatements(druidProperties.getMaxOpenPreparedStatements());
        druidDataSource.setSharePreparedStatements(druidProperties.isSharePreparedStatements());
        druidDataSource.setConnectProperties(druidProperties.getConnectionProperties());
        try {
            druidDataSource.setFilters(druidProperties.getFilters());
            druidDataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

}
