package com.mycompany.oms.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//For handling exceptions and giving back the response to the users
@RestControllerAdvice
public class ExceptionControllerAdvice{
	
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionInformation> exceptionHandler(Exception exception){
      ExceptionInformation info=new ExceptionInformation();
      info.setExceptionMessage(exception.getMessage());
      info.setTimestamp(LocalDateTime.now());
      info.setExceptionCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
      return new ResponseEntity<ExceptionInformation>(info,HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @ExceptionHandler(RecordExistsException.class)
  public ResponseEntity<ExceptionInformation> storeExceptionHandler(RecordExistsException exception){
      ExceptionInformation info=new ExceptionInformation();
      info.setExceptionMessage(exception.getMessage());
      info.setTimestamp(LocalDateTime.now());
      info.setExceptionCode(HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<ExceptionInformation>(info,HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ExceptionInformation> storeExceptionHandler(UserNotFoundException exception){
      ExceptionInformation info=new ExceptionInformation();
      info.setExceptionMessage(exception.getMessage());
      info.setTimestamp(LocalDateTime.now());
      info.setExceptionCode(HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<ExceptionInformation>(info,HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(CartNotFoundException.class)
  public ResponseEntity<ExceptionInformation> storeExceptionHandler(CartNotFoundException exception){
      ExceptionInformation info=new ExceptionInformation();
      info.setExceptionMessage(exception.getMessage());
      info.setTimestamp(LocalDateTime.now());
      info.setExceptionCode(HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<ExceptionInformation>(info,HttpStatus.NOT_FOUND);
      
  }
  
  @ExceptionHandler(MobileNotFoundException.class)
  public ResponseEntity<ExceptionInformation> storeExceptionHandler(MobileNotFoundException exception){
      ExceptionInformation info=new ExceptionInformation();
      info.setExceptionMessage(exception.getMessage());
      info.setTimestamp(LocalDateTime.now());
      info.setExceptionCode(HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<ExceptionInformation>(info,HttpStatus.NOT_FOUND);
      
  }
  
  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<ExceptionInformation> storeExceptionHandler(CustomerNotFoundException exception){
      ExceptionInformation info=new ExceptionInformation();
      info.setExceptionMessage(exception.getMessage());
      info.setTimestamp(LocalDateTime.now());
      info.setExceptionCode(HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<ExceptionInformation>(info,HttpStatus.NOT_FOUND);
      
  }
  
  @ExceptionHandler(NoSuchCategoryException.class)
  public ResponseEntity<ExceptionInformation> storeExceptionHandler(NoSuchCategoryException exception){
      ExceptionInformation info=new ExceptionInformation();
      info.setExceptionMessage(exception.getMessage());
      info.setTimestamp(LocalDateTime.now());
      info.setExceptionCode(HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<ExceptionInformation>(info,HttpStatus.NOT_FOUND);
      
  }
  
  @ExceptionHandler(OrderNotFoundException.class)
  public ResponseEntity<ExceptionInformation> storeExceptionHandler(OrderNotFoundException exception){
      ExceptionInformation info=new ExceptionInformation();
      info.setExceptionMessage(exception.getMessage());
      info.setTimestamp(LocalDateTime.now());
      info.setExceptionCode(HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<ExceptionInformation>(info,HttpStatus.NOT_FOUND);
      
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionInformation> storeExceptionHandler(MethodArgumentNotValidException exception){
      ExceptionInformation info=new ExceptionInformation();
      String s="Validation Failed: ";
      //To get the messages for all the validation errors that occured
      for(ObjectError error: exception.getBindingResult().getAllErrors()) {
    	  s=s+error.getDefaultMessage()+", ";
      }
      info.setExceptionMessage(s);
      info.setTimestamp(LocalDateTime.now());
      info.setExceptionCode(HttpStatus.BAD_REQUEST.value());
      return new ResponseEntity<ExceptionInformation>(info,HttpStatus.BAD_REQUEST);
  }
  

}
