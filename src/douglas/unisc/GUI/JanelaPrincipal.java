package douglas.unisc.GUI;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import ADReNA_API.Data.DataSet;
import ADReNA_API.Data.DataSetObject;
import ADReNA_API.NeuralNetwork.Backpropagation;

public class JanelaPrincipal extends JFrame {
	
	private JPanel contentPane;
	
	JLabel lblImage = new JLabel("");


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal frame = new JanelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReconhecimentoDeOvinos = new JLabel("Reconhecimento de Ovinos ");
		lblReconhecimentoDeOvinos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReconhecimentoDeOvinos.setBounds(174, 11, 247, 25);
		contentPane.add(lblReconhecimentoDeOvinos);
		
		JLabel lblNewLabel = new JLabel("Tamanho da cabe\u00E7a");
		lblNewLabel.setBounds(10, 51, 135, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox cbTamanhoCabeca = new JComboBox();
		cbTamanhoCabeca.setBounds(219, 47, 176, 20);
		cbTamanhoCabeca.addItem("00");
		cbTamanhoCabeca.addItem("01" );
		cbTamanhoCabeca.addItem("10" );
		contentPane.add(cbTamanhoCabeca);
		
		JLabel lblFormatoDaCabea = new JLabel("Formato da Cabe\u00E7a");
		lblFormatoDaCabea.setBounds(10, 76, 135, 14);
		contentPane.add(lblFormatoDaCabea);
		
		JComboBox cbFormatoCabeca = new JComboBox();
		cbFormatoCabeca.setBounds(219, 73, 176, 20);
		cbFormatoCabeca.addItem("00");
		cbFormatoCabeca.addItem("01");
		cbFormatoCabeca.addItem("10");
		contentPane.add(cbFormatoCabeca);
		
		JLabel lblCorDaFace = new JLabel("Cor da face");
		lblCorDaFace.setBounds(10, 101, 135, 14);
		contentPane.add(lblCorDaFace);
		
		JComboBox cbCorDaFace = new JComboBox();
		cbCorDaFace.setBounds(219, 98, 176, 20);
		cbCorDaFace.addItem("0");
		cbCorDaFace.addItem("1");
		contentPane.add(cbCorDaFace);
		
		JLabel lblPeito = new JLabel("Peito");
		lblPeito.setBounds(10, 126, 135, 14);
		contentPane.add(lblPeito);
		
		JComboBox cbFormatoDoPeito = new JComboBox();
		cbFormatoDoPeito.setBounds(219, 123, 176, 20);
		cbFormatoDoPeito.addItem("0");
		cbFormatoDoPeito.addItem("1");
		contentPane.add(cbFormatoDoPeito);
		
		JLabel lblComprimentoDosMembros = new JLabel("Comprimento dos membros");
		lblComprimentoDosMembros.setBounds(10, 151, 150, 14);
		contentPane.add(lblComprimentoDosMembros);
		
		JComboBox cbComMembros = new JComboBox();
		cbComMembros.setBounds(219, 147, 176, 20);
		cbComMembros.addItem("00");
		cbComMembros.addItem("01");
		cbComMembros.addItem("10");
		contentPane.add(cbComMembros);
		
		JLabel lblRobustezDosMembros = new JLabel("Robustez dos membros");
		lblRobustezDosMembros.setBounds(10, 179, 135, 14);
		contentPane.add(lblRobustezDosMembros);
		
		JComboBox cbRobustezMembros = new JComboBox();
		cbRobustezMembros.setBounds(219, 173, 176, 20);
		cbRobustezMembros.addItem("00");
		cbRobustezMembros.addItem("01");
		cbRobustezMembros.addItem("10");
		contentPane.add(cbRobustezMembros);
		
		JLabel lblCorDosCascos = new JLabel("Cor dos cascos");
		lblCorDosCascos.setBounds(10, 204, 176, 14);
		contentPane.add(lblCorDosCascos);
		
		JComboBox cbCorCascos = new JComboBox();
		cbCorCascos.setBounds(219, 198, 176, 20);
		cbCorCascos.addItem("00");
		cbCorCascos.addItem("01");
		cbCorCascos.addItem("11");
		contentPane.add(cbCorCascos);
		
		JLabel lblEntrada = new JLabel("Entradas:");
		lblEntrada.setBounds(174, 375, 221, 14);
		contentPane.add(lblEntrada);
		
		
		JButton btnNewButton = new JButton("Reconhecer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String o = cbTamanhoCabeca.getSelectedItem().toString()+cbFormatoCabeca.getSelectedItem().toString()+cbCorDaFace.getSelectedItem().toString()+
						cbFormatoDoPeito.getSelectedItem().toString()+cbComMembros.getSelectedItem().toString()+cbRobustezMembros.getSelectedItem().toString()
						+cbCorCascos.getSelectedItem().toString();
				double[]conjunto = new double[12];
				for (int i = 0; i < o.length(); i++) {
					String a = String.valueOf(o.charAt(i));
					conjunto[i]=Double.parseDouble(a);					
				}
				treinamento(conjunto);		
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						lblEntrada.setText("Entrada: "+o);
					}
				});
				t.start();	
			}
		});
		btnNewButton.setBounds(10, 371, 150, 23);
		contentPane.add(btnNewButton);
		//adicionando imagem ao jlabel
		
		JTextPane txtpnTamanhoDaCabea = new JTextPane();
		txtpnTamanhoDaCabea.setFont(new Font("Arial", Font.BOLD, 11));
		txtpnTamanhoDaCabea.setBackground(Color.LIGHT_GRAY);
		txtpnTamanhoDaCabea.setText("Tamanho da cabe\u00E7a : Pequena - 00, Media -01, Grande - 10;\r\nFormato da Cabe\u00E7a: Curta - 00, Mediana - 01, Comprida - 10;\r\nCor da Face: Branca - 0,\t Preta - 1;\r\nPeito: Estreito - 0, Largo - 1;\r\nComp. membros: Curtos - 00, M\u00E9dianos - 01, Compridos - 10;\r\nRobustez membros : Finos - 00, Medianos - 01, Grossos - 10;\r\nCor dos cascos: Brancos - 00, Pretos - 01, Amarelos - 11;");
		txtpnTamanhoDaCabea.setBounds(10, 229, 385, 131);
		contentPane.add(txtpnTamanhoDaCabea);
		
		
		
		JLabel lblNmeroMxDe = new JLabel("N\u00BA m\u00E1x. de itera\u00E7\u00F5es: 1000");
		lblNmeroMxDe.setBounds(405, 363, 167, 14);
		contentPane.add(lblNmeroMxDe);
		
		JLabel lblTxDeAprendizado = new JLabel("Tx. de Aprendizado : 0,003");
		lblTxDeAprendizado.setBounds(405, 380, 150, 14);
		contentPane.add(lblTxDeAprendizado);
		
		JLabel lblTxDeErro = new JLabel("Tx. de Erro: 0,0002");
		lblTxDeErro.setBounds(575, 363, 108, 14);
		contentPane.add(lblTxDeErro);
		
		JLabel lblNewLabel_1 = new JLabel("Camadas Internas: [15][15][15][15]");
		lblNewLabel_1.setBounds(573, 380, 201, 14);
		contentPane.add(lblNewLabel_1);
			
	}
	
	
	public void treinamento(double[] conjunto) {
		int[] camadasIntermediarias = new int[4];
		camadasIntermediarias[0]=15;
		camadasIntermediarias[1]=15;
		camadasIntermediarias[2]=15;
		camadasIntermediarias[3]=15;
		Backpropagation RNA = new Backpropagation(12,3,camadasIntermediarias);
		RNA.SetMaxIterationNumber(10000);
		RNA.SetErrorRate(0.0002);
		RNA.SetLearningRate(0.03);
		DataSet trainingDataSet = new DataSet(12,3);
		try {
			trainingDataSet.Add(new DataSetObject(new double[] {0,1,0,0,0,1,0,1,1,0,0,0}, new double[] {0,0,0}));
			trainingDataSet.Add(new DataSetObject(new double[] {1,0,1,0,1,1,0,0,1,0,0,0}, new double[] {0,0,1}));
			trainingDataSet.Add(new DataSetObject(new double[] {0,0,1,0,0,1,1,0,0,0,0,1}, new double[] {0,1,0}));
			trainingDataSet.Add(new DataSetObject(new double[] {0,1,0,1,0,0,1,0,0,0,0,0}, new double[] {0,1,1}));
			trainingDataSet.Add(new DataSetObject(new double[] {0,1,1,0,0,0,1,0,1,0,1,1}, new double[] {1,0,0}));
			RNA.Learn(trainingDataSet);
			double[] res =RNA.Recognize(conjunto);
			res = normaliza(res);
			final int[] intArray = new int[res.length];
			for (int i = 0; i < intArray.length; i++) {
				intArray[i] = (int)res[i];
			}
			System.out.println(intArray[0]);
			System.out.println(intArray[1]);
			System.out.println(intArray[2]);	
			
			addImagem(intArray);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double[] normaliza(double[] res) {
		for (int i = 0; i < res.length; i++) {
			if(res[i]> 0.7 ) {
				res[i]=1;
			}else {
				res[i]=0;
			}
		}	
		return res;
	}
	
	public void addImagem(int[] result) {
		int[] merino = {1,0,0};
		int[] ideal = {0,0,0};
		int[] suffolk = {0,0,1};
		int[] karakul = {0,1,0};
		int[] crioula = {0,1,1};
		
		lblImage.setBounds(405, 47, 369, 305);
		contentPane.add(lblImage);	
		if (Arrays.equals(result, crioula)) {
			lblImage.setIcon(new ImageIcon("C:\\Users\\Douglas\\eclipse-workspace\\IAAOvinos\\crioula.jpeg"));
		}else if (Arrays.equals(result, ideal)) {
			lblImage.setIcon(new ImageIcon("C:\\Users\\Douglas\\eclipse-workspace\\IAAOvinos\\ideal.jpg"));
		}else if (Arrays.equals(result, karakul)) {
			lblImage.setIcon(new ImageIcon("C:\\Users\\Douglas\\eclipse-workspace\\IAAOvinos\\karakul.jpg"));
		}else if (Arrays.equals(result, merino)){
			lblImage.setIcon(new ImageIcon("C:\\Users\\Douglas\\eclipse-workspace\\IAAOvinos\\merinoAustraliano.jpg"));
		}else if (Arrays.equals(result, suffolk)) {
			lblImage.setIcon(new ImageIcon("C:\\Users\\Douglas\\eclipse-workspace\\IAAOvinos\\suffolk.jpg"));
		}
	}
}
