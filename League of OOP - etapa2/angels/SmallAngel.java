package angels;

import common.Constants;
import input.Angels;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public class SmallAngel extends Angels {
        public SmallAngel(final String type, final int linePosition, final int columnPosition,
                          final String whatDoes) {
                super(type, linePosition, columnPosition, whatDoes);
        }

        /**
         * SmallAngel "accepta" sa aplice modificarile pe rogue.
         * Acesta ii modifica atat viata eroului verificand daca a
         * depasit maximul pe care poate a il aiba, cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param rogue
         */
        public void accept(final Rogue rogue) {
                if (rogue.getDead() == 1) {
                        return;
                }
                rogue.setHp(rogue.getHp() + Constants.SAHR);
                if (rogue.getHp() > Constants.ROGHP + Constants.ROGHPPERLVL * rogue.getLevel()) {
                        rogue.setHp(Constants.ROGHP + Constants.ROGHPPERLVL * rogue.getLevel());
                }
                rogue.incBonusProc(Constants.SADR);
                rogue.setWasHelpedByAngel(1);
        }

        /**
         * SmallAngel "accepta" sa aplice modificarile pe knight.
         * Acesta ii modifica atat viata eroului verificand daca a
         * depasit maximul pe care poate a il aiba, cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param knight
         */
        public void accept(final Knight knight) {
                if (knight.getDead() == 1) {
                        return;
                }
                knight.setHp(knight.getHp() + Constants.SAHK);
                if (knight.getHp() > Constants.KNIGHTHP
                                + Constants.KNIGHTHPPERLVL * knight.getLevel()) {
                        knight.setHp(Constants.KNIGHTHP
                                + Constants.KNIGHTHPPERLVL * knight.getLevel());
                }
                knight.incBonusProc(Constants.SADK);
                knight.setWasHelpedByAngel(1);
        }

        /**
         * SmallAngel "accepta" sa aplice modificarile pe pyromancer.
         * Acesta ii modifica atat viata eroului verificand daca a
         * depasit maximul pe care poate a il aiba, cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param pyromancer
         */
        public void accept(final Pyromancer pyromancer) {
                if (pyromancer.getDead() == 1) {
                        return;
                }
                pyromancer.setHp(pyromancer.getHp() + Constants.SAHP);
                if (pyromancer.getHp() > Constants.PYROHP
                        + Constants.PYROHPPERLVL * pyromancer.getLevel()) {
                        pyromancer.setHp(Constants.PYROHP
                                + Constants.PYROHPPERLVL * pyromancer.getLevel());
                }
                pyromancer.incBonusProc(Constants.SADP);
                pyromancer.setWasHelpedByAngel(1);
        }

        /**
         * SmallAngel "accepta" sa aplice modificarile pe wizard.
         * Acesta ii modifica atat viata eroului verificand daca a
         * depasit maximul pe care poate a il aiba, cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param wizard
         */
        public void accept(final Wizard wizard) {
                if (wizard.getDead() == 1) {
                        return;
                }
                wizard.setHp(wizard.getHp() + Constants.SAHW);
                if (wizard.getHp() > Constants.WIZHP + Constants.WIZHPPERLVL * wizard.getLevel()) {
                        wizard.setHp(Constants.WIZHP + Constants.WIZHPPERLVL * wizard.getLevel());
                }
                wizard.incBonusProc(Constants.SADW);
                wizard.setWasHelpedByAngel(1);
        }
}
