package spring.web.app.skeleton.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.web.app.skeleton.base.StringUtils;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CommonService {

	@Autowired
	EntityManager em;

	public <T> T get(Class<T> clazz) {
		return getTypedQuery(clazz, null, null, null, null).getSingleResult();
	}

	public <T> List<T> list(Class<T> clazz, String sort, String order, Integer firstResult, Integer maxResults) {
		return getTypedQuery(clazz, firstResult, maxResults, sort, order).getResultList();
	}

	public <T> Long count(Class<T> clazz) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
		Root<T> entity = countQuery.from(clazz);
		countQuery.select(builder.count(entity));

		return em.createQuery(countQuery).getSingleResult();
	}

	private <T> TypedQuery<T> getTypedQuery(Class<T> clazz, Integer firstResult, Integer maxResults, String sort, String order) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> entity = criteria.from(clazz);

		if (!StringUtils.isNullOrEmpty(sort))
			criteria.orderBy(toOrder(builder, entity, sort, order));

		criteria.select(entity);

		TypedQuery<T> query = em.createQuery(criteria);

		if (firstResult != null)
			query.setFirstResult(firstResult);

		if (maxResults != null)
			query.setMaxResults(maxResults);

		return query;
	}

	public Order toOrder(CriteriaBuilder builder, Root<?> entity, String sort, String order) {
		return "desc".equals(order) ? builder.desc(entity.get(sort)) : builder.asc(entity.get(sort));
	}

}
