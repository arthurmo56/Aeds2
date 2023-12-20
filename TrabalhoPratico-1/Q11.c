#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>

//tamanho maximo da string a ser lida
#define tam_max 350

//funcao que verifica se o palindromo
bool epalindromo(char frase[], int i)
{
    int tam = strlen(frase);

    if(i > tam/2)
        return true;
    //Compara caracteres em posicoes opostas da frase ou palavra
    else if(frase[i]== frase[tam-i-1])
        return epalindromo(frase, i+1);
    else
        return false;
}

int main()
{
    //Alocando memoria para fazer a leitura da String
    char *alc=(char*)malloc(tam_max*sizeof(char));
    //Ciclo que acaba quando FIM eh digitado
    while(1)
    {
        scanf(" %[^\n\r]", alc);
        if(strcmp(alc,"FIM")==0)
            break;
        printf("%s\n", epalindromo(alc, 0)? "SIM":"NAO");

    }

    free(alc);
    return 0;
}

