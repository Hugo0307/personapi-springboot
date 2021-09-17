package com.personapi.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaNotFoundException extends Exception {

	public PessoaNotFoundException(Long id) {
		super("Pessoa não existente no ID " + id);
	}

}
