package controler;

import model.Quizfrage;

import java.util.Comparator;

public class Quizcom implements Comparator<Quizfrage> {

    @Override
    public int compare(Quizfrage o1, Quizfrage o2) {
        return o1.getSchwierigkeit().compareTo(o2.getSchwierigkeit());
    }
}
