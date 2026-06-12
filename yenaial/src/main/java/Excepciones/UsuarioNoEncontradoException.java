package org.Clases.excepciones;

/**
 * Excepción lanzada cuando se intenta acceder a un usuario
 * que no existe en el sistema.
 *
 * <p>Se usa en operaciones de login, búsqueda o modificación
 * cuando el email o ID proporcionado no corresponde a ningún
 * usuario registrado.</p>
 *
 * @author Kodek
 * @version 1.0
 */
public class UsuarioNoEncontradoException extends RuntimeException {

    /**
     * Construye la excepción con un mensaje descriptivo.
     *
     * @param mensaje descripción del error
     */
    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    /**
     * Construye la excepción indicando el email que no fue encontrado.
     *
     * @param email email del usuario no encontrado
     */
    public UsuarioNoEncontradoException(String email, boolean esPorEmail) {
        super(esPorEmail
            ? "No se encontró ningún usuario con email=" + email
            : "No se encontró ningún usuario con id=" + email);
    }
}
