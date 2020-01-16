package com.tema1.round;

import com.tema1.common.Constants;
import com.tema1.goods.Goods;
import com.tema1.players.Player;
import com.tema1.players.PlayersComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Round {
        private int numberOfPlayers;
        private int count = 0;
        private int numberOfRounds;

        /**
         * <h1>COUNTER PENTRU CARTI.</h1>
         * Acest counter este folosit pentru a lua carti din input.
         * Acesta are rolul de a retinte indexul unde a ramas jucatorul
         * precedent cand a primit carti.
         * @return indexul
         */
        public int getCount() {
                return count;
        }

        /**
         * <h1>SETTER COUNT.</h1>
         * Cand un jucator ia o carte in mana,creste si counterul cu ajutorul
         * acestui setter.
         */
        public void setCount() {
                this.count = this.count + 1;
        }
        /**
         * <h1>NUMARUL DE PLAYERI DIN JOC.</h1>
         * Din main setez numarul de jucatori din joc.
         * Acest numar ne da si numarul de subrunde.
         */
        public void setNumberOfPlayers(final int numberOfPlayers) {
                this.numberOfPlayers = numberOfPlayers;
        }

        /**
         * <h1>SETTER NUMAR DE JUCATORI.></h1>
         * Setez numarul de runde din fieecare joc.
         * @param numberOfRounds
         */
        public void setNumberOfRounds(final int numberOfRounds) {
                this.numberOfRounds = numberOfRounds;
        }

        public Round() {
                if (numberOfRounds > Constants.MAXROUNDS) {
                        this.numberOfRounds = Constants.MAXROUNDS;
                }
        }

        /**
         * <h1>CREEAM JOCUL.</h1>
         * Metoda create the game, creeaza propriu-zis jocul.
         * Cu 3 for-uri care semnifica fiecare runda/subrunda si ce face
         * fiecare jucator in fiecare subrunda
         * Apelez functiile pentru fiecare jucator: de creere a sacului
         * daca
         * @param playersOrder
         * @param goods
         */
        public final void createTheGame(final List<String> playersOrder,
                                        final List<Goods> goods) {
                List<Player> players = new ArrayList<>();
                for (int i = 0; i < numberOfPlayers; ++i) {
                        Player player = new Player();
                        player.setPlayerStrategy(playersOrder.get(i));
                        player.id = i;
                        players.add(player);
                }
                for (int indexRound = 1; indexRound <= numberOfRounds; ++indexRound) {
                        int count = 0;
                        CreateBag createBag = new CreateBag();
                        InspectBag inspectBag = new InspectBag();
                        for (int indexSubRound = 0; indexSubRound < numberOfPlayers;
                             ++indexSubRound) {
                                for (int indexPlayer = 0; indexPlayer < numberOfPlayers;
                                     indexPlayer++) {
                                        if (indexPlayer == indexSubRound) {
                                                players.get(indexPlayer).setPlayerRoundType(1);
                                                players.get(indexPlayer).verifiedBag = 0;
                                        }
                                        if (indexPlayer != indexSubRound) {
                                                players.get(indexPlayer).setPlayerRoundType(0);
                                                for (int j = 0; j < Constants.MAXCARDOFPLAYER; ++j) {
                                                        players.get(indexPlayer).cardsInHand.add(
                                                                goods.get(getCount()));
                                                        setCount();
                                                        players.get(indexPlayer).verifiedBag = 0;
                                                }
                                                if (players.get(indexPlayer).getPlayerStrategy()
                                                        .equals("basic")) {
                                                    createBag.createBasicBag(
                                                            players.get(indexPlayer));
                                                } else if (players.get(indexPlayer).
                                                        getPlayerStrategy().equals("greedy")) {
                                                        if (indexRound % 2 == 0) {
                                                                createBag.createGreedyBag(
                                                                        players.get(indexPlayer));
                                                        } else {
                                                                createBag.createBasicBag(
                                                                        players.get(indexPlayer));
                                                        }
                                                } else if (players.get(indexPlayer).
                                                        getPlayerStrategy().equals("bribed")) {
                                                        createBag.createBribeBag(players.
                                                                get(indexPlayer));
                                                }
                                        }
                                        if (indexPlayer != indexSubRound) {
                                                players.get(indexPlayer).countFrequency();
                                        }
                                }
                                for (int indexPlayer = 0; indexPlayer < numberOfPlayers;
                                                    indexPlayer++) {
                                        //todo here
                                        if (players.get(indexPlayer).getPlayerStrategy().
                                                        equals("basic")
                                                && players.get(indexPlayer).
                                                        getPlayerRoundType() == 1) {
                                                for (int i = 0; i < players.size(); i++) {
                                                        if (indexPlayer != i) {
                                                                inspectBag.inspectBasic(players.
                                                                        get(indexPlayer), players.
                                                                        get(i));
                                                                goods.addAll(inspectBag.
                                                                        nonDeclaredGoods);
                                                                players.get(i).setPlayerCoin(
                                                                        players.get(i).
                                                                                getPlayerCoins()
                                                                                + players.get(i)
                                                                                .bribery);
                                                                players.get(i).bribery = 0;
                                                        }
                                                }
                                        }
                                        if (players.get(indexPlayer).getPlayerStrategy().
                                                equals("greedy")
                                                && players.get(indexPlayer).
                                                        getPlayerRoundType() == 1) {
                                                for (int i = 0; i < playersOrder.size(); i++) {
                                                        if (players.get(i).bribery == 0
                                                                && i != indexPlayer) {
                                                                inspectBag.inspectGreedy(
                                                                        players.get(indexPlayer),
                                                                        players.get(i));
                                                        } else if (players.get(i).bribery
                                                                != 0 && i != indexPlayer) {
                                                                players.get(indexPlayer).
                                                                        setPlayerCoin(players.get(
                                                                                indexPlayer).
                                                                                getPlayerCoins()
                                                                                + players.get(i).
                                                                                        bribery);
                                                                players.get(i).bribery = 0;

                                                                players.get(i).goodsOnTable.addAll(
                                                                        players.get(i).bag);
                                                                players.get(i).bag.clear();
                                                        }
                                                }
                                        }
                                        if (players.get(indexPlayer).getPlayerStrategy().
                                                equals("bribed")
                                                && players.get(indexPlayer).
                                                        getPlayerRoundType() == 1) {
                                                if (indexPlayer == 0) {
                                                        inspectBag.inspectBribe(players.get(
                                                                indexPlayer), players.
                                                                get(numberOfPlayers - 1));
                                                        players.get(numberOfPlayers - 1).
                                                                verifiedBag = 1;
                                                        players.get(numberOfPlayers - 1).
                                                                setPlayerCoin(players.get(
                                                                        numberOfPlayers - 1).
                                                                        getPlayerCoins()
                                                                        + players.get(
                                                                                numberOfPlayers - 1)
                                                                        .bribery);
                                                        players.get(numberOfPlayers - 1).
                                                                bribery = 0;
                                                        if (players.get(indexPlayer + 1).
                                                                verifiedBag == 0) {
                                                                inspectBag.inspectBribe(players.get(
                                                                        indexPlayer), players.
                                                                        get(indexPlayer + 1));
                                                                players.get(indexPlayer + 1).
                                                                        setPlayerCoin(players.get(
                                                                                indexPlayer + 1).
                                                                                getPlayerCoins()
                                                                                + players.get(
                                                                                        indexPlayer
                                                                                                + 1)
                                                                                .bribery);
                                                                players.get(indexPlayer + 1).
                                                                        bribery = 0;
                                                        }
                                                } else if (indexPlayer == (numberOfPlayers - 1)) {
                                                        inspectBag.inspectBribe(players.get(
                                                                indexPlayer), players.get(0));
                                                        players.get(0).verifiedBag = 1;
                                                        players.get(0).setPlayerCoin(players
                                                                .get(0).getPlayerCoins()
                                                                + players.get(0).bribery);
                                                        players.get(0).bribery = 0;
                                                        if (players.get(indexPlayer - 1).
                                                                verifiedBag == 0) {
                                                                inspectBag.inspectBribe(players.
                                                                        get(indexPlayer), players.
                                                                        get(indexPlayer - 1));
                                                                players.get(indexPlayer - 1).
                                                                        verifiedBag = 1;
                                                                players.get(indexPlayer - 1).
                                                                        setPlayerCoin(players.get(
                                                                                indexPlayer - 1).
                                                                                getPlayerCoins()
                                                                                + players.get(
                                                                                        indexPlayer
                                                                                                - 1)
                                                                                .bribery);
                                                                players.get(indexPlayer - 1).
                                                                        bribery = 0;
                                                        }
                                                } else {
                                                        inspectBag.inspectBribe(players.get(
                                                                indexPlayer),
                                                                players.get(indexPlayer + 1));
                                                        inspectBag.inspectBribe(players.get(
                                                                indexPlayer),
                                                                players.get(indexPlayer - 1));
                                                        players.get(indexPlayer + 1).
                                                                verifiedBag = 1;
                                                        players.get(indexPlayer + 1).setPlayerCoin(
                                                                players.get(indexPlayer + 1).
                                                                        getPlayerCoins()
                                                                        + players.get(indexPlayer
                                                                        + 1)
                                                                        .bribery);
                                                        players.get(indexPlayer + 1).bribery = 0;
                                                        players.get(indexPlayer - 1).
                                                                verifiedBag = 1;
                                                        players.get(indexPlayer - 1).
                                                                setPlayerCoin(players.get(
                                                                        indexPlayer - 1).
                                                                        getPlayerCoins()
                                                                        + players.get(indexPlayer
                                                                        - 1)
                                                                        .bribery);
                                                        players.get(indexPlayer - 1).bribery = 0;
                                                }
                                                //aici culege mita
                                                for (int i = 0; i < players.size(); i++) {
                                                        if (players.get(i).verifiedBag != 1) {
                                                                if (i != indexPlayer) {
                                                                        players.get(indexPlayer).
                                                                                setPlayerCoin(
                                                                                        players.get(
                                                                                                indexPlayer).getPlayerCoins() + players.get(i).bribery);
                                                                        players.get(i).
                                                                                setPlayerCoin(
                                                                                        players.get(i).getPlayerCoins() + players.get(i)
                                                                                        .bribery);
                                                                        players.get(i).bribery = 0;
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
                ProfitCalculator profitCalc = new ProfitCalculator();
                for (int i = 0; i < players.size(); i++) {
                        profitCalc.profitCalculator(players.get(i));
                        players.get(i).score = players.get(i).getPlayerCoins()
                                + profitCalc.getLegalProfit()
                                + profitCalc.getIllegalProfit();
                }
                for (int i = 0; i < players.size(); i++) {
                        players.get(i).countFrequencyOnTable();
                }
                GiveKingQueenBonus kq = new GiveKingQueenBonus();
                kq.whoDeserveKingBonus(players);
                for (int i = Constants.MINIDXLEGALGOODS; i <= Constants.MAXIDXLEGALGOODS; i++) {
                        if (kq.indexKingPlayer[i] != -1) {
                                players.get(kq.indexKingPlayer[i]).score += Constants.KINGBONUSES
                                        [kq.indexKingGood[i]];
                        }
                        if (kq.indexQuuenPlayer[i] != -1) {
                                players.get(kq.indexQuuenPlayer[i]).score += Constants.QUEENBONUSES
                                        [kq.indexQueenGood[i]];
                        }
                }

                List<Player> copiePlayers = new ArrayList<>();
                copiePlayers.addAll(players);
                PlayersComparator playersComparator = new PlayersComparator();
                Collections.sort(copiePlayers, playersComparator);

                for (int i = 0; i < copiePlayers.size(); i++) {
                        System.out.println(copiePlayers.get(i).id + " "
                                + copiePlayers.get(i).getPlayerStrategy().toUpperCase()
                                + " " + copiePlayers.get(i).score);
                }
        }
}
