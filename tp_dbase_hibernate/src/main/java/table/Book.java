package table;

public class Book {
	Long id;
	String title;
	String author;
	
	// Constructors
	
	public Book(String title) {
		this.title = title;
	}
	
	public Book(String title,String author) {
		this.title = title;
		this.author = author;
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
	

}
