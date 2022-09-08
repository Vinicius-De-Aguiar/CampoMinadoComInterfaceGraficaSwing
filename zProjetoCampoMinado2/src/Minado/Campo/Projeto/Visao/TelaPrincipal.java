package Minado.Campo.Projeto.Visao;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Minado.Campo.Projeto.Logica.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal  extends JFrame{
	
	
	public TelaPrincipal() {//recebido por herança
		
		
		boolean verificador=true;
		while(verificador==true) {
			
			try {
		String l = JOptionPane.showInputDialog("Digita a quantidade de linhas ");
		String c = JOptionPane.showInputDialog("Digite a quantidade de colunas ");
		String m = JOptionPane.showInputDialog("Digite a quantidade de bombas ");
		int linhas=Integer.parseInt(l);
		int colunas=Integer.parseInt(c);
		int minas=Integer.parseInt(m);
		
		
		Tabuleiro tabuleiro= new Tabuleiro(linhas, colunas, minas);
		
		
		add(new PainelTabuleiro(tabuleiro));
		
		
		setTitle("Campo Minado");
		setSize(690,438);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//FECHA O PROCESSO QND X CLICADO
		setVisible(true);
		verificador=false;
		}catch (Exception e) {
			JOptionPane.showInternalMessageDialog(null, "Opa, voce digitou letras onde não deveria,"
					+ " tente denovo");
		}
			
		}
	}
	
	
	
	public static void main(String[] args) {
		new TelaPrincipal();
		
		
	}
	

}
