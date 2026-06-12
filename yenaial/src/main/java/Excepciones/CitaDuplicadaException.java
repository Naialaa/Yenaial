package org.Clases.excepciones;

import java.time.LocalDateTime;

/**
 * Excepción lanzada cuando se intenta reservar una cita en una
 * fecha y hora que ya está ocupada.
 *
 * <p>El taller solo puede atender una bicicleta por franja horaria.
 * Si ya existe una cita en ese momento, se lanza esta excepción.</p>
 *
 * @author Kodek
 * @version 1.0
 */
public class CitaDuplicadaException extends RuntimeException {

    /**
     * Construye la excepción con un mensaje descriptivo.
     *
     * @param mensaje descripción del error
     */
    public CitaDuplicadaException(String mensaje) {
        super(mensaje);
    }

    /**
     * Construye la excepción indicando la fecha y hora del conflicto.
     *
     * @param fechaHora fecha y hora de la cita ya existente
     */
    public CitaDuplicadaException(LocalDateTime fechaHora) {
        super("Ya existe una cita reservada para el " + fechaHora
            + ". Por favor, elige otro horario.");
    }
}
