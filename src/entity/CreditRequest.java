package entity;
/**
* <h1>Credit request!</h1>
* Class encapsulates all data needed for credit request.
* @author  Bodnar Volodymyr
* @version 0.1
* @since   2014.04.15
*/
import java.util.Date;

public class CreditRequest implements EntityInterface{
	
	private static final long serialVersionUID = -2547960180374662418L;
	private Integer id;
	private Integer idCurrency;
	private Integer idCreditProgram;
	private Integer idUser;
	private boolean requestStatus;
	private long cardNumber;
	private float sum;
	private Date dateRequested;
	private Date dateApplied;
	private Integer idManagerApplied;
	
	public CreditRequest(){}
	
	/**
	 * @param id
	 * @param idCurrency
	 * @param idCreditProgram
	 * @param idUser
	 * @param requestStatus 
	 * @param cardNumber if requestStatus is true card number must be set to identify credit account
	 * @param sum sum which one wants to receive
	 * @param dateRequested date when client submitted request
	 * @param dateApplied if requestStatus is true, this specifies date when the request was approved
	 */
	public CreditRequest(Integer id, Integer idCurrency, Integer idCreditProgram,
			Integer idUser, boolean requestStatus, long cardNumber, float sum,
			Date dateRequested, Date dateApplied, Integer idManagerApplied) {
		super();
		this.id = id;
		this.idCurrency = idCurrency;
		this.idCreditProgram = idCreditProgram;
		this.idUser = idUser;
		this.requestStatus = requestStatus;
		this.cardNumber = cardNumber;
		this.sum = sum;
		this.dateRequested = dateRequested;
		this.dateApplied = dateApplied;
		this.idManagerApplied = idManagerApplied;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(int id) { this.id = id;  }
	
	public Integer getIdCurrency() {
		return idCurrency;
	}
	public void setIdCurrency(Integer idCurrency) {
		this.idCurrency = idCurrency;
	}
	public Integer getIdCreditProgram() {
		return idCreditProgram;
	}
	public void setIdCreditProgram(Integer idCreditProgram) {
		this.idCreditProgram = idCreditProgram;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public boolean isRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(boolean requestStatus) {
		this.requestStatus = requestStatus;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public Date getDateRequested() {
		return dateRequested;
	}
	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}
	public Date getDateApplied() {
		return dateApplied;
	}
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}

	public Integer getManagerApplied() {
		return idManagerApplied;
	}

	public void setManagerApplied(Integer managerApplied) {
		this.idManagerApplied = managerApplied;
	}
	
	
}
