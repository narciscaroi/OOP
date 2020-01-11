package player;

import common.Constants;
import input.Player;

public class Wizard extends Player {
        public Wizard() { }

        public Wizard(final String race, final int linePosition, final int columnPosition) {
                super(race, linePosition, columnPosition, Constants.WIZHP,
                        0,  Constants.WIZPROC);
        }
        private float initProc() {
                float proc = getProcentDmg() + Constants.WIZPROCPERLVL * getLevel();
                if (getCurrentField() == 'D') {
                        proc *= Constants.WIZDESERTBONUS;
                }
                return proc;
        }
        /**
         * Apelez prin DD cu cine se lupta un player.
         * @param player
         */
        @Override
        public void fightsWith(final Player player) {
                player.giveDamageTo(this);
        }
        /**
         * Calculez damage-ul dat pe spell-ul Drain.
         * Calculez procentul si minimul dupa formula data si le inmultesc.
         * @param rogue
         */
        @Override
        public void giveDamageTo(final Rogue rogue) {
                float proc = initProc();
                proc -= proc * Constants.DRMODWTR;
                int rogMaxHp = Constants.ROGHP + Constants.ROGHPPERLVL * rogue.getLevel();
                float minim = Math.min((Constants.WIZMINFCTMIN * rogMaxHp),
                                        rogue.getHpBeforeDeflect());
                int dmgDealt = Math.round(proc * minim);
                rogue.setHp(rogue.getHp() - Math.round(dmgDealt));
                rogue.checkIsDead();

                if (rogue.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel() - rogue.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                                setGotLevelUp(1);
                        }
                }
        }
        /**
         * Calculez damage-ul dat pe spell-ul Drain.
         * Calculez procentul si minimul dupa formula data si le inmultesc.
         * @param knight
         */
        @Override
        public void giveDamageTo(final Knight knight) {
                float proc = initProc();
                proc += proc * Constants.DRMODWTK;

                int kniMaxHp = Constants.KNIGHTHP + Constants.KNIGHTHPPERLVL * knight.getLevel();
                float minim = Math.min((Constants.WIZMINFCTMIN * kniMaxHp),
                                        knight.getHpBeforeDeflect());
                int dmgDealt = Math.round(proc * minim);
                knight.setHp(knight.getHp() - dmgDealt);
                knight.checkIsDead();

                if (knight.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel() - knight.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                                setGotLevelUp(1);
                        }
                }

        }
        /**
         * Calculez damage-ul dat pe spell-ul Drain.
         * Calculez procentul si minimul dupa formula data si le inmultesc.
         * @param wizard
         */
        @Override
        public void giveDamageTo(final Wizard wizard) {
                float proc = initProc();
                proc += proc * Constants.DRMODWTW;
                int wizMaxHp = Constants.WIZHP + Constants.WIZHPPERLVL * wizard.getLevel();
                float minim = Math.min((Constants.WIZMINFCTMIN * wizMaxHp), wizard.getHp());
                int dmgDealt = Math.round(proc * minim);
                wizard.setHp(wizard.getHp() - dmgDealt);
                wizard.checkIsDead();

                if (wizard.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel() - wizard.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                                setGotLevelUp(1);
                        }
                }
        }
        /**
         * Calculez damage-ul dat pe spell-ul Drain.
         * Calculez procentul si minimul dupa formula data si le inmultesc.
         * @param pyromancer
         */
        @Override
        public void giveDamageTo(final Pyromancer pyromancer) {
                float proc = initProc();
                proc -= proc * Constants.DRMODWTP;

                int pyroMaxHp = Constants.PYROHP + Constants.ROGHPPERLVL * pyromancer.getLevel();
                int minim = Math.round(Math.min((Constants.WIZMINFCTMIN * pyroMaxHp),
                        pyromancer.getHpBeforeDeflect()));

                float dmgDealt = (proc * minim);
                pyromancer.setHp(pyromancer.getHp() - Math.round(dmgDealt));
                pyromancer.checkIsDead();

                if (pyromancer.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel()
                                                - pyromancer.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                                setGotLevelUp(1);
                        }
                }
        }
        /**
         * La inceputul fiecarei runde verific daca un jucator a primit experienta.
         * Daca acestuia i-a crescut xp-ul, ii si incrementez leve-ul in functie de xp-ul
         * pe care acesta il are.
         */
        @Override
        public void checkExp() {
                while (getXp() >= (Constants.PLAYERBASEUPXP
                        + getLevel() * Constants.PLAYERXPMULTIPLIER)) {
                        super.setLevel(getLevel() + 1);
                        super.setHp(Constants.WIZHP + getLevel() * Constants.WIZHPPERLVL);
                }
        }

}
