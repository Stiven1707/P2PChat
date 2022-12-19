/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.p2pchat.server.services;

import co.unicauca.p2pchat.server.peerthreads.ServerThread;
import co.unicauca.p2pchat.server.peerthreads.PeerThread;
import co.unicauca.p2pchat.server.peerthreads.PeerThread;
import co.unicauca.p2pchat.server.peerthreads.ServerThread;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

/**
 * Clase Connection
 * 
 * Clase que representa una conexión entre un cliente y un servidor en un
 * sistema de chat peer-to-peer. Almacena un buffer de lectura, un nombre de
 * usuario y un objeto ServerThread y proporciona un método para actualizar la
 * escucha de mensajes de otros pares.
 */
public class Connection {

    // Atributo que almacena un buffer de lectura
    private BufferedReader bufferedReader;
    // Atributo que almacena el nombre de usuario
    private String username;
    // Atributo que almacena un objeto ServerThread
    private ServerThread server;

    /**
     * Constructor de la clase que inicializa los atributos bufferedReader,
     * username y server con los valores de los parámetros que se le pasan al
     * constructor.
     *
     * @param bufferedReader buffer de lectura
     * @param username nombre de usuario
     * @param server objeto ServerThread
     */
    public Connection(BufferedReader bufferedReader, String username, ServerThread server) {
        this.bufferedReader = bufferedReader;
        this.username = username;
        this.server = server;
    }
    /**
     * Método que actualiza la escucha de mensajes de otros pares. Lee una lista de direcciones IP:puerto
     * de la consola y crea una conexión con cada una de ellas. Si se ingresa el carácter 's', se omite
     * el proceso de conexión y se pasa a la siguiente etapa de comunicación.
     * @throws Exception
     */
    public void updateListenToPeer() throws Exception {
        System.out.println(">Digite la lista de ipv4:puerto(separados por espacios):");
        System.out.println("Ingrese el puerto para recibir mensajes de sus pares (s para omitir):");
        String input = bufferedReader.readLine();
        String[] inputValues = input.split(" ");

        if (!input.equals("s")) {
            for (int i = 0; i < inputValues.length; i++) {

                String[] address = inputValues[i].split(":");
                Socket socket = null;

                try {
                    socket = new Socket(address[0], Integer.valueOf(address[1]));
                    new PeerThread(socket).start();
                } catch (Exception e) {
                    if (socket != null) {
                        socket.close();
                    } else {
                        System.out.println("Entrada inválida. Saltando al siguiente paso");
                    }
                }
            }
        }
        SendMessage msg = new SendMessage(bufferedReader, username, server);

        msg.communicate();
    }

}
