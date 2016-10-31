package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) ) ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class Solver {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN   = '(';
    private static final char RIGHT_PAREN  = ')';
    private static final char PLUS         = '+';
    private static final char MINUS        = '-';
    private static final char TIMES        = '*';
    private static final char DIVISION     = '/';
    private static double calculate(char operation,Double a, Double b)
    {
        Double result = (double)0;
        switch (operation)
        {
            case PLUS:
            {
                result = a+b;
                break;
            }
            case MINUS:
            {
                result = a-b;
                break;
            }
            case TIMES:
            {
                result = a*b;
                break;
            }
            case DIVISION:
            {
                result = a/b;
                break;
            }
        }
        return result;
    }
    private static double evaluate(String[] values) {
        char[] stO = new char[values.length];
        Double[] st = new Double[values.length];
        int size = 0;
        int sizeO = 0;
        for (int i=0;i<values.length;i++)
        {
            switch (values[i].charAt(0))
            {
                case LEFT_PAREN:
                {
                    continue;
                }
                case RIGHT_PAREN:
                {
                    Double a = st[--size];
                    Double b = st[--size];
                    st[size++] = calculate(stO[--sizeO],b,a);
                    break;
                }
                case PLUS:
                {
                    stO[sizeO++] = PLUS;
                    break;
                }
                case MINUS:
                {
                    stO[sizeO++] = MINUS;
                    break;
                }
                case  TIMES:
                {
                    stO[sizeO++] = TIMES;
                    break;
                }
                case DIVISION:
                {
                    stO[sizeO++] = DIVISION;
                    break;
                }
                default:
                {
                    st[size++] = Double.parseDouble(values[i]);
                    break;
                }
            }
        }
        return st[0];
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
