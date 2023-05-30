package com.sudoku.game.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.core.graphicsengine.Scenario.Scene;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;
import com.sudoku.game.util.Resources;
import com.sudoku.game.util.Spinner;
import com.sudoku.game.util.ToggleButton;

/**
 * 
 * Esta clase representa el menu Ajustes del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class SettingsMenu implements UpdateAndPainting {
	private int x, y;
	private String value;
	
	private Rectangle panelPrincipal;
	
	private Rectangle btnBack;
	private BufferedImage iconBack;
	
	private ToggleButton[] btnSetting;
	
	private Rectangle[] panelGeneralSetting, panelSudokuSetting;
	
	private Rectangle panelLanguage;
	private Rectangle[] btnLenguaje;
	private BufferedImage iconPrevious, iconNext;
	
	private LinkedList<String> lenguajeList;
	
	private ToggleButton[] btnGeneralSetting, btnSudokuSetting;
	
	private int auxY;
	private Font font;
	
	private Spinner spinnerError;
	
	/**
	 * Constructor de la clase
	 * 
	 * Aqui se crean los objetos y se hacen los calculos iniciales de este menu.
	 */
	public SettingsMenu() {
		btnBack = new Rectangle(10, 10, 28, 28);
		iconBack = Resources.getImagen("icon/icon_atras.png");
		
		panelPrincipal = new Rectangle(0, 0, GameManifest.ANCHO_PANTALLA_COMPLETA, GameManifest.ALTO_PANTALLA_COMPLETA);
		
		btnSetting = new ToggleButton[2];
		btnSetting[0] = new ToggleButton(true, Resources.getImagen("icon/icon_setting_general.png"), Resources.getImagen("icon/maskOnButton.png"), Resources.getImagen("icon/maskOffButton.png"), new Dimension(64, 64), new Dimension(28, 28));
		btnSetting[1] = new ToggleButton(false, Resources.getImagen("icon/icon_setting_sudoku.png"), Resources.getImagen("icon/maskOnButton.png"), Resources.getImagen("icon/maskOffButton.png"), new Dimension(64, 64), new Dimension(28, 28));
		
		btnSetting[0].setActivated(true);
		
		panelGeneralSetting = new Rectangle[3];
		for(int i=0; i<panelGeneralSetting.length; i++) {
			panelGeneralSetting[i] = new Rectangle(0, 0, 400, 42);
		}
		
		panelLanguage = new Rectangle(0, 0, 200, 32);
		
		btnLenguaje = new Rectangle[2];
		for(int i=0; i<btnLenguaje.length; i++) {
			btnLenguaje[i] = new Rectangle(0, 0, 32, 32);
		}
		
		iconPrevious = Resources.getImagen("icon/icon_previous.png");
		iconNext = Resources.getImagen("icon/icon_next.png");
		
		lenguajeList = new LinkedList<String>();
		lenguajeList.add("Español");
		lenguajeList.add("English");
		lenguajeList.add("Portugues");
		
		btnGeneralSetting = new ToggleButton[2];
		for(int i=0; i<btnGeneralSetting.length; i++) {
			btnGeneralSetting[i] = new ToggleButton(false, null, Resources.getImagen("icon/icon_on.png"), Resources.getImagen("icon/icon_off.png"), new Dimension(32, 32), new Dimension(24, 24));
		}
		
		panelSudokuSetting = new Rectangle[6];
		for(int i=0; i<panelSudokuSetting.length; i++) {
			panelSudokuSetting[i] = new Rectangle(0, 0, 400, 42);
		}
		
		btnSudokuSetting = new ToggleButton[5];
		for(int i=0; i<btnSudokuSetting.length; i++) {
			btnSudokuSetting[i] = new ToggleButton(false, null, Resources.getImagen("icon/icon_on.png"), Resources.getImagen("icon/icon_off.png"), new Dimension(32, 32), new Dimension(24, 24));
		}
		
		spinnerError = new Spinner(0, 81, 0, new Dimension(64, 32), new Dimension(16, 16));
		
		font = new Font("Arial", Font.BOLD, 42);
	}
	
	/**
	 * Obtiene el valor actual del atributo value.
	 * 
	 * @return el valor actual
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Establece un nuevo valor para el atributo value.
	 * 
	 * @param value el nuevo valor para el atributo value.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Actualiza solo los objetos del menu de ajustes generales.
	 * 
	 * @param auxY margen o espacio que hay entre los objetos.
	 */
	private void upgradeGeneralSetting(int auxY) {
		x = (int) (GameManifest.CENTRO_PANTALLA_COMPLETA.x - (panelGeneralSetting[0].width / 2));
		
		auxY += 82;
		for(Rectangle panel: panelGeneralSetting) {
			panel.x = x;
			panel.y = auxY;
			auxY += panel.height;
		}
		
		panelLanguage.x = (int) (panelGeneralSetting[0].x + panelGeneralSetting[0].width) - (panelLanguage.width);
		panelLanguage.y = (int) panelGeneralSetting[0].getCenterY() - (panelLanguage.height / 2);
		
		btnLenguaje[0].x = panelLanguage.x;
		btnLenguaje[0].y = panelLanguage.y;
		
		btnLenguaje[1].x = (panelLanguage.x + panelLanguage.width) - (btnLenguaje[1].width);
		btnLenguaje[1].y = panelLanguage.y;
		
		x = (int) (panelLanguage.getCenterX() - (btnGeneralSetting[0].getHitBox().width / 2));
		y = (int) panelGeneralSetting[1].getCenterY() - (panelLanguage.height / 2);
		
		btnGeneralSetting[0].setCoordinates(x, y);
		
		x = (int) (panelLanguage.getCenterX() - (btnGeneralSetting[1].getHitBox().width / 2));
		y = (int) panelGeneralSetting[2].getCenterY() - (panelLanguage.height / 2);
		
		btnGeneralSetting[1].setCoordinates(x, y);
		
		btnGeneralSetting[0].upgrade();
		btnGeneralSetting[1].upgrade();
	}
	
	/**
	 * Actualiza solo los objetos del menu sudoku.
	 * 
	 * @param auxY margen o espacio que hay entre los objetos.
	 */
	private void upgradeSudokuSetting(int auxY) {
		x = (int) (GameManifest.CENTRO_PANTALLA_COMPLETA.x - (panelSudokuSetting[0].width / 2));
		
		auxY += 82;
		for(Rectangle panel: panelSudokuSetting) {
			panel.x = x;
			panel.y = auxY;
			auxY += panel.height;
		}
		
		x = (int) (panelLanguage.getCenterX() - (btnSudokuSetting[0].getHitBox().width / 2));
		y = (int) panelSudokuSetting[0].getCenterY() - (btnSudokuSetting[0].getHitBox().height / 2);
		
		btnSudokuSetting[0].setCoordinates(x, y);
		
		y = (int) panelSudokuSetting[1].getCenterY() - (btnSudokuSetting[1].getHitBox().height / 2);
		btnSudokuSetting[1].setCoordinates(x, y);
		
		y = (int) panelSudokuSetting[2].getCenterY() - (btnSudokuSetting[2].getHitBox().height / 2);
		btnSudokuSetting[2].setCoordinates(x, y);
		
		y = (int) panelSudokuSetting[3].getCenterY() - (btnSudokuSetting[3].getHitBox().height / 2);
		btnSudokuSetting[3].setCoordinates(x, y);
		
		y = (int) panelSudokuSetting[4].getCenterY() - (btnSudokuSetting[4].getHitBox().height / 2);
		btnSudokuSetting[4].setCoordinates(x, y);
		
		btnSudokuSetting[0].upgrade();
		btnSudokuSetting[1].upgrade();
		btnSudokuSetting[2].upgrade();
		btnSudokuSetting[3].upgrade();
		btnSudokuSetting[4].upgrade();
	}
	
	/**
	 * Pinta los objetos del panel de lenguaje
	 * 
	 * @param g contexto grafico del motor grafico.
	 */
	private void panelLenguaje(Graphics g) {
		Painter.image(g, iconPrevious, btnLenguaje[0]);
		Painter.image(g, iconNext, btnLenguaje[1]);
		
		setValue(lenguajeList.get(GameManifest.PROFILE.getLanguageIndex()));
		x = (int) panelLanguage.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
		y = (int) panelLanguage.getCenterY() + (Painter.getAltoString(g, getValue()) / 4);
		Painter.string(g, lenguajeList.get(GameManifest.PROFILE.getLanguageIndex()), GameManifest.COLORCELLFOREGROUND, x, y);
		
		setValue(GameManifest.IDIOME.getProperty("2002")+":");
		x = (int) panelGeneralSetting[0].x;
		y = (int) panelGeneralSetting[0].getCenterY() + (Painter.getAltoString(g, getValue()) / 4);
		Painter.string(g, getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
	}
	
	private void PainterSettingLabel(Graphics g, Rectangle panel, String value, String descripcion) {
		setValue(value);
		x = (int) panel.x;
		y = (int) panel.getCenterY() + (Painter.getAltoString(g, getValue()) / 4);
		
		Painter.string(g, getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
		
		if(btnSetting[0].isActivated()) {
			btnGeneralSetting[0].paint(g);
			btnGeneralSetting[1].paint(g);
		}
		else {
			btnSudokuSetting[0].paint(g);
			btnSudokuSetting[1].paint(g);
			btnSudokuSetting[2].paint(g);
			btnSudokuSetting[3].paint(g);
			btnSudokuSetting[4].paint(g);
		}
	}
	
	/**
	 * Cambia el idioma del juego dependiendo de la direccion.
	 * 
	 * @param index indice del idioma seleccionado
	 */
	private void changeIdiome(int index) {
		if(index > 0) {
			if((GameManifest.PROFILE.getLanguageIndex() + 1) < lenguajeList.size()) {
				GameManifest.PROFILE.setLanguageIndex(GameManifest.PROFILE.getLanguageIndex() + 1);
				GameManifest.IDIOME.changeLanguage(GameManifest.PROFILE.getLanguageIndex());
			}
		}
		else if(index < 0) {
			if((GameManifest.PROFILE.getLanguageIndex() - 1) > -1) {
				GameManifest.PROFILE.setLanguageIndex(GameManifest.PROFILE.getLanguageIndex() - 1);
				GameManifest.IDIOME.changeLanguage(GameManifest.PROFILE.getLanguageIndex());
			}
		}
	}
	
	/**
	 * Guarda los archvios de configuracion del juego y cambia el menu actual por el menu inicio.
	 */
	public void back() {
		Resources.guardarProfile();
		GameManifest.SCENARIO.setEscenarioActual(Scene.STARTMENU);
	}
	
	@Override
	public void upgrade() {
		panelPrincipal.width = GameManifest.ANCHO_PANTALLA_COMPLETA;
		panelPrincipal.height = GameManifest.ALTO_PANTALLA_COMPLETA;
		
		btnGeneralSetting[0].setActivated(GameManifest.PROFILE.isDarkMode());
		btnGeneralSetting[1].setActivated(GameManifest.PROFILE.isSoundEffect());
		
		btnSudokuSetting[0].setActivated(GameManifest.PROFILE.isCounter());
		//btnSudokuSetting[0].setActivated(GameManifest.PROFILE.isPunctuation());
		//btnSudokuSetting[0].setActivated(GameManifest.PROFILE.isErrorLimit());
		btnSudokuSetting[1].setActivated(GameManifest.PROFILE.isUsedNumbers());
		btnSudokuSetting[2].setActivated(GameManifest.PROFILE.isHighlightAreas());
		btnSudokuSetting[3].setActivated(GameManifest.PROFILE.isHighlightIdenticalNumbers());
		btnSudokuSetting[4].setActivated(GameManifest.PROFILE.isSmartNotes());
		
		if((GameManifest.RATON.getRectanguloPosicion().intersects(btnBack))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			back();
		}
		
		x = (int) (GameManifest.CENTRO_PANTALLA_COMPLETA.x - (148 / 2));
		y = 180;
		
		auxY = y;
		
		btnSetting[0].setCoordinates(x, y);
		btnSetting[1].setCoordinates(x + 74, y);
		
		if((GameManifest.RATON.getRectanguloPosicion().intersects(btnSetting[0].getHitBox()))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			if(!btnSetting[0].isActivated()) {
				btnSetting[1].setActivated(false);
				btnSetting[0].setActivated(!btnSetting[0].isActivated());
			}
		}
		else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnSetting[1].getHitBox()))&&(GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			
			if(!btnSetting[1].isActivated()) {
				btnSetting[0].setActivated(false);
				btnSetting[1].setActivated(!btnSetting[1].isActivated());
			}
		}
		
		btnSetting[0].upgrade();
		btnSetting[1].upgrade();
		
		upgradeGeneralSetting(auxY);
		upgradeSudokuSetting(auxY);
		
		if(btnSetting[0].isActivated()) {
			if((GameManifest.RATON.getRectanguloPosicion().intersects(btnLenguaje[0]))&&(GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				changeIdiome(-1);
			}
			else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnLenguaje[1]))&&(GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				changeIdiome(1);
			}
			
			if((GameManifest.RATON.getRectanguloPosicion().intersects(btnGeneralSetting[0].getHitBox()))&&(GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				
				btnGeneralSetting[0].setActivated(!btnGeneralSetting[0].isActivated());
				
				GameManifest.PROFILE.setDarkMode(!GameManifest.PROFILE.isDarkMode());
				GameManifest.changeTheme();
			}
			else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnGeneralSetting[1].getHitBox()))&&(GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				
				btnGeneralSetting[1].setActivated(!btnGeneralSetting[1].isActivated());
				GameManifest.PROFILE.setSoundEffect(!GameManifest.PROFILE.isSoundEffect());
			}
		}
		else if(btnSetting[1].isActivated()) {
			if((GameManifest.RATON.getRectanguloPosicion().intersects(btnSudokuSetting[0].getHitBox()))&&(GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				
				btnSudokuSetting[0].setActivated(!btnSudokuSetting[0].isActivated());
				GameManifest.PROFILE.setCounter(!GameManifest.PROFILE.isCounter());
			}
			else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnSudokuSetting[1].getHitBox()))&&(GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				
				btnSudokuSetting[1].setActivated(!btnSudokuSetting[1].isActivated());
				GameManifest.PROFILE.setUsedNumbers(!GameManifest.PROFILE.isUsedNumbers());
			}
			else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnSudokuSetting[2].getHitBox()))&&(GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				
				btnSudokuSetting[2].setActivated(!btnSudokuSetting[2].isActivated());
				GameManifest.PROFILE.setHighlightAreas(!GameManifest.PROFILE.isHighlightAreas());
			}
			else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnSudokuSetting[3].getHitBox()))&&(GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				
				btnSudokuSetting[3].setActivated(!btnSudokuSetting[3].isActivated());
				GameManifest.PROFILE.setHighlightIdenticalNumbers(!GameManifest.PROFILE.isHighlightIdenticalNumbers());
			}
			else if((GameManifest.RATON.getRectanguloPosicion().intersects(btnSudokuSetting[4].getHitBox()))&&(GameManifest.RATON.isClicIzquierdo())) {
				GameManifest.RATON.setClicIzquierdo(false);
				
				btnSudokuSetting[4].setActivated(!btnSudokuSetting[4].isActivated());
				GameManifest.PROFILE.setSmartNotes(!GameManifest.PROFILE.isSmartNotes());
			}
		}
		spinnerError.setCoordinates((int) (panelLanguage.getCenterX()) - 50, panelSudokuSetting[5].y);
		spinnerError.upgrade();
		GameManifest.PROFILE.setErrorLimit(spinnerError.getValue());
	}

	@Override
	public void paint(Graphics g) {
		Painter.image(g, iconBack, btnBack);
		
		setValue(GameManifest.IDIOME.getProperty("2001"));
		Painter.setFont(g, font);
		x = (int) panelPrincipal.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
		y = panelPrincipal.y + (100 + Painter.getAltoString(g, getValue()));
		Painter.string(g, getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
		auxY = y;
		
		Painter.setFont(g, font.deriveFont(Font.BOLD, 14));
		
		btnSetting[0].paint(g);
		btnSetting[1].paint(g);
		
		if(btnSetting[0].isActivated()) {
			panelLenguaje(g);
			PainterSettingLabel(g, panelGeneralSetting[1], GameManifest.IDIOME.getProperty("2003")+":", "");
			PainterSettingLabel(g, panelGeneralSetting[2], GameManifest.IDIOME.getProperty("2004")+":", "");
		}
		else if(btnSetting[1].isActivated()) {
			PainterSettingLabel(g, panelSudokuSetting[0], GameManifest.IDIOME.getProperty("2005")+":", "");
			PainterSettingLabel(g, panelSudokuSetting[1], GameManifest.IDIOME.getProperty("2006")+":", "");
			PainterSettingLabel(g, panelSudokuSetting[2], GameManifest.IDIOME.getProperty("2007")+":", "");
			PainterSettingLabel(g, panelSudokuSetting[3], GameManifest.IDIOME.getProperty("2008")+":", "");
			PainterSettingLabel(g, panelSudokuSetting[4], GameManifest.IDIOME.getProperty("2009")+":", "");
			PainterSettingLabel(g, panelSudokuSetting[5], GameManifest.IDIOME.getProperty("2010")+":", "");
			
			spinnerError.paint(g);
		}
	}
}