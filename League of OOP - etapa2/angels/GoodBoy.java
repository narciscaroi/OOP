package angels;

import common.Constants;
import input.Angels;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public class GoodBoy extends Angels {
        public GoodBoy(final String type, final int linePosition, final int columnPosition,
                       final String whatDoes) {
                super(type, linePosition, columnPosition, whatDoes);
        }

        /**
         * GoodBoy "accepta" sa aplice modificarile pe rogue.
         * Acesta ii modifica atat viata eroului verificand daca a depasit
         * maximul pe care il poate avea, cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param rogue
         */
        public void accept(final Rogue rogue) {
                if (rogue.getDead() == 1) {
                        return;
                }
                rogue.setHp(rogue.getHp() + Constants.GBHR);
                if (rogue.getHp() > Constants.ROGHP + Constants.ROGHPPERLVL * rogue.getLevel()) {
                        rogue.setHp(Constants.ROGHP + Constants.ROGHPPERLVL * rogue.getLevel());
                }
                rogue.incBonusProc(Constants.GBDR);
                rogue.setWasHelpedByAngel(1);
        }

        /**
         * GoodBoy "accepta" sa aplice modificarile pe knight.
         * Acesta ii modifica atat viata eroului verificand daca a depasit
         * maximul pe care il poate avea, cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param knight
         */
        public void accept(final Knight knight) {
                if (knight.getDead() == 1) {
                        return;
                }
                knight.setHp(knight.getHp() + Constants.GBHK);
                if (knight.getHp() > Constants.KNIGHTHP
                                + Constants.KNIGHTHPPERLVL * knight.getLevel()) {
                        knight.setHp(Constants.KNIGHTHP
                                + Constants.KNIGHTHPPERLVL * knight.getLevel());
                }
                knight.incBonusProc(Constants.GBDK);
                knight.setWasHelpedByAngel(1);
        }

        /**
         * GoodBoy "accepta" sa aplice modificarile pe pyromancer.
         * Acesta ii modifica atat viata eroului verificand daca a depasit
         * maximul pe care il poate avea, cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param pyromancer
         */
        public void accept(final Pyromancer pyromancer) {
                if (pyromancer.getDead() == 1) {
                        return;
                }
                pyromancer.setHp(pyromancer.getHp() + Constants.GBHP);
                if (pyromancer.getHp() > Constants.PYROHP
                                + Constants.PYROHPPERLVL * pyromancer.getLevel()) {
                        pyromancer.setHp(Constants.PYROHP
                                + Constants.PYROHPPERLVL * pyromancer.getLevel());
                }
                pyromancer.incBonusProc(Constants.GBDP);
                pyromancer.setWasHelpedByAngel(1);
        }

        /**
         * GoodBoy "accepta" sa aplice modificarile pe wizard.
         * Acesta ii modifica atat viata eroului verificand daca a depasit
         * maximul pe care il poate avea, cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param wizard
         */
        public void accept(final Wizard wizard) {
                if (wizard.getDead() == 1) {
                        return;
                }
                wizard.setHp(wizard.getHp() + Constants.GBHW);
                if (wizard.getHp() > Constants.WIZHP + Constants.WIZHPPERLVL * wizard.getLevel()) {
                        wizard.setHp(Constants.WIZHP + Constants.WIZHPPERLVL * wizard.getLevel());
                }
                wizard.incBonusProc(Constants.GBDW);
                wizard.setWasHelpedByAngel(1);
        }
}
