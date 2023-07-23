package com.hamburgueria.domain;

import javax.persistence.*;

import com.hamburgueria.Hamburgueria;

@Entity
@Table(name="cliente")
public class Cliente {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(name="nome")
  private String nome;

  @Column(name="senha", nullable = true)
  private String senha;

  @Column(name="telefone")
  private String telefone;

  @Column(name="email")
  private String email;

  @ManyToOne
  @JoinColumn(name="hamburgueria", referencedColumnName = "id")
  private Hamburgueria hamburgueria;

  public Cliente() {
  }

  public Cliente(String nome, String senha, String telefone, String email) {
    this.nome = nome;
    this.senha = senha;
    this.telefone = telefone;
    this.email = email;
  }

  public Cliente(String nome, String telefone, String email) {
    this.nome = nome;
    this.telefone = telefone;
    this.email = email;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return nome + ", e-mail: " + email + ", telefone: " + telefone + "\n";
  }

  public Hamburgueria getHamburgueria() {
    return hamburgueria;
  }

  public void setHamburgueria(Hamburgueria hamburgueria) {
    this.hamburgueria = hamburgueria;
  }
}
