#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include <string.h>
#include <ctype.h>
#define MAX_LENGTH 500

bool isFim(char s[MAX_LENGTH]) {
    return(strlen(s) == 4 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

bool isVogal( char s[MAX_LENGTH]) {

    bool resp = false;
    int i = 0;
    int ctrl = 0;

    while( i < strlen(s) - 1) {
        s[i] = toupper(s[i]);

        if(s[i] == 'A' || s[i] == 'E' || s[i] == 'I' || s[i] == 'O' || s[i] == 'U' ) {
            ctrl++;
        }
        if(s[i] == ' ') {
            ctrl++;
        }

        i++;
    }
    if (ctrl ==  strlen(s) - 1) {
        resp = true;
    }
    return resp;
}

bool isConsoante(char s[MAX_LENGTH]) {

    bool resp;
    int i = 0;
    int ctrl = 0;

    while (i < strlen(s) - 1) {
        s[i] = toupper(s[i]);
        if (s[i] == 'A' || s[i] == 'E' || s[i] == 'I' || s[i] == 'O'
                || s[i] == 'U') {
            resp = false;
        }

        else if (s[i] >= 'B' && s[i] <= 'Z') {
            ctrl++;
        }

        else if (s[i] == ' ') {
            ctrl++;
        }
        i++;
    }

    if (ctrl == strlen(s) - 1) {
        resp = true;
    }
    return resp;
}

bool isInteiro(char s[MAX_LENGTH]) {

    bool resp = false;
    int i = 0;
    int ctrl = 0;

    while (i < strlen(s) - 1) {
        if (s[i] >= '0' && s[i] <= '9') {
            ctrl++;
        } else if (s[i] == ' ') {
            ctrl++;
        }
        i++;
    }

    if (ctrl == strlen(s) - 1) {
        resp = true;
    }

    return resp;
}

bool isReal(char s[MAX_LENGTH]) {

    bool resp = false;
    int i = 0;
    int ctrl = 0;
    int point = 0;

    while (i < strlen(s) - 1) {
        if (s[i] >= '0' && s[i] <= '9') {
            ctrl++;
        } else if (s[i] == ' ') {
            ctrl++;
        } else if (s[i] == '.' || s[i] == ',') {
            ctrl++;
            point++;
        }
        i++;
    }

    if (ctrl == strlen(s) - 1 && point == 1) {
        resp = true;
    }else if(isInteiro(s)){
      resp = true;
    }

    return resp;
}

int main() {
    char str[MAX_LENGTH];

    fgets(str,MAX_LENGTH, stdin);

    while(!isFim(str)) {

        if(isVogal(str)) {
            printf("SIM ");
        } else
            printf("NAO ");

        if(isConsoante(str)) {
            printf("SIM ");
        } else
            printf("NAO ");

        if(isInteiro(str)) {
            printf("SIM ");
        } else
            printf("NAO ");

        if(isReal(str)) {
            printf("SIM\n");
        } else
            printf("NAO\n");

        fgets(str,MAX_LENGTH, stdin);
    } 

    return 0;
}