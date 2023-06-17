package view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Quizfrage;
import model.Schwierigkeit;

import java.util.ArrayList;
import java.util.Arrays;

public class QuizDialog extends Dialog<Quizfrage> {
    private TextField fr = new TextField();
    private TextField aw = new TextField();
    private TextField ra = new TextField();
    private ComboBox<Schwierigkeit> sww = new ComboBox<>();

    public QuizDialog() {
        this.setTitle("Frage erstellen");
        this.setHeaderText("Bitte geben Sie eine frage mit Antworten ein:");
        this.getDialogPane().setContent(getBoderIems() );
        ButtonType go = new ButtonType("Eintragen", ButtonBar.ButtonData.OK_DONE);
        ButtonType stop = new ButtonType("Abbrechen", ButtonBar.ButtonData.CANCEL_CLOSE);
        this.getDialogPane().getButtonTypes().addAll(go, stop);

        for(Schwierigkeit e : Schwierigkeit.values()){
            sww.getItems().add(e);
        }


        final Button bterst = (Button) this.getDialogPane().lookupButton(go);
        bterst.addEventFilter(ActionEvent.ACTION, actionEvent -> {
            String[] parts=aw.getText().split(",");
            ArrayList<String> l=new ArrayList<>(Arrays.asList(parts));


            try {
                Quizfrage x = new Quizfrage(fr.getText(), l, Integer.parseInt(ra.getText()),sww.getValue());
                setResult(x);
            } catch (Exception e){
                actionEvent.consume();
                Alert aa = new Alert(Alert.AlertType.ERROR);
                aa.setTitle("Fehler bei der Eingabe");
                aa.setHeaderText("Bei der eingabe ist ein Fehler aufgetreten");
                aa.setContentText(e.getMessage());
                aa.show();
            }

        });

        this.setResultConverter(new Callback<ButtonType, Quizfrage>() {
            @Override
            public Quizfrage call(ButtonType buttonType) {
                return getResult();
            }
        });
    }

    private GridPane getBoderIems() {
        GridPane p = new GridPane();
        p.addRow(0, new Label("Frage: "), fr);
        p.addRow(1, new Label("Antworten (mit , trennen!): "), aw);
        p.addRow(2, new Label("Richtige Antworten: Beginnend bei 0"), ra);
        p.addRow(3, new Label("Schwierigkeit: "), sww);
        return p;
    }
}


