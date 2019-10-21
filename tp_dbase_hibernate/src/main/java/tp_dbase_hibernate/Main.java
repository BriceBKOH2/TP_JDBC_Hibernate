package tp_dbase_hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//local
import dao.*;
import table.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		// Book to insert into book table
		Book b1 = new Book ("Dune", "Frank Herbert");
		Book b2 = new Book ("Java pour les nuls","Salim");
		Book b3 = new Book ("Bonnes Recettes", "Mamie");
		Book b4 = new Book ("Anonyme", "georges");
		List<Book> books = new ArrayList<Book>(Arrays.asList(b1,b2,b3,b4));
		// Insertion
		BookDAO.addBook(books);
		
		// Client to insert into client Table
		Client c1 = new Client ("Dupont", "Jean", Gender.M.name());
		Client c2 = new Client ("Dupont", "Jeanne", Gender.F.name());
		Client c3 = new Client ("Durant", "Jean", Gender.M.name());
		Client c4 = new Client ("Durant", "Jeanne", Gender.F.name());
		Client c5 = new Client ("Beteille", "Brice", Gender.M.name(),b1);
		Client c6 = new Client ("MaVoisine", "NoName", Gender.F.name(),b2);
		ArrayList<Client> clients = new ArrayList<Client>(Arrays.asList(c1,c2,c3,c4,c5,c6));
		// Insertion
		ClientDAO.addClient(clients);
			
		c1.setPreferBook(b4);
		c2.setPreferBook(b1);

		ClientDAO.purchase(c1, b2,b3);
		ClientDAO.purchase(c2, b2,b4);
		ClientDAO.purchase(c3 , b3);
		ClientDAO.purchase(c3, b2);
		ClientDAO.purchase(c4, b4);
		

//		Client c7 = ClientDAO.find(c1.getId());
//		c3.printPurchase();
//		System.out.println(ClientDAO.getPurchase(c3));
//		System.out.println(c3.getPurchase());
//		System.out.println(c7.getPreferBook());
//		System.out.println(c7);		
//		for (Client c : BookDAO.getBuyedBy(b4))
//				System.out.println(c);
//		for (Client c : BookDAO.getPreferedBy(b1))
//			System.out.println(c);
//		for(Book b : BookDAO.buyed())
//			System.out.println(b);
		for(Book b : BookDAO.prefered())
			System.out.println(b);
	}

}
