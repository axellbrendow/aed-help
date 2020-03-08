#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "strings.h"

#define TAMANHO_NOMES 100
#define MAXIMO_APELIDOS 10
#define NUMERO_DE_CAMPOS 7
#define PADRAO_CAMPO_NUMERICO -1

struct Time
{
    char nome[TAMANHO_NOMES];
    char apelidos[MAXIMO_APELIDOS][TAMANHO_NOMES];
    char estadio[TAMANHO_NOMES];
    char tecnico[TAMANHO_NOMES];
    char liga[TAMANHO_NOMES];
    char nomeArquivo[TAMANHO_NOMES];
    int capacidade;
    int fundacaoDia;
    int fundacaoMes;
    int fundacaoAno;
    long paginaTam;
};

typedef struct Time Time;

char* lerNome(Time* time, char *str);
char* lerApelidos(Time* time, char *str);
char* lerEstadio(Time* time, char *str);
char* lerTecnico(Time* time, char *str);
char* lerLiga(Time* time, char *str);
char* lerCapacidade(Time* time, char *str);
char* lerFundado(Time* time, char *str);

char campos[NUMERO_DE_CAMPOS][TAMANHO_NOMES] =
{
    "Full name",
    "Nickname(s)",
    "Ground",
    "Head coach",
    "League",
    "Capacity",
    "Founded",
};

char* (*funcoesDeLeitura[NUMERO_DE_CAMPOS])(Time*, char *) =
{
    lerNome,
    lerApelidos,
    lerEstadio,
    lerTecnico,
    lerLiga,
    lerCapacidade,
    lerFundado,
};

char meses[12][20] =
{
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
};

void iniciarTime(Time* time)
{
    strcpy(time->nome, "");
    for (int i = 0; i < MAXIMO_APELIDOS; i++) strcpy(time->apelidos[i], "");
    strcpy(time->estadio, "");
    strcpy(time->tecnico, "");
    strcpy(time->liga, "");
    strcpy(time->nomeArquivo, "");

    time->capacidade = PADRAO_CAMPO_NUMERICO;
    time->fundacaoDia = PADRAO_CAMPO_NUMERICO;
    time->fundacaoMes = PADRAO_CAMPO_NUMERICO;
    time->fundacaoAno = PADRAO_CAMPO_NUMERICO;
    time->paginaTam = PADRAO_CAMPO_NUMERICO;
}

char* lerNome(Time* time, char *str)
{
    strcpy(time->nome, str);

    return pularCaractere('\n', str);
}

char* lerApelidos(Time* time, char *str)
{
    int i = 0;
    strcpy(time->apelidos[i], str);

    return pularCaractere('\n', str);
}

char* lerEstadio(Time* time, char *str)
{
    strcpy(time->estadio, str);

    return pularCaractere('\n', str);
}

char* lerTecnico(Time* time, char *str)
{
    strcpy(time->tecnico, str);

    return pularCaractere('\n', str);
}

char* lerLiga(Time* time, char *str)
{
    strcpy(time->liga, str);

    return pularCaractere('\n', str);
}

char* lerCapacidade(Time* time, char *str)
{
    replace(' ', '\0', str);
    replace(',', '.', str);
    sscanf(str, "%f", &time->capacidade);

    return pularCaractere('\n', str);
}

char* lerFundado(Time* time, char *str)
{
    replace(' ', '\0', str);
    replace('-', '\0', str);
    replace('(', '\0', str);
    replace(')', '\0', str);
    int tamanho;

    do
    {
        tamanho = strlen(str);

        if (tamanho > 0 && tamanho <= 2 && caracteresNoIntervalo('0', '9', str) &&
            time->fundacaoDia != PADRAO_CAMPO_NUMERICO)
        {
            sscanf(str, "%d", &time->fundacaoDia);
        }

        else if (tamanho == 4 && caracteresNoIntervalo('0', '9', str) &&
            time->fundacaoAno != PADRAO_CAMPO_NUMERICO)
        {
            sscanf(str, "%d", &time->fundacaoAno);
        }

        else if (tamanho >= 3 && tamanho <= 9 &&
            caracteresNoIntervalo('a', 'z', str + 1) && // str + 1 pula a maiuscula do mês
            time->fundacaoMes != PADRAO_CAMPO_NUMERICO)
        {
            for (size_t i = 0; time->fundacaoMes <= 0 && i < 12; i++)
            {
                if (strcmp(meses[i], str) == 0) time->fundacaoMes = i + 1;
            }
        }

        str += tamanho;
        str = pularCaractere('\0', str);

    } while (*str != '\n');

    return pularCaractere('\n', str);
}

void lerCampos(Time* time, char* informacoes)
{
    iniciarTime(time);

    char* token;
    char* lastToken;
    int found = 0;

    while ( ( token = strtok(informacoes, "@") ) != NULL )
    {
        lastToken = token;
        token = strtok(informacoes, "@");
        found = 0;
        
        for (int i = 0; !found && i < NUMERO_DE_CAMPOS; i++)
        {
            found = strcmp(lastToken, campos[i]) == 0;

            if (found) token = funcoesDeLeitura[i](time, token);
        }
    }
}

void imprimir(Time* time)
{
    printf("nome: %s\n", time->nome);
    printf("apelidos:\n");
    for (int i = 0; i < MAXIMO_APELIDOS; i++) printf("\t%s\n", time->apelidos[i]);
    printf("estadio: %s\n", time->estadio);
    printf("tecnico: %s\n", time->tecnico);
    printf("liga: %s\n", time->liga);
    printf("nomeArquivo: %s\n", time->nomeArquivo);
    printf("capacidade: %d\n", time->capacidade);
    printf("fundacaoDia: %d\n", time->fundacaoDia);
    printf("fundacaoMes: %d\n", time->fundacaoMes);
    printf("fundacaoAno: %d\n", time->fundacaoAno);
    printf("paginaTam: %l\n", time->paginaTam);
}

// char nome[TAMANHO_NOMES];
// char apelidos[MAXIMO_APELIDOS][TAMANHO_NOMES];
// char estadio[TAMANHO_NOMES];
// char tecnico[TAMANHO_NOMES];
// char liga[TAMANHO_NOMES];
// char nomeArquivo[TAMANHO_NOMES];
// int capacidade;
// int fundacaoDia;
// int fundacaoMes;
// int fundacaoAno;
// long paginaTam;
