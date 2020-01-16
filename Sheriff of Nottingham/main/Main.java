package com.tema1.main;

import com.tema1.goods.Goods;
import com.tema1.goods.GoodsFactory;
import com.tema1.round.Round;

import java.util.ArrayList;
import java.util.List;

public final class Main {
    private Main() {
        // just to trick checkstyle
    }

    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0], args[1]);
        GameInput gameInput = gameInputLoader.load();
        //TODO implement homework logic
        List<String> playersOrder = gameInput.getPlayerNames();
        int numberOfRounds = gameInput.getRounds();
        int numberOfPlayers = playersOrder.size();
        //citesc id-urile cartilor
        List<Integer> cards = gameInput.getAssetIds();
        List<Goods> goods = new ArrayList<>();
        //trec din id-uri in bunuri
        //cu ajutorul lui GoodsFactory,prin id,iau toate datele despre acel bun
        for (int i = 0; i < cards.size(); i++) {
            goods.add(GoodsFactory.getInstance().getGoodsById(cards.get(i)));
        }
        Round rounds = new Round();
        rounds.setNumberOfPlayers(numberOfPlayers);
        rounds.setNumberOfRounds(numberOfRounds);
        //apelez metoda care creeaza jocul
        rounds.createTheGame(playersOrder, goods);
    }
}
