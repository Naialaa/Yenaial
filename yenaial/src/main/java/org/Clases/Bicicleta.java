package org.Clases;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad central que representa el vehículo (bicicleta) de un cliente.
 * Almacena información del modelo, la lista de componentes actuales instalados
 * y las hojas de trabajo históricas de reparaciones asociadas.
 * * @author TuNombre
 * @version 1.0
 */
@Entity
@Table(name = "bicicletas")
public class Bicicleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bicicleta")
    private int idBicicleta;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario propietario;

    @OneToMany(mappedBy = "bicicleta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Componente> componentes = new ArrayList<>();

    @OneToMany(mappedBy = "bicicleta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Reparacion> historialMantenimiento = new ArrayList<>();

    public Bicicleta() {}

    /**
     * Construye una bicicleta asignando sus características de fabricación.
     * * @param marca Fabricante de la bicicleta.
     * @param modelo Variante o línea de diseño específica.
     */
    public Bicicleta(String marca, String modelo) {
        this.marca  = marca;
        this.modelo = modelo;
    }

    /**
     * Modifica o añade un componente. Si ya existe un componente del mismo tipo
     * (ej: "Frenos"), se sustituye por la nueva versión; en caso contrario, se
     * incorpora por primera vez al listado.
     * * @param componenteNuevo Instancia del componente con el estado de salud actualizado.
     */
    public void cambiarComponente(Componente componenteNuevo) {
        for (int i = 0; i < componentes.size(); i++) {
            if (componentes.get(i).getTipo().equalsIgnoreCase(componenteNuevo.getTipo())) {
                componenteNuevo.setBicicleta(this);
                componentes.set(i, componenteNuevo);
                return;
            }
        }
        componenteNuevo.setBicicleta(this);
        componentes.add(componenteNuevo);
    }

    /**
     * Registra una nueva hoja de reparación en la lista histórica de este vehículo,
     * asegurando que la referencia externa apunte correctamente a esta bicicleta.
     * * @param rep La reparación finalizada o en progreso a archivar.
     */
    public void agregarAlHistorial(Reparacion rep) {
        rep.setBicicleta(this);
        historialMantenimiento.add(rep);
    }

    // Getters y Setters...
    public int getIdBicicleta()                                        { return idBicicleta; }
    public void setIdBicicleta(int idBicicleta)                        { this.idBicicleta = idBicicleta; }
    public String getMarca()                                           { return marca; }
    public void setMarca(String marca)                                 { this.marca = marca; }
    public String getModelo()                                          { return modelo; }
    public void setModelo(String modelo)                               { this.modelo = modelo; }
    public Usuario getPropietario()                                    { return propietario; }
    public void setPropietario(Usuario propietario)                    { this.propietario = propietario; }
    public List<Componente> getComponentes()                           { return componentes; }
    public List<Reparacion> getHistorialMantenimiento()                { return historialMantenimiento; }
}