package com.personapi.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.personapi.springboot.dto.MessageResponseDTO;
import com.personapi.springboot.entity.Pessoa;
import com.personapi.springboot.service.PessoaService;

@RestController
@RequestMapping("/api/v1/people")
public class PessoaController {
	

	private PessoaService pessoaService;
	
	@Autowired
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createPessoa(@RequestBody Pessoa pessoa) {
		return pessoaService.createPessoa(pessoa);

	}
	
}
