/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callanotherservice;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

/**
 *
 * @author richard
 */
public class ProdutoAppService {
    private static ProdutoDAO produtoDAO = FabricaDeDAOs.getDAO(ProdutoDAO.class);

    public long inclui(Produto umProduto) {
        try {	
            JPAUtil.beginTransaction();
            long numero = produtoDAO.inclui(umProduto);

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
    
    public void altera(Produto umProduto) throws ProdutoNaoEncontradoException {
        try {
            JPAUtil.beginTransaction();
            produtoDAO.altera(umProduto);

            JPAUtil.commitTransaction();

        } 
        catch(ObjetoNaoEncontradoException e) {
            JPAUtil.rollbackTransaction();

            throw new ProdutoNaoEncontradoException("Produto n�o encontrado");
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

    public void exclui(long id) throws ObjetoNaoEncontradoException {
        try {
            EntityManager em = JPAUtil.getEntityManager();

            Produto produto = em.find(Produto.class, id, LockModeType.PESSIMISTIC_WRITE);

            if(produto == null) {
                throw new ObjetoNaoEncontradoException();
            }

            em.remove(produto);
        }
        catch(RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

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
