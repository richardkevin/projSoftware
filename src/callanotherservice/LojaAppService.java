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
public class LojaAppService {
    private static LojaDAO lojaDAO = FabricaDeDAOs.getDAO(LojaDAO.class);
    private ProdutoAppService produtoAppService = new ProdutoAppService();

    public long inclui(Loja loja) {
        try {
            JPAUtil.beginTransaction();
            long numero = lojaDAO.inclui(loja);

            JPAUtil.commitTransaction();

            return numero;
        }
        catch(InfraestruturaException e) {
            try {
                JPAUtil.rollbackTransaction();
            }
            catch(InfraestruturaException ie) {
            }

            throw e;
        }
        finally {
            JPAUtil.closeEntityManager();
        }
    }

    public void altera(Loja loja, Produto produto) throws LojaNaoEncontradaException, ObjetoNaoEncontradoException, ProdutoNaoEncontradoException {
        try {
            JPAUtil.beginTransaction();
            produtoAppService.altera(produto);

            lojaDAO.altera(loja);

            JPAUtil.commitTransaction();
        }
        catch(ObjetoNaoEncontradoException e) {	
            JPAUtil.rollbackTransaction();

            throw new LojaNaoEncontradaException("Loja nao encontrada");
        }
        catch(InfraestruturaException e) {
            try {
                JPAUtil.rollbackTransaction();
            }
            catch(InfraestruturaException ie) {
            }

            throw e;
        }
        finally {
            JPAUtil.closeEntityManager();
        }
    }

    public Loja recuperaUmaLoja(long numero) throws LojaNaoEncontradaException {
        try {
            Loja loja = lojaDAO.recuperaUmaLoja(numero);

            return loja;
        }
        catch(ObjetoNaoEncontradoException e) {
            throw new LojaNaoEncontradaException("Loja nao encontrada");
        }
        finally {
            JPAUtil.closeEntityManager();
        }
    }

    public List<Loja> recuperaLojas() {
        try {
            List<Loja> loja = lojaDAO.recuperaLojas();
            return loja;
        }
        finally
        {
            JPAUtil.closeEntityManager();
        }
    }
}
