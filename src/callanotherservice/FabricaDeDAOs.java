package callanotherservice;
;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class FabricaDeDAOs
{	
	private static ResourceBundle prop;

	static
	{	try
		{	prop = ResourceBundle.getBundle("resources/dao");
		}
		catch(MissingResourceException e)
		{	System.out.println("Arquivo dao.properties nao encontrado.");
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDAO(Class<T> tipo)
	{			
		T dao = null;
		String nomeDaClasse = null; 
	
		try
		{	nomeDaClasse = prop.getString(tipo.getSimpleName());
			dao = (T) Class.forName(nomeDaClasse).newInstance();
		} 
		catch (InstantiationException e)
		{	System.out.println("Nao foi possivel criar um objeto do tipo " + nomeDaClasse);
			throw new RuntimeException(e);
		} 
		catch (IllegalAccessException e)
		{	System.out.println("Nao foi possivel criar um objeto do tipo " + nomeDaClasse);
			throw new RuntimeException(e);
		} 
		catch (ClassNotFoundException e)
		{	System.out.println("Classe " + nomeDaClasse + " nao encontrada");
			throw new RuntimeException(e);
		}
		catch(MissingResourceException e)
		{	System.out.println("Chave " + tipo + " nao encontrada em dao.properties");
			throw new RuntimeException(e);
		}
		
		return dao;
	}
}
