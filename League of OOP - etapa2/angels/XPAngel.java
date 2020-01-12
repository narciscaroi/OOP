package angels;

import common.Constants;
import input.Angels;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public class XPAngel extends Angels {
        public XPAngel(final String type, final int linePosition, final int columnPosition,
                       final String whatDoes) {
                super(type, linePosition, columnPosition, whatDoes);
        }

        /**
         * XPAngel "accepta" sa aplice modificarile pe rogue.
         * Acesta ii adauga eroului o anumita experienta, apoi
         * verifica daca acesta a depasit pragul pentru a primi
         * level up.
         * @param rogue
         */
        public void accept(final Rogue rogue) {
                rogue.setXp(rogue.getXp() + Constants.XPAR);
                rogue.setWasHelpedByAngel(1);
                rogue.checkExp();
        }

        /**
         * XPAngel "accepta" sa aplice modificarile pe knight.
         * Acesta ii adauga eroului o anumita experienta, apoi
         * verifica daca acesta a depasit pragul pentru a primi
         * level up.
         * @param knight
         */
        public void accept(final Knight knight) {
                knight.setXp(knight.getXp() + Constants.XPAK);
                knight.setWasHelpedByAngel(1);
                knight.checkExp();
        }

        /**
         * XPAngel "accepta" sa aplice modificarile pe pyromancer.
         * Acesta ii adauga eroului o anumita experienta, apoi
         * verifica daca acesta a depasit pragul pentru a primi
         * level up.
         * @param pyromancer
         */
        public void accept(final Pyromancer pyromancer) {
                pyromancer.setXp(pyromancer.getXp() + Constants.XPAP);
                pyromancer.setWasHelpedByAngel(1);
                pyromancer.checkExp();
        }

        /**
         * XPAngel "accepta" sa aplice modificarile pe wizard.
         * Acesta ii adauga eroului o anumita experienta, apoi
         * verifica daca acesta a depasit pragul pentru a primi
         * level up.
         * @param wizard
         */
        public void accept(final Wizard wizard) {
                wizard.setXp(wizard.getXp() + Constants.XPAW);
                wizard.setWasHelpedByAngel(1);
                wizard.checkExp();
        }
}
