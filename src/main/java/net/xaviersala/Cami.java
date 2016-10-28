package net.xaviersala;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Cami {

    private static final int CARES_DAU = 4;
    private static final int MATAR_BANDIT = 75;
    private static final int CASELLES_A_RECULAR = 5;

    static final Logger LOG = Logger.getLogger("Cami");

    List<Casella> caselles;

    List<PosicioCavaller> cavallers;

    /**
     * Crea el camí a partir del número de caselles que hi ha.
     */
    public Cami(int numeroCaselles) {
        caselles = new ArrayList<>();
        cavallers = new ArrayList<>();

        for (int i = 0; i < numeroCaselles; i++) {
            caselles.add(new Casella());
        }

    }

    /**
     * Afegeix un cavaller al tauler.
     *
     * @param cavaller
     *            Cavaller a afegir
     */
    public void afegirCavaller(Cavaller cavaller) {
        cavallers.add(new PosicioCavaller(cavaller, 0));
    }

    /**
     * Els cavallers avancen fins que es moren o arriben al castell final.
     *
     * @return cavaller guanyador
     */
    public Cavaller juga() {
        Cavaller princep = null;

        Random dau = new Random();

        int quantsEsMouen = cavallers.size() + 1;

        while (quantsEsMouen != 0 && princep == null) {

            quantsEsMouen = 0;

            for (PosicioCavaller cavallerpos : cavallers) {

                // Si ha hi ha un príncep o bé el cavaller està mort no val la
                // pena
                // seguir endavant.

                if (!haAcabat(princep, cavallerpos.getCavaller())) {
                    // Comptem quants cavallers es mouen
                    quantsEsMouen++;

                    // Tira el dau
                    int resultat = dau.nextInt(CARES_DAU) + 1;

                    // On sóc
                    int posicioActual = cavallerpos.getPosicio();
                    Cavaller cavallerActual = cavallerpos.getCavaller();
                    Casella casellaActual = caselles.get(posicioActual);

                    // Treure el cavaller de la posició actual
                    casellaActual.setCavaller(null);

                    // On vull anar
                    int novaPosicio = posicioActual + resultat;

                    // He arribat? Doncs sóc el príncep!
                    if (novaPosicio > caselles.size() - 1) {
                        princep = cavallerActual;

                    } else {

                        Casella casellaNova = caselles.get(novaPosicio);

                        LOG.info("Casella " + novaPosicio + ": " + cavallerActual + " > " + casellaNova.getHiHa());

                        if (casellaNova.hiHaCavaller()) {

                            // Baralla entre cavallers : 50 %
                            if (dau.nextBoolean()) {
                                // He guanyat
                                LOG.info(cavallerActual + " mata a " + casellaNova.getCavaller());
                                casellaNova.getCavaller().setEstaMort(true);
                                posarCavaller(cavallerpos, novaPosicio);

                            } else {
                                // m'he mort
                                LOG.info(casellaNova.getCavaller() + " mata a " + cavallerActual);
                                cavallerActual.setEstaMort(true);
                            }
                        }

                        switch (casellaNova.getHiHa()) {
                        case RES:
                            posarCavaller(cavallerpos, novaPosicio);
                            break;
                        case FERA:
                            int recula = novaPosicio - CASELLES_A_RECULAR;
                            if (recula - CASELLES_A_RECULAR < 0) {
                                recula = 0;
                            }
                            LOG.info("La fera fa recular el " + cavallerActual + " a la posició " + recula);
                            cavallerpos.setPosicio(recula);
                            Casella c = caselles.get(recula);
                            c.setCavaller(cavallerActual);
                            break;
                        // Casella del bandit
                        case BANDIT:
                            int r = dau.nextInt(100);
                            if (r < MATAR_BANDIT) {
                                LOG.info(cavallerActual + " sobreviu al bandit");
                                posarCavaller(cavallerpos, novaPosicio);
                            } else {
                                LOG.info(cavallerActual + " mort per un bandit");
                                mataCavaller(cavallerpos, cavallerActual);
                            }
                            break;
                        case TRAMPA:
                            LOG.info(cavallerActual + " mort en una trampa");
                            mataCavaller(cavallerpos, cavallerActual);
                        }
                    }
                }
            }

        }

        return princep;
    }

    /**
     * Comprova si ja tenim nou príncep o bé el cavaller actual està mort.
     *
     * @param princep
     *            Si té valor és que ja tenim príncep
     * @param cavallerpos
     * @return
     */
    private boolean haAcabat(Cavaller princep, Cavaller cavaller) {
        return cavaller.isEstaMort() || princep != null;
    }

    /**
     * Posiciona el cavaller en la nova posició
     *
     * @param cavallerpos
     * @param cavallerActual
     * @param novaPosicio
     * @param casellaNova
     * @return
     */
    private void posarCavaller(PosicioCavaller cavallerpos, int novaPosicio) {
        cavallerpos.setPosicio(novaPosicio);
        caselles.get(novaPosicio).setCavaller(cavallerpos.getCavaller());
    }

    private void mataCavaller(PosicioCavaller cavallerpos, Cavaller cavallerActual) {
        cavallerpos.setPosicio(-1);
        cavallerActual.setEstaMort(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return caselles.toString();
    }

}
