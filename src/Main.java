import java.util.ArrayList;
import java.util.Scanner;

// Classe Abstrata para representar uma Tarefa
abstract class Tarefa {
    private String descricao;
    private boolean concluida;

    public Tarefa(String descricao) {
        this.descricao = descricao;
        this.concluida = false;
    }

    public void marcarConcluida() {
        this.concluida = true;
    }

    public void desmarcarConcluida() {
        this.concluida = false;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public String getDescricao() {
        return descricao;
    }

    public abstract void exibirTarefa();
}

// Classe concreta herdando de Tarefa
class TarefaSimples extends Tarefa {
    public TarefaSimples(String descricao) {
        super(descricao);
    }

    @Override
    public void exibirTarefa() {
        System.out.println("[" + (isConcluida() ? "X" : " ") + "] " + getDescricao());
    }
}

// Classe principal para gerenciar tarefas
public class Main {
    private ArrayList<Tarefa> listaTarefas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main gerenciador = new Main();
        gerenciador.executar();
    }

    public void executar() {
        while (true) {
            System.out.println("\nGerenciador de Tarefas");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Remover Tarefa");
            System.out.println("3. Marcar Tarefa como Concluída");
            System.out.println("4. Desmarcar Tarefa");
            System.out.println("5. Listar Tarefas");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1:
                        adicionarTarefa();
                        break;
                    case 2:
                        removerTarefa();
                        break;
                    case 3:
                        marcarConcluida();
                        break;
                    case 4:
                        desmarcarConcluida();
                        break;
                    case 5:
                        listarTarefas();
                        break;
                    case 6:
                        System.out.println("Saindo...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Insira um número válido!");
            }
        }
    }

    private void adicionarTarefa() {
        System.out.print("Digite a descrição da tarefa: ");
        String descricao = scanner.nextLine().trim();
        if (!descricao.isEmpty()) {
            listaTarefas.add(new TarefaSimples(descricao));
            System.out.println("Tarefa adicionada com sucesso!");
        } else {
            System.out.println("Erro: A descrição não pode ser vazia!");
        }
    }

    private void removerTarefa() {
        listarTarefas();
        System.out.print("Digite o número da tarefa para remover: ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            if (indice >= 0 && indice < listaTarefas.size()) {
                listaTarefas.remove(indice);
                System.out.println("Tarefa removida com sucesso!");
            } else {
                System.out.println("Erro: Número inválido!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Insira um número válido!");
        }
    }

    private void marcarConcluida() {
        listarTarefas();
        System.out.print("Digite o número da tarefa para marcar como concluída: ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            if (indice >= 0 && indice < listaTarefas.size()) {
                listaTarefas.get(indice).marcarConcluida();
                System.out.println("Tarefa marcada como concluída!");
            } else {
                System.out.println("Erro: Número inválido!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Insira um número válido!");
        }
    }

    private void desmarcarConcluida() {
        listarTarefas();
        System.out.print("Digite o número da tarefa para desmarcar: ");
        try {
            int indice = Integer.parseInt(scanner.nextLine()) - 1;
            if (indice >= 0 && indice < listaTarefas.size()) {
                listaTarefas.get(indice).desmarcarConcluida();
                System.out.println("Tarefa desmarcada!");
            } else {
                System.out.println("Erro: Número inválido!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: Insira um número válido!");
        }
    }

    private void listarTarefas() {
        if (listaTarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada!");
        } else {
            System.out.println("\nLista de Tarefas:");
            for (int i = 0; i < listaTarefas.size(); i++) {
                System.out.println("------------------------------");
                System.out.print((i + 1) + ". ");
                listaTarefas.get(i).exibirTarefa();
            }
            System.out.println("------------------------------");
        }
    }
}
