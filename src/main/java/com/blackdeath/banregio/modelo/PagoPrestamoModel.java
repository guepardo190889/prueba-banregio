package com.blackdeath.banregio.modelo;

import java.util.List;

import lombok.Data;

/**
 * @author Seth Karim Luis Martínez
 */
@Data
public class PagoPrestamoModel {
	private List<PrestamoModel> prestamos;
	private CuentaModel cuenta;

	public PagoPrestamoModel(List<PrestamoModel> prestamosPagados, CuentaModel cuenta) {
		this.prestamos = prestamosPagados;
		this.cuenta = cuenta;
	}
}
