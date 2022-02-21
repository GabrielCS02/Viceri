package br.com.viceri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.viceri.model.Produto;



@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long> {

	public Optional<Produto>findByDetalhes(String detalhes);
	
	public List<Produto>findAllByNomeContainingIgnoreCase(String nome);
	
}
