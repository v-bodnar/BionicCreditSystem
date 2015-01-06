package entity;

/**
* <h1>Credits!</h1>
* Class describes approved credit requests with all data about user, request, dates, credit program
* @author  Bodnar Volodymyr
* @version 0.1
* @since   2014.04.15
*/

public class Credits implements EntityInterface{

	private static final long serialVersionUID = 469913120474717986L;
	private Integer id;
	private Integer idCreditRequest;
	private Integer idCreditProgram;
	private Integer idAccount;
	private Integer idUser;

	public Credits() {};
	
	/**
	 * @param id
	 * @param idCreditRequest
	 * @param idCreditProgram
	 * @param idAccount
	 * @param idUser
	 */
	public Credits(Integer id, Integer idCreditRequest, Integer idCreditProgram,
			Integer idAccount, Integer idUser) {
		super();
		this.id = id;
		this.idCreditRequest = idCreditRequest;
		this.idCreditProgram = idCreditProgram;
		this.idAccount = idAccount;
		this.idUser = idUser;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(int id) { this.id = id;  }
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCreditRequest() {
		return idCreditRequest;
	}
	public void setIdCreditRequest(Integer idCreditRequest) {
		this.idCreditRequest = idCreditRequest;
	}
	public Integer getIdCreditProgram() {
		return idCreditProgram;
	}
	public void setIdCreditProgram(Integer idCreditProgram) {
		this.idCreditProgram = idCreditProgram;
	}
	public Integer getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
}
