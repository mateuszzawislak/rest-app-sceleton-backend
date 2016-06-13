package spring.web.app.skeleton.act.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.web.app.skeleton.act.model.Act;
import spring.web.app.skeleton.common.CommonService;
import spring.web.app.skeleton.exception.EntityNotExistsException;
import spring.web.app.skeleton.exception.IllegalInputException;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ActService {

	@Autowired
	EntityManager em;

	@Autowired
	CommonService commonService;

	public Act get(long id) {
		try {
			return em.find(Act.class, id);
		} catch (NoResultException nre) {
			throw new EntityNotExistsException("act.not.found");
		}
	}

	public Act getByAlias(String alias) {
		try {
			TypedQuery<Act> query = em.createNamedQuery("Act.getByAlias", Act.class);
			query.setParameter("alias", alias);
			return query.getSingleResult();
		} catch (NoResultException nre) {
			throw new EntityNotExistsException("act.not.found");
		}
	}

	public Act create(Act act) {
		try {
			return em.merge(act);
		} catch (PersistenceException exception) {
			throw new IllegalInputException("act.alias.exists");
		}
	}

	public Act update(long id, Act editedAct) {
		Act act = get(id);

		if (null != editedAct.getTitle())
			act.setTitle(editedAct.getTitle());

		if (null != editedAct.getContent())
			act.setContent(editedAct.getContent());

		if (null != editedAct.getAlias())
			act.setAlias(editedAct.getAlias());

		try {
			return em.merge(act);
		} catch (PersistenceException exception) {
			throw new IllegalInputException("act.alias.exists");
		}
	}

	public void delete(long id) {
		em.remove(get(id));
	}

	public List<Act> list(Integer firstResult, Integer maxResults, String sort, String order) {
		return commonService.list(Act.class, sort, order, firstResult, maxResults);
	}

	public Long count() {
		return commonService.count(Act.class);
	}

}
