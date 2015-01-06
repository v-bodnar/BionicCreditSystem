package mysql;

import dao.AbstractJDBCDao;
import dao.PersistException;
import entity.Credits;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
* <h1>MySqlCreditsDao!</h1>
* Class describes CRUD and basic operations with MySQL Data Base of the entity.Credits 
* @author  Bodnar Volodymyr
* @version 0.1
*/
public class MySqlCreditsDao extends AbstractJDBCDao<Credits> {

	private static final long serialVersionUID = 8675136180681487467L;

	public MySqlCreditsDao(Connection connection) {
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
        return "SELECT id, id_user, id_account, id_credit_request, id_credit_program FROM credits ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO credits (id_user, id_account, id_credit_request, id_credit_program) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE credits \n" +
                "SET id_user = ?, id_account  = ?, id_credit_request = ?, id_credit_program = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM credits WHERE id= ?;";
    }
    
	@Override
	public String getLastIdQuery() {
		return "SHOW TABLE STATUS LIKE 'credits';";
	}

    @Override
    public Credits create() throws PersistException {
    	throw new PersistException("Credits random creation is not implemented");
    }



    @Override
    protected List<Credits> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Credits> result = new LinkedList<Credits>();
        try {
            while (rs.next()) {
                Credits Credits = new Credits();
                Credits.setId(rs.getInt("id"));
                Credits.setIdUser(rs.getInt("id_user"));
                Credits.setIdAccount(rs.getInt("id_account"));
                Credits.setIdCreditRequest(rs.getInt("id_credit_request"));
                Credits.setIdCreditProgram(rs.getInt("id_credit_program"));
                result.add(Credits);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Credits object) throws PersistException {
        try {
            statement.setInt(1, object.getIdUser());
            statement.setInt(2, object.getIdAccount());
            statement.setInt(3, object.getIdCreditRequest());
            statement.setInt(4, object.getIdCreditProgram());
            statement.setInt(5, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Credits object) throws PersistException {
        try {
            statement.setInt(1, object.getIdUser());
            statement.setInt(2, object.getIdAccount());
            statement.setInt(3, object.getIdCreditRequest());
            statement.setInt(4, object.getIdCreditProgram());
            
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
