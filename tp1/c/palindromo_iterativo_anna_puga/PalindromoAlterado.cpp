#include <stdio.h>
#include <stdlib.h>
#include <cstring>

#define bool short
#define true 1
#define false 0

#define MAX_LINES 700
#define MAX_COLUMNS 500

bool isFim(char *str) {
    return ( strlen ( str ) >= 3 && str [0] == 'F' &&
        str[1] == 'I' && str[2] == 'M' ) ;
}

bool palindromo(char *str) {
    char comp[MAX_COLUMNS];
    bool resp = 1;
    int i, j, tamanho = strlen(str);
    for(i = 0, j = tamanho-1; i < tamanho; i++, j--){
        comp[j] = str[i];
    }

    for(i = 0; resp && i < tamanho; i++){
        resp = str[i] == comp[i];
    }

    return resp;
}

char *replaceChar(char *str, char toSearch, char toReplace)
{
    char *charPtr = strchr(str, toSearch);
    if (charPtr != NULL) *charPtr = toReplace;
    return charPtr;
}

void lerLinha(char *str, int tamanhoMaximo, FILE *arquivo)
{
    fgets(str, tamanhoMaximo, arquivo);
    replaceChar(str, '\r', '\0');
    replaceChar(str, '\n', '\0');
}

int main() {
    char entrada [MAX_LINES][MAX_COLUMNS];
    int numEntrada = 0;
    
    // Leitura da entrada padrao
    do {
        lerLinha(entrada[numEntrada], MAX_COLUMNS, stdin);
    } while (isFim(entrada[numEntrada++]) == false);
    numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM

    int i, j;
    for(i = 0; i < numEntrada; i++){
        bool resp = palindromo(entrada[i]);
        if(resp)
            printf("SIM\n");
        else 
            printf("NAO\n");
    }
}


