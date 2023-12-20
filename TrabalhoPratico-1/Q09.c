#include <stdio.h>
#include <stdlib.h>

//verifica se o numero tem decimal
int temDecimal(double valor)
{
    return valor == (int) valor;
}

int main()
{
    FILE *arquivo;
    int n;


    scanf("%d", &n);

    // Abre o arquivo texto em modo binario
    arquivo = fopen("valores.txt", "wb");

    // Leitura e escrita dos valores
    for (int i = 0; i < n; i++)
    {
        double valor;

        scanf("%lf", &valor);
        fwrite(&valor, sizeof(double), 1, arquivo);
    }

    // Fecha o arquivo
    fclose(arquivo);

    // Reabre o arquivo
    arquivo = fopen("valores.txt", "rb");

    // Le os valores e printa na tela
    fseek(arquivo, 0, SEEK_END);
    for (int i = 0; i < n; i++)
    {
        fseek(arquivo, -sizeof(double) * (i + 1), SEEK_END);
        double valor;
        fread(&valor, sizeof(double), 1, arquivo);
        if (temDecimal(valor))
        {
            int valorInteiro = (int) valor;
            printf("%d\n", valorInteiro);
        }
        else
        {
            printf("%g\n", valor);
        }
    }

    // Fecha o arquivo
    fclose(arquivo);

    return 0;
}

