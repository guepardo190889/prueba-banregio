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

	private String numeroCliente;

	private int plazo;

	private BigDecimal monto;

	private BigDecimal interes;

	private BigDecimal iva;

	private BigDecimal pago;

	public PrestamoModel(DatoPago datoPago, Prestamo prestamo) {
		numeroCliente = prestamo.getCliente().getNumero();
		plazo = datoPago.plazo();
		monto = datoPago.pago();
		interes = datoPago.interes();
		iva = datoPago.iva();
		pago = datoPago.pago();
	}
}
