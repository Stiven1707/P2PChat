package co.unicauca.p2pchat.server.peerthreads;

import co.unicauca.p2pchat.dominio.kwic.KWIC;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
/**
 * Clase PeerThread
 * 
 * Clase que representa un hilo de ejecución en el servidor de chat p2p.
 * Se encarga de leer mensajes enviados por los clientes y procesarlos
 * para agregarlos a la lista de frases y mostrarlas ordenadas.
 */
public class PeerThread extends Thread {


    // Instancia de la clase KWIC para ordenar las frases
    private KWIC sen = new KWIC();
    
    // Buffer para leer mensajes enviados por los clientes
    private BufferedReader bufferedReader;
    /**
     * Constructor que inicializa el buffer para leer mensajes.
     * @param socket Socket del cliente conectado al servidor.
     * @throws IOException Si hay un error al crear el buffer.
     */
    public PeerThread(Socket socket) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /**
     * Método principal del hilo. Se encarga de leer mensajes enviados por los clientes,
     * procesarlos y mostrar la lista de frases ordenada.
     */
    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            try {
                JsonObject jsonObject = Json.createReader(bufferedReader).readObject();
                if (jsonObject.containsKey("username")) {
                    sen.agregarSentencia(jsonObject.getString("message"));
                    sen.mostrarListaOrdenada("[" + jsonObject.getString("username") + "]: ");
                }
            } catch (Exception e) {
                flag = false;
                interrupt();
            }

        }

    }
}
