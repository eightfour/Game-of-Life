package conwaysgameoflife;

import javax.swing.JButton;

public class Cell extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5935270204414483788L;
	
	private boolean alive = false;

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
