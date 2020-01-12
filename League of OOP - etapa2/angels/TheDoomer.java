package angels;

import input.Angels;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public class TheDoomer  extends Angels {
        public TheDoomer(final String type, final int linePosition, final int columnPosition,
                         final String whatDoes) {
                super(type, linePosition, columnPosition, whatDoes);
        }

        /**
         * TheDoomer "accepta" sa aplice modificarile pe rogue.
         * Acesta "omoara" eroul, adica seteaza cu 1 variabila care
         * indica ca este mort.
         * @param rogue
         */
        public void accept(final Rogue rogue) {
                rogue.setDead(1);
                rogue.setHp(0);
                rogue.setWasHelpedByAngel(1);
        }

        /**
         * TheDoomer "accepta" sa aplice modificarile pe knight.
         * Acesta "omoara" eroul, adica seteaza cu 1 variabila care
         * indica ca este mort.
         * @param knight
         */
        public void accept(final Knight knight) {
                knight.setDead(1);
                knight.setHp(0);
                knight.setWasHelpedByAngel(1);
        }

        /**
         * TheDoomer "accepta" sa aplice modificarile pe pyromancer.
         * Acesta "omoara" eroul, adica seteaza cu 1 variabila care
         * indica ca este mort.
         * @param pyromancer
         */
        public void accept(final Pyromancer pyromancer) {
                pyromancer.setDead(1);
                pyromancer.setHp(0);
                pyromancer.setWasHelpedByAngel(1);
        }

        /**
         * TheDoomer "accepta" sa aplice modificarile pe wizard.
         * Acesta "omoara" eroul, adica seteaza cu 1 variabila care
         * indica ca este mort.
         * @param wizard
         */
        public void accept(final Wizard wizard) {
                wizard.setDead(1);
                wizard.setHp(0);
                wizard.setWasHelpedByAngel(1);
        }
}
