package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Quizfrage implements Serializable {

    public static int ANZAHL_ANTWORTEN = 3;

    private String frage;
    private ArrayList<String> antworten;
    private int nummerRichtigeAntwort;
    private Schwierigkeit schwierigkeit =  Schwierigkeit.MITTEL;

    public Quizfrage() throws QuizException {
        this("Was ist der fünfte Buchstabe im Alphabet?",
                new ArrayList<String>(Arrays.asList(new String[] {"A","E","G"})),
                2,
                Schwierigkeit.LEICHT);
    }

    public Quizfrage(String frage, ArrayList<String> antworten, int nummerRichtigeAntwort, Schwierigkeit schwierigkeit) throws QuizException {
        setFrage(frage);
        setAntworten(antworten);
        setNummerRichtigeAntwort(nummerRichtigeAntwort);
        setSchwierigkeit(schwierigkeit);
    }

    public String getFrage() { return frage; }
    public ArrayList<String> getAntworten() { return antworten; }
    public int getNummerRichtigeAntwort() { return nummerRichtigeAntwort; };
    public Schwierigkeit getSchwierigkeit() { return schwierigkeit; }


    public String getTextRichtigeAntwort() { return antworten.get(getNummerRichtigeAntwort()-1); }

    public void setFrage(String frage) throws QuizException {
        if (frage==null || frage.isEmpty())
        {
            throw new QuizException("Kein oder zu kurzer Fragetext!");
        }
        this.frage = frage;
    }

    public void setAntworten(ArrayList<String> antworten) throws QuizException {
        if (antworten==null || antworten.size()<ANZAHL_ANTWORTEN)
        {
            throw new QuizException("Zu wenige Antwortmoeglichkeiten!");
        }
        this.antworten = antworten;
    }

    public void setNummerRichtigeAntwort(int nummerRichtigeAntwort) throws QuizException {
        if (nummerRichtigeAntwort<1 || nummerRichtigeAntwort>ANZAHL_ANTWORTEN)
        {
            throw new QuizException("Es gibt keine " + nummerRichtigeAntwort + ". Antwortmöglichkeit!");
        }
        this.nummerRichtigeAntwort = nummerRichtigeAntwort;
    }

    public void setSchwierigkeit(Schwierigkeit schwierigkeit)
    {
        this.schwierigkeit = schwierigkeit;
    }


    @Override
    public String toString()
    {
        return schwierigkeit.toString().charAt(0) + ": " + frage;
    }


    public String toStringDetails() {
        String lS = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append("Quizfrage: ").append(lS);
        sb.append(frage).append(lS);
        sb.append("Antworten:").append(lS);
        for (int idx=0; idx<antworten.size(); idx++)
        {
            sb.append(idx+1).append(".: ").append(antworten.get(idx)).append(lS);
        }
        sb.append("richtige Antwort: ").append(nummerRichtigeAntwort).append(lS);
        sb.append("Schwierigkeit: ").append(schwierigkeit).append(lS);
        return sb.toString();
    }

    public static void main(String[] args)
    {
        try
        {
            Quizfrage q1 = new Quizfrage();
            Quizfrage q2 = new Quizfrage("Wie groß ist der ungefähre Umfang der Erde?",
                    new ArrayList<String>(Arrays.asList(new String[] {"30.000km","40.000km","50.000km"})),
                    2,
                    Schwierigkeit.MITTEL);

            System.out.println(q1.toString());
            System.out.println(q2.toStringDetails());

            // Wie kann man enums vergleichen?
            // Ganz einfach, mit der automatisch vorhanden compareTo()-Methode:
            System.out.println(q1.getSchwierigkeit() + " <-> " + q2.getSchwierigkeit() + ": " +
                    q1.getSchwierigkeit().compareTo(q2.getSchwierigkeit()));

            System.out.println(Schwierigkeit.MITTEL + " <-> " + Schwierigkeit.MITTEL + ": " +
                    Schwierigkeit.MITTEL.compareTo(Schwierigkeit.MITTEL));
            System.out.println(Schwierigkeit.SCHWER + " <-> " + Schwierigkeit.LEICHT + ": " +
                    Schwierigkeit.SCHWER.compareTo(Schwierigkeit.LEICHT));
        }
        catch (QuizException e)
        {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
}
