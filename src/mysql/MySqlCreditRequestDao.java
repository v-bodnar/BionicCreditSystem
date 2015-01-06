package mysql;

import dao.AbstractJDBCDao;
import dao.PersistException;
import entity.CreditRequest;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
* <h1>MySqlCreditRequestDao!</h1>
* Class describes CRUD and basic operations with MySQL Data Base of the entity.CreditRequest 
* @author  Bodnar Volodymyr
* @version 0.1
*/
public class MySqlCreditRequestDao extends AbstractJDBCDao<CreditRequest> {

	private static final long serialVersionUID = 8349366553556898740L;

	public MySqlCreditRequestDao(Connection connection) {
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
        return "SELECT id, id_user, id_credit_program, id_currency, sum, card_number, request_status, date_requested, date_applied, manager_applied FROM credit_requests ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO credit_requests (id_user, id_credit_program, id_currency, sum, card_number, request_status, date_requested, date_applied, manager_applied) \n" +
                "VALUES (?, ?, ?, ? ,? ,? ,? ,?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE credit_requests \n" +
                "SET id_user = ?, id_credit_program  = ?, id_currency = ?, sum = ?, card_number = ?, request_status = ?, date_requested = ?, date_applied = ?, manager_applied = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM credit_requests WHERE id= ?;";
    }
    
	@Override
	public String getLastIdQuery() {
		return "SHOW TABLE STATUS LIKE 'credit_requests';";
	}

    @Override
    public CreditRequest create() throws PersistException {
    	throw new PersistException("Credit Request random creation is not implemented");
    }


    @Override
    protected List<CreditRequest> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<CreditRequest> result = new LinkedList<CreditRequest>();
        try {
            while (rs.next()) {
            	CreditRequest CreditRequest = new CreditRequest();
                CreditRequest.setId(rs.getInt("id"));
                CreditRequest.setIdUser(rs.getInt("id_user"));
                CreditRequest.setIdCreditProgram(rs.getInt("id_credit_program"));
                CreditRequest.setIdCurrency(rs.getInt("id_currency"));
                CreditRequest.setSum(rs.getFloat("sum"));
                CreditRequest.setCardNumber(rs.getLong("card_number"));
                CreditRequest.setRequestStatus(rs.getBoolean("request_status"));
                CreditRequest.setDateRequested(rs.getDate("date_requested"));
                CreditRequest.setDateApplied(rs.getDate("date_applied"));
                CreditRequest.setManagerApplied(rs.getInt("manager_applied"));
                result.add(CreditRequest);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, CreditRequest object) throws PersistException {
        try {
        	Date dateRequested = convert(object.getDateRequested());
        	Date dateApplied = convert(object.getDateApplied());
            statement.setInt(1, object.getIdUser());
            statement.setInt(2, object.getIdCreditProgram());
            statement.setInt(3, object.getIdCurrency());
            statement.setFloat(4, object.getSum());
            statement.setLong(5, object.getCardNumber());
            statement.setBoolean(6, object.isRequestStatus());
            statement.setDate(7, dateRequested);
            statement.setDate(8, dateApplied);
            statement.setInt(9, object.getManagerApplied());
            statement.setInt(10, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, CreditRequest object) throws PersistException {
        try {
        	Date dateRequested = convert(object.getDateRequested());
            statement.setInt(1, object.getIdUser());
            statement.setInt(2, object.getIdCreditProgram());
            statement.setInt(3, object.getIdCurrency());
            statement.setFloat(4, object.getSum());
            statement.setLong(5, object.getCardNumber());
            statement.setBoolean(6, object.isRequestStatus());
            statement.setDate(7, dateRequested);
            statement.setNull(8, java.sql.Types.DATE);
            statement.setNull(9, java.sql.Types.INTEGER);
            
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
