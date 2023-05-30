package com.sudoku.game.core;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;

/**
 * Esta clase se encarga de actualizar y pintar el teclado numerico, la barra de accion y el cronometro.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class ControlPanel implements UpdateAndPainting {
	private int x, y;
	private Rectangle rectangle;
	
	private int margen;
	
	private Chronometer cronometro;
	private NumericKeyboard keyboard;
	private ActionsBar actionsBar;
	
	public enum TYPEKEYCONTROL {
		NUMERIC,
		ERASER,
		HINT,
		OTHER,
		NONE;
	}
	
	private TYPEKEYCONTROL typeKeyControl;
	
	private Font font;
	private String value;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Aqui se crea e inicia los objetos de la clase.
	 */
	public ControlPanel() {
		x = 0;
		y = 0;
		
		margen = 10;
		
		rectangle = new Rectangle(x, y, 300, 300);
		
		cronometro = new Chronometer();
		keyboard = new NumericKeyboard();
		actionsBar = new ActionsBar();
		
		setTypeKeyControl(TYPEKEYCONTROL.NUMERIC);
		
		font = new Font("Arial", Font.BOLD, 22);
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
	 * Establece el ancho y alto que tendra el panel de control.
	 * 
	 * @param width ancho del panel de control.
	 * @param height alto del panel de control.
	 */
	public void setSizePanel(int width, int height) {
		rectangle.width = width;
		rectangle.height = height;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	/**
	 * Devuelve el NumericKeyboard para tener acceso a sus metodos y atribytos.
	 * 
	 * @return devuelve el NumericKeyboard;
	 */
	public NumericKeyboard getKeyboard() {
		return keyboard;
	}
	
	/**
	 * Devuelve el Chronometer para tener acceso a sus metodos y atribytos.
	 * 
	 * @return devuelve el Chronometer;
	 */
	public Chronometer getChronometer() {
		return cronometro;
	}
	
	/**
	 * Devuelve que tipo de tecla que esta seleccionada.
	 * 
	 * @return tipo de tecla seleccionada.
	 */
	public TYPEKEYCONTROL getTypeKeyControl() {
		return typeKeyControl;
	}
	
	/**
	 * Establece el tipo de tecla seleccionada.
	 * 
	 * @param typeKeyControl el nuevo tipo de tecla seleccionada.
	 */
	public void setTypeKeyControl(TYPEKEYCONTROL typeKeyControl) {
		this.typeKeyControl = typeKeyControl;
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
		rectangle.x = x - rectangle.width;
		rectangle.y = y;
		
		cronometro.setCoordinates((int) rectangle.getCenterX() - (cronometro.getRectanglePantalla().width / 2), rectangle.y + margen);
		cronometro.upgrade();
		
		keyboard.setDimension(rectangle.width);
		x = rectangle.x + (keyboard.getDimension());
		y = (int) rectangle.getCenterY() + 60;
		
		keyboard.setPosicionTablero(x, y);
		keyboard.upgrade();
		
		y = y + (keyboard.getDimension() * 7) + (margen * 3);
		actionsBar.setCoordenadas(x + (margen + 5), y);
		actionsBar.upgrade();
	}

	@Override
	public void paint(Graphics g) {
		Painter.rellenoRectangulo(g, rectangle, GameManifest.COLORCELLBACKGROUND);
		
		cronometro.paint(g);
		keyboard.paint(g);
		actionsBar.paint(g);
		
		switch(GameManifest.SCENARIO.getGameScenario().getBoard().getDifficulty().getDificultyMode()) {
		case EASY:
			setValue(GameManifest.IDIOME.getProperty("3002"));
			break;
		case MEDIUM:
			setValue(GameManifest.IDIOME.getProperty("3003"));
			break;
		case HARD:
			setValue(GameManifest.IDIOME.getProperty("3004"));
			break;
			default:
				setValue(GameManifest.IDIOME.getProperty("3002"));
		}
		
		Painter.setFont(g, font);
		x = (int) (cronometro.getRectanglePantalla().getCenterX() - (Painter.getAnchoString(g, getValue()) / 2));
		y = (int) ((cronometro.getRectanglePantalla().y + cronometro.getRectanglePantalla().height) + 42);
		
		Painter.string(g, getValue(), GameManifest.COLORCELLFOREGROUND, x, y);
	}
}