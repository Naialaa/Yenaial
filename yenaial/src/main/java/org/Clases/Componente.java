package org.Clases;

import jakarta.persistence.*;

/**
 * Entidad que representa un componente específico montado en una bicicleta
 * (por ejemplo: frenos, cadena, transmisión). Permite monitorizar su desgaste.
 * * @author TuNombre
 * @version 1.0
 */
@Entity
@Table(name = "componentes")
public class Componente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_componente")
    private int idComponente;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "marca_modelo", nullable = false, length = 100)
    private String marcaModelo;

    @Column(name = "estado_salud", nullable = false, length = 30)
    private String estadoSalud;

    @Column(name = "necesita_cambio", nullable = false)
    private boolean necesitaCambio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bicicleta", nullable = false)
    private Bicicleta bicicleta;

    public Componente() {}

    /**
     * Crea un componente asociándole su tipo, modelo y estado inicial.
     * Llama internamente a la comprobación de mantenimiento.
     * * @param tipo Categoría del componente (ej: "Frenos").
     * @param marcaModelo Fabricante y modelo exacto.
     * @param estadoSalud Estado actual (ej: "Buen estado", "Desgastado", "Critico").
     */
    public Componente(String tipo, String marcaModelo, String estadoSalud) {
        this.tipo        = tipo;
        this.marcaModelo = marcaModelo;
        this.estadoSalud = estadoSalud;
        comprobarMantenimiento();
    }

    /** * Actualiza el flag necesitaCambio según el estado de salud actual.
     * El cambio se activa si el estado es 'Desgastado' o 'Critico'.
     */
    public void comprobarMantenimiento() {
        this.necesitaCambio =
                this.estadoSalud.equalsIgnoreCase("Desgastado") ||
                        this.estadoSalud.equalsIgnoreCase("Critico");
    }

    // Getters y Setters...
    public int getIdComponente()                        { return idComponente; }
    public void setIdComponente(int idComponente)       { this.idComponente = idComponente; }
    public String getTipo()                             { return tipo; }
    public void setTipo(String tipo)                    { this.tipo = tipo; }
    public String getMarcaModelo()                      { return marcaModelo; }
    public void setMarcaModelo(String marcaModelo)      { this.marcaModelo = marcaModelo; }
    public String getEstadoSalud()                      { return estadoSalud; }

    /**
     * Actualiza el estado de salud y fuerza el recálculo automático
     * de la alerta de mantenimiento.
     * * @param estadoSalud El nuevo estado físico del componente.
     */
    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
        comprobarMantenimiento();
    }
    public boolean isNecesitaCambio()                   { return necesitaCambio; }
    public Bicicleta getBicicleta()                     { return bicicleta; }
    public void setBicicleta(Bicicleta bicicleta)       { this.bicicleta = bicicleta; }
}