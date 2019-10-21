package dao;

import javax.persistence.EntityManager;
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
		return book.getBuyedBy();
	}
	
	public static List<Client> getPreferedBy(Book book) {
		return book.getPreferedBy();
	}
	
	
		// Setters : Add entry to table or modify column - data
	public static void addBook(Book book) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.persist(book);
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
