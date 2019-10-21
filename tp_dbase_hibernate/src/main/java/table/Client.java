package table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Client {
	@Id
	@GeneratedValue
	Long id;
	
	@Column 
	@NotBlank
	String lastname;
	
	@Column 
	@NotBlank
	String firstname;
	
	@Column
	@NotNull
	String gender;
	
	@ManyToOne 
	Book preferedBook;
	
	@ManyToMany
	List<Book> purchase;
	
	// Constructors
	
	public Client() {
		
	}
	
	public Client(String lastname, String firstname, String gender) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.gender = gender;
		purchase = new ArrayList<Book>();
	}

	
	public Client(String lastname, String firstname, String gender, Book preferedBook) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.gender = gender;
		this.preferedBook = preferedBook;
		purchase = new ArrayList<Book>();
	}

	
	// Methods
	
		// Getters
	
	public long getId() {
		return id;
	}
	
	public String getLastname() {
		return lastname;
	}

	public String getFirstname() {
		return firstname;
	}
	
	public String getString() {
		return gender;
		
	}
	
	public Book getPreferBook() {
		return preferedBook;
	}
	
	public List<Book> getPurchase() {
		return purchase;
	}
	
	
		// Setters

	public void setId(long id) {
		this.id = id;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setString(String gender) {
		this.gender = gender;
	}

	public void setPreferBook(Book preferedBook) {
		this.preferedBook = preferedBook;
	}
	

	public void setPurchase(List<Book> purchase) {
		this.purchase = purchase;
	}

	public void addPurchase(Book book) {
		purchase.add(book);
	}
	
	public void addPurchase(List<Book> book) {
		purchase.addAll(book);
	}
	
	// Others
	
	public String toString() {
		if (this.getPreferBook() != null) {
			return "[id= " + this.getId() + " name=" + this.getFirstname() + " " + this.getLastname() + "]";
		}
		return "[id= " + this.getId() + " name=" + this.getFirstname() + " " + this.getLastname() + " prefered book= " + this.getPreferBook();
	}
	
	public void printPurchase() {
		System.out.print(this + " purchased :");
		for(Book b : purchase)
			System.out.print(b);
		System.out.println();
	}

}
