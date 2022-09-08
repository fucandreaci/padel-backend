package it.polimi.padel.model.parsables;/*
 * File: JsonParsableFactory
 * Project: Padel Backend
 * File Created: 08/09/22 - 15:17
 * Author: Andrea Fucci (fucciandrea01@gmail.com)
 * Copyright © 2022-2022 Andrea Fucci
 */

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


}
