package com.hamburgueria.repository;

import java.util.List;

import javax.persistence.*;

import com.hamburgueria.domain.Produto;


public class ProdutoRepo {
    
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hamburgueriajpa");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();
    private static EntityTransaction transaction = entityManager.getTransaction();

    public static void criaProduto(Produto produto) {
        transaction.begin();
        entityManager.persist(produto);
        transaction.commit();
    }

    public static Produto getProdutoById(Integer id) {
        return entityManager.find(Produto.class, id);
    }

    public static List<Produto> getProdutos() {
        return entityManager.createQuery("SELECT p FROM Produto p", Produto.class)
            .getResultList();
    }

    public static void atualizaProduto(Produto produto) {
        transaction.begin();
        entityManager.merge(produto);
        transaction.commit();
    }

    public static void deletaProduto(Integer id) {
        Produto produto = getProdutoById(id);
        if(produto != null) {
            transaction.begin();
            entityManager.remove(produto);
            transaction.commit();
        }
    }
}
