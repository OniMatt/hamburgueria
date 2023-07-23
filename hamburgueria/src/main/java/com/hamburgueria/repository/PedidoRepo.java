package com.hamburgueria.repository;

import java.util.List;

import javax.persistence.*;

import com.hamburgueria.domain.Pedido;


public class PedidoRepo {
    
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hamburgueriajpa");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static EntityTransaction transaction = entityManager.getTransaction();

    public static void criaPedido(Pedido pedido) {
        transaction.begin();
        entityManager.persist(pedido);
        transaction.commit();
    }

    public static Pedido getPedidoById(Integer id) {
        return entityManager.find(Pedido.class, id);
    }

    public static List<Pedido> getPedidos() {
        return entityManager.createQuery("SELECT p FROM Pedido p", Pedido.class)
            .getResultList();
    }

    public static void atualizaPedido(Pedido pedido) {
        transaction.begin();
        entityManager.merge(pedido);
        transaction.commit();
    }

    public static void deletaPedido(Integer id) {
        Pedido pedido = getPedidoById(id);
        if(pedido != null) {
            transaction.begin();
            entityManager.remove(pedido);
            transaction.commit();
        }
    }
}
