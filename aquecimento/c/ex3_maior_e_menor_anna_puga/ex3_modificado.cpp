#include <iostream>
using namespace std;

int numComparacoes = 0;

int main(){
    int V[10] = {3,4,5,1,2,8,9,20,7,6};
    int maior = V[0];
    int menor = maior;

    int i;
    for(i = 0; i < 10; i++){
        if(V[i] > maior) {
            maior = V[i];
            numComparacoes++;
        }
		else if(V[i] < menor) {
            menor = V[i];
            numComparacoes += 2; // if e o else if
        }
        else numComparacoes += 2; // if e o else if
    }

    cout << "maior: " << maior << endl;
    cout << "menor: " << menor << endl;
    cout << "numComparacoes: " << numComparacoes << endl;
}