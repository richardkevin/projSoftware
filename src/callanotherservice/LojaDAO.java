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
public class LojaDAO {

    public long inclui(Loja loja) {
      	try {
            EntityManager em = JPAUtil.getEntityManager();
            em.persist(loja);
            return loja.getId();
        }
        catch(RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

    public void altera(Loja umaLoja) throws ObjetoNaoEncontradoException {
        try {
            EntityManager em = JPAUtil.getEntityManager();

            Loja loja = em.find(Loja.class, umaLoja.getId(), LockModeType.PESSIMISTIC_WRITE);

            if(loja == null)
            {	throw new ObjetoNaoEncontradoException();
            }

            em.merge(umaLoja);
        }
        catch(RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

    public Loja recuperaUmaLoja(long id) throws ObjetoNaoEncontradoException {
        try {
            EntityManager em = JPAUtil.getEntityManager();
            Loja umaLoja = (Loja) em.find(Loja.class, id);

            if (umaLoja == null) {
                throw new ObjetoNaoEncontradoException();
            }

            return umaLoja;
        }
        catch(RuntimeException e) {
            throw new InfraestruturaException(e);
        }
    }

    public Loja recuperaUmaLojaComLock(long id) throws ObjetoNaoEncontradoException {
        try
            {
                EntityManager em = JPAUtil.getEntityManager();
                Loja umaLoja = em.find(Loja.class, id, LockModeType.PESSIMISTIC_WRITE);

                if (umaLoja == null) {
                    throw new ObjetoNaoEncontradoException();
                }

                return umaLoja;
            }
            catch(RuntimeException e) {
                throw new InfraestruturaException(e);
            }
    }

    public List<Loja> recuperaLojas() {
        try {
            EntityManager em = JPAUtil.getEntityManager();

            List<Loja> lojas = em.createQuery("select l from Loja l " + "order by l.id asc").getResultList();

            return lojas;
        }
        catch(RuntimeException e)
        {
            throw new InfraestruturaException(e);
        }
    }
}
