package com.example.facturas.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Proyecto_Factura")
public class Factura {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  Integer m_idFactura; 
	

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "persona_id" )
	private  Cliente cliente;

	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	@Column(name = "saldo_Total")
	private  Float  m_saldoTotal; 
	
	@DateTimeFormat(pattern ="yyyy/MM/dd hh:mm:ss" )
	private   LocalDateTime  m_fechaFactura;
	
	
	
	public Factura( Cliente cliente, Float m_saldoTotal, LocalDateTime m_fechaFactura) {
		super();
	
		this.cliente = cliente;
		this.m_saldoTotal = m_saldoTotal;
		this.m_fechaFactura = m_fechaFactura;
	}


	public Factura() {
		
	}


	public Integer getM_idFactura() {
		return m_idFactura;
	}



	public void setM_idFactura(Integer m_idFactura) {
		this.m_idFactura = m_idFactura;
	}



	public Float getM_saldoTotal() {
		return m_saldoTotal;
	}



	public void setM_saldoTotal(Float m_saldoTotal) {
		this.m_saldoTotal = m_saldoTotal;
	}



	public LocalDateTime getM_fechaFactura() {
		return m_fechaFactura;
	}



	public void setM_fechaFactura(LocalDateTime m_fechaFactura) {
		this.m_fechaFactura = m_fechaFactura;
	}
	
	
	
}
