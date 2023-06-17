package view;

import controler.Controler;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import model.Quiz;
import model.Quizfrage;

import java.io.Serializable;
import java.util.EventListener;

public class MyPane extends BorderPane implements Serializable {

    private Quiz model;
    private Controler con;

    private TextArea ta;
    private ListView<Quizfrage> lv;

    public MyPane(){
        model = new Quiz();
        con = new Controler(this, model);
        init();
    }

    public void init(){
        MenuBar mb = new MenuBar();

        Menu m1 = new Menu("Quzifrage");
        MenuItem er = new MenuItem("Erfassen");
        MenuItem loe = new MenuItem("Löschen");
        m1.getItems().addAll(er, loe);

        Menu m2 =new Menu("Quiz");
        MenuItem spe = new MenuItem("Speichern");
        MenuItem lad = new MenuItem("Laden");
        MenuItem sort =new MenuItem("Sortieren");
        m2.getItems().addAll(spe,lad, sort);

        mb.getMenus().addAll(m1, m2);

        lv = new ListView<>(model.getFragen());
        ta = new TextArea();
         // ich klicke auf eine Zeile in der LV und die Methode ausgewählt wird ausgeführt:
        lv.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Quizfrage> observable, Quizfrage oldValue, Quizfrage newValue)-> ausgewaehlt(newValue));

        this.setTop(mb);
        this.setCenter(lv);
        this.setBottom(ta);

        er.setOnAction(event ->{
            con.erfasse();
        });

        spe.setOnAction(event ->{
            con.save();
        });

        lad.setOnAction(event ->{
            con.laden();
        });

        loe.setOnAction(event ->{
            con.del(getausgewaehltefrage());
        });

        sort.setOnAction(event ->{
            con.sorti();
        });
    }

    public void ausgewaehlt(Quizfrage q){
        if(q!= null){
            ta.setText(q.toStringDetails());
        }
        else{
            ta.setText(" ");
        }
    }

    public Quizfrage getausgewaehltefrage(){
        return lv.getSelectionModel().getSelectedItem();
    }

    public void alert(String titel, String header, String content){
       Alert x = new Alert(Alert.AlertType.INFORMATION);
       x.setTitle(titel);
       x.setHeaderText(header);
       x.setContentText(content);
       x.show();
    }
}
