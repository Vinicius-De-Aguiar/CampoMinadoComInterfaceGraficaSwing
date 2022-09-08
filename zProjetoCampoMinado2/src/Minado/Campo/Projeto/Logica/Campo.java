package Minado.Campo.Projeto.Logica;

import java.util.ArrayList;
import java.util.List;

public class Campo {

	private final int linha;
	private final int coluna;

	private boolean aberto;// por padrao começa falso
	private boolean minado;
	private boolean marcado;// campo aberto

	private List<Campo> vizinhos = new ArrayList<>();
	private List<CampoObservador> observadores = new ArrayList<>();
	
	
	

	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;

	}
	
	public void registrarObservador(CampoObservador observador) {//ADD OBSERVADORES
		observadores.add(observador);
	}
	
	private void notificarObservadores(CampoEvento evento) {
		observadores.stream().forEach(o->o.eventoOcorreu(this, evento));//PASSA O OBJETO ATUAL( campo)
	}
	

	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;// se true significa q tem diagonal //diferença=1
		boolean colunaDiferente = coluna != vizinho.coluna;// se true significa q tem diagonal //diferença=1
		boolean diagonal = linhaDiferente && colunaDiferente;// se tiver na diagonal os 2 sao diferentes //diferença=2

		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;

		if (deltaGeral == 1 && !diagonal) {// diagonal false
			vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {// diagonal true
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}

	}

	public void alternarMarcacao() {// protege um campo, para nao ser aberto
		if (!aberto) {
			marcado = !marcado;
		
			if(marcado) {
				notificarObservadores(CampoEvento.MARCAR);
			}else {
				notificarObservadores(CampoEvento.DESMARCAR);
			}
		}
	}

	public boolean abrir() {
		if (!aberto && !marcado) {
			
			if (minado) {
				notificarObservadores(CampoEvento.EXPLODIR);
				return true;
			}
			setAberto(true);
			
			if (vizinhancaSegura()) {// enqaunto vizinhacaSegura continue expandido
				vizinhos.forEach(v -> v.abrir());// abre enquanto a vizinhança for segura
			}
			return true;

		} else {
			return false;
		}
	}

	public boolean vizinhancaSegura() {

		return vizinhos.stream().noneMatch(v -> v.minado);// se falso, pelo menso 1 vizinho tem bomba
	}

	void minar() {
		minado = true;
	}

	public boolean isMinado() {
		return minado;
	}
		
	public boolean isMarcado() {
		return marcado;
	}

	
		
	 void setAberto(boolean aberto) {
		this.aberto = aberto;
		
		if(aberto) {
			notificarObservadores(CampoEvento.ABRIR);
		}
	}

	public boolean isAberto() {
		return aberto;
	}

	public boolean isFechado() {
		return !isAberto();
	}

	// linhas e colunas
	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	public int minasNaVizinhanca() {
		return (int)vizinhos.stream().filter(v->v.minado).count();
	}
	
	void reiniciar() {
		aberto=false;
		minado=false;
		marcado=false;
		notificarObservadores(CampoEvento.REINICIAR);
	}
	
	
	

}
