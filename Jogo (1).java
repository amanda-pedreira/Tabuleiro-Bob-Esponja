import java.util.Scanner;

public class Jogo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        // VERSÃO INICIAL 

        // TABULEIRO (nosso array dimensional)
        // B - Bob Esponja
        // P - Plancton
        // A - Água-viva (perde vida se tocar uma vez, e derrota se tocar duas vezes)
        // F - Fórmula do Hamburguer de Siri (ganha se pegar)
        // ~ - Água normal
        

        // Criamos primeiro como ficaria o tabuleiro, mais "visual" inicialmente.
        String[][] tabuleiro = {
            {"B","~","A","~","~","A","~","P"},  
            {"~","A","~","~","~","~","A","~"},  
            {"~","~","A","~","A","~","~","~"},  
            {"~","~","~","A","~","~","A","~"},  
            {"~","A","~","~","~","A","~","~"},  
            {"~","~","A","~","A","~","~","A"},  
            {"A","~","~","~","~","~","A","~"},  
            {"~","A","~","F","A","~","~","~"}   
        };

        // Aqui falamos onde quero que cada personagem comece, e essa variavel vai ser usada pra ir guardando a posição que eles forem tomando durante o jogo, aqui está apenas a inicial!
        int bobLinhaDoTabuleiro = 0;
        int bobColunaDoTabuleiro = 0;

        int planctonLinhaDoTabuleiro = 0;
        int planctonColunaDoTabuleiro = 7;

        //  VERSÃO 2.0 
        // Aqui decidimos adicionar a função deles terem vidas e perder elas conforme forem tocando em água-viva ou tocar no outro personagem. Se tocar na água-viva, o personagem fica -1 vida; se tocar em outro personagem, ambos ficam -1 vida.
        int vidasBob = 2;
        int vidasPlancton = 2; 


        
        // Apresentação do Jogo
        System.out.println("=======================================");
        System.out.println("     BEM-VINDO AO JOGO DO BOB ESPONJA!");
        System.out.println("=======================================");
        System.out.println();
        System.out.println("Objetivo: Pegue a fórmula do Hambúrguer de Siri");
        System.out.println("antes do seu oponente, evitando água-vivas e colisões!");
        System.out.println();
        System.out.println("Personagens:");
        System.out.println("B - Bob Esponja  - Começa com 2 vidas");
        System.out.println("P - Plancton    - Começa com 2 vidas");
        System.out.println("A - Água-viva   - Causa dano ao tocar");
        System.out.println("F - Fórmula     - Objetivo final");
        System.out.println("~ - Água normal - Pode andar livremente");
        System.out.println();
        System.out.println("Como jogar:");
        System.out.println("- A cada turno, o número de casas que você pode andar é sorteado (1 ou 2).");
        System.out.println("- Escolha a direção:");
        System.out.println("  1 - Direita   2 - Esquerda   3 - Cima   4 - Baixo");
        System.out.println("- Se pisar na água-viva ou colidir com o outro jogador, perde 1 vida.");
        System.out.println("- Quem pegar a fórmula primeiro responde a pergunta final!");
        System.out.println();
        System.out.println("Vamos começar!");
        System.out.println("---------------------------------------");
        System.out.println();

        // Aqui colocamos a opção para o jogador decidir quem começa, pra que os jogadores decidam na vida real e fique mais fácil na hora de jogar. E se a entrada for inválida (digitarem coisas aleatorias) o Bob começa por padrão.
        System.out.println("Quem começa o jogo? (b para Bob Esponja / p para Plancton)");

        // turnoDoJogador serve para guardar quem está jogando no momento; colocamos toLowerCase pra garantir que 'B'/'P' ou 'b'/'p' sejam iguais.
        String turnoDoJogador = scanner.next().toLowerCase();

        if (!turnoDoJogador.equals("b") && !turnoDoJogador.equals("p")) {
            System.out.println("Você digitou uma opção inválida. Bob Esponja começará o jogo por padrão.");
            turnoDoJogador = "b"; // Bob começa por padrão
        }


        // Vamos começar o jogo! Entra num loop enquanto o jogo não terminar (ser false)
        boolean jogoAindaNaoTerminou = true;

        while (jogoAindaNaoTerminou) {

            // Sempre volta pra cá quando troca o turno
            // Aqui está chamando a função (lá debaixo) que imprime o tabuleiro, também está mostrando as vidas restantes
            System.out.println("\n=== TABULEIRO ATUAL ===");
            System.out.println("");
            imprimirTabuleiro(tabuleiro);
            System.out.println();
            System.out.println("Vidas do Bob Esponja: " + vidasBob + " | Vidas do Plancton: " + vidasPlancton);
            System.out.println();

            //  VERSÃO 3.0
            // Aqui decidimos pesquisar uma forma de sortear números no java, pra adicionar um elemento de sorte no jogo, pra ser como uma especie de dado. O jogador não sabe quantas casas vai andar, pode ser 1 ou 2 (isso pode facilitar ou dificultar a vida dele)
            int numeroCasasParaAndar = (int)(Math.random() * 2) + 1; 


            // Aqui o jogador que está no "turnoDoJogador" é informado de quantas casas vai andar (sorteio - versão 3.0)
            if (turnoDoJogador.equals("b")) {
                System.out.println("O jogador Bob Esponja vai andar " + numeroCasasParaAndar + " casa(s)!");
            } else {
                System.out.println("O jogador Plancton vai andar " + numeroCasasParaAndar + " casa(s)!");
            }


            // Aqui é para o jogador escolher para onde mover o personagem
            System.out.println("Escolha a direção:");
            System.out.println("1 - Direita");
            System.out.println("2 - Esquerda");
            System.out.println("3 - Cima");
            System.out.println("4 - Baixo");

            int direcaoEscolhida = scanner.nextInt();

            if (direcaoEscolhida != 1 && direcaoEscolhida != 2 && direcaoEscolhida != 3 && direcaoEscolhida != 4) {
                System.out.println("Comando inválido!");
            }


            //  MOVIMENTAÇÃO DO JOGADOR 

            // Aqui verificamos de quem é o turno, e movemos o personagem correspondente 
            if (turnoDoJogador.equals("b")) {  // TURNO DO BOB

                // Criamos isso de guardar a posição antiga, pois quando ele queima na água-viva ou bate no outro personagem, ele volta pra posição anterior
                int linhaAntesDeAndar = bobLinhaDoTabuleiro;   
                int colunaAntesDeAndar = bobColunaDoTabuleiro;


                // Aqui temos um array que chama a função 'mover' (lá embaixo) que recebe essas cinco informações... (na função, define a posição do personagem fazendo um comando para cada direção que ele escolher)
                int[] novaPosicao = moverPersonagem(
                    bobLinhaDoTabuleiro, 
                    bobColunaDoTabuleiro, 
                    direcaoEscolhida, 
                    numeroCasasParaAndar, 
                    tabuleiro
                );

               
                // Aqui atualizamos o array 'tabuleiro' com a nova posição do Bob, pra ir indicando onde ele está agora: 
                // tabuleiro[][] = tabuleiro[0][1] = tabuleiro[linha][coluna] ===  bobLinhaDoTabuleiro = novaPosicao[0]; entra em linha/0 e bobColunaDoTabuleiro = novaPosicao[1]; entra em coluna/1
                bobLinhaDoTabuleiro = novaPosicao[0]; 
                bobColunaDoTabuleiro = novaPosicao[1]; 

                
                // VERSÃO 2.0 (se bater no outro personagem ou pisar na água-viva perde vida, antes apenas acabava o jogo)

                // Criamos um if que verifica primeiro se a posição que ele foi, foi a mesma que a do Plancton, ambos perdem 1 vida
                if (bobLinhaDoTabuleiro == planctonLinhaDoTabuleiro &&
                    bobColunaDoTabuleiro == planctonColunaDoTabuleiro) {

                    System.out.println("");
                    System.out.println("BOB ESPONJA BATEU NO PLANCTON! Os dois perdem 1 vida!");
                    System.out.println("");
                    
                    vidasBob--;
                    vidasPlancton--;

                    // Aqui faz ele voltar para posição anterior
                    bobLinhaDoTabuleiro = linhaAntesDeAndar;
                    bobColunaDoTabuleiro = colunaAntesDeAndar;
                }

                // Se não, verifica se a posição que ele foi, foi a mesma que a da água-viva, Bob perde 1 vida
                else if (tabuleiro[bobLinhaDoTabuleiro][bobColunaDoTabuleiro].equals("A")) {
                    System.out.println("Bob Esponja pisou em uma água-viva!");
                    System.out.println("");
                    vidasBob--;

                    bobLinhaDoTabuleiro = linhaAntesDeAndar;
                    bobColunaDoTabuleiro = colunaAntesDeAndar;
                }


                // E novamente, se não for nenhuma das duas opções, verifica se ele pegou a fórmula (posição da fórmula no tabuleiro for igual a F)
                // VERSÃO 4.0 - Decidimos colocar uma pergunta final após pegar a fórmula, pra dar uma dificuldade a mais no jogo
                else if (tabuleiro[bobLinhaDoTabuleiro][bobColunaDoTabuleiro].equals("F")) {

                    // chamando a função que faz a pergunta final (versão 4.0)
                    fazerPerguntaFinal("Bob", scanner);
                    break; // Encerra o jogo após pegar fórmula
                }

            } else {  // TURNO DO PLANCTON

                int linhaAntesDeAndar = planctonLinhaDoTabuleiro;   
                int colunaAntesDeAndar = planctonColunaDoTabuleiro;


                // Aqui temos um array que chama a função 'mover' (lá embaixo) que recebe essas cinco informações... (na função, define a posição do personagem fazendo um comando para cada direção que ele escolher)
                int[] novaPosicao = moverPersonagem(
                    planctonLinhaDoTabuleiro, 
                    planctonColunaDoTabuleiro, 
                    direcaoEscolhida, 
                    numeroCasasParaAndar, 
                    tabuleiro
                );

               
                // tabuleiro[][] = tabuleiro[0][1] = tabuleiro[linha][coluna] === planctonLinhaDoTabuleiro = novaPosicao[0]; entra em linha/0 e planctonColunaDoTabuleiro = novaPosicao[1]; entra em coluna/1
                planctonLinhaDoTabuleiro = novaPosicao[0]; 
                planctonColunaDoTabuleiro = novaPosicao[1]; 

                
                // VERSÃO 2.0 (se bater no outro personagem ou pisar na água-viva perde vida, antes apenas acabava o jogo)

                // Criamos um if que verifica primeiro se a posição que ele foi, foi a mesma que a do Plancton, ambos perdem 1 vida
                if (bobLinhaDoTabuleiro == planctonLinhaDoTabuleiro &&
                    bobColunaDoTabuleiro == planctonColunaDoTabuleiro) {

                    System.out.println("");
                    System.out.println("PLANCTON BATEU NO BOB ESPONJA! Os dois perdem 1 vida!");
                    System.out.println("");
                    vidasBob--;
                    vidasPlancton--;

                    // Aqui faz ele voltar para posição anterior
                    planctonLinhaDoTabuleiro = linhaAntesDeAndar;
                    planctonColunaDoTabuleiro = colunaAntesDeAndar;
                }

                // Se não, verifica se a posição que ele foi, foi a mesma que a da água-viva, Plancton perde 1 vida
                else if (tabuleiro[planctonLinhaDoTabuleiro][planctonColunaDoTabuleiro].equals("A")) {
                    System.out.println("Plancton pisou em uma água-viva!");
                    System.out.println("");
                    vidasPlancton--;

                    planctonLinhaDoTabuleiro = linhaAntesDeAndar;
                    planctonColunaDoTabuleiro = colunaAntesDeAndar;
                }


                else if (tabuleiro[planctonLinhaDoTabuleiro][planctonColunaDoTabuleiro].equals("F")) {

                    fazerPerguntaFinal("Plancton", scanner);
                    break; // Encerra o jogo após pegar fórmula
                }

            }


            // Aqui verificamos como está a vida, pois se algum jogador já tiver perdido todas as vidas, o jogo acaba. (vidas - versão 2.0)
            if (vidasBob <= 0) {
                System.out.println("");
                System.out.println("Bob Esponja perdeu todas as vidas!");
                System.out.println("");

                // Como o Bob Esponja perdeu todas as vidas, o Plancton vence automaticamente, mas ainda precisa responder a pergunta final (perguntas - versão 4.0)
                fazerPerguntaFinal("Plancton", scanner);

                // para sair do loop do jogo.
                break;
                    
            }
            if (vidasPlancton <= 0) {
                System.out.println("");
                System.out.println("Plancton perdeu todas as vidas!");
                System.out.println("");

                // Como o Plancton perdeu todas as vidas, o Bob Esponja vence automaticamente, mas ainda precisa responder a pergunta final (perguntas - versão 4.0)
                fazerPerguntaFinal("Bob", scanner);

                // para sair do loop do jogo.
                break;
                    
            }


            // Aqui está chamando a função (lá debaixo) que limpa o tabuleiro, essa função serve para limpar o tabuleiro de Bob e Plancton, pra depois colocar eles nas novas posições.
            limparPersonagens(tabuleiro);
            // Aqui é pra definir as novas posições do Bob e Plancton no tabuleiro, encaixando nos "tabuleiro[linha][coluna]"
            tabuleiro[bobLinhaDoTabuleiro][bobColunaDoTabuleiro] = "B";
            tabuleiro[planctonLinhaDoTabuleiro][planctonColunaDoTabuleiro] = "P";

            // TROCA DE TURNO
            turnoDoJogador = turnoDoJogador.equals("b") ? "p" : "b";        }

    }









    // NOSSAS FUNÇÕES 


    public static int[] moverPersonagem(
        // Define as variaveis e arrays que a função vai receber
        int linhaAtual, 
        int colunaAtual, 
        int direcaoEscolhida, 
        int numeroCasasParaAndar, 
        String[][] tabuleiro
    ) {

        // Definimos novas variáveis para receber a nova posição. Antes de alterar o valor delas (com o movimento) é igual o atual
        int novaLinhaDepoisDoMovimento = linhaAtual;
        int novaColunaDepoisDoMovimento = colunaAtual;

        // Aqui fazemos um loop para caso seja sorteado 2 casas, ele faça o movimento duas vezes. Sem o loop, ele só andaria 1 casa mesmo que fosse sorteado 2.
        for (int i = 0; i < numeroCasasParaAndar; i++) {

            // Aqui fizemos um switch case que altera a posição conforme a direção escolhida 
            switch (direcaoEscolhida) {
                case 1: // se escolher 1, que é direita, a coluna aumenta 1
                    if (novaColunaDepoisDoMovimento == 7) { // se estiver na última coluna
                        System.out.println("Esse lado está bloqueado! Não pode ir para a direita.");
                    } else {
                        novaColunaDepoisDoMovimento++; // senão, move uma casa
                    }
                    break;

                case 2: // se escolher 2, que é esquerda, a coluna diminui 1        
                    if (novaColunaDepoisDoMovimento == 0) { // se estiver na primeira coluna
                        System.out.println("Esse lado está bloqueado! Não pode ir para a esquerda.");
                    } else {
                        novaColunaDepoisDoMovimento--;
                    }
                    break;

                case 3: // se escolher 3, que é cima, a linha diminui 1
                    if (novaLinhaDepoisDoMovimento == 0) { // se estiver na primeira linha
                        System.out.println("Esse lado está bloqueado! Não pode subir.");
                    } else {
                        novaLinhaDepoisDoMovimento--;
                    }
                    break;

                case 4: // se escolher 4, que é baixo, a linha aumenta 1
                    if (novaLinhaDepoisDoMovimento == 7) { // se estiver na última linha
                        System.out.println("Esse lado está bloqueado! Não pode descer.");
                    } else {
                        novaLinhaDepoisDoMovimento++;
                    }
                    break;

                default: // se o jogador digitou outro número, não faz nada
                    System.out.println("Direção inválida!");
                    break;
            }
        }

        // Aqui estamos "devolvendo" a nova posição do personagem depois de andar, retornando os dois valores (linha e coluna) e quem chamou a função vai receber esse array e atualizar a posição no tabuleiro.
        return new int[]{novaLinhaDepoisDoMovimento, novaColunaDepoisDoMovimento};

    }


    // VERSÃO 4.0 - Função que faz a pergunta final quando o jogador pega a fórmula
    public static void fazerPerguntaFinal(String jogador, Scanner scanner) {
        System.out.println("");
        System.out.println(jogador + " PEGOU A FÓRMULA!");
        System.out.println("Prove que você conhece a fórmula!");
        System.out.println("Qual é o ingrediente mais importante?");
        System.out.println("1 - Suco de Alga");
        System.out.println("2 - Molho do Fundo do Mar");
        System.out.println("3 - Pó de Água-viva");
        System.out.println("4 - Caroço de Coral");
        System.out.println("5 - Dente de Tubarão");
        System.out.println("");

        String resposta = scanner.next();
        String respostaCorreta = "3";

        if (resposta.equals(respostaCorreta)) {
            System.out.println("Resposta correta! " + jogador + " É O VENCEDOR!!!");
        } else {
            System.out.println("Resposta errada... NINGUÉM GANHOU!");
        }
    }


    
    // Pra não  ter que ficar redesenhando o tabuleiro toda hora, essa função vai mostrar no console como está o tabuleiro, com todos os personagens e elementos, atualizando a cada movimento dos personagens. 
    public static void imprimirTabuleiro(String[][] tabuleiro) {

        System.out.println("---------------------------------");

        // Esse primeiro for é pra ler cada linha do tabuleiro, se l < que a quantidade de linhas do tabuleiro, e c < que a quantidade de colunas do tabuleiro, vai copiando o valor de cada posição do tabuleiro
        for (int l = 0; l < tabuleiro.length; l++) {

            // Esse segundo for é pra ler cada coluna da linha atual
            for (int c = 0; c < tabuleiro[0].length; c++) {

                // tabuleiro[l][c] pega o valor da linha l e coluna c e vai imprimindo linha por linha e coluna por coluna
                System.out.print(tabuleiro[l][c] + "  ");
            }

            // Quando termina uma linha, pula para a próxima linha no console.
            System.out.println();
        }


        System.out.println("---------------------------------");
    }





    // Aqui fica apagando os personagens do tabuleiro, pra depois colocar eles nas novas posições
    public static void limparPersonagens(String[][] tabuleiro) {

        for (int l = 0; l < tabuleiro.length; l++) {
            for (int c = 0; c < tabuleiro[0].length; c++) {
                if (tabuleiro[l][c].equals("B") || tabuleiro[l][c].equals("P")) {
                    tabuleiro[l][c] = "~"; // volta para água normal
                }
            }
        }

    }

}
















