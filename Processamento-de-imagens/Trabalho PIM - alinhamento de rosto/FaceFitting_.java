import ij.*;
import ij.gui.*;
import ij.process.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import ij.text.TextWindow;

public class FaceFitting_ extends Dialog implements WindowListener {
	private Point leftSP; 	// left selected point - orelha esquerda
	private Point rightSP;  // right selected point - orelha direita
	private Point topSP;	// top selected point - testa
	private Point bottomSP;	// bottom selected point - queixo

	private ImagePlus imp;

	private int newImageWidth;
	private int newImageHeight;

	private JTextField heightTF;

	public FaceFitting_() {
		super(new Frame(), "Face Fitting");
		this.setPreferredSize(new Dimension(550, 250));
		this.addWindowListener(this);

		createComponents();

		this.pack();
		GUI.center(this);
		this.setVisible(true);
	}

	private void createComponents() {

		Panel panel = new Panel();
		panel.setLayout(null);

		JLabel descLabel = new JLabel();
		descLabel.setText("<html>Selecione 4 pontos na imagem do rosto " +
		                  "na ordem descrita a seguir,<br> informe a altura da imagem resultante" +
		                  " e depois clique em \"Ajustar face\":<br><br>1) extremidade direita do rosto, na altura média da orelha direita" +
		                  "<br>2) extremidade esquerda do rosto, na altura média da orelha esquerda" +
		                  "<br>3) extremidade superior do rosto, ponto central da testa" +
		                  "<br>4) extremidade inferior do rosto, ponto central do queixo.</html>");

		descLabel.setBounds(20, 10, 500, 140);
		panel.add(descLabel);
		JButton button = new JButton("Ajustar Face");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelectedPixel()) runTransformations();
			}
		});
		button.setBounds(272, 170, 130, 20);
		panel.add(button);

		JLabel heightLabel = new JLabel("Altura:");
		heightTF = new JTextField("350");

		heightLabel.setBounds(107, 170, 80, 20);
		heightTF.setBounds(158, 170, 50, 20);

		panel.add(heightLabel);
		panel.add(heightTF);

		this.add(panel);
	}

	private Point calculateUpperLeftCorner(double horizontalM) {
		double verticalM = - 1 / horizontalM;

		double x = (topSP.y + verticalM * rightSP.x - horizontalM * topSP.x - rightSP.y) / (verticalM - horizontalM);
		double y = horizontalM * (x - topSP.x) + topSP.y;

		return new Point((int) Math.round(x), (int) Math.round(y));
	}

	private void runTransformations() {
		ColorProcessor cp = (ColorProcessor) imp.getProcessor();

		double horizontalM = (rightSP.y - leftSP.y) / (double) (rightSP.x - leftSP.x);
		double angle = Math.atan(horizontalM);

		double sinAngle = Math.sin(angle);
		double cosAngle = Math.cos(angle);

		Point upperLeftCornerPoint = calculateUpperLeftCorner(horizontalM);

		int width = (int) Math.round((Math.cos(-angle) * leftSP.x - Math.sin(-angle) * leftSP.y)
		                             - (Math.cos(angle) * rightSP.x - Math.sin(-angle) * rightSP.y));
		int height = (int) (Math.round(Math.sin(-angle) * bottomSP.x + Math.cos(-angle) * bottomSP.y)
		                    - Math.round(Math.sin(-angle) * topSP.x + Math.cos(-angle) * topSP.y));

		double div = (double)width/height;
		newImageWidth = (int)Math.round(newImageHeight*div);

		int nx = cp.getWidth();
		int ny = cp.getHeight();

		int dx = upperLeftCornerPoint.x;
		int dy = upperLeftCornerPoint.y;

		ColorProcessor newCp = new ColorProcessor(newImageWidth, newImageHeight);
		for (int x = 0; x < newImageWidth; x++) {
			for (int y = 0; y < newImageHeight; y++) {
				double xScale = width / (double) newImageWidth;
				double yScale = height / (double) newImageHeight;

				int []rgb1;
				int []rgb2;
				int []rgb3;
				int []rgb4;

				double newX = xScale * (x * cosAngle - y * sinAngle) + dx;
				double newY = yScale * (x * sinAngle + y * cosAngle) + dy;

				int x0, y0;
				x0 = (int) Math.floor(newX);
				y0 = (int) Math.floor(newY);
				double a = newX - x0;
				double b = newY - y0;

				rgb1 = cp.getPixel(x0, y0, null);
				rgb2 = cp.getPixel(x0 + 1, y0, null);
				rgb3 = cp.getPixel(x0, y0 + 1, null);
				rgb4 = cp.getPixel(x0 + 1, y0 + 1, null);

				for (int i = 0; i < 3; i++) {
					rgb1[i] = (int) (rgb1[i] + a * (rgb2[i] - rgb1[i]));
					rgb2[i] = (int) (rgb3[i] + a * (rgb4[i] - rgb3[i]));
				}

				for (int i = 0; i < 3; i++) {
					rgb1[i] = (int) (rgb1[i] + b * (rgb2[i] - rgb1[i]));
				}

				newCp.putPixel(x, y, rgb1);
			}
		}

		ImagePlus newImp = new ImagePlus("Imagem transformada", newCp);
		newImp.show();
	}

	private boolean getSelectedPixel() {
		imp = WindowManager.getCurrentImage();
		try {
			String heightString = heightTF.getText();

			for (int i = 0; i < heightString.length(); i++) {
				if (!Character.isDigit(heightString.charAt(i))) throw new Exception("Valor de altura inválido");
			}

			newImageHeight = Integer.parseInt(heightString);

			if (imp == null) throw new Exception("Não há imagem aberta.");
			Roi roi = imp.getRoi();
			if (roi == null) throw new Exception("Nenhuma seleção encontrada. Devem ser selecionados quatro pontos, nesta ordem:\n1) extremidade direita do rosto, na altura média da orelha direita" +
				                                     "\n2) extremidade esquerda do rosto, na altura média da orelha esquerda" +
				                                     "\n3) extremidade superior do rosto, ponto central da testa" +
				                                     "\n4) extremidade inferior do rosto, ponto central do queixo.");
			if (roi.getType() != 10) throw new Exception("Tipo de seleção deve ser Ponto. Atual é "
				        + roi.getTypeAsString() + ".");

			FloatPolygon fp = roi.getFloatPolygon();
			int n = fp.npoints;
			if (n != 4) throw new Exception("Devem ser selecionados quatro pontos, nesta ordem:\n1) extremidade direita do rosto, na altura média da orelha direita" +
				                                "\n2) extremidade esquerda do rosto, na altura média da orelha esquerda" +
				                                "\n3) extremidade superior do rosto, ponto central da testa" +
				                                "\n4) extremidade inferior do rosto, ponto central do queixo.");
			float[] xp = fp.xpoints;
			float[] yp = fp.ypoints;

			rightSP = new Point((int) xp[0], (int) yp[0]);
			leftSP = new Point((int) xp[1], (int) yp[1]);
			topSP = new Point((int) xp[2], (int) yp[2]);
			bottomSP = new Point((int) xp[3], (int) yp[3]);

		} catch (Exception e) {
			IJ.error("Erro na captura das coordenadas", e.getMessage());
			return false;
		}
		return true;
	}

	public void windowClosing(WindowEvent e) 		{
		dispose();
	}

	public void windowActivated(WindowEvent e) 		{}
	public void windowClosed(WindowEvent e) 		{}
	public void windowDeactivated(WindowEvent e) 	{}
	public void windowDeiconified(WindowEvent e) 	{}
	public void windowIconified(WindowEvent e) 		{}
	public void windowOpened(WindowEvent e) 		{}
}