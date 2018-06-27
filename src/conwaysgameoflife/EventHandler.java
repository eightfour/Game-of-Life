package conwaysgameoflife;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EventHandler implements ActionListener, ChangeListener {

	private GUI gui;
	private Thread losT;

	public EventHandler(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void stateChanged(ChangeEvent a) {
		Object o = a.getSource();
		initUniverse(o);
		initRandomUniverse(o);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		Object o = a.getSource();
		if (o == gui.getOk()) {
			actionOkDialog(o);
			return;
		}
		actionOkRandDialog(o);
		initCell(o);
		nextGen(o);
		neu(o);
		los(o);
		stop(o);
		actionRandomUniverse(o);
	}

	private void neu(Object o) {
		if (o == gui.getNeu()) {
			stop(o);
			resetGenerationAndPopulation();
			for (int i = 0; i < gui.getcButtons().length; i++) {
				gui.getcButtons()[i].setAlive(false);
				gui.getcButtons()[i].setBackground(Color.LIGHT_GRAY);
			}
		}
	}

	private void actionOkDialog(Object o) {
		if (o == gui.getOk()) {
			gui.getDialog().dispose();
		}
	}

	private int getCellPos(Cell b) {
		for (int i = 0; i < gui.getcButtons().length; i++) {
			if (b == gui.getcButtons()[i]) {
				return i;
			}
		}
		throw new RuntimeException("Zelle nicht vorhanden.");
	}

	private int countAliveNeighborhood(Cell b) {
		int count = 0;
		int cellPos = getCellPos(b);
		int cRows = gui.getcRows();

		// kleiner
		if (cellPos < gui.getN1()) {
			if (cellPos < gui.getN4()) {
				int pufferMinusN4 = cellPos - gui.getN4();
				if ((gui.getcButtons()[(cRows * cRows) + pufferMinusN4].getBackground().equals(Color.BLACK))) {
					count++;
				}
				int pufferMinusN3 = cellPos - gui.getN3();
				if (gui.getcButtons()[(cRows * cRows) + pufferMinusN3].getBackground().equals(Color.BLACK)) {
					count++;
				}
				int pufferMinusN2 = cellPos - gui.getN2();
				if (gui.getcButtons()[(cRows * cRows) + pufferMinusN2].getBackground().equals(Color.BLACK)) {
					count++;
				}
				int pufferMinusN1 = cellPos - gui.getN1();
				if (gui.getcButtons()[(cRows * cRows) + pufferMinusN1].getBackground().equals(Color.BLACK)) {
					count++;
				}
			}
			if (cellPos < gui.getN3() && cellPos >= gui.getN4()) {
				if (gui.getcButtons()[cellPos - gui.getN4()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				int pufferMinusN3 = cellPos - gui.getN3();
				if (gui.getcButtons()[(cRows * cRows) + pufferMinusN3].getBackground().equals(Color.BLACK)) {
					count++;
				}
				int pufferMinusN2 = cellPos - gui.getN2();
				if (gui.getcButtons()[(cRows * cRows) + pufferMinusN2].getBackground().equals(Color.BLACK)) {
					count++;
				}
				int pufferMinusN1 = cellPos - gui.getN1();
				if (gui.getcButtons()[(cRows * cRows) + pufferMinusN1].getBackground().equals(Color.BLACK)) {
					count++;
				}
			}
			if (cellPos < gui.getN2() && cellPos >= gui.getN3()) {
				if (gui.getcButtons()[cellPos - gui.getN4()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos - gui.getN3()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				int pufferMinusN2 = cellPos - gui.getN2();
				if (gui.getcButtons()[(cRows * cRows) + pufferMinusN2].getBackground().equals(Color.BLACK)) {
					count++;
				}
				int pufferMinusN1 = cellPos - gui.getN1();
				if (gui.getcButtons()[(cRows * cRows) + pufferMinusN1].getBackground().equals(Color.BLACK)) {
					count++;
				}
			}
			if (cellPos < gui.getN1() && cellPos >= gui.getN2()) {
				if (gui.getcButtons()[cellPos - gui.getN4()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos - gui.getN3()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos - gui.getN2()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				int pufferMinusN1 = cellPos - gui.getN1();
				if (gui.getcButtons()[(cRows * cRows) + pufferMinusN1].getBackground().equals(Color.BLACK)) {
					count++;
				}
			}
			if (gui.getcButtons()[cellPos + gui.getN4()].getBackground().equals(Color.BLACK)) {
				count++;
			}
			if (gui.getcButtons()[cellPos + gui.getN3()].getBackground().equals(Color.BLACK)) {
				count++;
			}
			if (gui.getcButtons()[cellPos + gui.getN2()].getBackground().equals(Color.BLACK)) {
				count++;
			}
			if (gui.getcButtons()[cellPos + gui.getN1()].getBackground().equals(Color.BLACK)) {
				count++;
			}
		} else {

			if (cellPos > (((cRows * cRows) - 1) - gui.getN1())) {

				if (cellPos > (((cRows * cRows) - 1) - gui.getN4())) {
					int pufferPlusN4 = (cellPos + gui.getN4()) - ((cRows * cRows));
					if (gui.getcButtons()[0 + pufferPlusN4].getBackground().equals(Color.BLACK)) {
						count++;
					}
					int pufferPlusN3 = (cellPos + gui.getN3()) - (cRows * cRows);
					if (gui.getcButtons()[0 + pufferPlusN3].getBackground().equals(Color.BLACK)) {
						count++;
					}
					int pufferPlusN2 = (cellPos + gui.getN2()) - (cRows * cRows);
					if (gui.getcButtons()[0 + pufferPlusN2].getBackground().equals(Color.BLACK)) {
						count++;
					}
					int pufferPlusN1 = (cellPos + gui.getN1()) - (cRows * cRows);
					if (gui.getcButtons()[0 + pufferPlusN1].getBackground().equals(Color.BLACK)) {
						count++;
					}
				}

				if ((cellPos > (((cRows * cRows) - 1) - gui.getN3())) && (cellPos <= (((cRows * cRows) - 1) - gui.getN4())) ) {
					int pufferPlusN3 = (cellPos + gui.getN3()) - (cRows * cRows);
					if (gui.getcButtons()[0 + pufferPlusN3].getBackground().equals(Color.BLACK)) {
						count++;
					}
					int pufferPlusN2 = (cellPos + gui.getN2()) - (cRows * cRows);
					if (gui.getcButtons()[0 + pufferPlusN2].getBackground().equals(Color.BLACK)) {
						count++;
					}
					int pufferPlusN1 = (cellPos + gui.getN1()) - (cRows * cRows);
					if (gui.getcButtons()[0 + pufferPlusN1].getBackground().equals(Color.BLACK)) {
						count++;
					}
					if (gui.getcButtons()[cellPos + gui.getN4()].getBackground().equals(Color.BLACK)) {
						count++;
					}
				}

				if ((cellPos > (((cRows * cRows) - 1) - gui.getN2())) && (cellPos <= (((cRows * cRows) - 1) - gui.getN3()))) {
					int pufferPlusN2 = (cellPos + gui.getN2()) - (cRows * cRows);
					if (gui.getcButtons()[0 + pufferPlusN2].getBackground().equals(Color.BLACK)) {
						count++;
					}
					int pufferPlusN1 = (cellPos + gui.getN1()) - (cRows * cRows);
					if (gui.getcButtons()[0 + pufferPlusN1].getBackground().equals(Color.BLACK)) {
						count++;
					}
					if (gui.getcButtons()[cellPos + gui.getN4()].getBackground().equals(Color.BLACK)) {
						count++;
					}
					if (gui.getcButtons()[cellPos + gui.getN3()].getBackground().equals(Color.BLACK)) {
						count++;
					}
				}

				if ((cellPos > (((cRows * cRows) - 1) - gui.getN1())) && (cellPos <= (((cRows * cRows) - 1) - gui.getN2()))) {
					int pufferPlusN1 = (cellPos + gui.getN1()) - (cRows * cRows);
					if (gui.getcButtons()[0 + pufferPlusN1].getBackground().equals(Color.BLACK)) {
						count++;
					}
					if (gui.getcButtons()[cellPos + gui.getN4()].getBackground().equals(Color.BLACK)) {
						count++;
					}
					if (gui.getcButtons()[cellPos + gui.getN3()].getBackground().equals(Color.BLACK)) {
						count++;
					}
					if (gui.getcButtons()[cellPos + gui.getN2()].getBackground().equals(Color.BLACK)) {
						count++;
					}
				}

				if (gui.getcButtons()[cellPos - gui.getN1()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos - gui.getN2()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos - gui.getN3()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos - gui.getN4()].getBackground().equals(Color.BLACK)) {
					count++;
				}

			} else {

				if (gui.getcButtons()[cellPos - gui.getN1()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos - gui.getN2()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos - gui.getN3()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos - gui.getN4()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos + gui.getN4()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos + gui.getN3()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos + gui.getN2()].getBackground().equals(Color.BLACK)) {
					count++;
				}
				if (gui.getcButtons()[cellPos + gui.getN1()].getBackground().equals(Color.BLACK)) {
					count++;
				}
			}
		}
		return count;
	}

	private boolean rule1(Cell b, int neighbors) {
		if (neighbors == 3) {
			return true;
		} else {
			return false;
		}
	}

	private boolean rule2(Cell b, int neighbors) {
		if (neighbors < 2) {
			return false;
		} else {
			return true;
		}
	}

	private boolean rule3(Cell b, int neighbors) {
		if (neighbors == 2 || neighbors == 3) {
			return true;
		} else {
			return false;
		}
	}

	private boolean rule4(Cell b, int neighbors) {
		if (neighbors > 3) {
			b.setBackground(Color.LIGHT_GRAY);
			return false;
		} else {
			return true;
		}
	}

	private boolean entscheidungLebend(Cell b, int neighbor) {
		return rule2(b, neighbor) && rule3(b, neighbor) && rule4(b, neighbor);
	}

	private void nextGen(Object o) {
		if (o.equals(gui.getNext())) {
			for (int i = 0; i < gui.getcButtons().length; i++) {
				Cell b = gui.getcButtons()[i];
				int aliveNeighbours = countAliveNeighborhood(b);

				if (b.getBackground().equals(Color.BLACK)) {
					if (entscheidungLebend(b, aliveNeighbours)) {
						b.setAlive(true);
					} else {
						b.setAlive(false);
					}
				}

				if (b.getBackground().equals(Color.LIGHT_GRAY)) {
					if (rule1(b, aliveNeighbours)) {
						b.setAlive(true);
					} else {
						b.setAlive(false);
					}
				}
			}
			draw();
		}
	}

	private void setGenerationAndPopulation() {
		gui.setGenerationCount(gui.getGenerationCount() + 1);
		gui.setPopulationCount(countPopulation());
		gui.getGenerationField().setText("Generation: " + gui.getGenerationCount());
		gui.getPopulationField().setText("Population: " + gui.getPopulationCount());
	}

	private void resetGenerationAndPopulation() {
		gui.setGenerationCount(0);
		gui.setPopulationCount(0);
		gui.getGenerationField().setText("Generation: " + gui.getGenerationCount());
		gui.getPopulationField().setText("Population: " + gui.getPopulationCount());
	}

	private int countPopulation() {
		int pop = 0;
		for (int i = 0; i < gui.getcButtons().length; i++) {
			Cell b = gui.getcButtons()[i];
			if (b.isAlive()) {
				pop++;
			}
		}
		return pop;
	}

	private synchronized void los(Object o) {
		if (o == gui.getGo()) {
			losT = new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(1);
						gui.getNext().doClick();
						gui.getGo().doClick();
					} catch (InterruptedException e) {
					}
				}
			};
			losT.start();
		}
	}

	@SuppressWarnings("deprecation")
	private void stop(Object o) {
		if (o == gui.getStop()) {
			if (losT != null) {
				losT.stop();
			}
		}
	}

	private void draw() {
		for (int i = 0; i < gui.getcButtons().length; i++) {
			Cell b = gui.getcButtons()[i];
			if (b.isAlive()) {
				b.setBackground(Color.BLACK);
			} else {
				b.setBackground(Color.LIGHT_GRAY);
			}
		}
		setGenerationAndPopulation();
	}

	private void initCell(Object o) {
		for (Cell b : gui.getcButtons()) {
			if (o == b) {
				if (!b.isAlive()) {
					b.setBackground(Color.BLACK);
					b.setAlive(true);
				} else {
					b.setBackground(Color.LIGHT_GRAY);
					b.setAlive(false);
				}

			}
		}
	}

	private void actionOkRandDialog(Object o) {
		if (o == gui.getOkRand()) {
			gui.getDialogRandom().dispose();
			makeRandomUniverse();
		}
	}

	private void actionRandomUniverse(Object o) {
		if (o == gui.getRandStart()) {
			gui.initShowRandDialog();
		}
	}

	private void makeRandomUniverse() {
		if(gui.getRandomStartCells() == 0){
			gui.getNeu().doClick();
			return;
		}
		Random r = new Random();
		int randomInt = 0;
		for (int i = 0; i < gui.getcButtons().length; i++) {
			Cell b = gui.getcButtons()[i];
			randomInt = r.nextInt(101);

			if (randomInt <= gui.getRandomStartCells()) {
				b.setAlive(true);
			} else {
				b.setAlive(false);
			}
		}
		draw();
		gui.setGenerationCount(0);
		gui.getGenerationField().setText("Generation: " + gui.getGenerationCount());
	}

	public void initRandomUniverse(Object o) {
		if (o == gui.getSliderRand()) {
			gui.setRandomStartCells(gui.getSliderRand().getValue());
		}
	}

	public void initUniverse(Object o) {
		if (o == gui.getSlider()) {
			gui.setcRows(gui.getSlider().getValue());
		}
	}

	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public Thread getLosT() {
		return losT;
	}

	public void setLosT(Thread losT) {
		this.losT = losT;
	}

}
