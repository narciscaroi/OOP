package angels;

import common.Constants;
import input.Angels;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public class Dracula extends Angels {

        public Dracula(final String type, final int linePosition, final int columnPosition,
                       final String whatDoes) {
                super(type, linePosition, columnPosition, whatDoes);
        }

        /**
         * Dracula "accepta" sa aplice modificarile pe rogue.
         * Acesta ii modifica atat viata eroului cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param rogue
         */
        public void accept(final Rogue rogue) {
                if (rogue.getDead() == 1) {
                        return;
                }
                rogue.setHp(rogue.getHp() - Constants.DHR);
                rogue.checkIsDead();
                rogue.decBonusProc(Constants.DDR);
                rogue.setWasHelpedByAngel(1);
        }

        /**
         * Dracula "accepta" sa aplice modificarile pe knight.
         * Acesta ii modifica atat viata eroului cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param knight
         */
        public void accept(final Knight knight) {
                if (knight.getDead() == 1) {
                        return;
                }
                knight.setHp(knight.getHp() - Constants.DHK);
                knight.checkIsDead();
                knight.decBonusProc(Constants.DDK);
                knight.setWasHelpedByAngel(1);
        }

        /**
         * Dracula "accepta" sa aplice modificarile pe pyromancer.
         * Acesta ii modifica atat viata eroului cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param pyromancer
         */
        public void accept(final Pyromancer pyromancer) {
                if (pyromancer.getDead() == 1) {
                        return;
                }
                pyromancer.setHp(pyromancer.getHp() - Constants.DHP);
                pyromancer.checkIsDead();

                pyromancer.decBonusProc(Constants.DDP);
                pyromancer.setWasHelpedByAngel(1);
        }

        /**
         * Dracula "accepta" sa aplice modificarile pe wizard.
         * Acesta ii modifica atat viata eroului cat si modificatorii
         * de damage si seteaza ca jucatorul a fost ajutat de un inger.
         * @param wizard
         */
        public void accept(final Wizard wizard) {
                if (wizard.getDead() == 1) {
                        return;
                }
                wizard.setHp(wizard.getHp() - Constants.DHW);
                wizard.checkIsDead();
                wizard.decBonusProc(Constants.DDW);
                wizard.setWasHelpedByAngel(1);
        }
}
