package com.hamburgueria;
 
import java.util.ArrayList;
import java.util.List;

import com.hamburgueria.domain.Cliente;
import com.hamburgueria.domain.Pedido;
import com.hamburgueria.domain.Produto;
import com.hamburgueria.enumeration.StatusEnum;
import com.hamburgueria.repository.ClienteRepo;
import com.hamburgueria.business.appCliente.AppCliente;
import com.hamburgueria.business.menuPrincipal.MenuPrincipal;
import com.hamburgueria.repository.HamburgueriaRepo;
import com.hamburgueria.repository.PedidoRepo;
import com.hamburgueria.repository.ProdutoRepo;

public class Main {
  public static void main( String[] args ) {
    /**Hamburgueria h = new Hamburgueria("Teste");
    Cliente c = new Cliente("junior", "12345", "teste.com");
    Produto.inicializaProdutos();
    Pedido p = new Pedido(StatusEnum.EM_PREPARO, c);
    List<Produto> produtos = new ArrayList<>();
    

    c.setHamburgueria(h);
    
    HamburgueriaRepo.criaHamburgueria(h);
    HamburgueriaRepo.inicializaProdutos(h);

    produtos.add(ProdutoRepo.getProdutoById(1));
    produtos.add(ProdutoRepo.getProdutoById(2));
    produtos.add(ProdutoRepo.getProdutoById(3));
    p.setProdutosPedido(produtos);

    ClienteRepo.criaCliente(c);

    PedidoRepo.criaPedido(p);


    crepo.atualizaCliente(c);
    crepo.getClienteById(1);
    crepo.getClientes();
    crepo.deletaCliente(1);**/

    MenuPrincipal.menuPrincipal();
    AppCliente.menuClientes();
  }
}
