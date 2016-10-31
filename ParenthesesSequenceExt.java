package seminar1;

import seminar1.collections.ArrayStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. пустая строка — правильная скобочная последовательность;
 * 2. правильная скобочная последовательность,
 *      взятая в скобки одного типа — правильная скобочная последовательность;
 * 3. правильная скобочная последовательность,
 *      к которой приписана слева или справа правильная скобочная последовательность
 *      — тоже правильная скобочная последовательность.
 */
public class ParenthesesSequenceExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_BRACE    = '}';
    private static final char LEFT_BRACKET   = '[';
    private static final char RIGHT_BRACKET  = ']';

    // sequence = "()()" | "(({}[]))[[[" | "{}" | ...
    private static boolean isBalanced(String sequence) {
        char[] st = new char[sequence.length()];
        int size = 0;
        for (int i=0;i<sequence.length();i++)
        {
            if (sequence.charAt(i)==LEFT_BRACE||sequence.charAt(i)==LEFT_BRACKET||sequence.charAt(i)==LEFT_PAREN)
            {
                st[size++] =sequence.charAt(i);
            }
            else
            {
                if (--size<0)
                {
                    return false;
                }
                char c = st[size];
                if (c==LEFT_PAREN && sequence.charAt(i) != RIGHT_PAREN)
                {
                    return false;
                }
                if (c==LEFT_BRACE && sequence.charAt(i) != RIGHT_BRACE)
                {
                    return false;
                }
                if (c==LEFT_BRACKET && sequence.charAt(i) != RIGHT_BRACKET)
                {
                    return false;
                }
            }
        }
        if (size==0)
        {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(isBalanced(sequence) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
