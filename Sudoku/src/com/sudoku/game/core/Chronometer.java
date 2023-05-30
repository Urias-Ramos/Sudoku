package com.sudoku.game.core;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.sudoku.game.core.graphicsengine.UpdateAndPainting;
import com.sudoku.game.util.GameManifest;
import com.sudoku.game.util.Painter;

/**
 * Esta clase se encarga del funcionamiento del cronometro del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Chronometer implements UpdateAndPainting, Runnable {
	private boolean keepGoing;
	private boolean pause;
	private Thread thread;
	
	private String value;
	private String time;
	private int hour, minutes, seconds;
	
	private int x, y;
	private Rectangle screen;
	private Font font;
	
	/**
	 * Constructor de la clase.
	 * 
	 * Crea e inicializa los objetos de la clase.
	 */
	public Chronometer() {
		setKeepGoing(false);
		setPause(false);
		
		thread = new Thread(this);
		
		setTime("");
		setValue("00:00");
		setHour(0);
		setMinutes(0);
		setSeconds(0);
		
		x = 0;
		y = 0;
		
		screen = new Rectangle(x, y, 200, 42);
		font = new Font("Arial", Font.BOLD, 38);
	}
	
	/**
	 * Devuelve el tiempo actual en el que se encuentra el cronometro.
	 * 
	 * @return tiempo actual.
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * Establece el nuevo valor para el atributo time.
	 * 
	 * @param time nuevo valor para el atributo time.
	 */
	public void setTime(String time) {
		this.time = time;
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
	 * Devuelve la hora actual en el que el cronometro se encuentra.
	 * 
	 * @return la hora actual.
	 */
	public int getHour() {
		return hour;
	}
	
	/**
	 * Establece un nuevo valor para el atributo hour.
	 * 
	 * @param hour nuevo valor para el atributo hour.
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	/**
	 * Devuelve el minuto actual en el que el cronometro se encuentra.
	 * 
	 * @return el minuto actual.
	 */
	public int getMinutes() {
		return minutes;
	}
	
	/**
	 * Establece un nuevo valor para el atributo minutes.
	 * 
	 * @param hour nuevo valor para el atributo minutes.
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	
	/**
	 * Devuelve el segundo actual en el que el cronometro se encuentra.
	 * 
	 * @return el segundo actual.
	 */
	public int getSeconds() {
		return seconds;
	}
	
	/**
	 * Establece un nuevo valor para el atributo seconds.
	 * 
	 * @param hour nuevo valor para el atributo seconds.
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	/**
	 * Devuelve el valor actual del atributo keepGoing.
	 * 
	 * @return valor actual del atributo keepGoing.
	 */
	public boolean isKeepGoing() {
		return keepGoing;
	}
	
	/**
	 * Establece el nuevo valor del atributo keepGoing.
	 * 
	 * @param keepGoing nuevo valor para el atributo.
	 */
	public void setKeepGoing(boolean keepGoing) {
		this.keepGoing = keepGoing;
	}
	
	public boolean isPause() {
		return pause;
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	public synchronized void pause() {
		setPause(true);
	}
	
	public synchronized void resume() {
		setPause(false);
		notify();
	}
	
	public synchronized void stop() {
		resume();
		setKeepGoing(false);
	}
	
	public synchronized void start() {
		if(thread == null) {
			thread = new Thread(this);
		}
		
		setTime("");
		setValue("00:00");
		setHour(0);
		setMinutes(0);
		setSeconds(0);
		
		setKeepGoing(true);
		setPause(false);
		
		thread.start();
	}
	
	/**
	 * Este metodo se encarga de calcular el tiempo del cronometro.
	 */
	private void calculateTime() {
		setSeconds(getSeconds() + 1);
		if(getSeconds() == 60) {
			setSeconds(0);
			setMinutes(getMinutes() + 1);
		}
		
		if(getMinutes() == 60) {
			setMinutes(0);
			setHour(getHour() + 1);
		}
		
		setTime("");
		if(getHour() > 0) {
			setTime((getHour() <= 9?"0":"") + getHour() + ":");
		}
		
		setTime(getTime() + (getMinutes() <= 9?"0":"") + getMinutes() + ":" + (getSeconds() <= 9?"0":"") + getSeconds());
		
		setValue(getTime());
	}
	
	public Rectangle getRectanglePantalla() {
		return screen;
	}
	
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void upgrade() {
		screen.x = x;
		screen.y = y;
	}

	@Override
	public void paint(Graphics g) {
		Painter.setFont(g, font);
		
		Painter.bordeRectangulo(g, screen, GameManifest.COLORLINE);
		
		x = (int) screen.getCenterX() - (Painter.getAnchoString(g, getValue()) / 2);
		y = (int) screen.getCenterY() + (Painter.getAltoString(g, getValue()) / 4) + 4;
		
		if(!GameManifest.PROFILE.isCounter()) {
			x = (int) screen.getCenterX() - (Painter.getAnchoString(g, "-") / 2);
			y = (int) screen.getCenterY() + (Painter.getAltoString(g, "-") / 4) + 4;
			Painter.string(g, "-", GameManifest.COLORVALUE, x, y);
		}
		else {
			Painter.string(g, getValue(), GameManifest.COLORVALUE, x, y);
		}
	}

	@Override
	public void run() {
		while(isKeepGoing()) {
			
			calculateTime();
			
			if(isKeepGoing()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			synchronized(this) {
				while(isPause()) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		thread = null;
	}
}