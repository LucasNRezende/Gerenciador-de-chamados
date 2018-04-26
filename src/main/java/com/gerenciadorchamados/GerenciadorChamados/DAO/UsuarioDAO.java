package com.gerenciadorchamados.GerenciadorChamados.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerenciadorchamados.GerenciadorChamados.model.Usuario;

@Repository


public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {
	
	@Query("select c from Usuario c where c.nome like %:nome%")
	List<Usuario> findByNome(@Param("nome") String nome); 

}
