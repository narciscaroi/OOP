CAROI NARCIS-FLORIN, 323CD

Singleton -> Folosit in input/Map
Factory -> Folosit in input/PlayerFactory
Double Dispatch -> Folosit in input/Player mostenit de clasele din player/*
			unde am folosit "metoda" de "vizitare" specifica double 
			dispatch-ului

common/Constants -> Este clasa cu toate constantele de care am avut nevoie.

input/InputLoad -> Clasa in care citesc datele din fisier.

input/Input -> Clasa in care retin datele citite din fisier. In aceasta clasa
		retin toate datele citite din fisierul de in cu ajutorul clasei de 
		mai sus.


input/Map -> Clasa in care creez harta cu tipurile de locatii date din input.
		Citesc din fisier cate un string care reprezinta linia matricii mele
		de char-uri, parcurg string-ul si pun char cu char in matricea mea
		de char-uri.

input/Player -> Clasa abstracta care contine toate datele despre un erou,
		(numit de mine player la inceput :)) din greseala si nu am mai 
		schimbat), clasa care contine metodele de care am nevoie la 
		toate tipurile de eroi. Aici am metode abstracte pentru folosirea
		"double dispatch", dar si metodele in care fac deplasarea pe harta,
		verific daca player-ul trebuie sa primeasca DoT(damage over time),
		dar si verificarea daca acesta este mort.

input/PlayerFactory -> Clasa pe care am facut-o singleton si am folosit
				si Factory. Verific tipul playerului (R/K/P/W) si
				returnez un player de tipul unui clase din 
				player/* , clase care mostenesc clasa input/Player.

main/Main -> Clasa main, in care instantiez 3 obiecte si acelea sunt
		pentru citirea din fisier, creea efectiva a jocului si 
		pentru citire in fisier. Tot in aceasta clasa, imi fac 
		copii pentru toate datele de intrare si lucrez cu acelea.

player/Knight -> Aici am implementat toate metodele din Player si anume
			metoda in care verific cu cine se lupta si metodele in care
			calculez cat damage trebuie sa ii dea inamicului. In plus
			am mai creat o metoda care imi verifica daca playerul 
			trebuie sa primeasca lvlup.

player/Rogue & Pyromancer & Wizard -> Acelasi principiu ca la knight
						doar ca fiecare cu spell-urile lui, terenul
						lui in care primeste bonus, constantele lui,etc.


round/Round -> Clasa care, in sfarsit, imi creeaza jocul efectiv.
			Avand un for mare, care semnifica rundele jocului,
			mai am doua for-uri prin care parcurg lista de 
			playeri. In primul for mic, setez datele playerilor
			in fiecare runda (ex: tipul locului de pe harta pe
			care se afla, etc), iar in cel de-al doilea verific
			care playeri trebuie sa se lupte si apelez metodele
			de lupta. Fiind double dispatch, programul stie
			sa se duca automat pe metoda care este pentru 
			tipul de jucator cu care se lupta.

output/Output -> Parcurg lista de playeri, ii scriu in fisier tipul lui, apoi
			daca este mort, scriu "dead" si trec la urmatorul erou,daca
			este inca in viata scriu si level-ul, xp-ul, hp-ul,
			linia si coloana(teoretic pozitia pe harta).