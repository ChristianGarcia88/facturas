package com.example.facturas.interfaz;

import java.util.List;

import com.example.facturas.entity.Cliente;
import com.example.facturas.entity.Factura;

public interface IFactura {
	
	
	void  create  ( Factura factura );
	Factura  searchFactura( Integer  id );
	List<Factura>  listFactura();
	void  deleteFactura(Integer  id );
	Factura[]  listadoFacturaCliente(Integer id );
	void updateFacturas( Factura factura) throws Exception;
	void  deleteFromUser(  Integer idUsuario ) throws Exception;
	

}
