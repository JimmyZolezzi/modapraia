package moda.praia.modulo.clientes.repositorios;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import moda.praia.modulo.clientes.bean.PersistentLogin;

@Repository
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class HibernateTokenRepositoryImpl implements PersistentTokenRepository {

	private final Logger log = Logger.getLogger(HibernateTokenRepositoryImpl.class);

	@PersistenceContext
	private EntityManager em; 

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		log.info("Creating Token for user : {}" + token.getUsername());
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLast_used(token.getDate());
		em.persist(persistentLogin);

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		log.info("Fetch Token if any for seriesId : {}" + seriesId);
		try {
			Query query = em.createQuery("select p from PersistentLogin p where p.series =:seriesId");
			query.setParameter("seriesId", seriesId);
			PersistentLogin persistentLogin = (PersistentLogin) query.getSingleResult();
			return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
					persistentLogin.getToken(), persistentLogin.getLast_used());

		} catch (Exception e) {
			log.info("Token not found...");
			return null;
		}
	}

	@Override
	public void removeUserTokens(String username) {
		log.info("Removing Token if any for user : {}" + username);

		Query query = em.createQuery("select p from PersistentLogin p where p.username =:username");
		query.setParameter("username", username);
		try{
			
			PersistentLogin persistentLogin = (PersistentLogin) query.getSingleResult();
			if (persistentLogin != null) {
				log.info("rememberMe was selected");
				em.remove(persistentLogin);
			}
			
		}catch(NoResultException e){
			log.info("Nenhum token armazenado");
		}
		

	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		log.info("Updating Token for seriesId : {}" + seriesId);
		Query query = em.createQuery("select p from PersistentLogin p where p.series =:seriesId");
		query.setParameter("seriesId", seriesId);
		PersistentLogin persistentLogin = (PersistentLogin) query.getSingleResult();
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLast_used(lastUsed);
		em.merge(persistentLogin);
	}

}
