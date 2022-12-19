package co.unicauca.p2pchat.dominio.kwic;

import java.util.ArrayList;

/**
 *
 * @author Mike
 */
public class KWIC {

    public ArrayList<Sentence> listaSentence = new ArrayList<>();

    public KWIC() {
    }

    public void agregarSentencia(String frase) {
        Sentence sentencia = new Sentence(frase);
        listaSentence.add(sentencia);
        System.out.println("\nLlego aqui " + frase);
        for (int i = 1; i < sentencia.getPalabra().split(" ").length; i++) {
            int cant = listaSentence.size();
            listaSentence.add(sentencia.shift(listaSentence.get(cant - 1).getPalabra()));
        }
    }

    public void mostrarLista() {
        System.out.println("\n\nLista desordenada:");
        for (int i = 0; i < listaSentence.size(); i++) {
            System.out.println("Dato " + (i + 1) + ": " + listaSentence.get(i).getPalabra());
        }
    }

    public void mostrarListaOrdenada(String nombre) {
        Sorter sort = new Sorter(listaSentence);
        sort.listaOrdenada();
        ArrayList<Sentence> listaSentence1 = sort.listaSentence;
        System.out.println("\n\nLista ordenada:");
        for (int i = 0; i < listaSentence1.size(); i++) {
            System.out.println(nombre + " " + (i + 1) + ": " + listaSentence1.get(i).getPalabra());
        }
    }
}
