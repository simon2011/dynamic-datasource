package com.anson.dynamic.datasource;

import com.anson.dynamic.datasource.autoconfigure.DynamicDataSource;
import com.anson.dynamic.datasource.autoconfigure.DynamicDataSourceProperties;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * YML数据源提供者
 *
 * @author Anson.pei
 * @since 1.0.0
 */
@Slf4j
public class YmlDynamicDataSourceProvider extends AbstractDynamicDataSourceProvider implements DynamicDataSourceProvider {

    private DynamicDataSourceProperties properties;

    public YmlDynamicDataSourceProvider(DynamicDataSourceProperties properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, DataSource> loadDataSources() {
        Map<String, DynamicDataSource> dataSourcePropertiesMap = properties.getDatasource();
        Map<String, DataSource> dataSourceMap = new HashMap<>(dataSourcePropertiesMap.size());
        for (Map.Entry<String, DynamicDataSource> item : dataSourcePropertiesMap.entrySet()) {
            dataSourceMap.put(item.getKey(), createDataSource(item.getValue()));
        }
        return dataSourceMap;
    }

}
