package qa.qcri.aidr.task.dao;

import org.hibernate.criterion.Criterion;

import qa.qcri.aidr.task.entities.Document;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

@Local
public interface AbstractTaskManagerService<E, I extends Serializable> {

	public E getById(I id);
	public E getByCriteria(Criterion criterion);
	
	public List<E> getAll();
	public List<E> getAllByCriteria(Criterion criterion);
	public List<E> getByCriteriaWithLimit(Criterion criterion, Integer count);
	
	public List<E> getByCriteriaByOrder(Criterion criterion, String order, String[] orderBy, Integer count);
	public List<E> getByCriteriaWithAliasByOrder(Criterion criterion, String order, String[] orderBy, Integer count, String aliasTable, Criterion aliasCriterion);
	
	public void update(E e);
	public void update(List<E> entityCollection);
	
	public void save(E e);
	public void save(List<E> entityCollection);
	
	public void delete(E e);
	public void delete(List<E> entityCollection);
	public void deleteByCriteria(Criterion criterion);
}