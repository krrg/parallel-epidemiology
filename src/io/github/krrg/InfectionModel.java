package io.github.krrg;

/**
 * Created by krr428 on 11/28/15.
 */
public interface InfectionModel {

    public boolean beginInfected (Individual individual);
    public boolean isInfectedBy (Individual patient, Individual remote);

}
