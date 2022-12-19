package co.unicauca.p2pchat.dominio.kwic;

public class Sentence {
    private String palabra;

    public Sentence(String palabra) {
        this.palabra = palabra;
    }
    public Sentence shift(String pal){
        String[] intercambio = pal.split(" ");
        StringBuilder palabraCambiada = new StringBuilder();
        palabraCambiada.append(intercambio[intercambio.length-1]);
        for (int i = 0; i < intercambio.length-1; i++) {
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
