package tablemodels;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;

import dao.GenericDao;
import dao.PersistException;
import entity.CreditProgram;
import entity.CreditRequest;
import entity.Credits;
import entity.User;
import rmi.DAOStubFactory;
import userinterface.ErrorFrame;
import userinterface.MainFrame;

public class CreditsTableModel extends AbstractTableModel {

	private static Logger log = Logger.getLogger(CreditsTableModel.class.getName());
	
	private static final long serialVersionUID = 3055099154218814587L;
	private List<List<Object>> creditsList;
	private User currentUser;
	private String[] colNames = { "Номер", "Название", "Cумма, грн", "Срок, мес.", "Процентная ставка", "Счет"};
	private static DAOStubFactory daoStubFactory = MainFrame.getDaoStubFactory();
	private GenericDao<CreditProgram> creditProgramDao = daoStubFactory.getCreditProgramDao();
	private GenericDao<CreditRequest> creditRequestDao = daoStubFactory.getCreditRequestDao();
	private GenericDao<Credits> creditsDao = daoStubFactory.getCreditsDao();
	//private GenericDao<Role> roleDao = daoStubFactory.getRoleDao();
	private GenericDao<User> userDao = daoStubFactory.getUserDao();

	public CreditsTableModel(User currentUser){
		//log.setLevel(Level.OFF);
		//log.info("Starting model initializing");
		this.currentUser = currentUser;
		//log.info("Starting to form date");
		formDataArray();
	}
	
	@Override
	public int getRowCount() {
			return creditsList.size();
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//log.info("Getting value of cell ("+ rowIndex + ":" + columnIndex + ")");
		if (columnIndex == 1) return "<html><a href=''>" + creditsList.get(rowIndex).get(columnIndex)+ "</a><html>";
		//if (columnIndex == 2) return "<html><a href=''>" + creditsList.get(rowIndex).get(columnIndex)+ "</a><html>";
		return creditsList.get(rowIndex).get(columnIndex);
	}
	
	@Override
	public String getColumnName(int column) {
		return "<html><b>" + colNames[column] + "</b></htnl>";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
		return creditsList.get(0).get(column).getClass();
	}
	
	public void formDataArray() {
		try {
			creditsList = new ArrayList<List<Object>>();
			List<Credits> credits = creditsDao.getAll();
			for (Credits credit : credits) {	
				if(userDao.getByPK(credit.getIdUser()).equals(currentUser)){
					List<Object> creditParam = new ArrayList<Object>();
					creditParam.add(0, credit.getId());
					creditParam.add(1, creditProgramDao.getByPK(credit.getIdCreditProgram()).getTitle());
					creditParam.add(2, creditRequestDao.getByPK(credit.getIdCreditRequest()).getSum());
					creditParam.add(3, creditProgramDao.getByPK(credit.getIdCreditProgram()).getPeriod());
					creditParam.add(4, creditProgramDao.getByPK(credit.getIdCreditProgram()).getYearPercent());
					creditParam.add(5, creditRequestDao.getByPK(credit.getIdCreditRequest()).getCardNumber());
					creditsList.add(creditParam);
				}	
			}
		} catch (PersistException | RemoteException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
	}

}
