package com.personapi.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.personapi.springboot.dto.PessoaDTO;
import com.personapi.springboot.entity.Pessoa;


@Mapper
public interface PessoaMapper {
	
	
	PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);
	
	//@Mapping(target = "dataNascimento", source = "dataNascimento", dateFormat = "dd-MM-yyyy")
	Pessoa toModel(PessoaDTO pessoaDTO);//fazendo a conversão de um DTO pra uma classe modelo
	
	PessoaDTO toDto(Pessoa pessoa);

}
