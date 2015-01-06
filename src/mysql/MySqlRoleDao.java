package mysql;

import dao.AbstractJDBCDao;
import dao.PersistException;
import entity.Role;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
* <h1>MySqlRoleDao!</h1>
* Class describes CRUD and basic operations with MySQL Data Base of the entity.Role 
* @author  Bodnar Volodymyr
* @version 0.1
*/
public class MySqlRoleDao extends AbstractJDBCDao<Role> {
	
	private static final long serialVersionUID = 680222007301518675L;

	public MySqlRoleDao(Connection connection) {
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
        return "SELECT id, title FROM roles ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO roles (title) \n" +
                "VALUES ( ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE roles \n" +
                "SET title = ? \n" +
                "WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM roles WHERE id= ?;";
    }
    
	@Override
	public String getLastIdQuery() {
		return "SHOW TABLE STATUS LIKE 'roles';";
	}
	
    @Override
    public Role create() throws PersistException {
    	throw new PersistException("Role random creation is not implemented");
    }



    @Override
    protected List<Role> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Role> result = new LinkedList<Role>();
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setTitle(rs.getString("title"));
                result.add(role);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Role object) throws PersistException {
        try {
            statement.setString(1, object.getTitle());
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Role object) throws PersistException {
        try {
            statement.setString(1, object.getTitle());
            
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
