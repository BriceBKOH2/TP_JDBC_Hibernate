package table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table
public class Book {
	@Id
	@GeneratedValue
	Long id;
	
	@Column
	@NotBlank
	String title;
	
	@Column
	String author;
	
	@OneToMany (mappedBy = "preferedBook")
	List<Client> preferedBy;
	
	@ManyToMany
	List<Client> buyedBy;
	
	
	// Constructors
	
	public Book() {
		
	}
	
	public Book(String title) {
		this.title = title;
		preferedBy = new ArrayList<Client>();
		buyedBy = new ArrayList<Client>();
	}
	
	public Book(String title,String author) {
		this.title = title;
		this.author = author;
		preferedBy = new ArrayList<Client>();
		buyedBy = new ArrayList<Client>();
	}

	// Methods 
	
		// Getters
	
	public long getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public String getAuthor() {
		return author;
	}
	
	public List<Client> getPreferedBy() {
		return preferedBy;
	}
	
	public List<Client> getBuyedBy() {
		return buyedBy;
	}
	
	
		// Setters

	public void setId(long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	

	public void setPreferedBy(List<Client> clients) {
		this.preferedBy = clients;
	}
	
	public void addPreferedBy(Client client) {
		preferedBy.add(client);
	}
	
	public void addPreferedBy(List<Client> client) {
		preferedBy.addAll(client);
	}

	public void setBuyedBy(List<Client> buyedBy) {
		this.buyedBy = buyedBy;
	}
	
	public void addBuyedBy(Client client) {
		buyedBy.add(client);
	}
	
	public void addBuyedBy(List<Client> client) {
		buyedBy.addAll(client);
	}
	
		// Others
	
	public String toString() {
		return "[id= " + this.getId() + " title= " + this.getTitle() + " author= " + this.getAuthor() + "]";
	}
}
