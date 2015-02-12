package ws.folixame.events.webservices;

/**  
 * @descriptionriptionriptionriptionriptionriptionriptionription this class defines how to initialize an image, the parameters and the getters and setters to access the different parameters.
 * @author Amaia Eskisabel Azpiazu, Gonzalo Fernández Naveira, Jorge Yagüe París 
*/ 

public class Image {
	//imageId - image's identification number
	private int imageId;
	//name - image's name
	private String name;
	//url - image's url
	private String url;
	//alt - image's description
	private String alt;
	//eventsId - event's identification number
	private int eventsId;
	
	/** 
	  * @description initialize an empty image
	*/
	
	public Image() {}

	/** 
	  * @description initialize a image with parameters
	  * @param imageId - image's identification number
	  * @param name - image's name
	  * @return url - image's url
	  * @return alt - image's description
	  * @return eventsId - event's identification number
	*/
	
	public Image(int imageId, String name, String url, String alt, int eventsId) {
		this.imageId = imageId;
		this.name = name;
		this.url = url;
		this.alt = alt;
		this.eventsId = eventsId;
	}

	/** 
	  * @description getter for the identification number of the image
	  * @return id - image's identification number
	*/
	
	public int getImageId() {
		return imageId;
	}

	/** 
	  * @description setter for the identification number of the image
	  * @param id - image's identification number
	*/
	
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	/** 
	  * @description getter for the name of the image
	  * @return name - image's name
	*/
	
	public String getName() {
		return name;
	}

	/** 
	  * @description setter for the name of the image
	  * @param name - image's name
	*/
	
	
	public void setName(String name) {
		this.name = name;
	}

	/** 
	  * @description getter for the url of the image
	  * @return url - image's url
	*/
	
	public String getUrl() {
		return url;
	}

	/** 
	  * @description setter for the url of the image
	  * @param url - image's url
	*/
	
	public void setUrl(String url) {
		this.url = url;
	}

	/** 
	  * @description getter for the description of the image
	  * @return alt - image's description
	*/
	
	public String getAlt() {
		return alt;
	}

	/** 
	  * @description setter for the description of the image
	  * @param alt - image's description
	*/
	
	public void setAlt(String alt) {
		this.alt = alt;
	}

	/** 
	  * @description getter for the identification number of the event
	  * @return eventsId - event's identification number
	*/
	
	public int geteventsId() {
		return eventsId;
	}

	/** 
	  * @description setter for the identification number of the event
	  * @param eventsId - event's identification number
	*/
	
	public void setEventsId(int eventsId) {
		this.eventsId = eventsId;
	}
}
