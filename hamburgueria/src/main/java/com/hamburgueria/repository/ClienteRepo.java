package com.hamburgueria.repository;

import java.util.List;

import javax.persistence.*;

import com.hamburgueria.domain.Cliente;


public class ClienteRepo {
    
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hamburgueriajpa");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static EntityTransaction transaction = entityManager.getTransaction();

    public static void criaCliente(Cliente cliente) {
        transaction.begin();
        entityManager.persist(cliente);
        transaction.commit();
    }

    public static Cliente getClienteById(Integer id) {
        return entityManager.find(Cliente.class, id);
    }

    public static Cliente getClienteByEmail(String email) {
        return (Cliente)entityManager.createQuery("SELECT c FROM Cliente c WHERE email = '" + email + "'")
            .getSingleResult();
    }

    public static List<Cliente> getClientes() {
        return entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class)
            .getResultList();
    }

    public static void atualizaCliente(Cliente cliente) {
        transaction.begin();
        entityManager.merge(cliente);
        transaction.commit();
    }

    public static void deletaCliente(Integer id) {
        Cliente cliente = getClienteById(id);
        if(cliente != null) {
            transaction.begin();
            entityManager.remove(cliente);
            transaction.commit();
        }
    }
}
