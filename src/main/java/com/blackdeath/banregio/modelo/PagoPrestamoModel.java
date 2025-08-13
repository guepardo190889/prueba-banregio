package com.blackdeath.banregio.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.blackdeath.banregio.entidad.Prestamo;

import lombok.Data;

/**
 * @author Seth Karim Luis Martínez
 */
@Data
public class PagoPrestamoModel {
	private List<PrestamoModel> prestamos;
	private BigDecimal saldoCuenta;

	public PagoPrestamoModel(List<Prestamo> prestamosPagados, BigDecimal saldoCuenta) {
		this.prestamos = new ArrayList<>();

		for (Prestamo prestamo : prestamosPagados) {
			prestamos.add(new PrestamoModel(prestamo));
		}

		this.saldoCuenta = saldoCuenta;
	}
}
