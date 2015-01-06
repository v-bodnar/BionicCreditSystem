package service;

import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rmi.DAOStubFactory;
import userinterface.ErrorFrame;
import userinterface.MainFrame;
import dao.GenericDao;
import dao.PersistException;
import entity.User;

public class FieldChecker {

	private static DAOStubFactory daoStubFactory = MainFrame.getDaoStubFactory();
	private static GenericDao<User> userDao = daoStubFactory.getUserDao();
	public static String checkPassword(String password) {
		String note = "";
		Pattern numbers = Pattern.compile("[0-9]");
		Pattern letters = Pattern.compile("[A-Za-z]");
		Matcher numbersM = numbers.matcher(password);
		Matcher lettersM = letters.matcher(password);

		if (password.trim().length() == 0)
			note = "<html><div color = 'red'>Поле пароля обязательно для заполнения!</div><html>";
		else if (password.length() < 6)
			note = "<html><div color = 'red'>Пароль должен содержать как минимум 6 символов!</div><html>";
		else if (!numbersM.find())
			note = "<html><div color = 'red'>Пароль должен содержать цифры!</div><html>";
		else if (!lettersM.find())
			note = "<html><div color = 'red'>Пароль должен содержать буквы!</div><html>";
		return note;
	}

	public static String checkUserName(String login) {
		String note = "";
		Pattern words = Pattern.compile("\\W");
		Matcher numbersM = words.matcher(login);

		User user = null;
		try {
			user = userDao.getEntity("users","username", login);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			new ErrorFrame(e);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		if (login.trim().length() == 0)
			note = "<html><div color = 'red'>Поле Логин обязательно для заполнения!</div><html>";
		else if (numbersM.find())
			note = "<html><div color='red'>Логин может содержать только символы латинского алфавита и цифры!</div><html>";
		else if (user != null)
			note = "<html><div color='red'>Пользователь с таким логином уже существует. Введите другой логин!</div><html>";
		return note;
	}

	public static String checkEmail(String email) {
		String note = "";
		final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
				"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher emailM = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

		if (email.trim().length() == 0)
			note = "<html><div color = 'red'>Поле Email обязательно для заполнения!</div><html>";
		else if (!emailM.find())
			note = "<html><div color='red'>Проверьте правильность ввода электронного адреса!</div><html>";
		return note;
	}

	public static String checkNameField(String email) {
		String note = "";
		final Pattern NUMBERS = Pattern.compile("\\d");
		final Pattern WORD = Pattern.compile("[А-Яа-яїъьёыі]",Pattern.CASE_INSENSITIVE);
		final Pattern PUNCT = Pattern.compile("[\\.?,:;\"\\|/<>-_='~`!@#$%^&*(){}]",Pattern.CASE_INSENSITIVE);
		Matcher wordM = WORD.matcher(email);
		Matcher numbersM = NUMBERS.matcher(email);
		Matcher punctM = PUNCT.matcher(email);
		if (email.trim().length() == 0)
			note = "<html><div color = 'red'>Поле обязательно для заполнения!</div><html>";
		else if (!wordM.find())
			note = "<html><div color='red'>Поле может содержать только символы русского алфавита!</div><html>";
		else if (numbersM.find())
			note = "<html><div color='red'>Поле не может содержать цифры!</div><html>";
		else if (punctM.find())
			note = "<html><div color='red'>Поле не может содержать спец символы или знаки пунктуации!</div><html>";
		return note;
	}

	public static String checkInn(String inn) {
		String note = "";
		final Pattern numbers = Pattern.compile("\\d");
		Matcher numbersM = numbers.matcher(inn);
		
		User user = null;
		try {
			user = userDao.getEntity("users",
					"inn", inn);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		
		if (inn.trim().length() == 0)
			note = "<html><div color = 'red'>Поле ИНН обязательно для заполнения!</div><html>";
		else if (inn.trim().length() != 10)
			note = "<html><div color='red'>Корректный идентификационный код содержит 10 цифр!</div><html>";
		else if (!numbersM.find())
			note = "<html><div color='red'>Корректный идентификационный код содержит 10 цифр!</div><html>";
		else if (user != null)
			note = "<html><div color='red'>Уже есть пользователь с таким идентификационнім коддом</div><html>";
		return note;
	}

	public static String checkIfNull(String field) {
		String note = "";
		if (field.trim().length() == 0)
			note = "<html><div color = 'red'>Поле обязательно для заполнения!</div><html>";
		else if (field.trim().length() > 500)
			note = "<html><div color = 'red'>Превышен лимит символов!</div><html>";
		return note;
	}
	public static String checkIncome(String income) {
		String note = "";
		Float inc = new Float(0);
		
		if (income.trim().length() == 0)
			return note = "<html><div color = 'red'>Поле обязательно для заполнения!</div><html>";
		else{
			try {
				inc = Float.parseFloat(income);
			} catch (NumberFormatException e) {
				return note = "<html><div color='red'>Введите сумму ваших доходов за последние пол года.</div><html>";
			}
		}
		
		if (inc < 0 || inc > 1000000) {

			note = "<html><div color='red'>Вы действительно столько зарабатываете? Не надо мне врать!</div><html>";
		}
		return note;
	}
	
	public static String checkSum(String sum) {
		String note = "";
		Float inc = new Float(0);
		
		if (sum.trim().length() == 0)
			return note = "<html><div color = 'red'>Поле Сумма обязательно для заполнения!</div><html>";
		else{
			try {
				inc = Float.parseFloat(sum);
			} catch (NumberFormatException e) {
				return note = "<html><div color='red'>Введите сумму.</div><html>";
			}
		}
		
		if (inc < 0 || inc > 10000000) {

			note = "<html><div color='red'>Вы хотите обанкротить собственный банк?!</div><html>";
		}
		return note;
	}
	
	public static String checkRequestSum(String sum, Float min, Float max) {
		String note = "";
		Float inc = new Float(0);
		
		if (sum.trim().length() == 0)
			return note = "<html><div color = 'red'>Поле Сумма обязательно для заполнения!</div><html>";
		else{
			try {
				inc = Float.parseFloat(sum);
			} catch (NumberFormatException e) {
				return note = "<html><div color='red'>Введите сумму.</div><html>";
			}
		}
		
		if (inc < min || inc > max) return note = "<html><div color='red'>Введите сумму в пределах от " + min + " до " + max + ".</div><html>";
		
		if (inc < 0 || inc > 10000000) {

			note = "<html><div color='red'>Вы хотите обанкротить собственный банк?!</div><html>";
		}
		return note;
	}
	
	public static String checkPeriod(String sum) {
		String note = "";
		Integer inc = new Integer(0);
		
		if (sum.trim().length() == 0)
			return note = "<html><div color = 'red'>Поле Период обязательно для заполнения!</div><html>";
		else{
			try {
				inc = Integer.parseInt(sum);
			} catch (NumberFormatException e) {
				return note = "<html><div color='red'>Введите количество месяцев на которое расчитана кредитная программа.</div><html>";
			}
		}
		
		if (inc < 0 || inc > 600) {

			note = "<html><div color='red'>Вы хотите обанкротить собственный банк?!</div><html>";
		}
		return note;
	}
	
	public static String checkPerсent(String perc) {
		String note = "";
		Integer inc = new Integer(0);
		
		if (perc.trim().length() == 0)
			return note = "<html><div color = 'red'>Поле Процентная ставка обязательно для заполнения!</div><html>";
		else{
			try {
				inc = Integer.parseInt(perc);
			} catch (NumberFormatException e) {
				return note = "<html><div color='red'>Укажите процентную ставку от 0 до 100</div><html>";
			}
		}
		
		if (inc < 0 || inc > 100) {

			note = "<html><div color='red'>Укажите процентную ставку от 0 до 100</div><html>";
		}
		return note;
	}
	
	public static String checkPort(String sum) {
		String note = "";
		Integer inc = new Integer(0);
		
		if (sum.trim().length() == 0)
			return note = "<html><div color = 'red'>Поле обязательно для заполнения!</div><html>";
		else{
			try {
				inc = Integer.parseInt(sum);
			} catch (NumberFormatException e) {
				return note = "<html><div color='red'>Выберите порт в диапазоне 0 - 65535.</div><html>";
			}
		}
		
		if (inc < 0 || inc > 65535) {

			note = "<html><div color='red'>Выберите порт в диапазоне 0 - 65535</div><html>";
		}
		return note;
	}
	public static String checkLatin(String login) {
		String note = "";
		Pattern words = Pattern.compile("\\W");
		Matcher numbersM = words.matcher(login);


		if (login.trim().length() == 0)
			note = "<html><div color = 'red'>Поле Логин обязательно для заполнения!</div><html>";
		else if (numbersM.find())
			note = "<html><div color='red'>Логин может содержать только символы латинского алфавита и цифры!</div><html>";

		return note;
	}

}
