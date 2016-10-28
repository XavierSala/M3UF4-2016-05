package net.xaviersala;

public class Cavaller {

    String nom;
    boolean estaMort;

    /**
     * @param nom
     */
    public Cavaller(String nom) {
        super();
        this.nom = nom;
        estaMort = false;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Cavaller " + nom;
    }

    /**
     * @return the estaMort
     */
    public boolean isEstaMort() {
        return estaMort;
    }

    /**
     * @param estaMort the estaMort to set
     */
    public void setEstaMort(boolean estaMort) {
        this.estaMort = estaMort;
    }



}
