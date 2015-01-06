package entity;

/**
* <h1>Account!</h1>
* Class describes users account in the bank.
* @author  Bodnar Volodymyr
* @version 0.1
*/

public class Account implements EntityInterface{
	
	private static final long serialVersionUID = 1095993390917414380L;
	private Integer id;
	private Integer idCurrency;
	private Integer idUser;
	private long cardNumber;
	private float balance;
	private float minBalance;
	
	public Account(){}
	
	/**
	 * @param id <strong>Integer</strong> unique identification number
	 * @param idCurrency <strong>Integer</strong> primary key for Currency entity @see entity.Currency
	 * @param idUser <strong>Integer</strong> primary key for User entity @see entity.User
	 * @param cardNumber <strong>long</strong>
	 * @param balance <strong>float</strong> current card balance
	 * @param minBalance <strong>float</strong> minimum possible card balance, describes credit sum given. (Default -1000)
	 */
	public Account(Integer id, Integer idCurrency, Integer idUser, long cardNumber,
			float balance, float minBalance) {
		super();
		this.id = id;
		this.idCurrency = idCurrency;
		this.idUser = idUser;
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.minBalance = minBalance;
	}
	
	/**
	 * @return <strong>Integer</strong> unique identification number
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets identification number
	 * @param id <strong>Integer</strong> unique identification number
	 */
	public void setId(int id) { 
		this.id = id;  
	}
	
	/**
	 * @return <strong>Integer</strong> unique identification number of the currency used by this account
	 */
	public Integer getIdCurrency() {
		return idCurrency;
	}

	/**
	 * Sets identification number of the currency used by this account
	 * @param <strong>Integer</strong> unique identification number of the currency used by this account
	 */
	public void setIdCurrency(Integer idCurrency) {
		this.idCurrency = idCurrency;
	}
	
	/**
	 * @return <strong>Integer</strong> unique identification number of the User that use this account 
	 */
	public Integer getIdUser() {
		return idUser;
	}
	/**
	 * Sets identification number of the User that use this account 
	 * @param <strong>Integer</strong> unique identification number of the User that use this account 
	 */
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return <strong>long</strong> unique card number of this account 
	 */
	public long getCardNumber() {
		return cardNumber;
	}

	/**
	 * Sets card number of this account 
	 * @param <strong>long</strong> unique card number of this account 
	 */
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * @return <strong>float</strong> balance of this account
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * Sets balance of this account
	 * @param <strong>float</strong> balance of this account 
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * @return <strong>float</strong> minimal balance of this account
	 */
	public float getMinBalance() {
		return minBalance;
	}

	/**
	 * Sets  minimal balance of this account
	 * @param <strong>float</strong> minimal balance of this account
	 */
	public void setMinBalance(float minBalance) {
		this.minBalance = minBalance;
	}
}
