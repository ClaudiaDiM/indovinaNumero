package it.polito.tdp.indovinanumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtTentativi; 

    @FXML
    private Button btnProva;

    @FXML
    void doNuova(ActionEvent event) {
    	this.segreto = (int)(Math.random()* NMAX) +1;
    	// math.random mi da un numero compreso tra 0.1 e 0.99 casuale
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	
    	layoutTentativo.setDisable(false);
    	// abilito l'hbox che avevo disattivato;
    	txtRisultato.clear();
    	txtRimasti.setText(Integer.toString(TMAX));

    }

    @FXML
    void doTentativo(ActionEvent event) {
    	// leggere l'input dell'utente VA SEMPRE CONTROLLATO!!! ---> devo sempre controllare che ciò che scrive l'utente sia corretto tramite un try-catch!
    	String st = txtTentativi.getText();
    	int tentativo;
    	try {
    	  tentativo = Integer.parseInt(st);
    	} catch (NumberFormatException e) {
    		txtRisultato.appendText("Devi inserire un numero!\n"); //appendTxt mi inserisce un testo in un'area di testo
    		return;
    	}
    	
    	this.tentativiFatti++;
    	if(tentativo == this.segreto) {
    		txtRisultato.appendText("HAI VINTO! Hai utilizzato " + this.tentativiFatti + " tentativi");
    		layoutTentativo.setDisable(true);
    		this.inGioco = false;
    		return;
    	}
    	
    	if(this.tentativiFatti == TMAX) {
    		txtRisultato.appendText("HAI PERSO! Il numero segreto era" + this.segreto);
    		this.inGioco = false;
    		return;
    	}
    	
    	if(tentativo < this.segreto) {
    		txtRisultato.appendText("Numero troppo basso!\n");
    	}
    	else {
    		txtRisultato.appendText("Numero troppo altro!\n");
    	}
    	
    	txtRimasti.setText(Integer.toString(TMAX - this.tentativiFatti));

    }

    @FXML
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
