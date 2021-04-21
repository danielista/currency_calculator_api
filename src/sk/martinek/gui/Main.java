package sk.martinek.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sk.martinek.api.AllOtherCurrencies;
import sk.martinek.calc.CalcRates;
import sk.martinek.database.Database;

import java.io.IOException;

public class Main extends Application {
    private Double resultik;
    private String fromCurr;
    private String toCurr;

    @Override
    public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        AllOtherCurrencies cc = new AllOtherCurrencies();
        Database db = new Database();
        CalcRates calculator = new CalcRates();





        Label fromLabel = new Label("From ");
        Label toLabel = new Label("To ");
        Label resultLabel = new Label();
        resultLabel.setText("...");
        Label vyslednaHodnota = new Label("výsledná hodnota");
        Label resultConversionLabel = new Label();
        resultConversionLabel.setText("...");
        resultConversionLabel.setStyle("-fx-background-color: black ; \n" +
                "-fx-text-fill: white;  \n " + "-fx-font-size: 30px;  ");

        ComboBox<String> fromCurrencies = new ComboBox<>();
        fromCurrencies.setEditable(false);
        fromCurrencies.getItems().addAll("USD","EUR","CZK","GBP");
        fromCurrencies.setPromptText("Currency");
        fromCurrencies.setPrefWidth(100);
        ComboBox<String> toCurrencies = new ComboBox<>();
        toCurrencies.setEditable(false);
        toCurrencies.getItems().addAll("USD","EUR","CZK","GBP","BTC");
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
                            fromCurr = fromCurrencies.getValue();
                            toCurr = toCurrencies.getValue();
                            Double kolko = Double.parseDouble(from.getText().trim());
                            System.out.println("kolko:  "+kolko+ " "+ fromCurr);

                            if(fromCurr.equals("EUR") && toCurr.equals("BTC")){

                                resultLabel.setText("Kurz: too much difficult");
                                String btcResult = calculator.calculationBTCfromEUR(kolko);
                                resultConversionLabel.setText(btcResult);
                                // saving data to MONGO
                                db.saveDataFromCalculator(fromCurr,kolko,toCurr,btcResult);
                            }else if ((!fromCurr.equals("EUR")) && toCurr.equals("BTC")){

                                resultConversionLabel.setText("Nepodporovaná konverzia");
                                resultConversionLabel.setStyle("-fx-background-color: red ; \n" +
                                        "-fx-text-fill: white;  \n " + "-fx-font-size: 15px;  ");
                            }else {
                                try {
                                    //kurz statement
                                    resultik = cc.convertorApi(fromCurr,toCurr);
                                    resultLabel.setText("Kurz: " + resultik.toString());

                                    //result statement
                                    String vysledok = calculator.calculatorMultiExchange(kolko,fromCurr,toCurr);
                                    resultConversionLabel.setText("="+vysledok + " "+toCurr);
                                    System.out.println("= "+vysledok + " "+toCurr);

                                    // saving data to MONGO
                                    db.saveDataFromCalculator(fromCurr,kolko,toCurr,vysledok);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
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
        middle.add(vyslednaHodnota,2,2);
        middle.add(resultLabel,1,4);
        middle.add(resultConversionLabel,2,4);


        BorderPane BPane = new BorderPane(middle);
        BPane.setTop(new Label("CONVERTOOOR 2021"));


        primaryStage.setTitle("Currency Convertor by Daniel Martinek using free API");
        primaryStage.setScene(new Scene(BPane, 400, 200));
        primaryStage.show();

    }


    public static void main(String[] args) throws IOException {
          launch(args);
       // Database db = new Database();
       // db.testMongo();
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
