package com.hamburgueria.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.hamburgueria.enumeration.StatusEnum;

@Entity
@Table(name="pedido")
public class Pedido {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Transient
  private List<Produto> produtosPedido;

  @Enumerated(EnumType.STRING)
  private StatusEnum status;

  @ManyToOne
  @JoinColumn(name = "cliente", referencedColumnName = "id")
  private Cliente cliente;

  public Pedido( StatusEnum status, Cliente cliente ) {
    this.produtosPedido = new ArrayList<>();
    this.status = status;
    this.cliente = cliente;
  }

  public Pedido() {
  }

  public void atualizaPedido() {

  }

  public void imprimePedido() {
    System.out.println( "Pedido: " );
    produtosPedido.stream().forEach( p -> System.out.println( p.toString() ) );
    System.out.println( getPrecoTotal() + "R$" );
  }

  public double getPrecoTotal() {
    return produtosPedido.stream()
        .mapToDouble( Produto::getPreco )
        .sum();
  }

  public List<Produto> getProdutosPedido() {
    return produtosPedido;
  }

  public void setProdutosPedido( List<Produto> produtos ) {
    this.produtosPedido = produtos;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus( StatusEnum status ) {
    this.status = status;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente( Cliente cliente ) {
    this.cliente = cliente;
  }

  @Override
  public String toString() {
    return getPrecoTotal() + "R$, " + cliente.getNome();
  }

}
