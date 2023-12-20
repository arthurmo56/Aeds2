#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <ctype.h>

//tamanho maximo da string a ser lida
#define tam_max 350

//função que verifica se e palindromo
bool epalindromo(char frase[], int tam)
{
    //Compara caracteres em posicoes opostas da frase ou palavra
    int lesq = 0, ldir = tam - 1;
    while (lesq < ldir)
    {
        if (frase[lesq] != frase[ldir])
        {
            return false;
        }

        lesq++;
        ldir--;
    }

    return true;
}

int main()
{
    //Alocando memoria para fazer a leitura da String
    char *alc=(char*)malloc(tam_max*sizeof(char));
    //Ciclo que acaba quando FIM e digitado
    while(1)
    {
        scanf(" %[^\n\r]", alc);
        if(strcmp(alc,"FIM")==0)
            break;
        int tam=strlen(alc);

        printf("%s\n", epalindromo(alc,tam)? "SIM":"NAO");

    }

    //Libera o espaco alocado
    free(alc);
    return 0;
}
