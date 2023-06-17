# Vorbereitung_eines_Quizspiels
Zur Vorbereitung eines Quizspiels soll ein Programm zum Erfassen von Fragen und möglichen Antworten erstellt werden (noch nicht das Spiel selbst).

# Aufbau der Klassen:

*Quizfrage*:
+ ANZAHL_ANTWORTEN : int = 3 - frage: String
- antworten: String[]
- nummerRichtigeAntwort: int
- schwierigkeit: Schwierigkeit // enum

+ Quizfrage( … ) throws QuizException
+ … // weitere ev. notwendige Methoden
+ toString() : String
+ toStringDetails() : String
// Attribute und Methoden dieser Klasse soweit // hier aufgelistet inkl. Prüfungen vorhanden!

------------------------

*Quiz*:
- fragen : ArrayList<Quizfrage> // ev. Anpassung für ListView erforderlich

+ Quiz()
+ eintragen(qf : Quizfrage) : … oder throws …
+ loeschen( … ) : … oder throws …
+ speichern(dateiname : File) : throws …
+ laden(dateiname : File) : throws …
+ …
// weitere ev. notwendige Methoden (zur Abfrage von Texten / // Listen, zumm Suchen, Sortieren, …)

# Vorgaben:

frage … ein nicht leerer Text, darf niemals null oder ein Leerstring sein (in der Vorlage schon so realisiert).
• antworten … ein String-Array, je ein Feld für jede mögliche Antwort. Array-Größe = ANZAHL_ANTWORTEN. Hinweis: Wem die Variante mit einem Array unlösbar erscheint, kann als Notlösung auch zwei (oder drei) Attribute (antwort1, antwort2 … ) definieren – mit kleinem Punkteabzug. Das macht den Aufbau der GUI (Eingabefenster) jedoch unflexibler und erfodert dort mehr Code-Zeilen!
  
• nummerRichtigeAntwort … ein Wert zwischen 1 und ANZAHL_ANTWORTEN. 1, wenn die erste Antwort die richtige ist, zwei falls es die zweite ist, … .
  
• schwierigkeit … hier ist eine eigene Enumaration Schwierigkeit mit den Werten LEICHT, MITTEL und SCHWER zu definieren. Bis diese Klasse erstellt ist, compiliert Quizfrage nicht (= Syntaxfehler)! Hinweis: Wer das nicht kann, verwendet statt dessen z.B.: einen int Wert 1 bis 3 oder ein char ‘L’, ‘M’ und ‘S’.
  
• Quizfragen sollen nicht zweimal mit dem identen Text (frage) eingetragen werden können!
  
• speichern / laden: Es soll möglich sein, alle erfassten Quizfragen zu speichern bzw. wieder zu laden. Das kann mittels Serialisierung oder im Textformat erfolgen. Nur eine Variante muss implementiert werden.
  
• : … oder “throws …”: Tritt in einer Methode der Klasse Quiz ein Fehler auf, der dort nicht gelöst werden kann, dann ist entweder ein entsprechender Rückgabewert vorzusehen oder eine passende (eigene) Exception zu werfen. Bitte überlegen, welche Art der Rückmeldung bei den vier gekennzeichneten Methoden für die grafische Oberfläche sinnvoll ist bzw. wie dort darauf reagiert werden soll.
  
• Die Klasse QuizException ist selbst zu erstellen! Bis diese Klasse erstellt ist, compiliert Quizfrage nicht!
  
  
# Grafische Oberfläche

• MVC einhalten! Der Aufbau der Anwendung soll gemäß dem Model-View-Controller Prinzip erfolgen!
  
• Fehlermeldungen: Fehler werden mittels Exceptions oder entsprechenden Rückgabewerten aufgezeigt und im Controller behandelt (bzw. dort an die View Klasse weitergereicht), welche sie z.B. mit Hilfe eines Alert-Dialoges ausgibt. Dabei ist ein die Ursache beschreibender Text mit- bzw. auszugegeben.
  
• Hauptfenster: Angezeigt wird eine Menüleiste, eine Auflistung aller Quizfragen (ListView oder notfalls eine TextArea mit Schwierigkeit & Frage für alle Einträge) und eine Detailansicht (Frage, mögliche Antworten, Lösung, Schwierigkeitsgrad) der in der Liste gerade ausgewählten Quizfrage. Die Liste kann mittels Menü-Auswahl Sortieren nach Schwierigkeit & Alphabet sortiert werden.
  
• Menüleiste: folgenden Menüs stehen zur Verfügung: (Quizfrage) --> Erfassen, Löschen ; (Quiz) --> Speichern, Laden, Sortieren
  
• Speichern / Laden: Es öffnet sich ein FileChooser-Dialog, der Anwender wählt die betreffende Datei bzw. gibt einen Dateinamen ein und alle bisher erfassten Fragen werden gespeichert bzw. geladen (Aufruf entspr. Methoden im Model!).
  
• Erfassen: Es öffnet sich ein FX-Dialog-Fenster, in dem die Daten einer Frage eingegeben werden können. Dieser Dialog soll erst schließen, wenn die unter Punkt 1) beschriebenen Bedingungen eingehalten werden (→ mit Validierung). Im Controller wird dann die vom Dialog generierte Quizfrage ins Model übernommen, sofern noch keine idente Frage vorhanden ist (die Prüfung erfolgt dabei in der Hinzufügen-Methode des Models!) und die Anzeige des Hauptfensters aktualisiert sich. Hinweis: Wer den FX-Dialog nicht gelernt hat, kann auch eine Stage-implementieren, die geöffnet wird und mittels Callback die Daten an den Controller überträgt,… – allerdings mit Punkteabzug.
  
• Löschen: Die in der Liste ausgewählte Frage wird gelöscht. Ist kein Eintrag ausgewählt wird eine Fehlermeldung angezeigt.
  
• Sortieren: Sortiert werden soll nach Schwierigkeit und Text der Frage. Das ist durch einen Aufruf einer im Modell erstellten Sortiermethode zu bewältigen. Je nach Implementierung (z.B.: bei Verwenden einer TextArea) nicht vergessen die Anzeige zu aktualisieren! → Angezeigt werden damit in der ListView zuerst alle leichten Fragen, dann jene mit Schwierigkeit MITTEL, … jeweils alphabetisch aufsteigend sortiert. Details zum Vergleichen von emums siehe Vorlage.
  
