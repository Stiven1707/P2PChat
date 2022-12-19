package co.unicauca.p2pchat.server.peerthreads;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase ServerThread
 *
 * Esta clase se encarga de crear un hilo de ejecución que espera por conexiones
 * entrantes en el puerto especificado. Cuando se recibe una conexión, se crea
 * una instancia de la clase ServerThreadThreads y se agrega a la lista de hilos
 * de ServerThreadThreads.
 *
 * Además, esta clase tiene un método sendMessage() que permite enviar un
 * mensaje a todos los clientes conectados a través de sus respectivos hilos de
 * ejecución (ServerThreadThreads).
 *
 */
public class ServerThread extends Thread {

    /**
     * Objeto ServerSocket para esperar por conexiones entrantes
     */
    private final ServerSocket serverSocket;

    /**
     * Lista de hilos de ejecución de los clientes conectados
     * (ServerThreadThreads)
     */
    private final Set<ServerThreadThreads> serverThreadThreads = new HashSet<>();

    /**
     * Constructor de la clase
     *
     * @param portNumb Puerto en el que se esperarán por conexiones entrantes
     * @throws IOException Si ocurre un error al crear el objeto ServerSocket
     */
    public ServerThread(String portNumb) throws IOException {
        serverSocket = new ServerSocket(Integer.valueOf(portNumb));
    }

    /**
     * Método que ejecuta el hilo de ejecución del servidor
     */
    @Override
    public void run() {
        try {
            while (true) {
                ServerThreadThreads serverThreadThread = new ServerThreadThreads(serverSocket.accept(), this);
                serverThreadThreads.add(serverThreadThread);
                serverThreadThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Método que ejecuta el hilo de ejecución del servidor
     */
    public void sendMessage(String message) {
        try {
            serverThreadThreads.forEach(t -> t.getPrintWriter().println(message));
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public Set<ServerThreadThreads> getServerThreadThreads() {
        return serverThreadThreads;
    }
}
