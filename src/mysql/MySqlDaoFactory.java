package mysql;

import dao.*;
import entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class MySqlDaoFactory implements DaoFactory<Connection> {

	
	private Preferences root = Preferences.userRoot();
	private final Preferences NODE = root
			.node("/com/bionic/credit_program/server");
	private final String DB_PROTOCOL = NODE.get("DB_PROTOCOL", "jdbc:mysql://");
	private final String DB_HOST = NODE.get("DB_HOST", "89.252.29.137");
	private final String DB_PORT = NODE.get("DB_PORT", "3306");
	private final String DB_NAME = NODE.get("DB_NAME", "credit");
	private final String DB_USER = NODE.get("DB_USER", "bodnar");
	private final String DB_PASSWORD = NODE.get("DB_PASSWORD", "aq1sw2de3");
	private final String DRIVER = "com.mysql.jdbc.Driver";//Имя драйвера
	private final String URL = DB_PROTOCOL+DB_HOST+":"+DB_PORT+"/"+DB_NAME+"?useUnicode=true&characterEncoding=utf-8";//URL адрес
	
	@SuppressWarnings("rawtypes")
    private Map<Class, DaoCreator> creators;

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return  connection;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    @SuppressWarnings({"rawtypes"})
    public MySqlDaoFactory() {
        try {
            Class.forName(DRIVER);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Account.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlAccountDao(connection);
            }
        });
        creators.put(CreditProgram.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlCreditProgramDao(connection);
            }
        });
        creators.put(CreditRequest.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlCreditRequestDao(connection);
            }
        });
        creators.put(Credits.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlCreditsDao(connection);
            }
        });
        creators.put(Role.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlRoleDao(connection);
            }
        });
        creators.put(Transaction.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlTransactionDao(connection);
            }
        });
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
					return new MySqlUserDao(connection);
            }
        });
    }
}