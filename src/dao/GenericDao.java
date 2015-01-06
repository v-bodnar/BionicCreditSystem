package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entity.EntityInterface;
/**
* <h1>GenericDao!</h1>
* Unified interface for management of persistent object state.
* @author  Bodnar Volodymyr
* @version 0.1
* @param <T> persistent object type
*/
public interface GenericDao<T extends EntityInterface> extends Remote {

    /** Creates new record and object represented by it  */
    public T create() throws RemoteException, PersistException;

    /** Creates new record that is represented by object  */
    public T persist(T object)  throws RemoteException, PersistException;

    /** Returns object with primary key equals to key param or null */
    public T getByPK(Integer key) throws RemoteException, PersistException;
    
    /** Returns next primary key */
    public Integer getNextPK() throws RemoteException, PersistException;

    /** Saves state of the object into the Data Base */
    public T update(T object) throws RemoteException, PersistException;

    /** Deletes record that represents object from Data Base */
    public void delete(T object) throws RemoteException, PersistException;

    /** Returns the list of all objects of type T from Data Base*/
    public List<T> getAll() throws RemoteException, PersistException;
    
    /** Returns object of type T with searched needle from Data Base  */
    public T getEntity(String table, String column, String needle) throws RemoteException, PersistException;
}
