package it.polito.tdp.totocalcio.model;

import java.util.*;

public class Ricerca {
	
	private Pronostico pronostico;
	private int N;
	private List<Risultato> soluzione;

	public List<Risultato> cerca(Pronostico pronostico) {
		// preparo le strutture dati dove lavora la funzione ricorsiva
		this.pronostico = pronostico;
		this.N = pronostico.size();
		
		List<RisultatoPartita> parziale = new ArrayList<>();
		int livello = 0;
		
		this.soluzione = new ArrayList<>();
		
		ricorsiva(parziale, livello);
		
		return this.soluzione;
	}
	
	private void ricorsiva(List<RisultatoPartita> parziale, int livello) {
		// Caso terminale?
		if(livello==N) {
			// questa soluzione parziale è una soluzione completa
			// System.out.println(parziale);
			this.soluzione.add(new Risultato(parziale));
		}
		else {
			// Caso generale
			PronosticoPartita pp = pronostico.get(livello);
			
			// pp sono i sotto-problemi da provare
			for(RisultatoPartita ris: pp.getRisultati()) {
				// provo a mettere 'ris' nella posizione 'livello' della soluzione parziale
				
				// costruzione della soluzione parziale
				parziale.add(ris);
				// chiamata ricorsiva
				ricorsiva(parziale, livello+1);
				// backtracking
				parziale.remove(parziale.size()-1);
			}
		}
	}

}
