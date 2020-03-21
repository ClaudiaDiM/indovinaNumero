package it.polito.tdp.indovinanumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.indovinanumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	// QUANDO CREO UN PACKEGE CON UNA CLASSE DEVO CREARE NEL CONTROLLER IL RIFERIMENTO A QUELLA CLASSE E AL FONDO CREO UNA CLASSE, POI MODIFICO ANCHE IL MODEL
	private Model model; 

	

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
    	//comunico che inizia una nuova partita
    	this.model.nuovaPartita();
    	
    	layoutTentativo.setDisable(false);
    	// abilito l'hbox che avevo disattivato;
    	txtRisultato.clear();
    	txtRimasti.setText(Integer.toString(this.model.getTMAX()));

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
    	
    	int risultato = -1;
    	//devo catturare le eccezioni scatenate dal modello
    	try {
    	risultato = this.model.tentativo(tentativo);
    	} catch (IllegalStateException se) {
    		txtRisultato.appendText(se.getMessage());
    		return;
    	} catch (InvalidParameterException pe) {
    		txtRisultato.appendText(pe.getMessage());
    		return;
    	}
    	if(risultato == 0) {
    		txtRisultato.appendText("Hai Vinto! Hai vinto con" + this.model.getTentativiFatti() + " tentativi");
    	}
    	else if(risultato == -1) {
    		txtRisultato.appendText("Tentativo troppo basso\n");
    	}
    	else {
    		txtRisultato.appendText("Tentativo troppo alto\n");
    	}
    	
    	txtRimasti.setText(Integer.toString(this.model.getTMAX() - this.model.getTentativiFatti()));
    	
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
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
