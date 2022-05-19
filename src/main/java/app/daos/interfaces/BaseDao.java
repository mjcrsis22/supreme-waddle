package app.daos.interfaces;

import java.util.Collection;
import java.util.Optional;

public abstract interface BaseDao<T> {
	boolean save(T objeto);

	Optional<T> findById(Long id);

	Collection<T> findAll();

	void delete(T t);

	void update(T t);
}
