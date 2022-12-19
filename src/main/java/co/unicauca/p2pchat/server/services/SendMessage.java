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
 * Clase SendMessage
 *
 * Esta clase se encarga de gestionar la comunicación entre el cliente y el
 * servidor. Utiliza un objeto BufferedReader para leer los mensajes enviados
 * por el cliente y un objeto ServerThread para enviar los mensajes al resto de
 * los clientes conectados al servidor.
 *
 * El método communicate() se encarga de ejecutar un bucle infinito que espera
 * por mensajes del cliente. Si el mensaje es "e", se cierra la aplicación. Si
 * el mensaje es "c", se actualiza la conexión con el peer especificado. En
 * cualquier otro caso, se envía el mensaje al resto de los clientes a través
 * del objeto ServerThread.
 *
 */
public class SendMessage {

    /**
     * Objeto BufferedReader para leer los mensajes del cliente
     */
    private final BufferedReader bufferedReader;

    /**
     * Nombre de usuario del cliente
     */
    private final String username;

    /**
     * Objeto ServerThread para enviar los mensajes al resto de los clientes
     */
    private final ServerThread serverThread;

    /**
     * Constructor de la clase
     *
     * @param bufferedReader Objeto BufferedReader para leer los mensajes del
     * cliente
     * @param username Nombre de usuario del cliente
     * @param serverThread Objeto ServerThread para enviar los mensajes al resto
     * de los clientes
     */
    public SendMessage(BufferedReader bufferedReader, String username, ServerThread serverThread) {
        this.bufferedReader = bufferedReader;
        this.username = username;
        this.serverThread = serverThread;
    }

    /**
     * Método que gestiona la comunicación entre el cliente y el servidor
     *
     * @throws IOException Si ocurre un error al leer el mensaje del cliente
     * @throws Exception Si ocurre cualquier otro tipo de error
     */
    public void communicate() throws IOException, Exception {

        try {
            System.out.println("Ahora puede comunicarse (e para salir, c para cambiar):");
            while (true) {
                String message = bufferedReader.readLine();
                switch (message) {
                    case "e" -> {
                        System.exit(0);
                    }
                    case "c" ->
                        new Connection(bufferedReader, username, serverThread).updateListenToPeer();
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
