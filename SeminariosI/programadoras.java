import java.util.Scanner;

class Programadora
{
    int tempo;
    String nome;
    int quantquestao;

    public Programadora(int tempo, String nome, int quantquestao)
    {
        this.nome = nome;
        this.tempo = tempo;
        this.quantquestao = quantquestao;
    }

    public Programadora()
    {
        this(Integer.MAX_VALUE, "", 0);
    }

    public Programadora(String linha)
    {
        this();

        String[] nomesETempos = linha.split(";");

        this.tempo = 0;
        this.nome = nomesETempos[0];

        for (int i = 1; i < nomesETempos.length; i++)
        {
            int guardTemp = Integer.parseInt(nomesETempos[i]);

            if (guardTemp != 0)
            {
                quantquestao = quantquestao + 1;
            }

            tempo = tempo + guardTemp;
        }
    }

    public Programadora comparar(Programadora programadora)
    {
        Programadora melhor = programadora;

        if (this.quantquestao > programadora.quantquestao)
        {
            melhor = this;
        }

        else if (this.quantquestao == programadora.quantquestao)
        {
            if (this.tempo < programadora.tempo)
            {
                melhor = this;
            }
        }

        return melhor;
    }
}

public class programadoras
{
    public static void main(String args[])
    {
        Scanner leitor = new Scanner(System.in);
        int x = 0;
        Programadora melhor = new Programadora();
        int tamanho = Integer.parseInt(leitor.nextLine());
        Programadora programadora;

        for (x = 0; x < tamanho; x++)
        {
            programadora = new Programadora(leitor.nextLine());
            melhor = melhor.comparar(programadora);
        }

        System.out.println(melhor.nome);
        System.out.println(melhor.quantquestao + " PROBLEMAS");
        System.out.println(melhor.tempo + " MINUTOS");
    }
}
