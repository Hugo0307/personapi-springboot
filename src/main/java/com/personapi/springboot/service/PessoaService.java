package com.personapi.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personapi.springboot.dto.MessageResponseDTO;
import com.personapi.springboot.dto.PessoaDTO;
import com.personapi.springboot.entity.Pessoa;
import com.personapi.springboot.exception.PessoaNotFoundException;
import com.personapi.springboot.mapper.PessoaMapper;
import com.personapi.springboot.repository.PessoaRepository;


@Service //indica pra o spring gerenciar essa classe do tipo serviço, onde vai conter todas as regras de negócio da aplicação
public class PessoaService {
	
	private PessoaRepository pessoaRepository;
	
	private final PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;
	
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

	public List<PessoaDTO> listAll() {
		List<Pessoa> allPessoas = pessoaRepository.findAll();
		return allPessoas.stream()
				.map(pessoaMapper::toDto)
				.collect(Collectors.toList());
	}

	public PessoaDTO findById(Long id) throws PessoaNotFoundException {
		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new PessoaNotFoundException(id));
		
		return pessoaMapper.toDto(pessoa);
		
		//esse codigo acima com expressão lambda é o mesmo que o código abaixo
		
		/*Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
		if(optionalPessoa.isEmpty()) {
			throw new PessoaNotFoundException(id);
		}
		return pessoaMapper.toDto(optionalPessoa.get()); */
	}

	
}
