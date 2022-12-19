package co.unicauca.p2pchat.presentacion.peer;

import co.unicauca.p2pchat.server.services.Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import co.unicauca.p2pchat.server.peerthreads.ServerThread;
/**
 * Clase Peer
 * 
 * Esta clase se encarga de iniciar el servidor de chat y esperar por conexiones
 * entrantes. Solicita al usuario que ingrese un nombre de usuario y un puerto
 * en el que se esperarán por conexiones entrantes. Luego, inicia el servidor
 * y espera por conexiones a través de una instancia de la clase Connection.
 * 
 */
public class Peer {
    /**
     * Método principal de la clase
     * 
     * @param args Argumentos de la línea de comandos (no se utilizan en este caso)
     * @throws IOException Si ocurre un error al leer el nombre de usuario y el puerto del usuario
     * @throws Exception Si ocurre cualquier otro tipo de error
     */
    public static void main(String[] args) throws IOException, Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Digite nombre y # de puerto: ");
        String[] setupValues = bufferedReader.readLine().split(" ");
        ServerThread serverThread = new ServerThread(setupValues[1]);

        serverThread.start();
        new Connection(bufferedReader, setupValues[0], serverThread).updateListenToPeer();
    }
}
