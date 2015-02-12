package ws.folixame.events.webservices;

import java.util.ArrayList;
import java.util.Date;

/**  
 * @description this class defines how to initialize an event, the parameters and the getters and setters to access the different parameters.
 * @author Amaia Eskisabel Azpiazu, Gonzalo Fernández Naveira, Jorge Yagüe París 
*/ 

public class Event {
	
	//id - event's identification number
	private int id;
	//name - event's name
	private String name;
	//place - event's location
	private String place;
	//shortDescription - a short description of the event
	private String shortDescription;
	//longDescription - a detail description of the event
	private String longDescription;
	//eventsCategoriesId - event's category
	private int eventsCategoriesId;
	//eventDate - the most important day in the event
	private Date eventDate;
	//startDate - event's start date
	private Date startDate;
	//endDate - event's end date
	private Date endDate;
	//validated - parameter that describes the state of the event, 0=not validated and 1 = validated
	private int validated;
	//images - parameter that has all the images of the event
	private ArrayList<Image> images;
	
	/** 
	  * @description initialize an empty event
	*/
	
	public Event() {}

	/** 
	  * @description initialize an event without images
	  * @param id - event's identification number
	  * @param name - event's name
	  * @param place - event's location
	  * @param shortDescription - a short description of the event
	  * @param longDescription - a detail description of the event
	  * @param eventsCategoriesId - event's category
	  * @param eventDate - the most important day in the event
	  * @param startDate - event's start date
	  * @param endDate - event's end date
	  * @param validated - parameter that describes the state of the event, 0=not validated and 1 = validated
	*/
		
	public Event(int id, String name, String place,
			String shortDescription, String longDescription, int eventsCategoriesId,
			Date eventDate, Date startDate, Date endDate, int validated) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.eventsCategoriesId = eventsCategoriesId;
		this.eventDate = eventDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.validated = validated;
	}
	
	/** 
	  * @description initialize an event with images
	  * @param id - event's identification number
	  * @param name - event's name
	  * @param place - event's location
	  * @param shortDescription - a short description of the event
	  * @param longDescription - a detail description of the event
	  * @param eventsCategoriesId - event's category
	  * @param eventDate - the most important day in the event
	  * @param startDate - event's start date
	  * @param endDate - event's end date
	  * @param validated - parameter that describes the state of the event, 0=not validated and 1 = validated
	  * @param images - parameter that has all the images of the event
	*/
	
	public Event(int id, String name, String place,
			String shortDescription, String longDescription, int eventsCategoriesId,
			Date eventDate, Date startDate, Date endDate, int validated,
			ArrayList<Image> images) {
		super();
		this.id = id;
		this.name = name;
		this.place = place;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.eventsCategoriesId = eventsCategoriesId;
		this.eventDate = eventDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.validated = validated;
		this.images = images;
	}
	
	/** 
	  * @description getter for the identification number of the event
	  * @return id - event's identification number
	*/

	public int getId() {
		return id;
	}

	/** 
	  * @description setter for the identification number of the event
	  * @param id - event's identification number
	*/
	
	public void setId(int id) {
		this.id = id;
	}

	/** 
	  * @description getter for the name of the event
	  * @return name - event's name
	*/
	
	public String getName() {
		return name;
	}

	/** 
	  * @description setter for the name of the event
	  * @param name - event's name
	*/
	
	public void setName(String name) {
		this.name = name;
	}
	
	/** 
	  * @description getter for the place of the event
	  * @return place - event's place
	*/

	public String getPlace() {
		return place;
	}

	/** 
	  * @description setter for the place of the event
	  * @param place - event's place
	*/
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	/** 
	  * @description getter for the short description of the event
	  * @return shortDescription - event's short description
	*/

	public String getShortDescription() {
		return shortDescription;
	}

	/** 
	  * @description setter for the short description of the event
	  * @param shortDescription - event's short description
	*/
	
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/** 
	  * @description getter for the detail description of the event
	  * @return longDescription - event's detail description
	*/
	
	public String getLongDescription() {
		return longDescription;
	}

	/** 
	  * @description setter for the detail description of the event
	  * @param longDescription - event's detail description
	*/
	
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
	/** 
	  * @description getter for the category type of the event
	  * @return eventsCategoriesId - event's category type
	*/

	public int getEventsCategoriesId() {
		return eventsCategoriesId;
	}

	/** 
	  * @description setter for the category type of the event
	  * @param eventsCategoriesId - event's category type
	*/
	
	public void setEventsCategoriesId(int eventsCategoriesId) {
		this.eventsCategoriesId = eventsCategoriesId;
	}
	
	/** 
	  * @description getter for the most important date of the event
	  * @return eventDate - event's most important date
	*/

	public Date getEventDate() {
		return eventDate;
	}

	/** 
	  * @description setter for the most important date of the event
	  * @param eventDate - event's most important date
	*/
	
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/** 
	  * @description getter for the beginning(date) of the event
	  * @return startDate - event's start date
	*/
	
	public Date getStartDate() {
		return startDate;
	}

	/** 
	  * @description setter for the beginning(date) of the event
	  * @param startDate - event's start date
	*/
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/** 
	  * @description getter for the end of the event
	  * @return endDate - event's latest date
	*/
	
	public Date getEndDate() {
		return endDate;
	}

	/** 
	  * @description setter for the end of the event
	  * @param endDate - event's latest date
	*/
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/** 
	  * @description getter for the validation of the event
	  * @return validated - sets if the event is validated or not
	*/

	public int getValidated() {
		return validated;
	}

	/** 
	  * @description setter for the validation of the event
	  * @param validated - sets if the event is validated or not
	*/
	
	public void setValidated(int validated) {
		this.validated = validated;
	}
	
	
	/** 
	  * @description getter for the images of the event
	  * @return images - sets the images of the event
	*/

	public ArrayList<Image> getImages() {
		return images;
	}

	/** 
	  * @description setter for the images of the event
	  * @param images - sets the images of the event
	*/
	
	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}
}
