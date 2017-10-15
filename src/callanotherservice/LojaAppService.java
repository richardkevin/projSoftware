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

    public Loja recuperaUmaLoja(long numero) throws LojaNaoEncontradaException {
        try {
            Loja umLoja = lojaDAO.recuperaUmaLoja(numero);

            return umLoja;
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
