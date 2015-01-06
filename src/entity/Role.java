package entity;

/**
* <h1>Role!</h1>
* Class describes user role in the system.
* @author  Bodnar Volodymyr
* @version 0.1
* @since   2014.04.17
*/
public class Role implements EntityInterface {
	private static final long serialVersionUID = -4490032012384452365L;
	private Integer id;
	private String title;
	private String shortTitle;
		
	
	public Role() {}
	/**
	 * @param id
	 * @param title
	 * @param shortTitle
	 */
	public Role(Integer id, String title, String shortTitle) {
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
