package com.ims.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidateResponse {
	
    private String  message ;

    List<String> productMessage = new ArrayList<>() ;
   

  

 

    public void setMessage(String message) {
		this.message = message;
	}






	public String getMessage() {
        return message;
    }

    public List<String> getProductMessage() {
        return productMessage;
    }

    public void setProductMessage(List<String> productMessage) {
        this.productMessage = productMessage;
    }
}








