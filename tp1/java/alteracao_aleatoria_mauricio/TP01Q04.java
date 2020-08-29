import java.util.Random;

/*
Alteração Aleatória - Crie um método iterativo que recebe uma string, sorteia duas letras
min ́usculas aleat ́orias (c ́odigo ASCII ≥ ’a’ e ≤ ’z’), substitui todas as ocorrˆencias da primeira
letra na string pela segunda e retorna a string com as altera ̧c ̃oes efetuadas. Na sa ́ıda padr ̃ao,
para cada linha de entrada, execute o m ́etodo desenvolvido nesta quest ̃ao e mostre a string
retornada como uma linha de sa ́ıda. Abaixo, observamos um exemplo de entrada supondo que
para a primeira linha as letras sorteados foram o ’a’ e o ’q’. Para a segunda linha, foram o ’e’ e
o ’k’.

A classe Random do Java gera n ́umeros (ou letras) aleat ́orios e o exemplo abaixo mostra uma
letra min ́uscula na tela. Em especial, destacamos que:
i) seed ́e a semente para gera ̧c ̃ao de n ́umeros aleat ́orios;
ii) nesta quest ̃ao, por causa da corre ̧c ̃ao autom ́atica, a seed ser ́a quatro;
iii) a disciplina de Estat ́ıstica e Probabilidade faz uma discuss ̃ao sobre “aleat ́orio“.

Random gerador = new Random();
gerador.setSeed(4);
System.out.println((char)(’a’+(Math.abs(gerador.nextInt())%26)));
*/

public class TP01Q04 {

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static String alteraString(String s) {
        String alterada = "";
        char letra1;
        char letra2;

        Random gerador = new Random(26);
        gerador.setSeed(4);
        letra1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

        letra2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == letra1) {
                alterada += letra2;
            } else {
                alterada += s.charAt(i);
            }
        }       
        return s;
    }

    public static void main(String[] args) {
        String[] entrada = new String[1000];
        int numEntrada = 0;

        do {
            entrada[numEntrada] = MyIO.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--;

        for (int i = 0; i < numEntrada; i++) {
           
            MyIO.println(alteraString(entrada[i]));
        }
    }
}
