package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) )
 * ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * 1 + ( 2 + 3 ) * 4 * 5 = 101
 * 1 + 5 * 4 * 5 = 101
 * 1 + 5 * 20 = 101
 * 1 + 100 = 101
 * 20 / 4 = 5
 * (101 - 1) / 5 = 20
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class SolverExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN   = '(';
    private static final char RIGHT_PAREN  = ')';
    private static final char PLUS         = '+';
    private static final char MINUS        = '-';
    private static final char TIMES        = '*';
    private static final char DIVISION     = '/';
    private static double calculate(double a, double b, char operation)
    {
        double result = 0;
        switch (operation){
            case PLUS:{
                result = a+b;
                break;
            }
            case MINUS:{
                result = b-a;
                break;
            }
            case TIMES:{
                result = b*a;
                break;
            }
            case DIVISION:{
                result = b/a;
                break;
            }
    }
        return result;
    }
    private static double evaluate(String[] values) {
        char[] stO = new char[values.length];
        double[] stD = new double[values.length];
        int countO = 0;
        int countD = 0;
        for (int i=0; i<values.length;i++)
        {
            switch(values[i].charAt(0)){
                case LEFT_PAREN:{
                    stO[countO++] = LEFT_PAREN;
                    break;
                }
                case RIGHT_PAREN:{
                    while(stO[countO-1]!=LEFT_PAREN){
                        double a = stD[--countD];
                        double b = stD[--countD];
                        stD[countD++] = calculate(a,b,stO[--countO]);
                    }
                    countO--;
                    break;
                }
                case PLUS:{
                    if (countO-1>=0&&stO[countO-1]!=LEFT_PAREN)
                    {
                        double a = stD[--countD];
                        double b = stD[--countD];
                        stD[countD++] = calculate(a,b,stO[--countO]);
                        stO[countO++] = PLUS;
                    }
                    else
                    {
                        stO[countO++] = PLUS;
                    }
                    break;
                }
                case MINUS:{
                    if (countO-1>=0&&stO[countO-1]!=LEFT_PAREN) {
                        double a = stD[--countD];
                        double b = stD[--countD];
                        stD[countD++] = calculate(a,b,stO[--countO]);
                        stO[countO++] = MINUS;
                    }
                    else {
                        stO[countO++] = MINUS;
                    }
                    break;
                }
                case TIMES:{
                    if (countO-1>=0&&(stO[countO-1]==DIVISION||stO[countO-1]==TIMES)){
                        double a = stD[--countD];
                        double b = stD[--countD];
                        stD[countD++] = calculate(a,b,stO[--countO]);
                        stO[countO++] = TIMES;
                    }
                    else{
                        stO[countO++] = TIMES;
                    }
                    break;
                }
                case DIVISION: {
                    if (countO-1>=0&&(stO[countO - 1] == DIVISION || stO[countO - 1] == TIMES)) {
                        double a = stD[--countD];
                        double b = stD[--countD];
                        stD[countD++] = calculate(a, b, stO[--countO]);
                        stO[countO++] = DIVISION;
                    } else {
                        stO[countO++] = DIVISION;
                    }
                    break;
                }
                default:{
                    stD[countD++] = Integer.parseInt(values[i]);
                    break;
                }
            }
        }
        while (countD-1!=0){
            double a = stD[--countD];
            double b = stD[--countD];
            stD[countD++] = calculate(a, b, stO[--countO]);
        }
        return stD[0];
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
