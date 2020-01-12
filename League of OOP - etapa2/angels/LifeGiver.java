package angels;

import common.Constants;
import input.Angels;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public class LifeGiver extends Angels {
        public LifeGiver(final String type, final int linePosition, final int columnPosition,
                         final String whatDoes) {
                super(type, linePosition, columnPosition, whatDoes);
        }

        /**
         * LifeGiver "accepta" sa aplice modificarile pe rogue.
         * Acesta ii modifica viata eroului verificand daca a depasit
         * maximul pe care poate sa il aiba si seteaza ca jucatorul
         * a fost ajutat de un inger.
         * @param rogue
         */
        @Override
        public void accept(final Rogue rogue) {
                if (rogue.getDead() == 1) {
                        return;
                }
                rogue.setHp(rogue.getHp() + Constants.LGR);
                if (rogue.getHp() > Constants.ROGHP + Constants.ROGHPPERLVL * rogue.getLevel()) {
                        rogue.setHp(Constants.ROGHP + Constants.ROGHPPERLVL * rogue.getLevel());
                }
                rogue.setWasHelpedByAngel(1);
        }

        /**
         * LifeGiver "accepta" sa aplice modificarile pe knight.
         * Acesta ii modifica viata eroului verificand daca a depasit
         * maximul pe care poate sa il aiba si seteaza ca jucatorul
         * a fost ajutat de un inger.
         * @param knight
         */
        public void accept(final Knight knight) {
                if (knight.getDead() == 1) {
                        return;
                }
                knight.setHp(knight.getHp() + Constants.LGK);
                System.out.println(knight.getLevel());
                if (knight.getHp() > Constants.KNIGHTHP
                                + Constants.KNIGHTHPPERLVL * knight.getLevel()) {
                        knight.setHp(Constants.KNIGHTHP
                                + Constants.KNIGHTHPPERLVL * knight.getLevel());
                }
                knight.setWasHelpedByAngel(1);
        }

        /**
         * LifeGiver "accepta" sa aplice modificarile pe pyromancer.
         * Acesta ii modifica viata eroului verificand daca a depasit
         * maximul pe care poate sa il aiba si seteaza ca jucatorul
         * a fost ajutat de un inger.
         * @param pyromancer
         */
        public void accept(final Pyromancer pyromancer) {
                if (pyromancer.getDead() == 1) {
                        return;
                }
                pyromancer.setHp(pyromancer.getHp() + Constants.LGP);
                if (pyromancer.getHp() > Constants.PYROHP
                        + Constants.PYROHPPERLVL * pyromancer.getLevel()) {
                        pyromancer.setHp(Constants.PYROHP
                                + Constants.PYROHPPERLVL * pyromancer.getLevel());
                }
                pyromancer.setWasHelpedByAngel(1);
        }

        /**
         * LifeGiver "accepta" sa aplice modificarile pe wizard.
         * Acesta ii modifica viata eroului verificand daca a depasit
         * maximul pe care poate sa il aiba si seteaza ca jucatorul
         * a fost ajutat de un inger.
         * @param wizard
         */
        public void accept(final Wizard wizard) {
                if (wizard.getDead() == 1) {
                        return;
                }
                wizard.setHp(wizard.getHp() + Constants.LGW);
                if (wizard.getHp() > Constants.WIZHP + Constants.WIZHPPERLVL * wizard.getLevel()) {
                        wizard.setHp(Constants.WIZHP + Constants.WIZHPPERLVL * wizard.getLevel());
                }
                wizard.setWasHelpedByAngel(1);
        }
}
