
package com.anson.dynamic.datasource.autoconfigure;

import com.anson.dynamic.datasource.DynamicRoutingDataSource;
import com.anson.dynamic.datasource.aop.DynamicDataSourceAnnotationInterceptor;
import com.anson.dynamic.datasource.DynamicDataSourceProvider;
import com.anson.dynamic.datasource.strategy.DynamicDataSourceStrategy;
import com.anson.dynamic.datasource.YmlDynamicDataSourceProvider;
import com.anson.dynamic.datasource.aop.DynamicDataSourceAnnotationAdvisor;
import com.anson.dynamic.datasource.autoconfigure.druid.DruidDynamicDataSourceConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * DynamicDataSourceAutoConfiguration
 *
 * @author Anson.pei
 * @see DynamicDataSourceProvider
 * @see DynamicDataSourceStrategy
 * @see DynamicRoutingDataSource
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@Import(DruidDynamicDataSourceConfiguration.class)
public class DynamicDataSourceAutoConfiguration {

    private final DynamicDataSourceProperties properties;

    public DynamicDataSourceAutoConfiguration(DynamicDataSourceProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        return new YmlDynamicDataSourceProvider(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicRoutingDataSource dynamicDataSource(DynamicDataSourceProvider dynamicDataSourceProvider) {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        dynamicRoutingDataSource.setDynamicDataSourceProvider(dynamicDataSourceProvider);
        dynamicRoutingDataSource.setDynamicDataSourceStrategyClass(properties.getStrategy());
        dynamicRoutingDataSource.setPrimary(properties.getPrimary());
        return dynamicRoutingDataSource;
    }

    @Bean
    @ConditionalOnMissingBean
    public DynamicDataSourceAnnotationAdvisor dynamicDatasourceAnnotationAdvisor() {
        return new DynamicDataSourceAnnotationAdvisor(new DynamicDataSourceAnnotationInterceptor());
    }

}