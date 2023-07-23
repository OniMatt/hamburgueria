package com.hamburgueria.business.appCliente;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

import com.hamburgueria.Hamburgueria;
import com.hamburgueria.domain.Cliente;
import com.hamburgueria.domain.Pedido;
import com.hamburgueria.enumeration.StatusEnum;
import com.hamburgueria.repository.ClienteRepo;
import com.hamburgueria.repository.PedidoRepo;
import com.hamburgueria.utils.Teclado;

public class AppCliente {

    public static void signin() {
        boolean emailExistente = false;
        String email = Teclado.leString("Informe seu e-mail:");
        String senha = Teclado.leString("Cadastre sua senha:");

        while(!emailExistente) {
            try{
                Cliente cliente = ClienteRepo.getClienteByEmail(email);
                emailExistente = true;
                cliente.setSenha(senha);
                ClienteRepo.atualizaCliente(cliente);
            } catch(NoSuchElementException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        
        System.out.println("Cadastro completo.");
        Teclado.pressioneEnter();
    }

    public static Optional< Cliente > login() {
        boolean senhaCorreta = false;
        boolean emailExistente = false;
        Optional< Cliente > cliente = Optional.of(new Cliente());

        while (!senhaCorreta && !emailExistente) {
            String email = Teclado.leString("Informe seu e-mail:");
            String senha = Teclado.leString("Informe sua senha:");

            while(!emailExistente) {
                try{
                    cliente = Optional.of(ClienteRepo.getClienteByEmail(email));
                    emailExistente = true;
                } catch(NoSuchElementException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            
            if (!cliente.get().getSenha().equals(senha)) {
                System.out.println("A senha está incorreta, tente novamente.");
            }
            senhaCorreta = true;
            System.out.println("Você está logado.");
            Teclado.pressioneEnter();
            return cliente;
        }
        return Optional.empty();
    }

    public void logoff() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logoff'");
    }

    public static void consultaPedido(Pedido pedido) {
        System.out.println("Status: " + pedido.getStatus().texto);
    }

    public static void editaCadastro(Cliente cliente) {
        System.out.println("1. Nome: " + cliente.getNome());
        System.out.println("2. E-mail: " + cliente.getEmail());
        System.out.println("3. Telefone: " + cliente.getTelefone());
        System.out.println("4. Senha: " + cliente.getSenha());

        int opcao = Teclado.leInt("Qual informação deseja editar?");
        String alteracao = Teclado.leString("Qual será a nova informação?");

        switch( opcao ){
            case( 1 ):
                cliente.setNome(alteracao);
                System.out.println("Nome alterado para: " + alteracao);
                break;
            case( 2 ):
                cliente.setEmail(alteracao);
                System.out.println("E-mail alterado para: " + alteracao);
                break;
            case( 3 ):
                cliente.setTelefone(alteracao);
                System.out.println("Telefone alterado para: " + alteracao);
                break;
            case( 4 ):
                cliente.setSenha(alteracao);
                System.out.println("Senha alterada para: " + alteracao);
                break;
        }
        Teclado.pressioneEnter();
    }

    public static void cancelaPedido(Pedido pedido) {
        pedido.setStatus(StatusEnum.CANCELADO);
        PedidoRepo.atualizaPedido(pedido);
        System.out.println("Pedido cancelado.");
    }

    public static void menuLogin(Cliente cliente) {
        boolean sair = false;

        while(!sair) {
            imprimeMenuLogin();
            int opcao = Teclado.leInt("Escolha uma opção");

            switch(opcao) {
                case( 1 ):
                    consultaPedido(selecionaPedido(cliente).get());
                    break;
                
                case( 2 ):
                    editaCadastro(cliente);
                    break;
                
                case( 3 ):
                    cancelaPedido(selecionaPedido(cliente).get());
                    break;
                case( 4 ):
                    System.exit(0);
                default:
                    System.out.println("Selecione uma opção válida.");
            }
        }

    }

    public static void menuClientes() {
        boolean sair = false;
        while( !sair ) {
            imprimeMenuClientes();
            int opcao = Teclado.leInt("Escolha uma opção");

            switch(opcao) {
                case( 1 ):
                    menuLogin(login().get());
                    break;
                
                case( 2 ):
                    signin();
                    break;
                
                case( 3 ):
                    System.exit(0);

                default:
                    System.out.println("Selecione uma opção válida.");
            }
        }
        
    }

    public static Optional< Pedido > selecionaPedido( Cliente cliente ) {
        List<Pedido> pedidos = Hamburgueria.getClientePedidos( cliente );
        IntStream.range( 0, pedidos.size() )
            .forEach( i -> {
                System.out.print (( i+1 ) + ". " );
                pedidos.get( i ).imprimePedido();
            });
        int pedidoIndex = Teclado.leInt( "Qual pedido você deseja consultar?" );
        return Optional.of( pedidos.get( pedidoIndex - 1 ) );
    }

    public static void imprimeMenuClientes() {
        System.out.println( " ==========[ Menu ]==========" );
        System.out.println( "|                            |" );
        System.out.println( "| 1. Login.                  |" );
        System.out.println( "| 2. Cadastro.               |" );
        System.out.println( "| 3. Sair.                   |" );
        System.out.println( "|                            |" );
        System.out.println( " =============================" );
    }

    public static void imprimeMenuLogin() {
        System.out.println( " ==========[ Menu ]==========" );
        System.out.println( "|                            |" );
        System.out.println( "| 1. Consultar pedido.       |" );
        System.out.println( "| 2. Editar cadastro.        |" );
        System.out.println( "| 3. Cancelar pedido.        |" );
        System.out.println( "| 4. Sair.                   |" );
        System.out.println( "|                            |" );
        System.out.println( " =============================" );
    }

}
