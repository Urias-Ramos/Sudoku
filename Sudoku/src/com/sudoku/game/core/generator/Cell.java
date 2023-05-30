package com.sudoku.game.core.generator;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.sudoku.game.core.ControlPanel.TYPEKEYCONTROL;
import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;
import com.sudoku.game.util.Sound;

/**
 * Esta clase representa una celda del sudoku.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Cell implements UpdateAndPainting {
	private int region;
	private int rowRegion, columnRegion;
	private String value;
	
	private boolean editable;
	private boolean selected;
	private boolean selectedNumber;
	private boolean cellError;
	
	private boolean selectedMouse;
	private boolean selectedTutorial;
	
	private int dimension;
	private int margen;
	
	private int x, y;
	private Rectangle rectangle;
	
	private boolean visible;
	
	private LinkedList<Notes> noteList;
	
	private LinkedList<String> numberList;
	
	private int indexX, indexY;
	private int max;
	
	private Font font;
	
	private Sound soundEraser, soundPencil;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Aqui se crea una instancia de la clase utilizando los parametros solicitados.
	 * 
	 * @param value valor actual de la celda.
	 * @param region identifica a que region pertenece la celda.
	 * @param rowRegion identifica a que region de fila pertenece la celda.
	 * @param columnRegion identifica a que region de columna pertenece la celda.
	 * @param dimension dimension de la celda.
	 */
	public Cell(String value, int region, int rowRegion, int columnRegion, int dimension) {
		setValue(value);
		setEditable(false);
		setSelected(false);
		setSelectedNumber(false);
		setCellError(false);
		setSelectedMouse(false);
		
		setRegion(region);
		setRowRegion(rowRegion);
		setColumnRegion(columnRegion);
		
		setDimension(dimension);
		margen = 0;
		
		x = 0;
		y = 0;
		
		rectangle = new Rectangle(0, 0, getDimension(), getDimension());
		
		noteList = new LinkedList<Notes>();
		initializeNoteList();
		
		setVisible(true);
		
		font = new Font("Arial", Font.BOLD, 22);
		
		soundEraser = new Sound("sound/sfx-eraser.wav");
		soundPencil = new Sound("sound/sfx-writting.wav");
	}
	
	/**
	 * Este metodo crea las celdas de notas asociadas a la celda.
	 */
	private void initializeNoteList() {
		noteList.clear();
		
		for(int i=0; i<9; i++) {
			noteList.add(new Notes("", ((getDimension() + (margen * 2)) / 3) - (margen * 2)));
		}
	}
	
	/**
	 * Actualiza el valor de la nota, si esta no esta vacia.
	 * 
	 * @param value nuevo valor para la nota.
	 */
	public void upgradeNote(String value) {
		if(!value.contentEquals("")) {
			noteList.get(Integer.parseInt(value) - 1).setValue("");
		}
	}
	
	/**
	 * Devuelve el estado de la celda si esta visible o no.
	 * 
	 * @return estado actual del atributo visible.
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Establece un nuevo valor para el estado de la celda visible.
	 * 
	 * @param visible nuevo valor para el atributo.
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Devuelve el valor de la tecla.
	 * 
	 * @return valor de la tecla.
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Establece el valor que tendra la tecla.
	 * 
	 * @param value nuevo valor de la tecla
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Devuelve el estado actual del atributo editable.
	 * 
	 * @return valor actual del atributo.
	 */
	public boolean isEditable() {
		return editable;
	}
	
	/**
	 * Establece un nuevo valor para el atributo editable.
	 * 
	 * @param editable nuevo valor para el atributo.
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
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
	 * Devuelve el valor actual del atributo selectedNumber.
	 * 
	 * @return valor actual del atributo.
	 */
	public boolean isSelectedNumber() {
		return selectedNumber;
	}
	
	/**
	 * Establece el nuevo valor para el atributo selectedNumber.
	 * 
	 * @param selectedNumber nuevo valor para el atributo.
	 */
	public void setSelectedNumber(boolean selectedNumber) {
		this.selectedNumber = selectedNumber;
	}
	
	/**
	 * Devuelve el valor actual del atributo cellError.
	 * 
	 * @return valor actual del atributo.
	 */
	public boolean isCellError() {
		return cellError;
	}
	
	/**
	 * Etablece el nuevo valor para el atributo cellError.
	 * 
	 * @param cellError nuevo valor para el atributo.
	 */
	public void setCellError(boolean cellError) {
		this.cellError = cellError;
	}
	
	/**
	 * Devuelve el valor actual del atributo selectedMouse.
	 * 
	 * @return valor actual del atributo.
	 */
	public boolean isSelectedMouse() {
		return selectedMouse;
	}
	
	/**
	 * Etablece el nuevo valor para el atributo selectedMouse.
	 * 
	 * @param selectedMouse nuevo valor para el atributo.
	 */
	public void setSelectedMouse(boolean selectedMouse) {
		this.selectedMouse = selectedMouse;
	}
	
	/**
	 * Devuelve el valor actual del atributo selectedTutorial.
	 * 
	 * @return valor actual del atributo.
	 */
	public boolean isSelectedTutorial() {
		return selectedTutorial;
	}
	
	/**
	 * Etablece el nuevo valor para el atributo selectedTutorial.
	 * 
	 * @param selectedTutorial nuevo valor para el atributo.
	 */
	public void setSelectedTutorial(boolean selectedTutorial) {
		this.selectedTutorial = selectedTutorial;
	}
	
	/**
	 * Devuelve la dimension de la celda.
	 * 
	 * @return dimension de la celda.
	 */
	public int getDimension() {
		return dimension;
	}
	
	/**
	 * Establece un nuevo valor para la dimension de la celda.
	 * 
	 * @param dimension nuevo valor para la dimension de la celda.
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	/**
	 * Devuelve la region a la que pertenece la celda.
	 * 
	 * @return region a la que pertenece la celda.
	 */
	public int getRegion() {
		return region;
	}
	
	/**
	 * Establece una nueva region a la que pertenecera la celda.
	 * 
	 * @param region nuevo valor para el atributo.
	 */
	public void setRegion(int region) {
		this.region = region;
	}
	
	/**
	 * Devuelve la region de fila a la que pertenece la celda.
	 * 
	 * @return region de fila a la que pertenece la celda.
	 */
	public int getRowRegion() {
		return rowRegion;
	}
	
	/**
	 * Establece una nueva region de fila a la que pertenecera la celda.
	 * 
	 * @param rowRegion nuevo valor para el atributo.
	 */
	public void setRowRegion(int rowRegion) {
		this.rowRegion = rowRegion;
	}
	
	/**
	 * Devuelve la region de columna a la que pertenece la celda.
	 * 
	 * @return region de columna a la que pertenece la celda.
	 */
	public int getColumnRegion() {
		return columnRegion;
	}
	
	/**
	 * Establece una nueva region de columna a la que pertenecera la celda.
	 * 
	 * @param columnRegion nuevo valor para el atributo.
	 */
	public void setColumnRegion(int columnRegion) {
		this.columnRegion = columnRegion;
	}
	
	/**
	 * Devuelve una lista con las 9 listas de notas de la celda.
	 * 
	 * @return lista con las 9 celdas de notas.
	 */
	public LinkedList<Notes> getNotesList() {
		return noteList;
	}
	
	/**
	 * Devuelve una lista con numeros.
	 * 
	 * @return lista con los numeros.
	 */
	public LinkedList<String> getNumberList() {
		return numberList;
	}
	
	/**
	 * Establece un uan nueva lista para el atributo numberList-
	 * 
	 * @param numberList nuevo valor para el atributo.
	 */
	public void setNumberList(LinkedList<String> numberList) {
		this.numberList = numberList;
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
	
	/**
	 * Devuelve las coordenadas donde esta ubicada la celda.
	 * 
	 * @return ubicacion de la celda.
	 */
	public Point getCoordinates() {
		return new Point((int) rectangle.getCenterX(), rectangle.y);
	}
	
	/**
	 * Se encarga de agregar y tomar acciones dependiendo del valor a agregar.
	 * 
	 * @param value nuevo valor para la celda.
	 */
	private void addValueCell(String value) {
		//El valor es borrar el valor de la celda
		if(value.contentEquals("")) {
			if(!getValue().contentEquals(value)) {
				soundEraser.play();
				GameManifest.SCENARIO.getGameScenario().getKeyboard().getKey(Integer.parseInt(getValue()) - 1).incrementCounter();
				setValue(value);
				setCellError(false);;
			}
		}
		else if((!getValue().contentEquals(value))&&(getValue().contentEquals(""))) {
			//Si el valor de la celda es diferente del valor a colocar
			soundPencil.play();
			setValue(value);
			
			GameManifest.SCENARIO.getGameScenario().getKeyboard().getKey(Integer.parseInt(value) - 1).decrementCounter();
			GameManifest.CELLCORRECT++;
			GameManifest.SCOREACTUAL++;
			
			//Si esta activado las notas inteligentes este se actualiza
			if(GameManifest.PROFILE.isSmartNotes()) {
				GameManifest.SCENARIO.getGameScenario().getBoard().InteligenceNote(getRegion(), getRowRegion(), getColumnRegion());
			}
			verificatedCellValue(getValue());
		}
	}
	
	/**
	 * Se encarga de ver si el valor ingresado en la celda existe y si es asi marca la celda con error.
	 * 
	 * @param value valor a verificar.
	 */
	private void verificatedCellValue(String value) {
		setCellError(false);
		if(!value.contentEquals("")) {
			if((isExistValue(getValue(), getNumberList()))||(!GameManifest.GENERATESUDOKU.verificarValorCelda(getValue(), getRegion(), getRowRegion(), getColumnRegion()))) {
				setCellError(true);
				GameManifest.ERRORCOUNT++;
				GameManifest.CELLCORRECT--;
			}
		}
	}

	@Override
	public void upgrade() {
		rectangle.x = x;
		rectangle.y = y;
		
		if(GameManifest.SHOWNOTES) {
			indexX = 0;
			indexY = 0;
			max = 3;
			
			for(Notes note: noteList) {
				
				note.setCoordinates((x + ((note.getDimension() + margen) * indexX) + margen), (y + ((note.getDimension() + margen) * indexY) + margen));
				note.upgrade();
				
				if((indexX + 1) < max) {
					indexX++;
				}
				else {
					indexY++;
					indexX = 0;
				}
			}
		}
		
		if(!GameManifest.PAUSED) {
			setSelectedMouse(false);
			if(GameManifest.RATON.getRectanguloPosicion().intersects(rectangle)) {
				setSelectedMouse(true);
				if((GameManifest.RATON.isClicIzquierdo())&&(isEditable())) {
					GameManifest.RATON.setClicIzquierdo(false);
					
					if(!GameManifest.SHOWNOTES) {
						if((GameManifest.SCENARIO.getGameScenario().getControlPanel().getTypeKeyControl() == TYPEKEYCONTROL.NUMERIC)||(GameManifest.SCENARIO.getGameScenario().getControlPanel().getTypeKeyControl() == TYPEKEYCONTROL.ERASER)) {
							addValueCell(GameManifest.NUMEROSELECT);
						}
						else if(GameManifest.SCENARIO.getGameScenario().getControlPanel().getTypeKeyControl() == TYPEKEYCONTROL.HINT) {
							addValueCell(""+GameManifest.GENERATESUDOKU.getHint(getRegion(), getRowRegion(), getColumnRegion()));
						}
					}
					else {
						if(!GameManifest.NUMEROSELECT.contentEquals("")) {
							if(noteList.get(Integer.parseInt(GameManifest.NUMEROSELECT) - 1).getValue().contentEquals("")) {
								noteList.get(Integer.parseInt(GameManifest.NUMEROSELECT) - 1).setValue(GameManifest.NUMEROSELECT);
							}
							else {
								noteList.get(Integer.parseInt(GameManifest.NUMEROSELECT) - 1).setValue("");
							}
						}
					}
				}
			}
			
			setSelectedNumber(false);
			if(GameManifest.PROFILE.isHighlightIdenticalNumbers()) {
				if((GameManifest.NUMEROSELECT.contentEquals(getValue()))&&(!GameManifest.NUMEROSELECT.contentEquals(""))) {
					setSelectedNumber(true);
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		if(isVisible()) {
			if((isSelectedNumber())||(isCellError())) {
				if(isSelectedNumber()) {
					Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORNUMBERSELECTED);
				}
				
				if(isCellError()) {
					Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORBACKGROUNDERROR);
				}
			}
			else {
				if(!isSelected()&&(!isSelectedMouse())) {
					Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORCELLBACKGROUND);
				}
				else {
					Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORREGIONSELECTED);
				}
			}
			
			if(isSelectedTutorial()) {
				Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORCELESTE);
			}
			
			if((GameManifest.SHOWNOTES)&&(getValue().contentEquals(""))) {
				for(Notes note: noteList) {
					note.paint(g);
				}
			}
			else if(!GameManifest.PAUSED) {
				Painter.setFont(g, font);
				
				x = (int) (rectangle.getCenterX() - (Painter.getAnchoString(g, ""+getValue()) / 2));
				y = (int) (rectangle.getCenterY() + (Painter.getAltoString(g, ""+getValue()) / 2));
				
				if(!isEditable()) {
					Painter.string(g, ""+getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
				}
				else {
					if(isCellError()) {
						Painter.string(g, ""+getValue(), GameManifest.COLORNUMBERERROR, x, y);
					}
					else {
						Painter.string(g, ""+getValue(), GameManifest.COLORVALUE, x, y);
					}
				}
			}
		}
	}
}