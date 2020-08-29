#include<stdio.h>
#include<string.h>

#define TAMANHO_MAXIMO 10000

int operands[3]; // A entrada apresenta no máximo 3 operandos

typedef struct Expr Expr;
struct Expr
{
	char *str;
	int pos;
	int len;
};

int operando(Expr *expr) // Esta função obtém o valor de operandos e operações
{
	int resul, aux; // Seria bom pular espaços em branco antes de pegar o char
	char c = expr->str[expr->pos++]; // Pega o caractere atual e anda para o próximo

	if (c == 'A') resul = operands[0];
	else if (c == 'B') resul = operands[1];
	else if (c == 'C') resul = operands[2];
	else if (c == 'n'){ // not() encontrada
		expr->pos += 3; // Pula os caracteres "ot("
		resul = !operando(expr); // Obtém o operando da not e o nega
		expr->pos++; // Após esse operando, paramos num ')', então, pula-o
	}
	else if (c == 'a'){ // and() encontrada
		expr->pos += 3; // Pula os caracteres "nd("
		resul = operando(expr); // Obtém o primeiro operando da and()
		// Roda enquanto houver uma vírgula após um operando
		while (expr->str[expr->pos] == ','){
			expr->pos++; // Pula a vírgula
			aux = operando(expr); // Obtém o operando após ela
			resul = resul && aux; // Faz a and()
		}
		expr->pos++; // Quando sair do while, é porque paramos num ')', então, pula-o
	}
	else if (c == 'o'){
		expr->pos += 2;
		resul = operando(expr);
		while (expr->str[expr->pos] == ','){
			expr->pos++;
			aux = operando(expr);
			resul = resul || aux;
		}
		expr->pos++;
	}
	// Seria interessante pular espaços em branco após qualquer operação também
	return resul;
}

int main()
{
	int numOperands, resultado;
	char expressao[TAMANHO_MAXIMO];
	scanf("%d", &numOperands);

	for (int i = 0; i < numOperands; i++) scanf("%d", &operands[i]);
	scanf("%*c");

	fgets(expressao, TAMANHO_MAXIMO, stdin);

	Expr expr;
	expr.str = expressao;
	expr.pos = 0;
	expr.len = strlen(expressao);
	resultado = operando(&expr);

	printf("'%s' = %d\n", expressao, resultado);

}
