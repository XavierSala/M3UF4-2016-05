package net.xaviersala;

public class PosicioCavaller {

    Cavaller cavaller;
    int posicio;


    /**
     * @param cavaller
     * @param posicio
     */
    public PosicioCavaller(Cavaller cavaller, int posicio) {
        super();
        this.cavaller = cavaller;
        this.posicio = posicio;
    }
    /**
     * @return the cavaller
     */
    public Cavaller getCavaller() {
        return cavaller;
    }
    /**
     * @param cavaller the cavaller to set
     */
    public void setCavaller(Cavaller cavaller) {
        this.cavaller = cavaller;
    }
    /**
     * @return the posicio
     */
    public int getPosicio() {
        return posicio;
    }
    /**
     * @param posicio the posicio to set
     */
    public void setPosicio(int posicio) {
        this.posicio = posicio;
    }



}
