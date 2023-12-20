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

typedef struct CelulaDupla
{

    Jogador jogador;
    struct CelulaDupla* prox;
    struct CelulaDupla* ant;

} CelulaDupla;

CelulaDupla* novaCelulaDupla(Jogador jogador)
{
    CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
    ler(&jogador);
    nova->jogador = jogador;
    nova->ant = nova->prox = NULL;
    return nova;
}



CelulaDupla* primeiro;
CelulaDupla* ultimo;

void start ()
{
    primeiro = novaCelulaDupla(novojogadorPadrao());
    ultimo = primeiro;
}

void inserirFim(Jogador x)
{
    ultimo->prox = novaCelulaDupla(x);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
}

void swap(CelulaDupla *i, CelulaDupla *j)
{
    Jogador tmp = i->jogador;
    i->jogador = j->jogador;
    j->jogador = tmp;
}

int verificar(CelulaDupla *esq, CelulaDupla *dir)
{
    // 1 se i <= j
    if( esq == NULL || dir == NULL )
    {
        return 0;
    }
    int resp;
    int i = 0;
    int j = i;
    for(CelulaDupla *a = primeiro; a != esq; a = a->prox, i++);
    for(CelulaDupla *a = primeiro; a != dir; a = a->prox, j++);
    if(i <= j)
        resp = 1;
    else
        resp = 0;

    return resp;
}

int verificar2(CelulaDupla *esq, CelulaDupla *dir)
{
    // 1 se i < j
    if( esq == NULL || dir == NULL )
    {
        return 0;
    }
    int resp;
    int i = 0;
    int j = i;
    for(CelulaDupla *a = primeiro; a != esq; a = a->prox, i++);
    for(CelulaDupla *a = primeiro; a != dir; a = a->prox, j++);
    if(i < j)
        resp = 1;
    else
        resp = 0;

    return resp;
}

void quicksort(CelulaDupla *esq, CelulaDupla *dir)
{

    CelulaDupla* i = esq;
    CelulaDupla* j = dir;
    Jogador pivo = i->jogador;
    while (verificar(i, j))
    {
        comp++;
        while(strcmp(getEstado(&i->jogador),getEstado(&pivo)) < 0 ||
                (strcmp(getEstado(&i->jogador),getEstado(&pivo)) == 0 &&  strcmp(getNome(&i->jogador),getNome(&pivo)) < 0 ))
        {
            i = i->prox;
            comp++;
        }
        while (strcmp(getEstado(&j->jogador),getEstado(&pivo)) > 0 ||
                (strcmp(getEstado(&j->jogador),getEstado(&pivo)) == 0 &&  strcmp(getNome(&j->jogador),getNome(&pivo)) > 0 ))
        {
            j = j->ant;
            comp++;
        }
        comp++;
        if (verificar(i, j))
        {
            swap(i, j);
            mov++;
            i = i->prox;
            j = j->ant;
        }
    }
    comp++;
    if (verificar2(esq, j))
        quicksort(esq, j);
    comp++;
    if (verificar2(i, dir))
        quicksort(i, dir);

}

int main()
{
    struct timeval inicio, fim;
    double tempo_decorrido;
    gettimeofday(&inicio, NULL); // Marca o inicio do tempo

    start();
    char search[10];

    // Preenchendo o vetor
    while(1)
    {

        fgets(search, sizeof(search), stdin);
        if(!strcmp(search,"FIM\n"))
            break;
        Jogador a = novojogadorPadrao();
        setID(&a, atoi(search));
        inserirFim(a);

    }
    quicksort(primeiro->prox, ultimo);

    for(CelulaDupla* i=primeiro->prox; i != NULL; i = i->prox)
    {
        imprimir(&i->jogador);
    }

    gettimeofday(&fim, NULL); // Marca o fim do tempo

    //Calcula o tempo decorrido em segundos e microssegundos
    tempo_decorrido = (fim.tv_sec - inicio.tv_sec) + (double)(fim.tv_usec - inicio.tv_usec) / 1000000.0;
    FILE *arquivo;
    arquivo = fopen("matrícula_quicksort2.txt","w");
    fprintf(arquivo,"813168\t%d comparacoes\t%d movimentacoes\t%f segundos",  comp, mov, tempo_decorrido);
    fclose(arquivo);
    return 0;

}
