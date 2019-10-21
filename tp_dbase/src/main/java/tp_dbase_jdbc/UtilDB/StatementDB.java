package tp_dbase_jdbc.UtilDB;

import java.sql.*;
import java.util.ArrayList;

import tp_dbase_jdbc.table.*;

public class StatementDB {
	Connection connect;
	
	// Constructors
	
		public StatementDB() {
			connect =  ConnectDB.getConnect();
		}
		
		public StatementDB(Connection connParam){
			connect = connParam;
		}
		
		// Methods
		
			// Getters
		public Connection getConnect(){
			return connect;	
		}
			
			// Setters
		
		public void setConnect(Connection connect) {
			this.connect = connect;
		}
		
			// Execute Statement
		
		public Statement exeQuery(String reqSql) {
			try (Statement statm = connect.createStatement())
			{
				statm.executeQuery(reqSql);
				return statm;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public Statement exeUpdate(String reqSql) {
			try (Statement statm = connect.createStatement())
			{
				statm.executeUpdate(reqSql);
				return statm;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
			
		}
		
			// Create Table
		
		public static String createTableBook() {
			return "CREATE TABLE book" 
					+ "(id bigserial PRIMARY KEY NOT NULL,"
					+ "title varchar(255) NOT NULL,"
					+ "author varchar(100)"
					+ ");";
		}
		
		public static String createTableClient() {
			return "CREATE TABLE client" 
					+ "(id bigserial PRIMARY KEY NOT NULL,"
					+ "lastname varchar(100) NOT NULL,"
					+ "firstname varchar(100) NOT NULL,"
					+ "gender char NOT NULL,"
					+ "prefered_book varchar(255)"
					+ ");";
		}
		
		public static String createTablePurchase() {
			return "CREATE TABLE purchase" 
					+ "(id bigserial PRIMARY KEY NOT NULL,"
					+ "id_client bigint REFERENCES client(id) NOT NULL,"
					+ "id_book bigint REFERENCES book(id) NOT NULL"
					+ ");";
		}
		
			// Drop Table
		
		public static String dropTableBook() {
			return "DROP TABLE IF EXISTS book;";
		}
		
		public static String dropTableClient() {
			return "DROP TABLE IF EXISTS client;";
		}
		
		public static String dropTablePurchase() {
			return "DROP TABLE IF EXISTS purchase;";
		}
		
			// Getters
		
		public Book getBook(long id) {
			try (Statement statm = connect.createStatement())	
			{
				ResultSet result = statm.executeQuery("SELECT * FROM book WHERE id = " + id + ";" );
				if (result.next()) {
					Book book = new Book(result.getString("title"),result.getString("author"));
					book.setId(id);
					return book;
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public Book getBook(String title) {
			try (Statement statm = connect.createStatement())	
			{
				ResultSet result = statm.executeQuery("SELECT * FROM book WHERE title = '" + title + "';" );
				if (result.next()) {
					Book book = new Book(result.getString("title"),result.getString("author"));
					book.setId(result.getLong("id"));
					statm.close();
					return book;
				}
				statm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		public Client getClient(long id) {
			try (Statement statm = connect.createStatement())	
			{
				ResultSet result = statm.executeQuery("SELECT * FROM client WHERE id = " + id + ";" );
				if (result.next()) {
					Client client = new Client(result.getString("lastname"),result.getString("firstname")
							,Gender.valueOf(result.getString("gender")),this.getBook(result.getString("prefered_book")));
					client.setId(id);
					statm.close();
					return client;
				}
				statm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		
			// Insert
		
		public void insertBook(Book book) {
			
			try (PreparedStatement prepStatm = connect.prepareStatement(
					"INSERT INTO book (title,author) "
					+ "VALUES (?,?);"
					,Statement.RETURN_GENERATED_KEYS);)
			{
				prepStatm.setString(1,book.getTitle());
				prepStatm.setString(2,book.getAuthor());
				prepStatm.execute();
				
				ResultSet result = prepStatm.getGeneratedKeys();
				result.next();
				// Stocking id in object from table book where the book was inserted
				book.setId(result.getLong("id")); 
				
				prepStatm.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void insertClient(Client client) {
			
			try (PreparedStatement prepStatm = connect.prepareStatement(""
					+ "INSERT INTO client (lastname,firstname,gender,prefered_book) "
					+ "VALUES (?,?,?,?);"
					,Statement.RETURN_GENERATED_KEYS);)
			{
				prepStatm.setString(1,client.getLastname());
				prepStatm.setString(2,client.getFirstname());
				prepStatm.setString(3,client.getGender().toString());
				
				if (client.getPreferBook() != null) {
					prepStatm.setString(4,client.getPreferBook().getTitle());
				} 
				else {
					prepStatm.setString(4,null);
				}
				prepStatm.execute();
				
				ResultSet result = prepStatm.getGeneratedKeys();
				result.next();
				// Stocking id in object from table book where the book was inserted
				client.setId(result.getLong("id")); 
				prepStatm.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void insertPurchase(Client client, Book book) {
			
			try (PreparedStatement prepStatm = connect.prepareStatement(""
					+ "INSERT INTO purchase (id_client,id_book) "
					+ "VALUES (?,?);"
					,Statement.RETURN_GENERATED_KEYS);)
			{
				prepStatm.setLong(1,client.getId());
				prepStatm.setLong(2,book.getId());

				prepStatm.execute();
				
				ResultSet result = prepStatm.getGeneratedKeys();
				result.next();
				// Stocking id in object from table book where the book was inserted
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
			// Query
		
		public ArrayList<Book> getBookByPurchase(Client client) {
			ArrayList<Book> books = new ArrayList<Book>();
			
			try (PreparedStatement prepStatm = connect.prepareStatement(
					"SELECT * FROM purchase WHERE id_client = ?"))
			{
				prepStatm.setLong(1,client.getId());
				
				ResultSet result = prepStatm.executeQuery();
				Book book;
				
				while(result.next()) {
					Statement statm = connect.createStatement();
					ResultSet resultBook = statm.executeQuery("SELECT * FROM book WHERE id = " + result.getLong("id_book"));
					
					if (resultBook.next()) {
						book = this.getBook(resultBook.getLong("id"));
						books.add(book);
					}
					statm.close();
				}

				prepStatm.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return books;
		}
		
		public ArrayList<Client> getClientByPurchase(Book book) {
			ArrayList<Client> clients = new ArrayList<Client>();
			
			try (PreparedStatement prepStatm = connect.prepareStatement(
					"SELECT * FROM purchase WHERE id_book = ?"))
			{
				prepStatm.setLong(1,book.getId());
				
				ResultSet result = prepStatm.executeQuery();
				Client client;
				
				while(result.next()) {
					Statement statm = connect.createStatement();
					ResultSet resultClient = statm.executeQuery("SELECT * FROM client WHERE id = " + result.getLong("id_client"));
					
					if (resultClient.next()) {
						client = this.getClient(resultClient.getLong("id"));
						clients.add(client);
					}
					statm.close();
				}

				prepStatm.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return clients;
		}
		
}
