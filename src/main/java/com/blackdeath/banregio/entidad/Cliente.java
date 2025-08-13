package com.blackdeath.banregio.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
@Table(name = "clientes", indexes = { @Index(name = "idx_clientes_id", columnList = "id") })
@Entity
public class Cliente {

	/**
	 * Identificador único del cliente
	 */
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	/**
	 * Número del cliente
	 */
	@Column(name = "numero", updatable = false, nullable = false, unique = true, length = 8)
	private String numero;

}
