obor:  
 **informační technologie**

název projektu:  
**farm**

**Kateřina Bendová**  
**Informační a komunikační technologie**  
**2025**
----
***OBSAH***

1. Cíl práce  
2. Popis hry  
3. Systém requirements   
4. Základní struktura  
5. Testovací data  
6. Uživatelská příručka  
7. Závěr  
8. Zdroje

----
1. **Cíl práce**

Cílem této hry bylo navrhnout a vytvořit fungující 2D počítačovou hru se simulací farmy. Pojala jsem to spíše jako rozšíření svých znalostí v oblasti 2D grafiky v programovacím jazyce Java. Naučilo mě to porozumět animacím, kolizím, principům kreslení a aktualizování herní scény. 

Hra farm simuluje jednoduchý farmářský svět, ve kterém si hráč může vyzkoušet jaké to je žít na farmě. Krmí zvířata, pěstuje pro ně potravu a musí obhospodařovat své území. Je to skvělý způsob jak na chvíli uniknout realitě. Hra je vhodná pro všechny věkové kategorie, každý si v ní najde to své.  
Do budoucna mám v plánu tuto hru ještě rozšířit a nabídnout tak uživateli větší zážitek ze hry.

2. **Popis hry**

Uživatel se může po herním poli pohybovat s hráčem farmáře. Jak na to se dozvíte v uživatelské příručce.   
Farmář má za úkol mít vždy v inventáři dostatek krmiva pro všechna zvířata. Po sklizni rostliny na poli se další vypěstuje přesně za 2 minuty. Každé zvíře má 6 životů. Když jejich životy klesnou na 2, tak se budou chtít nakrmit. Pokud nebude dostatek krmiva v inventáři a oni se nebudou moci najíst, tak umřou.

3. **Systém requirements** 

Hra byla vytvořena v programovacím jazyce Java, konkrétně ve verzi JDK 2024.3.3. Žádné další externí knihovny nejsou potřeba. Aplikaci lze spustit z příkazové řádky nebo v jakémkoli vývojovém prostředí podporujícím Javu.

4. **Základní struktura**

Projekt je vytvořen formou objektového programování. Mezi hlavní třídy patří třída GamePanel, Entity, Animal a Player. Třída Main je velice důležitou součástí kódu. Jedná se o třídu kde se celý program spouští.

5. **Testovací data**  
   

Fajn způsob jak program otestovat je samozřejmě naprogramovat UNIT testy. Já osobně jsem hru testovala v průběhu jejího vývoje hraním. Myslím si, že je to asi nejjednodušší a nejzábavnější způsob jak hru testovat.

6. **Uživatelská příručka**

Po spuštění programu na mě vyskočí nové okno. Na výběr budu mít ze 3 možností “new game” \-\> spustím novou hru, “load game” \-\> načtu již probíhající hru, “quit” \-\> celou hru ukončím. Pomocí kláves “W”, “S”, “šipka nahoru” nebo “šipka dolu” si vybírám další krok, pokud jsem s výběrem spokojený/á, tak výběr potvrdím stisknutím klávesnice “ENTER”. 

Pokud chci zahájit hru nebo v ní dále pokračovat, tak se mi načte hlavní herní pole. Po něm se pohybuji pomocí kláves “A” \= doleva, “D” \= doprava, “W” \= nahoru, “S” \= dolu. 

Ve hře je možné se teleportovat na jinou mapu.Toto místo je označené zvláštním políčkem, stačí po něm “přeběhnout”. Vrátím se stejným způsobem.

Pokud chci sebrat obilí stačí přijít na pole a stisknout “T”.

7. **Závěr**  
   

Hra **farm** mě hodně naučila. Faktem zůstává, že jsem si špatně uspořádala čas. Nezbylo mi ho tolik, kolik bych si představovala. Hru mám v plánu do budoucna rozšiřovat, takže toto není kompletní verze, ale je to vše co bylo v mých silách zvládnout.

Kdybych měla říct s čím jsem se nejvíce prala, tak to byla asi teleportace mezi mapami. Když už se zdálo, že vše funguje bez problémů, objevily se další komplikace.   
Stejně tak, jako předělávání kódu kvůli časové náročnosti. V těchto chvílích jsem vážně litovala toho, že jsem na doposud krásně fungující kód sáhla. 

Určitě mi závěrečná práce pomohla rozšířit své programovací znalosti a dovednosti. Mimo jiné jsem si uvědomila, že nechávat věci na poslední chvíli se moc nevyplatí.

8. **Zdroje**  
   

**Název playlistu**: *How to Make a 2D Game in Java*. In: **YouTube** \[online\]. \[cit. 2025-05-29\]. Dostupné z: [https://www.youtube.com/playlist?list=PL\_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq](https://www.youtube.com/playlist?list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq)

(nevyužila jsem vše, ale dalo mi to dobrý základ)

