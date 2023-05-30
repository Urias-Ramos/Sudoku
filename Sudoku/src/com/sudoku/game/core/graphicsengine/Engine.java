package com.sudoku.game.core.graphicsengine;

import javax.swing.JOptionPane;

import com.sudoku.game.util.GameManifest;

/**
 * Esta clase reprsenta el motor grafico del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Engine implements Runnable {
	
	/**
	 * Constructor de la clase.
	 */
	public Engine() {
		GameManifest.HILO_JUEGO = new Thread(this);
		GameManifest.RUNNING_GAME = false;
	}
	
	/**
	 * Crea el hilo principal si no esta creado y lo enciende.
	 */
	public synchronized void startGame() {
		if(GameManifest.HILO_JUEGO != null) {
			GameManifest.RUNNING_GAME = true;
			GameManifest.HILO_JUEGO.start();
		}
		else {
			JOptionPane.showMessageDialog(GameManifest.SCENARIO, "EL HILO DEL MOTOR NO ESTA EN LINEA", "ENGINE", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Detiene el motor grafico.
	 */
	public static synchronized void stopGame() {
		GameManifest.RUNNING_GAME = false;
	}
	
	/**
	 * Ordena al SCENARIO a actualizarse.
	 */
	private void upgradeEngine() {
		GameManifest.SCENARIO.upgradeScenario();
	}
	
	/**
	 * Ordena al SCENARIO a pintarse.
	 */
	private void paintEngine() {
		GameManifest.SCENARIO.paintScenario();
	}

	@Override
	public void run() {
		int NANOSEGUNDOS = 1000000000;//1s = 1000000000ns
		int APS_REQUERIDOS = 60;//El juego se debe actualizar 60 veces por segundo
		
		double TOTAL_ACTUALIZACION = (NANOSEGUNDOS / APS_REQUERIDOS);
		
		long referenciaActualizacion = System.nanoTime();
		
		double delta = 0;
		long INICIO_CICLO = 0;
		
		int tazaActualizaciones = 0;
		int tazaFotogramas = 0;
		
		long timer = 0;
		while(GameManifest.RUNNING_GAME) {
			INICIO_CICLO = System.nanoTime();
			
			delta += (INICIO_CICLO - referenciaActualizacion) / TOTAL_ACTUALIZACION;
			
			referenciaActualizacion = INICIO_CICLO;
			
			while(delta >= 1) {
				upgradeEngine();
				tazaActualizaciones++;
				delta--;
			}
			paintEngine();
			tazaFotogramas++;
			
			if((timer) >= NANOSEGUNDOS) {
				GameManifest.APS = tazaActualizaciones;
				GameManifest.FPS = tazaFotogramas;
				
				tazaActualizaciones = 0;
				tazaFotogramas = 0;
				timer = 0;
			}
		}
		GameManifest.HILO_JUEGO = null;
	}
}