package application;
import jeu_2048_de_farida.*;


public class Application {
	
	private static FenJeu2048DeFarida f;

	public static void main(String [] args){
		setF(new FenJeu2048DeFarida("2048"));
		
	}

	public static FenJeu2048DeFarida getF() {
		return f;
	}

	public static void setF(FenJeu2048DeFarida f) {
		Application.f = f;
	}
}
