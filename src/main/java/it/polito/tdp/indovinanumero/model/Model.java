package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	
	private Set<Integer> tentativi; //set è un contenitore tipo le liste (è comodo se voglio cercare se all'interno di questo contenitore c'è un certo elemento)
	
	
	
	public Model() {
		this.inGioco = false;
		this.tentativiFatti = 0;
	}
	
	public void nuovaPartita() {
		this.segreto = (int)(Math.random()* NMAX) +1;
    	// math.random mi da un numero compreso tra 0.1 e 0.99 casuale
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	this.tentativi = new HashSet<Integer>();
	}
	
	public int tentativo(int tentativo) {
		//controllo se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è gia terminata");
		}
		
		// controllo l?input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero tra 1 e " + NMAX + "\ndiverso da quello inserito prima");
		}
		
		this.tentativiFatti++;
		this.tentativi.add(tentativo);
		
		if(this.tentativiFatti == TMAX) {
			this.inGioco = false;
		}
		
		if(tentativo == this.segreto){
			this.inGioco = false;
			return 0;
		}
		
		if(tentativo < this.segreto) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	private boolean tentativoValido(int tentativo) {
		if(tentativo < 1 || tentativo > NMAX) {
			return false;
		}
		else {
			if(this.tentativi.contains(tentativo)) {
				return false;
			}
			else
				return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	

}
