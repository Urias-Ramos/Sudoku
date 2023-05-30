package com.sudoku.game.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Esta clase se encarga de abrir y reproducir el sonido del juego.
 * 
 * @author Urias Ramos
 * @version 1.0
 * @since 2023-03-19
 *
 */
public class Sound {
	private Clip clip;
	
	/**
	 * Constructor de la clase.
	 * 
	 * crea una objeto Sound con el sonido establecido.
	 * 
	 * @param ruta direccion del archivo dentro del juego.
	 */
	public Sound(String ruta) {
		clip = cargarSonido(ruta);
	}
	
	/**
	 * Devuelve un objeto Clip con el sonido solicitado dentro del juego.
	 * 
	 * @param rutaSonido direccion del sonido.
	 * @return Clip con el sonido solicitado.
	 */
	private Clip cargarSonido(String rutaSonido) {
		Clip clip = null;
		InputStream inputstream = getClass().getClassLoader().getResourceAsStream(rutaSonido);
		try {
			AudioInputStream audioinputstream = AudioSystem.getAudioInputStream(new BufferedInputStream(inputstream));
			DataLine.Info dataline = new DataLine.Info(Clip.class, audioinputstream.getFormat());
			
			clip = (Clip) AudioSystem.getLine(dataline);
			clip.open(audioinputstream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return clip;
	}
	
	/**
	 * Reproduce el sonido, si la opcion de effectos de sonido esta activado.
	 */
	public void play() {
		if(GameManifest.PROFILE.isSoundEffect()) {
			if(!clip.isRunning()) {
				clip.stop();
				clip.flush();
				clip.setMicrosecondPosition(0);
				clip.start();
			}
		}
	}
}