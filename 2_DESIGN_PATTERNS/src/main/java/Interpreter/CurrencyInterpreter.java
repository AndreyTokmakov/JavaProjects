package Interpreter;

import java.util.Objects;

class CurrencyContext {
    private int input;
    private double output;
    public int getInput() {
        return input;
    }
    public void setInput(int input) {
        this.input = input;
    }
    public double getOutput() {
        return output;
    }
    public void setOutput(double output) {
        this.output = output;
    }
    public CurrencyContext(int in){
        this.input=in;
    }
}

abstract class CurrencyExpression {
    public abstract double usd(int input);

    public abstract double gbp(int input);

    public abstract double bdt(int input);

    public abstract double myr(int input);

    public void interpret(CurrencyContext context, String type){
        if (Objects.equals(type, "usd")){
            context.setOutput(usd(context.getInput()));
        } else if(Objects.equals(type, "bdt")){
            context.setOutput(bdt(context.getInput()));
        } else if(Objects.equals(type, "gbp")){
            context.setOutput(gbp(context.getInput()));
        } else if(Objects.equals(type, "myr")){
            context.setOutput(myr(context.getInput()));
        }
    }
}

class GBPConverter extends CurrencyExpression{
    public double usd(int input) {
        return input*1.6703;
    }
    public double gbp(int input) {
        return input;
    }
    public double bdt(int input) {
        return input*129.4726;
    }
    public double myr(int input) {
        return input*5.5160;
    }
}

class MYRConverter extends CurrencyExpression{
    public double usd(int input) {
        return input*0.3028;
    }
    public double gbp(int input) {
        return input*0.1813;
    }
    public double bdt(int input) {
        return input*23.4720;
    }
    public double myr(int input) {
        return input;
    }
}

class USDConverter extends CurrencyExpression{
    public double usd(int input) {
        return input;
    }
    public double gbp(int input) {
        return input*0.5987;
    }
    public double bdt(int input) {
        return input*77.5164;
    }
    public double myr(int input) {
        return input*3.3025;
    }
}

class BDTConverter extends CurrencyExpression{
    public double usd(int input) {
        return input*0.0129;
    }
    public double gbp(int input) {
        return input*0.0077;
    }
    public double bdt(int input) {
        return input;
    }
    public double myr(int input) {
        return input*0.0426;
    }
}

public class CurrencyInterpreter
{
    public static void main(String[] args)
    {
        System.out.println("We will convert BDT 1000 to other languages");
        CurrencyContext bdtCurrency = new CurrencyContext(1000);
        CurrencyExpression bdConvert = new BDTConverter();
        bdConvert.interpret(bdtCurrency, "usd");
        System.out.println("After conversion "+bdtCurrency.getOutput());
    }
}
