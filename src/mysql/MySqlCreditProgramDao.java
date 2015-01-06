package mysql;

import dao.AbstractJDBCDao;

import dao.PersistException;
import entity.CreditProgram;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import userinterface.ErrorFrame;

/**
* <h1>MySqlCreditProgramDao!</h1>
* Class describes CRUD and basic operations with MySQL Data Base of the entity.CreditProgram 
* @author  Bodnar Volodymyr
* @version 0.1
*/
public class MySqlCreditProgramDao extends AbstractJDBCDao<CreditProgram> {
	
	private static final long serialVersionUID = 3455945655994081594L;

	public MySqlCreditProgramDao(Connection connection) {
        super(connection);
      //This Object can be called remotely
		try {
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			new ErrorFrame(e);
		}
    }

    @Override
    public String getSelectQuery() {
        return "SELECT id, id_currency, title, min_sum, max_sum, period, year_percent, full_description, short_description, date_created, user_created, enabled FROM credit_programs ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO credit_programs (id_currency, title, min_sum, max_sum, period, year_percent, full_description, short_description, date_created, user_created, enabled) \n" +
                "VALUES (?, ?, ?, ? ,? ,? ,? ,?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE credit_programs \n" +
                "SET id_currency = ?, title  = ?, min_sum = ?, max_sum = ?, period = ?, year_percent = ?, full_description = ?, short_description = ?, date_created = ?, user_created = ?, enabled = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM credit_programs WHERE id= ?;";
    }
    
	@Override
	public String getLastIdQuery() {
		return "SHOW TABLE STATUS LIKE 'credit_programs';";
	}

    @Override
    public CreditProgram create() throws PersistException {
        throw new PersistException("Credit Program random creation is not implemented");
    }


    @Override
    protected List<CreditProgram> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<CreditProgram> result = new LinkedList<CreditProgram>();
        try {
            while (rs.next()) {
            	CreditProgram creditProgram = new CreditProgram();
            	creditProgram.setId(rs.getInt("id"));
            	creditProgram.setIdCurrency(rs.getInt("id_currency"));
            	creditProgram.setTitle(rs.getString("title"));
            	creditProgram.setMinSum(rs.getFloat("min_sum"));
            	creditProgram.setMaxSum(rs.getFloat("max_sum"));
            	creditProgram.setPeriod(rs.getInt("period"));
            	creditProgram.setYearPercent(rs.getFloat("year_percent"));
            	creditProgram.setFullDescription(rs.getString("full_description"));
            	creditProgram.setShortDescription(rs.getString("short_description"));
            	creditProgram.setDateCreated(rs.getDate("date_created"));
            	creditProgram.setIdUserCreated(rs.getInt("user_created"));
            	creditProgram.setEnabled(rs.getBoolean("enabled"));
                result.add(creditProgram);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, CreditProgram object) throws PersistException {
        try {
        	Date dateRequested = convert(object.getDateCreated());
            statement.setInt(1, object.getIdCurrency());
            statement.setString(2, object.getTitle());
            statement.setFloat(3, object.getMinSum());
            statement.setFloat(4, object.getMaxSum());
            statement.setInt(5, object.getPeriod());
            statement.setFloat(6, object.getYearPercent());
            statement.setString(7, object.getFullDescription());
            statement.setString(8, object.getShortDescription());
            statement.setDate(9, dateRequested);
            statement.setInt(10, object.getIdUserCreated());
            statement.setBoolean(11, object.isEnabled());
            statement.setInt(12, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, CreditProgram object) throws PersistException {
        try {
        	Date dateRequested = convert(object.getDateCreated());
            statement.setInt(1, object.getIdCurrency());
            statement.setString(2, object.getTitle());
            statement.setFloat(3, object.getMinSum());
            statement.setFloat(4, object.getMaxSum());
            statement.setInt(5, object.getPeriod());
            statement.setFloat(6, object.getYearPercent());
            statement.setString(7, object.getFullDescription());
            statement.setString(8, object.getShortDescription());
            statement.setDate(9, dateRequested);
            statement.setInt(10, object.getIdUserCreated());
            statement.setBoolean(11, object.isEnabled());
            
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
