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

public class CreditProgramTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 260642704695392358L;
	private static DAOStubFactory daoStubFactory = MainFrame.getDaoStubFactory();
	private GenericDao<CreditProgram> creditProgramDao = daoStubFactory.getCreditProgramDao();
	private List<List<Object>> creditProgramList;
	private String[] colNames = { "Номер", "Название", "Минимальная сумма, грн", "Максимальная сумма, грн", "Срок, мес.", "Процентная ставка"};

	public CreditProgramTableModel() {
		formDataArray();
	}
	
	@Override
	public int getRowCount() {
		return creditProgramList.size();
	}

	@Override
	public int getColumnCount() {

		return colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (creditProgramList.get(0).get(columnIndex) == null) return "".getClass();
		return creditProgramList.get(rowIndex).get(columnIndex);		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
		return creditProgramList.get(0).get(column).getClass();
	}

	@Override
	public String getColumnName(int column) {
		return "<html><b>" + colNames[column] + "</b></htnl>";
	}
	
	public void formDataArray() {
		creditProgramList = new ArrayList<List<Object>>();
		try {
			List<CreditProgram> creditProgramsList = creditProgramDao.getAll();
			for (CreditProgram creditProgram : creditProgramsList) {
				if(creditProgram.isEnabled()){
					List<Object> creditProgramParam = new ArrayList<Object>();
					creditProgramParam.add(0, creditProgram.getId());
					creditProgramParam.add(1, creditProgram.getTitle());
					creditProgramParam.add(2, creditProgram.getMinSum());
					creditProgramParam.add(3, creditProgram.getMaxSum());
					creditProgramParam.add(4, creditProgram.getPeriod());
					creditProgramParam.add(5, creditProgram.getYearPercent());
					creditProgramList.add(creditProgramParam);
				}
			}
		} catch (PersistException | RemoteException e) {
			e.printStackTrace();
			new ErrorFrame(e);
		}
	}

}
