package it.polimi.padel.model.parsables;

public class JsonParsableFactory {
    private static JsonParsableFactory jsonParsableFactory;

    private JsonParsableFactory () {}

    /**
     * Singleton pattern
     * Ottengo l'istanza della classe
     *
     * @return
     */
    public static JsonParsableFactory getFactory() {
        if (jsonParsableFactory == null)
            jsonParsableFactory = new JsonParsableFactory();

        return jsonParsableFactory;
    }

    /**
     * Crea un nuovo oggetto {@link OrarioStruttura}
     * @return
     */
    public OrarioStruttura getOrarioStruttura() {
        return new OrarioStruttura();
    }

    /**
     * Crea un nuovo oggetto {@link Regola}
     * @return
     */
    public Regola getRegola() {
        return new Regola();
    }

    /**
     * Crea un nuovo oggetto {@link InfoVarie}
     * @return
     */
    public InfoVarie getInfo() {
        return new InfoVarie();
    }


    /**
     * Crea un nuovo oggetto {@link Contatto}
     * @return
     */
    public Contatto getContatti() {
        return new Contatto();
    }


}
