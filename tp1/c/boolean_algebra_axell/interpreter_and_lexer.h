#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>

// https://ruslanspivak.com/lsbasi-part5/

const char *LPAREN = "LPAREN";
const char *RPAREN = "RPAREN";
const char *AND = "AND";
const char *OR = "OR";
const char *NOT = "NOT";
const char *LOGIC_VALUE = "LOGIC_VALUE";
const char *COMMA = "COMMA";
const char *EOFTOKEN = "EOF"; // Possível remover

// -------------------- Class Token

typedef struct Token Token;

struct Token
{
    char type[30];
    int value;
};

Token *token_new(const char *type, int value)
{
    Token *token = (Token *) malloc(sizeof(Token));

    strcpy(token->type, type);
    token->value = value;

    return token;
}

int equals(const char *str1, const char *str2)
{
    return strcmp(str1, str2) == 0;
}

int contains(const char *str1, const char *str2)
{
    return strstr(str1, str2) != NULL;
}

int isWhitespace(char c)
{
    return c == ' ' || c == '\t' || c == '\r' || c == '\n';
}

int isOnInterval(int lowerValue, int greaterValue, int value)
{
    return value >= lowerValue && value <= greaterValue;
}

int isDigit(char c)
{
    return isOnInterval('0', '9', c);
}

// -------------------- Class Lexer

typedef struct Lexer Lexer;

#define MAX_EXPRESSION_SIZE 10000

struct Lexer
{
    char expression[MAX_EXPRESSION_SIZE];
    int cursor;
    int exprLength;
    char current_char;
    int A;
    int B;
    int C;
};

Lexer* lexer_new(const char *expression, int A, int B, int C)
{
    Lexer* lexer = (Lexer *) malloc(sizeof(Lexer));

    strcpy(lexer->expression, expression);
    lexer->cursor = 0;
    lexer->exprLength = strlen(expression);
    lexer->current_char = expression[lexer->cursor];
    lexer->A = A;
    lexer->B = B;
    lexer->C = C;

    return lexer;
}

void lexer_error(Lexer* lexer, const char *format, ...)
{
    char errorMsg[MAX_EXPRESSION_SIZE + 2];
    va_list args;
    va_start(args, format);
    vsprintf(errorMsg, format, args); // Sends the formatted string to errorMsg
    va_end(args);

    // Shows the error and the expression
    fprintf(stderr, "\nERROR: %s\n%s\n", errorMsg, lexer->expression);

    // Creates an array to store the string that will show where the compilation stopped
    char spaces[lexer->cursor + 3];

    memset(spaces, ' ', lexer->cursor); // Fill it with ' '

    spaces[lexer->cursor] = '^'; // Put an indicator where compilation stopped
    spaces[lexer->cursor + 1] = '\n';
    spaces[lexer->cursor + 2] = '\0';
    fprintf(stderr, spaces);

    exit(1);
}

void lexer_advance(Lexer* lexer)
{
    lexer->cursor++;
    lexer->current_char = ( lexer->cursor >= lexer->exprLength ) ?
        EOF : lexer->expression[lexer->cursor];
}

void lexer_skip_spaces(Lexer* lexer)
{
    while (lexer->current_char != EOF && isWhitespace(lexer->current_char))
        lexer_advance(lexer);
}

/**
 * Tries to get the str starting from current position. If it fails, the program
 * exits.
 * 
 * @param lexer Lexer with the expression.
 * @param str String to be searched.
 */
const char *lexer_get_str(Lexer* lexer, const char* str)
{
    int length = strlen(str);
    int matches = 1;

    for (size_t i = 0; matches && i < length; i++)
    {
        matches = lexer->current_char == str[i] && lexer->current_char != EOF;

        if (!matches) lexer_error(lexer, "invalid string, expected '%s'", str);
        else lexer_advance(lexer);
    }

    return str;
}

Token *lexer_next_token(Lexer* lexer)
{
    while (lexer->current_char != EOF)
    {
        if (isWhitespace(lexer->current_char))
        {
            lexer_skip_spaces(lexer);
            continue;
        }

        if (lexer->current_char == 'a')
            return token_new( AND, lexer_get_str(lexer, "and")[0] );

        if (lexer->current_char == 'o')
            return token_new( OR, lexer_get_str(lexer, "or")[0] );

        if (lexer->current_char == 'n')
            return token_new( NOT, lexer_get_str(lexer, "not")[0] );

        if (lexer->current_char == '(')
        {
            lexer_advance(lexer);
            return token_new(LPAREN, '(');
        }

        if (lexer->current_char == ')')
        {
            lexer_advance(lexer);
            return token_new(RPAREN, ')');
        }

        if (lexer->current_char == ',')
        {
            lexer_advance(lexer);
            return token_new(COMMA, ',');
        }

        if (lexer->current_char == 'A')
        {
            lexer_advance(lexer);
            return token_new(LOGIC_VALUE, lexer->A);
        }

        if (lexer->current_char == 'B')
        {
            lexer_advance(lexer);
            return token_new(LOGIC_VALUE, lexer->B);
        }

        if (lexer->current_char == 'C')
        {
            lexer_advance(lexer);
            return token_new(LOGIC_VALUE, lexer->C);
        }

        lexer_error(lexer, "undefined token '%c'", lexer->current_char);
    }

    return token_new(EOFTOKEN, EOF);
}

// -------------------- Class Interpreter

typedef struct Interpreter Interpreter;

struct Interpreter
{
    Lexer* lexer;
    Token* current_token;
};

void interpreter_eat(Interpreter* interpreter, const char* token_type);
int interpreter_factor(Interpreter *interpreter);
int interpreter_expr(Interpreter* interpreter);

Interpreter* interpreter_new(const char *expression, int A, int B, int C)
{
    Interpreter* interpreter = (Interpreter*) malloc(sizeof(Interpreter));

    interpreter->lexer = lexer_new(expression, A, B, C);
    interpreter->current_token = lexer_next_token(interpreter->lexer);

    return interpreter;
}

/**
 * Consumes the current token and goes to the next.
 * 
 * @param interpreter Expression interpreter.
 * @param token_type Expected token type.
 */
void interpreter_eat(Interpreter* interpreter, const char* token_type)
{
    if (equals(interpreter->current_token->type, token_type))
    {
        free(interpreter->current_token);
        interpreter->current_token = lexer_next_token(interpreter->lexer);
    }

    else if (equals(interpreter->current_token->type, EOFTOKEN))
        lexer_error(interpreter->lexer, "End-of-file, expected (%s)", token_type);

    else lexer_error(interpreter->lexer, "invalid token type, expected (%s)", token_type);
}

/**
 * Try to get a logic value or an expression from where the interpreter is.
 * 
 * @param interpreter Expression interpreter.
 * 
 * @return int Logic value of this factor.
 */
int interpreter_factor(Interpreter *interpreter)
{
    // factor : LOGIC_VALUE | expr
    int value = -1;

    if (equals(interpreter->current_token->type, LOGIC_VALUE))
    {
        value = interpreter->current_token->value;
        interpreter_eat(interpreter, LOGIC_VALUE);
    }

    else if (equals(interpreter->current_token->type, AND) ||
        equals(interpreter->current_token->type, OR) ||
        equals(interpreter->current_token->type, NOT))
    {
        value = interpreter_expr(interpreter);
    }

    else lexer_error(interpreter->lexer, "invalid token, expected LOGIC_VALUE, AND, OR or NOT");

    return value;
}

int interpreter_expr(Interpreter* interpreter)
{
    /*
    expr   : (AND | OR | NOT) LPAREN factor (COMMA factor)* RPAREN
    factor : LOGIC_VALUE | expr
    */
    int tokenValue = interpreter->current_token->value;
    int factor;

    if      (tokenValue == 'a') interpreter_eat(interpreter, AND);
    else if (tokenValue == 'o') interpreter_eat(interpreter, OR);
    else if (tokenValue == 'n') interpreter_eat(interpreter, NOT);
    else lexer_error(interpreter->lexer, "expected 'and', 'or' or 'not'");

    interpreter_eat(interpreter, LPAREN);

    int result = interpreter_factor(interpreter);

    if (tokenValue == 'n') result = !result;

    else
        while (equals(interpreter->current_token->type, COMMA))
        {
            interpreter_eat(interpreter, COMMA);

            if (tokenValue == 'a')
            {
                factor = interpreter_factor(interpreter);
                result = result && factor;
            }

            else if (tokenValue == 'o')
            {
                factor = interpreter_factor(interpreter);
                result = result || factor;
            }
        }

    interpreter_eat(interpreter, RPAREN);
    
    return result;
}

int interpreter_run(Interpreter* interpreter)
{
    int result = interpreter_expr(interpreter);

    free(interpreter->current_token);
    free(interpreter->lexer);

    return result;
}
