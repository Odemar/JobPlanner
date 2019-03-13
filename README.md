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