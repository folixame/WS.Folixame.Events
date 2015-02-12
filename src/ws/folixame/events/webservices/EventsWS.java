package ws.folixame.events.webservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.jws.WebService;

/**  
 * @description this class will hold web services
 * @author Amaia Eskisabel Azpiazu, Gonzalo Fernández Naveira, Jorge Yagüe París 
*/ 

@WebService(endpointInterface = "ws.folixame.events.webservices.IEventsWS")
public class EventsWS implements IEventsWS {
	
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
	
	@Override
	public boolean createEvent(String name, String place, String shortDescription, String longDescription, int eventsCategoriesId, Date eventDate,
			Date startDate, Date endDate, int validated) throws DatabaseException {
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection;
		
		connection = dbManagement.dbConnection();

		String sql = "insert into Events(name,place,short_description,long_description,Events_Categories_id,date,start_date,end_date,validated) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, place);
			st.setString(3, shortDescription);
			st.setString(4, longDescription);
			st.setInt(5, eventsCategoriesId);
			st.setDate(6, new java.sql.Date(eventDate.getTime()));
			st.setDate(7, new java.sql.Date(startDate.getTime()));
			st.setDate(8, new java.sql.Date(endDate.getTime()));
			st.setInt(9, validated);
		    st.execute();
		    st.close();
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to create the event");
		} finally {
			dbManagement.closeConnection(connection);
		}
		return true;
	}

	/** 
	  * @description read all validated events from the database
	  * @return events - a list with all the events that are validated by the administrator
	  * @throws DatabaseException - error reading validated events
	*/
	
	@Override
	public ArrayList<Event> readEventsForUsers(int validated) throws DatabaseException {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Image> images;
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "select * from Events where validated = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setInt(1, validated);
		    ResultSet rs = st.executeQuery();

		    if(!rs.isBeforeFirst()) {
		    	throw new DatabaseException("Could not find validated events");
		    } else {
		    	while (rs.next()) {
			    	int id = rs.getInt("id");
			    	String name = rs.getString("name");
			    	String place = rs.getString("place");
			    	String shortDescription = rs.getString("short_description");
			    	String longDescription = rs.getString("long_description");
			    	int eventsCategoriesId = rs.getInt("Events_Categories_id");
			    	Date date = rs.getDate("date");
			    	Date startDate = rs.getDate("start_date");
			    	Date endDate = rs.getDate("end_date");
			    	images = readImagesByEventId(id);
			    	Event event = new Event(id, name, place, shortDescription, longDescription, eventsCategoriesId, date, startDate, endDate, validated, images);
			    	events.add(event);
			    }
			    st.close();
		    }

		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to read events");
		} finally {
			dbManagement.closeConnection(connection);
		} 

		return events;
	}
	
	/** 
	  * @description read all the events that aren not validated from the database
	  * @return events - a list with all the events that are not validated by the administrator
	  * @throws DatabaseException - error reading not validated events
	*/
	
	@Override
	public ArrayList<Event> readEventsForAdministrator(int validated) throws DatabaseException {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Image> images;
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "select * from Events where validated = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setInt(1, validated);
		    ResultSet rs = st.executeQuery();

		    if(!rs.isBeforeFirst()){
		    	throw new DatabaseException("Could not find  not validated events");
		    } else {
		    	while (rs.next()) {
			    	int id = rs.getInt("id");
			    	String name = rs.getString("name");
			    	String place = rs.getString("place");
			    	String shortDescription = rs.getString("short_description");
			    	String longDescription = rs.getString("long_description");
			    	int eventsCategoriesId = rs.getInt("Events_Categories_id");
			    	Date date = rs.getDate("date");
			    	Date startDate = rs.getDate("start_date");
			    	Date endDate = rs.getDate("end_date");
			    	images = readImagesByEventId(id);
			    	Event event = new Event(id, name, place, shortDescription, longDescription, eventsCategoriesId, date, startDate, endDate, validated, images);
			    	events.add(event);
			    }
			    st.close();
		    }
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to read not validated events");
		} finally {
			dbManagement.closeConnection(connection);
		} 

		return events;
	}
	
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
	
	@Override
	public boolean updateEventById(int id, String name, String place,
			String shortDescription, String longDescription, int eventsCategoriesId,
			Date eventDate, Date startDate, Date endDate,  int validated) throws DatabaseException {

		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "update Events set name=?, place=?, short_description=?, long_description=?, Events_Categories_id=?, date=?, start_date=?, end_date=?, validated=? where id = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, place);
			st.setString(3, shortDescription);
			st.setString(4, longDescription);
			st.setInt(5, eventsCategoriesId);
			st.setDate(6, new java.sql.Date(eventDate.getTime()));
			st.setDate(7, new java.sql.Date(startDate.getTime()));
			st.setDate(8, new java.sql.Date(endDate.getTime()));
			st.setInt(9, validated);
			st.setInt(10, id);
		    st.execute();
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to update event by id");
		} finally {
			dbManagement.closeConnection(connection);
		}
		return true;
	}

	/** 
	  * @description delete an event from the database using the identification number
	  * @param id - event's identification number
	  * @return boolean - true if the event has been deleted and false if not
	  * @throws DatabaseException - error deleting event by id
	*/
	
	@Override
	public boolean deleteEventById(int id) throws DatabaseException {
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "delete from Events where id = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setInt(1, id);
		    st.execute();
		    st.close();
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to delete event by id");
		}  finally {
			dbManagement.closeConnection(connection);
		}
		return true;
	}
	
	/** 
	  * @description read an event from the database using the identification number
	  * @param id - event's identification number
	  * @return event - object that has all the information that is related with the event
	  * @throws DatabaseException - error reading event by id
	*/
	
	@Override
	public Event readEventById(int id) throws DatabaseException {
		Event event = null;
		ArrayList<Image> images;
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "select * from Events where id = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setInt(1, id);
		    ResultSet rs = st.executeQuery();
		    if(!rs.isBeforeFirst()){
		    	throw new DatabaseException("Could not read event by id");
		    } else {
		    	while (rs.next()) {
			    	String name = rs.getString("name");
			    	String place = rs.getString("place");
			    	String shortDescription = rs.getString("short_description");
			    	String longDescription = rs.getString("long_description");
			    	int eventsCategoriesId = rs.getInt("Events_Categories_id");
			    	Date date = rs.getDate("date");
			    	Date startDate = rs.getDate("start_date");
			    	Date endDate = rs.getDate("end_date");
			    	int validated = rs.getInt("validated");
			    	images = readImagesByEventId(id);
			    	event = new Event(id, name, place, shortDescription, longDescription, eventsCategoriesId, date, startDate, endDate, validated, images);
				}
			    st.close();
		    } 
		    
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to read event by id");
		} finally {
			dbManagement.closeConnection(connection);
		} 
		return event;
	}

	/** 
	  * @description reads all the events from the database for a concrete category
	  * @param eventsCategoriesId - event's identification number
	  * @return events - a list with all the events for selected category
	  * @throws DatabaseException - error reading event by category
	*/
		
	@Override
	public ArrayList<Event> readEventsByCategory(int eventsCategoriesId) throws DatabaseException {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Image> images;
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "select * from Events where Events_Categories_id = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setInt(1, eventsCategoriesId);
		    ResultSet rs = st.executeQuery();

		    if(!rs.isBeforeFirst()){
		    	throw new DatabaseException("Could not find events by category");
		    } else {
		    	while (rs.next()) {
			    	int id = rs.getInt("id");
			    	String name = rs.getString("name");
			    	String place = rs.getString("place");
			    	String shortDescription = rs.getString("short_description");
			    	String longDescription = rs.getString("long_description");
			    	Date date = rs.getDate("date");
			    	Date startDate = rs.getDate("start_date");
			    	Date endDate = rs.getDate("end_date");
			    	int validated = rs.getInt("validated");
			    	images = readImagesByEventId(id);
			    	Event event = new Event(id, name, place, shortDescription, longDescription, eventsCategoriesId, date, startDate, endDate, validated, images);
			    	events.add(event);
		    	}
		    	st.close();
		    }

		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to read events by category");
		} finally {
			dbManagement.closeConnection(connection);
		} 
		return events;
	}

	/** 
	  * @description reads all the events from the database for a concrete place
	  * @param place - place where the event will be celebrated
	  * @return events - a list with all the events for selected place
	  * @throws DatabaseException - error reading event by place
	*/
	
	@Override
	public ArrayList<Event> readEventsByPlace(String place) throws DatabaseException {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Image> images;
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "select * from Events where place = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, place);
		    ResultSet rs = st.executeQuery();

		    if(!rs.isBeforeFirst()){
		    	throw new DatabaseException("Could not find events by place");
		    } else {
		    	while (rs.next()) {
			    	int id = rs.getInt("id");
			    	String name = rs.getString("name");
			    	String shortDescription = rs.getString("short_description");
			    	String longDescription = rs.getString("long_description");
			    	int eventsCategoriesId = rs.getInt("Events_Categories_id");
			    	Date date = rs.getDate("date");
			    	Date startDate = rs.getDate("start_date");
			    	Date endDate = rs.getDate("end_date");
			    	int validated = rs.getInt("validated");
			    	images = readImagesByEventId(id);
			    	Event event = new Event(id, name, place, shortDescription, longDescription, eventsCategoriesId, date, startDate, endDate, validated, images);
			    	events.add(event);
			    }
			    st.close();
		    }

		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to read events by place");
		} finally {
			dbManagement.closeConnection(connection);
		} 

		return events;
	}
	
	/** 
	  * @description reads all the events from the database that will be celebrated between two dates
	  * @param startDate - earliest day for the event
	  * @param endDate - latest day for the events
	  * @return events - a list with all the events for selected dates
	  * @throws DatabaseException - error reading event that will be celebrated between two dates
	*/

	@Override
	public ArrayList<Event> readEventsBetweenDates(Date startDate, Date endDate) throws DatabaseException {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Image> images;
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "select * from Events where start_date between ? and ? and end_date between ? and ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setDate(1, new java.sql.Date(startDate.getTime()));
			st.setDate(2, new java.sql.Date(endDate.getTime()));
			st.setDate(3, new java.sql.Date(startDate.getTime()));
			st.setDate(4, new java.sql.Date(endDate.getTime()));
		    ResultSet rs = st.executeQuery();

		    if(!rs.isBeforeFirst()){
		    	throw new DatabaseException("Could not find events between certain dates");
		    } else {
		    	while (rs.next()) {
			    	int id = rs.getInt("id");
			    	String name = rs.getString("name");
			    	String place = rs.getString("place");
			    	String shortDescription = rs.getString("short_description");
			    	String longDescription = rs.getString("long_description");
			    	int eventsCategoriesId = rs.getInt("Events_Categories_id");
			    	Date date = rs.getDate("date");
			    	int validated = rs.getInt("validated");
			    	images = readImagesByEventId(id);
			    	Event event = new Event(id, name, place, shortDescription, longDescription, eventsCategoriesId, date, startDate, endDate, validated, images);
			    	events.add(event);
			    }
			    st.close();
		    }
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to read events between certain dates");
		} finally {
			dbManagement.closeConnection(connection);
		} 

		return events;
	}
	
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

	@Override
	public ArrayList<Event> readEventsAdvancedSearch(String text, int eventsCategoriesId, String place, Date startDate, Date endDate) throws DatabaseException {
		ArrayList<Event> events = new ArrayList<Event>();
		ArrayList<Image> images;
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "select * from Events where long_description like ? and Events_Categories_id=? and place=? and start_date between ? and ? and end_date between ? and ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, "%"+text+"%");
			st.setInt(2, eventsCategoriesId);
			st.setString(3, place);
			st.setDate(4, new java.sql.Date(startDate.getTime()));
			st.setDate(5, new java.sql.Date(endDate.getTime()));
			st.setDate(6, new java.sql.Date(startDate.getTime()));
			st.setDate(7, new java.sql.Date(endDate.getTime()));
		    ResultSet rs = st.executeQuery();

		    if(!rs.isBeforeFirst()){
		    	throw new DatabaseException("Could not find events with those parameters");
		    } else {
		    	while (rs.next()) {
			    	int id = rs.getInt("id");
			    	String name = rs.getString("name");
			    	String shortDescription = rs.getString("short_description");
			    	String longDescription = rs.getString("long_description");
			    	Date date = rs.getDate("date");
			    	int validated = rs.getInt("validated");
			    	images = readImagesByEventId(id);
			    	Event event = new Event(id, name, place, shortDescription, longDescription, eventsCategoriesId, date, startDate, endDate, validated, images);
			    	events.add(event);
			    }
			    st.close();
		    }

		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to read events between certain dates");
		} finally {
			dbManagement.closeConnection(connection);
		} 

		return events;
	}
	
	/** 
	  * @description creates an image for a event
	  * @param name - image's name
	  * @param url - url where the image is located
	  * @param alt - image's description
	  * @param eventsId - event's identification number
	  * @return boolean - true if the image has been created and false if not
	  * @throws DatabaseException - error creating an image
	*/
	
	@Override
	public boolean createImage(String name, String url, String alt, int eventsId) throws DatabaseException {
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "insert into Images(name,url,alt,Events_id) values(?,?,?,?)";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, url);
			st.setString(3, alt);
			st.setInt(4, eventsId);
		    st.execute();
		    st.close();
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to create the image");
		} finally {
			dbManagement.closeConnection(connection);
		}
		return true;
	}
	
	/** 
	  * @description reads all the images from the database for a concrete event
	  * @param eventsId - event's identification number
	  * @return images - a list with all the images for selected event
	  * @throws DatabaseException - error reading images by event id
	*/
	
	@Override
	public ArrayList<Image> readImagesByEventId(int eventsId) throws DatabaseException {
		ArrayList<Image> images = new ArrayList<Image>();
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "select * from Images where Events_id = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setInt(1, eventsId);
		    ResultSet rs = st.executeQuery();

		    if(!rs.isBeforeFirst()){
		    	throw new DatabaseException("Could not find images by event id");
		    } else {
		    	while (rs.next()) {
			    	int imageId = rs.getInt("id");
			    	String name = rs.getString("name");
			    	String url = rs.getString("url");
			    	String alt = rs.getString("alt");
			    	Image image = new Image(imageId, name, url, alt, eventsId);
			    	images.add(image);
			    }
			    st.close();
		    }

		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to read images by event id");
		} finally {
			dbManagement.closeConnection(connection);
		} 
		return images;
	}
	
	/** 
	  * @description updates an image for a event
	  * @param name - image's name
	  * @param url - url where the image is located
	  * @param alt - image's description
	  * @param eventsId - event's identification number
	  * @return boolean - true if the image has been updated and false if not
	  * @throws DatabaseException - error updating image by event id
	*/
	
	@Override
	public boolean updateImageByEventId(int eventsId,String name, String url, String alt) throws DatabaseException {
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "update Images set name=? and url=? and alt=? where Events_id = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, url);
			st.setString(3, alt);
			st.setInt(4, eventsId);
		    st.execute();
		    st.close();
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to update image by id");
		}  finally {
			dbManagement.closeConnection(connection);
		}
		return true;
	}
	
	/** 
	  * @description deletes an image for a event
	  * @param eventsId - event's identification number
	  * @return boolean - true if the image has been deleted and false if not
	  * @throws DatabaseException - error deleting image by event' id
	*/
	
	@Override
	public boolean deleteImagesByEventId(int eventsId) throws DatabaseException {
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "delete from Images where Events_id = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setInt(1, eventsId);
		    st.execute();
		    st.close();
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to delete image by event id");
		}  finally {
			dbManagement.closeConnection(connection);
		}
		return true;
	}
	
	/** 
	  * @description deletes an image
	  * @param imageId - image's identification number
	  * @return boolean - true if the image has been deleted and false if not
	  * @throws DatabaseException - error deleting image
	*/
	
	@Override
	public boolean deleteImagesById(int imageId) throws DatabaseException {
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "delete from Images where id = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setInt(1, imageId);
		    st.execute();
		    st.close();
		    return true;
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to delete image by id");
		}  finally {
			dbManagement.closeConnection(connection);
		} 
	}
	
	/** 
	  * @description creates a category
	  * @param name - name of the category
	  * @return boolean - true if the category has been created and false if not
	  * @throws DatabaseException - error creating a category
	*/
	
	@Override
	public boolean createCategory(String name) throws DatabaseException {
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "insert into Events_Categories(name) values(?)";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, name);
		    st.execute();
		    st.close();
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to create the category");
		}  finally {
			dbManagement.closeConnection(connection);
		}
		return true;
	}
	
	/** 
	  * @description reads all the categories from the database
	  * @return categories - a list with all the categories
	  * @throws DatabaseException - error reading the categories
	*/
	
	@Override
	public ArrayList<EventsCategories> readAllCategories() throws DatabaseException {
		ArrayList<EventsCategories> categories = new ArrayList<EventsCategories>();
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "select * from Events_Categories";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
		    ResultSet rs = st.executeQuery();

		    if(!rs.isBeforeFirst()){
		    	throw new DatabaseException("Could not read the categories");
		    } else {
			    	while (rs.next()) {
			    	int id = rs.getInt("id");
			    	String name = rs.getString("name");
			    	EventsCategories category = new EventsCategories(id, name);
			    	categories.add(category);
			    }
			    st.close();
		    }

		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to read the categories");
		} finally {
			dbManagement.closeConnection(connection);
		} 
		return categories;
	}
	
	/** 
	  * @description updates a category
	  * @param name - category's name
	  * @return boolean - true if the category has been updated and false if not
	  * @throws DatabaseException - error updating category by id
	*/
	
	@Override
	public boolean updateCategoryById(int id,String name) throws DatabaseException {
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "update Events_Categories set name=? where id = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setString(1, name);
			st.setInt(2, id);
		    st.execute();
		    st.close();
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to update category");
		} finally {
			dbManagement.closeConnection(connection);
		}
		return true;
	}
	
	/** 
	  * @description deletes a category
	  * @param id - category's identification number
	  * @return boolean - true if the category has been deleted and false if not
	  * @throws DatabaseException - error deleting a category
	*/
	
	@Override
	public boolean deleteCategoryById(int id) throws DatabaseException {
		DatabaseManagement dbManagement = new DatabaseManagement();
		Connection connection = dbManagement.dbConnection();
		String sql = "delete from Events_Categories where id = ?";
		PreparedStatement st;
		try {
			st = connection.prepareStatement(sql);
			st.setInt(1, id);
		    st.execute();
		    st.close();
		} catch (SQLException e) {
			throw new DatabaseException("Database error while trying to delete category");
		} finally {
			dbManagement.closeConnection(connection);
		}
		return true;
	}
}
