#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#define max 750

bool recursivo(char* s, int i);
bool isPalindromo(char* s);
bool ehFim(char* str);

int main(){
    char str[max][max];
    int tam=0;
    do{
        fgets(str[tam], max, stdin);
        str[tam][strlen(str[tam]) - 1] = '\0';
    }while(ehFim(str[tam++])==false);
    tam--;
    for(int i=0;i<tam;i++){
        if(isPalindromo(str[i])){
            printf("SIM\n");
        }
        else{
            printf("NAO\n");
        }
    }
}
bool ehFim(char* str){
    bool resp=false;
    if(str[0]=='F' && str[1]=='I' && str[2]=='M') resp=true;
    return resp;
}
bool isPalindromo(char* s){
    return recursivo(s, 0);
}
bool recursivo(char* s, int i){
    bool resp=false;
    int k=strlen(s)-i-1;
    int j=1+i;
    if(i==strlen(s)/2){
        resp=true;
    }
    else if(s[i]==s[k] && recursivo(s, j)){
        resp=true;
    }
    return resp;
}