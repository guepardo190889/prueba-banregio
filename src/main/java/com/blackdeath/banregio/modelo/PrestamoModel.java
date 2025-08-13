package com.blackdeath.banregio.modelo;

import java.math.BigDecimal;

import com.blackdeath.banregio.entidad.Prestamo;
import com.blackdeath.banregio.helper.PagoHelper.DatoPago;

import lombok.Data;

/**
 * @author Seth Karim Luis Martínez
 */
@Data
public class PrestamoModel {

	private String cliente;

	private int plazo;

	private BigDecimal monto;

	private BigDecimal interes;

	private BigDecimal iva;

	private BigDecimal pago;

	public PrestamoModel(DatoPago datoPago, Prestamo prestamo) {
		cliente = prestamo.getCliente().getNumero();
		plazo = datoPago.plazo();
		monto = prestamo.getMonto();
		interes = datoPago.interes();
		iva = datoPago.iva();
		pago = datoPago.pago();
	}
}
