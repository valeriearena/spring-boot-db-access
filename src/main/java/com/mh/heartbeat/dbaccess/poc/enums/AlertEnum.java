package com.mh.heartbeat.dbaccess.poc.enums;

public enum AlertEnum {

  RED_ALERT("Red Alert"),
  BLUE_ALERT("Blue Alert");

  private String description;

  private AlertEnum(String description){
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public static AlertEnum fromDescription(String description){
    for(AlertEnum alertEnum:AlertEnum.values()){
      if(alertEnum.getDescription().equals(description)){
        return alertEnum;
      }
    }
    return null;
  }
}
