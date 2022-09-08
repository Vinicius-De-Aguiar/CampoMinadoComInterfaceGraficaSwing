package Minado.Campo.Projeto.Visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Minado.Campo.Projeto.Logica.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel{//serie de botoes//container//colocar todos os botoes
	
	
	public PainelTabuleiro(Tabuleiro tabuleiro) {
		setLayout(getLayout());
		setLayout(new GridLayout(tabuleiro.getLinhas(),tabuleiro.getColunas()));
		
		int total= tabuleiro.getLinhas()*tabuleiro.getColunas();
		
//		for (int i = 0; i < total; i++) {
//			add(new JButton());//cria botoes
//		}
//		
		
		tabuleiro.paraCadaCampo(c->add(new BotaoCampo(c)));
		
		tabuleiro.registrarObservador(e->{
			SwingUtilities.invokeLater(()->{
				
			if(e.isGanhou()) {
				JOptionPane.showMessageDialog(this, "GANHOU :D");
			}else {
				JOptionPane.showMessageDialog(this, "PERDEU D:");
			}
			
			tabuleiro.reiniciar();
			});
		});
		
		
		
	}
	

}
