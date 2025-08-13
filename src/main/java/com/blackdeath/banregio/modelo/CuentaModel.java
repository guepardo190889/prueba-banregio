package com.blackdeath.banregio.modelo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seth Karim Luis Martínez
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CuentaModel {

	private String numeroCliente;

	private BigDecimal monto;
}
