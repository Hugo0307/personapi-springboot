package com.personapi.springboot.test.utils;

import com.personapi.springboot.dto.TelefoneDTO;
import com.personapi.springboot.entity.Telefone;

/**
 * Classe responsável por gerar dados fakes para testes.
 * @author Hugo
 * @since 21/09/2021
 */
public class TelefoneUtils {
	
	private static final long ID = 1L;
	private static final String NUMERO_TELEFONE = "(71) 99988-8899";
	
	public static TelefoneDTO createFakeDTO() {
		return TelefoneDTO.builder()
				.numero(NUMERO_TELEFONE)
				.build();
	}
	
	public static Telefone createFakeEntity() {
		return Telefone.builder()
				.id(ID)
				.numero(NUMERO_TELEFONE)
				.build();
	}

}
