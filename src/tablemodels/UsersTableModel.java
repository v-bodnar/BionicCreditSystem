package tablemodels;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.table.AbstractTableModel;

import dao.GenericDao;
import dao.PersistException;
import entity.Role;
import entity.User;
import rmi.DAOStubFactory;
import userinterface.ErrorFrame;
import userinterface.MainFrame;

public class UsersTableModel extends AbstractTableModel {

	
	private static final long serialVersionUID = -6966079214999543893L;
	private List<List<Object>> userList;
	private static DAOStubFactory daoStubFactory = MainFrame.getDaoStubFactory();
	private static GenericDao<User> userDao = daoStubFactory.getUserDao();
	private static GenericDao<Role> roleDao = daoStubFactory.getRoleDao();
	private String[] colNames = { "Номер", "Фамилия", "Имя", "Отчество","ИНН", "Доход","Роль" };

	public UsersTableModel() {
		formDataArray();
	}

	@Override
	public int getRowCount() {
		return userList.size();
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 1)return "<html><a href=''>" + userList.get(rowIndex).get(columnIndex) + "</a><html>";
		return userList.get(rowIndex).get(columnIndex);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
		return userList.get(0).get(column).getClass();
	}

	@Override
	public String getColumnName(int column) {
		return "<html><b>" + colNames[column] + "</b></html>";
	}
	
	public void formDataArray() {
		try {
			userList = new ArrayList<List<Object>>();
			List<User> usersList = userDao.getAll();
			for (User user : usersList) {
				if (user.isEnabled()){
					List<Object> requestParam = new ArrayList<Object>();
					requestParam.add(0, user.getId());
					requestParam.add(1, user.getLastName());
					requestParam.add(2, user.getFirstName());
					requestParam.add(3, user.getMiddleName());
					requestParam.add(4, user.getInn());
					requestParam.add(5, user.getIncome());
					requestParam.add(6, roleDao.getByPK(user.getIdRole()).getTitle());
					userList.add(requestParam);
				}
			}
		} catch (PersistException | RemoteException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
	}

	
	
	public void search(String keyword) {
		if (!keyword.trim().equals("")) {
			formDataArray();
			
			Iterator<List<Object>> iter = userList.iterator();
			while (iter.hasNext()) {
			    if (!findTokens(iter.next(), keyword)) {
			        iter.remove();
			    }
			}
			
			this.fireTableDataChanged();
		} else {
			formDataArray();
			this.fireTableDataChanged();
		}
	}

	private boolean findTokens(List<Object> str, String keyword) {
		StringTokenizer st = new StringTokenizer(keyword.trim());
		while (st.hasMoreTokens()) {	
			String token = st.nextToken();
			String stack = ((String) str.get(1)).toLowerCase();
			String stack1 = ((String) str.get(2)).toLowerCase();
			String stack2 = ((String) str.get(3)).toLowerCase();
			String stack3 = (Long.toString((Long)str.get(4))).toLowerCase();

			if (stack.contains(token.toLowerCase())
					|| stack1.contains(token.toLowerCase())
					|| stack2.contains(token.toLowerCase())
					|| stack3.contains(token.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

}
