# OOP-spillprosjekt

### Beskrivelse:
RPG hvor du styrer en karakter i en 2-dimensjonal verden. 
Du vil ha mulighet til å gå rundt om i verden med WASD, og komme over forskjellige situasjoner hvor hver situasjon kan skille seg ut fra den forrige. Eksempel på dette vil være å svare på en tekstialog til turn-based kamp. Hvor mye forskjellig vi lager, vil være avhengig av tid vi får brukt på prosjektet.

### Javafx vs Java swing
#### Java swing JPanel
![Java swing JPanel canvas](https://github.com/Shamzaa/OOP-spillprosjekt/blob/master/java-swing-JPanel.png "Java swing JPanel gir fin og klar grafikk")

#### Javafx canvas
![Javafx canvas](https://github.com/Shamzaa/OOP-spillprosjekt/blob/master/javafx-canvas.png "Javafx canvas gir uønsket grafiske artifacts")


### krav
- 4:3 oppløsning (640:480)
- lite bugs
- kontroll med WASD
- skal forhåpenligvis aldri krasje
- skal støtte nivåer som ikke kodet (ikke som i sokoban-spillet vi har laget i øving 4)
   - JSON inneholder metadata om nivå, og informasjon om hver entity/sprite
   - et bilde (.png), som inneholder informasjon om hver rute

### utviklingsmetode
Iterativ:
- "engine" for å vise grafikk
- implementere maps/level
- entities
- player
- movement
- interacton
- battle

### TODO
- Tittel til spillet
- Lyd
  - Lydeffecter
  - Musikk
- Entity class
  - NPC class
    - Vendor
    - etc
  - Fighter class
  	- Player
  	- Hostile

- Level class
  - BattleScene class

  
- Game class
- Save/Load functionality

### Requirements
 - [Java json library](http://mvnrepository.com/artifact/org.json/json)
 
### Resources
 - [Undertale](http://store.steampowered.com/app/391540/), Toby Fox (musikk)
 - [Sprites](http://opengameart.org/content/antifareas-rpg-sprite-set-1-enlarged-w-transparent-background-fixed)
 - [Sprites](http://rpgmaker.net/forums/topics/9810/)
 - [Teksturer](https://facepunch.com/showthread.php?t=1213795)


