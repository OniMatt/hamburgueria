package com.hamburgueria.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "nome", unique = true)
  private String nome;

  @Column(name = "preco")
  private double preco;

  public Produto( String nome, double preco ) {
    this.nome = nome;
    this.preco = preco;
  }

  public Produto() {
  }

  public static List<Produto> inicializaProdutos() {
    List<Produto> produtos = new ArrayList<>();
    produtos.add( new Produto( "Hambúrguer de Risoto de Camarão", 35.0 ) ); 
    produtos.add( new Produto( "Hambúrguer de Macarrão à Bolonhesa", 20.0 ) );
    produtos.add( new Produto( "Hambúrguer de Fettuccine ao Molho Alfredo", 30.0 ) );
    produtos.add( new Produto( "Hambúrguer de Filé Mignon ao Molho Madeira", 25.0 ) );
    produtos.add( new Produto( "Hambúrguer de Gnocchi de Ricota", 25.0 ) );
    produtos.add( new Produto( "Del Valle de Morango", 5.0 ) ); 
    produtos.add( new Produto( "Dolly Limão", 8.0 ) );
    produtos.add( new Produto( "Charrua Safra 1980 Envelhecido", 19.0 ) );
    produtos.add( new Produto( "Chá Matte Leão", 5.0 ) );
    produtos.add( new Produto( "Ceva", 5.0 ) );

    return produtos;
  }

  public String getNome() {
    return nome;
  }

  public void setNome( String nome ) {
    this.nome = nome;
  }

  public double getPreco() {
    return preco;
  }

  public void setPreco( double preco ) {
    this.preco = preco;
  }

  @Override
  public String toString() {
    return nome + ", " + preco + "R$\n";
  }
}
