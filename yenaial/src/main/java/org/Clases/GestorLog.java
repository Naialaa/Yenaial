package org.Clases;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utilidad encargada de registrar las operaciones del taller y las excepciones
 * críticas en un archivo físico de persistencia de logs.
 * * @author TuNombre
 * @version 1.0
 */
public class GestorLog {
    private static final String RUTA_ARCHIVO = "taller_error.log";

    /**
     * Registra una línea formateada en el archivo de logs con la marca de tiempo actual.
     * Si ocurre un error de escritura, se reporta por el canal de error estándar.
     * * @param nivel Gravedad del evento registrado (ej: "INFO", "ERROR", "WARN").
     * @param mensaje Descripción detallada del suceso.
     */
    public static void registrarEvento(String nivel, String mensaje) {
        String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (FileWriter fw = new FileWriter(RUTA_ARCHIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println("[" + fechaHora + "] [" + nivel.toUpperCase() + "] " + mensaje);

        } catch (IOException e) {
            System.err.println("Error crítico en el Log: " + e.getMessage());
        }
    }
}
