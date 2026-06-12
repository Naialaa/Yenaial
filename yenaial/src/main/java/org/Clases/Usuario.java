package org.Clases;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que gestiona las cuentas de acceso del taller, distinguiendo
 * los perfiles por roles y vinculando sus pertenencias.
 * * @author TuNombre
 * @version 1.0
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol = Rol.cliente;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Bicicleta> bicicletas = new ArrayList<>();

    /** Perfiles de autorización del sistema. */
    public enum Rol { admin, cliente }

    public Usuario() {}

    /**
     * Crea un usuario con credenciales iniciales cifradas y rol asignado.
     * * @param nombre Nombre completo.
     * @param email Identificador único de inicio de sesión.
     * @param rol Perfil asignado en la aplicación.
     * @param passwordHash Contraseña ya hasheada.
     */
    public Usuario(String nombre, String email, Rol rol, String passwordHash) {
        this.nombre       = nombre;
        this.email        = email;
        this.rol          = rol;
        this.passwordHash = passwordHash;
    }

    /**
     * Registra una bicicleta asignándole este usuario como propietario
     * e incluyéndola en su colección personal de forma sincronizada.
     * * @param bici La bicicleta a vincular.
     */
    public void registrarBicicleta(Bicicleta bici) {
        bici.setPropietario(this);
        this.bicicletas.add(bici);
    }

    // Getters y Setters...
    public int getIdUsuario()                        { return idUsuario; }
    public void setIdUsuario(int idUsuario)          { this.idUsuario = idUsuario; }
    public String getNombre()                        { return nombre; }
    public void setNombre(String nombre)             { this.nombre = nombre; }
    public String getEmail()                         { return email; }
    public void setEmail(String email)               { this.email = email; }
    public Rol getRol()                              { return rol; }
    public void setRol(Rol rol)                      { this.rol = rol; }
    public String getPasswordHash()                  { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public List<Bicicleta> getBicicletas()           { return bicicletas; }
}
