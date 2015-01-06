package tablemodels;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import dao.GenericDao;
import dao.PersistException;
import entity.Account;
import entity.CreditProgram;
import entity.CreditRequest;
import entity.Credits;
import entity.User;
import rmi.DAOStubFactory;
import service.DataGenerator;
import userinterface.ErrorFrame;
import userinterface.MainFrame;

public class UserCreditRequestTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -3991660442601361412L;
	private static DAOStubFactory daoStubFactory = MainFrame.getDaoStubFactory();
	private GenericDao<Account> accountDao = daoStubFactory.getAccountDao();
	private GenericDao<CreditProgram> creditProgramDao = daoStubFactory.getCreditProgramDao();
	private GenericDao<CreditRequest> creditRequestDao = daoStubFactory.getCreditRequestDao();
	private GenericDao<Credits> creditsDao = daoStubFactory.getCreditsDao();
	//private GenericDao<Currency> currencyDao = daoStubFactory.getCurrencyDao();
	//private GenericDao<Role> roleDao = daoStubFactory.getRoleDao();
	//private GenericDao<Transaction> transactionDao = daoStubFactory.getTransactionDao();
	private GenericDao<User> userDao = daoStubFactory.getUserDao();
	private User currentUser;
	private List<List<Object>> requestList;
	private String[] colNames = { "Номер", "Программа",
			"Сумма", "Дата запроса", "Подтвердил", "Дата подтв.", "Статус","Действие" };

	public UserCreditRequestTableModel (User currentUser) {
		this.currentUser = currentUser;
		formDataArray();
	}

	@Override
	public int getRowCount() {
		return requestList.size();
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 1) return "<html><a href=''>" + requestList.get(rowIndex).get(columnIndex)+ "</a><html>";
		//if (columnIndex == 3) return "<html><a href=''>" + requestList.get(rowIndex).get(columnIndex)+ "</a><html>";
		return requestList.get(rowIndex).get(columnIndex);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
		if (requestList.get(0).get(column) == null) return "".getClass();
		return requestList.get(0).get(column).getClass();
	}

	@Override
	public String getColumnName(int column) {
		return "<html><b>" + colNames[column] + "</b></html>";
	}


	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		super.addTableModelListener(arg0);
	}

	public void formDataArray() {
		try {
			requestList = new ArrayList<List<Object>>();
			List<CreditRequest> creditRequests = creditRequestDao.getAll();
			for (CreditRequest request : creditRequests) {
				Integer idUser = request.getIdUser();
				if(userDao.getByPK(idUser).equals(currentUser)){
					Integer idCreditProgram = request.getIdCreditProgram();
					String programTitle = creditProgramDao.getByPK(idCreditProgram)
							.getTitle();
					String managerApplied;
					if (request.getManagerApplied() == 0)
						managerApplied = " - ";
					else
						managerApplied = userDao.getByPK(
								request.getManagerApplied()).getFirstName()
								+ " "
								+ userDao.getByPK(request.getManagerApplied())
										.getLastName();
	
					List<Object> requestParam = new ArrayList<Object>();
					requestParam.add(0, request.getId());
					requestParam.add(1, programTitle);
					requestParam.add(2, request.getSum());
					requestParam.add(3, request.getDateRequested());
					requestParam.add(4, managerApplied);
					requestParam.add(5, request.getDateApplied());
					requestParam.add(6, request.isRequestStatus());
					if(request.getDateApplied() == null)
					requestParam.add(7, "<html><a href = ''>Удалить</a><html>");
					else requestParam.add(7, "");
					requestList.add(requestParam);
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
			
			Iterator<List<Object>> iter = requestList.iterator();
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
			String stack = ((String) str.get(2)).toLowerCase();

			if (stack.contains(token.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public void hideConfirmed(Boolean confirmed) {
		
		if(confirmed){
			Iterator<List<Object>> iter = requestList.iterator();
			while (iter.hasNext()) {
				List<Object> request = iter.next();
			    if (request.get(5) != null) {
			        iter.remove();
			    }
			}
			this.fireTableDataChanged();
		}else{
			formDataArray();
			this.fireTableDataChanged();
		}
	}

}
