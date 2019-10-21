package tp_dbase_jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

import tp_dbase_jdbc.UtilDB.*;
import tp_dbase_jdbc.table.*;

public class Main {

	public static void main(String[] args) throws SQLException {
			
		StatementDB statm = new StatementDB();
		
		// Drop all tables for a clean test environnement
		statm.exeUpdate(StatementDB.dropTablePurchase());
		statm.exeUpdate(StatementDB.dropTableBook());
		statm.exeUpdate(StatementDB.dropTableClient());
		// Create all tables necessary
		statm.exeUpdate(StatementDB.createTableBook());
		statm.exeUpdate(StatementDB.createTableClient());                                                                                                                                                                                                           
		statm.exeUpdate(StatementDB.createTablePurchase());

		// Book to insert into book Table
		Book b1 = new Book ("Dune", "Frank Herbert");
		Book b2 = new Book ("Java pour les nuls","Salim");
		Book b3 = new Book ("Bonnes Recettes", "Mamie");
		Book b4 = new Book ("Anonyme");
		ArrayList<Book> books = new ArrayList<Book>(Arrays.asList(b1,b2,b3,b4));
		
		// insertion Table book
		for(Book b : books) {
			statm.insertBook(b);
//			System.out.println("id= " + b.getId() + " title = " + b.getTitle() + " auteur = " + b.getAuthor());
		}
		// Client to insert into client Table
		Client c1 = new Client ("Dupont", "Jean", Gender.M);
		Client c2 = new Client ("Dupont", "Jeanne", Gender.F);
		Client c3 = new Client ("Durant", "Jean", Gender.M);
		Client c4 = new Client ("Durant", "Jeanne", Gender.F);
		Client c5 = new Client ("Beteille", "Brice", Gender.M,b1);
		Client c6 = new Client ("MaVoisine", "NoName", Gender.F,b2);
		ArrayList<Client> clients = new ArrayList<Client>(Arrays.asList(c1,c2,c3,c4,c5,c6));
		
		// insertion Table client
		for(Client c : clients) {
			statm.insertClient(c);
//			if (c.getPreferBook() != null)
//			System.out.println("id= " + c.getId() + " Nom = " + c.getLastname() + " Prenom = " 
//			+ c.getFirstname() + " Gender = " + c.getGender().toString() + " Prefered Book = " 
//			+ c.getPreferBook().getTitle() );
//			else
//			System.out.println("id= " + c.getId() + " Nom = " + c.getLastname() + " Prenom = " 
//			+ c.getFirstname() + " Gender = " + c.getGender().toString() + " Prefered Book = " 
//			+ null );
		}

		// insertion Table purchase
		statm.insertPurchase(c1,b1);
		statm.insertPurchase(c1,b2);
		statm.insertPurchase(c2,b3);
		statm.insertPurchase(c1,b3);
		statm.insertPurchase(c4,b4);
		statm.insertPurchase(c2,b1);
		statm.insertPurchase(c1,b4);
		statm.insertPurchase(c6,b3);
		
		
		clients = statm.getClientByPurchase(b3);
		
		for(Client c : clients) {
			if (c.getPreferBook() != null)
				System.out.println("id= " + c.getId() + " Nom = " + c.getLastname() + " Prenom = " 
				+ c.getFirstname() + " Gender = " + c.getGender().toString() + " Prefered Book = " 
				+ c.getPreferBook().getTitle() );
				else
				System.out.println("id= " + c.getId() + " Nom = " + c.getLastname() + " Prenom = " 
				+ c.getFirstname() + " Gender = " + c.getGender().toString() + " Prefered Book = " 
				+ null );
		}
		
		
	}

}
