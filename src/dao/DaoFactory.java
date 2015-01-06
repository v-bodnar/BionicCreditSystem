package dao;

/**
* <h1>DaoFactory!</h1>
* Object fabric for working with DB
* @author  Bodnar Volodymyr
* @version 0.1
*/

public interface DaoFactory<Context> {

	@SuppressWarnings({"rawtypes"})
    public interface DaoCreator<Context> {
        public GenericDao create(Context context);
    }

    /** Returns connection object */
    public Context getContext() throws PersistException;

    @SuppressWarnings({"rawtypes"})
    /** Returns object for persistent object management */
    public GenericDao getDao(Context context, Class dtoClass) throws PersistException;
}
