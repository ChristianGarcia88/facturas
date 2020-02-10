package com.example.facturas.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.ws.rs.Path;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import com.example.facturas.entity.Cliente;
import com.example.facturas.entity.Factura;

public interface Repository  extends  CrudRepository<Factura, Integer>  {
	
	@Query("select   facCliente   from    Factura   facCliente where  facCliente.cliente.m_idcliente= ?1" )
	Factura[]  listadoFacturaCliente( Integer x );
	
	  @Transactional
	  @Modifying(clearAutomatically = true)
	@Query(value="Delete from  proyecto_factura f  where  f.persona_id=?1", nativeQuery = true)
	void  deleteFromUser(Integer idCliente);
	
	

}


