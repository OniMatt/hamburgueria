package com.hamburgueria.repository;

import java.util.List;

import javax.persistence.*;

import com.hamburgueria.Hamburgueria;

public class HamburgueriaRepo {
    
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hamburgueriajpa");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static EntityTransaction transaction = entityManager.getTransaction();

    public static void criaHamburgueria(Hamburgueria hamburgueria) {
        transaction.begin();
        try {
            entityManager.persist(hamburgueria);
            transaction.commit();
        } catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
            transaction.rollback();
        }    
    }

    public static Hamburgueria getHamburgueriaById(Integer id) {
        return entityManager.find(Hamburgueria.class, id);
    }

    public static List<Hamburgueria> getHamburguerias() {
        return entityManager.createQuery("SELECT h FROM Hamburgueria h", Hamburgueria.class)
            .getResultList();
    }

    public static void atualizaHamburgueria(Hamburgueria hamburgueria) {
        transaction.begin();
        entityManager.merge(hamburgueria);
        transaction.commit();
    }

    public static void deletaHamburgueria(Integer id) {
        Hamburgueria hamburgueria = getHamburgueriaById(id);
        if(hamburgueria != null) {
            transaction.begin();
            entityManager.remove(hamburgueria);
            transaction.commit();
        }
    }

    public static void inicializaProdutos(Hamburgueria hamburgueria) {
        transaction.begin();
        try {
            hamburgueria.getCardapio().stream()
            .forEach(produto -> entityManager.persist(produto));
            transaction.commit();
        } catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
            transaction.rollback();
        }
    }
}
