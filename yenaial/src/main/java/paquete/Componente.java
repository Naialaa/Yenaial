package paquete;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "componentes", schema = "yenaial_db")
public class Componente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_componente", nullable = false)
    private Integer id;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "marca_modelo", nullable = false, length = 100)
    private String marcaModelo;

    @Column(name = "estado_salud", nullable = false, length = 30)
    private String estadoSalud;

    @ColumnDefault("0")
    @Column(name = "necesita_cambio", nullable = false)
    private Boolean necesitaCambio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_bicicleta", nullable = false)
    private Bicicleta idBicicleta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarcaModelo() {
        return marcaModelo;
    }

    public void setMarcaModelo(String marcaModelo) {
        this.marcaModelo = marcaModelo;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public Boolean getNecesitaCambio() {
        return necesitaCambio;
    }

    public void setNecesitaCambio(Boolean necesitaCambio) {
        this.necesitaCambio = necesitaCambio;
    }

    public Bicicleta getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(Bicicleta idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

}