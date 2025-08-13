package com.blackdeath.banregio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blackdeath.banregio.entidad.Prestamo;
import com.blackdeath.banregio.entidad.PrestamoEstado;

/**
 * @author Seth Karim Luis Martínez
 */
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

	List<Prestamo> findByClienteNumeroAndEstadoOrderByFechaDesc(String clienteNumero, PrestamoEstado estado);
}
