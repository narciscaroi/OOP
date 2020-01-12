package player;

import common.Constants;
import input.Angels;
import input.Player;

public class Knight extends Player implements HeroStrategy {
        public Knight() {
        }

        public Knight(final String race, final int linePosition, final int columnPosition,
                      final int id) {
                super(race, linePosition, columnPosition,
                        Constants.KNIGHTHP, Constants.KNIGHTDMG, 0, id, "Knight");
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
         * A.
         * @param angel
         */
        @Override
        public void angelVisitor(final Angels angel) {
               angel.accept(this);
        }

        /**
         * A.
         * @return
         */
        @Override
        public void heroStrategy() {
                float maxHp = Constants.KNIGHTHP + Constants.KNIGHTHPPERLVL * getLevel();

                if (Constants.KLEFTFRAC * maxHp < getStartFightHp()
                        && getStartFightHp() < Constants.KRIGHTFRAC * maxHp
                        && getSettedStrategy() == 0
                        && getNextMovementStopped() == 0) {
                        setHp(getHp() * Constants.KDOWNHP);
                        incBonusProc(Constants.KUPCOEF);
                        setSettedStrategy(1);
                        System.out.println("AAAAAAAAAAAAAAAAAAA");
                        return;
                }

                if (Constants.KLEFTFRAC * maxHp > getStartFightHp()
                     && getSettedStrategy() == 0
                     && getNextMovementStopped() == 0) {
                        setHp(getHp() * Constants.KUPHP);
                        decBonusProc(Constants.KDOWNCOEF);
                        System.out.println("BBBBBBBBBBBBBBBB");
                        setSettedStrategy(1);
                }

        }
        /**
         * Calculez damage-ul pe care il da impotriva unui Rogue.
         * Ii calculez damage-ul dat pe primul spell cu modificatorul
         * de rasa adunat cu bonusul de rasa acumulat pe parcurs
         * si verific daca acesta da "instakill" pe acest spell.
         * Apoi calculez damage-ul pe al doilea spell cu modificatorul de rasa, la care adun
         * damage-ul pe primul spell si il alculez cu bonus de teren.
         * Verific daca Knight l-a omorat pe Rogue si in caz ca da, ii setez noul XP si setez
         * si faptul ca acesta a primit xp-ul pentru acest win.
         * @param rogue
         */
        @Override
        public void giveDamageTo(final Rogue rogue) {
                rogue.heroStrategy();
                heroStrategy();

                float execDamage = getDamage() + getLevel() * Constants.KNIGHTHPPERLVL;

                float procent = Constants.KENEMYHPPROC
                                + rogue.getLevel() * Constants.KENEMYMINPROC;
                if (procent > Constants.KENEMYMAXPROC) {
                        procent = Constants.KENEMYHPPROC;
                }

                execDamage *= (Constants.KMODKTR + getBonusProc());
                float rogMaxHp = rogue.getHp() + rogue.getLevel() * Constants.ROGHPPERLVL;
                int verExecuteHp = Math.round(procent * rogMaxHp);
                if (verExecuteHp >= rogue.getHp()) {
                        rogue.setHp(0);
                        rogue.checkIsDead();
                        int newXp = getXp() + Math.max(Constants.MINEXPGET, Constants.MAXFCTARGEXP
                                - (getLevel() - rogue.getLevel() * Constants.PLAYERMAXFCTMULT));
                        setXp(newXp);
                        return;
                }

                float slamDamage = Constants.KSLAMBASEDMG + getLevel() * Constants.KSLAMDMGPERLVL;
                slamDamage *= (Constants.KMODSLAMKTR + getBonusProc());
                rogue.setNextMovementStopped(1);
                System.out.println("EXEC DMG  " + execDamage);
                System.out.println("SLAM DMG " + slamDamage);
                float totalDamage = execDamage + slamDamage;
                if (getCurrentField() == 'L') {
                        totalDamage *= Constants.KNIGHTFIELDBONUS;
                }

                float rogNewHp = rogue.getHp() - Math.round(totalDamage);
                rogue.setHp(rogNewHp);
                rogue.checkIsDead();
                if (rogue.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel() - rogue.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                        }
                }
        }
        /**
         * Calculez damage-ul pe care il da impotriva unui Knight.
         * Ii calculez damage-ul dat pe primul spell cu modificatorul de rasa
         * si verific daca acesta da "instakill" pe acest spell.
         * Apoi calculez damage-ul pe al doilea spell cu modificatorul de rasa, la care adun
         * damage-ul pe primul spell si il alculez cu bonus de teren.
         * Verific daca Knight l-a omorat si in caz ca da, ii setez noul XP si setez
         * si faptul ca acesta a primit xp-ul pentru acest win.
         * @param knight
         */
        @Override
        public void giveDamageTo(final Knight knight) {
                setNextMovementStopped(1);
                knight.heroStrategy();
                heroStrategy();

                float execDamage = getDamage() + getLevel() * Constants.KNIGHTHPPERLVL;

                float procent = Constants.KENEMYHPPROC
                        + knight.getLevel() * Constants.KENEMYMINPROC;
                if (procent > Constants.KENEMYMAXPROC) {
                        procent = Constants.KENEMYHPPROC;
                }
                execDamage *= Constants.KMODKTK;
                float kniMaxHp = knight.getHp() + knight.getLevel() * Constants.KNIGHTHPPERLVL;
                int verExecuteHp = Math.round(procent * kniMaxHp);
                if (verExecuteHp >= knight.getHp()) {
                        knight.setHp(0);
                        knight.checkIsDead();
                        int newXp = getXp() + Math.max(Constants.MINEXPGET, Constants.MAXFCTARGEXP
                                - (getLevel() - knight.getLevel()) * Constants.PLAYERMAXFCTMULT);
                        setXp(newXp);
                        return;
                }

                float slamDamage = Constants.KSLAMBASEDMG + getLevel() * Constants.KSLAMDMGPERLVL;
                slamDamage *= (Constants.KMODSLAMKTK + getBonusProc());
                System.out.println("EXEC DMG : " + execDamage);
                System.out.println("SLAM DMG : " + slamDamage);
                knight.setNextMovementStopped(1);
                float totalDamage = execDamage + slamDamage;
                if (getCurrentField() == 'L') {
                        totalDamage *=  Constants.KNIGHTFIELDBONUS;
                }

                float kniNewHp = knight.getHp() - Math.round(totalDamage);
                knight.setHp(kniNewHp);
                knight.checkIsDead();
                if (knight.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel() - knight.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                        }
                }
        }
        /**
         * Calculez procentul de la deflect.
         * @param wizard
         * @return
         */
        public float wizzDeflectProcc(final Wizard wizard) {
                float proccent = Constants.WIZDEFLPR + Constants.WIZDELFINCPROC * wizard.getLevel();
                if (proccent > Constants.WIZDEFLMAXPROC) {
                        proccent = Constants.WIZDEFLMAXPROC;
                }
                return proccent;
        } /**
         * Calculez damage-ul dat de deflect fara race modifier.
         * @param wizard
         * @return
         */
        public float wizzInstaKillDeflectDmg(final Wizard wizard) {
                float procent = wizzDeflectProcc(wizard);
                float deflectDmg = procent * wizard.getHp();
                return deflectDmg;
        }
        /**
         * Calculez damage-ul pe care il da impotriva unui Wizard.
         * Ii calculez damage-ul dat pe primul spell cu modificatorul
         * de rasa la care adun procentul bonus acumulat pe parcurs
         * si verific daca acesta da "instakill" pe acest spell.
         * Apoi calculez damage-ul pe al doilea spell cu modificatorul de rasa, la care adun
         * damage-ul pe primul spell si il alculez cu bonus de teren.
         * Verific daca Knight l-a omorat si in caz ca da, ii setez noul XP si setez
         * si faptul ca acesta a primit xp-ul pentru acest win.
         * Calculez damage-ul dat de wizard pe deflect.
         * @param wizard
         */
        @Override
        public void giveDamageTo(final Wizard wizard) {
                wizard.heroStrategy();
                heroStrategy();

                float execDamage = getDamage() + getLevel() * Constants.KNIGHTHPPERLVL;
                float deflectExecDamage = execDamage;
                float procent = Constants.KENEMYHPPROC
                        + wizard.getLevel() * Constants.KENEMYMINPROC;
                if (procent > Constants.KENEMYMAXPROC) {
                        procent = Constants.KENEMYHPPROC;
                }

                execDamage *= (Constants.KMODKTW + getBonusProc());
                float wizMaxHp = wizard.getHp() + wizard.getLevel() * Constants.KNIGHTHPPERLVL;
                int verExecuteHp = Math.round(procent * wizMaxHp);

                if (verExecuteHp >= wizard.getHp()) {
                        float deflectDmg = wizzInstaKillDeflectDmg(wizard);
                        int newKniHp = Math.round(deflectDmg);
                        setHp(newKniHp);
                        wizard.setHp(0);
                        wizard.checkIsDead();
                        int newXp = getXp() + Math.max(Constants.MINEXPGET, Constants.MAXFCTARGEXP
                                - (getLevel() - wizard.getLevel()) * Constants.PLAYERMAXFCTMULT);
                        setXp(newXp);
                        return;
                }

                float slamDamage = Constants.KSLAMBASEDMG + getLevel() * Constants.KSLAMDMGPERLVL;
                float deflectSlamDamage = Constants.KSLAMBASEDMG
                        + getLevel() * Constants.KSLAMDMGPERLVL;

                slamDamage *= (Constants.KMODSLAMKTW + getBonusProc());
                wizard.setNextMovementStopped(1);

                if (getCurrentField() == 'L') {
                        execDamage *= Constants.KNIGHTFIELDBONUS;
                        slamDamage *= Constants.KNIGHTFIELDBONUS;
                        deflectExecDamage *= Constants.KNIGHTFIELDBONUS;
                        deflectSlamDamage *= Constants.KNIGHTFIELDBONUS;
                }
                float totalDamage = execDamage + slamDamage;
                System.out.println("EEEXEEEC " + execDamage);
                System.out.println("SLLLAAAAM " + slamDamage);
                float wizNewHp = wizard.getHp() - Math.round(totalDamage);
                wizard.setHp(wizNewHp);
                wizard.checkIsDead();
                if (wizard.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel() - wizard.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                        }
                }

                float proc = wizzDeflectProcc(wizard);
                float totalDeflectDmg = proc * (deflectExecDamage + deflectSlamDamage);
                if (wizard.getCurrentField() == 'D') {
                        totalDeflectDmg *= Constants.WIZDESERTBONUS;
                }
                totalDeflectDmg *= (Constants.DEFKODWTK + wizard.getBonusProc());
                System.out.println("TOTAL DEFLECT DMG " + totalDeflectDmg);
                System.out.println("DAAAA " + wizard.getBonusProc());
                float kniNewHp = getHp() - Math.round(totalDeflectDmg);
                setHp(kniNewHp);
                checkIsDead();
                if (getDead() == 1) {
                        if (wizard.getGotLevelUp() == 0) {
                                int newXp = wizard.getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (wizard.getLevel() - getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                wizard.setXp(newXp);
                        }
                }
        }
        /**
         * Calculez damage-ul pe care il da impotriva unui Pyromancer.
         * Ii calculez damage-ul dat pe primul spell cu modificatorul de rasa
         * si verific daca acesta da "instakill" pe acest spell.
         * Apoi calculez damage-ul pe al doilea spell cu modificatorul de rasa, la care adun
         * damage-ul pe primul spell si il alculez cu bonus de teren.
         * Verific daca Knight l-a omorat si in caz ca da, ii setez noul XP si setez
         * si faptul ca acesta a primit xp-ul pentru acest win.
         * @param pyromancer
         */
        @Override
        public void giveDamageTo(final Pyromancer pyromancer) {
                pyromancer.heroStrategy();
                heroStrategy();

                float execDamage = getDamage() + getLevel() * Constants.KNIGHDMGPERLVL;

                float procent = Constants.KENEMYHPPROC
                       + pyromancer.getLevel() * Constants.KENEMYMINPROC;
                if (procent > Constants.KENEMYMAXPROC) {
                        procent = Constants.KENEMYHPPROC;
                }
                execDamage *= (Constants.KMODKTP + getBonusProc());
                float pyrMaxHp = pyromancer.getHp() + pyromancer.getLevel()
                       * Constants.PYROHPPERLVL;
                System.out.println("BONUS PROC " + getBonusProc());
                int verExecuteHp = Math.round(procent * pyrMaxHp);
                if (verExecuteHp >= pyromancer.getHp()) {
                        pyromancer.setHp(0);
                        pyromancer.checkIsDead();
                        int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                Constants.MAXFCTARGEXP - (getLevel() - pyromancer.getLevel())
                                        * Constants.PLAYERMAXFCTMULT);
                        setXp(newXp);
                        return;
                }

                float slamDamage = Constants.KSLAMBASEDMG + getLevel() * Constants.KSLAMDMGPERLVL;
                slamDamage *= (Constants.KMODSLAMKTP + getBonusProc());
                pyromancer.setNextMovementStopped(1);
                System.out.println("EXEC DMG  " + execDamage);
                System.out.println("SLAM DMG  " + slamDamage);
                float totalDamage = execDamage + slamDamage;
                if (getCurrentField() == 'L') {
                        totalDamage *= Constants.KNIGHTFIELDBONUS;
                }

                float pyrNewHp = pyromancer.getHp() - Math.round(totalDamage);
                pyromancer.setHp(pyrNewHp);
                pyromancer.checkIsDead();
                if (pyromancer.getDead() == 1) {
                        if (getGotLevelUp() == 0) {
                                int newXp = getXp() + Math.max(Constants.MINEXPGET,
                                        Constants.MAXFCTARGEXP - (getLevel()
                                                - pyromancer.getLevel())
                                                * Constants.PLAYERMAXFCTMULT);
                                setXp(newXp);
                        }
                }
        }
        /**
         * La inceputul fiecarei runde verific daca un jucator a primit experienta.
         * Daca acestuia i-a crescut xp-ul, ii si incrementez leve-ul in functie de xp-ul
         * pe care acesta il are si setez faptul ca acesta a primit lvlup.
         */
        @Override
        public void checkExp() {
                while (getXp() >= (Constants.PLAYERBASEUPXP
                       + getLevel() * Constants.PLAYERXPMULTIPLIER)
                        && getDead() == 0) {
                        super.setLevel(getLevel() + 1);
                        super.setHp(Constants.KNIGHTHP + getLevel() * Constants.KNIGHTHPPERLVL);
                        super.setGotLevelUp(1);
                }
        }
}
