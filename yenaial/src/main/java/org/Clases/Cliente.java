package org.Clases;
import java.util.List;
import java.util.ArrayList;

/**
 * Representa un cliente del taller en un entorno no persistente o como DTO.
 * Se encarga de gestionar los datos básicos de contacto y su garaje virtual de bicicletas.
 * * @author TuNombre
 * @version 1.0
 */
public class Cliente {
    private int idCliente;
    private String nombre;
    private String email;
    private List<Bicicleta> bicicletas;

    public Cliente() {
        this.bicicletas = new ArrayList<>();
    }

    /**
     * Construye un cliente con sus datos de contacto principales.
     * * @param idCliente Identificador único.
     * @param nombre Nombre y apellidos del cliente.
     * @param email Correo electrónico.
     * @param telefono Teléfono de contacto.
     */
    public Cliente(int idCliente, String nombre, String email, String telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.email = email;
        this.bicicletas = new ArrayList<>();
    }

    /**
     * Registra y añade una nueva bicicleta al garaje virtual del cliente.
     * * @param bici La instancia de la bicicleta a registrar.
     */
    public void registrarBicicleta(Bicicleta bici) {
        this.bicicletas.add(bici);
    }

    // Getters y Setters...
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Bicicleta> getBicicletas() { return bicicletas; }
}