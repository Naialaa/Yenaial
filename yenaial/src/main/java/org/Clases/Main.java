package org.Clases;

import jakarta.persistence.*;
import paquete.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase principal encargada de orquestar las pruebas de integración del taller.
 * Coordina la inserción de datos, relaciones JPA y consultas de control,
 * dejando constancia de los eventos principales en el sistema de logs.
 * * @author TuNombre
 * @version 1.0
 */
public class Main {

    /**
     * Punto de entrada de la aplicación. Configura la factoría de persistencia,
     * ejecuta el flujo secuencial de pruebas del taller (usuarios, bicicletas,
     * componentes, reparaciones y citas) y vuelca reportes por consola.
     * * @param args Argumentos de inicialización por consola (no utilizados).
     */
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("yenaial");

        try {
            // ── 1. CREAR USUARIO ───────────────────────────────────────────
            paquete.Usuario usuario = new paquete.Usuario();
            usuario.setNombre("María García");
            usuario.setEmail("maria@bicifix.es");
            usuario.setRol("cliente");
            usuario.setPasswordHash("$2b$12$EKC9OYQXwpXvRUo5LdEDYeaQXYgr.va3B9vK52F2MeeHuYCj9l5k6");

            persistir(emf, usuario);
            GestorLog.registrarEvento("INFO", "Usuario creado: " + usuario.getEmail());
            System.out.println("✔ Usuario guardado  → id=" + usuario.getId());

            // ── 2. REGISTRAR BICICLETA ─────────────────────────────────────
            paquete.Bicicleta bici = new paquete.Bicicleta();
            bici.setMarca("Trek");
            bici.setModelo("Marlin 7");
            bici.setIdUsuario(usuario);

            persistir(emf, bici);
            GestorLog.registrarEvento("INFO", "Bicicleta registrada: " + bici.getMarca() + " " + bici.getModelo());
            System.out.println("✔ Bicicleta guardada → id=" + bici.getId());

            // ── 3. AÑADIR COMPONENTES ──────────────────────────────────────
            paquete.Componente frenos = new paquete.Componente();
            frenos.setTipo("Frenos");
            frenos.setMarcaModelo("Shimano MT200");
            frenos.setEstadoSalud("Buen estado");
            frenos.setNecesitaCambio(false);
            frenos.setIdBicicleta(bici);

            paquete.Componente cadena = new paquete.Componente();
            cadena.setTipo("Cadena");
            cadena.setMarcaModelo("KMC X11");
            cadena.setEstadoSalud("Desgastado");
            cadena.setNecesitaCambio(true);
            cadena.setIdBicicleta(bici);

            persistir(emf, frenos);
            persistir(emf, cadena);
            System.out.println("✔ Componentes guardados");
            System.out.println("   → Cadena necesita cambio: " + cadena.getNecesitaCambio());

            // ── 4. ABRIR REPARACIÓN ────────────────────────────────────────
            paquete.Reparacione rep = new paquete.Reparacione();
            rep.setIdBicicleta(bici);
            rep.setDescripcionTrabajo("Cambio de cadena y ajuste de frenos");
            rep.setFecha(LocalDate.now());
            rep.setEstadoProgreso("EN_DIAGNOSTICO");

            persistir(emf, rep);
            GestorLog.registrarEvento("INFO", "Reparación abierta id=" + rep.getId());
            System.out.println("✔ Reparación abierta → estado: " + rep.getEstadoProgreso());

            // ── 5. AVANZAR ESTADO ──────────────────────────────────────────
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            paquete.Reparacione repManaged = em.find(paquete.Reparacione.class, rep.getId());
            repManaged.setEstadoProgreso("EN_REPARACION");
            em.getTransaction().commit();
            em.close();
            System.out.println("✔ Estado actualizado → " + repManaged.getEstadoProgreso());

            // ── 6. PEDIR CITA ──────────────────────────────────────────────
            paquete.Cita cita = new paquete.Cita();
            cita.setIdUsuario(usuario);
            cita.setIdBicicleta(bici);
            cita.setFechaHora(Instant.now().plusSeconds(60 * 60 * 24 * 3)); // +3 días

            persistir(emf, cita);
            GestorLog.registrarEvento("INFO", "Cita creada para: " + cita.getFechaHora());
            System.out.println("✔ Cita guardada     → " + cita.getFechaHora());

            // ── 7. CONSULTAS ───────────────────────────────────────────────
            em = emf.createEntityManager();

            System.out.println("\n── Usuarios en BD ──────────────────────────────────");
            List<paquete.Usuario> usuarios = em
                    .createQuery("SELECT u FROM Usuario u", paquete.Usuario.class)
                    .getResultList();
            for (paquete.Usuario u : usuarios) {
                System.out.printf("  [%d] %-20s %-30s %s%n",
                        u.getId(), u.getNombre(), u.getEmail(), u.getRol());
            }

            System.out.println("\n── Bicicletas del usuario ───────────────────────────");
            List<paquete.Bicicleta> bicis = em
                    .createQuery("SELECT b FROM Bicicleta b WHERE b.idUsuario = :id", paquete.Bicicleta.class)
                    .setParameter("id", usuario.getId())
                    .getResultList();
            for (paquete.Bicicleta b : bicis) {
                System.out.printf("  [%d] %s %s%n", b.getId(), b.getMarca(), b.getModelo());
            }

            System.out.println("\n── Componentes que necesitan cambio ─────────────────");
            List<paquete.Componente> alertas = em
                    .createQuery("SELECT c FROM Componente c WHERE c.necesitaCambio = true", paquete.Componente.class)
                    .getResultList();
            for (paquete.Componente c : alertas) {
                System.out.printf("  %-15s %-20s estado: %s%n",
                        c.getTipo(), c.getMarcaModelo(), c.getEstadoSalud());
            }

            System.out.println("\n── Reparaciones activas ─────────────────────────────");
            List<paquete.Reparacione> reparaciones = em
                    .createQuery("SELECT r FROM Reparacione r WHERE r.estadoProgreso <> 'LISTA_PARA_RETIRAR'",
                            paquete.Reparacione.class)
                    .getResultList();
            for (paquete.Reparacione r : reparaciones) {
                System.out.printf("  [%d] %s  →  %s%n",
                        r.getId(), r.getDescripcionTrabajo(), r.getEstadoProgreso());
            }

            em.close();

            // ── 8. MARCAR COMO LISTA PARA RETIRAR ─────────────────────────
            em = emf.createEntityManager();
            em.getTransaction().begin();
            paquete.Reparacione repFinal = em.find(paquete.Reparacione.class, rep.getId());
            repFinal.setEstadoProgreso("LISTA_PARA_RETIRAR");
            em.getTransaction().commit();
            em.close();
            System.out.println("\n✔ Reparación marcada como LISTA_PARA_RETIRAR");
            GestorLog.registrarEvento("INFO", "Reparación finalizada id=" + rep.getId());

        } finally {
            emf.close();
        }
    }

    /**
     * Método utilitario (Helper) encargado de abrir transacciones independientes,
     * persistir cualquier entidad mapeada por JPA en la base de datos
     * y registrar fallos de persistencia en el log.
     * * @param emf Factoría para la creación del gestor de entidades (EntityManager).
     * @param entidad Objeto o registro mapeado por JPA que se desea almacenar.
     */
    private static void persistir(EntityManagerFactory emf, Object entidad) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            GestorLog.registrarEvento("ERROR",
                    "Error al persistir " + entidad.getClass().getSimpleName() + ": " + e.getMessage());
            throw e;
        } finally {
            em.close();
        }
    }
}