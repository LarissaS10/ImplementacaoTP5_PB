package org.example;
import org.example.app.ClientApp;
import org.example.app.ServerApp;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("client")) {
            ClientApp.start();
        } else {
            ServerApp.start();
        }
    }
}