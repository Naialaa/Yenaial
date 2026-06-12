package org.Clases;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidad que mapea el flujo de trabajo de una reparación en el taller.
 * Almacena el diagnóstico, estados y sincronización del mantenimiento.
 * * @author TuNombre
 * @version 1.0
 */
@Entity
@Table(name = "reparaciones")
public class Reparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reparacion")
    private int idReparacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bicicleta", nullable = false)
    private Bicicleta bicicleta;

    @Column(name = "descripcion_trabajo", columnDefinition = "TEXT")
    private String descripcionTrabajo;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_progreso", nullable = false)
    private EstadoProgreso estadoProgreso = EstadoProgreso.EN_DIAGNOSTICO;

    /**
     * Enum que define los diferentes estados del ciclo de vida de una reparación.
     */
    public enum EstadoProgreso {
        EN_DIAGNOSTICO,
        EN_REPARACION,
        LISTA_PARA_PRUEBA,
        LISTA_PARA_RETIRAR
    }

    public Reparacion() {}

    /**
     * Construye un registro de reparación y lo vincula de forma bidireccional
     * al historial de la bicicleta afectada.
     * * @param bicicleta La bicicleta que entra a taller.
     * @param descripcionTrabajo Detalle de las operaciones o fallos detectados.
     * @param fecha Fecha de apertura de la orden.
     */
    public Reparacion(Bicicleta bicicleta, String descripcionTrabajo, LocalDate fecha) {
        this.bicicleta          = bicicleta;
        this.descripcionTrabajo = descripcionTrabajo;
        this.fecha              = fecha;
        this.estadoProgreso     = EstadoProgreso.EN_DIAGNOSTICO;
        bicicleta.agregarAlHistorial(this);
    }

    // Getters y Setters...
    public int getIdReparacion()                                { return idReparacion; }
    public void setIdReparacion(int idReparacion)               { this.idReparacion = idReparacion; }
    public Bicicleta getBicicleta()                             { return bicicleta; }
    public void setBicicleta(Bicicleta bicicleta)               { this.bicicleta = bicicleta; }
    public String getDescripcionTrabajo()                       { return descripcionTrabajo; }
    public void setDescripcionTrabajo(String descripcionTrabajo){ this.descripcionTrabajo = descripcionTrabajo; }
    public LocalDate getFecha()                                 { return fecha; }
    public void setFecha(LocalDate fecha)                       { this.fecha = fecha; }
    public EstadoProgreso getEstadoProgreso()                   { return estadoProgreso; }
    public void setEstadoProgreso(EstadoProgreso estadoProgreso){ this.estadoProgreso = estadoProgreso; }
}