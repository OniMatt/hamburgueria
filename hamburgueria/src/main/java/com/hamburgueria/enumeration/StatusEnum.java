package com.hamburgueria.enumeration;

public enum StatusEnum {
  EM_PREPARO("Em preparo"),
  EM_TRANSITO("Saiu para entrega"),
  ENTREGUE("Entregue"),
  CANCELADO("Cancelado");

  public final String texto;

  private StatusEnum(String texto) {
    this.texto = texto;
  }
  public static void imprimeStatus() {
    System.out.println( "1. Em preparo" );
    System.out.println( "2. Saiu para entrega" );
    System.out.println( "3. Entregue" );
    System.out.println( "4. Cancelado" );
  }
}
