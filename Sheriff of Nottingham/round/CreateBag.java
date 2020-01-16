package com.tema1.round;

import com.tema1.common.Constants;
import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.goods.GoodsType;
import com.tema1.players.Player;
import com.tema1.players.GoodsComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateBag {
    public CreateBag() {
    }

    /**
     * <h1>IMPLEMENT OF BASIC BAG.</h1>
     * ->Creez sacul pentru tipul de jucator basic atunci cand acesta nu
     * este serif.
     * ->Impart cartile din mana jucatorului in doua : intr-o parte am
     * cartile legale, iar in cealalta am cartile ilegale
     * ->Verific size-ul partii cu cartile legale si aplic strategia
     * sa de joc ca si comerciant
     * ->Strategia de joc este urmatoarea:
     *          -daca are carti legale,acesta cauta cartea cu frecventa cea
     *        mai mare, apoi dupa profit si apoi dupa id
     *          -daca nu are carti legale,acesta cauta prin cartile ilegale
     *        cartea cu cel mai mare profit si o pune in sac
     * @param player
     * <h1>createBasicBag</h1>
     */
    public void createBasicBag(final Player player) {
        List<Goods> legalGoods = new ArrayList<>();
        List<Goods> illegalGoods = new ArrayList<>();
        GoodsComparator goodsComparator = new GoodsComparator();
        Collections.sort(player.cardsInHand, goodsComparator);

        for (int i = 0; i < player.cardsInHand.size(); i++) {
            if (player.cardsInHand.get(i).getType().equals(GoodsType.Legal)) {
                legalGoods.add(player.cardsInHand.get(i));
            }
        }
        for (int i = 0; i < player.cardsInHand.size(); i++) {
            if (player.cardsInHand.get(i).getType().equals(GoodsType.Illegal)) {
                illegalGoods.add(player.cardsInHand.get(i));
            }
        }

        if (legalGoods.size() > 0) {
           int countProfit = -1;
           int countId = 0;
           int countIndex = -1;
            for (int i = 0; i < legalGoods.size(); i++) {
                legalGoods.get(i).set0FrequencyCards();
            }
            for (int i = 0; i < legalGoods.size(); i++) {
                legalGoods.get(i).incFrequencyCards();
            }
            for (int i = 0; i < legalGoods.size(); i++) {
                if (countProfit < legalGoods.get(i).getFrequencyCards()) {
                    countProfit = legalGoods.get(i).getFrequencyCards();
                    countId = legalGoods.get(i).getId();
                    countIndex = i;
                } else if (countProfit == legalGoods.get(i).getFrequencyCards()) {
                    if (legalGoods.get(countIndex).getProfit() < legalGoods.get(i).getProfit()) {
                        countId = legalGoods.get(i).getId();
                    }
                }
            }
            for (int i = 0; i < legalGoods.size(); i++) {
                if (countId == legalGoods.get(i).getId()) {
                    player.bag.add(legalGoods.get(i));
                }
            }
            player.goodsDeclared.add(0, GoodsFactory.getInstance().getGoodsById(countId));
        } else if (legalGoods.size() == 0) {
            if (illegalGoods.size() > 0) {
                int countProfit = 0;
                int countIndex = -1;
                for (int i = 0; i < illegalGoods.size(); i++) {
                    if (countProfit < illegalGoods.get(i).getProfit()) {
                        countProfit = illegalGoods.get(i).getProfit();
                        countIndex = i;
                    }
                }
                if (countIndex >= 0) {
                    player.bag.add(illegalGoods.get(countIndex));
                }
            }
            player.goodsDeclared.add(0, GoodsFactory.getInstance().getGoodsById(0));
        }
        player.cardsInHand.clear();
    }

    /**
     * <h1>IMPLEMENT OF GREEDY BAG.</h1>
     * ->Ca si la basic, separ cartile.
     * ->Acum,la aceasta strategie,verific daca are sau nu carti ilegale
     * ->Daca nu are carti ilegale,aplic strategia de la basic(apelez metoda
     * createBasicBag)
     * ->Daca are carti ilegale, calculez frecventele la fiecare,caut
     * cartea cu frecventa cea mai mare,iar in caz de egalitate,caut
     * dupa profit,apoi dupa id
     * @param player cu modificarile sale
     * <h1>createGreedyBag</h1>
     */
    public  void  createGreedyBag(final Player player) {
        List<Goods> greedyLegalGoods = new ArrayList<>();
        List<Goods> greedyIllegalGoods = new ArrayList<>();
        GoodsComparator goodsComparator = new GoodsComparator();
        Collections.sort(player.cardsInHand, goodsComparator);
        //separ cartile din mana in 2 parti:
        //prima parte cu cele legale
        for (int i = 0; i < player.cardsInHand.size(); i++) {
            if (player.cardsInHand.get(i).getType().equals(GoodsType.Legal)) {
                greedyLegalGoods.add(player.cardsInHand.get(i));
            }
        }
        //a doua parte cu cele ilegale
        for (int i = 0; i < player.cardsInHand.size(); i++) {
            if (player.cardsInHand.get(i).getType().equals(GoodsType.Illegal)) {
                greedyIllegalGoods.add(player.cardsInHand.get(i));
            }
        }

        if (greedyIllegalGoods.size() > 0) {
           if (greedyLegalGoods.size() > 0) {
               int countProfit = -1;
               int countId = -1;
               int countIndex = -1;
               //resetez frecventele cartilor din mana
               for (int i = 0; i < greedyLegalGoods.size(); i++) {
                   greedyLegalGoods.get(i).set0FrequencyCards();
               }
               //setez iar frecventele
               for (int i = 0; i < greedyLegalGoods.size(); i++) {
                   greedyLegalGoods.get(i).incFrequencyCards();
               }
               //caut bunul cu cel mai mare profit
               //daca profitul este egal, il caut pe cel cu
               //indexul mai mare
               for (int i = 0; i < greedyLegalGoods.size(); i++) {
                   if (countProfit < greedyLegalGoods.get(i).getFrequencyCards()) {
                       countProfit = greedyLegalGoods.get(i).getFrequencyCards();
                       countId = greedyLegalGoods.get(i).getId();
                       countIndex = i;
                   } else if (countProfit == greedyLegalGoods.get(i).getFrequencyCards()) {
                       if (greedyLegalGoods.get(countIndex).getProfit() < greedyLegalGoods.get(i).getProfit()) {
                           countId = greedyLegalGoods.get(i).getId();
                       }
                   }
               }
               for (int i = 0; i < greedyLegalGoods.size(); i++) {
                   if (countId == greedyLegalGoods.get(i).getId()) {
                       player.bag.add(greedyLegalGoods.get(i));
                   }
               }
               //declar bunul pus in bag
               player.goodsDeclared.add(0, GoodsFactory.getInstance().getGoodsById(countId));
           }
           //daca sac-ul inca nu este plin(are mai putin de 8 bunuri)
            if (player.bag.size() < Constants.MAXGOODSINBAG) {
                int countProfit2 = 0;
                int countIndex2 = -1;
                //caut bunul cu profitul cel mai mare
                for (int i = 0; i < greedyIllegalGoods.size(); i++) {
                    if (countProfit2 < greedyIllegalGoods.get(i).getProfit()) {
                        countProfit2 = greedyIllegalGoods.get(i).getProfit();
                        countIndex2 = i;
                    }
                }
                if (countIndex2 >= 0) {
                    player.bag.add(greedyIllegalGoods.get(countIndex2));
                    //declar ca fiind mere
                    player.goodsDeclared.add(0, GoodsFactory.getInstance().getGoodsById(0));
                }
            }
            player.bribery = 0;

            player.cardsInHand.clear();
        } else if (greedyIllegalGoods.size() <= 0) {
            //in caz ca nu are carti illegale,acesta aplica stategia basic
            createBasicBag(player);
        }
    }

    /**
     * <h1> IMPLEMENT BAG OF BRIBE BAG.</h1>
     * Ca si la celelalte doua strategii,separ cartile in doua parti.
     * Daca nu are carti ilegale, apelez metoda createBasicBag pentru a
     * folosi strategia lui basic
     * Daca are carti ilegale, contorizez cate carti ilegale am,
     * si apoi in functie de acestea jucatorul joaca astfel :
     *     - pentru 0 carti,apeleaza create basic
     *     - pentru 1 sau 2 carti, acesta,daca are bani, da mita 5 coins-uri
     *    -  pentru mai mult de 2 carti, acesta da mita 10 coins-uri
     * In sac caut cartea cu profitul cel mai mare, in caz de egalitate
     * cu id-ul mai mare, si pun toate cartile de acest timp in sac
     * @param player
     * <h1>createBribeBag</h1>
     */
    public void createBribeBag(final Player player) {
        List<Goods> bribeLegalGoods = new ArrayList<>();
        List<Goods> bribeIllegalGoods = new ArrayList<>();
        GoodsComparator goodsComparator = new GoodsComparator();
        Collections.sort(player.cardsInHand, goodsComparator);
        //impart cartile din mana in 2 parti
        for (int i = 0; i < player.cardsInHand.size(); i++) {
            if (player.cardsInHand.get(i).getType().equals(GoodsType.Legal)) {
                bribeLegalGoods.add(player.cardsInHand.get(i));
            }
        }
        for (int i = 0; i < player.cardsInHand.size(); i++) {
            if (player.cardsInHand.get(i).getType().equals(GoodsType.Illegal)) {
                bribeIllegalGoods.add(player.cardsInHand.get(i));
            }
        }
        for (int i = 0; i < bribeIllegalGoods.size(); i++) {
            if (player.bag.size() == Constants.MAXGOODSINBAG) {
                break;
            }
            player.bag.add(bribeIllegalGoods.get(i));
        }
        if (bribeIllegalGoods.size() > 0) {
            int countIllegal = 0;
            for (int i = 0; i < player.bag.size(); i++) {
                if (player.bag.get(i).getType() == GoodsType.Illegal) {
                    countIllegal++;
                }
            }
            int coins = player.getPlayerCoins();
            if (countIllegal == Constants.COUNTILLEGAL0) {
                createBasicBag(player);
                return;
            }
            if (countIllegal == Constants.COUNTILLEGAL1
                    || countIllegal == Constants.COUNTILLEGAL2) {
                if (coins > Constants.CASEMINBRIBECOINS) {
                    player.bribery = Constants.CASEMINBRIBECOINS;
                } else {
                    createBasicBag(player);
                    return;
                }
                player.setPlayerCoin(player.getPlayerCoins() - player.bribery);
            }
            if (countIllegal > Constants.COUNTILLEGAL2) {
                if (coins > Constants.CASE2BRIBECOINS) {
                    player.bribery = Constants.CASE2BRIBECOINS;
                } else {
                    createBasicBag(player);
                    return;
                }
                player.setPlayerCoin(player.getPlayerCoins() - player.bribery);
            }
            player.goodsDeclared.add(0, GoodsFactory.getInstance().getGoodsById(0));
            if (player.bag.size() < Constants.MAXGOODSINBAG) {
                int countProfit = 0;
                int countId = 0;
                int countFreq = 0;
                if (bribeLegalGoods.size() > 0) {
                    for (int i = 0; i < bribeLegalGoods.size(); i++) {
                        bribeLegalGoods.get(i).set0FrequencyCards();
                    }
                    for (int i = 0; i < bribeLegalGoods.size(); i++) {
                        bribeLegalGoods.get(i).incFrequencyCards();
                    }
                    for (int i = 0; i < bribeLegalGoods.size(); i++) {
                        if (countProfit < bribeLegalGoods.get(i).getProfit()) {
                            countProfit = bribeLegalGoods.get(i).getProfit();
                            countId = bribeLegalGoods.get(i).getId();
                        } else if (countProfit == bribeLegalGoods.get(i).getProfit()) {
                            if (countId < bribeLegalGoods.get(i).getId()) {
                                countId = bribeLegalGoods.get(i).getId();
                            }
                        }
                    }
                    for (int i = 0; i < bribeLegalGoods.size(); i++) {
                        if (countId == bribeLegalGoods.get(i).getId()) {
                            player.bag.add(bribeLegalGoods.get(i));
                        }
                    }
                    player.goodsDeclared.add(0, GoodsFactory.getInstance().getGoodsById(countId));
                }
            }
        } else if (bribeIllegalGoods.size() == 0) {
            createBasicBag(player);
        }
    }

}
