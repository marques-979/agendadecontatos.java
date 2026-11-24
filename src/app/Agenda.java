package src.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

// Classe que gerencia a coleção de contatos e estruturas auxiliares
public class Agenda {
    // Lista dinâmica para armazenar contatos (ArrayList)
    private List<Contato> contatos = new ArrayList<>();
    // Fila para gerenciar contatos em espera
    private Queue<Contato> filaEspera = new LinkedList<>();
    // Pilha para histórico de exclusões (para desfazer)
    private Stack<Contato> historicoRemovidos = new Stack<>();

    // Adiciona contato na lista (entrada)
    public void adicionar(Contato c) {
        contatos.add(c);
    }

    // Retorna cópia da lista de contatos (saída)
    public List<Contato> listar() {
        return new ArrayList<>(contatos);
    }

    // Busca por nome (conteúdo) - operação lógica e de iteração
    public List<Contato> buscarPorNome(String nome) {
        List<Contato> encontrados = new ArrayList<>();
        String alvo = nome.toLowerCase();
        for (Contato c : contatos) {
            // comparação em lower-case para busca case-insensitive
            if (c.getNome().toLowerCase().contains(alvo)) {
                encontrados.add(c);
            }
        }
        return encontrados;
    }

    // Remove o primeiro contato que tem o nome exato (ignora case)
    public boolean removerPorNome(String nome) {
        for (int i = 0; i < contatos.size(); i++) {
            // estrutura de controle e comparação
            if (contatos.get(i).getNome().equalsIgnoreCase(nome)) {
                Contato rem = contatos.remove(i); // operação de remoção
                historicoRemovidos.push(rem); // registra na pilha (histórico)
                return true;
            }
        }
        return false;
    }

    // Desfaz a última remoção usando a pilha (pop)
    public boolean desfazerUltimaRemocao() {
        if (historicoRemovidos.isEmpty()) return false;
        Contato c = historicoRemovidos.pop();
        contatos.add(c);
        return true;
    }

    // Fila: adicionar contato à fila de espera
    public void adicionarAFila(Contato c) {
        filaEspera.add(c);
    }

    // Fila: chamar próximo (poll retorna null se vazia)
    public Contato chamarProximoDaFila() {
        return filaEspera.poll();
    }

    // Ordenação por nome (Selection Sort) - operações aritméticas/compare
    public void ordenarPorNome() {
        int n = contatos.size();
        for (int i = 0; i < n - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                if (contatos.get(j).getNome().compareToIgnoreCase(contatos.get(menor).getNome()) < 0) {
                    menor = j;
                }
            }
            if (menor != i) {
                Contato tmp = contatos.get(i);
                contatos.set(i, contatos.get(menor));
                contatos.set(menor, tmp);
            }
        }
    }

    // Busca binária (requer lista ordenada por nome)
    // Retorna índice ou -1 se não encontrado
    public int buscarBinariaPorNome(String nome) {
        int lo = 0, hi = contatos.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2; // operação aritmética
            int cmp = contatos.get(mid).getNome().compareToIgnoreCase(nome);
            if (cmp == 0) return mid;
            if (cmp < 0) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }
}
