package net.xaviersala;

/**
 * Plantilla base per fer un programa fent servir les llibreries ACM.
 *
 */
public class App {

    private static final int MIDACAMI = 20;

    private static final String[] noms = { "Sigfrid", "Tallaferro", "Sapastre", "Ferran", "Gausfred", "Guiu", "Marimon",
            "Nofre" };

    public static void main(String[] args) {

        // Crear el taulell
        Cami cami = new Cami(MIDACAMI);

        System.out.println(cami);

        // Afegir cavallers
        for (int i = 0; i < noms.length; i++) {
            cami.afegirCavaller(new Cavaller(noms[i]));
        }

        // Fer la cursa
        Cavaller princep = cami.juga();

        if (princep == null) {
            System.out.println("El regne s'ha quedat sense prínceps");
        } else {
            System.out.println("************************************");
            System.out.println("El " + princep + " és el nou príncep");
            System.out.println("************************************");
        }
    }

}
