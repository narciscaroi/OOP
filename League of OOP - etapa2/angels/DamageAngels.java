package angels;

import common.Constants;
import input.Angels;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public class DamageAngels extends Angels {

        public DamageAngels(final String type, final int linePosition, final int columnPosition,
                            final String whatDoes) {
                super(type, linePosition, columnPosition, whatDoes);
        }

        /**
         * DamageAngel "accepta" sa aplice modificarile pe rogue.
         * Acesta ii modifica modificatorii de damage si seteaza
         * ca jucatorul a fost ajutat de un inger.
         * @param rogue
         */
        public void accept(final Rogue rogue) {
                if (rogue.getDead() == 1) {
                        return;
                }
                rogue.incBonusProc(Constants.DAR);
                rogue.setWasHelpedByAngel(1);

        }

        /**
         * DamageAngel "accepta" sa aplice modificarile pe knight.
         *Acesta ii modifica modificatorii de damage si seteaza
         *ca jucatorul a fost ajutat de un inger.
         * @param knight
         */
        public void accept(final Knight knight) {
                if (knight.getDead() == 1) {
                        return;
                }
                knight.incBonusProc(Constants.DAK);
                knight.setWasHelpedByAngel(1);
        }

        /**
         * DamageAngel "accepta" sa aplice modificarile pe pyromancer.
         * Acesta ii modifica modificatorii de damage si seteaza
         * ca jucatorul a fost ajutat de un inger.
         * @param pyromancer
         */
        public void accept(final Pyromancer pyromancer) {
                if (pyromancer.getDead() == 1) {
                        return;
                }
                pyromancer.incBonusProc(Constants.DAP);
                pyromancer.setWasHelpedByAngel(1);
        }

        /**
         * DamageAngel "accepta" sa aplice modificarile pe wizard.
         * Acesta ii modifica modificatorii de damage si seteaza
         * ca jucatorul a fost ajutat de un inger.
         * @param wizard
         */
        public void accept(final Wizard wizard) {
                if (wizard.getDead() == 1) {
                        return;
                }
                wizard.incBonusProc(Constants.DAW);
                wizard.setWasHelpedByAngel(1);
        }
}
