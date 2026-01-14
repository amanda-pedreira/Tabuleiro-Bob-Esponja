# Jogo de Tabuleiro — Bob Esponja (Java)

Projeto acadêmico desenvolvido para a disciplina de **Algoritmo e Programação**, com o objetivo de aplicar, na prática, os principais fundamentos da linguagem **Java**, utilizando um jogo de tabuleiro inspirado no universo do **Bob Esponja**.

---

## 🎮 Sobre o jogo

O jogo é executado **no console** e funciona em formato de tabuleiro (matriz 8x8), onde dois jogadores controlam personagens clássicos do desenho:
- **B** — Bob Esponja  
- **P** — Plancton
- 
Objetivo: **chegar primeiro até a Fórmula do Hambúrguer de Siri**, desviando de obstáculos e do adversário.
Durante o jogo, os jogadores enfrentam:
-  **Água-vivas**, que fazem o personagem perder vidas  
-  **Colisões entre personagens**, onde ambos perdem vida  
-  **Fator sorte**, com o número de casas a serem andadas sendo sorteado a cada turno  

Quando a fórmula é encontrada, ainda existe um **desafio final** para definir o vencedor.

---

## 🧩 Estrutura do tabuleiro

O tabuleiro é representado por um **array bidimensional (matriz)**, onde cada posição pode conter:

- `B` — Bob Esponja  
- `P` — Plancton  
- `A` — Água-viva  
- `F` — Fórmula do Hambúrguer de Siri  
- `~` — Água (caminho livre)

O estado do tabuleiro é atualizado dinamicamente e exibido no console a cada jogada.

---

## ☕ O que foi aplicado em Java

Neste projeto, colocamos em prática os principais conceitos da linguagem Java trabalhados em sala de aula, incluindo:

- Uso e manipulação de **arrays bidimensionais (matrizes)**
- **Estruturas condicionais** para definir regras e eventos do jogo
- **Laços de repetição** para controle do fluxo e execução contínua
- Implementação de **funções (métodos)** com passagem de parâmetros e retorno
- Leitura e validação de **entradas do usuário** com `Scanner`
- Controle de estado do jogo (vidas, posições, turnos)
- Atualização e exibição dinâmica do tabuleiro no console
- Organização e legibilidade do código com comentários explicativos

O foco do projeto não foi aprender Java através de jogos, mas sim **transformar os conceitos aprendidos em sala em uma aplicação funcional**, usando o jogo como meio.

---

