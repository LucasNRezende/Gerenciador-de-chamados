package com.gerenciadorchamados.GerenciadorChamados.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerenciadorchamados.GerenciadorChamados.model.Software;

@Repository

public interface SoftwareDAO extends JpaRepository<Software, Integer> {
	
	@Query("select c from Software c where c.nome like %:nome%")
	List<Software> findByNome(@Param("nome") String nome); 

}
