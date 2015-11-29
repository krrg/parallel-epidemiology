package io.github.krrg;

/**
 * Created by krr428 on 11/28/15.
 */
public class IndividualFactory {

    private static IndividualFactory instance = null;

    public static IndividualFactory getInstance() {
        if (instance == null) {
            instance = new IndividualFactory();
        }
        return instance;
    }


    public Individual createIndividual() {
        return null;
    }


}
