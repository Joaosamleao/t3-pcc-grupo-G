# Problema do Carteiro Chinês – Circuito Euleriano em Dígrafo (Java + Algs4)  - GRUPO G

Este projeto implementa a busca de um **circuito euleriano em um dígrafo ponderado**, no contexto do **Problema do Carteiro Chinês**, utilizando Java e a biblioteca **algs4**.

O programa:

1. Lê um arquivo de entrada com o grafo já eulerizado
2. Constrói o dígrafo ponderado
3. Calcula os graus de entrada e saída
4. Verifica se o grafo está balanceado
5. Executa o algoritmo de **Hierholzer**
6. Imprime um circuito euleriano
7. Calcula o custo total do circuito

# Processo de Eulerização do Grafo

O grafo original deste projeto consiste em um Grafo Acíclico Direcionado (DAG) composto por 6 vértices (0 a 5) e 11 arestas. Por sua natureza direcional e acíclica, o grafo original não possuía um Circuito Euleriano válido, pois apresentava vértices desbalanceados — ou seja, o grau de entrada (arestas chegando) era diferente do grau de saída (arestas partindo).

Para viabilizar o roteamento Euleriano (passar por todas as arestas exatamente uma vez), foi necessário realizar o balanceamento (eulerização) adicionando **arestas artificiais de retorno** com peso/custo `0`.

### Análise de Saldo dos Vértices
A diferença matemática entre saídas e entradas (`Saldo = Saídas - Entradas`) revelou as seguintes necessidades:

* **Nós Provedores (Precisam enviar arestas para balancear, saldo negativo):**
    * **Nó A (Esquerdo):** Saldo -1 (Envia 1, Recebe 2)
    * **Nó C (Direito):** Saldo -1 (Envia 1, Recebe 2)
    * **Nó D (Baixo):** Saldo -1 (Envia 1, Recebe 2)
* **Nós Receptores (Precisam receber arestas para balancear, saldo positivo):**
    * **Nó B (Topo):** Saldo +1 (Envia 2, Recebe 1)

**A Solução Aplicada**
Para zerar o saldo de todo o sistema e garantir que `Grau de Entrada == Grau de Saída` para todos os nós, foram injetadas 4 novas arestas direcionadas no grafo, ligando provedores a receptores e criando caminhos de retorno:

* **C -> A (paralela):** Uma aresta artificial `C -> A` de peso `0` foi adicionada para balancear o Nó C.
* **D -> B (nova rota):** Uma aresta artificial `D -> B` de peso `0` foi adicionada para balancear o Nó D.
* **A -> B (paralela):** Duas arestas artificiais `A -> B` de peso `0` foram adicionadas para balancear o Nó A e o Nó B.

**Resultado:** A lista de arestas foi expandida de 6 para **10 arestas totais**. Com essa modificação estrutural, o grafo tornou-se fortemente conexo e perfeitamente balanceado, estando apto para a execução de algoritmos de busca de caminhos Eulerianos (como o Algoritmo de Hierholzer).

---

# Estrutura do Projeto

```
dados/
  entrada_eulerizada.txt
  entrada_original.txt

src/
  DirectedEdge.java
  DirectedEulerianCycleWeighted.java
  EdgeWeightedDigraph.java
  Main.java

```

---

# Requisitos

* Java 8 ou superior
* IntelliJ IDEA
* Biblioteca Algs4

Biblioteca utilizada:

* Algorithms, 4th Edition Library (algs4.jar)

Download da biblioteca:

* https://algs4.cs.princeton.edu/code/

---

# Passo a passo para executar no IntelliJ

## 1. Clonar ou baixar o repositório

Via Git:

```
git clone <url-do-repositorio>
```

Ou baixe o projeto como `.zip` e extraia.

---

# 2. Baixar a biblioteca algs4

Baixe o arquivo:

```
algs4.jar
```

Disponível em:

https://algs4.cs.princeton.edu/code/algs4.jar

Coloque o arquivo dentro do projeto (exemplo):

```
libs/algs4.jar
```

---

# 3. Abrir o projeto no IntelliJ

1. Abrir o IntelliJ
2. Clique em **Open**
3. Selecione a pasta do projeto

---

# 4. Adicionar a biblioteca ao projeto

No IntelliJ:

1. Vá em **File → Project Structure**
2. Clique em **Modules**
3. Vá na aba **Dependencies**
4. Clique no botão **+**
5. Escolha **JARs or directories**
6. Selecione o arquivo:

```
algs4.jar
```

7. Clique em **Apply**
8. Clique em **OK**

---

# 5. Executar o programa

1. Abra o arquivo:

```
Main.java
```

2. Clique com o botão direito no arquivo

3. Selecione:

```
Run 'Main'
```

---

# Arquivo de entrada

O programa lê o arquivo:

```
dados/entrada_eulerizada.txt
```

Formato esperado:

```
V
E
v w peso
v w peso
...
```

Onde:

* **V** = número de vértices
* **E** = número de arestas
* **v w peso** = aresta dirigida de `v` para `w`

Exemplo:

```
6
17
0 1 10
0 4 12
1 4 10
...
```

---

# Saída esperada

O programa exibirá:

* graus de entrada e saída
* verificação de balanceamento
* circuito euleriano
* custo total

Exemplo:

```
Vertices: 6
Arestas: 17

Grafo carregado.

Graus dos vertices:
a | entrada = 2 | saida = 2
b | entrada = 2 | saida = 2
...

O grafo está balanceado.
Executando algoritmo de Hierholzer...

Circuito: a e c f c e b d f ...

Custo total do circuito: 195
```

---

# Vídeo Explicativo (Link)
