package mysql;

import dao.AbstractJDBCDao;
import dao.PersistException;
import entity.Currency;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
* <h1>MySqlCurrencyDao!</h1>
* Class describes CRUD and basic operations with MySQL Data Base of the entity.Currency 
* @author  Bodnar Volodymyr
* @version 0.1
*/
public class MySqlCurrencyDao extends AbstractJDBCDao<Currency> {

	private static final long serialVersionUID = -5816679486317118273L;

	public MySqlCurrencyDao(Connection connection) {
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
        return "SELECT id, title, short_title FROM currency ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO currency (title, short_title) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE currency \n" +
                "SET title = ?, short_title  = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM currency WHERE id= ?;";
    }
    
	@Override
	public String getLastIdQuery() {
		return "SHOW TABLE STATUS LIKE 'currency';";
	}

    @Override
    public Currency create() throws PersistException {
    	throw new PersistException("Currency random creation is not implemented");
    }



    @Override
    protected List<Currency> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Currency> result = new LinkedList<Currency>();
        try {
            while (rs.next()) {
                Currency Currency = new Currency();
                Currency.setId(rs.getInt("id"));
                Currency.setTitle(rs.getString("title"));
                Currency.setShortTitle(rs.getString("short_title"));
                result.add(Currency);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Currency object) throws PersistException {
        try {
            statement.setString(1, object.getTitle());
            statement.setString(2, object.getShortTitle());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Currency object) throws PersistException {
        try {
            statement.setString(1, object.getTitle());
            statement.setString(2, object.getShortTitle());
            
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
