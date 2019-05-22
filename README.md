# JobPlanner
### Toevoegen aan IntelliJ
Om dit project toe te voegen aan IntelliJ, moet je op 'Check out from Version Control' klikken
bij het beginscherm van IntelliJ. Vervolgens kies je voor 'Git'. 

Kies dan de gewenste map waarin het project moet naar gekopieerd worden en klik op 'Clone'.
Nadat de bestanden zijn gekopieerd, zal een nieuw venster verschijnen. Hierin moet eigenlijk
niets worden aangepast, klik dus gewoon telkens op 'Next'. 

Wanneer het project is aangemaakt, zijn ook een aantal bestanden aangemaakt voor de
configuraties van de bestandenindeling van het project. Deze moeten niet naar GitHub worden
gekopieerd, dus klik onderaan op 'Version Control'. Hier staan de 'Unversioned Files' welke
genegeerd moeten worden. Dit doe je door er rechts op te klikken en 'Ignore' te selecteren.
De bestanden in de map '.idea' mogen allemaal genegeerd worden. Ook alle bestanden met een
.iml type mogen genegeerd worden. Voor het .iml bestand zal je merken dat dit niet direct
verdwijnt, maar wees geduldig, na een 20-tal seconden is het verdwenen uit de 'Unversioned
files' lijst.

### Werken met GitHub in IntelliJ
Om aanpassingen aan de bestanden op te slaan, klik dan op het groene vinkje rechtsboven,
welke een representatie is voor 'Commit'. Hierbij moet ook een korte en bondige boodschap bij
worden gegeven die zegt wat er verandert is. Als dan de 'Commit' knop wordt aangeklikt,
worden de veranderingen lokaal opgeslagen.

Als je dan uiteindelijk tevreden bent met de veranderingen, kan je deze pushen naar de repository
door naar 'VCS -> Git -> Push' te gaan. Je kan ook commit'en en push'en in 1 stap door in het
commit venster op het dropdown pijltje naast 'Commit' te klikken en 'Commit & Push' te selecteren.

### Maven
Aan dit project is een Maven framework toegevoegd. Dit maakt het makkelijker om dependencies toe
te voegen. Het enige dat je moet doen is de code voor een dependency in het pom.xml bestand zetten.
De code kan er als volgt uitzien:
````xml
<!-- https://mvnrepository.com/artifact/com.jfoenix/jfoenix -->
<dependency>
    <groupId>com.jfoenix</groupId>
    <artifactId>jfoenix</artifactId>
    <version>1.4.0</version>
</dependency>
````
Deze code moet dan in het pom.xml bestand tussen <dependencies> tags staan. Dit is een voorbeeld
van JFoenix, wat extra knoppen toevoegd om te gebruiken in de GUI (en al is toegevoegd).

Maven maakt ook een aantal nieuwe mappen aan om code, afbeeldingen,... in te zetten. Gebruik
deze zeker, dit maakt alles ordelijker. In de map 'resources' kan je bv. extra mappen aanmaken
om een bepaald soort bestanden in te zetten (.fxml, .sql, .jpg, ...).
### Scene Builder
Op het internet kan je een programma vinden genaamd 'JavaFX SceneBuilder'. Dit programma
kan .fxml bestanden interpreteren en weergeven. Ook kan je met SceneBuilder objecten toevoegen,
vergroten en verkleinen, allerlei eigenschappen ervan wijzigen,... En alle wijzigingen worden
omgezet naar .fxml code, waarin je dan methode kan toewijzen die moeten worden uitgevoerd bij
bepaalde interacties.

Vanuit IntelliJ (of andere code editors als NetBeans) kan je een Scene Builder instellen om de
.fxml bestanden mee te bewerken. Dit doe je door naar 'File -> Settings -> Languages & Frameworks -> JavaFX'
te gaan en daar het pad van de executable in te geven. Nu kan je rechts klikken op een .fxml
bestand en onderaan op 'Open in SceneBuilder' te klikken.
