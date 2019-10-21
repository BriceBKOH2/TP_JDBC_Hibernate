package dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import table.*;

public class ClientDAO {

	// Methods
	
		// Getters : Get entry from table 
	
	public static Client find(Long id) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c LEFT JOIN fetch c.preferedBook "
				+ "WHERE c.id =:id",Client.class);
		query.setParameter("id", id);
		Client client = query.getSingleResult();
		DatabaseHelper.commitTxAndClose(em);
		
		return client;
	}
	
	public static List<Book> getPurchase(Client client) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Book> query = em.createQuery("SELECT DISTINCT b FROM Book b INNER JOIN b.buyedBy c "
				+ "WHERE c.id =:id",Book.class);
		query.setParameter("id", client.getId());
		return query.getResultList();
	}
	
	public static Book getPreferedBook(Client client) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		TypedQuery<Book> query = em.createQuery("SELECT DISTINCT b FROM Book b INNER JOIN b.preferedBy c "
				+ "WHERE c.id =:id",Book.class);
		query.setParameter("id", client.getId());
		return query.getSingleResult();
	}
	
		// Setters : Add entry to table or modify column - data
	public static void addClient(Client client) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.persist(client);
		DatabaseHelper.commitTxAndClose(em);
	}
	
	public static void addClient(List<Client> clients) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		for (Client c : clients)
			em.persist(c);
		DatabaseHelper.commitTxAndClose(em);
	}
//	
//	public static void purchase (Client client, Book book) {
//		client.addPurchase(book);
////		book.addBuyedBy(client);
//		EntityManager em = DatabaseHelper.createEntityManager();
//		DatabaseHelper.beginTx(em);
//		em.merge(client);
//		em.merge(book);
//		DatabaseHelper.commitTxAndClose(em);
//	}
	
	public static void purchase (Client client, Book...books) {
		
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);

		for (Book b : books) {
			client.addPurchase(b);
			em.merge(b);
		}
		em.merge(client);
		DatabaseHelper.commitTxAndClose(em);
	}
	
	public static void purchase (Client client, List<Book> books) {
		
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);

		for (Book b : books) {
			client.addPurchase(b);
			em.merge(b);
		}
		em.merge(client);
		DatabaseHelper.commitTxAndClose(em);
	}
	
	public static void setPreferedBook(Client client, Book book) {
		client.setPreferBook(book);	
		book.addPreferedBy(client);
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.merge(book);
		em.merge(client);
		DatabaseHelper.commitTxAndClose(em);
	}
	
}
