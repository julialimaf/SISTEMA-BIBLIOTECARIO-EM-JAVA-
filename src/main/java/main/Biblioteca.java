package main;

import java.util.ArrayList;
import java.util.Scanner;
import utils.Cliente;
import utils.Conta;
import utils.Livro;

public class Biblioteca {

    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Conta> contasBancarias = new ArrayList<>();
    static ArrayList<Livro> livros = new ArrayList<>();

    public static void main(String[] args) {
        operacoes();
    }

    public static void operacoes() {
        System.out.println("\n=== Bem-vindo(a) à Biblioteca ===");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Ver Lista de Livros");
        System.out.println("3 - Alugar livro");
        System.out.println("4 - Cadastrar Livro");
        System.out.println("5 - Sair");
        System.out.print("Escolha uma opção: ");

        int operacao = teclado.nextInt();
        teclado.nextLine();

        switch (operacao) {
            case 1 -> criarConta();
            case 2 -> verListaLivros();
            case 3 -> alugarLivro();
            case 4 -> cadastrarLivro();
            case 5 -> {
                System.out.println("Obrigada por escolher a nossa Biblioteca!");
                System.exit(0);
            }
            default -> {
                System.out.println("Opção inválida!");
                operacoes();
            }
        }
    }

    public static void criarConta() {
        System.out.println("\n=== Criação de Conta ===");
        System.out.print("Nome: ");
        String nome = teclado.nextLine();

        System.out.print("CPF: ");
        String cpf = teclado.nextLine();

        System.out.print("Idade: ");
        int idade = teclado.nextInt();
        teclado.nextLine();

        Cliente cliente = new Cliente(nome, cpf, idade);
        Conta conta = new Conta(cliente);
        contasBancarias.add(conta);

        System.out.println("\nConta criada com sucesso!");
        System.out.println("Número da conta: " + conta.getNumeroConta());
        operacoes();
    }

    public static Conta encontrarConta(int numeroConta) {
        for (Conta c : contasBancarias) {
            if (c.getNumeroConta() == numeroConta) {
                return c;
            }
        }
        return null;
    }

    public static void cadastrarLivro() {
        System.out.println("\n=== Cadastro de Livro ===");
        System.out.print("Título: ");
        String titulo = teclado.nextLine();

        System.out.print("Autor: ");
        String autor = teclado.nextLine();

        Livro livro = new Livro(titulo, autor);
        livros.add(livro);

        System.out.println("Livro cadastrado com sucesso!");
        operacoes();
    }

    public static void verListaLivros() {
        System.out.println("\n=== Lista de Livros ===");

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado ainda.");
        } else {
            for (Livro l : livros) {
                System.out.println(l);
            }
        }

        operacoes();
    }

    public static void alugarLivro() {
        System.out.println("\n=== Alugar Livro ===");

        if (livros.isEmpty()) {
            System.out.println("Não há livros disponíveis.");
            operacoes();
            return;
        }

        System.out.println("Livros disponíveis:");
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            if (!l.isAlugado()) {
                System.out.println((i + 1) + " - " + l.getTitulo() + " (Autor: " + l.getAutor() + ")");
            }
        }

        System.out.print("Digite o número do livro que deseja alugar: ");
        int escolha = teclado.nextInt();
        teclado.nextLine();

        if (escolha < 1 || escolha > livros.size()) {
            System.out.println("Número inválido.");
        } else {
            Livro livroEscolhido = livros.get(escolha - 1);
            if (livroEscolhido.isAlugado()) {
                System.out.println("Esse livro já está alugado.");
            } else {
                livroEscolhido.setAlugado(true);
                System.out.println("Você alugou o livro: " + livroEscolhido.getTitulo());
            }
        }

        operacoes();
    }
}
