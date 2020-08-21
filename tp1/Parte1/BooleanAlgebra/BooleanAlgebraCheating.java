import java.util.Scanner;

public class BooleanAlgebraCheating
{
    public static String evaluateExpression(String expression)
    {
        while (expression.length() > 1)
        {
            expression = expression.replace("not(0)", "1");
            expression = expression.replace("not(1)", "0");

            expression = expression.replace("or(1)", "1");
            expression = expression.replace("or(0)", "0");
            expression = expression.replace("or(0,0", "or(0");
            expression = expression.replace("or(0,1", "or(1");
            expression = expression.replace("or(1,0", "or(1");
            expression = expression.replace("or(1,1", "or(1");

            expression = expression.replace("and(1)", "1");
            expression = expression.replace("and(0)", "0");
            expression = expression.replace("and(0,0", "and(0");
            expression = expression.replace("and(0,1", "and(0");
            expression = expression.replace("and(1,0", "and(0");
            expression = expression.replace("and(1,1", "and(1");
        }

        return expression;
    }

    public static void main(String[] args)
    {
        Scanner myscan = new Scanner(System.in);
        String line = myscan.nextLine();

        while (line.charAt(0) != '0')
        {
            try (Scanner scan = new Scanner(line))
            {
                int numOperands = scan.nextInt();
                int[] operands = new int[numOperands];
    
                for (int i = 0; i < numOperands; i++)
                    operands[i] = scan.nextInt();
    
                String expression = scan.nextLine()
                    .replace(" ", "")
                    .replace("A", "" + operands[0])
                    .replace("B", "" + operands[1])
                    .replace("C", "" + operands[2]);
    
                System.out.println(evaluateExpression(expression));
            }

            line = myscan.nextLine();
        }

        myscan.close();
    }
}
