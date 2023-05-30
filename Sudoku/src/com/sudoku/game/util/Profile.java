package com.sudoku.game.util;

import java.io.Serializable;

/**
 * Esta clase es la encargada de almacenar los datos de la aplicacion, 
 * como la configuracion, idioma y tema del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Profile implements Serializable {
	private boolean darkMode;
	private boolean soundEffect;
	private boolean counter;
	private int errorLimit;
	private boolean usedNumbers;
	private boolean highlightAreas;
	private boolean highlightIdenticalNumbers;
	private boolean smartNotes;
	
	private int languageIndex;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Contructor de la clase
	 * 
	 * Aqui se inician los atributos de configuracion de la aplicacion.
	 */
	public Profile() {
		setDarkMode(false);
		setSoundEffect(true);
		setCounter(true);
		setErrorLimit(0);
		setUsedNumbers(true);
		setHighlightAreas(true);
		setHighlightIdenticalNumbers(true);
		setSmartNotes(true);
		setLanguageIndex(0);
	}

	public boolean isDarkMode() {
		return darkMode;
	}

	public void setDarkMode(boolean darkMode) {
		this.darkMode = darkMode;
	}

	public boolean isSoundEffect() {
		return soundEffect;
	}

	public void setSoundEffect(boolean soundEffect) {
		this.soundEffect = soundEffect;
	}

	public boolean isCounter() {
		return counter;
	}

	public void setCounter(boolean counter) {
		this.counter = counter;
	}
	
	public int getErrorLimit() {
		return errorLimit;
	}

	public void setErrorLimit(int errorLimit) {
		this.errorLimit = errorLimit;
	}

	public boolean isUsedNumbers() {
		return usedNumbers;
	}

	public void setUsedNumbers(boolean usedNumbers) {
		this.usedNumbers = usedNumbers;
	}

	public boolean isHighlightAreas() {
		return highlightAreas;
	}

	public void setHighlightAreas(boolean highlightAreas) {
		this.highlightAreas = highlightAreas;
	}

	public boolean isHighlightIdenticalNumbers() {
		return highlightIdenticalNumbers;
	}

	public void setHighlightIdenticalNumbers(boolean highlightIdenticalNumbers) {
		this.highlightIdenticalNumbers = highlightIdenticalNumbers;
	}

	public boolean isSmartNotes() {
		return smartNotes;
	}

	public void setSmartNotes(boolean smartNotes) {
		this.smartNotes = smartNotes;
	}

	public int getLanguageIndex() {
		return languageIndex;
	}

	public void setLanguageIndex(int languageIndex) {
		this.languageIndex = languageIndex;
	}
}