package org.Clases.excepciones;

/**
 * Excepción lanzada cuando se intenta acceder a una bicicleta
 * que no existe en el sistema.
 *
 * <p>Se usa en operaciones de búsqueda, actualización o eliminación
 * cuando el identificador proporcionado no corresponde a ninguna
 * bicicleta registrada.</p>
 *
 * @author Kodek
 * @version 1.0
 */
public class BicicletaNoEncontradaException extends RuntimeException {

    /**
     * Construye la excepción con un mensaje descriptivo.
     *
     * @param mensaje descripción del error
     */
    public BicicletaNoEncontradaException(String mensaje) {
        super(mensaje);
    }

    /**
     * Construye la excepción indicando el ID que no fue encontrado.
     *
     * @param idBicicleta identificador de la bicicleta no encontrada
     */
    public BicicletaNoEncontradaException(int idBicicleta) {
        super("No se encontró ninguna bicicleta con id=" + idBicicleta);
    }
}
