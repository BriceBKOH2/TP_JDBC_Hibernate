package tp_dbase.Table;

public class Client {
	Long id;
	String lastname;
	String firstname;
	Gender gender;
	Book preferBook;
	
	// Constructors
	
	public Client() {
		lastname ="Default";
		firstname ="Default";
		gender = Gender.F;
	}
	
	 // simplified constructor with F or M instead of Gender.F / .M
	
	public Client(String lastname, String firstname, Gender gender) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.gender = gender;
	}

	
	public Client(String lastname, String firstname, Gender gender, Book preferBook) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.gender = gender;
		this.preferBook = preferBook;
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
	
	public Gender getGender() {
		return gender;
		
	}
	
	public Book getPreferBook() {
		return preferBook;
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

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setPreferBook(Book preferBook) {
		this.preferBook = preferBook;
	}

}
