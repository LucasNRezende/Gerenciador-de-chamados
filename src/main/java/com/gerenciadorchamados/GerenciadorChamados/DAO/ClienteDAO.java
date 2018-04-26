package com.gerenciadorchamados.GerenciadorChamados.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gerenciadorchamados.GerenciadorChamados.model.Cliente;

@Repository
public interface ClienteDAO extends JpaRepository<Cliente, Integer> {

	@Query("select c from Cliente c where c.razaosocial like %:razaosocial%")
	List<Cliente> findByRazaosocial(@Param("razaosocial") String razao);

	@Query("select c from Cliente c where c.cnpj like %:cnpj%")
	List<Cliente> findByCnpj(@Param("cnpj") String cnpj);

}
