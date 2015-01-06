package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.EntityInterface;

/**
 * <h1>AbstractJDBCDao!</h1>
 * Abstract class that specifies base CRUD operations with the use of JDBC.
 * @author  Bodnar Volodymyr
 * @version 0.1
 * @param <T> type of persistence object
 */
public abstract class AbstractJDBCDao<T extends EntityInterface>  implements GenericDao<T>, Serializable   {
	private static Logger log = Logger.getLogger(AbstractJDBCDao.class.getName());
	public static final long serialVersionUID = 4L;
    private Connection connection;

    /**
     * Returns sql statement for getting all records
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Returns sql statement for inserting a new record to Data Base
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Returns sql statement for updating Data Base record
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Returns sql statement for deleting Data Base record
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();
    
    /**
     * Returns sql statement for retrieving Primary Key DataBase.
     * <p/>
     * SEECT id FROM [Table] ORDER BY id DESC LIMIT 1;
     */
    public abstract String getLastIdQuery();
    
    

    /**
     * Returns List of objects that RusultSet contains
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;

    /**
     * Inserts arguments to the insert statement, depending on the entity fields values
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * Inserts arguments to the update statement, depending on the entity fields values
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;

	/**
	 * Inserts record to Data Base, depending on the entity fields values
	 * @param object <strong><T></strong> object that represents entity, for example @see entity.CreditProgram
	 * @return object <strong><T></strong> last inserted record represented like entity, for example @see entity.CreditProgram
	 */
    @Override
    public T persist(T object) throws PersistException {
        T persistInstance;
        // Inserting record
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        // Retrieving inserted record
        sql = getSelectQuery() + " WHERE id = last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return persistInstance;
    }
	/**
	 * Searches record in the Data Base with primary key equal to given by param
	 * @param key <strong>Integer</strong> primary key to search for
	 * @return <strong><T></strong> record represented like entity with searched primary key
	 */
    @Override
    public T getByPK(Integer key) throws PersistException {
    	log.setLevel(Level.OFF);
    	log.info("Starting getByPK");
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null || list.size() == 0) {
            throw new PersistException("Record with PK = " + key + " not found.");
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        log.info("Finished getByPK");
        return list.iterator().next();
    }
    
	/**
	 * Updates record in Data Base, depending on the entity fields values
	 * @param object <strong><T></strong> object that represents entity, for example @see entity.CreditProgram
	 * @return object <strong><T></strong> updated record represented like entity, for example @see entity.CreditProgram
	 */
    @Override
    public T update(T object) throws PersistException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object); // заполнение аргументов запроса оставим на совесть потомков
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        // Retrieving updated record
        T persistInstance;
        sql = getSelectQuery() + " WHERE id = "+object.getId();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new PersistException("Exception on findByPK can't find updated data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return persistInstance;
    }

	/**
	 * Deletes record from Data Base
	 * @param object <strong><T></strong> object for deletion that represents entity, for example @see entity.CreditProgram
	 */
    @Override
    public void delete(T object) throws PersistException{
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
	/**
	 * Returns all records represented by T entity from Data Base
	 * @return <strong>List<T></strong> list of records represented by T entity, for example @see entity.CreditProgram
	 */
    @Override
    public List<T> getAll() throws PersistException {
    	log.setLevel(Level.OFF);
    	log.info("Starting getByPK");
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        log.info("Finished getByPK");
        return list;
    }

	/**
	 * Returns next primary key from Data Base
	 * @return <strong>Integer</strong> next primary key from Data Base
	 */
    @Override
	public Integer getNextPK() throws PersistException {
        String sql = getLastIdQuery();
        Integer nextPK;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
            	nextPK = (Integer)(rs.getInt("Auto_increment"));
            }else nextPK = (Integer)1;
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return nextPK ;
	}
    
	/**
	 * Searches and returns record with field equals <strong>needle</strong>
	 * @param table <strong>String</strong> table in Data Base where entity is held
	 * @param column <strong>String</strong> column name in which we search the value
	 * @param needle <strong>String</strong> value that we search
	 * @return object <strong><T></strong> entity that fits searched params
	 */
    @Override
	public T getEntity(String table, String column, String needle) throws PersistException {
        String sql = "SELECT * FROM " + table + " WHERE " + column + " = '"+ needle+"'";
        List<T> list;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null || list.size() == 0) {
            throw new PersistException("Record with String " + needle + " in column " + column + " notfound in table "+table);
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        return list.iterator().next();
	}

	public AbstractJDBCDao(Connection connection){
        this.connection = connection;
    }
}
