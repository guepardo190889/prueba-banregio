package com.blackdeath.banregio.servicio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blackdeath.banregio.entidad.Cuenta;
import com.blackdeath.banregio.entidad.CuentaEstado;
import com.blackdeath.banregio.entidad.Prestamo;
import com.blackdeath.banregio.entidad.PrestamoEstado;
import com.blackdeath.banregio.helper.CuentaHelper;
import com.blackdeath.banregio.helper.PagoHelper;
import com.blackdeath.banregio.helper.PagoHelper.DatoPago;
import com.blackdeath.banregio.modelo.CuentaModel;
import com.blackdeath.banregio.modelo.PagoPrestamoModel;
import com.blackdeath.banregio.modelo.PrestamoModel;
import com.blackdeath.banregio.repositorio.CuentaRepository;
import com.blackdeath.banregio.repositorio.PrestamoRepository;

/**
 * @author Seth Karim Luis Martínez
 */
@Service
public class PrestamoService {

	private final PrestamoRepository prestamoRepository;
	private final CuentaRepository cuentaRepository;

	public PrestamoService(PrestamoRepository prestamoRepository, CuentaRepository cuentaRepository) {
		this.prestamoRepository = prestamoRepository;
		this.cuentaRepository = cuentaRepository;
	}

	/**
	 * Realiza el pago de los préstamos tomando el monto de la cuenta del cliente
	 * 
	 * @param fechaActual
	 * @param tasaInteres
	 * @param tasaIVA
	 * @param diasAnioComercial
	 */
	public List<PagoPrestamoModel> pagar(LocalDate fechaActual, BigDecimal tasaInteres, BigDecimal tasaIVA,
			int diasAnioComercial) {
		Objects.requireNonNull(fechaActual, "fechaActual no puede ser null");
		Objects.requireNonNull(tasaInteres, "tasaInteresAnual no puede ser null");

		if (diasAnioComercial <= 0) {
			throw new IllegalArgumentException("diasAnioComercial debe ser > 0");
		}

		List<Cuenta> cuentasActivas = cuentaRepository.findByEstado(CuentaEstado.ACTIVA);

		List<PagoPrestamoModel> modelos = new ArrayList<>();

		for (Cuenta cuentaActiva : cuentasActivas) {
			List<Prestamo> prestamosPendientes = prestamoRepository.findByClienteNumeroAndEstadoOrderByFechaDesc(
					cuentaActiva.getCliente().getNumero(), PrestamoEstado.PENDIENTE);

			if (!prestamosPendientes.isEmpty()) {
				modelos.add(pagarPorCuenta(cuentaActiva, prestamosPendientes, fechaActual, tasaInteres,
						diasAnioComercial, tasaIVA));
			}
		}

		return modelos;
	}

	/**
	 * Realiza el pago de los préstamos de una cuenta
	 * 
	 * @param cuenta
	 * @param prestamos
	 * @param fechaActual
	 * @param tasaInteres
	 * @param diasAnioComercial
	 * @param tasaIva
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	private PagoPrestamoModel pagarPorCuenta(Cuenta cuenta, List<Prestamo> prestamos, LocalDate fechaActual,
			BigDecimal tasaInteres, int diasAnioComercial, BigDecimal tasaIva) {
		if (CuentaHelper.tieneSaldo(cuenta)) {
			List<PrestamoModel> prestamosPagados = new ArrayList<>();

			for (Prestamo prestamoPendiente : prestamos) {
				DatoPago datoPago = PagoHelper.calularMontoPago(fechaActual, prestamoPendiente.getFecha(),
						prestamoPendiente.getMonto(), tasaInteres, diasAnioComercial, tasaIva);

				if (CuentaHelper.tieneSaldoSuficienteParaPagarPrestamo(cuenta, datoPago.pago())) {
					prestamoPendiente.setEstado(PrestamoEstado.PAGADO);
					cuenta.setMonto(cuenta.getMonto().subtract(datoPago.pago()));

					prestamoRepository.save(prestamoPendiente);
					cuenta = cuentaRepository.save(cuenta);

					prestamosPagados.add(new PrestamoModel(datoPago, prestamoPendiente));
				}
			}

			return new PagoPrestamoModel(prestamosPagados,
					new CuentaModel(cuenta.getCliente().getNumero(), cuenta.getMonto()));
		}

		return null;
	}
}
