package com.sudoku.game.core.generator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.sudoku.game.core.generator.Dificulty.DificultyMode;
import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.core.graphicsengine.Scenario.Scene;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;
import com.sudoku.game.util.Sound;

/**
 * Esta clase representa el tablero del sudoku. 
 * Es la responsable de actualizar y pintar las regiones.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Board implements UpdateAndPainting {
	private int dimension;
	private int margen;
	
	private int x, y;
	private Rectangle rectangle;
	
	private int auxIndexRegion, indexRegionCellSelected;
	
	private LinkedList<Region> regionList;
	
	private int indexX, indexY;
	private int max;
	
	private boolean visible;
	private boolean regionVisible;
	private boolean cellVisible;
	
	private Dificulty dificulty;
	private Sound soundWinner, soundDefeat;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Aqui se crea una instancia de la clase.
	 */
	public Board() {
		setDimension(500);
		margen = 2;
		
		soundWinner = new Sound("sound/sfx-victory.wav");
		soundDefeat = new Sound("sound/sfx-defeat.wav");
		
		x = 0;
		y = 0;
		
		rectangle = new Rectangle(0, 0, getDimension(), getDimension());
		
		regionList = new LinkedList<Region>();
		initializeRegionList();
		
		dificulty = new Dificulty();
		setDifficulty(DificultyMode.EASY);
		
		visible = true;
		regionVisible = true;
	}
	
	/**
	 * Establece la dificultad del sudoku.
	 * 
	 * @param mode nivel de dicultad
	 */
	public void setDifficulty(DificultyMode mode) {
		dificulty.setDificulty(mode);
	}
	
	/**
	 * Devuelve el Dificultad actual del sudoku.
	 * 
	 * @return Dificultad actual.
	 */
	public Dificulty getDifficulty() {
		return dificulty;
	}
	
	/**
	 * Inicia el sudoku.
	 */
	public void startSudoku() {
		GameManifest.GameEmpty();
		GameManifest.GENERATESUDOKU.iniciar();
		emptyBoard();
		GameManifest.GENERATESUDOKU.establecerDificultad(dificulty);
		restartSudoku();
	}
	
	/**
	 * Se encarga de restablecer el sudoku.
	 */
	public void restartSudoku() {
		GameManifest.GameEmpty();
		emptyBoard();
		
		for(int i=0; i<9; i++) {
			GameManifest.SCENARIO.getGameScenario().getKeyboard().getKey(i).setCount(9);
		}
		
		for(int i=0; i<9; i++) {
			regionList.get(i).setCell(GameManifest.GENERATESUDOKU.getSudokuBoard((i + 1)));
		}
		
		GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().start();
	}
	
	/**
	 * Rellena una region con los datos del sudoku generado.
	 * 
	 * @param region region a rellenar.
	 */
	public void fillRegion(int region) {
		regionList.get(region).setCell(GameManifest.GENERATESUDOKU.getSudokuBoard((region + 1)));
	}
	
	/**
	 * Restablece los valores por defecto de las celdas de cada region.
	 */
	private void emptyBoard() {
		for(Region region: getRegion()) {
			for(Cell cell: region.getCellList()) {
				cell.setEditable(false);
				cell.setCellError(false);
				cell.setValue("");
			}
		}
	}
	
	/**
	 * Devuelve el valor actual del atributo visible.
	 * 
	 * @return valor actual del atributo.
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Establece un nuevo valor para el atributo visible.
	 * 
	 * @param visible nuevo valor para el atributo.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Devuelve el valor actual del atributo regionVisible.
	 * 
	 * @return valor actual del atributo.
	 */
	public boolean isRegionVisible() {
		return regionVisible;
	}
	
	/**
	 * Establece un nuevo valor para el atributo regionVisible.
	 * 
	 * @param regionVisible nuevo valor para el atributo.
	 */
	public void setRegionVisible(boolean regionVisible) {
		this.regionVisible = regionVisible;
	}
	
	/**
	 * Devuelve el valor actual del atributo cellVisible.
	 * 
	 * @return valor actual del atributo.
	 */
	public boolean isCellVisible() {
		return cellVisible;
	}
	
	/**
	 * Establece un nuevo valor para el atributo cellVisible.
	 * 
	 * @param cellVisible nuevo valor para el atributo.
	 */
	public void setCellVisible(boolean cellVisible) {
		this.cellVisible = cellVisible;
	}
	
	/**
	 * Devuelve una lista con todas las regiones dentro del tablero.
	 * 
	 * @return lista con las regiones.
	 */
	public LinkedList<Region> getRegion() {
		return regionList;
	}
	
	/**
	 * Agrega a una lista las regiones del sudoku.
	 */
	private void initializeRegionList() {
		regionList.clear();
		
		int row = 1, column = 1;
		int max = 1;
		for(int i=0; i<9; i++) {
			regionList.add(new Region((i + 1), row, column, 167));
			
			if(max > 2) {
				row++;
				max = 1;
				column = 1;
			}
			else {
				max++;
				column++;
			}
		}
	}
	
	/**
	 * Devuelve la dimension del tablero.
	 * 
	 * @return dimension del tablero.
	 */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * Establece un nuevo valor para la dimension del tablero.
	 * 
	 * @param dimension nuevo valor para la dimension del tablero.
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	/**
	 * Se pinta las celdas y region que coincida con los parametros solicitados.
	 * 
	 * @param indexRegion numero de region a la que pertenece.
	 * @param indexColumn region de columna de la celda.
	 * @param row region de fila de la celda.
	 */
	private void selectedRowRegion(int indexRegion, int indexColumn,int row) {
		for(Region region: getRegion()) {
			if(region.getRowRegion() == indexRegion) {
				for(Cell cell: region.getCellList()) {
					if(cell.getRowRegion() == region.getCellList().get(row).getRowRegion()) {
						cell.setSelectedMouse(true);
					}
				}
			}
			
			if(region.getColumnRegion() == indexColumn) {
				for(Cell cell: region.getCellList()) {
					if(cell.getColumnRegion() == region.getCellList().get(row).getColumnRegion()) {
						cell.setSelectedMouse(true);
					}
				}
			}
		}
	}
	
	/**
	 * Devuelve un booleano indicando si sudoku fue llenado de forma correcta.
	 * 
	 * @return true si el sudoku se soluciono de forma correcta, false de lo contrario.
	 */
	private boolean isCorrect() {
		if(regionList.size() > 0) {
			auxIndexRegion = 0;
			for(Region region: regionList) {
				for(Cell cell: region.getCellList()) {
					if(!GameManifest.GENERATESUDOKU.verificarValorCelda(cell.getValue(), (auxIndexRegion + 1) , cell.getRowRegion(), cell.getColumnRegion())) {
						return false;
					}
				}
				auxIndexRegion++;
			}
		}
		return true;
	}
	
	/**
	 * Las notas inteligentes de una celda en x region. Se actualizan solas.
	 * 
	 * @param indexCellRegion region a la que pertenece la celda.
	 * @param indexCellRowRegion region de fila de la celda.
	 * @param indexCellColumnRegion region de columna de la celda.
	 */
	public void InteligenceNote(int indexCellRegion, int indexCellRowRegion, int indexCellColumnRegion) {
		if(!GameManifest.NUMEROSELECT.contentEquals("")) {
			upgradeRegion(indexCellRegion, indexCellRowRegion, indexCellColumnRegion);
		}
	}
	
	/**
	 * Actualiza una region y las celdas que pertenecen a x region de fila y columna.
	 * 
	 * @param indexRegion numero de la region
	 * @param indexCellRowRegion region de fila de la celda.
	 * @param indexCellColumnRegion region de columna de la celda.
	 */
	private void upgradeRegion(int indexRegion, int indexCellRowRegion, int indexCellColumnRegion) {
		for(Region region: regionList) {
			if(region.getRegionNumber() == indexRegion) {
				for(Cell cell: region.getCellList()) {
					cell.upgradeNote(GameManifest.NUMEROSELECT);
				}
				upgradeRowRegionNotes(region.getRowRegion(), indexCellRowRegion);
				upgradeColumnRegionNotes(region.getColumnRegion(), indexCellColumnRegion);
			}
		}
	}
	
	/**
	 * Actualiza las notas que pertenecen a una region de fila x y una celda con region de fila x.
	 * 
	 * @param rowRegion region de fila de la region.
	 * @param indexCellRowRegion region de fila de la celda.
	 */
	private void upgradeRowRegionNotes(int rowRegion, int indexCellRowRegion) {
		for(Region region: regionList) {
			if(region.getRowRegion() == rowRegion) {
				for(Cell cell: region.getCellList()) {
					if(cell.getRowRegion() == indexCellRowRegion) {
						cell.upgradeNote(GameManifest.NUMEROSELECT);
					}
				}
			}
		}
	}
	
	/**
	 * Actualiza las notas que pertenecen a una region de columna x y una celda con region de columna x.
	 * 
	 * @param columnRegion region de columna de la region.
	 * @param indexCellColumnRegion region de columna de la celda.
	 */
	private void upgradeColumnRegionNotes(int columnRegion, int indexCellColumnRegion) {
		for(Region region: regionList) {
			if(region.getColumnRegion() == columnRegion) {
				for(Cell cell: region.getCellList()) {
					if(cell.getColumnRegion() == indexCellColumnRegion) {
						cell.upgradeNote(GameManifest.NUMEROSELECT);
					}
				}
			}
		}
	}
	
	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
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
		rectangle.width = getDimension() + (margen * 4);
		rectangle.height = getDimension() + (margen * 4);
		
		rectangle.x = x;
		rectangle.y = y;
		
		indexX = 0;
		indexY = 0;
		max = 3;
		if(regionList.size() > 0) {
			auxIndexRegion = 0;
			indexRegionCellSelected = -1;
			for(Region region: regionList) {
				
				region.setVisible(isRegionVisible());
				region.setCellVisible(isCellVisible());
				region.setCoordinates((x + ((region.getDimension() + margen) * indexX) + margen), (y + ((region.getDimension() + margen) * indexY) + margen));
				region.upgrade();
				
				if(region.getSelectedCellIndex() != -1) {
					indexRegionCellSelected = auxIndexRegion;
				}
				
				if((indexX + 1) < max) {
					indexX++;
				}
				else {
					indexY++;
					indexX = 0;
				}
				
				auxIndexRegion++;
			}
			
			if(!GameManifest.PAUSED) {
				if(GameManifest.PROFILE.isHighlightAreas()) {
					if(indexRegionCellSelected != -1) {
						selectedRowRegion(regionList.get(indexRegionCellSelected).getRowRegion(), regionList.get(indexRegionCellSelected).getColumnRegion() ,regionList.get(indexRegionCellSelected).getSelectedCellIndex());
					}
				}
			}
		}
		
		if(!GameManifest.TUTORIAL.isVisible()) {
			if(GameManifest.CELLCORRECT == 81) {
				if(isCorrect()) {
					GameManifest.SCENARIO.getWinnerMenu().setWinner(true);
					GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().stop();
					soundWinner.play();
					GameManifest.SCENARIO.setEscenarioActual(Scene.WINNERMENU);
				}
			}
			else if(GameManifest.PROFILE.getErrorLimit() > 0) {
				if((GameManifest.ERRORCOUNT >= GameManifest.PROFILE.getErrorLimit())) {
					GameManifest.SCENARIO.getWinnerMenu().setWinner(false);
					GameManifest.SCENARIO.getGameScenario().getControlPanel().getChronometer().stop();
					soundDefeat.play();
					GameManifest.SCENARIO.setEscenarioActual(Scene.WINNERMENU);
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		if(isVisible()) {
			Painter.rellenoRectangulo(g, rectangle, Color.BLACK);
			Painter.bordeRectangulo(g, rectangle, Color.BLACK);
			Painter.string(g, ""+GameManifest.CELLCORRECT, GameManifest.COLORCELLFOREGROUND, 10, 70);
			
			if(GameManifest.PROFILE.getErrorLimit() > 0) {
				Painter.string(g, GameManifest.ERRORCOUNT+"/"+GameManifest.PROFILE.getErrorLimit(), GameManifest.COLORCELLFOREGROUND, 10, 90);
			}
			
			if(regionList.size() > 0) {
				for(Region region: regionList) {
					region.paint(g);
				}
			}
		}
	}
}