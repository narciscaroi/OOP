package player;

import common.Constants;
import input.Player;

public class Rogue extends Player {
        public Rogue() {
        }

        public Rogue(final String race, final int linePosition, final int columnPosition) {
                super(race, linePosition, columnPosition, Constants.ROGHP, Constants.ROGDMG, 0);
        }
        /**
         * Calculez damage-ul dat pe backstab de baza cu modificatorul de teren si critica.
         * @return
         */
        private float backstab() {
                float dmgDealt = getDamage() + Constants.ROGDMGPERLVL * getLevel();
                if (getCurrentField() == 'W') {
                        if (getCounterRogueCritChance() % Constants.ROGVERIFYIFCRIT == 0) {
                                dmgDealt *= Constants.ROGCRITPROC;
                        }
                        dmgDealt *= Constants.ROGFIELDBONUS;
                }
                return dmgDealt;
        }
        /**
         * Calculez damage-ul de baza dat de paralysis cu modificatorul de teren.
         * @return
         */
        private float paralysis() {
                float paralysisDmg = Constants.PARALYDMG + Constants.PARALYDMGPERLVL * getLevel();
                if (getCurrentField() == 'W') {
                        paralysisDmg *= Constants.ROGFIELDBONUS;
                }
                return paralysisDmg;
        }
        /**
         * Calculez damage-ul dat de wizard pe spell-ul deflect.
         * @param wizard
         * @return
         */
        private float deflect(final Wizard wizard) {
                float deflectDmgDealt = backstab();
                float deflectParalysisDmg = paralysis();
                float proccent = Constants.WIZDEFLPR + Constants.WIZDELFINCPROC * wizard.getLevel();
                if (proccent > Constants.WIZDEFLMAXPROC) {
                        proccent = Constants.WIZDEFLMAXPROC;
                }
                float deflectDmg = proccent * (deflectDmgDealt + deflectParalysisDmg);
                if (wizard.getCurrentField() == 'D') {
                        deflectDmg *= Constants.WIZDESERTBONUS;
                }
                deflectDmg *= Constants.DEFKODWTR;
                return  deflectDmg;
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
         * Calculez damage-ul dat de spell-ul backstab si spell-ul paralysis.
         * Setez oponentului cate runde primeste DoT in functie de teren.'
         * Setez cat DoT primeste per runda un player.
         * @param rogue
         */
        @Override
        public void giveDamageTo(final Rogue rogue) {
                //backstab
                float dmgDealt = backstab();
                dmgDealt *= Constants.BSMODRTR;

                int counterDot = 0;
                float paralysisDmg = paralysis();
                paralysisDmg *= Constants.PMODRTR;
                if (getCurrentField() == 'W') {
                        counterDot = Constants.PDOTWTIME;
                } else {
                        counterDot = Constants.PDOTTIME;
                }
                int totalDamage = Math.round(dmgDealt) + Math.round(paralysisDmg);
                int newHp = rogue.getHp() - totalDamage;
                setCounterRogueCritChance();
                rogue.setHp(newHp);
                rogue.counterRoundsDot = counterDot;
                rogue.dot = Math.round(paralysisDmg);
                rogue.setNextMovementStopped(counterDot);

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
                setCounterRogueCritChance();
        }
        /**
         * Calculez damage-ul dat de spell-ul backstab si spell-ul paralysis.
         * Setez oponentului cate runde primeste DoT in functie de teren.'
         * Setez cat DoT primeste per runda un player.
         * @param knight
         */
        @Override
        public void giveDamageTo(final Knight knight) {
                //backstab
                float dmgDealt = backstab();
                dmgDealt *= Constants.BSMODRTK;

                //paralysis
                int counterDot = 0;
                float paralysisDmg = paralysis();
                paralysisDmg *= Constants.PMODRTK;
                if (getCurrentField() == 'W') {
                        counterDot = Constants.PDOTWTIME;
                } else {
                        counterDot = Constants.PDOTTIME;
                }
                knight.dot = Math.round(paralysisDmg);
                knight.counterRoundsDot = counterDot;
                knight.setNextMovementStopped(counterDot);
                setCounterRogueCritChance();
                //modific hp
                int totalDamage = Math.round(dmgDealt) + Math.round(paralysisDmg);
                int newHp = knight.getHp() - totalDamage;
                knight.setHp(newHp);
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
         * Calculez damage-ul dat de spell-ul backstab si spell-ul paralysis.
         * Setez oponentului cate runde primeste DoT in functie de teren.'
         * Setez cat DoT primeste per runda un player.
         * Calculez cat damage primeste  Rogue-ul de la Wizard pe deflect
         * @param
         */
        @Override
        public void giveDamageTo(final Wizard wizard) {
                //backstab
                float dmgDealt = backstab();
                dmgDealt *= Constants.BSMODRTW;
                //todo DoT paralysis
                int counterDot = 0;
                float paralysisDmg = paralysis();

                paralysisDmg *= Constants.PMODRTW;
                if (getCurrentField() == 'W') {
                        counterDot = Constants.PDOTWTIME;
                } else {
                        counterDot = Constants.PDOTTIME;
                }

                wizard.dot = Math.round(paralysisDmg);
                wizard.counterRoundsDot = counterDot;
                wizard.setNextMovementStopped(counterDot);
                wizard.setTookDot();
                //modific hp
                int totalDamage = Math.round(dmgDealt) + Math.round(paralysisDmg);
                int newHp = wizard.getHp() - totalDamage;
                wizard.setHp(newHp);
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
                //deflect dat de wizz in atacator
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
                setCounterRogueCritChance();
        }
        /**
         * Calculez damage-ul dat de spell-ul backstab si spell-ul paralysis.
         * Setez oponentului cate runde primeste DoT in functie de teren.'
         * Setez cat DoT primeste per runda un player.
         * @param pyromancer
         */
        @Override
        public void giveDamageTo(final Pyromancer pyromancer) {
                //backstab
                float dmgDealt = backstab();
                dmgDealt *= Constants.BSMODRTP;

                int counterDot = 0;
                float paralysisDmg = paralysis();
                paralysisDmg *= Constants.PMODRTP;
                if (getCurrentField() == 'W') {
                        counterDot = Constants.PDOTWTIME;
                } else {
                        counterDot = Constants.PDOTTIME;
                }
                pyromancer.dot = Math.round(paralysisDmg);
                pyromancer.counterRoundsDot = counterDot;
                pyromancer.setNextMovementStopped(counterDot);
                int totalDamage = Math.round(dmgDealt) + Math.round(paralysisDmg);
                //modific hp
                int newHp = pyromancer.getHp() - totalDamage;
                pyromancer.setHp(newHp);
                pyromancer.checkIsDead();
                setCounterRogueCritChance();
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
                        super.setHp(Constants.ROGHP + getLevel() * Constants.ROGHPPERLVL);
                }
        }

}
