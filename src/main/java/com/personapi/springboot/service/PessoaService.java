package com.personapi.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personapi.springboot.dto.MessageResponseDTO;
import com.personapi.springboot.dto.PessoaDTO;
import com.personapi.springboot.entity.Pessoa;
import com.personapi.springboot.exception.PessoaNotFoundException;
import com.personapi.springboot.mapper.PessoaMapper;
import com.personapi.springboot.repository.PessoaRepository;

@Service //indica pra o spring gerenciar essa classe do tipo serviço, onde vai conter todas as regras de negócio da aplicação
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {
	
	private PessoaRepository pessoaRepository;
	
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	private final PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;
	
	/**
	 * Método que insere os dados da pessoa no sistema e retorna o id da pessoa criada no sistema.
	 * @author Hugo
	 * @param pessoaDTO
	 * @return
	 */
	public MessageResponseDTO createPessoa(PessoaDTO pessoaDTO) {
		Pessoa pessoaToSave = pessoaMapper.toModel(pessoaDTO);
		
		Pessoa savedPessoa = pessoaRepository.save(pessoaToSave);//já salva a pessoa com a data de nascimento convertida
		return createMessageResponse(savedPessoa.getId(), "Pessoa criada com o id: ");
	}

	/**
	 * Método que retorna a lista de pessoas cadastrada no sistema.
	 * @author Hugo
	 * @return Retorna a lista de pessoas cadastradas no sistema.
	 */
	public List<PessoaDTO> listAll() {
		List<Pessoa> allPessoas = pessoaRepository.findAll();
		return allPessoas.stream()
				.map(pessoaMapper::toDto)
				.collect(Collectors.toList());
	}

	/**
	 * Método que retorna o registro da pessoa buscada pelo id e caso não exista no sistema,
	 * retorna uma exception informando que não existe a pessoa buscada pelo id informado.
	 * @param id
	 * @return Retorna uma pessoa cadastrada no sistema.
	 * @throws PessoaNotFoundException
	 */
	public PessoaDTO findById(Long id) throws PessoaNotFoundException {
		Pessoa pessoa = verifyIfExists(id);
		return pessoaMapper.toDto(pessoa);
		
		//esse codigo acima com expressão lambda é o mesmo que o código abaixo
		
		/*Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);
		if(optionalPessoa.isEmpty()) {
			throw new PessoaNotFoundException(id);
		}
		return pessoaMapper.toDto(optionalPessoa.get()); */
	}

	/**
	 * Método responsável por deletar o registro da pessoa pelo id informado
	 * @param id
	 * @throws PessoaNotFoundException
	 */
	public void delete(Long id) throws PessoaNotFoundException{
		verifyIfExists(id);
		pessoaRepository.deleteById(id);
	}
	
	/**
	 * Método responsável por verificar se existe a pessoa pelo id informado e retorna
	 * uma exception caso não haja registro com o id informado.
	 * @param id
	 * @return Retorna uma exception caso não haja a pessoa com o id informado.
	 * @throws PessoaNotFoundException
	 */
	private Pessoa verifyIfExists(Long id) throws PessoaNotFoundException {
		return pessoaRepository.findById(id)
				.orElseThrow(() -> new PessoaNotFoundException(id));
	}

	/**
	 * Método que realiza a atualiza o registro da pessoa do id informado
	 * @param id
	 * @param pessoaDTO
	 * @return Retorna o id da pessoa que o registro foi atualizado, caso não exista retorna uma exception
	 * @throws PessoaNotFoundException
	 */
	public MessageResponseDTO updateById(Long id, PessoaDTO pessoaDTO) throws PessoaNotFoundException {
		verifyIfExists(id);
		
		Pessoa pessoaToUpdate = pessoaMapper.toModel(pessoaDTO);
		
		Pessoa updatePessoa = pessoaRepository.save(pessoaToUpdate);//já salva a pessoa com a data de nascimento convertida
		return createMessageResponse(updatePessoa.getId(), "ID da pessoa atualizada: ");
		
	}

	/**
	 * Método criado para não haver código boilerplate
	 * @param id
	 * @param message
	 * @return Retorna a mensagem de resposta ao criar e ou atualizar o registro no sistema. 
	 */
	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO
				.builder()
				.message(message + id)
				.build();
	}
	
}
