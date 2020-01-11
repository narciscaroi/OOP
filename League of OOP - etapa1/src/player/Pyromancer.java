package player;

import common.Constants;
import input.Player;

public class Pyromancer extends Player {
        public Pyromancer() {
        }

        public Pyromancer(final String race, final int linePosition, final int columnPosition) {
                super(race, linePosition, columnPosition, Constants.PYROHP, Constants.PYRDMG, 0);
        }
        /**
         * calculez damage-ul dat de pyromancer dat pe spell-ul FireBlast.
         * @return damage-ul pe FireBlast
         */
        private float fireblast() {
                float fireDmg = getDamage() + Constants.PYRDMGPERLVL * getLevel();
                if (getCurrentField() == 'V') {
                        fireDmg *= Constants.PYRFIELDBONUS;
                }
                return fireDmg;
        }
        /**
         * Calculez damage-ul dat pe spell-ul Ignite in runa luptei.
         * @return
         */
        private float igniteNow() {
                float igniteDmg = Constants.PYRIGNDMG + Constants.PYRIGNDMGPERLVL * getLevel();
                if (getCurrentField() == 'V') {
                        igniteDmg *= Constants.PYRFIELDBONUS;
                }
                return igniteDmg;
        }
        /**
         * Calculez damage-ul dat pe ignite urmatoarele 2 runde ca DoT.
         * @return
         */
        private float igniteNext() {
                float igniteDmg = Constants.PYRIGNDOTDMG + Constants.PYRIGNDOTPERLVL * getLevel();
                if (getCurrentField() == 'V') {
                        igniteDmg *= Constants.PYRFIELDBONUS;
                }
                return igniteDmg;
        }
        /**
         * Calculesz damage-ul dat pe deflect de catre wizard.
         * @param wizard
         * @return
         */
        private float deflect(final Wizard wizard) {
                float deflectFireDealt = getDamage() + Constants.PYRDMGPERLVL * getLevel();
                float deflectIgniteNow = Constants.PYRIGNDMG + Constants.PYRIGNDMGPERLVL
                        * getLevel();
                float deflectDmg = deflectFireDealt + deflectIgniteNow;
                float proccent = Constants.WIZDEFLPR + Constants.WIZDELFINCPROC * wizard.getLevel();
                if (proccent > Constants.WIZDEFLMAXPROC) {
                        proccent = Constants.WIZDEFLMAXPROC;
                }
                if (getCurrentField() == 'V') {
                        deflectDmg *= Constants.PYRFIELDBONUS;
                }
                deflectDmg = proccent * (deflectDmg);
                deflectDmg = Math.round(deflectDmg);
                deflectDmg *= Constants.DEFKODWTP;
                if (wizard.getCurrentField() == 'D') {
                        deflectDmg *= Constants.WIZDESERTBONUS;
                }
                return  Math.round(deflectDmg);
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
         * Calculez damage-ul dat impotriva unui Pyromancer.
         * Calculez damage-ul dat de primul spell cu race modifier.
         * Calculez damage-ul dat pe al doilea spell cu race modifier, care este si DoT
         * si setez damage-ul pe care o sa il primeasca playerul cu care se lupta
         * urmatoarele 2 runde.
         * @param rogue
         */
        @Override
        public void giveDamageTo(final Rogue rogue) {
                float fireDmg = fireblast();
                fireDmg *= Constants.PYRDMGPTR;

                float igniteNowDmg = igniteNow();
                igniteNowDmg *= Constants.PYRIGNPTR;

                int totalDamage = Math.round(fireDmg) + Math.round(igniteNowDmg);
                int rogNewHp = rogue.getHp() - totalDamage;
                rogue.setHp(rogNewHp);
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
                float igniteNextRounds = igniteNext();
                igniteNextRounds *= Constants.PYRIGNPTR;
                rogue.counterRoundsDot = Constants.PYRIGNDOTTIME;
                rogue.dot = Math.round(igniteNextRounds);
        }
        /**
         * Calculez damage-ul dat impotriva unui Pyromancer.
         * Calculez damage-ul dat de primul spell cu race modifier.
         * Calculez damage-ul dat pe al doilea spell cu race modifier, care este si DoT
         * si setez damage-ul pe care o sa il primeasca playerul cu care se lupta
         * urmatoarele 2 runde.
         * @param knight
         */
        @Override
        public void giveDamageTo(final Knight knight) {
                float fireDmg = fireblast();
                fireDmg *= Constants.PYRDMGPTK;

                float igniteNowDmg = igniteNow();
                igniteNowDmg *= Constants.PYRIGNPTK;

                int totalDamage = Math.round(fireDmg) + Math.round(igniteNowDmg);
                int rogNewHp = knight.getHp() - totalDamage;
                knight.setHp(rogNewHp);
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
                float igniteNextRounds = igniteNext();
                igniteNextRounds *=  Constants.PYRIGNPTK;
                knight.counterRoundsDot = Constants.PYRIGNDOTTIME;
                knight.dot = Math.round(igniteNextRounds);
        }
        /**
         * Calculez damage-ul dat impotriva unui Wizard
         * Calculez damage-ul dat de primul spell cu race modifier.
         * Calculez damage-ul dat pe al doilea spell cu race modifier, care este si DoT
         * si setez damage-ul pe care o sa il primeasca playerul cu care se lupta
         * urmatoarele 2 runde.
         * Calculez damage-ul pe care o sa il primeasca pyromancer de la deflectul lui wizard.
         * @param wizard
         */
        @Override
        public void giveDamageTo(final Wizard wizard) {
                float fireDmg = fireblast();
                fireDmg *= Constants.PYRDMGPTW;

                float igniteNowDmg = igniteNow();
                igniteNowDmg *= Constants.PYRIGNPTW;

                int totalDamage = Math.round(fireDmg) + Math.round(igniteNowDmg);
                int wizNewHp = wizard.getHp() - totalDamage;
                wizard.setHp(wizNewHp);
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

                float igniteNextRounds = igniteNext();
                igniteNextRounds *= Constants.PYRIGNPTW;
                wizard.counterRoundsDot = Constants.PYRIGNDOTTIME;
                wizard.dot = Math.round(igniteNextRounds);
                //deflect
                float deflectDmg = deflect(wizard);
                int newRogHp = getHp() - Math.round(deflectDmg);
                setHp(newRogHp);
                if (getDead() == 1) {
                        if (wizard.getGotLevelUp() == 0) {
                                int newXp = wizard.getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (wizard.getLevel() - getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                wizard.setXp(newXp);
                                wizard.setGotLevelUp(1);
                        }
                }
        }
        /**
         * Calculez damage-ul dat impotriva unui Pyromancer.
         * Calculez damage-ul dat de primul spell cu race modifier.
         * Calculez damage-ul dat pe al doilea spell cu race modifier, care este si DoT
         * si setez damage-ul pe care o sa il primeasca playerul cu care se lupta
         * urmatoarele 2 runde.
         * @param pyromancer
         */
        @Override
        public void giveDamageTo(final Pyromancer pyromancer) {
                float fireDmg = fireblast();
                fireDmg *= Constants.PYRDMGPTP;

                float igniteNowDmg = igniteNow();
                igniteNowDmg *= Constants.PYRIGNPTP;

                int totalDamage = Math.round(fireDmg) + Math.round(igniteNowDmg);
                int rogNewHp = pyromancer.getHp() - totalDamage;
                pyromancer.setHp(rogNewHp);
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

                float igniteNextRounds = igniteNext();
                igniteNextRounds *= Constants.PYRIGNPTP;
                pyromancer.counterRoundsDot = Constants.PYRIGNDOTTIME;
                pyromancer.dot = Math.round(igniteNextRounds);
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
                        super.setHp(Constants.PYROHP + getLevel() * Constants.PYROHPPERLVL);
                }
        }
}
