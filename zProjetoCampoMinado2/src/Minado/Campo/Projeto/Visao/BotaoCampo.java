package Minado.Campo.Projeto.Visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import Minado.Campo.Projeto.Logica.Campo;
import Minado.Campo.Projeto.Logica.CampoEvento;
import Minado.Campo.Projeto.Logica.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador,MouseListener{
	
	private final Color BG_PADAO = new Color(51,153,255);
	private final Color BG_MARCAR = new Color(153,0,0);
	private final Color BG_EXPLODIR= new Color(0,0,0);
	private final Color TEXTO_VERDE = new Color(0,100,0);
	
	
	private Campo campo;
	
	public BotaoCampo(Campo campo) {
		this.campo=campo;
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(BG_PADAO);
		setText("??");
		setOpaque(true);
		
		
		addMouseListener(this);//a propria classe implementa
		campo.registrarObservador(this);
		
	}

	@Override
	public void eventoOcorreu(Campo c, CampoEvento e) {
		switch(e) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		
		case MARCAR:
			aplicarEstiloMarcar();
			break;
			
		case EXPLODIR:
			aplicarEstiloeExplodir();
			break;
		default:
			aplicarEstiloPadrao();
		}
		
	}

	private void aplicarEstiloPadrao() {
		setBackground(BG_PADAO);
		setBorder(BorderFactory.createBevelBorder(0));
		setForeground(Color.BLACK);
		setText("??");
		
	}

	private void aplicarEstiloeExplodir() {
		setBackground(BG_EXPLODIR);
		setForeground(Color.RED);
		setText("X");
	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCAR);
		setForeground(Color.WHITE);
		setText("M");
	}

	private void aplicarEstiloAbrir() {
		// TODO Auto-generated method stub
		setBackground(BG_PADAO);
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(campo.isMinado()) {
			setBackground(BG_EXPLODIR);
			return;
		}
		
		switch (campo.minasNaVizinhanca()) {
		case 1: 
			setForeground(TEXTO_VERDE);
			
			break;
		case 2:
			setForeground(Color.DARK_GRAY);
			break;
		
		case 3:
			setForeground(Color.CYAN);
			break;
			
		case 4:
			setForeground(Color.magenta);
			break;
			
		case 5:
			setForeground(Color.YELLOW);
			break;
			
		case 6:
			setForeground(Color.RED);
			break;
		default:
			setForeground(Color.PINK);
			
		}
		
		String valor=!campo.vizinhancaSegura()? campo.minasNaVizinhanca()+"":"㋡";//mostra o numero de bombas
		setText(valor);
		
	}
	
	//interface dos eventos do mouse
	
	@Override
	public void mousePressed(MouseEvent e) {//clic mouse
		if(e.getButton()==1) {
			campo.abrir();
			System.out.println("Botao esquerddo");
		}else {
			campo.alternarMarcacao();
			System.out.println("outro botao");
		}
		
	}
	

	public void mouseClicked(MouseEvent e) {
	}
	

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {	
	}
	 
	

}
