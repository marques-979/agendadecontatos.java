package src.app;
import java.util.List;
import java.util.Scanner;

// Aplicação principal que utiliza a classe Agenda para gerenciar contatos
public class AgendaApp {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        // Scanner para entrada de dados do usuário (entrada)
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Agenda de Contatos (versão avançada) ===");

        // Loop principal de interação com menu (estrutura de controle)
        while (true) {
            System.out.println();
            System.out.println("1) Adicionar contato");
            System.out.println("2) Listar contatos");
            System.out.println("3) Buscar por nome");
            System.out.println("4) Remover por nome");
            System.out.println("5) Desfazer última remoção");
            System.out.println("6) Adicionar contato à fila de espera");
            System.out.println("7) Chamar próximo da fila");
            System.out.println("8) Ordenar contatos por nome (SelectionSort)");
            System.out.println("9) Buscar por nome (busca binária - requer ordenação)");
            System.out.println("0) Sair");
            System.out.print("Escolha uma opção: ");
            String opc = sc.nextLine().trim(); // leitura de entrada

            if (opc.equals("1")) {
                // Entrada dos dados do novo contato
                System.out.print("Nome: ");
                String nome = sc.nextLine().trim();
                System.out.print("Telefone: ");
                String tel = sc.nextLine().trim();
                System.out.print("Email: ");
                String email = sc.nextLine().trim();
                System.out.print("Aniversário (dd/mm): ");
                String aniv = sc.nextLine().trim();

                Contato c = new Contato(nome, tel, email, aniv);
                agenda.adicionar(c); // operação de adição
                System.out.println("Contato adicionado com sucesso."); // saída

            } else if (opc.equals("2")) {
                List<Contato> lista = agenda.listar(); // obter cópia da lista
                if (lista.isEmpty()) System.out.println("Nenhum contato cadastrado.");
                else {
                    System.out.println("\n---- Lista de Contatos ----");
                    int i = 1;
                    for (Contato c : lista) {
                        System.out.println("\nContato " + (i++) + ":");
                        c.exibirInfo(); // saída: exibe contato
                    }
                }

            } else if (opc.equals("3")) {
                System.out.print("Nome para buscar: ");
                String nome = sc.nextLine().trim();
                List<Contato> encontrados = agenda.buscarPorNome(nome); // busca (lógica)
                if (encontrados.isEmpty()) System.out.println("Nenhum contato encontrado.");
                else {
                    System.out.println("\n---- Contatos encontrados ----");
                    for (Contato c : encontrados) c.exibirInfo();
                }

            } else if (opc.equals("4")) {
                System.out.print("Nome exato para remover: ");
                String nome = sc.nextLine().trim();
                boolean ok = agenda.removerPorNome(nome); // remoção e push na pilha
                System.out.println(ok ? "Remoção realizada." : "Contato não encontrado.");

            } else if (opc.equals("5")) {
                boolean ok = agenda.desfazerUltimaRemocao(); // pop da pilha
                System.out.println(ok ? "Última remoção desfeita." : "Nenhuma remoção para desfazer.");

            } else if (opc.equals("6")) {
                System.out.print("Nome do contato para adicionar à fila: ");
                String nome = sc.nextLine().trim();
                List<Contato> encontrados = agenda.buscarPorNome(nome);
                if (encontrados.isEmpty()) System.out.println("Contato não encontrado para colocar na fila.");
                else {
                    Contato c = encontrados.get(0);
                    agenda.adicionarAFila(c); // enfileira o contato
                    System.out.println("Contato adicionado à fila de espera.");
                }

            } else if (opc.equals("7")) {
                Contato proximo = agenda.chamarProximoDaFila(); // desenfileira
                if (proximo == null) System.out.println("Fila vazia.");
                else {
                    System.out.println("Chamando próximo da fila:");
                    proximo.exibirInfo();
                }

            } else if (opc.equals("8")) {
                agenda.ordenarPorNome(); // ordena (SelectionSort)
                System.out.println("Contatos ordenados por nome.");

            } else if (opc.equals("9")) {
                System.out.print("Nome exato para busca binária: ");
                String nome = sc.nextLine().trim();
                int idx = agenda.buscarBinariaPorNome(nome); // busca binária
                if (idx < 0) System.out.println("Contato não encontrado (verifique se a lista está ordenada).");
                else {
                    System.out.println("Contato encontrado na posição " + (idx + 1) + ":");
                    agenda.listar().get(idx).exibirInfo();
                }

            } else if (opc.equals("0")) {
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close(); // fechar Scanner (boa prática)
        System.out.println("Encerrando agenda. Até logo!");
    }
}
