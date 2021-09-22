package com.personapi.springboot.test.utils;

import java.time.LocalDate;
import java.util.Collections;

import com.personapi.springboot.dto.PessoaDTO;
import com.personapi.springboot.entity.Pessoa;

/**
 * Classe responsável por gerar dados fakes para teste unitário.
 * @author Hugo
 * @since 21/09/2021
 */
public class PessoaUtils {
	
	private static final long ID = 1L;
	private static final String NOME = "José";
	private static final String SOBRENOME = "Almeida";
	private static final String CPF = "025.888.635-82";
	public static final LocalDate DATA_NASCIMENTO = LocalDate.of(1987, 07, 03);
	
	public static PessoaDTO createFakeDTO() {
		return PessoaDTO.builder()
				.nome(NOME)
				.sobrenome(SOBRENOME)
				.cpf(CPF)
				.dataNascimento(DATA_NASCIMENTO)
				.telefones(Collections.singletonList(TelefoneUtils.createFakeDTO()))
				.build();
	}
	
	public static Pessoa createFakeEntity() {
		return Pessoa.builder()
				.id(ID)
				.nome(NOME)
				.sobrenome(SOBRENOME)
				.cpf(CPF)
				.dataNascimento(DATA_NASCIMENTO)
				.telefones(Collections.singletonList(TelefoneUtils.createFakeEntity()))
				.build();
	}
	

}
