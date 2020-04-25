package com.mh.heartbeat.dbaccess.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;
import org.springframework.data.util.ParsingUtils;

@SpringBootApplication
public class SpringBootDBAccessApplication{

  public static void main(String[] args) {
    SpringApplication.run(SpringBootDBAccessApplication.class, args);
  }

  @Bean
  public NamingStrategy namingStrategy() {
    return new NamingStrategy() {
      @Override
      public String getColumnName(RelationalPersistentProperty property) {
        return ParsingUtils.reconcatenateCamelCase(property.getName(), "");
      }
    };
  }

}
