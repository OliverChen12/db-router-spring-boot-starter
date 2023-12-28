package org.example.config;

import org.example.DBRouterConfig;
import org.example.Util.PropertyUtil;
import org.example.dynamic.DynamicDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceAutoConfig implements EnvironmentAware {
    private Map<String, Map<String, Object>> dataSourceMap = new HashMap<String, Map<String, Object>>();
    private int dbCount;
    private int tbCount;
    @Bean
    public DBRouterConfig dbRouterConfig(){
        return new DBRouterConfig(dbCount, tbCount);
    }

    @Bean
    public DataSource dataSource(){
        Map<Object,Object> tarrgetDataSources = new HashMap<>();
        for(String dbInfo : dataSourceMap.keySet()){
            Map<String, Object> objMap = dataSourceMap.get(dbInfo);
            tarrgetDataSources.put(dbInfo, new DriverManagerDataSource(objMap.get("url").toString(), objMap.get("username").toString(), objMap.get("password").toString()));
        }
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(tarrgetDataSources);
        return dynamicDataSource;
    }

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "router.jdbc.datasource.";
        dbCount = Integer.valueOf(environment.getProperty(prefix + "dbCount"));
        tbCount = Integer.valueOf(environment.getProperty(prefix + "tbCount"));
        String dataSources = environment.getProperty(prefix + "list");
        for (String dbInfo : dataSources.split(",")){
            Map<String, Object> dataSourceProps = PropertyUtil.handle(environment, prefix + dbInfo, Map.class);
            dataSourceMap.put(dbInfo, dataSourceProps);
        }

    }
}
