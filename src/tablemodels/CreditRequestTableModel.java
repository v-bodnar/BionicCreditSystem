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

public class CreditRequestTableModel extends AbstractTableModel {
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
	private String[] colNames = { "Номер", "Клиент", "Доход", "Программа",
			"Сумма", "Дата запроса", "Подтвердил", "Дата подтв.", "Статус" };

	public CreditRequestTableModel(User currentUser) {
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
		if (columnIndex == 3) return "<html><a href=''>" + requestList.get(rowIndex).get(columnIndex)+ "</a><html>";
		return requestList.get(rowIndex).get(columnIndex);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
		if (requestList.get(0).get(column) == null) return String.class;
		return requestList.get(0).get(column).getClass();
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Integer key = (Integer) getValueAt(rowIndex, 0);
		if (columnIndex == 8) {
			try {
				CreditRequest creditRequest = creditRequestDao.getByPK(key);
				Long cardNumber = DataGenerator.getCardNumber(userDao.getByPK(creditRequest.getIdUser()).getInn());
				Integer accountId = accountDao.getNextPK();
				Integer creditId = creditsDao.getNextPK();
				int question = confirm();
				
				if (question == 0) {
					creditRequest.setRequestStatus(true);
					creditRequest.setManagerApplied(currentUser.getId());
					creditRequest.setDateApplied(new Date());
					creditRequestDao.update(creditRequest);
					Account account = new Account(accountId, 1, creditRequest.getIdUser(), cardNumber, 0, creditRequest.getSum());
					accountDao.persist(account);
					Credits credit = new Credits(creditId, creditRequest.getId(), creditRequest.getIdCreditProgram(), accountId, creditRequest.getIdUser());
					creditsDao.persist(credit);
					formDataArray();
				} else if (question == 1) {
					creditRequest.setRequestStatus(false);
					creditRequest.setManagerApplied(currentUser.getId());
					creditRequest.setDateApplied(new Date());
					creditRequestDao.update(creditRequest);
					formDataArray();
				}
				
				fireTableRowsUpdated(rowIndex, rowIndex);
			} catch (RemoteException | PersistException e) {
    			e.printStackTrace();
    			new ErrorFrame(e);
			}
		}

	}

	@Override
	public String getColumnName(int column) {
		return "<html><b>" + colNames[column] + "</b></html>";
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (requestList.get(row).get(7) == null)return true;
		return false;
		// return true;
	}

	public int confirm() {
		JFrame frame = new JFrame();
		Object[] options = { "Подтвердить", "Отказать", "Отмена" };
		int n = JOptionPane.showOptionDialog(frame,
				"Подтвердить заявку на кредит или отказать?", "Внимание!",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, // the titles of buttons
				options[0]); // default button title
		//System.out.println(n);
		return n;
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
				Integer idCreditProgram = request.getIdCreditProgram();
				Float userIncome = userDao.getByPK(idUser).getIncome();
				String userFIO = userDao.getByPK(idUser).getFirstName() + " "
						+ userDao.getByPK(idUser).getLastName();
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
				requestParam.add(1, userFIO);
				requestParam.add(2, userIncome);
				requestParam.add(3, programTitle);
				requestParam.add(4, request.getSum());
				requestParam.add(5, request.getDateRequested());
				requestParam.add(6, managerApplied);
				requestParam.add(7, request.getDateApplied());
				requestParam.add(8, request.isRequestStatus());
				requestList.add(requestParam);
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
			String stack = ((String) str.get(1)).toLowerCase();
			String stack1 = ((String) str.get(3)).toLowerCase();
			String stack2 = ((String) str.get(6)).toLowerCase();
			if (stack.contains(token.toLowerCase())
					|| stack1.contains(token.toLowerCase())
					|| stack2.contains(token.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public void hideConfirmed(Boolean confirmed) {
		
		if(confirmed){
			Iterator<List<Object>> iter = requestList.iterator();
			while (iter.hasNext()) {
				List<Object> request =iter.next();
			    if ((Date)request.get(7) != null) {
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
