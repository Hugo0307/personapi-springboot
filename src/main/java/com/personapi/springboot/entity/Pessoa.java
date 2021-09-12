package com.personapi.springboot.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)//aqui o nullable recebendo false é o mesmo que criar um campo not null no DB
	private String nome;
	
	@Column(nullable = false)
	private String sobrenome;
	
	@Column(nullable = false, unique = true)//aqui o unique faz com que não exista mais de um cpf com a mesma numeração
	private String cpf;
	
	@Column(nullable = true)
	private LocalDate dataNascimento;
	
	//o feche LAZY é utilizado para performance, é uma estratégia de obtenção de dados
	//o cascade faz com que ao cadastrar a pessoa, o telefone informado seja salvo na mesma operação
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<Telefone> telefones;
	
}
