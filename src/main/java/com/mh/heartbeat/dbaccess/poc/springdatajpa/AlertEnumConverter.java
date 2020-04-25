package com.mh.heartbeat.dbaccess.poc.springdatajpa;

import com.mh.heartbeat.dbaccess.poc.enums.AlertEnum;
import io.micrometer.core.instrument.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AlertEnumConverter implements AttributeConverter<AlertEnum, String> {

  @Override
  public String convertToDatabaseColumn(AlertEnum alertEnum) {

    if(alertEnum != null){
      return alertEnum.getDescription();
    }

    return null;
  }

  @Override
  public AlertEnum convertToEntityAttribute(String alertDescription) {

    if(!StringUtils.isBlank(alertDescription)){
      return AlertEnum.fromDescription(alertDescription);
    }

    return null;
  }
}
