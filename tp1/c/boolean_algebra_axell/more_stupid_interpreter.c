#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_EXPRESSION_SIZE 10000
#define do_operation(operator, operationLength)\
	expr->cursor += operationLength; /* Skips the operation name and the '(' */\
	result = operation(expr); /* Takes it's first operand */\
	skip_spaces(expr); /* Skips ' ', ',' and '\t' */\
	/* Run while cursor is not stopped at a ')' */\
	while (expr->str[expr->cursor] != ')')\
	{\
		aux = operation(expr); /* Takes the next operand */\
		result = result operator aux; /* Do the operation */\
	}\
	expr->cursor++; // Skipks the ')'

typedef struct expr expr;
struct expr // Struct to store an expression and a cursor for it
{
	char *str; // Expression
	int cursor; // Position in the expression
	int len; // Expression length
};

void construct(expr* expr, char *str, int cursor, int len)
{
	expr->str = str;
	expr->cursor = cursor;
	expr->len = len;
}

void skip_spaces(expr* expr) /* Skips ' ', ',' and '\t' */
{
	while ( expr->cursor < expr->len && (expr->str[expr->cursor] == ' ' ||
		expr->str[expr->cursor] == '\t' || expr->str[expr->cursor] == ',') ) expr->cursor++;
}

int operands[3] = { 0, 0, 0 }; // Values for A, B and C

int operation(expr* expr)
{
	skip_spaces(expr); /* Skips ' ', ',' and '\t' */
	char curr_char = expr->str[expr->cursor++]; // Get current char and go to the next
	int result = -1; // Result of this operation
	int aux; // Auxiliar variable to store more operands for 'and' and 'or' operations

	if      (curr_char == 'A') result = *(operands + 0); // Get value of A
	else if (curr_char == 'B') result = *(operands + 1); // Get value of B
	else if (curr_char == 'C') result = *(operands + 2); // Get value of C
	                             // Get first operand and negate it
	else if (curr_char == 'n') { do_operation(-, 3) result = !result; }
	else if (curr_char == 'a') { do_operation(&&, 3) }
	else if (curr_char == 'o') { do_operation(||, 2) }

	skip_spaces(expr);

	return result;
}

int main()
{
    char *expression = (char *) malloc(sizeof(char) * MAX_EXPRESSION_SIZE);
	int numberOfOperands;

    scanf("%d", &numberOfOperands);
    for (size_t i = 0; i < numberOfOperands; i++) scanf("%d", operands + i);
    scanf("%*c"); // Discards one character after the last operand

    fgets(expression, MAX_EXPRESSION_SIZE, stdin);
    expression[strlen(expression) - 1] = '\0'; // Eliminate line break

	expr *expr = malloc(sizeof(expr));
	construct(expr, expression, 0, strlen(expression));

	printf("%s = %d\n", expression, operation(expr)); // Print expression and it's result

	free(expr);
    free(expression);

    return EXIT_SUCCESS;
}
