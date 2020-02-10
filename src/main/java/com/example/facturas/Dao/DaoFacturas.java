package com.example.facturas.Dao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.facturas.entity.Cliente;
import com.example.facturas.entity.Factura;

import com.example.facturas.interfaz.IFactura;
import com.example.facturas.repository.Repository;


@Component
public class DaoFacturas  implements  IFactura  {
	
@Autowired
private  Repository   repoFactura;

	@Override
	public void create(  Factura factura  ) {
		
		
		repoFactura.save(factura);
		
	}

	
	public Factura searchFactura(Integer id)  throws  NoSuchElementException {
		Optional<Factura>  factura= repoFactura.findById(id);
		if(factura.get() == null ) {
			throw new  NoSuchElementException();
			
		}
		return  factura.get();
	}

	@Override
	public List<Factura> listFactura()  {
		List<Factura>  listaFactura= (List<Factura>) repoFactura.findAll();
		
		return listaFactura;
	}

	@Override
	public void deleteFactura(Integer id) {
		
		repoFactura.deleteById(id);
		
	}

	@Override
	public Factura[] listadoFacturaCliente(Integer id) throws NoSuchElementException {
		
		Factura[] facturas=repoFactura.listadoFacturaCliente(id);
		if(facturas== null) {
			
			throw new  NoSuchElementException();
		}
		return facturas;
	}


	@Override
	public void updateFacturas( Factura factura) throws Exception   {
		
	Factura facturaUpdate=	repoFactura.save(factura);
	
	if (facturaUpdate.getM_fechaFactura()== null) {
		
		throw new Exception();
		
	}
	
	
		
		
	}
	
	@Override
		public void deleteFromUser(Integer idUsuario)  throws  Exception {
		
		try {
			repoFactura.deleteFromUser(idUsuario);
			
		} catch (Exception e) {
			new Exception (e.getMessage());
		}
			
		
		}
	


}
