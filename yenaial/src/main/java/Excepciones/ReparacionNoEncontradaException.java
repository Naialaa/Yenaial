package org.Clases.excepciones;

/**
 * Excepción lanzada cuando se intenta acceder o actualizar una
 * reparación que no existe en el sistema.
 *
 * <p>Se usa al buscar una reparación por ID para cambiar su estado
 * de progreso o consultar su información.</p>
 *
 * @author Kodek
 * @version 1.0
 */
public class ReparacionNoEncontradaException extends RuntimeException {

    /**
     * Construye la excepción con un mensaje descriptivo.
     *
     * @param mensaje descripción del error
     */
    public ReparacionNoEncontradaException(String mensaje) {
        super(mensaje);
    }

    /**
     * Construye la excepción indicando el ID que no fue encontrado.
     *
     * @param idReparacion identificador de la reparación no encontrada
     */
    public ReparacionNoEncontradaException(int idReparacion) {
        super("No se encontró ninguna reparación con id=" + idReparacion);
    }
}
