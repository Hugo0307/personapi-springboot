package com.personapi.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.personapi.springboot.dto.MessageResponseDTO;
import com.personapi.springboot.dto.PessoaDTO;
import com.personapi.springboot.exception.PessoaNotFoundException;
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
	public MessageResponseDTO createPessoa(@RequestBody @Valid PessoaDTO pessoaDTO) {
		return pessoaService.createPessoa(pessoaDTO);
	}
	
	@GetMapping
	public List<PessoaDTO> listAll() {
		return pessoaService.listAll();
	}
	
	@GetMapping("/{id}")
	public PessoaDTO findById(@PathVariable Long id) throws PessoaNotFoundException {
		return pessoaService.findById(id);
	}
}
