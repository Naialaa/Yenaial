package org.Clases;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa una cita programada en el taller.
 * Permite gestionar la reserva de fechas y horas para que un usuario
 * traiga su bicicleta a revisión o reparación.
 * * @author TuNombre
 * @version 1.0
 */
@Entity
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private int idCita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_addbicicleta", nullable = false)
    private Bicicleta bicicleta;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "motivo", length = 255)
    private String motivo;

    public Cita() {}

    /**
     * Crea una nueva cita con todos los datos requeridos.
     * * @param usuario El cliente que solicita la cita.
     * @param bicicleta La bicicleta que se llevará al taller.
     * @param fechaHora Fecha y hora programada del encuentro.
     * @param motivo Descripción breve del problema o revisión a realizar.
     */
    public Cita(Usuario usuario, Bicicleta bicicleta, LocalDateTime fechaHora, String motivo) {
        this.usuario   = usuario;
        this.bicicleta = bicicleta;
        this.fechaHora = fechaHora;
        this.motivo    = motivo;
    }

    // Getters y Setters...
    public int getIdCita()                              { return idCita; }
    public void setIdCita(int idCita)                   { this.idCita = idCita; }
    public Usuario getUsuario()                         { return usuario; }
    public void setUsuario(Usuario usuario)             { this.usuario = usuario; }
    public Bicicleta getBicicleta()                     { return bicicleta; }
    public void setBicicleta(Bicicleta bicicleta)       { this.bicicleta = bicicleta; }
    public LocalDateTime getFechaHora()                 { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora)   { this.fechaHora = fechaHora; }
    public String getMotivo()                           { return motivo; }
    public void setMotivo(String motivo)                { this.motivo = motivo; }
}