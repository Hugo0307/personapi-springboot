package com.personapi.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personapi.springboot.dto.MessageResponseDTO;
import com.personapi.springboot.dto.PessoaDTO;
import com.personapi.springboot.entity.Pessoa;
import com.personapi.springboot.mapper.PessoaMapper;
import com.personapi.springboot.repository.PessoaRepository;


@Service //indica pra o spring gerenciar essa classe do tipo serviço, onde vai conter todas as regras de negócio da aplicação
public class PessoaService {
	
	private PessoaRepository pessoaRepository;
	
	
	private PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;
	
	@Autowired
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	public MessageResponseDTO createPessoa(PessoaDTO pessoaDTO) {
		Pessoa pessoaToSave = pessoaMapper.toModel(pessoaDTO);
		
		Pessoa savedPessoa = pessoaRepository.save(pessoaToSave);//já salva a pessoa com a data de nascimento convertida
		return MessageResponseDTO
				.builder()
				.message("ID da pessoa criada: " + savedPessoa.getId())
				.build();
	}

}
