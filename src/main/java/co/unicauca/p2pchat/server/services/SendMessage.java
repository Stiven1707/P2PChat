/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.p2pchat.server.services;

import co.unicauca.p2pchat.server.services.Connection;
import co.unicauca.p2pchat.server.peerthreads.ServerThread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import javax.json.Json;

/**
 *
 * @author Mike
 */
public class SendMessage {

    private final BufferedReader bufferedReader;
    private final String username;
    private final ServerThread serverThread;

    public SendMessage(BufferedReader bufferedReader, String username, ServerThread serverThread) {
        this.bufferedReader = bufferedReader;
        this.username = username;
        this.serverThread = serverThread;
    }

    public void communicate() throws IOException, Exception {

        try {
            System.out.println("Ahora puede comunicarse (e para salir, c para cambiar):");
            while (true) {
                String message = bufferedReader.readLine();
                switch (message) {
                    case "e" -> {
                        System.exit(0);
                    }
                    case "c" -> new Connection(bufferedReader, username, serverThread).updateListenToPeer();
                    default -> {
                        StringWriter stringWriter = new StringWriter();
                        Json.createWriter(stringWriter).writeObject(Json.createObjectBuilder()
                                .add("username", username)
                                .add("message", message)
                                .build());
                        serverThread.sendMessage(stringWriter.toString());
                    }
                }
            }
        } catch (Exception e) {
        }

    }
}
