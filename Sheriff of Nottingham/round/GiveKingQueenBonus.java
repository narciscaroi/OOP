package com.tema1.round;
import com.tema1.goods.Goods;
import com.tema1.players.Player;
import com.tema1.common.Constants;

import java.util.ArrayList;
import java.util.List;

public class GiveKingQueenBonus {
    private int maxFrequency = 0;
    private int secondFreq = 0;
    public int[] indexKingPlayer = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    public int[] indexKingGood = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    public int[] indexQuuenPlayer = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};;
    public int[] indexQueenGood = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    public List<Goods> kingBonus = new ArrayList<>(Constants.MAXIDXLEGALGOODS);
    public List<Integer> playerKingBonus = new ArrayList<>();

    public GiveKingQueenBonus() {
    }

    /**
     * <h1>PLAYERS WHO DESERVED TO GET KING/QUEEN BONUS.</h1>
     * Parcurg id-urile bunurilor si verific fiecare jucator daca are acel tip.
     * Cu un counter de maxFrecv contorizez frecventa maxima a unui bun,
     * iar player-ul care are are numar de bunuri primeste KING BONUS pentru
     * bunul respectiv.
     * Cu un couter auxiliar secondFrecv, retin primul numar mai mic de aparitii
     * fata de max frecv
     * Retin in vectori id-ul bunului si id-ul playerului
     * @param players
     */
    public void whoDeserveKingBonus(final List<Player> players) {
        for (int i = Constants.MINIDXLEGALGOODS; i <= Constants.MAXIDXLEGALGOODS; i++) {
            maxFrequency = 0;
            secondFreq = 0;
            for (int j = 0; j < players.size(); j++) {
                for (int k = 0; k < players.get(j).goodsOnTable.size(); k++) {
                    if (players.get(j).goodsOnTable.get(k).getId() == i) {
                       players.get(j).countFrequencyOnTable();
                       if (maxFrequency < players.get(j).goodsOnTable.get(k).
                               getFrequencyOnTable()) {
                           if (maxFrequency != 0) {
                               secondFreq = maxFrequency;
                               indexQuuenPlayer[i] = indexKingPlayer[i];
                               indexQueenGood[i] = indexKingGood[i];
                           }
                           maxFrequency = players.get(j).goodsOnTable.get(k).getFrequencyOnTable();
                           indexKingPlayer[i] = j;
                           indexKingGood[i] = i;
                           break;
                       }
                       if (secondFreq < players.get(j).goodsOnTable.get(k).getFrequencyOnTable()) {
                           secondFreq = players.get(j).goodsOnTable.get(k).getFrequencyOnTable();
                           indexQueenGood[i] = i;
                           indexQuuenPlayer[i] = j;
                           break;
                       }
                   }
                }
            }
        }
    }
}