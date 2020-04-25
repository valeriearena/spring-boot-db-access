package com.mh.heartbeat.dbaccess.poc;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import java.util.Collections;

@Configuration
public class SpringDataJdbcConfiguration extends AbstractJdbcConfiguration {

  @Override
  public JdbcCustomConversions jdbcCustomConversions() {

    return new JdbcCustomConversions(Collections.singletonList(TinyIntToBooleanConverter.INSTANCE));

  }

  @ReadingConverter
  enum TinyIntToBooleanConverter implements Converter<Short, Boolean> {

    INSTANCE;

    @Override
    public Boolean convert(Short source) {

      if(source == null || source == 0){
        return false;
      }

      return true;
    }
  }
}