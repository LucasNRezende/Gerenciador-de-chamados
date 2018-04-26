package com.gerenciadorchamados.GerenciadorChamados.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerenciadorchamados.GerenciadorChamados.model.Atendimento;


@Repository

public interface AtendimentoDAO extends JpaRepository<Atendimento, Integer> {
	
	@Query("select c from Atendimento c where c.tipoatendimento like %:tipoatendimento%")
	List<Atendimento> findByTipoatendimento(@Param("tipoatendimento") String tipoatendimento);
	


}
