// stdio = standard input output
#include <stdio.h>
#include <string.h>

#define MAX_TAMANHO_EXPRESSAO 10000
#define MAX_OPERANDOS 3

int operandos[MAX_OPERANDOS]; // Cria um arranjo global que guardará os operandos

typedef struct Interpretador Interpretador;

struct Interpretador
{
	char *expressao;
	int tamanho;
	int cursor;
};

void pularEspacos(Interpretador* interpretador)
{
	char c = interpretador->expressao[interpretador->cursor];

	while (interpretador->cursor < interpretador->tamanho &&
			(c == ' ' || c == '\r' || c == '\n' || c == '\t' || c == ',')) // Pula espaços e vírgulas
		c = interpretador->expressao[++interpretador->cursor];
}

int buscarOperacao(Interpretador* interpretador)
{
	pularEspacos(interpretador); // Após essa função, o cursor irá parar em um caractere válido
	int resultado, aux;

	// Obtém o caractere na posição do cursor e avança-o
	char c = interpretador->expressao[++interpretador->cursor]; 

	if (c == 'n') {
		interpretador->cursor += 3; // Pula os caracteres "ot("
		resultado = !buscarOperacao(interpretador);
	}
	else if (c == 'a') {
		interpretador->cursor += 3; // Pula os caracteres "nd("
		resultado = buscarOperacao(interpretador);

		// Obtém o caractere na posição do cursor e avança-o
		c = interpretador->expressao[++interpretador->cursor];

		while (interpretador->expressao[interpretador->cursor] != ')')
		{
			aux = buscarOperacao(interpretador);
			resultado = resultado && aux;
		}
		interpretador->cursor++;
	}
	else if (c == 'o') {
		interpretador->cursor += 2; // Pula os caracteres "r("
		resultado = buscarOperacao(interpretador);

		// Obtém o caractere na posição do cursor e avança-o
		c = interpretador->expressao[++interpretador->cursor];

		while (interpretador->expressao[interpretador->cursor] != ')')
		{
			aux = buscarOperacao(interpretador);
			resultado = resultado || aux;
		}
		interpretador->cursor++;
	}
	else if (c == 'A') resultado = operandos[0];
	else if (c == 'B') resultado = operandos[1];
	else if (c == 'C') resultado = operandos[2];
	
	pularEspacos(interpretador);

	return resultado;
}

int main()
{
	char expressao[MAX_TAMANHO_EXPRESSAO];
	int numOperandos;

	scanf("%d", &numOperandos); // Lê a quantidade de operandos

	for (int i = 0; i < numOperandos; i++)
	{
		scanf("%d", &operandos[i]); // Lê os operandos e guarda-os no arranjo
	}

	scanf("%*c"); // Avança o cursor um caractere (descarta-o)

	// A partir de onde o cursor estiver, lê a linha até o final
	fgets(expressao, MAX_TAMANHO_EXPRESSAO, stdin);

	// Cria Interpretador
	Interpretador interpretador;
	interpretador.expressao = expressao;
	interpretador.tamanho = strlen(expressao);
	interpretador.cursor = 0;

	int resultado = buscarOperacao(interpretador);

	printf("Expressao: %sResultado: %d", expressao, resultado);

	return 0;
}

