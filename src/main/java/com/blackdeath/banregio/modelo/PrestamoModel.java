package com.blackdeath.banregio.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.blackdeath.banregio.entidad.Prestamo;
import com.blackdeath.banregio.entidad.PrestamoEstado;

import lombok.Data;

/**
 * @author Seth Karim Luis Martínez
 */
@Data
public class PrestamoModel {

	private Long id;

	private String numeroCliente;

	private LocalDate fecha;

	private BigDecimal monto;

	private PrestamoEstado estado;

	public PrestamoModel(Prestamo prestamo) {
		id = prestamo.getId();
		numeroCliente = prestamo.getCliente().getNumero();
		fecha = prestamo.getFecha();
		monto = prestamo.getMonto();
		estado = prestamo.getEstado();
	}
}
