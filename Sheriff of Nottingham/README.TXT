-----MAIN----
->Citesc cartile si numarul de playeri si apelez metoda care "imi creeaza" jocul.

-ROUND-
->Prin 3 for-uri trec prin cene n runde citite din fisier si m subrunde = numarul 
de playeri
->Initializez fiecare jucator cu datele din fisier, coins-urile initiale
->Setez tipul jucatorului in fiecare runda(Serif sau comerciant), ii creez sacul
in caz ca acesta este comerciant, iar daca acesta nu este comerciant, ii aplic
strategia de serif
->Calculez profiutl pentru fiecare bun, adaug coinsurile care i-au mai ramas
->Calculez scorul si printez rezultatele

-CreateBag-
->Cu ajutorul a 3 metode,adaug in sacul fiecarui jucator bunuri in functie
de strategia sa.

-InspectBag-
->Cu cele 3 metode, pentru fiecare dintre jucatori cand respectivii sunt serifi,
controlez bag-urile celorlati playeri.

-ProfitCalculator-
->Parcugand bunurile de pe taraba ale fiecarui jucator, verific daca acesta a
adus bunuri ilegale si adaug profitul si bonus lor.
->Apoi in functie de ce bunuri legale adusese si de bonusurile aduse de cele
ilegale, calculez profitul legal.

-GiveKingQueenBonus-
->Parcurg fiecare jucator si calculez frecventele cartilor si in functie de rezultat,
un anumit jucator poate sa primeasca bonusurile aferente.

-PlayerComparator-
->Compar cei doi jucatori in functie de score si de id si returnez un indice pentru
a sti daca acestea trebuie interschimbate

-GoodsComparator-
->Compar cele doua bunuri in functie de frecventa,profit si id si returnez un indice
pentru a stii daca trebuie interschimbate

-Player-
->Retine fiecare detaliu despre un jucator
->Cu metoda countFreq controrizez frecventa unui tip de carti din mana
->Cu metoda CountFreqOnTable contorizez de cate ori apare un tip de obiect pe taraba