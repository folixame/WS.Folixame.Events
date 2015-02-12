package ws.folixame.events.webservices;

/**  
 * @descriptionription this class defines how to initialize a category, the parameters and the getters and setters to access the different parameters.
 * @author Amaia Eskisabel Azpiazu, Gonzalo Fernández Naveira, Jorge Yagüe París 
*/ 

public class EventsCategories {
	//id - Category's identification number
	private int id;
	//name - Category's name
	private String name;
	
	/** 
	  * @description initialize an empty category
	*/
	
	public EventsCategories(){}

	/** 
	  * @description initialize a category with parameters
	  * @param id - category's identification number
	  * @param name - category's name
	*/
	
	public EventsCategories(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/** 
	  * @description getter for the identification number of the category
	  * @return id - category's identification number
	*/
	
	
	public int getId() {
		return id;
	}
	
	/** 
	  * @description setter for the identification number of the category
	  * @param id - category's identification number
	*/

	public void setId(int id) {
		this.id = id;
	}
	
	/** 
	  * @description getter for the name of the category
	  * @return name - category's name
	*/

	public String getName() {
		return name;
	}
	
	/** 
	  * @description setter for the name of the category
	  * @param name - category's name
	*/
	
	public void setName(String name) {
		this.name = name;
	}
}
