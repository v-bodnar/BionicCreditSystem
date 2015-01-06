package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import mysql.MySqlDaoFactory;
import entity.CreditProgram;

public class DataGenerator {
	private static final String SEP = "/";
	private static final URL FIRST_NAMES_URL = DataGenerator.class.getClassLoader().getResource("files" + SEP + "FirstNames.txt");
	private static final URL MIDDLE_NAMES_URL = DataGenerator.class.getClassLoader().getResource("files" + SEP + "MiddleNames.txt");
	private static final URL LAST_NAMES_URL = DataGenerator.class.getClassLoader().getResource("files" + SEP + "LastNames.txt");
	private static final URL CITIES_URL = DataGenerator.class.getClassLoader().getResource("files" + SEP + "Cities.txt");
	private static final URL STREETS_URL = DataGenerator.class.getClassLoader().getResource("files" + SEP + "Streets.txt");
	private static final List<String> FIRST_NAMES = readFile(FIRST_NAMES_URL);
	private static final List<String> MIDDLE_NAMES = readFile(MIDDLE_NAMES_URL);
	private static final List<String> LAST_NAMES = readFile(LAST_NAMES_URL);
	private static final List<String> CITIES = readFile(CITIES_URL);
	private static final List<String> STREETS = readFile(STREETS_URL);

	public DataGenerator() {
		System.out.println(FIRST_NAMES_URL.getPath());
	}

	public static List<String> readFile(URL fileName) {
		ArrayList<String> result = new ArrayList<>();

		// Открываем файл и считаем данные
		BufferedReader file;
		try {
			file = new BufferedReader(new InputStreamReader(fileName.openStream()));
			Scanner scr = new Scanner(file);
			while (scr.hasNextLine()) {
				result.add(scr.nextLine());
			}

			scr.close();
			file.close(); // Закрываем файл
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public static String getFirstName() {
		Random r = new Random();
		return FIRST_NAMES.get(r.nextInt(FIRST_NAMES.size()));
	}

	public static String getMiddleName() {
		Random r = new Random();
		return MIDDLE_NAMES.get(r.nextInt(MIDDLE_NAMES.size()));
	}

	public static String getLastName() {
		Random r = new Random();
		return LAST_NAMES.get(r.nextInt(LAST_NAMES.size()));
	}

	public static String getCity() {
		Random r = new Random();
		return CITIES.get(r.nextInt(CITIES.size()));
	}

	public static String getStreet() {
		Random r = new Random();
		return STREETS.get(r.nextInt(STREETS.size()));
	}

	public static String getAddress() {
		Random r = new Random();
		return "Украина г. " + getCity() + " " + getStreet() + " "
				+ r.nextInt(100);
	}

	public static String getEmail() {
		Transliterator t = new Transliterator();
		return t.transliterate(getFirstName().charAt(0) + getLastName())
				+ "@gmail.com";
	}

	public static  String getEmail(String firstName, String lastName) {
		Transliterator t = new Transliterator();
		return t.transliterate(firstName.charAt(0) + lastName) + "@gmail.com";
	}

	public static String getUserName() {
		Transliterator t = new Transliterator();
		return t.transliterate(getFirstName().charAt(0) + getLastName());
	}
	public static String getUserName(String firstName, String lastName) {
		Transliterator t = new Transliterator();
		return t.transliterate(firstName.charAt(0) + lastName);
	}

	public static long getInn(Integer id) {
		Random r = new Random();
		String inn = "" + id;
		for (int i = 0; i < 10 - id.toString().length(); i++) {
			inn += r.nextInt(9);
		}
		return Long.parseLong(inn);
	}
	
	public static long getCardNumber(Long inn) {
		Random r = new Random();
		String cardNumber = "" + inn;
		for (int i = 0; i < 6; i++) {
			cardNumber += r.nextInt(9);
		}
		return Long.parseLong(cardNumber);
	}

	public static float getIncome() {
		Random r = new Random();
		float income = 0;
		for (int i = 0; i < 10; i++) {
			income += r.nextInt(9) * 100;
		}
		return income;
	}
	
	public static String formatName(String name){
		String result = null;
		if (name.length()>1)return result = name.substring(0, 1).toUpperCase() + name.substring(1, name.length()).toLowerCase();
		else if(name.length() == 1) return result = name.substring(0, 1).toUpperCase();
		else return result; 
	}
	

	


}
