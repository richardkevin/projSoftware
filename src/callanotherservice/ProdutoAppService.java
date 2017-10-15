/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callanotherservice;

import java.util.List;

/**
 *
 * @author richard
 */
public class ProdutoAppService {
    private static ProdutoDAO produtoDAO = FabricaDeDAOs.getDAO(ProdutoDAO.class);

    public Produto recuperaUmProduto(long numero) throws ProdutoNaoEncontradoException {
        try {
            Produto umProduto = produtoDAO.recuperaUmProduto(numero);

            return umProduto;
        }
        catch(ObjetoNaoEncontradoException e) {
            throw new ProdutoNaoEncontradoException("Produto nao encontrada");
        }
        finally {
            JPAUtil.closeEntityManager();
        }
    }

    public List<Produto> recuperaProdutos() {
        try {
            List<Produto> produto = produtoDAO.recuperaProdutos();
            return produto;
        }
        finally
        {
            JPAUtil.closeEntityManager();
        }
    }
}
