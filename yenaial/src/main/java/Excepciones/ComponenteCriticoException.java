package org.Clases.excepciones;

/**
 * Excepción lanzada cuando un componente se encuentra en un estado
 * que requiere atención inmediata antes de continuar operando.
 *
 * <p>Se lanza al intentar registrar una reparación o cita en una
 * bicicleta que tiene componentes marcados como {@code Critico},
 * forzando al sistema a gestionar el problema antes de seguir.</p>
 *
 * @author Kodek
 * @version 1.0
 */
public class ComponenteCriticoException extends RuntimeException {

    /**
     * Construye la excepción con un mensaje descriptivo.
     *
     * @param mensaje descripción del error
     */
    public ComponenteCriticoException(String mensaje) {
        super(mensaje);
    }

    /**
     * Construye la excepción indicando el componente y la bicicleta afectados.
     *
     * @param tipoComponente tipo del componente en estado crítico (ej: "Frenos")
     * @param idBicicleta    identificador de la bicicleta afectada
     */
    public ComponenteCriticoException(String tipoComponente, int idBicicleta) {
        super("El componente '" + tipoComponente + "' de la bicicleta id="
            + idBicicleta + " está en estado crítico y necesita cambio inmediato.");
    }
}
