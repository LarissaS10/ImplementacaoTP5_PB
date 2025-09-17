package org.example.app;
import io.javalin.Javalin;
import org.example.data.Database;
import org.example.model.Cliente;
import org.example.model.Produto;

import java.util.stream.Collectors;

public class ServerApp {
    public static void start() {
        Database.preencherDadosIniciais();

        Javalin app = Javalin.create().start(7000);

        //ENDPOINTS DE CLIENTE (CRUD)
        app.get("/clientes", ctx -> ctx.json(Database.clientes));
        app.get("/clientes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Cliente cliente = Database.clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
            if (cliente != null) {
                ctx.json(cliente);
            } else {
                ctx.status(404).result("Cliente não encontrado.");
            }
        });
        app.post("/clientes", ctx -> {
            Cliente novoCliente = ctx.bodyAsClass(Cliente.class);
            novoCliente.setId(Database.proximoIdCliente++);
            Database.clientes.add(novoCliente);
            ctx.status(201).json(novoCliente);
        });
        app.put("/clientes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Cliente clienteAtualizado = ctx.bodyAsClass(Cliente.class);
            clienteAtualizado.setId(id);
            Database.clientes = Database.clientes.stream().map(c -> c.getId() == id ? clienteAtualizado : c).collect(Collectors.toList());
            ctx.status(200).json(clienteAtualizado);
        });
        app.delete("/clientes/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean removido = Database.clientes.removeIf(c -> c.getId() == id);
            if (removido) {
                ctx.status(204);
            } else {
                ctx.status(404).result("Cliente não encontrado.");
            }
        });

        // ENDPOINTS DE PRODUTO (CRUD)
        app.get("/produtos", ctx -> ctx.json(Database.produtos));
        app.get("/produtos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Produto produto = Database.produtos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
            if (produto != null) {
                ctx.json(produto);
            } else {
                ctx.status(404).result("Produto não encontrado.");
            }
        });
        app.post("/produtos", ctx -> {
            Produto novoProduto = ctx.bodyAsClass(Produto.class);
            novoProduto.setId(Database.proximoIdProduto++);
            Database.produtos.add(novoProduto);
            ctx.status(201).json(novoProduto);
        });
        app.put("/produtos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Produto produtoAtualizado = ctx.bodyAsClass(Produto.class);
            produtoAtualizado.setId(id);
            Database.produtos = Database.produtos.stream().map(p -> p.getId() == id ? produtoAtualizado : p).collect(Collectors.toList());
            ctx.status(200).json(produtoAtualizado);
        });
        app.delete("/produtos/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean removido = Database.produtos.removeIf(p -> p.getId() == id);
            if (removido) {
                ctx.status(204);
            } else {
                ctx.status(404).result("Produto não encontrado.");
            }
        });

        System.out.println("Servidor Javalin iniciado na porta 7000.");
    }
}
