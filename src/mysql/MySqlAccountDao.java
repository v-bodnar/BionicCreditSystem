package mysql;
/**
* <h1>MySqlAccountDao!</h1>
* Class describes CRUD and basic operations with MySQL Data Base of the entity.Account 
* @author  Bodnar Volodymyr
* @version 0.1
*/
import dao.AbstractJDBCDao;
import dao.PersistException;
import entity.Account;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import userinterface.ErrorFrame;

public class MySqlAccountDao extends AbstractJDBCDao<Account>{
	
	private static final long serialVersionUID = -932696434737956582L;
	
	/**
	* @param connection <strong>Connection</strong> to DB
	*/
	public MySqlAccountDao(Connection connection) {
        super(connection);
        //This Object can be called remotely
		try {
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
    }
    
    @Override
    public String getSelectQuery() {
        return "SELECT id, id_user, id_currency, card_number, min_balance, balance FROM accounts";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO accounts (id_user, id_currency, card_number, min_balance, balance) \n" +
                "VALUES (?, ?, ? ,? ,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE accounts SET id_user= ?, id_currency = ?, card_number = ?, min_balance = ?, balance = ? WHERE id= ?;";
    }
    
    @Override
    public String getDeleteQuery() {
        return "DELETE FROM accounts WHERE id= ?;";
    }
	@Override
	public String getLastIdQuery() {
		return "SHOW TABLE STATUS LIKE 'accounts';";
	}

    @Override
    public Account create() throws PersistException {
    	throw new PersistException("Account random creation is not implemented");
    }



    @Override
    protected List<Account> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Account> result = new LinkedList<Account>();
        try {
            while (rs.next()) {
                Account Account = new Account();
                Account.setId(rs.getInt("id"));
                Account.setIdUser(rs.getInt("id_user"));
                Account.setIdCurrency(rs.getInt("id_currency"));
                Account.setCardNumber(rs.getLong("card_number"));
                Account.setMinBalance(rs.getFloat("min_balance"));
                Account.setBalance(rs.getFloat("balance"));
                result.add(Account);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Account object) throws PersistException {
        try {
            statement.setInt(1, object.getIdUser());
            statement.setInt(2, object.getIdCurrency());
            statement.setLong(3, object.getCardNumber());
            statement.setFloat(4, object.getMinBalance());
            statement.setFloat(5, object.getBalance());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Account object) throws PersistException {
        try {
            statement.setInt(1, object.getIdUser());
            statement.setInt(2, object.getIdCurrency());
            statement.setLong(3, object.getCardNumber());
            statement.setFloat(4, object.getMinBalance());
            statement.setFloat(5, object.getBalance());
            statement.setFloat(6, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}
