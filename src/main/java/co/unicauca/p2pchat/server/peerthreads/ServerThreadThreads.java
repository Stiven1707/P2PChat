package co.unicauca.p2pchat.server.peerthreads;

import co.unicauca.p2pchat.server.peerthreads.ServerThread;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clase ServerThreadThreads
 *
 * Clase que representa un hilo de ejecución para el servidor en un sistema de
 * chat peer-to-peer. Almacena un objeto ServerThread y un socket y proporciona
 * métodos para establecer y obtener un objeto PrintWriter y para enviar un
 * mensaje a través del socket.
 */
class ServerThreadThreads extends Thread {

    // Atributo que almacena un objeto ServerThread
    private ServerThread serverThread;
    // Atributo que almacena un socket
    private Socket socket;
    // Atributo que almacena un objeto PrintWriter
    private PrintWriter printWriter;

    /**
     * Constructor de la clase que inicializa los atributos serverThread y
     * socket con los valores de los parámetros que se le pasan al constructor.
     *
     * @param socket socket para la conexión
     * @param serverThread objeto ServerThread
     */
    public ServerThreadThreads(Socket socket, ServerThread serverThread) {
        this.serverThread = serverThread;
        this.socket = socket;
    }

    /**
     * Método que establece el valor del atributo printWriter con el valor del
     * parámetro que se le pasa.
     *
     * @param printWriter objeto PrintWriter
     */
    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }
    /**
     * Método que se ejecuta al iniciar el hilo de ejecución. Crea un buffer de lectura y un objeto
     * PrintWriter a partir del socket y espera a recibir mensajes a través del buffer de lectura.
     * Si se produce una excepción al intentar leer un mensaje, elimina el hilo de la lista de hilos
     * del objeto ServerThread.
     */
    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                serverThread.sendMessage(bufferedReader.readLine());
            }
        } catch (Exception e) {
            serverThread.getServerThreadThreads().remove(this);
        }
    }
}
