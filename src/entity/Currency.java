package entity;


/**
* <h1>Currency!</h1>
* Class describes currency entity.
* @author  Bodnar Volodymyr
* @version 0.1
* @since   2014.04.15
*/

public class Currency implements EntityInterface {

	private static final long serialVersionUID = -527125802818498788L;
	private Integer id;
	private String title;
	private String shortTitle;
	
	public Currency(){}
	
	/**
	 * @param id
	 * @param title
	 * @param shortTitle
	 */
	public Currency(Integer id, String title, String shortTitle) {
		super();
		this.id = id;
		this.title = title;
		this.shortTitle = shortTitle;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(int id) { this.id = id;  }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
		
}
