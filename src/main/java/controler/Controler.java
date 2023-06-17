package controler;

import javafx.stage.FileChooser;
import model.Quiz;
import model.QuizException;
import model.Quizfrage;
import view.MyPane;
import view.QuizDialog;

import java.io.File;
import java.io.Serializable;
import java.util.Optional;

public class Controler implements Serializable {
    public Quiz model;
    public MyPane view;

    public Controler(MyPane myPane, Quiz model) {
        this.model = model;
        this.view = myPane;
    }


    public void erfasse() {
        QuizDialog qd = new QuizDialog();
        Optional<Quizfrage> op = qd.showAndWait();

        if (op.isPresent()){
            model.eintragen(qd.getResult()); //f
            System.out.println("Test");
        }
    }

    public void save(){
        FileChooser fc  = new FileChooser();
        File f = fc.showSaveDialog(view.getScene().getWindow());
        try {
            if(f == null){
                throw new QuizException("f ist null");
            }
            model.speichern(f);
        } catch (Exception e){
            throw new QuizException(e.getMessage());
        }
    }

    public void laden(){
        FileChooser fc  = new FileChooser();
        File f = fc.showOpenDialog(view.getScene().getWindow());
        try {
            if (f == null){
                throw new QuizException("f ist null");
            }
            model.laden(f);
        } catch (Exception e){
            throw new QuizException(e.getMessage());
        }
    }

    public void del(Quizfrage q){
        try{
            model.loeschen(q);
        } catch (Exception e){
            throw new QuizException(e.getMessage());
        }
    }


    public void sorti() {
        model.getFragen().sort((x, y )-> x.getSchwierigkeit().compareTo(y.getSchwierigkeit()));
    }
}
