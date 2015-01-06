package tablemodels;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.GenericDao;
import dao.PersistException;
import entity.CreditProgram;
import rmi.DAOStubFactory;
import userinterface.ErrorFrame;
import userinterface.MainFrame;

public class UserCreditProgramTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 287938898437834795L;
	private static DAOStubFactory daoStubFactory = MainFrame.getDaoStubFactory();
	//private GenericDao<Account> accountDao = daoStubFactory.getAccountDao();
	private GenericDao<CreditProgram> creditProgramDao = daoStubFactory.getCreditProgramDao();
	//private GenericDao<CreditRequest> creditRequestDao = daoStubFactory.getCreditRequestDao();
	//private GenericDao<Credits> creditsDao = daoStubFactory.getCreditsDao();
	//private GenericDao<Currency> currencyDao = daoStubFactory.getCurrencyDao();
	//private GenericDao<Role> roleDao = daoStubFactory.getRoleDao();
	//private GenericDao<Transaction> transactionDao = daoStubFactory.getTransactionDao();
	//private GenericDao<User> userDao = daoStubFactory.getUserDao();

	private String[] colNames = { "Номер", "Название", "Минимальная сумма, грн", "Максимальная сумма, грн", "Срок, мес.", "Процентная ставка"};

	@Override
	public int getRowCount() {
		int count = 0;
		try {
			ArrayList<CreditProgram> creditPrograms = (ArrayList<CreditProgram>)creditProgramDao.getAll();
			for (CreditProgram program : creditPrograms){
				//if (program.get
			}
		} catch (PersistException | RemoteException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
		return count;
	}

	@Override
	public int getColumnCount() {

		return colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		List <CreditProgram> cp = null;
		try {
			cp = creditProgramDao.getAll();
		} catch (PersistException | RemoteException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
		switch (columnIndex) {
		case 0:
			return cp.get(rowIndex).getId();
		case 1:
			return cp.get(rowIndex).getTitle();
		case 2:
			return cp.get(rowIndex).getMinSum();
		case 3:
			return cp.get(rowIndex).getMaxSum();
		case 4:
			return cp.get(rowIndex).getPeriod();
		case 5:
			return cp.get(rowIndex).getYearPercent();
		
		}
		return null;
		
	}
	


	@Override
	public String getColumnName(int column) {
		return "<html><b>" + colNames[column] + "</b></htnl>";
	}

}
