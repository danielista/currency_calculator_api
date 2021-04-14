package sk.martinek.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sk.martinek.api.ApiRequest;
import sk.martinek.api.Currency;
import sk.martinek.api.MediumApi;
import sk.martinek.calc.CalcRates;
import sk.martinek.database.Database;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Currency cc = new Currency();






        Label fromLabel = new Label("From ");
        Label toLabel = new Label("To ");
        Label resultLabel = new Label();
        resultLabel.setText("...");
        Label resultConversionLabel = new Label();
        resultConversionLabel.setText("...");


        ComboBox<String> fromCurrencies = new ComboBox<>();
        fromCurrencies.setEditable(false);
        fromCurrencies.getItems().addAll("USD","EUR","CZK","GBP");
        fromCurrencies.setPromptText("Currency");
        fromCurrencies.setPrefWidth(100);
        ComboBox<String> toCurrencies = new ComboBox<>();
        toCurrencies.setEditable(false);
        toCurrencies.getItems().addAll("USD","EUR","CZK","GBP");
        toCurrencies.setPromptText("Currency");
        toCurrencies.setPrefWidth(100);

        TextField from = new TextField();
        from.setPromptText("1");
        from.setPrefWidth(100);

                  Button calculate = new Button();
                    calculate.setText("CONVERT");
                    calculate.setPrefWidth(70);
                    calculate.setStyle("-fx-background-color: green; \n" +
                            "-fx-text-fill: white; ");
                    calculate.setOnAction(event -> {
                            System.out.println("say hello :D Converting...");
                            String fromCurr = fromCurrencies.getValue();
                            String toCurr = toCurrencies.getValue();
                            try {
                                Double resultik = cc.convertorApi(fromCurr,toCurr);
                                resultLabel.setText("Kurz: " + resultik.toString());

                                Double kolko = Double.parseDouble(from.getText().trim());
                                System.out.println("kolko:  "+kolko+ " "+ fromCurr);

                                kolko = kolko * resultik;
                                String vysledok = String.valueOf(kolko );

                                resultConversionLabel.setText("="+vysledok + " "+toCurr);
                                System.out.println("= "+vysledok + " "+toCurr);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    });




        GridPane middle = new GridPane();
        middle.setMinSize(400, 200);
        //middle.setPadding(new Insets(10, 10, 10, 10));
        middle.setVgap(5);
        middle.setHgap(5);
        middle.setAlignment(Pos.CENTER);


        middle.add(fromLabel, 0, 0);
        middle.add(toLabel, 0, 1);
        middle.add(from, 2, 0);

        middle.add(fromCurrencies,1,0);
        middle.add(toCurrencies,1,1);

        middle.add(calculate,1,2);
        middle.add(resultLabel,1,4);
        middle.add(resultConversionLabel,2,4);


        BorderPane BPane = new BorderPane(middle);
        BPane.setTop(new Label("CONVERTOOOR 2021"));


        primaryStage.setTitle("Currency Convertor by Daniel Martinek using free API");
        primaryStage.setScene(new Scene(BPane, 400, 200));
        primaryStage.show();

    }


    public static void main(String[] args) throws IOException {
        //launch(args);
        Database db = new Database();
        db.testMongo();
/*
        Set<String> set = new HashSet<>();
        set.add("USD");
        set.add("BTC");

        MediumApi quest = new MediumApi();
        Map map = quest.getExchangeRates(set);

        System.out.println(map.toString());

        Currency cc = new Currency();
        //  System.out.println(cc.apiCC("CZK"));
        //  System.out.println(cc.convertorApi("EUR","EUR"));

        */
    }
}
