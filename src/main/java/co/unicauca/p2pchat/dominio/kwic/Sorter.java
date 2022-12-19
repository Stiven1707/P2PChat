package co.unicauca.p2pchat.dominio.kwic;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

public class Sorter {
    public ArrayList<Sentence> listaSentence;

    public Sorter(ArrayList<Sentence> listaSentence) {
        this.listaSentence = listaSentence;
    }

    public void listaOrdenada(){
        Collator comparador = Collator.getInstance(new Locale("es"));
                comparador.setStrength(Collator.TERTIARY);
        listaSentence.sort((o1, o2)
                  -> comparador.compare(o1.getPalabra(),
                      o2.getPalabra()));

    }
}
