package co.unicauca.p2pchat.dominio.kwic;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Clase Sorter
 * 
 * Clase encargada de ordenar una lista de objetos Sentence en base a la palabra
 * que contiene cada objeto. Utiliza un comparador de collator para hacer la
 * comparación.
 */
public class Sorter {

    public ArrayList<Sentence> listaSentence;

    /**
     * Constructor de la clase que inicializa el atributo listaSentence con el
     * valor del parámetro que se le pasa al constructor.
     *
     * @param listaSentence lista de objetos Sentence a ordenar
     */
    public Sorter(ArrayList<Sentence> listaSentence) {
        this.listaSentence = listaSentence;
    }

    /**
     * Método que ordena la lista de objetos Sentence en base a la palabra que
     * contiene cada objeto. Utiliza un comparador de collator para hacer la
     * comparación.
     */
    public void listaOrdenada() {
        Collator comparador = Collator.getInstance(new Locale("es"));
        comparador.setStrength(Collator.TERTIARY);
        listaSentence.sort((o1, o2)
                -> comparador.compare(o1.getPalabra(),
                        o2.getPalabra()));

    }
}
