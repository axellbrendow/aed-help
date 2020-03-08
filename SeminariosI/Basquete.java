import java.util.Scanner;

class Atleta
{
    String nome;
    int pontos;
    int tentativas;
    int acertos;
    double aproveitamento;

    public Atleta(String nome, int pontos, int tentativas, int acertos, double aproveitamento)
    {
        this.nome = nome;
        this.pontos = pontos;
        this.tentativas = tentativas;
        this.acertos = acertos;
        this.aproveitamento = aproveitamento;
    }

    public Atleta()
    {
        this("", 0, 0, 0, 0.0); // Começa o atleta com um nome vazio e tudo zerado
    }

    public Atleta(String linha)
    {
        this(); // Começa o atleta com os valores padrões

        // Separa a linha em strings [ "EARVIN", "10-8", "22-20", "15-11" ]
        String[] nomeTentativasEAcertos = linha.split(";");

        this.nome = nomeTentativasEAcertos[0];

        // Começa a quantidade de pontos por cesta igual a 1. Como as cestas começam
        // em 1 pontos e depois vão para 2 e 3 pontos. Em cada iteração do for irei
        // incrementar a quantidade de pontos por cesta em uma unidade.
        int pontosPorCesta = 1;
        
        // Começa o contador em 1, pois na posição 0 do arranjo nomeTentativasEAcertos
        // terá o nome do jogador. Dessa forma, só irei pegar as strings "num-num"
        for (int i = 1; i < nomeTentativasEAcertos.length; i++)
        {
            // Separa as tentativas e acertos em [ "10", "8" ]
            String[] tentativasEAcertos = nomeTentativasEAcertos[i].split("-");

            // Acumula a quantidade de tentativas
            this.tentativas += Integer.parseInt(tentativasEAcertos[0]);

            int acertos = Integer.parseInt(tentativasEAcertos[1]);

            // Acumula a quantidade de acertos
            this.acertos += acertos;

            // Acumula a quantidade de pontos
            this.pontos += acertos * pontosPorCesta;

            pontosPorCesta++; // As próximas cestas valem 1 ponto a mais
        }

        this.aproveitamento = ((double) this.acertos) / this.tentativas;
    }
}

class Basquete
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int quantAtletas = Integer.parseInt( scanner.nextLine() );

        // Lê a próxima linha e já a transforma no primeiro atleta
        Atleta atleta = new Atleta( scanner.nextLine() );

        // Já pressupõe que o primeiro atleta é o maior pontuador
        Atleta maiorPontuador = atleta;

        // Já pressupõe que o primeiro atleta é o com melhor aproveitamento
        Atleta atletaComMelhorAproveitamento = atleta;

        // Começa o contador em 1 pois o primeiro atleta já foi lido
        for (int i = 1; i < quantAtletas; i++)
        {
            atleta = new Atleta( scanner.nextLine() ); // Lê um atleta

            // Checa se ele fez mais pontos que o maior pontuador já encontrado
            if (atleta.pontos > maiorPontuador.pontos)
            {
                maiorPontuador = atleta;
            }

            // Checa se ele teve um maior aproveitamento que o maior aproveitamento
            // já encontrado
            if (atleta.aproveitamento > atletaComMelhorAproveitamento.aproveitamento)
            {
                atletaComMelhorAproveitamento = atleta;
            }
        }

        String aproveitamento =
            String.format("%.2f", atletaComMelhorAproveitamento.aproveitamento * 100);

        System.out.println(maiorPontuador.nome + " " + maiorPontuador.pontos + " PONTOS");
        System.out.println(atletaComMelhorAproveitamento.nome + " " + aproveitamento + " %");
    }
}
