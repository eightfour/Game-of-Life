package conwaysgameoflife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class GUI {

	private EventHandler evh = new EventHandler(this);
	private JFrame jframe = new JFrame("Conways Spiel des Lebens");
	private JPanel panel1 = new JPanel();
	private JPanel panelC = new JPanel();
	private JPanel panelS = new JPanel();
	private JPanel panelN = new JPanel();
	private JButton neu = new JButton("Neu");
	private JButton next = new JButton("N�chste Generation");
	private JButton go = new JButton("Los");
	private JButton stop = new JButton("Stop");

	private  int generationCount = 0;
	private  int populationCount = 0;
	private JTextField generationField = new JTextField("Generation: " + generationCount);
	private JTextField populationField = new JTextField("Population: " + populationCount);

	private JSlider slider = new JSlider(10, 100, 10);
	private JDialog dialog = new JDialog(jframe, "W�hlen Sie die Gr��e des Universums", true);
	private JButton ok = new JButton("OK");

	private JButton randStart = new JButton("Zufallsstart");
	private int randomStartCells = 31;
	private JDialog dialogRandom = new JDialog(jframe, "W�hlen Sie den Prozentsatz der anfangs lebenden Zellen", true);
	private JButton okRand = new JButton("OK");
	private JSlider sliderRand = new JSlider(0, 100, 31);

	private int cRows = 10;
	private Cell[] cButtons;

	private int n1 = 11;
	private int n2 = 10;
	private int n3 = 9;
	private int n4 = 1;

	public GUI() {
		showDialog();
		initFrame();
		initPanel();
		jframe.setVisible(true);
	}

	private void initRandSlider() {
		sliderRand.addChangeListener(evh);
		sliderRand.setPaintTicks(true);
		sliderRand.setPaintLabels(true);
		sliderRand.setMajorTickSpacing(10);
		dialogRandom.add(sliderRand, BorderLayout.CENTER);
	}

	protected void initShowRandDialog() {
		dialogRandom.setLayout(new BorderLayout());
		initRandSlider();
		JPanel jp = new JPanel();
		okRand.addActionListener(evh);
		okRand.setBackground(Color.GRAY);
		jp.add(okRand);
		dialogRandom.add(jp, BorderLayout.SOUTH);
		dialogRandom.setSize(500, 200);
		dialogRandom.setLocationRelativeTo(null);
		dialogRandom.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		dialogRandom.setVisible(true);

	}

	private void initSlider() {
		slider.addChangeListener(evh);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(10);
		dialog.add(slider, BorderLayout.CENTER);
	}

	private void showDialog() {
		dialog.setLayout(new BorderLayout());
		initSlider();
		JPanel jp = new JPanel();
		ok.addActionListener(evh);
		ok.setBackground(Color.GRAY);
		jp.add(ok);
		dialog.add(jp, BorderLayout.SOUTH);
		dialog.setSize(500, 200);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		dialog.setVisible(true);
	}

	private void initFrame() {
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setPreferredSize(null);
		jframe.setSize(460, 415);
		jframe.setLocationRelativeTo(null);
	}

	private void initPanel() {
		panel1.setLayout(new BorderLayout());
		initPanelNorth();
		initPanelSouth();
		initPanelCenter();
		jframe.add(panel1);
	}

	private void initPanelNorth() {
		panelN.setBackground(Color.DARK_GRAY);
		panelN.setLayout(new GridLayout(2, 1));
		generationField.setBackground(Color.GRAY);
		populationField.setBackground(Color.GRAY);
		generationField.setPreferredSize(new Dimension(1000, 20));
		populationField.setPreferredSize(new Dimension(1000, 20));
		generationField.setEditable(false);
		populationField.setEditable(false);
		panelN.add(generationField);
		panelN.add(populationField);

		panel1.add(panelN, BorderLayout.NORTH);
	}

	private void initPanelSouth() {
		panelS.setBackground(Color.DARK_GRAY);
		neu.setBackground(Color.GRAY);
		randStart.setBackground(Color.GRAY);
		next.setBackground(Color.GRAY);
		go.setBackground(Color.GRAY);
		stop.setBackground(Color.GRAY);
		stop.addActionListener(evh);
		go.addActionListener(evh);
		neu.addActionListener(evh);
		randStart.addActionListener(evh);
		next.addActionListener(evh);
		panelS.add(neu);
		panelS.add(randStart);
		panelS.add(next);
		panelS.add(go);
		panelS.add(stop);
		panel1.add(panelS, BorderLayout.SOUTH);

	}

	private void initPanelCenter() {
		n1 = cRows + 1;
		n2 = cRows;
		n3 = cRows - 1;
		panelC.setLayout(new GridLayout(cRows, cRows));
		cButtons = new Cell[cRows * cRows];
		for (int i = 0; i < cButtons.length; i++) {
			cButtons[i] = new Cell();
				cButtons[i].setBackground(Color.LIGHT_GRAY);
			cButtons[i].addActionListener(evh);
			panelC.add(cButtons[i]);
		}
		panel1.add(panelC, BorderLayout.CENTER);
	}

	public EventHandler getEvh() {
		return evh;
	}

	public void setEvh(EventHandler evh) {
		this.evh = evh;
	}

	public JFrame getJframe() {
		return jframe;
	}

	public void setJframe(JFrame jframe) {
		this.jframe = jframe;
	}

	public JPanel getPanel1() {
		return panel1;
	}

	public void setPanel1(JPanel panel1) {
		this.panel1 = panel1;
	}

	public JPanel getPanelC() {
		return panelC;
	}

	public void setPanelC(JPanel panelC) {
		this.panelC = panelC;
	}

	public JPanel getPanelS() {
		return panelS;
	}

	public void setPanelS(JPanel panelS) {
		this.panelS = panelS;
	}

	public JButton getNeu() {
		return neu;
	}

	public void setNeu(JButton neu) {
		this.neu = neu;
	}

	public JButton getNext() {
		return next;
	}

	public void setNext(JButton next) {
		this.next = next;
	}

	public JSlider getSlider() {
		return slider;
	}

	public void setSlider(JSlider slider) {
		this.slider = slider;
	}

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public int getcRows() {
		return cRows;
	}

	public void setcRows(int cRows) {
		this.cRows = cRows;
	}

	public Cell[] getcButtons() {
		return cButtons;
	}

	public void setcButtons(Cell[] cButtons) {
		this.cButtons = cButtons;
	}

	public JButton getOk() {
		return ok;
	}

	public void setOk(JButton ok) {
		this.ok = ok;
	}

	public int getN1() {
		return n1;
	}

	public void setN1(int n1) {
		this.n1 = n1;
	}

	public int getN2() {
		return n2;
	}

	public void setN2(int n2) {
		this.n2 = n2;
	}

	public int getN3() {
		return n3;
	}

	public void setN3(int n3) {
		this.n3 = n3;
	}

	public int getN4() {
		return n4;
	}

	public void setN4(int n4) {
		this.n4 = n4;
	}

	public JButton getGo() {
		return go;
	}

	public void setGo(JButton go) {
		this.go = go;
	}

	public JButton getStop() {
		return stop;
	}

	public void setStop(JButton stop) {
		this.stop = stop;
	}

	public JPanel getPanelN() {
		return panelN;
	}

	public void setPanelN(JPanel panelN) {
		this.panelN = panelN;
	}

	public JTextField getGenerationField() {
		return generationField;
	}

	public void setGenerationField(JTextField generationField) {
		this.generationField = generationField;
	}

	public synchronized int getGenerationCount() {
		return generationCount;
	}

	public synchronized void setGenerationCount(int generationCount) {
		this.generationCount = generationCount;
	}

	public JTextField getPopulationField() {
		return populationField;
	}

	public void setPopulationField(JTextField populationField) {
		this.populationField = populationField;
	}

	public synchronized int getPopulationCount() {
		return populationCount;
	}

	public synchronized void setPopulationCount(int populationCount) {
		this.populationCount = populationCount;
	}

	public JButton getRandStart() {
		return randStart;
	}

	public void setRandStart(JButton randStart) {
		this.randStart = randStart;
	}

	public int getRandomStartCells() {
		return randomStartCells;
	}

	public void setRandomStartCells(int randomStartCells) {
		this.randomStartCells = randomStartCells;
	}

	public JDialog getDialogRandom() {
		return dialogRandom;
	}

	public void setDialogRandom(JDialog dialogRandom) {
		this.dialogRandom = dialogRandom;
	}

	public JButton getOkRand() {
		return okRand;
	}

	public void setOkRand(JButton okRand) {
		this.okRand = okRand;
	}

	public JSlider getSliderRand() {
		return sliderRand;
	}

	public void setSliderRand(JSlider sliderRand) {
		this.sliderRand = sliderRand;
	}

	public static void main(String[] args) {

		new GUI();
	}

}
