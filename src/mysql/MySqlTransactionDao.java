package mysql;

import dao.AbstractJDBCDao;
import dao.PersistException;
import entity.Transaction;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


/**
* <h1>MySqlTransactionDao!</h1>
* Class describes CRUD and basic operations with MySQL Data Base of the entity.Transaction 
* @author  Bodnar Volodymyr
* @version 0.1
*/
public class MySqlTransactionDao extends AbstractJDBCDao<Transaction> {
	private static final long serialVersionUID = 1476441231896164610L;
	public MySqlTransactionDao(Connection connection) {
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
        return "SELECT id, id_currency, id_account_recipient, id_user_recipient, id_account_sender, id_user_sender, sum, date, description FROM transactions ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO transactions (id_currency, id_account_recipient, id_user_recipient, id_account_sender, id_user_sender, sum, date, description) \n" +
                "VALUES (?, ?, ?, ? ,? ,? ,? ,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE transactions \n" +
                "SET id_currency = ?, id_account_recipient  = ?, id_user_recipient = ?, id_account_sender = ?, id_user_sender = ?, sum = ?, date = ?, description = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM transactions WHERE id= ?;";
    }
    
	@Override
	public String getLastIdQuery() {
		return "SHOW TABLE STATUS LIKE 'transactions';";
	}

    @Override
    public Transaction create() throws PersistException {
    	throw new PersistException("Role random creation is not implemented");
    }



    @Override
    protected List<Transaction> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Transaction> result = new LinkedList<Transaction>();
        try {
            while (rs.next()) {
                Transaction Transaction = new Transaction();
                Transaction.setId(rs.getInt("id"));
                Transaction.setIdCurrency(rs.getInt("id"));
                Transaction.setIdAccountRecipient(rs.getInt("title"));
                Transaction.setIdUserRecipient(rs.getInt("min_sum"));
                Transaction.setIdAccountSender(rs.getInt("max_sum"));
                Transaction.setIdUserSender(rs.getInt("period"));
                Transaction.setSum(rs.getFloat("year_percent"));
                Transaction.setDate(rs.getDate("full_description"));
                Transaction.setDescription(rs.getString("short_description"));
                result.add(Transaction);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Transaction object) throws PersistException {
        try {
        	Date sqlDate = convert(object.getDate());
            statement.setInt(1, object.getIdCurrency());
            statement.setInt(2, object.getIdAccountRecipient());
            statement.setInt(3, object.getIdUserRecipient());
            statement.setInt(4, object.getIdAccountSender());
            statement.setInt(5, object.getIdUserSender());
            statement.setFloat(6, object.getSum());
            statement.setDate(7, sqlDate);
            statement.setString(8, object.getDescription());
            statement.setInt(9, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Transaction object) throws PersistException {
        try {
        	Date sqlDate = convert(object.getDate());
            statement.setInt(1, object.getIdCurrency());
            statement.setInt(2, object.getIdAccountRecipient());
            statement.setInt(3, object.getIdUserRecipient());
            statement.setInt(4, object.getIdAccountSender());
            statement.setInt(5, object.getIdUserSender());
            statement.setFloat(6, object.getSum());
            statement.setDate(7, sqlDate);
            statement.setString(8, object.getDescription());
            
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
