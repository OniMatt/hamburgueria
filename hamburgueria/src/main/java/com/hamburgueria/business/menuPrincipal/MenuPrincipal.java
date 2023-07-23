package com.hamburgueria.business.menuPrincipal;

import java.util.Optional;

import com.hamburgueria.Hamburgueria;
import com.hamburgueria.domain.Cliente;
import com.hamburgueria.repository.HamburgueriaRepo;
import com.hamburgueria.utils.Teclado;

public class MenuPrincipal {
  private static Hamburgueria h = new Hamburgueria( "McSyo" );

  public static void menuPrincipal() {
    HamburgueriaRepo.criaHamburgueria(h);
    HamburgueriaRepo.inicializaProdutos(h);
    boolean sair = false;

    while ( !sair ) {
      imprimeMenuPrincipal();

      int opcao = Teclado.leInt( "Escolha uma opção." );
      switch ( opcao ) {
        case ( 1 ):
          Cliente clienteTemp = h.cadastraCliente().get();
          boolean criarPedido = Teclado.leBoolean( "Deseja criar um pedido para este cliente? " );
          if ( criarPedido )
            h.criaPedido( clienteTemp );
          break;

        case ( 2 ):
          Optional< Cliente > clienteTemp2 = h.getCliente();
          if ( !clienteTemp2.isPresent() ) {
            break;
          } 
          h.criaPedido( clienteTemp2.get() );
          Teclado.pressioneEnter();
          break;

        case ( 3 ):
          h.atualizaPedido();
          break;

        case ( 4 ):
          break;

        case ( 5 ):
          return;

        default:
          Teclado.pressioneEnter( "Opção inválida, pressione Enter para continuar." );
      }
    }
  }

  public static void imprimeMenuPrincipal() {
    System.out.println( " ==========[ Menu ]==========" );
    System.out.println( "|                            |" );
    System.out.println( "| 1. Cadastrar cliente.      |" );
    System.out.println( "| 2. Montar pedido.          |" );
    System.out.println( "| 3. Atualiza pedido.        |" );
    System.out.println( "| 4. Interface do cliente.   |" );
    System.out.println( "| 5. Fechar.                 |" );
    System.out.println( "|                            |" );
    System.out.println( " =============================" );
  }

}
