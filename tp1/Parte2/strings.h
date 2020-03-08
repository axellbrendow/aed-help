#pragma once

#include <string.h>

char *pularCaractere(char caractere, char* str)
{
    while (*str == caractere) str++;

    return str;
}

int caracteresNoIntervalo(char limiteInferior, char limiteSuperior, char* str)
{
    int todos = 1;
    int tamanho = strlen(str);

    for (int i = 0; todos && i < tamanho; i++)
    {
        todos = str[i] >= limiteInferior && str[i] <= limiteSuperior;
    }

    return todos;
}

void replace(char charToFind, char charToReplace, char *str)
{
    while ( ( str = strchr(str, charToFind) ) != NULL )
    {
        *str++ = charToReplace;
    }
}

int numeroDeSubstrings(char *substring, char *str)
{
    int contagem = 0;
    int tamanhoSubstr = strlen(substring);

    while ( ( str = strstr(str, substring) ) != NULL )
    {
        contagem++;
        str += tamanhoSubstr;
    }

    return contagem;
}