package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Quiz implements Serializable{

    public ObservableList<Quizfrage> fragen;

    public Quiz() {
        fragen =  FXCollections.observableArrayList(new ArrayList<Quizfrage>());
    }

    public void setFragen(ObservableList<Quizfrage> fragen) {
        this.fragen = fragen;
    }

    public ObservableList<Quizfrage> getFragen() {
        return fragen;
    }

    public void eintragen(Quizfrage x) throws QuizException{
       if (x == null || fragen.contains(x)){
           throw new QuizException("Diese frage gibt es bereits!");
       }
        fragen.add(x);
    }

    //Stringbuilder: Es funktioniert effizienter als der normale string man erspart sich zb. Stringane + "XY"
    public String getDetails(){
        StringBuilder x = new StringBuilder();
        for (Quizfrage q : fragen) {
            return x.toString();
        }
        return null;
    }

    public Quizfrage loeschen(Quizfrage c){
        Iterator<Quizfrage> x = fragen.iterator();

        while (x.hasNext()){
            Quizfrage g = (Quizfrage) x.next();
            if (c.equals(g)){
                x.remove();
                return g;
            }
        }
        return null;
    }

    public void speichern(File dateiname) throws QuizException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dateiname))){
            oos.writeObject(new ArrayList<Quizfrage>(fragen));
        } catch (IOException e) {
            throw new QuizException(e.getMessage());
        }

    }

    public void laden(File dateiname){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dateiname))) {
            ArrayList<Quizfrage> alQuizFrage = (ArrayList<Quizfrage>) ois.readObject();
            fragen.setAll(alQuizFrage);
        } catch (Exception e) {
            throw new QuizException(e.getMessage());
        }
    }



}
