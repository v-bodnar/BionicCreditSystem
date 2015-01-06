package mysql;

import dao.AbstractJDBCDao;
import dao.PersistException;
import entity.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
* <h1>MySqlUserDao!</h1>
* Class describes CRUD and basic operations with MySQL Data Base of the entity.User 
* @author  Bodnar Volodymyr
* @version 0.1
*/
public class MySqlUserDao extends AbstractJDBCDao<User>{

	private static final long serialVersionUID = -8718721858380875899L;
	public MySqlUserDao(Connection connection) {
		super(connection);
		//This Object can be called remotely
		try {
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getSelectQuery() {
		return "SELECT id, id_role, id_currency, username, password, first_name, middle_name, last_name, inn, income, address, email, registration_date, enabled FROM users ";
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO users (id_role, id_currency, username, password, first_name, middle_name, last_name, inn, income, address, email, registration_date, enabled ) \n"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE users \n"
				+ "SET id_role = ?, id_currency = ?, username  = ?, password  = ?, first_name = ?, middle_name = ?, last_name = ?, inn = ?, income = ?, address = ?, email = ?, registration_date = ?, enabled = ? \n"
				+ "WHERE id = ?;";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM users WHERE id= ?;";
	}
	
	@Override
	public String getLastIdQuery() {
		return "SHOW TABLE STATUS LIKE 'users';";
	}

	
	
	@Override
	public User create() throws PersistException, RemoteException {
		System.out.println("not implemented");
		return null;
	}



	@Override
	protected List<User> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<User> result = new LinkedList<User>();
		try {
			while (rs.next()) {
				User User = new User();
				User.setId(rs.getInt("id"));
				User.setIdRole(rs.getInt("id_role"));
				User.setIdCurrency(rs.getInt("id_currency"));
				User.setUserName(rs.getString("username"));
				User.setPasswordHash(rs.getString("password"));
				User.setFirstName(rs.getString("first_name"));
				User.setMiddleName(rs.getString("middle_name"));
				User.setLastName(rs.getString("last_name"));
				User.setInn(rs.getLong("inn"));
				User.setIncome(rs.getFloat("income"));
				User.setAddress(rs.getString("address"));
				User.setEmail(rs.getString("email"));
				User.setRegistrationDate(rs.getDate("registration_date"));
				User.setEnabled(rs.getBoolean("enabled"));
				result.add(User);
			}
		} catch (Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			User object) throws PersistException {
		try {
			Date sqlDate = convert(object.getRegistrationDate());
			statement.setInt(1, object.getIdRole());
			statement.setInt(2, object.getIdCurrency());
			statement.setString(3, object.getUserName());
			statement.setString(4, object.getPasswordHash());
			statement.setString(5, object.getFirstName());
			statement.setString(6, object.getMiddleName());
			statement.setString(7, object.getLastName());
			statement.setLong(8, object.getInn());
			statement.setFloat(9, object.getIncome());
			statement.setString(10, object.getAddress());
			statement.setString(11, object.getEmail());
			statement.setDate(12, sqlDate);
			statement.setBoolean(13, object.isEnabled());
			statement.setInt(14, object.getId());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement,
			User object) throws PersistException {
		try {
			Date sqlDate = convert(object.getRegistrationDate());
			statement.setInt(1, object.getIdRole());
			statement.setInt(2, object.getIdCurrency());
			statement.setString(3, object.getUserName());
			statement.setString(4, object.getPasswordHash());
			statement.setString(5, object.getFirstName());
			statement.setString(6, object.getMiddleName());
			statement.setString(7, object.getLastName());
			statement.setLong(8, object.getInn());
			statement.setFloat(9, object.getIncome());
			statement.setString(10, object.getAddress());
			statement.setString(11, object.getEmail());
			statement.setDate(12, sqlDate);
			statement.setBoolean(13, object.isEnabled());
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}
	
	protected java.sql.Date convert(java.util.Date date) {
		if (date == null) {
			return null;
		}
		return new java.sql.Date(date.getTime());
	}
}
