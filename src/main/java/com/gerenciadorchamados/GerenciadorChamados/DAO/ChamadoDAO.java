package com.gerenciadorchamados.GerenciadorChamados.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.gerenciadorchamados.GerenciadorChamados.model.Chamado;



@Repository

public interface ChamadoDAO extends JpaRepository<Chamado, Integer>{

	@Query("select c from Chamado c where c.tipochamado like %:tipochamado%")
	List<Chamado> findByTipochamado(@Param("tipochamado") String tipochamado);
	
	@Query("select c from Chamado c where c.statuschamado like %:statuschamado%")
	List<Chamado> findByStatuschamado(@Param("statuschamado") String statuschamado);
	


}
