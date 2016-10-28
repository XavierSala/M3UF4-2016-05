package net.xaviersala;

import java.util.Random;

public class Casella {
    private static final int PROV_BANDIT = 70;
    private static final int PROV_FERA = 80;
    private static final int PROV_TRAMPA = 90;
    Cavaller cavaller;
    ObjectesCasella hiHa;

    /**
     *
     */
    public Casella() {

        Random tria = new Random();

        cavaller = null;

        hiHa = ObjectesCasella.RES;

        int valor = tria.nextInt(100);
        if (valor > PROV_TRAMPA) {
            hiHa = ObjectesCasella.TRAMPA;
        } else if (valor > PROV_FERA) {
            hiHa = ObjectesCasella.FERA;
        } else if (valor > PROV_BANDIT) {
            hiHa = ObjectesCasella.BANDIT;
        }
    }

    boolean hiHaCavaller() {
        return cavaller != null;
    }

    public void setCavaller(Cavaller cavaller) {
        this.cavaller = cavaller;
    }

    public Cavaller getCavaller() {
        return cavaller;
    }

    /**
     * @return the hiHa
     */
    public ObjectesCasella getHiHa() {
        return hiHa;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "[" + cavaller + "," + hiHa + "]";
    }

}
