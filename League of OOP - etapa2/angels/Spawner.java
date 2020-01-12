package angels;

import common.Constants;
import input.Angels;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public class Spawner extends Angels {
        public Spawner(final String type, final int linePosition, final int columnPosition,
                       final String whatDoes) {
                super(type, linePosition, columnPosition, whatDoes);
        }

        /**
         * Spawner "accepta" sa aplice modificarile pe rogue.
         * Acesta verifica daca eroul este mort, iar daca acesta
         * este mort, "il invie", adica ii reseteaza variabila care
         * indica daca este mort si ii seteaza un anumit hp.
         * @param rogue
         */
        public void accept(final Rogue rogue) {
                if (rogue.getDead() == 1) {
                        rogue.setDead(0);
                        //rogue.setXp(0);
                        rogue.setHp(Constants.SHR);
                        rogue.setWasHelpedByAngel(1);
                        rogue.setWasBroughtToLife(1);
                }
        }

        /**
         * Spawner "accepta" sa aplice modificarile pe knight.
         * Acesta verifica daca eroul este mort, iar daca acesta
         * este mort, "il invie", adica ii reseteaza variabila care
         * indica daca este mort si ii seteaza un anumit hp.
         * @param knight
         */
        public void accept(final Knight knight) {
                if (knight.getDead() == 1) {
                        knight.setDead(0);
                        //knight.setXp(0);
                        knight.setHp(Constants.SHK);
                        knight.setWasHelpedByAngel(1);
                        knight.setWasBroughtToLife(1);
                }
        }

        /**
         * Spawner "accepta" sa aplice modificarile pe pyromancer.
         * Acesta verifica daca eroul este mort, iar daca acesta
         * este mort, "il invie", adica ii reseteaza variabila care
         * indica daca este mort si ii seteaza un anumit hp.
         * @param pyromancer
         */
        public void accept(final Pyromancer pyromancer) {
                if (pyromancer.getDead() == 1) {
                        pyromancer.setDead(0);
                       // pyromancer.setXp(0);
                        pyromancer.setHp(Constants.SHP);
                        pyromancer.setWasHelpedByAngel(1);
                        pyromancer.setWasBroughtToLife(1);
                }
        }

        /**
         * Spawner "accepta" sa aplice modificarile pe wizard.
         * Acesta verifica daca eroul este mort, iar daca acesta
         * este mort, "il invie", adica ii reseteaza variabila care
         * indica daca este mort si ii seteaza un anumit hp.
         * @param wizard
         */
        public void accept(final Wizard wizard) {
                if (wizard.getDead() == 1) {
                        wizard.setDead(0);
                       // wizard.setXp(0);
                        wizard.setHp(Constants.SHW);
                        wizard.setWasHelpedByAngel(1);
                        wizard.setWasBroughtToLife(1);
                }
        }
}
