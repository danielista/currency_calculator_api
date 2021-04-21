package sk.martinek.calc;

import sk.martinek.api.AllOtherCurrencies;
import sk.martinek.api.BitcoinApi;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CalcRates {

    BitcoinApi apiBTC = new BitcoinApi();
    AllOtherCurrencies apiConvertor = new AllOtherCurrencies();

    public String calculationBTCfromEUR( Double Eur ){
        Double Kurz = apiBTC.getRatesFromAPIServer();

        Double result = Kurz*Eur;

        NumberFormat nf = new DecimalFormat("0.00");
        String decimalString = nf.format(result);
        System.out.println(decimalString);

        System.out.println(result);
        return decimalString;
    }

    public String calculatorMultiExchange(Double Kolko, String from, String to) throws IOException {

        Double result = Kolko * apiConvertor.convertorApi(from,to);
        NumberFormat nf = new DecimalFormat("0.00");
        String decimalString = nf.format(result);
        System.out.println(decimalString);

        return decimalString;
    }


}
