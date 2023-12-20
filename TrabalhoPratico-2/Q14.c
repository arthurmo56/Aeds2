#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <sys/time.h>

#define maxtam 60
#define stringtam 110
#define tamvetor 1000

// Contagem de comparacoes
int comp = 0;
// Contagem de movimentacoes
int mov = 0;

// Atributos
typedef struct Jogador
{

    int id;
    char nome[maxtam];
    int altura;
    int peso;
    char universidade[maxtam];
    int anoNascimento;
    char cidadeNascimento[maxtam];
    char estadoNascimento[maxtam];

} Jogador;

// Construtores
Jogador novojogador(int id, char nome[], int altura, int peso, char universidade[], int anoNascimento, char cidadeNascimento[],
                    char estadoNascimento[])
{
    Jogador novo;
    novo.id = id;
    strcpy(novo.nome,nome);
    novo.altura = altura;
    novo.peso = peso;
    strcpy(novo.universidade,universidade);
    novo.anoNascimento = anoNascimento;
    strcpy(novo.cidadeNascimento,cidadeNascimento);
    strcpy(novo.estadoNascimento,estadoNascimento);
    return novo;
}
Jogador novojogadorPadrao()
{
    return novojogador(-1, "", 0, 0, "", 0, "", "");
}
// Setters
void setID(Jogador *a, int id)
{
    a->id = id;
}
void setNome(Jogador *a, char nome[])
{
    strcpy(a->nome,nome);
}
void setAltura(Jogador *a, int altura)
{
    a->altura = altura;
}
void setPeso(Jogador *a, int peso)
{
    a->peso = peso;
}
void setUniversidade(Jogador *a, char universidade[])
{
    strcpy(a->universidade,universidade);
}
void setAno(Jogador *a, int ano)
{
    a->anoNascimento = ano;
}
void setCidade(Jogador *a, char cidade[])
{
    strcpy(a->cidadeNascimento,cidade);
}
void setEstado(Jogador *a, char estado[])
{
    strcpy(a->estadoNascimento,estado);
}

// Getters
int getId(Jogador *a)
{
    return a->id;
}
char* getNome(Jogador *a)
{
    return a->nome;
}
int getAltura(Jogador *a)
{
    return a->altura;
}
int getPeso(Jogador *a)
{
    return a->peso;
}
char* getUniversidade(Jogador *a)
{
    return a->universidade;
}
int getAno(Jogador *a)
{
    return a->anoNascimento;
}
char* getCidade(Jogador *a)
{
    return a->cidadeNascimento;
}
char* getEstado(Jogador *a)
{
    return a->estadoNascimento;
}

// Clone
Jogador clone(Jogador *a)
{
    Jogador copia;
    copia.id = a->id;
    strcpy(copia.nome,a->nome);
    copia.altura = a->altura;
    copia.peso = a->peso;
    strcpy(copia.universidade,a->universidade);
    copia.anoNascimento = a->anoNascimento;
    strcpy(copia.cidadeNascimento,a->cidadeNascimento);
    strcpy(copia.estadoNascimento,a->estadoNascimento);
    return copia;
}

// Imprimir
void imprimir(Jogador *jogador)
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           jogador->id, jogador->nome, jogador->altura, jogador->peso,
           jogador->anoNascimento, jogador->universidade,
           jogador->cidadeNascimento, jogador->estadoNascimento);
}


// Ler
void ler(Jogador *a)
{
    FILE *file;
    char linha[200]; // Tamanho m�ximo de uma linha
    char *nomeArquivo = "/tmp/players.csv";

    file = fopen(nomeArquivo, "r");

    if (file == NULL)
    {
        perror("Erro ao abrir o arquivo");
        exit(1);
    }

    int primeiraLinha = 1; // Flag para controlar a primeira linha

    while (fgets(linha, sizeof(linha), file) != NULL)
    {
        if (primeiraLinha)
        {
            primeiraLinha = 0;
            continue; // Ignora a primeira linha com cabe�alhos
        }
        int i, j, len;
        len = strlen(linha);

        // Percorra a string e verifique se h� duas v�rgulas consecutivas
        for (i = 0; i < len - 1; i++)
        {
            if (linha[i] == ',' && linha[i + 1] == ',')
            {
                // Desloque todos os caracteres � direita da segunda v�rgula
                // para abrir espa�o para o espa�o em branco
                for (j = len; j > i + 1; j--)
                {
                    linha[j] = linha[j - 1];
                }

                // Insira um espa�o em branco no lugar das duas v�rgulas
                linha[i + 1] = ' ';

                // Aumente o tamanho da string
                len++;
            }
        }


        char *token;
        int id;

        linha[strcspn(linha, "\n\r")] = '\0';

        token = strtok(linha, ",");
        id = atoi(token);

        if (id == a->id)
        {
            // O jogador com o ID desejado foi encontrado

            token = strtok(NULL, ","); // Nome do jogador
            strcpy(a->nome, token);

            token = strtok(NULL, ","); // Altura do jogador
            a->altura = atoi(token);

            token = strtok(NULL, ","); // Peso do jogador
            a->peso = atoi(token);

            token = strtok(NULL, ","); // Universidade do jogador
            if (token != NULL && strcmp(token," ") && strlen(token) > 0)
            {
                strcpy(a->universidade, token);
            }
            else
            {
                strcpy(a->universidade, "nao informado");
            }
            token = strtok(NULL, ","); // Ano de nascimento do jogador
            if (token != NULL && strlen(token) > 0)
            {
                a->anoNascimento = atoi(token);
            }
            else
            {
                a->anoNascimento = 0; // Defina um valor padr�o, se o campo estiver vazio ou nulo
            }

            token = strtok(NULL, ","); // Cidade de nascimento do jogador
            if (token != NULL && strcmp(token," ") && strlen(token) > 0)
            {
                strcpy(a->cidadeNascimento, token);

            }
            else
            {
                strcpy(a->cidadeNascimento, "nao informado");
            }

            token = strtok(NULL, ","); // Estado de nascimento do jogador
            if (token != NULL && strcmp(token," ") && strlen(token) > 0)
            {
                strcpy(a->estadoNascimento, token);

            }
            else
            {
                strcpy(a->estadoNascimento, "nao informado");
            }

            break; // para de ler o arquivo assim que encontrar o jogador
        }
    }

    fclose(file);
}

// Swap para a ordena��o
void swap(Jogador *a, Jogador *b)
{

    Jogador tmp = *a;
    *a = *b;
    *b = tmp;
}

int getMax(Jogador *array, int n) {
    int maior = array[0].id;

    for (int i = 1; i < n; i++) {
        if(maior < array[i].id){
            maior = array[i].id;
            comp++;
        }
        comp++;
    }
    return maior;
}
void radcountingSort(Jogador *array, int n, int exp) {
        int count[10];
        Jogador output[n];

        //Inicializar cada posicao do array de contagem 
        for (int i = 0; i < 10; count[i] = 0, i++);

        //Agora, o count[i] contem o numero de elemento iguais a i
        for (int i = 0; i < n; i++) {
            count[(array[i].id/exp) % 10]++;
        }

        //Agora, o count[i] contem o numero de elemento menores ou iguais a i
        for (int i = 1; i < 10; i++) {
            count[i] += count[i-1];
        }

        //Ordenando
        for (int i = n-1; i >= 0; i--) {
            output[count[(array[i].id/exp) % 10] - 1] = array[i];
            mov++;
            count[(array[i].id/exp) % 10]--;
        }

        //Copiando para o array original
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
            mov++;
        }
    }

void radixsort(Jogador *array, int n) {
    //Array para contar o numero de ocorrencias de cada elemento
    int max = getMax(array, n);
    for (int exp = 1; max/exp > 0; exp *= 10) {
            radcountingSort(array, n, exp);
    }
}

int main()
{
    struct timeval inicio, fim;
    double tempo_decorrido;
    gettimeofday(&inicio, NULL); // Marca o inicio do tempo

    Jogador vetor[tamvetor];
    int n =0;
    char search[10];

    // Preenchendo o vetor
    while(1)
    {

        fgets(search, sizeof(search), stdin);
        if(!strcmp(search,"FIM\n"))
            break;
        if(n>=tamvetor)
        {
            printf("vetor cheio");
            return 1;
        }
        vetor[n] = novojogadorPadrao();
        setID(&vetor[n], atoi(search));
        ler(&vetor[n]);
        n++;
    }
    radixsort(vetor,n);
    for(int i=0; i<n; i++)
    {
        imprimir(&vetor[i]);
    }

    gettimeofday(&fim, NULL); // Marca o fim do tempo

    // Calcula o tempo decorrido em segundos e microssegundos
    tempo_decorrido = (fim.tv_sec - inicio.tv_sec) + (double)(fim.tv_usec - inicio.tv_usec) / 1000000.0;
    FILE *arquivo;
    arquivo = fopen("matrícula_radixsort.txt","w");
    fprintf(arquivo,"813168\t%d comparacoes\t%d movimentacoes\t%f segundos",  comp, mov, tempo_decorrido);
    fclose(arquivo);
    return 0;

}