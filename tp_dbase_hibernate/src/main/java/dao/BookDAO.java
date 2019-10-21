package dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import table.*;

public class BookDAO {
	
	// Methods
	
		// Getters : Get entry from table 
		
	public static Book find(Long id) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		Book book = em.find(Book.class,id);
		DatabaseHelper.commitTxAndClose(em);
		return book;
	}
		
	public static List<Client> getBuyedBy(Book book) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Client> query = em.createQuery("SELECT DISTINCT c FROM Client c INNER JOIN c.purchase b "
				+ "WHERE b.id =:id",Client.class);
		query.setParameter("id", book.getId());
		return query.getResultList();
	}
	
	public static List<Client> getPreferedBy(Book book) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Client> query = em.createQuery("SELECT DISTINCT c FROM Client c INNER JOIN c.preferedBook b "
				+ "WHERE b.id =:id",Client.class);
		query.setParameter("id", book.getId());
		return query.getResultList();
	}
	
	public static List<Book> buyed() {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Book> query = em.createQuery("SELECT DISTINCT b FROM Client c INNER JOIN c.purchase b "
				,Book.class);
		return query.getResultList();
	}
	
	public static List<Book> prefered() {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Book> query = em.createQuery("SELECT DISTINCT b FROM Client c INNER JOIN c.preferedBook b "
				,Book.class);
		return query.getResultList();
	}
	
		// Setters : Add entry to table or modify column - data
	
	public static void addBook(Book...books) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		for (Book b : books)
			em.persist(b);
		DatabaseHelper.commitTxAndClose(em);
	}
	
	public static void addBook(List<Book> books) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		for (Book b : books)
			em.persist(b);
		DatabaseHelper.commitTxAndClose(em);
	}
	
}
