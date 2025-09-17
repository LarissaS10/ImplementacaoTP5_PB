package org.example.data;
import org.example.model.Cliente;
import org.example.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<Cliente> clientes = new ArrayList<>();
    public static List<Produto> produtos = new ArrayList<>();
    public static int proximoIdCliente = 1;
    public static int proximoIdProduto = 1;

    public static void preencherDadosIniciais() {
        //adicionando clientes
        clientes.add(new Cliente(proximoIdCliente++, "Larissa Souza", "larissa@email.com"));
        clientes.add(new Cliente(proximoIdCliente++, "Elias Flach", "elias@email.com"));
        clientes.add(new Cliente(proximoIdCliente++, "Apolo Souza Flach", "apolo@email.com"));

        //adicionando produtos
        produtos.add(new Produto(proximoIdProduto++, "Notebook", 3500));
        produtos.add(new Produto(proximoIdProduto++, "Mouse", 150.00));
        produtos.add(new Produto(proximoIdProduto++, "Teclado", 300.00));
    }
}
