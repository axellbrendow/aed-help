#include <stdio.h>
#include <stdlib.h>
#include <cstring>
#define bool short
#define true 1
#define false 0

bool isFim(char *s) {
    return ( strlen ( s ) >= 3 && s [0] == 'F' &&
        s[1] == 'I' && s[2] == 'M' ) ;
}

bool palindromo(char *s) {
    char comp[200];
    bool resp = 1;
    int i, j;
    for(i = 0, j = strlen(s)-2; i < strlen(s)-1; i++, j--){
        comp[j] = s[i];
    }

    for(i = 0; i < strlen(s)-1; i++){
        if(s[i] != comp[i]){
            resp = 0;
            i = strlen(s);
        }
    }

    return resp;
}

void lerLinha(char *string, int tamanhoMaximo, FILE *arquivo)
{
    fgets(string, tamanhoMaximo, arquivo);
    int tamanhoReal = strlen(string);
    string[tamanhoReal - 1] = '\0';
}

int main() {
    char entrada [700][200];
    int numEntrada = 0;
    
    // Leitura da entrada padrao
    do {
        lerLinha(entrada[numEntrada], 200, stdin);
    } while (isFim(entrada[numEntrada++]) == false);
    numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM

    int i, j;
    for(i = 0; i < numEntrada; i++){
        palindromo(entrada[i]);
        bool resp = palindromo(entrada[i]);
        if(resp)
            printf("SIM\n");
        else 
            printf("NAO\n");
    }
}


