import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;

public class html {
    public static int[] contagem = new int[25];

 public static void main(String[] args) 
 {
    String str,nome;
   do
    {
        for(int i = 0; i < contagem.length; i++) contagem[i] = 0;
        nome = MyIO.readLine();
        str = MyIO.readLine();
        if(!(nome.charAt(0) == 'F' && nome.charAt(1) == 'I' && nome.charAt(2) == 'M'))
        {
            getUrl(str);
            MyIO.println("a(" + contagem[0] + ") e(" + contagem[1] + ") i(" + contagem[2] + ") o(" + contagem[3] + ") u(" + contagem[4] + ") á(" + contagem[5] + ") é(" + contagem[6] + ") í(" + contagem[7] + ") ó(" + contagem[8] + ") ú(" + contagem[9] + ") à(" + contagem[10] + ") è(" + contagem[11] + ") ì(" + contagem[12] + ") ò(" + contagem[13] + ") ù(" + contagem[14] + ") ã(" + contagem[15] + ") õ(" + contagem[16] + ") â(" + contagem[17] + ") ê(" + contagem[18] + ") î(" + contagem[19] + ") ô(" + contagem[20] + ") û(" + contagem[21] + ") consoante(" + contagem[22] + ") <br>(" + contagem[23] + ") <table>(" + contagem[24] + ") " + nome);
        }
    }while(!(nome.charAt(0) == 'F' && nome.charAt(1) == 'I' && nome.charAt(2) == 'M'));
 }

    public static void getUrl(String str) {
        try {
            String htmline;
            URL URL = new URL(str);
            BufferedReader br = new BufferedReader(new InputStreamReader(URL.openStream(),Charset.forName("UTF-8")));
            String content = "";
            while ((htmline = br.readLine()) != null) {
                content += htmline;
            }
            conta(content);
            br.close();

        } catch (MalformedURLException me) {
            System.out.println(me);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void conta(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '<')
            {
                if (str.charAt(i + 1) == 'b')
                {
                    if(str.charAt(i + 2) == 'r')
                    {
                        if(str.charAt(i + 3) == '>')
                        {
                            contagem[23]++;
                            i = i + 3;
                        }
                    }

                } else if (str.charAt(i + 1) == 't') 
                {
                    if(str.charAt(i + 2) == 'a')
                    {
                        if(str.charAt(i + 3) == 'b')
                        {
                            if(str.charAt(i + 4) == 'l')
                            {
                                if(str.charAt(i + 5) == 'e')
                                {
                                    if(str.charAt(i + 6) == '>')
                                    {
                                        contagem[24]++;
                                        i = i + 6;
                                    }
                                }
                            }
                        }
                    }

                }
            }
             else if (str.charAt(i) == 'a')
                contagem[0]++;
            else if (str.charAt(i) == 'e')
                contagem[1]++;
            else if (str.charAt(i) == 'i')
                contagem[2]++;
            else if (str.charAt(i) == 'o')
                contagem[3]++;
            else if (str.charAt(i) == 'u')
                contagem[4]++;
            else if (str.charAt(i) == '\u00e1')
                contagem[5]++;
            else if (str.charAt(i) == '\u00e9')
                contagem[6]++;
            else if (str.charAt(i) == '\u00ed')
                contagem[7]++;
            else if (str.charAt(i) == '\u00f3')
                contagem[8]++;
            else if (str.charAt(i) == '\u00fa')
                contagem[9]++;
            else if (str.charAt(i) == '\u00e0')
                contagem[10]++;
            else if (str.charAt(i) == '\u00e8')
                contagem[11]++;
            else if (str.charAt(i) == '\u00ec')
                contagem[12]++;
            else if (str.charAt(i) == '\u00f2')
                contagem[13]++;
            else if (str.charAt(i) == '\u00f9')
                contagem[14]++;
            else if (str.charAt(i) == '\u00e3')
                contagem[15]++;
            else if (str.charAt(i) == '\u00f5')
                contagem[16]++;
            else if (str.charAt(i) == '\u00e2')
                contagem[17]++;
            else if (str.charAt(i) == '\u00ea')
                contagem[18]++;
            else if (str.charAt(i) == '\u00ee')
                contagem[19]++;
            else if (str.charAt(i) == '\u00f4')
                contagem[20]++;
            else if (str.charAt(i) == '\u00fb')
                contagem[21]++;
            else {
                char c = str.charAt(i);
                if (c < 91 && c > 64) {
                    c += 32;
                }

                if ((c > 96 && c < 123) && !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                    contagem[22]++;
                }
            }

        }



    }
}