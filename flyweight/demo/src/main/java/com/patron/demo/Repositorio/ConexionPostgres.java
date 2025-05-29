package com.patron.demo.Repositorio;

import com.patron.demo.implementacion.ListaReproduccion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConexionPostgres {

    public void guardarListaReproduccion(ListaReproduccion lista) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(lista);
            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }
    }
}
