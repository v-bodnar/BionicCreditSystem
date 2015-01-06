package entity;

import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
* <h1>User</h1>
* Class describes user profile.
* @author  Bodnar Volodymyr
* @version 0.1
* @since   2014.04.15
*/

public class User implements EntityInterface  {
	 
	private static final long serialVersionUID = -1350186137802064416L;
	private Integer id;
	private Integer idRole;
	private Integer idCurrency;
	private String userName;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String address;
	private String email;
	private long inn;
	private float income;
	private Date registrationDate;
	private Boolean enabled;
	
	
	public User() throws RemoteException{}


	/**
	 * @param id
	 * @param userName
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param inn tax identification code
	 * @param address
	 * @param email
	 * @param role admin/manager/cllient 
	 * @throws RemoteException 
	 */
	public User(Integer id,Integer idRole, Integer idCurrency, String userName, String password, String firstName, String middleName,
			String lastName, long inn,float income, String address, String email, Date registrationDate, Boolean enabled) throws RemoteException {
		super();
		this.id = id;
		this.userName = userName;
		this.password = hashPassword(inn,password);
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.inn = inn;
		this.income = income;
		this.address = address;
		this.email = email;
		this.idRole = idRole;
		this.idCurrency = idCurrency;
		this.registrationDate = registrationDate;
		this.enabled = enabled;
	}


	public Integer getId() {
		return id;
	}

	public void setId(int id) { this.id = id;  }

	public String getUserName() throws RemoteException {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public long getInn() {
		return inn;
	}


	public void setInn(long inn) {
		this.inn = inn;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getIdRole() {
		return idRole;
	}


	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	public float getIncome() {
		return income;
	}


	public void setIncome(float income) {
		this.income = income;
	}


	public Integer getIdCurrency() {
		return idCurrency;
	}


	public void setIdCurrency(Integer idCurrency) {
		this.idCurrency = idCurrency;
	}


	public String getPasswordHash() {
		return password;
	}


	public void setPassword(String password) {    
		this.password = hashPassword(this.getInn(), password);
	}
	public void setPasswordHash(String password) {    
		this.password = password;
	}

    @Override
	public boolean equals(Object obj) {
		return (getInn() == ((User)obj).getInn() && getId().equals(((User)obj).getId()));
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	private String hashPassword(Long inn, String password){
    	final String SALT = "Zaq1xsw2cde3~";
    	String hash = null;
  	  	MessageDigest cript;
			try {
				String pass = SALT+Long.toString(inn)+password;
				cript = MessageDigest.getInstance("SHA-1");
		          cript.reset();
		          cript.update(pass.getBytes());
		          hash = byteArrayToHexString(cript.digest());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return hash;
    }
    
    private static String byteArrayToHexString(byte[] b) {
    	  String result = "";
    	  for (int i=0; i < b.length; i++) {
    	    result +=
    	          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
    	  }
    	  return result;
    	}
	
}
