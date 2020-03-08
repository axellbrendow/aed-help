#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "interpreter_and_lexer.h"

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
            
        Interpreter* interpreter = interpreter_new(expression, operands[0], operands[1], operands[2]);

        printf("%s = %d\n\n\n", expression, interpreter_run(interpreter));

        free(expression);
        free(interpreter);
    }

    return 0;
}
