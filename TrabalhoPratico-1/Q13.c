#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>

//tamanho maximo da string a ser lida
#define tam_max 350

//metodo que faz a alteracao
char* alteracao(char* frase, int i, char letra, char letraS) {


    if(i<0)
        return frase;

    if(frase[i]==letra)
        frase[i] = letraS;

    return alteracao(frase, i-1, letra, letraS);

}
int main()
{
    srand(4);
    char letra = 'a' + (rand() % 26);
    char letraS = 'a' + (rand() % 26);
    //Alocando memoria para fazer a leitura da String
    char *alc=(char*)malloc(tam_max*sizeof(char));
    //Ciclo que acaba quando FIM eh digitado
    while(1)
    {

        scanf(" %[^\n\r]", alc);
        if(strcmp(alc,"FIM")==0)
            break;
        int tam = strlen(alc);
        alc[tam]='\0';
        printf("%s\n", alteracao(alc, tam-1, letra, letraS));

    }

    free(alc);
    return 0;
}

