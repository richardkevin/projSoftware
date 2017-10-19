/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callanotherservice;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author richard
 */
public class CallAnotherService {
    public static void main(String[] args) throws LojaNaoEncontradaException, ProdutoNaoEncontradoException, ObjetoNaoEncontradoException {
        Scanner teclado = new Scanner(System.in);
        int opcao, preco;
        Long lojaId, prodId;
        String nome;
        Produto produto;
        Loja loja;

        LojaAppService lojaAppService = new LojaAppService();
        ProdutoAppService produtoAppService = new ProdutoAppService();

        do {
            menuPrincipal();
            opcao = Integer.parseInt(teclado.nextLine());

            switch (opcao) {
                case 0:
                    System.out.println("Obrigado!");
                    break;
                case 1: {
                    System.out.println("Informe o nome do produto:");
                    nome = teclado.nextLine();
                    System.out.println("Informe o preco do produto:");
                    preco = Integer.parseInt(teclado.nextLine());
                    System.out.println("Informe o loja do produto:");
                    lojaId = Long.parseLong(teclado.nextLine());

                    loja = lojaAppService.recuperaUmaLoja(lojaId);
                    produto = new Produto(nome, preco, loja);

                    long numero = produtoAppService.inclui(produto);

                    System.out.println(produto.getNome() + " incluido com sucesso!");
                    break;
                }
                case 2:
                    System.out.println("Informe o nome da loja:");
                    nome = teclado.nextLine();

                    loja = new Loja(nome);

                    lojaAppService.inclui(loja);

                    System.out.println(loja.getNome() + " incluido com sucesso!");
                    break;
                case 3:
                    List<Produto> produtos = produtoAppService.recuperaProdutos();

                    for (Produto p : produtos) {
                        System.out.println(
                          "Id: " + p.getId() +
                          "  Nome: " + p.getNome());
                    }
                    break;
                case 4:
                    List<Loja> lojas = lojaAppService.recuperaLojas();

                    for (Loja l : lojas) {
                        System.out.println(
                                "Id: " + l.getId() +
                                "  Nome: " + l.getNome());
                    }
                    break;
                case 5: {
                    System.out.println("Qual loja deseja selecionar?");
                    lojaId = Long.parseLong(teclado.nextLine());
                    loja = lojaAppService.recuperaUmaLoja(lojaId);

                    System.out.println("Selecione o produto ?");
                    prodId = Long.parseLong(teclado.nextLine());
                    produto = produtoAppService.recuperaUmProduto(prodId);

                    System.out.println("Qual o preço do produto?");
                    preco = Integer.parseInt(teclado.nextLine());

                    produto.setPreco(preco);

                    try {
                      lojaAppService.altera(loja, produto);
                    }
                    catch(LojaNaoEncontradaException e) {
                        System.out.println('\n' + e.getMessage());
                    }

                    System.out.println(produto.getNome() + " adicionado com sucesso.");
                    break;

                }
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

    }

    public static void menuPrincipal() {
        System.out.println("O que voce deseja fazer?");
        System.out.println("0. Sair");
        System.out.println("1. Cadastrar um produto");
        System.out.println("2. Cadastrar uma loja");
        System.out.println("3. Listar produtos");
        System.out.println("4. Listar lojas");
        System.out.println("5. Alterar preço de um produto na loja");
        System.out.println("\n");
    }

}
