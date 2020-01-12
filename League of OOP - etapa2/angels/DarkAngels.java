package angels;

import common.Constants;
import input.Angels;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public class DarkAngels extends Angels {
        public DarkAngels(final String type, final int linePosition, final int columnPosition,
                          final String whatDoes) {
                super(type, linePosition, columnPosition, whatDoes);
        }

        /**
         * DarkAngel "accepta" sa aplice modificarile pe rogue.
         * Acesta ii modifica viata eroului si seteaza
         * ca jucatorul a fost ajutat de un inger.
         * @param rogue
         */
        public void accept(final Rogue rogue) {
                if (rogue.getDead() == 1) {
                        return;
                }
                rogue.setHp(rogue.getHp() - Constants.DALR);
                rogue.checkIsDead();
                rogue.setWasHelpedByAngel(1);
        }

        /**
         * DarkAngel "accepta" sa aplice modificarile pe knight.
         * Acesta ii modifica viata eroului si seteaza
         * ca jucatorul a fost ajutat de un inger.
         * @param knight
         */
        public void accept(final Knight knight) {
                if (knight.getDead() == 1) {
                        return;
                }
                knight.setHp(knight.getHp() - Constants.DALK);
                knight.checkIsDead();
                knight.setWasHelpedByAngel(1);
        }

        /**
         * DarkAngel "accepta" sa aplice modificarile pe pyromancer.
         * Acesta ii modifica viata eroului si seteaza
         * ca jucatorul a fost ajutat de un inger.
         * @param pyromancer
         */
        public void accept(final Pyromancer pyromancer) {
                if (pyromancer.getDead() == 1) {
                        return;
                }
                pyromancer.setHp(pyromancer.getHp() - Constants.DALP);
                pyromancer.checkIsDead();
                pyromancer.setWasHelpedByAngel(1);
        }

        /**
         * DarkAngel "accepta" sa aplice modificarile pe wizzard.
         * Acesta ii modifica viata eroului si seteaza
         * ca jucatorul a fost ajutat de un inger.
         * @param wizard
         */
        public void accept(final Wizard wizard) {
                if (wizard.getDead() == 1) {
                        return;
                }
                wizard.setHp(wizard.getHp() - Constants.DALW);
                wizard.checkIsDead();
                wizard.setWasHelpedByAngel(1);
        }
}
