package com.blackdeath.banregio.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blackdeath.banregio.entidad.Cuenta;
import com.blackdeath.banregio.entidad.CuentaEstado;

/**
 * @author Seth Karim Luis Martínez
 */
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	List<Cuenta> findByEstado(CuentaEstado estado);

}
