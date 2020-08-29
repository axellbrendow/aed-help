import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Html
{
    public static String getHtml(String endereco)
    {
        URL url;
        InputStream is = null;
        String resp = "", line;

        try
        {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is)))
            {
                line = br.readLine();

                while (line != null)
                {
                    resp += line + "\n";
                    line = br.readLine();
                }
            }
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                }

                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return resp;
    }

    public static boolean isFim(String str)
    {
        return str.length() >= 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I'
                && str.charAt(2) == 'M';
    }

    /**
     * Encontra o índice de uma substring numa string.
     * 
     * @param substr Substring a ser procurada.
     * @param str String a ser percorrida.
     * @param start Índice inicial da busca.
     * 
     * @return -1 caso {@code substr} não esteja em {@code str}. Caso contrário,
     * retorna o índice onde {@code substr} começa em {@code str}.
     */
    public static int indexOf(String substr, String str, int start)
    {
        int substrIndex = 0;
        int index = -1;

        for (int i = start; substrIndex < substr.length() && i < str.length(); i++)
        {
            if (str.charAt(i) == substr.charAt(substrIndex))
            {
                if (substrIndex == 0)
                    index = i;

                substrIndex++;
            }

            else if (str.charAt(i) == substr.charAt(0))
            {
                index = i;
                substrIndex = 1;
            }

            else
            {
                index = -1;
                substrIndex = 0;
            }
        }

        return substrIndex == substr.length() ? index : -1;
    }

    /**
     * Conta quantas vezes {@code substr} está em {@code str}.
     * 
     * @param substr Substring a ser contada.
     * @param str String onde {@code substr} será procurada.
     * 
     * @return quantas vezes {@code substr} está em {@code str}. 
     */
    public static int countSubstr(String substr, String str)
    {
        int count = 0;
        int index = indexOf(substr, str, 0);

        while (index != -1)
        {
            count++;
            index = indexOf(substr, str, index + substr.length());
        }

        return count;
    }

    /**
     * Percorre {@code charsToCount} e, para cada caractere, conta quantas vezes
     * ele aparece em {@code str}, acumulando o resultado de cada caractere.
     * 
     * @param charsToCount Caracteres a serem contados.
     * @param str String a ser percorrida.
     * 
     * @return a soma das vezes que os caracteres apareceram.
     */
    public static int countChars(String charsToCount, String str)
    {
        int count = 0;

        for (int i = 0; i < charsToCount.length(); i++)
            count += countSubstr("" + charsToCount.charAt(i), str);

        return count;
    }

    public static void main(String[] args)
    {
        MyIO.setCharset("ISO-8859-1");
        // MyIO.setCharset("UTF-8");
        String nome = MyIO.readLine();
        String endereco;
        String html;
        final String consoantesMinusculas = "bcdfghjklmnpqrstvwxyz";

        while (!isFim(nome))
        {
            endereco = MyIO.readLine();
            html = getHtml(endereco);

            int numBrs = countSubstr("<br>", html);
            int numTables = countSubstr("<table>", html);

            MyIO.print("a(" + (countSubstr("a", html) - numTables) + ")");
            MyIO.print(" e(" + (countSubstr("e", html) - numTables) + ")");
            MyIO.print(" i(" + countSubstr("i", html) + ")");
            MyIO.print(" o(" + countSubstr("o", html) + ")");
            MyIO.print(" u(" + countSubstr("u", html) + ")");

            MyIO.print(" á(" + countSubstr("" + (char) 225, html) + ")"); // á
            MyIO.print(" é(" + countSubstr("" + (char) 233, html) + ")"); // é
            MyIO.print(" í(" + countSubstr("" + (char) 237, html) + ")"); // í
            MyIO.print(" ó(" + countSubstr("" + (char) 243, html) + ")"); // ó
            MyIO.print(" ú(" + countSubstr("" + (char) 250, html) + ")"); // ú

            MyIO.print(" à(" + countSubstr("" + (char) 224, html) + ")"); // à
            MyIO.print(" è(" + countSubstr("" + (char) 232, html) + ")"); // è
            MyIO.print(" ì(" + countSubstr("" + (char) 236, html) + ")"); // ì
            MyIO.print(" ò(" + countSubstr("" + (char) 242, html) + ")"); // ò
            MyIO.print(" ù(" + countSubstr("" + (char) 249, html) + ")"); // ù

            MyIO.print(" ã(" + countSubstr("" + (char) 227, html) + ")"); // ã
            MyIO.print(" õ(" + countSubstr("" + (char) 245, html) + ")"); // õ

            MyIO.print(" â(" + countSubstr("" + (char) 226, html) + ")"); // â
            MyIO.print(" ê(" + countSubstr("" + (char) 234, html) + ")"); // ê
            MyIO.print(" î(" + countSubstr("" + (char) 238, html) + ")"); // î
            MyIO.print(" ô(" + countSubstr("" + (char) 244, html) + ")"); // ô
            MyIO.print(" û(" + countSubstr("" + (char) 251, html) + ")"); // û

            int numConsoantesMinusculas = countChars(consoantesMinusculas, html)
                    - 3 * numTables
                    - 2 * numBrs;

            MyIO.print(" consoante(" + numConsoantesMinusculas + ")");

            MyIO.print(" <br>(" + numBrs + ")");
            MyIO.print(" <table>(" + numTables + ")");
            MyIO.println(" " + nome);

            nome = MyIO.readLine();
        }
    }
}
