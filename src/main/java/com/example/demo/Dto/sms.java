package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder

public class sms {
    private String ApiKey;
    private String Content;
    private String Phone;
    private String SecretKey;
    private String SmsType;
    private String Brandname;
    private int Sandbox;
    private String campaignid;
}
