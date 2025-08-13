package com.blackdeath.banregio.entidad;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Seth Karim Luis Martínez
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cuentas", indexes = { @Index(name = "idx_cuentas_id", columnList = "id") })
@Entity
public class Cuenta {

	/**
	 * Identificador único de la cuenta
	 */
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	/**
	 * Cliente de la cuenta
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", updatable = false, nullable = false, unique = false)
	private Cliente cliente;

	/**
	 * Monto de la cuenta
	 */
	@Column(name = "monto", updatable = true, nullable = false, unique = false, precision = 11, scale = 2)
	private BigDecimal monto;

	/**
	 * Estado de la cuenta
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", updatable = true, nullable = false, unique = false, length = 12)
	private CuentaEstado estado;

}
