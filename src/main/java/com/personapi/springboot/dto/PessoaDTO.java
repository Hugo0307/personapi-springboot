package com.personapi.springboot.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {
	
		private Long id;
		
		@NotEmpty
		@Size(min = 2, max = 100)
		private String nome;
		
		@NotEmpty
		@Size(min = 2, max = 100)
		private String sobrenome;
		
		@NotEmpty
		@CPF
		private String cpf;
		
		private LocalDate dataNascimento;
		
		@Valid //quando os dados do telefone, cada um dos dados da lista precisa ser validado
		@NotEmpty
		private List<TelefoneDTO> telefones;

}
