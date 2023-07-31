package chap07;

public class Main {

    public static void main(String[] args) {
        ImpeCalculator impeCal = new ImpeCalculator();
        ExeTimeCalculator calculator = new ExeTimeCalculator(impeCal);
        long result = calculator.factorial(4);
        
        RecCalculator recCal = new RecCalculator();
        ExeTimeCalculator calculator2 = new ExeTimeCalculator(recCal);
        long result2 = calculator2.factorial(4);
    }
}
