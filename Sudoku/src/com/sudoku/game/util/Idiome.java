package com.sudoku.game.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Esta clase se encarga de manipular el idioma del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Idiome {
	private ResourceBundle bundle;

	/**
	 * Constructor de la clase.
	 */
	public Idiome() {
		changeLanguage(GameManifest.PROFILE.getLanguageIndex());
	}
	
	/**
	 * Cambia el idioma de la aplicación.
	 * 
	 * @param ID indice del idioma a modificar.
	 */
	public void changeLanguage(int ID) {
		switch(ID) {
		case 0:
			bundle = ResourceBundle.getBundle("idiome/Spanish", Locale.getDefault());
			break;
		case 1:
			bundle = ResourceBundle.getBundle("idiome/English", Locale.getDefault());
			break;
		case 2:
			bundle = ResourceBundle.getBundle("idiome/Portugues", Locale.getDefault());
			break;
		}
	}
	
	/**
	 * Devuelve el texto en el idioma estaclecido.
	 * 
	 * @param key almacena el id del texto a mostrar
	 * @return texto dado por key.
	 */
	public String getProperty(String key) {
		return bundle.getString(key);
	}
}