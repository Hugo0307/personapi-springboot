package com.personapi.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.personapi.springboot.dto.MessageResponseDTO;
import com.personapi.springboot.dto.PessoaDTO;
import com.personapi.springboot.exception.PessoaNotFoundException;
import com.personapi.springboot.service.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/people")
@Tag(name = "API REST Pessoa")
@CrossOrigin(origins = "*")
//@AllArgsConstructor(onConstructor = @__(@Autowired))//cria um construtor default em tempo de compilação como o que está abaixo comentado
public class PessoaController {
	
	private PessoaService pessoaService;
	
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@GetMapping
	@Operation(description = "retorna os dados de uma lista de pessoas")
	public List<PessoaDTO> listAll() {
		return pessoaService.listAll();
	}
	
	@GetMapping("/{id}")
	@Operation(description = "retorna os dados de uma pessoa")
	public PessoaDTO findById(@PathVariable Long id) throws PessoaNotFoundException {
		return pessoaService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(description = "salva os dados de uma pessoa")
	public MessageResponseDTO createPessoa(@RequestBody @Valid PessoaDTO pessoaDTO) {
		return pessoaService.createPessoa(pessoaDTO);
	}
		
	@PutMapping("/{id}")
	@Operation(description = "atualiza os dados de uma pessoa")
	public MessageResponseDTO updateById(@PathVariable Long id, 
			@RequestBody @Valid PessoaDTO pessoaDTO) throws PessoaNotFoundException {
		return pessoaService.updateById(id, pessoaDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(description = "deleta os dados de uma pessoa")
	public void deleteById(@PathVariable Long id) throws PessoaNotFoundException {
		pessoaService.delete(id);
	}
	
}
