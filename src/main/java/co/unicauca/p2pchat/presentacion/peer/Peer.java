package co.unicauca.p2pchat.presentacion.peer;

import co.unicauca.p2pchat.server.services.Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import co.unicauca.p2pchat.server.peerthreads.ServerThread;

public class Peer {

    public static void main(String[] args) throws IOException, Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Digite nombre y # de puerto: ");
        String[] setupValues = bufferedReader.readLine().split(" ");
        ServerThread serverThread = new ServerThread(setupValues[1]);

        serverThread.start();
        new Connection(bufferedReader, setupValues[0], serverThread).updateListenToPeer();
    }
}
