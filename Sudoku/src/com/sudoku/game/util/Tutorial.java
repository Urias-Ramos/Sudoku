package com.sudoku.game.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.sudoku.game.core.ControlPanel.TYPEKEYCONTROL;
import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.core.graphicsengine.Scenario.Scene;

/**
 * Esta clase es la encargada de manajar los eventos del tutorial.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Tutorial implements UpdateAndPainting {
	private int x, y;
	private Rectangle rectangle;
	
	private int index;
	private String value;
	
	private LinkedList<String> listText;
	
	private Font font;
	
	private boolean showTablero, showRegion, showCell, showPanelOption;
	
	private Point point;
	
	private boolean visible;
	
	/**
	 * Constructor de la clase
	 * 
	 * Aqui se crean e inicializan los objetos.
	 */
	public Tutorial() {
		x = 0;
		y = 0;
		rectangle = new Rectangle(x, y, 100, 64);
		
		index = 0;
		setValue("");
		
		listText = new LinkedList<String>();
		for(int i=0; i<12; i++) {
			listText.add("");
		}
		
		font = new Font("Arial", Font.BOLD, 14);
		
		showTablero = false;
		showRegion = false;
		showCell = false;
		setShowPanelOption(false);
		
		setVisible(false);
	}
	
	/**
	 * Devuelve el valor actual del atributo visible
	 * 
	 * @return devuelve el valor actual del atributo visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Establece el valor del atributo visible
	 * 
	 * @param visible el nuevo valor para el atributo visible.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
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
	 * Devuelve el valor actual del atributo showTablero.
	 * 
	 * @return valor actual del atributo showTablero.
	 */
	public boolean isShowTablero() {
		return showTablero;
	}
	
	/**
	 * Establece un nuevo valor para el atributo showTablero.
	 * 
	 * @param showTablero el nuevo valor para el atributo showTablero.
	 */
	public void setShowTablero(boolean showTablero) {
		this.showTablero = showTablero;
	}
	
	/**
	 * Devuelve el valor actual del atributo showRegion.
	 * 
	 * @return valor actual del atributo showRegion.
	 */
	public boolean isShowRegion() {
		return showRegion;
	}
	
	/**
	 * Establece un nuevo valor para el atributo showRegion
	 * 
	 * @param showRegion el nuevo valor para el atributo showRegion.
	 */
	public void setShowRegion(boolean showRegion) {
		this.showRegion = showRegion;
	}
	
	/**
	 * Devuelve el valor actual del atributo showCell.
	 * 
	 * @return valor actual del atributo showCell.
	 */
	public boolean isShowCell() {
		return showCell;
	}
	
	/**
	 * Establece un nuevo valor para el atributo showCell
	 * 
	 * @param showCell el nuevo valor para el atributo showCell.
	 */
	public void setShowCell(boolean showCell) {
		this.showCell = showCell;
	}
	
	/**
	 * Devuelve el valor actual del atributo showPanelOption.
	 * 
	 * @return valor actual del atributo showPanelOption.
	 */
	public boolean isShowPanelOption() {
		return showPanelOption;
	}
	/**
	 * Establece un nuevo valor para el atributo showPanelOption
	 * 
	 * @param showPanelOption el nuevo valor para el atributo showPanelOption.
	 */
	public void setShowPanelOption(boolean showPanelOption) {
		this.showPanelOption = showPanelOption;
	}
	
	/**
	 * Establece los valores por defecto, cuando se inicia el tutorial.
	 */
	public void startTutorial() {
		index = 0;
		point = null;
		setVisible(false);
		setShowTablero(false);
		setShowRegion(false);
		setShowCell(false);
		setShowPanelOption(false);
		
		for(int i=0; i<9; i++) {
			GameManifest.SCENARIO.getGameScenario().getKeyboard().getKey(i).setCount(9);
			GameManifest.SCENARIO.getGameScenario().getKeyboard().getKey(i).setSelected(false);
		}
		
		int[] table = {0, 0, 0, 0, 0, 0, 0, 0, 0};
		for(int i=0; i<9; i++) {
			for(int c=0; c<9; c++) {
				GameManifest.SCENARIO.getGameScenario().getBoard().getRegion().get(c).setCell(table);
			}
		}
		GameManifest.SCENARIO.getGameScenario().getControlPanel().setTypeKeyControl(TYPEKEYCONTROL.NONE);
		
		GameManifest.SCENARIO.getGameScenario().getBoard().getRegion().get(1).getCellList().get(8).setSelectedTutorial(false);
		GameManifest.SCENARIO.getGameScenario().setShowControlPanel(false);
	}
	
	/**
	 * Establece las coordenadas donde se pintaran los objetos.
	 * 
	 * @param x el nuevo valor de la coordenada x.
	 * @param y el nuevo valor de la coordenada y.
	 */
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void upgrade() {
		x = x - (rectangle.width / 2);
		y = y - (rectangle.height / 2);
		
		if(point != null) {
			x = point.x - ((rectangle.width) / 2);
			y = point.y - (rectangle.height + 10);
		}
		
		listText.set(0, GameManifest.IDIOME.getProperty("7001"));
		listText.set(1, GameManifest.IDIOME.getProperty("7002"));
		listText.set(2, GameManifest.IDIOME.getProperty("7003"));
		listText.set(3, GameManifest.IDIOME.getProperty("7004"));
		listText.set(4, GameManifest.IDIOME.getProperty("7005"));
		listText.set(5, GameManifest.IDIOME.getProperty("7006"));
		listText.set(6, GameManifest.IDIOME.getProperty("7007"));
		listText.set(7, GameManifest.IDIOME.getProperty("7008"));
		listText.set(8, GameManifest.IDIOME.getProperty("7009"));
		listText.set(9, GameManifest.IDIOME.getProperty("7010"));
		listText.set(10, GameManifest.IDIOME.getProperty("7011"));
		listText.set(11, GameManifest.IDIOME.getProperty("7012"));
		
		rectangle.x = x;
		rectangle.y = y;
		
		if((GameManifest.RATON.isClicIzquierdo())) {
			GameManifest.RATON.setClicIzquierdo(false);
			if(index == 10) {
				if(GameManifest.SCENARIO.getGameScenario().getBoard().getRegion().get(1).getCellList().get(8).getValue().contentEquals("5")) {
					index++;
				}
			}
			else {
				index++;
			}
			
			if(index > listText.size() - 1) {
				startTutorial();
				
				GameManifest.SCENARIO.setEscenarioActual(Scene.STARTMENU);
			}
			
			switch(index) {
			case 1:
				GameManifest.GENERATESUDOKU.getTutorialTable();
				setShowTablero(true);
				break;
			case 2:
				setShowRegion(true);
				break;
			case 3:
				setShowCell(true);
				break;
			case 4:
				GameManifest.SCENARIO.getGameScenario().getBoard().fillRegion(0);
				GameManifest.SCENARIO.getGameScenario().getBoard().fillRegion(4);
				GameManifest.SCENARIO.getGameScenario().getBoard().fillRegion(8);
				break;
			case 5:
				GameManifest.SCENARIO.getGameScenario().getBoard().fillRegion(1);
				GameManifest.SCENARIO.getGameScenario().getBoard().fillRegion(2);
				GameManifest.SCENARIO.getGameScenario().getBoard().fillRegion(5);
				GameManifest.SCENARIO.getGameScenario().getBoard().fillRegion(3);
				GameManifest.SCENARIO.getGameScenario().getBoard().fillRegion(6);
				GameManifest.SCENARIO.getGameScenario().getBoard().fillRegion(7);
				break;
			case 6:
				 GameManifest.SCENARIO.getGameScenario().getBoard().getRegion().get(1).getCellList().get(8).setSelectedTutorial(true);
				break;
			case 7:
				setShowPanelOption(true);
				GameManifest.SCENARIO.getGameScenario().setShowControlPanel(true);
				break;
			case 10:
				break;
			}
			
			point = null;
			if(index == 6) {
				point = GameManifest.SCENARIO.getGameScenario().getBoard().getRegion().get(1).getCellList().get(8).getCoordinates();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		if(!listText.get(index).contentEquals("")) {
			Painter.rellenoRectangulo(g, rectangle, Color.decode("#4990e2"));
			Painter.setFont(g, font);
			
			rectangle.width = 0;
			rectangle.height = 20;
			for(String text: listText.get(index).split("\n")) {
				setValue(text);
				
				if(rectangle.width < (Painter.getAnchoString(g, getValue()) + 20)) {
					rectangle.width = Painter.getAnchoString(g, getValue()) + 20;
				}
				
				rectangle.height += Painter.getAltoString(g, getValue()) + 20;
				
				x = (int) rectangle.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
				y = (int) rectangle.getCenterY() + (Painter.getAltoString(g, getValue()) / 4);
				
				Painter.string(g, getValue(), Color.BLACK, x, y);
			}
			
			Painter.setFont(g, font.deriveFont(12));
			setValue(GameManifest.IDIOME.getProperty("7013"));
			
			rectangle.height = rectangle.height + Painter.getAltoString(g, getValue());
			
			x = (int) rectangle.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
			y += (Painter.getAltoString(g, getValue()) + 3);
			
			Painter.string(g, getValue(), Color.WHITE, x, y);
		}
	}
}