package co.unicauca.p2pchat.server.peerthreads;

import co.unicauca.p2pchat.dominio.kwic.KWIC;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;

public class PeerThread extends Thread {

    private KWIC sen = new KWIC();
    private BufferedReader bufferedReader;

    public PeerThread(Socket socket) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

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
