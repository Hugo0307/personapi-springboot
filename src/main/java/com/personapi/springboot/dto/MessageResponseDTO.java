package com.personapi.springboot.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class MessageResponseDTO {
	
	
	private String message;
	
}
