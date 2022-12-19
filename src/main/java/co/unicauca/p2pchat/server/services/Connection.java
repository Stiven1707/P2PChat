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
 *
 * @author Mike
 */
public class Connection {

    private BufferedReader bufferedReader;
    private String username;
    private ServerThread server;

    public Connection(BufferedReader bufferedReader, String username, ServerThread server) {
        this.bufferedReader = bufferedReader;
        this.username = username;
        this.server = server;
    }

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
