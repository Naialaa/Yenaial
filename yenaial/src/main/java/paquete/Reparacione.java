package paquete;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "reparaciones", schema = "yenaial_db")
public class Reparacione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reparacion", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_bicicleta", nullable = false)
    private Bicicleta idBicicleta;

    @Lob
    @Column(name = "descripcion_trabajo")
    private String descripcionTrabajo;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ColumnDefault("'EN_DIAGNOSTICO'")
    @Lob
    @Column(name = "estado_progreso", nullable = false)
    private String estadoProgreso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bicicleta getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(Bicicleta idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo(String descripcionTrabajo) {
        this.descripcionTrabajo = descripcionTrabajo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstadoProgreso() {
        return estadoProgreso;
    }

    public void setEstadoProgreso(String estadoProgreso) {
        this.estadoProgreso = estadoProgreso;
    }

}