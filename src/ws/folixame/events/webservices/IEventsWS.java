package ws.folixame.events.webservices;

import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.bind.annotation.XmlElement;

/**  
 * @description this class will be the interface for the web services
 * @author Amaia Eskisabel Azpiazu, Gonzalo Fernández Naveira, Jorge Yagüe París 
*/ 

@WebService(serviceName = "Events")
@SOAPBinding (style = Style.DOCUMENT)
public interface IEventsWS {
	
	/** 
	  * @description insert the events in the database
	  * @param name - event's name
	  * @param place - event's location
	  * @param shortDescription - a short description of the event
	  * @param longDescription - a detail description of the event
	  * @param eventsCategoriesId - event's category
	  * @param eventDate - the most important day in the event
	  * @param startDate - event's start date
	  * @param endDate - event's end date
	  * @param validated - parameter that describes the state of the event, 0=not validated and 1 = validated
	  * @return boolean - true if the event has been created and false if not
	  * @throws DatabaseException - error creating event
	*/
	
	@WebMethod(operationName = "createEvent")
	boolean createEvent(@WebParam(name = "name") @XmlElement(required=true) String name, @WebParam(name = "place") @XmlElement(required=true) String place, @WebParam(name = "shortDescription") @XmlElement(required=true) String shortDescription, @WebParam(name = "longDescription") @XmlElement(required=true) String longDescription, @WebParam(name = "categoryId") @XmlElement(required=true) int eventscategoriesid, @WebParam(name = "eventDate") @XmlElement(required=true) Date eventDate,
			@WebParam(name = "startDate") @XmlElement(required=true) Date startDate, @WebParam(name = "endDate") @XmlElement(required=true) Date endDate, @WebParam(name = "validated") @XmlElement(required=true) int validated) throws DatabaseException;
	/** 
	  * @description read all validated events from the database
	  * @return events - a list with all the events that are validated by the administrator
	  * @throws DatabaseException - error reading validated events
	*/
	
	@WebMethod(operationName = "readEventsForUsers")
	public ArrayList<Event> readEventsForUsers(@WebParam(name = "validated") @XmlElement(required=true) int validated) throws DatabaseException;
	
	/** 
	  * @description read all the events that are not validated from the database
	  * @return events - a list with all the events that are not validated by the administrator
	  * @throws DatabaseException - error reading not validated events
	*/
	
	@WebMethod(operationName = "readEventsForAdministrator")
	public ArrayList<Event> readEventsForAdministrator(@WebParam(name = "validated") @XmlElement(required=true) int validated) throws DatabaseException;
	
	/** 
	  * @description update the events in the database
	  * @param id - event's identification number
	  * @param name - event's name
	  * @param place - event's location
	  * @param shortDescription - a short description of the event
	  * @param longDescription - a detail description of the event
	  * @param eventsCategoriesId - event's category
	  * @param eventDate - the most important day in the event
	  * @param startDate - event's start date
	  * @param endDate - event's end date
	  * @param validated
	  * @return boolean - true if the event has been updated and false if not
	  * @throws DatabaseException - error updating event by id
	*/
	
	@WebMethod(operationName = "updateEventById")
	public boolean updateEventById(@WebParam(name = "id") @XmlElement(required=true) int id, @WebParam(name = "name") @XmlElement(required=true) String name, @WebParam(name = "place") @XmlElement(required=true) String place, @WebParam(name = "shortDescription") @XmlElement(required=true) String shortDescription, @WebParam(name = "longDescription") @XmlElement(required=true) String longDescription, @WebParam(name = "categoryId") @XmlElement(required=true) int eventscategoriesid,
			@WebParam(name = "eventDate") @XmlElement(required=true) Date eventDate, @WebParam(name = "startDate") @XmlElement(required=true) Date startDate, @WebParam(name = "endDate") @XmlElement(required=true) Date endDate, @WebParam(name = "validated") @XmlElement(required=true) int validated) throws DatabaseException;

	/** 
	  * @description delete an event from the database using the identification number
	  * @param id - event's identification number
	  * @return boolean - true if the event has been deleted and false if not
	  * @throws DatabaseException - error deleting event by id
	*/
	
	@WebMethod(operationName = "deleteEventById")
	public boolean deleteEventById(@WebParam(name = "id") @XmlElement(required=true) int id) throws DatabaseException;
	
	/** 
	  * @description read an event from the database using the identification number
	  * @param id - event's identification number
	  * @return event - object that has all the information that is related with the event
	  * @throws DatabaseException - error reading event by id
	*/
	
	@WebMethod(operationName = "readEventById")
	public Event readEventById(@WebParam(name = "eventId") @XmlElement(required=true) int eventId) throws DatabaseException;
	
	/** 
	  * @description reads all the events from the database for a concrete category
	  * @param eventsCategoriesId - event's identification number
	  * @return events - a list with all the events for selected category
	  * @throws DatabaseException - error reading event by category
	*/
	
	@WebMethod(operationName = "readEventsByCategory")
	public ArrayList<Event> readEventsByCategory(@WebParam(name = "categoryId") @XmlElement(required=true) int eventscategoriesid) throws DatabaseException;
	
	/** 
	  * @description reads all the events from the database for a concrete place
	  * @param place - place where the event will be celebrated
	  * @return events - a list with all the events for selected place
	  * @throws DatabaseException - error reading event by place
	*/
	
	@WebMethod(operationName = "readEventsByPlace")
	public ArrayList<Event> readEventsByPlace(@WebParam(name = "place") @XmlElement(required=true) String place) throws DatabaseException;
	
	/** 
	  * @description reads all the events from the database that will be celebrated between two dates
	  * @param startDate - earliest day for the event
	  * @param endDate - latest day for the events
	  * @return events - a list with all the events for selected dates
	  * @throws DatabaseException - error reading event that will be celebrated between two dates
	*/
	
	@WebMethod(operationName = "readEventsBetweenDates")
	public ArrayList<Event> readEventsBetweenDates(@WebParam(name = "startDate") @XmlElement(required=true) Date startDate, @WebParam(name = "endDate") @XmlElement(required=true) Date endDate) throws DatabaseException;
	
	/** 
	  * @description reads all the events that fulfill a description, category, place and dates
	  * @param text - event's description
	  * @param eventsCategoriesId - category's identification number
	  * @param place - event's place
	  * @param startDate - earliest day for the event
	  * @param endDate - latest day for the events
	  * @return events - a list with all the events for selected dates
	  * @throws DatabaseException - error reading event using an advanced search
	*/

	
	@WebMethod(operationName = "readEventsAdvancedSearch")
	public ArrayList<Event> readEventsAdvancedSearch(@WebParam(name = "text") @XmlElement(required=true) String text, @WebParam(name = "categoryId") @XmlElement(required=true) int eventscategoriesid, @WebParam(name = "place") @XmlElement(required=true) String place, @WebParam(name = "startDate") @XmlElement(required=true) Date startDate, @WebParam(name = "endDate") @XmlElement(required=true) Date endDate) throws DatabaseException;
	
	/** 
	  * @description creates an image for a event
	  * @param name - image's name
	  * @param url - url where the image is located
	  * @param alt - image's description
	  * @param eventsId - event's identification number
	  * @return boolean - true if the image has been created and false if not
	  * @throws DatabaseException - error creating an image
	*/
	
	@WebMethod(operationName = "createImage")
	boolean createImage(@WebParam(name = "name") @XmlElement(required=true) String name, @WebParam(name = "url") @XmlElement(required=true) String url, @WebParam(name = "alt") @XmlElement(required=true) String alt, @WebParam(name = "eventsId") @XmlElement(required=true) int eventsId) throws DatabaseException;
	
	/** 
	  * @description reads all the images from the database for a concrete event
	  * @param eventsId - event's identification number
	  * @return images - a list with all the images for selected event
	  * @throws DatabaseException - error reading images by event id
	*/
	
	@WebMethod(operationName = "readImagesByEventId")
	ArrayList<Image> readImagesByEventId(@WebParam(name = "eventsId") @XmlElement(required=true) int eventsId) throws DatabaseException;
	
	/** 
	  * @description updates an image for a event
	  * @param name - image's name
	  * @param url - url where the image is located
	  * @param alt - image's description
	  * @param eventsId - event's identification number
	  * @return boolean - true if the image has been updated and false if not
	  * @throws DatabaseException - error updating image by event id
	*/
	
	@WebMethod(operationName = "updateImageByEventId")
	boolean updateImageByEventId(@WebParam(name = "eventsId") @XmlElement(required=true) int eventsId, @WebParam(name = "name") @XmlElement(required=true) String name, @WebParam(name = "url") @XmlElement(required=true) String url, @WebParam(name = "alt") @XmlElement(required=true) String alt) throws DatabaseException;
	
	/** 
	  * @description deletes an image for a event
	  * @param eventsId - event's identification number
	  * @return boolean - true if the image has been deleted and false if not
	  * @throws DatabaseException - error deleting image by event' id
	*/
	
	@WebMethod(operationName = "deleteImagesByEventId")
	boolean deleteImagesByEventId(@WebParam(name = "eventsId") @XmlElement(required=true) int eventsId) throws DatabaseException;
	
	/** 
	  * @description deletes an image
	  * @param imageId - image's identification number
	  * @return boolean - true if the image has been deleted and false if not
	  * @throws DatabaseException - error deleting image
	*/
	
	@WebMethod(operationName = "deleteImagesById")
	boolean deleteImagesById(@WebParam(name = "id") @XmlElement(required=true) int id) throws DatabaseException;
	
	/** 
	  * @description creates a category
	  * @param name - name of the category
	  * @return boolean - true if the category has been created and false if not
	  * @throws DatabaseException - error creating a category
	*/
	
	@WebMethod(operationName = "createCategory")
	boolean createCategory(@WebParam(name = "name") @XmlElement(required=true) String name) throws DatabaseException;
	
	/** 
	  * @description reads all the categories from the database
	  * @return categories - a list with all the categories
	  * @throws DatabaseException - error reading the categories
	*/
	
	@WebMethod(operationName = "readAllCategories")
	ArrayList<EventsCategories> readAllCategories() throws DatabaseException;
	
	/** 
	  * @description updates a category
	  * @param name - category's name
	  * @return boolean - true if the category has been updated and false if not
	  * @throws DatabaseException - error updating category by id
	*/
	
	@WebMethod(operationName = "updateCategoryById")
	boolean updateCategoryById(@WebParam(name = "id") @XmlElement(required=true) int id, @WebParam(name = "name") @XmlElement(required=true) String name) throws DatabaseException;
	
	/** 
	  * @description deletes a category
	  * @param id - category's identification number
	  * @return boolean - true if the category has been deleted and false if not
	  * @throws DatabaseException - error deleting a category
	*/
	
	@WebMethod(operationName = "deleteCategoryById")
	boolean deleteCategoryById(@WebParam(name = "id") @XmlElement(required=true) int id) throws DatabaseException;
}
