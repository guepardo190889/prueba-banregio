package com.blackdeath.banregio.entidad;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "prestamos", indexes = { @Index(name = "idx_prestamos_id", columnList = "id") })
@Entity
public class Prestamo {

	/**
	 * Identificador único del préstamo
	 */
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	/**
	 * Cliente del préstamo
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id", updatable = false, nullable = false, unique = false)
	private Cliente cliente;

	/**
	 * Fecha de otorgamiento del préstamo
	 */
	@Column(name = "fecha", updatable = false, nullable = false, unique = false)
	private LocalDate fecha;

	/**
	 * Monto del préstamo
	 */
	@Column(name = "monto", updatable = true, nullable = false, unique = false, precision = 11, scale = 2)
	private BigDecimal monto;

	/**
	 * Estado del préstamo
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", updatable = true, nullable = false, unique = false, length = 12)
	private PrestamoEstado estado;
}
