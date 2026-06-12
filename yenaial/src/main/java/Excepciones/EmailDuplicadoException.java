package org.Clases.excepciones;

/**
 * Excepción lanzada cuando se intenta registrar un usuario con un
 * email que ya existe en la base de datos.
 *
 * <p>La columna {@code email} de la tabla {@code usuarios} tiene
 * restricción {@code UNIQUE}. Esta excepción representa ese error
 * a nivel de lógica de negocio, antes de que llegue a la BD.</p>
 *
 * @author Kodek
 * @version 1.0
 */
public class EmailDuplicadoException extends RuntimeException {

    /**
     * Construye la excepción con un mensaje descriptivo.
     *
     * @param mensaje descripción del error
     */
    public EmailDuplicadoException(String mensaje) {
        super(mensaje);
    }

    /**
     * Construye la excepción indicando el email en conflicto.
     *
     * @param email el email que ya está registrado
     */
    public EmailDuplicadoException(String email, boolean unused) {
        super("El email '" + email + "' ya está registrado en el sistema.");
    }
}
