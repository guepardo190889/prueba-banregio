package com.blackdeath.banregio.helper;

import java.math.BigDecimal;

import com.blackdeath.banregio.entidad.Cuenta;

/**
 * @author Seth Karim Luis Martínez
 */
public class CuentaHelper {

	/**
	 * Indica si la cuenta tiene saldo
	 * 
	 * @param cuenta
	 * @return
	 */
	public static boolean tieneSaldo(Cuenta cuenta) {
		return cuenta.getMonto().signum() == 1;
	}

	/**
	 * Indica si la cuenta tiene el saldo suficiente para pagar un préstamo
	 * 
	 * @param cuenta
	 * @param montoPagoPrestamo
	 * @return
	 */
	public static boolean tieneSaldoSuficienteParaPagarPrestamo(Cuenta cuenta, BigDecimal montoPagoPrestamo) {
		return cuenta.getMonto().compareTo(montoPagoPrestamo) == 1;
	}

}
