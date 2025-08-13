package com.blackdeath.banregio.controlador;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackdeath.banregio.modelo.PagoPrestamoModel;
import com.blackdeath.banregio.servicio.PrestamoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

/**
 * @author Seth Karim Luis Martínez
 */
@Log4j2
@Tag(name = "Prétamos", description = "Operaciones de préstamos")
@RequestMapping("/api/v1/prestamos")
@RestController
public class PrestamoController {

	private final PrestamoService prestamoService;

	public PrestamoController(PrestamoService prestamoService) {
		this.prestamoService = prestamoService;
	}

	/**
	 * Paga todos los préstamos posibles de las cuentas activas
	 * 
	 * @param fechaActual
	 * @param tasaInteres
	 * @param tasaIVA
	 * @param diasAnioComercial
	 * @return
	 */
	@Operation(summary = "Paga todos los préstamos posibles de las cuentas activas", responses = {
			@ApiResponse(responseCode = "200", description = "Pagos procesados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PagoPrestamoModel.class))),
			@ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content) })
	@PostMapping(value = "/pagar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PagoPrestamoModel>> pagar(
			@Parameter(description = "Fecha actual", required = true) LocalDate fechaActual,
			@Parameter(description = "Tasa de interés", required = true) BigDecimal tasaInteres,
			@Parameter(description = "tasa del IVA", required = true) BigDecimal tasaIVA,
			@Parameter(description = "Número de días del año comercial", required = true) int diasAnioComercial) {

		try {
			List<PagoPrestamoModel> modelos = prestamoService.pagar(fechaActual, tasaInteres, tasaIVA,
					diasAnioComercial);

			return ResponseEntity.ok(modelos);

		} catch (Exception e) {
			log.error("PrestamoController.pagar()", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
