package com.example.facturas.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.DELETE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.facturas.Dao.DaoFacturas;
import com.example.facturas.entity.Cliente;
import com.example.facturas.entity.Factura;

import com.example.facturas.util.Mensajes;
import com.example.facturas.utilerias.Fechas;



@RestController
@RequestMapping("/facturas")
public class ControllerFacturas {
	
	
	@Autowired
	private DaoFacturas daoFacturas;
	@Autowired
	private  Fechas fechaActual;
	private Logger l = LoggerFactory.getLogger(ControllerFacturas.class);
	
	
	
	@PostMapping("/crear")
	public  ResponseEntity<?>  crear( @RequestBody Cliente cliente ) {
		  
		Factura factura = new Factura  ();
		factura.setCliente(cliente);
		
		
		factura.setM_fechaFactura(LocalDateTime.now());
		System.out.println("-----------------------" + factura.getM_fechaFactura());
		factura.setM_saldoTotal(0F);
		
	
		l.info(cliente.toString()  + " " + factura.toString());
		daoFacturas.create(factura);
		
		
		
		return new  ResponseEntity<Mensajes>(new Mensajes("Creacion Factura Exitoso", HttpStatus.OK,  fechaActual.fechaActual()),  HttpStatus.OK);
		
	}
	
	@GetMapping("/listasFacturas")
	public  ResponseEntity<?>listarFacturas(){
		
		List<Factura> listaFacturas=daoFacturas.listFactura();
		if(listaFacturas.isEmpty()) { return new ResponseEntity<Mensajes>(new Mensajes("No hay Facturas en el Registro", HttpStatus.NOT_FOUND, fechaActual.fechaActual()),HttpStatus.NOT_FOUND);}
		
		return new ResponseEntity<List<Factura>>(listaFacturas,HttpStatus.ACCEPTED);
	} 
	
	
	@GetMapping("/searchFactura/{id}")
	public  ResponseEntity<?>  searchFactura(@PathVariable("id")  Integer id ) throws NoSuchElementException {
		try {
			
			Factura  factura=	daoFacturas.searchFactura(id);
			
			return  new ResponseEntity<>(factura, HttpStatus.FOUND);
			
		} catch (NoSuchElementException e) {
			
			return new  ResponseEntity<Mensajes>(new Mensajes(e.getMessage(), HttpStatus.NOT_FOUND,  fechaActual.fechaActual()),  HttpStatus.NOT_FOUND);
			
		}
	
	}
	
	
	@GetMapping("/factura/cliente/{idcliente}")
	public ResponseEntity<?>  retornaFacturaClientes(@PathVariable("idcliente") Integer clienteId) throws NoSuchElementException, InterruptedException {
		
		try {
			
			//Thread.sleep(1000);
		return  new ResponseEntity<>(daoFacturas.listadoFacturaCliente(clienteId), HttpStatus.FOUND);
			
		} catch (NoSuchElementException e) {
						throw new NoSuchElementException("");
			//return new ResponseEntity<Mensajes>(new Mensajes(e.getMessage(), HttpStatus.NOT_FOUND,  fechaActual.fechaActual()), HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@PutMapping("/update")
	public   ResponseEntity<?>   updateFacturas(@RequestBody  Factura facturas ) throws Exception  {
		try{
		daoFacturas.updateFacturas(facturas);
		return new ResponseEntity<Mensajes>(new Mensajes("Actualizacion facturas exitosa", HttpStatus.ACCEPTED, fechaActual.fechaActual()),HttpStatus.ACCEPTED);
		}
		catch( Exception e ) {
			
			return    new ResponseEntity<Mensajes>(new Mensajes("No se pudo actualizar"+" "+ e.getMessage(), HttpStatus.BAD_REQUEST, fechaActual.fechaActual()), HttpStatus.BAD_REQUEST);
			
		}
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>   eliminarFacturas(@PathVariable("id") Integer id ){
		
		daoFacturas.deleteFactura(id);
		return  new ResponseEntity<Mensajes>(new Mensajes("Eliminacion realizada ", HttpStatus.ACCEPTED, fechaActual.fechaActual()),HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping ("/delete/factura/user/{id}")
	public   ResponseEntity<?>  deleteFromUser(@PathVariable("id")  Integer id) throws Exception{
	try {
		daoFacturas.deleteFromUser(id);
		return  new ResponseEntity<Mensajes>( new Mensajes("Eliminacion desde Facturas Realizada", HttpStatus.ACCEPTED, fechaActual.fechaActual()), HttpStatus.ACCEPTED);
	} catch (Exception e) {
		
		return  new ResponseEntity<Mensajes>( new Mensajes("Eliminacion truncada"+ e.getMessage(), HttpStatus.ACCEPTED, fechaActual.fechaActual()), HttpStatus.ACCEPTED);
	}
		
		
		
		
		
		
	}
	
	
	
	
}
