package angels;

import common.Constants;
import input.Angels;
import player.Knight;
import player.Pyromancer;
import player.Rogue;
import player.Wizard;

public class LevelUpAngel extends Angels {
        public LevelUpAngel(final String type, final int linePosition, final int columnPosition,
                            final String whatDoes) {
                super(type, linePosition, columnPosition, whatDoes);
        }

        /**
         * LevelUpAngel "accepta" sa aplice modificarile pe rogue.
         * Acesta ii modifica atat modificatorii de damage, cat si
         * xp-ul jucatorului atat cat este necesar pentru a primi
         * level up de damage si seteaza ca jucatorul a fost ajutat
         * de un inger.
         * @param rogue
         */
        public void accept(final Rogue rogue) {
                if (rogue.getDead() == 1) {
                        return;
                }
                rogue.incBonusProc(Constants.LUARMOD);

                int newXpToAdd = Constants.PLAYERBASEUPXP
                        + rogue.getLevel() * Constants.PLAYERXPMULTIPLIER - rogue.getXp();
                rogue.setXp(rogue.getXp() + newXpToAdd);
                rogue.setWasHelpedByAngel(1);
                rogue.checkExp();

        }

        /**
         * LevelUpAngel "accepta" sa aplice modificarile pe knight.
         * Acesta ii modifica atat modificatorii de damage, cat si
         * xp-ul jucatorului atat cat este necesar pentru a primi
         * level up de damage si seteaza ca jucatorul a fost ajutat
         * de un inger.
         * @param knight
         */
        public void accept(final Knight knight) {
                if (knight.getDead() == 1) {
                        return;
                }
                knight.incBonusProc(Constants.LUAKMOD);

                int newXpToAdd = Constants.PLAYERBASEUPXP
                        + knight.getLevel() * Constants.PLAYERXPMULTIPLIER - knight.getXp();
                knight.setXp(knight.getXp() + newXpToAdd);
                knight.setWasHelpedByAngel(1);
                knight.checkExp();
        }

        /**
         * LevelUpAngel "accepta" sa aplice modificarile pe pyromancer.
         * Acesta ii modifica atat modificatorii de damage, cat si
         * xp-ul jucatorului atat cat este necesar pentru a primi
         * level up de damage si seteaza ca jucatorul a fost ajutat
         * de un inger.
         * @param pyromancer
         */
        public void accept(final Pyromancer pyromancer) {
                if (pyromancer.getDead() == 1) {
                        return;
                }
                pyromancer.incBonusProc(Constants.LUAPMOD);

                int newXpToAdd = Constants.PLAYERBASEUPXP
                        + pyromancer.getLevel() * Constants.PLAYERXPMULTIPLIER - pyromancer.getXp();
                pyromancer.setXp(pyromancer.getXp() + newXpToAdd);
                pyromancer.setWasHelpedByAngel(1);
                pyromancer.checkExp();
        }

        /**
         * LevelUpAngel "accepta" sa aplice modificarile pe wizard.
         * Acesta ii modifica atat modificatorii de damage, cat si
         * xp-ul jucatorului atat cat este necesar pentru a primi
         * level up de damage si seteaza ca jucatorul a fost ajutat
         * de un inger.
         * @param wizard
         */
        public void accept(final Wizard wizard) {
                if (wizard.getDead() == 1) {
                        return;
                }
                wizard.incBonusProc(Constants.LUAWMOD);

                int newXpToAdd = Constants.PLAYERBASEUPXP
                        + wizard.getLevel() * Constants.PLAYERXPMULTIPLIER - wizard.getXp();
                wizard.setXp(wizard.getXp() + newXpToAdd);
                wizard.setWasHelpedByAngel(1);
                wizard.checkExp();
        }
}
