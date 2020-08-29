#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_EXPRESSION_LENGTH 10000
int numberOfOperands;
int operands[3] = { 0, 0, 0 };

typedef struct expr expr;
struct expr
{
	char *str;
	int pos;
	int len;
};

void construct(expr* expr, char *str, int pos, int len)
{
	expr->str = str;
	expr->pos = pos;
	expr->len = len;
}

void skip_spaces(expr* expr)
{
	while (expr->pos < expr->len &&
		(expr->str[expr->pos] == ' ' || expr->str[expr->pos] == '\t' || expr->str[expr->pos] == ',')) expr->pos++;
}


int operacao(expr* expr)
{
	skip_spaces(expr);
	int last = expr->str[expr->pos++];
	int result = -1;
	int aux;

	if (last == ')') result = ')';
	else if (last == 'A') result = *(operands + 0);
	else if (last == 'B') result = *(operands + 1);
	else if (last == 'C') result = *(operands + 2);
	else if (last == 'n')
	{
		expr->pos += 3;
		result = !operacao(expr);
		expr->pos++; // Skips the right parenthesis ')'
	}
	else if (last == 'a')
	{
		expr->pos += 3;
		result = operacao(expr);
		skip_spaces(expr);
		
		while (expr->str[expr->pos] != ')')
		{
			aux = operacao(expr);
			result = result && aux;
		}

		expr->pos++; // Skips the right parenthesis ')'
	}
	else if (last == 'o')
	{
		expr->pos += 2;
		result = operacao(expr);
		skip_spaces(expr);
		
		while (expr->str[expr->pos] != ')')
		{
			aux = operacao(expr);
			result = result || aux;
		}

		expr->pos++; // Skips the right parenthesis ')'
	}

	return result;
}

int main()
{
    char *expression = (char *) malloc(sizeof(char) * MAX_EXPRESSION_LENGTH);

    scanf("%d", &numberOfOperands);
    for (size_t i = 0; i < numberOfOperands; i++) scanf("%d", operands + i);
    scanf("%*c");

    fgets(expression, MAX_EXPRESSION_LENGTH, stdin);
    expression[strlen(expression) - 1] = '\0';

	expr *expr = malloc(sizeof(expr));
	construct(expr, expression, 0, strlen(expression));

	printf("%s = %d\n", expression, operacao(expr));

	free(expr);
    free(expression);

    return EXIT_SUCCESS;
}

