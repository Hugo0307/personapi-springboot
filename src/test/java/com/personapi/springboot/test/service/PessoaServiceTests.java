package com.personapi.springboot.test.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.personapi.springboot.dto.MessageResponseDTO;
import com.personapi.springboot.dto.PessoaDTO;
import com.personapi.springboot.entity.Pessoa;
import com.personapi.springboot.repository.PessoaRepository;
import com.personapi.springboot.service.PessoaService;
import com.personapi.springboot.test.utils.PessoaUtils;

/**
 * Classe responsável por realizar os testes unitários
 * @author Hugo
 * @since 21/09/2021
 */
@ExtendWith(MockitoExtension.class)
public class PessoaServiceTests {
	
	@Mock
	private PessoaRepository pessoaRepository;
	
	@InjectMocks
	private PessoaService pessoaService;
	
	
	@org.junit.jupiter.api.Test
	public void testGivenPessoaDTOThenReturnSavedMessage() throws Exception {
		
		PessoaDTO pessoaDTO = PessoaUtils.createFakeDTO();
		Pessoa expectedSavedPessoa = PessoaUtils.createFakeEntity();
		
		when(pessoaRepository.save(any(Pessoa.class))).thenReturn(expectedSavedPessoa);
		
		MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPessoa.getId());
		MessageResponseDTO succesMessage  = pessoaService.createPessoa(pessoaDTO);
		
		assertEquals(expectedSuccessMessage, succesMessage);
	}

	private MessageResponseDTO createExpectedMessageResponse(Long id) {
		return MessageResponseDTO
				.builder()
				.message("Pessoa criada com o id: " + id)
				.build();
	}	

}
