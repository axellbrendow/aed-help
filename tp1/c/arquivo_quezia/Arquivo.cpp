#include <stdio.h>
#include <stdlib.h>
#include <string.h>



void EscreveNumeros(FILE *arq, char *num){

    int tam = 0; //quantidade de numeros a serem lidos
    if(num[0] == '.'){ 

    tam = strlen(num) + 1;
    char *aux = (char *)malloc(sizeof(char) * tam); //ponteiro auxiliar para alocar espaço para o total de numeros

    aux[0] = '0';

    for(int i = 0; i < tam; i++){
           aux[i + 1] = num[i];
    }

     fprintf(arq, aux);

    }else{

      fprintf(arq, num);
    }

    fprintf(arq, "\n");
    fflush(arq);
}

void LerInvertido(FILE *arq, int tam){

    char Max[100][64];

    char *aux = (char *)malloc(sizeof(char) * 64); //ponteiro auxiliar para receber os numeros do arquivo escrito.

    for(int i = 0; i < tam; i++){
        fgets(aux, 64, arq);
        strcpy(Max[i], aux);
    }

    for(int i = tam - 1; i >= 0; i--){
        printf("%s", Max[i]);
    }

    free(aux);

}

int main(){

    char Num[1024];

    FILE *escreve = fopen("FILE arquivo.txt", "w");
    FILE *Ler = fopen("FILE arquivo.txt", "r");


    scanf("%[^\n]%*c", Num);

    int qtd = atoi(Num);

    for(int i = 0; i < qtd; i++){
        scanf("%[^\n]%*c", Num);
        EscreveNumeros(escreve, Num);
    }

  LerInvertido(Ler, qtd);


  fclose(escreve);
  fclose(Ler);

    return 0;
}
