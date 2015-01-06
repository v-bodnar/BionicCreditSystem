package entity;

import java.util.Date;

/**
* <h1>Credit Program!</h1>
* Class describes credit program in the bank.
* @author  Bodnar Volodymyr
* @version 0.1
*/

public class CreditProgram implements EntityInterface{
	
	private static final long serialVersionUID = 4686048659967330842L;
	private Integer id;
	private String title;
	private float yearPercent;
	private Integer period;
	private float minSum;
	private float maxSum;
	private Integer idCurrency;
	private String shortDescription;
	private String fullDescription;
	private Date dateCreated;
	private Integer idUserCreated;
	private Boolean enabled;
	
	public CreditProgram() {}

	/**
	 * @param id <strong>Integer</strong> Unique
	 * @param title <strong>String</strong> Credit program title
	 * @param yearPercent <strong>float</strong> Year Percent for this credit program
	 * @param period <strong>Integer</strong> Time period in which sum + year percents must be payed
	 * @param minSum <strong>float</strong> Minimum sum of the credit that can be given by this program
	 * @param maxSum <strong>float</strong> Maximum sum of the credit that can be given by this program
	 * @param idCurrency <strong>Integer</strong> primary key for Currency entity @see entity.Currency
	 * @param shortDescription <strong>String</strong> 
	 * @param fullDescription <strong>String</strong> Description of the credit program
	 * @param dateCreated <strong>Date</strong> Date/time stamp when credit program was created
	 * @param idUserCreated <strong>Integer</strong> primary key for User entity @see entity.User
	 * @param enabled <strong>Boolean</strong> indicates if credit program is enabled
	 */
	public CreditProgram(Integer id, Integer idCurrency, String title, float yearPercent, Integer period,
			float minSum, float maxSum,
			String shortDescription, String fullDescription, Date dateCreated, Integer idUserCreated, Boolean enabled) {
		super();
		this.id = id;
		this.title = title;
		this.yearPercent = yearPercent;
		this.period = period;
		this.minSum = minSum;
		this.maxSum = maxSum;
		this.idCurrency = idCurrency;
		this.shortDescription = shortDescription;
		this.fullDescription = fullDescription;
		this.dateCreated = dateCreated;
		this.idUserCreated = idUserCreated;
		this.enabled = enabled;
	}

	/**
	 * @return <strong>Integer</strong> unique identification number of this credit program
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets identification number of this credit program
	 * @param id <strong>Integer</strong> identification number of this credit program
	 */
	public void setId(int id) { 
		this.id = id;  
	}

	/**
	 * @return title <strong>String</strong> Credit program title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets credit program title
	 * @param title <strong>String</strong> Credit program title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return title <strong>float</strong> Year Percent for this credit program
	 */
	public float getYearPercent() {
		return yearPercent;
	}

	/**
	 * Sets year percent for this credit program
	 * @param yearPercent <strong>float</strong> Year Percent for this credit program
	 */
	public void setYearPercent(float yearPercent) {
		this.yearPercent = yearPercent;
	}

	/**
	 * @return <strong>Integer</strong> Time period in which sum + year percents must be payed
	 */
	public Integer getPeriod() {
		return period;
	}

	/**
	 * Sets time period in which sum + year percents must be payed
	 * @param <strong>Integer</strong> Time period in which sum + year percents must be payed
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}

	/**
	 * @return minSum <strong>float</strong> Minimum sum of the credit that can be given by this program
	 */
	public float getMinSum() {
		return minSum;
	}

	/**
	 * Sets minimum sum of the credit that can be given by this program
	 * @param minSum <strong>float</strong> Minimum sum of the credit that can be given by this program
	 */
	public void setMinSum(float minSum) {
		this.minSum = minSum;
	}

	/**
	 * @return maxSum <strong>float</strong> Maximum sum of the credit that can be given by this program
	 */
	public float getMaxSum() {
		return maxSum;
	}

	/**
	 * Sets maximum sum of the credit that can be given by this program
	 * @param maxSum <strong>float</strong> Maximum sum of the credit that can be given by this program
	 */
	public void setMaxSum(float maxSum) {
		this.maxSum = maxSum;
	}

	/**
	 * @return idCurrency <strong>Integer</strong> primary key for Currency entity @see entity.Currency
	 */
	public Integer getIdCurrency() {
		return idCurrency;
	}

	/**
	 * Sets primary key for Currency entity @see entity.Currency used for this credit program
	 * @param idCurrency <strong>Integer</strong> primary key for Currency entity @see entity.Currency
	 */
	public void setIdCurrency(Integer idCurrency) {
		this.idCurrency = idCurrency;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * @return fullDescription <strong>String</strong> Description of the credit program
	 */
	public String getFullDescription() {
		return fullDescription;
	}

	/**
	 * Sets description of the credit program
	 * @param fullDescription <strong>String</strong> Description of the credit program
	 */
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	/**
	 * @return dateCreated <strong>Date</strong> Date/time stamp when credit program was created
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * Sets Date/time stamp when credit program was created
	 * @param dateCreated <strong>Date</strong> Date/time stamp when credit program was created
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return idUserCreated <strong>Integer</strong>  Users id that created this credit program, primary key for User entity @see entity.User
	 */
	public Integer getIdUserCreated() {
		return idUserCreated;
	}

	/**
	 * Sets primary key for User that created this program
	 * @param idUserCreated <strong>Integer</strong> primary key for User entity @see entity.User
	 */
	public void setIdUserCreated(Integer idUserCreated) {
		this.idUserCreated = idUserCreated;
	}

	/**
	 * @return enabled <strong>Boolean</strong> indicates if credit program is enabled
	 */
	public Boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets credit program to enabled or disabled
	 * @param enabled <strong>Boolean</strong> indicates if credit program is enabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
