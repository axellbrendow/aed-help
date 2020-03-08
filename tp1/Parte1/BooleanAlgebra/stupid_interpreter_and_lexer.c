#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_EXPRESSION_SIZE 10000

typedef struct expr expr;
struct expr
{
    char *str;
    int cursor, len, A, B, C;
};

expr* construct(expr* expr, char *str, int cursor, int len, int A, int B, int C)
{
    expr->str = str;
    expr->cursor = cursor;
    expr->len = len;
    expr->A = A;
    expr->B = B;
    expr->C = C;
    return expr;
}

int stupid_interpret(expr* expr);

int read_operation(expr* expr, int operation_size, int (*operation)(int, int))
{
    expr->cursor += operation_size; /* Skips the operation */
    /* Takes the 1º operand and goes to the next */
    int result = stupid_interpret(expr), aux = -1;
    
    while (expr->str[expr->cursor] != ')') // Runs until operation end ')'
    {
        aux = stupid_interpret(expr); // Get current operand and goes to the next
        result = operation(result, aux);
    }

    expr->cursor++; // Skips the ')'
    return result;
}

int and_op(int result, int aux) { return result && aux; }
int or_op(int result, int aux) { return result || aux; }

void skip_spaces(expr* expr)
{
    while (expr->cursor < expr->len &&
        (expr->str[expr->cursor] == ' ' || expr->str[expr->cursor] == ',')) expr->cursor++;
}

int stupid_interpret(expr* expr)
{
    int result = -1;
    int aux;

    if (expr->cursor < expr->len)
    {
        skip_spaces(expr);
        char c = expr->str[expr->cursor]; // Takes the current character
        expr->cursor++; // Skips it
        skip_spaces(expr); // And goes to the next character

        switch (c)
        {
        case ')': result = ')'; break;
        case 'A': result = expr->A; break;
        case 'B': result = expr->B; break;
        case 'C': result = expr->C; break;
        case 'a': result = read_operation(expr, 3, and_op); break;
        case 'o': result = read_operation(expr, 2, or_op); break;
        case 'n': result = !read_operation(expr, 3, NULL); break;
        default: result = EOF; break;
        }
    }

    return result;
}

int operands[3] = { 0, 0, 0 }; // Values for A, B and C

void readInput(char *expression, int* numberOfOperands)
{
    scanf("%d", numberOfOperands);
    puts("\n");

    if (*numberOfOperands == 0) { free(expression); exit(0); }
    
    for (size_t i = 0; i < *numberOfOperands; i++) scanf("%d", operands + i);
    scanf("%*c"); // Discards one character after the last operand

    fgets(expression, MAX_EXPRESSION_SIZE, stdin);
    expression[strlen(expression) - 1] = '\0'; // Eliminate line break
}

int main()
{
    while (1)
    {
        char *expression = (char *) malloc(sizeof(char) * MAX_EXPRESSION_SIZE);
        int numberOfOperands = 0;

        printf("Enter an expression. Ex.:  2 1 1 not(and(A , B))\n\
or type 0 to exit: ");

        readInput(expression, &numberOfOperands);

        expr expr;
        construct(&expr, expression, 0, strlen(expression), operands[0], operands[1], operands[2]);

        int result = stupid_interpret(&expr);
        
        printf("%s = %d\n\n\n", expression, result);

        free(expression);
    }

    return 0;
}
