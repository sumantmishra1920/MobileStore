package com.mycompany.oms.exceptions;

import java.time.LocalDateTime;

//Class for the response entity to be thrown in case exception occurs
public class ExceptionInformation {
 
    private String exceptionMessage;
    private LocalDateTime timestamp;
    private Integer exceptionCode;

    public String getExceptionMessage() {
        return exceptionMessage;
    }
    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public Integer getExceptionCode() {
        return exceptionCode;
    }
    public void setExceptionCode(Integer exceptionCode) {
        this.exceptionCode = exceptionCode;
    }    
}
