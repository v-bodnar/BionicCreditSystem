package entity;
import java.util.Date;

/**
* <h1>Transactions!</h1>
* Class describes each transaction between accounts and third parties partners.
* @author  Bodnar Volodymyr
* @version 0.1
* @since   2014.04.15
*/

public class Transaction implements EntityInterface {

	private static final long serialVersionUID = 2377198877549453365L;
	private Integer id;
	private Integer idUserRecipient;
	private Integer idAccountRecipient;
	private Integer idUserSender;
	private Integer idAccountSender;
	private Integer idCurrency;
	private float sum;
	private Date date;
	private String description;
	
	public Transaction(){}
	
	/**
	 * @param id
	 * @param idUserRecipient
	 * @param idAccountRecipient
	 * @param idUserSender
	 * @param idAccountSender
	 * @param idCurrency
	 * @param sum
	 * @param date
	 * @param description
	 */
	public Transaction(Integer id, Integer idUserRecipient, Integer idAccountRecipient,
			Integer idUserSender, Integer idAccountSender, Integer idCurrency, float sum,
			Date date, String description) {
		super();
		this.id = id;
		this.idUserRecipient = idUserRecipient;
		this.idAccountRecipient = idAccountRecipient;
		this.idUserSender = idUserSender;
		this.idAccountSender = idAccountSender;
		this.idCurrency = idCurrency;
		this.sum = sum;
		this.date = date;
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(int id) { this.id = id;  }
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdUserRecipient() {
		return idUserRecipient;
	}
	public void setIdUserRecipient(Integer idUserRecipient) {
		this.idUserRecipient = idUserRecipient;
	}
	public Integer getIdAccountRecipient() {
		return idAccountRecipient;
	}
	public void setIdAccountRecipient(Integer idAccountRecipient) {
		this.idAccountRecipient = idAccountRecipient;
	}
	public Integer getIdUserSender() {
		return idUserSender;
	}
	public void setIdUserSender(Integer idUserSender) {
		this.idUserSender = idUserSender;
	}
	public Integer getIdAccountSender() {
		return idAccountSender;
	}
	public void setIdAccountSender(Integer idAccountSender) {
		this.idAccountSender = idAccountSender;
	}
	public Integer getIdCurrency() {
		return idCurrency;
	}
	public void setIdCurrency(Integer idCurrency) {
		this.idCurrency = idCurrency;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
