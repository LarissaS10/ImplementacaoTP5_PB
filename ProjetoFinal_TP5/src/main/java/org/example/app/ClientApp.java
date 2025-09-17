package org.example.app;

import okhttp3.*;
import java.io.IOException;
import java.util.Scanner;

public class ClientApp {

    private static final String BASE_URL = "http://localhost:7000";
    private static final OkHttpClient httpClient = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1: cadastrarCliente(scanner); break;
                    case 2: listarClientes(); break;
                    case 3: atualizarCliente(scanner); break;
                    case 4: deletarCliente(scanner); break;
                    case 5: cadastrarProduto(scanner); break;
                    case 6: listarProdutos(); break;
                    case 7: atualizarProduto(scanner); break;
                    case 8: deletarProduto(scanner); break;
                    case 0:
                        System.out.println("Saindo :)");
                        return;
                    default:
                        System.out.println("Opção invalida, tente novamente.");
                }
            } catch (IOException e) {
                System.out.println("Erro de comunicação com a API: " + e.getMessage());
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- Menu de Cliente ---");
        System.out.println("1. Cadastrar Cliente (POST)");
        System.out.println("2. Listar Clientes (GET)");
        System.out.println("3. Atualizar Cliente (PUT)");
        System.out.println("4. Deletar Cliente (DELETE)");
        System.out.println("----------------------");
        System.out.println("--- Menu de Produto ---");
        System.out.println("5. Cadastrar Produto (POST)");
        System.out.println("6. Listar Produtos (GET)");
        System.out.println("7. Atualizar Produto (PUT)");
        System.out.println("8. Deletar Produto (DELETE)");
        System.out.println("----------------------");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    //---------------------------------------------------------
    // METODOS PARA REQUISICOES HTTP
    //---------------------------------------------------------

    private static void cadastrarCliente(Scanner scanner) throws IOException {
        System.out.print("Nome do cliente: "); String nome = scanner.nextLine();
        System.out.print("Email do cliente: "); String email = scanner.nextLine();
        String json = "{\"nome\":\"" + nome + "\", \"email\":\"" + email + "\"}";
        Request request = new Request.Builder().url(BASE_URL + "/clientes").post(RequestBody.create(json, JSON)).build();
        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println("Status: " + response.code() + " | Resposta: " + response.body().string());
        }
    }

    private static void listarClientes() throws IOException {
        Request request = new Request.Builder().url(BASE_URL + "/clientes").build();
        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println("Status: " + response.code());
            System.out.println("Resposta:\n" + response.body().string());
        }
    }

    private static void atualizarCliente(Scanner scanner) throws IOException {
        System.out.print("ID do cliente a atualizar: "); int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Novo nome: "); String nome = scanner.nextLine();
        System.out.print("Novo email: "); String email = scanner.nextLine();
        String json = "{\"id\":" + id + ", \"nome\":\"" + nome + "\", \"email\":\"" + email + "\"}";
        Request request = new Request.Builder().url(BASE_URL + "/clientes/" + id).put(RequestBody.create(json, JSON)).build();
        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println("Status: " + response.code() + " | Resposta:\n" + response.body().string());
        }
    }

    private static void deletarCliente(Scanner scanner) throws IOException {
        System.out.print("ID do cliente a deletar: "); int id = scanner.nextInt();
        Request request = new Request.Builder().url(BASE_URL + "/clientes/" + id).delete().build();
        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println("Status: " + response.code());
        }
    }

    private static void cadastrarProduto(Scanner scanner) throws IOException {
        System.out.print("Nome do produto: "); String nome = scanner.nextLine();
        System.out.print("Preco do produto: "); double preco = scanner.nextDouble();
        String json = "{\"nome\":\"" + nome + "\", \"preco\":" + preco + "}";
        Request request = new Request.Builder().url(BASE_URL + "/produtos").post(RequestBody.create(json, JSON)).build();
        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println("Status: " + response.code() + " | Resposta: " + response.body().string());
        }
    }

    private static void listarProdutos() throws IOException {
        Request request = new Request.Builder().url(BASE_URL + "/produtos").build();
        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println("Status: " + response.code());
            System.out.println("Resposta:\n" + response.body().string());
        }
    }

    private static void atualizarProduto(Scanner scanner) throws IOException {
        System.out.print("ID do produto a atualizar: "); int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Novo nome: "); String nome = scanner.nextLine();
        System.out.print("Novo preco: "); double preco = scanner.nextDouble();
        String json = "{\"id\":" + id + ", \"nome\":\"" + nome + "\", \"preco\":" + preco + "}";
        Request request = new Request.Builder().url(BASE_URL + "/produtos/" + id).put(RequestBody.create(json, JSON)).build();
        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println("Status: " + response.code() + " | Resposta:\n" + response.body().string());
        }
    }

    private static void deletarProduto(Scanner scanner) throws IOException {
        System.out.print("ID do produto a deletar: "); int id = scanner.nextInt();
        Request request = new Request.Builder().url(BASE_URL + "/produtos/" + id).delete().build();
        try (Response response = httpClient.newCall(request).execute()) {
            System.out.println("Status: " + response.code());
        }
    }
}
