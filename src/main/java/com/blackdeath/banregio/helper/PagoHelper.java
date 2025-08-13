package com.blackdeath.banregio.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * @author Seth Karim Luis Martínez
 */
public class PagoHelper {

	public record DatoPago(int plazo, BigDecimal interes, BigDecimal iva, BigDecimal pago) {
	}

	/**
	 * Calcula el monto del pago
	 * 
	 * @param fechaActual
	 * @param fechaPrestamo
	 * @param montoPrestamo
	 * @param tasaInteres
	 * @param diasAnioComercial
	 * @param tasaIva
	 * @return
	 */
	public static DatoPago calularMontoPago(LocalDate fechaActual, LocalDate fechaPrestamo, BigDecimal montoPrestamo,
			BigDecimal tasaInteres, int diasAnioComercial, BigDecimal tasaIva) {

		int plazo = calcularPlazo(fechaActual, fechaPrestamo);
		BigDecimal interes = calcularInteres(montoPrestamo, plazo, tasaInteres, diasAnioComercial);
		BigDecimal iva = calcularIva(interes, tasaIva);
		BigDecimal pago = montoPrestamo.multiply(interes).add(iva);

		return new DatoPago(plazo, interes, iva, pago);
	}

	/**
	 * Calcula los días transcrurridos entre la fecha actual y la fecha del préstamo
	 * 
	 * @param fechaActual
	 * @param fechaPrestamo
	 * @return
	 */
	private static int calcularPlazo(LocalDate fechaActual, LocalDate fechaPrestamo) {
		Objects.requireNonNull(fechaActual, "fechaActual no puede ser null");
		Objects.requireNonNull(fechaPrestamo, "fechaPrestamo no puede ser null");

		return (int) ChronoUnit.DAYS.between(fechaPrestamo, fechaActual);
	}

	/**
	 * Calcula la tasa de interés
	 * 
	 * @param montoPrestamo
	 * @param plazo
	 * @param tasaInteres
	 * @param diasAnioComercial
	 * @return
	 */
	private static BigDecimal calcularInteres(BigDecimal montoPrestamo, int plazo, BigDecimal tasaInteres,
			int diasAnioComercial) {
		Objects.requireNonNull(montoPrestamo, "montoPrestamo no puede ser null");
		Objects.requireNonNull(tasaInteres, "tasaInteresAnual no puede ser null");

		if (plazo < 0) {
			throw new IllegalArgumentException("plazoDias no puede ser negativo");
		}
		if (diasAnioComercial <= 0) {
			throw new IllegalArgumentException("diasAnioComercial debe ser > 0");
		}
		if (montoPrestamo.signum() < 0) {
			throw new IllegalArgumentException("montoPrestamo no puede ser negativo");
		}
		if (tasaInteres.signum() < 0) {
			throw new IllegalArgumentException("tasaInteresAnual no puede ser negativa");
		}

		int escala = 2;
		RoundingMode redondeo = RoundingMode.HALF_UP;

		if (montoPrestamo.signum() == 0 || plazo == 0 || tasaInteres.signum() == 0) {
			return BigDecimal.ZERO.setScale(escala, redondeo);
		}

		BigDecimal tasaInteresDecimal = porcentajeDecimal(tasaInteres);

		return montoPrestamo.multiply(BigDecimal.valueOf(plazo)).multiply(tasaInteresDecimal)
				.divide(BigDecimal.valueOf(diasAnioComercial), escala, redondeo);
	}

	/**
	 * Calcula el IVA de un interés
	 * 
	 * @param interes
	 * @param tasaIva
	 * @return
	 */
	private static BigDecimal calcularIva(BigDecimal interes, BigDecimal tasaIva) {
		BigDecimal tasaIvaDecimal = porcentajeDecimal(tasaIva);

		return interes.multiply(tasaIvaDecimal);
	}

	/**
	 * Convierte un valor porcentual (ej. 35.5) a tasa decimal (0.355).
	 * 
	 * @param porcentaje
	 * @return
	 */
	private static BigDecimal porcentajeDecimal(BigDecimal porcentaje) {
		Objects.requireNonNull(porcentaje, "porcentaje no puede ser null");

		return porcentaje.divide(BigDecimal.valueOf(100));
	}

}
