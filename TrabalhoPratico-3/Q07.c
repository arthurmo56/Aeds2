#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define maxtam 60
#define stringtam 110
#define TAMVET 200

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
    printf("## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
           jogador->nome, jogador->altura, jogador->peso,
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
    for (i = 0; i < len - 1; i++) {
        if (linha[i] == ',' && linha[i + 1] == ',') {
            // Desloque todos os caracteres � direita da segunda v�rgula
            // para abrir espa�o para o espa�o em branco
            for (j = len; j > i + 1; j--) {
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

// Structs
typedef struct Celula{
    Jogador jogador;
    struct Celula *prox;

}Celula;

Celula *novaCelula(Jogador jogador){
    Celula *nova = (Celula*)malloc(sizeof(Celula));
    nova->jogador = jogador;
    nova->prox = NULL;
    return nova;
}
typedef struct FilaCirc{
    Celula *primeiro;
    Celula *ultimo;


}FilaCirc;

FilaCirc filacirc(int tam){
    FilaCirc a;
    a.primeiro = novaCelula(novojogadorPadrao());
    a.ultimo = a.primeiro;
    for(int i = 0; i < tam; i++){
        a.ultimo->prox = novaCelula(novojogadorPadrao());
        a.ultimo = a.ultimo->prox;
    }
    a.ultimo->prox = a.primeiro;
    a.ultimo = a.ultimo->prox;
    return a;

}
 void Inserir(FilaCirc *a, Jogador jogador){

    if(a->ultimo->prox == a->primeiro){
        a->primeiro = a->primeiro->prox;
    }
    ler(&jogador);
    a->ultimo->jogador = jogador;
    a->ultimo = a->ultimo->prox;

 }

 Jogador Remover(FilaCirc *a){
    if(a->primeiro == a->ultimo){
            printf("Erro ao Remover!");
    }
    Jogador resp = a->primeiro->jogador;
    a->primeiro = a->primeiro->prox;
    return resp;
 }

 int calcMedia(FilaCirc *a){

    Celula *i = a->primeiro;
    int count = 0;
    float media = 0;
    if (a->primeiro != a->ultimo) {
        while (i != a->ultimo) {
            media += getAltura(&i->jogador);
            i = i->prox;
            count++;
        }
        media /= count;
        if (media - (int)media > 0.4) {
            media += 1;
        }
        return (int)media;
    } else {
        return 0;
    }
}

void mostrar(FilaCirc *a){
    Celula *i = a->primeiro;
    int count = 0;
    while(i != a->ultimo){
        printf("[%d] ", count);
        imprimir(&i->jogador);
        i = i->prox;
        count++;
    }
}




int main()
{

    FilaCirc jog = filacirc(5);
    char search[10];
    int media;
    while(1)
    {
        fgets(search,sizeof(search),stdin);
        if(!strcmp(search,"FIM\n"))
            break;
        Jogador a = novojogadorPadrao();
        setID(&a,atoi(search));
        Inserir(&jog, a);
        media = calcMedia(&jog);
        printf("%d\n",media);
    }
    int qtdreg;
    scanf("%d", &qtdreg);
    for(int i = 0; i <= qtdreg; i++){
        int num;
        Jogador a = novojogadorPadrao();
        fgets(search,sizeof(search),stdin);
        char *token = strtok(search, " ");
        if(token[0] == 'I'){
            num =atoi(strtok(NULL," "));
            setID(&a, num);
            Inserir(&jog,a);
            media = calcMedia(&jog);
            printf("%d\n",media);


        }else if(token[0] == 'R'){
            a = Remover(&jog);
            printf("(R) %s\n", getNome(&a));

        }
    }
    mostrar(&jog);



    return 0;
}
