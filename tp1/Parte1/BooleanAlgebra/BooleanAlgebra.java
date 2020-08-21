import java.util.Scanner;

public class BooleanAlgebra
{
    public static int indexOf(String find, String str, int start)
    {
        if (find.isEmpty()) return str.isEmpty() ? -1 : 0;

        int findStart = -1;
        int findIndex = -1;

        for (int i = start; findIndex != find.length() && i < str.length(); i++)
        {
            if (findStart != -1)
            {
                if (str.charAt(i) == find.charAt(findIndex)) findIndex++;

                else if (str.charAt(i) == find.charAt(0))
                {
                    findStart = i;
                    findIndex = 1;
                }

                else
                {
                    findStart = -1;
                    findIndex = -1;
                }
            }

            else if (str.charAt(i) == find.charAt(0))
            {
                findStart = i;
                findIndex = 1;
            }
        }

        return findIndex == find.length() ? findStart : -1;
    }

    public static String replace(String find, String replace, String str)
    {
        String newStr = "";
        int findStart = indexOf(find, str, 0);
        if (findStart == -1) return str;
        
        for (int i = 0; i < findStart; i++)
            newStr += str.charAt(i);

        newStr += replace;

        for (int i = findStart + find.length(); i < str.length(); i++)
            newStr += str.charAt(i);

        return indexOf(find, newStr, 0) != -1 ? replace(find, replace, newStr) : newStr;
    }

    public static String evaluateExpression(String expression)
    {
        while (expression.length() > 1)
        {
            expression = replace("not(0)", "1", expression);
            expression = replace("not(1)", "0", expression);

            expression = replace("or(1)", "1", expression);
            expression = replace("or(0)", "0", expression);
            expression = replace("or(0,0", "or(0", expression);
            expression = replace("or(0,1", "or(1", expression);
            expression = replace("or(1,0", "or(1", expression);
            expression = replace("or(1,1", "or(1", expression);

            expression = replace("and(1)", "1", expression);
            expression = replace("and(0)", "0", expression);
            expression = replace("and(0,0", "and(0", expression);
            expression = replace("and(0,1", "and(0", expression);
            expression = replace("and(1,0", "and(0", expression);
            expression = replace("and(1,1", "and(1", expression);
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
    
                String expression = scan.nextLine();
                expression = replace(" ", "", expression);
                expression = replace("A", "" + operands[0], expression);
                expression = replace("B", "" + operands[1], expression);
                expression = replace("C", "" + operands[2], expression);
    
                System.out.println(evaluateExpression(expression));
            }

            line = myscan.nextLine();
        }

        myscan.close();
    }
}
