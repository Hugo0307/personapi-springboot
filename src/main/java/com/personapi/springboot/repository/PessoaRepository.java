package com.personapi.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.personapi.springboot.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
