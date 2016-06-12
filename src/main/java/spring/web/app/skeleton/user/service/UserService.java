package spring.web.app.skeleton.user.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.web.app.skeleton.common.CommonService;
import spring.web.app.skeleton.exception.IllegalInputException;
import spring.web.app.skeleton.exception.NoPermissionException;
import spring.web.app.skeleton.security.IPasswordEncrypter;
import spring.web.app.skeleton.user.model.User;
import spring.web.app.skeleton.user.model.UserRole;
import spring.web.app.skeleton.user.model.UserRoleName;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService {

	@Autowired
	EntityManager em;

	@Autowired
	CommonService commonService;

	@Autowired
	IPasswordEncrypter passwordEncrypter;

	public User authenticateByEmailAndPassword(String email, String password) {
		Optional<User> optUser = getByEmail(email);

		if (!optUser.isPresent())
			throw new NoPermissionException();

		User user = optUser.get();
		if (!passwordEncrypter.check(password, user.getPassword()))
			throw new NoPermissionException();

		user.setLastLoggedAt(new Date());
		em.merge(user);

		return user;
	}

	public List<User> list(Integer firstResult, Integer maxResults, String sort, String order) {
		return commonService.list(User.class, sort, order, firstResult, maxResults);
	}

	public Long count() {
		return commonService.count(User.class);
	}

	public User register(String email, String password) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(passwordEncrypter.hash(password));

		Set<UserRole> roles = new HashSet<>(Arrays.asList(getRoleByName(UserRoleName.USER).get()));
		user.setRoles(roles);
		try {
			em.persist(user);
			em.flush();
		} catch (PersistenceException e) {
			if (e.getCause() instanceof ConstraintViolationException)
				throw new IllegalInputException("user.with.email.exists");

			throw e;
		}

		return user;
	}

	private Optional<UserRole> getRoleByName(UserRoleName name) {
		try {
			TypedQuery<UserRole> query = em.createNamedQuery("UserRole.getByName", UserRole.class);
			query.setParameter("name", name);
			return Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException nre) {
			return Optional.empty();
		}
	}

	private Optional<User> getByEmail(String email) {
		try {
			TypedQuery<User> query = em.createNamedQuery("User.getByEmail", User.class);
			query.setParameter("email", email);
			return Optional.ofNullable(query.getSingleResult());
		} catch (NoResultException nre) {
			return Optional.empty();
		}
	}

}
