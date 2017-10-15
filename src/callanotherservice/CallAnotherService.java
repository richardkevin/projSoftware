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
    public static void main(String[] args) throws LojaNaoEncontradaException, ProdutoNaoEncontradoException {
        Scanner teclado = new Scanner(System.in);
        int opcao;
        long idLojaSelecionada;

        LojaAppService lojaAppService = new LojaAppService();

        do {
            menuPrincipal();
            opcao = teclado.nextInt();

            switch (opcao) {
                case 0: 
                    System.out.println("Obrigado!");
                    break;
                case 1: {
                    List<Loja> lojas = lojaAppService.recuperaLojas();

                    for (Loja loja : lojas) {
                        System.out.println("Id = " + loja.getId() + "  Nome = " + loja.getNome());
                    }
                    break;
                }
                case 2:
                    System.out.println("Qual loja deseja selecionar?");
                    idLojaSelecionada = teclado.nextLong();
                    try {
                        Loja loja = lojaAppService.recuperaUmaLoja(idLojaSelecionada);
                        System.out.println("Loja selecionada: " + loja.getNome());
                        menuLoja(loja);
                    }
                    catch (LojaNaoEncontradaException e){
                        System.out.println("Loja não encontrada");
                    }
                        
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

    }

    public static void menuPrincipal() {
        System.out.println("O que voce deseja fazer?");
        System.out.println("0. Sair");
        System.out.println("1. Listar Lojas");
        System.out.println("2. Escolher Loja");
    }

    public static void menuLoja(Loja loja) throws ProdutoNaoEncontradoException {
        Scanner teclado = new Scanner(System.in);
        int opcao;
        long idProduto;

        ProdutoAppService produtoAppService = new ProdutoAppService();

        do {
            System.out.println("Bem vindo ao "+ loja.getNome());
            System.out.println("O que voce deseja fazer?");
            System.out.println("0. Sair");
            System.out.println("1. Listar Produtos");
            System.out.println("2. Consultar Produto");

            opcao = teclado.nextInt();

            switch (opcao) {
                case 0: 
                    System.out.println("Obrigado!");
                    break;
                case 1: {
                    List<Produto> produtos = produtoAppService.recuperaProdutos();

                    for (Produto produto : produtos) {
                        System.out.println("Id: " + produto.getId() + "  Nome: " + produto.getNome() + "\n");
                    }
                    break;
                }
                case 2:
                    System.out.println("Qual produto?");
                    idProduto = teclado.nextLong();
                    Produto produto = produtoAppService.recuperaUmProduto(idProduto);
                    System.out.println(
                        "Id: " + produto.getId() +
                        "  Produto: " + produto.getNome() + 
                        "  Preco: " + produto.getPreco() +
                        "  Quantidade: " + produto.getQuantidade()
                    );
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcao != 0);

    }


}
