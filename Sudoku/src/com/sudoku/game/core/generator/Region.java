package com.sudoku.game.core.generator;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;

/**
 * Esta clase representa las regiones del tablero del sudoku.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Region implements UpdateAndPainting {
	private int regionNumber;
	private int rowRegion, columnRegion;
	private boolean selected;
	
	private int indexCellSelected;
	private int selectedCellIndex;
	
	private int dimension;
	private int margen;
	
	private int x, y;
	private Rectangle rectangle;
	
	private boolean visible;
	private boolean cellVisible;
	
	private LinkedList<Cell> cellList;
	
	private LinkedList<String> numberList;
	
	private int indexX, indexY;
	private int max;
	
	/**
	 * Constructor de la clase-
	 * 
	 * Aqui se crea la instancia de la clase con los parametros solicitados.
	 * 
	 * @param regionNumber numero de region dentro del tablero.
	 * @param rowRegion region de fila dentro del tablero.
	 * @param columnRegion region de columna dentro del tablero.
	 * @param dimension dimension de la region.
	 */
	public Region(int regionNumber, int rowRegion, int columnRegion, int dimension) {
		setRegionNumber(regionNumber);
		setRowRegion(rowRegion);
		setColumnRegion(columnRegion);
		setSelected(false);
		setSelectedCellIndex(-1);
		
		setDimension(dimension);
		margen = 2;
		
		x = 0;
		y  = 0;
		
		rectangle = new Rectangle(0, 0, getDimension(), getDimension());
		
		cellList = new LinkedList<Cell>();
		initializeCellList();
		
		numberList = new LinkedList<String>();
		
		setVisible(true);
		setCellVisible(true);
	}
	
	/**
	 * Devuelve el estado de la region si esta visible o no.
	 * 
	 * @return estado actual del atributo visible.
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Establece un nuevo valor para el estado de la region visible.
	 * 
	 * @param visible nuevo valor para el atributo.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Devuelve el valor del atributo cellVisible.
	 * 
	 * @return valor actual del atributo.
	 */
	public boolean isCellVisible() {
		return cellVisible;
	}
	
	/**
	 * Establece un nuevo valor para el atributo cellVisible.
	 * 
	 * @param cellVisible valor actual del atributo.
	 */
	public void setCellVisible(boolean cellVisible) {
		this.cellVisible = cellVisible;
	}
	
	/***
	 * Le asigna el valor a una celda.
	 * 
	 * @param indexCell indice de la celda a cambiar su valor.
	 * @param value valor que tendra la celda.
	 */
	public void startCell(int indexCell, String value) {
		cellList.get(indexCell).setValue(value);
	}
	
	/**
	 * Devuelve la lista con todas las celdas de la region-
	 * 
	 * @return lista de celdas.
	 */
	public LinkedList<Cell> getCellList() {
		return cellList;
	}
	
	/**
	 * Agrega a la region las 9 celdas que perteneceran a la region.
	 */
	private void initializeCellList() {
		cellList.clear();
		
		int row = 1, column = 1;
		int max = 1;
		for(int i=0; i<9; i++) {
			cellList.add(new Cell("", getRegionNumber(), row, column, ((getDimension() + (margen * 2)) / 3) - (margen * 2)));
			
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
	 * Llena las celdas y establece sus propiedades dado el arreglo pasado como argumento.
	 * 
	 * @param tabla valores de la region.
	 */
	public void setCell(int[] tabla) {
		int index = 0;
		for(Cell cell: getCellList()) {
			if(tabla[index] == 0) {
				cell.setValue("");
				cell.setEditable(true);
			}
			else {
				GameManifest.CELLCORRECT++;
				cell.setEditable(false);
				cell.setValue(""+tabla[index]);
				GameManifest.SCENARIO.getGameScenario().getKeyboard().getKey(Integer.parseInt(cell.getValue()) - 1).decrementCounter();
			}
			index++;
		}
	}
	
	/**
	 * Devuelve el numero de la region en la que pertenece en el tablero.
	 * 
	 * @return region actual dentro del tablero.
	 */
	public int getRegionNumber() {
		return regionNumber;
	}
	
	/**
	 * Establece un nuevo valor para el atributo regionNumber.
	 * 
	 * @param regionNumber nuevo valor para la region.
	 */
	public void setRegionNumber(int regionNumber) {
		this.regionNumber = regionNumber;
	}
	
	/**
	 * Devuelve el numero de la region de fila en la que pertenece en el tablero.
	 * 
	 * @return region de fila actual dentro del tablero.
	 */
	public int getRowRegion() {
		return rowRegion;
	}
	
	/**
	 * Establece un nuevo valor para el atributo rowRegion.
	 * 
	 * @param rowRegion nuevo valor para la region de fila.
	 */
	public void setRowRegion(int rowRegion) {
		this.rowRegion = rowRegion;
	}
	
	/**
	 * Devuelve el numero de la region de columna en la que pertenece en el tablero.
	 * 
	 * @return region de columna actual dentro del tablero.
	 */
	public int getColumnRegion() {
		return columnRegion;
	}
	
	/**
	 * Establece un nuevo valor para el atributo columnRegion.
	 * 
	 * @param columnRegion nuevo valor para la region de columna.
	 */
	public void setColumnRegion(int columnRegion) {
		this.columnRegion = columnRegion;
	}
	
	/**
	 * Devuelve el valor actual del atributo selected.
	 * 
	 * @return valor actual del atributo.
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Etablece el nuevo valor para el atributo selected.
	 * 
	 * @param selected nuevo valor para el atributo.
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * Devuelve el valor actual del atributo selectedCellIndex.
	 * 
	 * @return valor actual del atributo.
	 */
	public int getSelectedCellIndex() {
		return selectedCellIndex;
	}
	
	/**
	 * Etablece el nuevo valor para el atributo selectedCellIndex.
	 * 
	 * @param selected nuevo valor para el atributo.
	 */
	public void setSelectedCellIndex(int selectedCellIndex) {
		this.selectedCellIndex = selectedCellIndex;
	}
	
	/**
	 * Devuelve la dimension de la region.
	 * 
	 * @return dimension de la region.
	 */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * Establece un nuevo valor para la dimension de la region.
	 * 
	 * @param dimension nuevo valor para la dimension de la region.
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	/**
	 * Devuelve la existencia de un valor dentro de una lista.
	 * 
	 * @param value valor a verificar.
	 * @param lista lista donde se debe buscar el valor.
	 * @return true si existe el valor, falso si no existe.
	 */
	private boolean isExistValue(String value, LinkedList<String> lista) {
		for(int i=0; i<lista.size(); i++) {
			if(lista.get(i).contentEquals(value)) {
				return true;
			}
		}
		return false;
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
		rectangle.x = x;
		rectangle.y = y;
		
		if(cellList.size() > 0) {
			
			numberList.clear();
			for(Cell cell: cellList) {
				if(!cell.getValue().contentEquals("")) {
					if(!isExistValue(cell.getValue(), numberList)) {
						numberList.add(cell.getValue());
					}
				}
			}
			
			indexX = 0;
			indexY = 0;
			max = 3;
			
			setSelected(false);
			for(Cell cell: cellList) {
				
				cell.setVisible(isCellVisible());
				cell.setNumberList(numberList);
				cell.setCoordinates((x + ((cell.getDimension() + margen) * indexX) + margen), (y + ((cell.getDimension() + margen) * indexY) + margen));
				cell.upgrade();
				
				if(!GameManifest.PAUSED) {
					if(GameManifest.PROFILE.isHighlightAreas()) {
						if(GameManifest.RATON.getRectanguloPosicion().intersects(rectangle)) {
							setSelected(true);
						}
					}
				}
				
				if((indexX + 1) < max) {
					indexX++;
					
				}
				else {
					indexY++;
					indexX = 0;
				}
			}
		}
		
		setSelectedCellIndex(-1);
		indexCellSelected = 0;
		for(Cell cell: cellList) {
			cell.setSelected(isSelected());
			
			if(cell.isSelectedMouse()) {
				setSelectedCellIndex(indexCellSelected);
			}
			indexCellSelected++;
		}
	}

	@Override
	public void paint(Graphics g) {
		if(isVisible()) {
			Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORREGIONBACKGROUND);
			
			if(cellList.size() > 0) {
				for(Cell cell: cellList) {
					cell.paint(g);
				}
			}
		}
	}
}