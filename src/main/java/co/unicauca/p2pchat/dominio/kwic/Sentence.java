package co.unicauca.p2pchat.dominio.kwic;

/**
 * Clase Sentence
 * 
 * Clase que representa una frase o una palabra. Almacena una palabra y
 * proporciona métodos para desplazar la palabra a diferentes posiciones.
 */
public class Sentence {

    private String palabra;

    /**
     * Constructor de la clase que inicializa el atributo palabra con el valor
     * del parámetro que se le pasa al constructor.
     *
     * @param palabra palabra a almacenar en el objeto Sentence
     */
    public Sentence(String palabra) {
        this.palabra = palabra;
    }

    /**
     * Método que desplaza la palabra almacenada en el objeto Sentence una vez a
     * la derecha y devuelve un nuevo objeto Sentence con la palabra en su nueva
     * posición.
     *
     * @param pal palabra a desplazar
     * @return nuevo objeto Sentence con la palabra desplazada una vez a la
     * derecha
     */
    public Sentence shift(String pal) {
        String[] intercambio = pal.split(" ");
        StringBuilder palabraCambiada = new StringBuilder();
        palabraCambiada.append(intercambio[intercambio.length - 1]);
        for (int i = 0; i < intercambio.length - 1; i++) {
            palabraCambiada.append(" ").append(intercambio[i]);
        }
        return new Sentence(palabraCambiada.toString());
    }
    
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

}
