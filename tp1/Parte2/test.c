#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "strings.h"
#include "Time.h"

#define MAXIMO 30000

void extrairTabela(FILE *file, char *bufferTabela)
{
    char linha[MAXIMO];
    int numeroDeTabelas = 0;
    int primeiraTabelaEncontrada = 0;
    int primeiraTabelaTerminada = 0;
    int numeroDeIniciosDeTabela = 0;
    int numeroDeFinsDeTabela = 0;
    // int tamanhoLinha;

    while (!primeiraTabelaTerminada && fgets(linha, MAXIMO, file) != NULL)
    {
        // tamanhoLinha = strlen(linha);
        // linha[tamanhoLinha - 1] = '\0';
        numeroDeIniciosDeTabela = numeroDeSubstrings("<table", linha);
        numeroDeFinsDeTabela = numeroDeSubstrings("</table", linha);

        if (numeroDeIniciosDeTabela > 0)
        {
            if (strstr(linha, "infobox")) primeiraTabelaEncontrada = 1;
            numeroDeTabelas += numeroDeIniciosDeTabela;
        }

        if (numeroDeFinsDeTabela > 0)
        {
            numeroDeTabelas -= numeroDeFinsDeTabela;
            primeiraTabelaTerminada = (numeroDeTabelas <= 0 ? 1 : 0);
        }

        if (primeiraTabelaEncontrada)
        {
            strcat(bufferTabela, linha);
        }
    }
}

void extrairTexto(char *tabela)
{
    char strDe1Char[2];
    strDe1Char[1] = '\0';
    char texto[MAXIMO];
    texto[0] = '\0';
    int tamanhoTabela = strlen(tabela);
    int numeroDeTags = 0;
    int campoEncontrado = 0;
    int entidade = 0;

    for (int i = 0; i < tamanhoTabela; i++)
    {
        if (tabela[i] == '&') entidade = 1;

        else if (entidade && tabela[i] == ';')
        {
            strcat(texto, " ");
            entidade = 0;
        }

        else if (!entidade && tabela[i] != '\t')
        {
            strDe1Char[0] = tabela[i];

            if (tabela[i] == '<')
            {
                numeroDeTags++;

                if (tabela[i + 1] == 's' && tabela[i + 2] == 't' &&
                    tabela[i + 3] == 'y' && tabela[i + 4] == 'l') // <style
                {
                    tabela[i + 7] = '<';
                    char* end;
                    if ( ( end = strstr(tabela + i, "</style") ) != NULL )
                        *(end - 1) = '>';
                }
                else if (tabela[i + 1] == 't' && tabela[i + 2] == 'r') strcat(texto, "\n");
                else if (tabela[i + 1] == 't' && tabela[i + 2] == 'h') campoEncontrado = 1;
                else if (campoEncontrado && tabela[i + 1] == '/')
                {
                    campoEncontrado = 0;
                    strcat(texto, "@");
                }
            }

            else if (tabela[i] == '>') numeroDeTags--;

            else if (numeroDeTags == 0) strcat(texto, strDe1Char);
        }
    }

    puts(texto);
}

int main(int argc, char const *argv[])
{
//     printf("string:\n\n%s\n",
// "Kilmarnock\n\
// \n\
// Full name@Kilmarnock Football Club\n\
// Nickname(s)@Killie\n\
// Founded@5 January 1869  150 years ago (1869-01-05)\n\
// Ground@Rugby ParkKilmarnock, East Ayrshire\n\
// Capacity@17,889 1 \n\
// Main Shareholder@Billy Bowie\n\
// Manager@Angelo Alessio\n\
// League@Scottish Premiership\n\
// 2018ÔÇô19@Scottish Premiership, 3rd of 12\n\
// Website@Club website\n\
// \n\
// @@\n\
// @@@@@@@@@@@@@@Home colours@@@@@@@@@@@@@@Away colours@@\n\
// @\n\
//  Current season@");

//     Time meutime;

// char *data = "Kilmarnock\n\
// \n\
// Full name@Kilmarnock Football Club\n\
// Nickname(s)@Killie\n\
// Founded@5 January 1869  150 years ago (1869-01-05)\n\
// Ground@Rugby ParkKilmarnock, East Ayrshire\n\
// Capacity@17,889 1 \n\
// Main Shareholder@Billy Bowie\n\
// Manager@Angelo Alessio\n\
// League@Scottish Premiership\n\
// 2018ÔÇô19@Scottish Premiership, 3rd of 12\n\
// Website@Club website\n\
// \n\
// @@\n\
// @@@@@@@@@@@@@@Home colours@@@@@@@@@@@@@@Away colours@@\n\
// @\n\
//  Current season@\n";

//     lerCampos(&meutime, data);

//     imprimir(&meutime);
    const char *entrada = ( argc < 2 ? "pubQ1eQ2.in" : argv[1] );

    FILE *input = fopen(entrada, "r");
    FILE *pagina;
    char bufferTabela[MAXIMO];
    char linkPagina[200];
    int tamanhoLink = 0;
    
    // lÃª o caminho para o arquivo
    fgets(linkPagina, 200, input); // fgets inclui o \n na string

    while (strstr(linkPagina, "FIM") == NULL)
    {
        tamanhoLink = strlen(linkPagina) - 1;
        linkPagina[tamanhoLink] = '\0'; // remove o \n

        pagina = fopen(linkPagina, "r");
        bufferTabela[0] = '\0';

        extrairTabela(pagina, bufferTabela);
        fclose(pagina);
        replace('\n', '@', bufferTabela);
        extrairTexto(bufferTabela);

        puts("\n###################################\n");

        fgets(linkPagina, 200, input);
    }

    fclose(input);
}
