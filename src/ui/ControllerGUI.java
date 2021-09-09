package ui;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Pane;

public class ControllerGUI {

	@FXML
	private Pane mainPane;
	
	@FXML
	void startMenu() throws IOException {
		
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Billboards-menu.fxml"));
    	fxmlloader.setController(this);
    	Parent menu = fxmlloader.load();
    	mainPane.getChildren().setAll(menu);
	}
	
	@FXML
    void addBillboard(ActionEvent event) throws IOException {

//		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("NewBillboard.fxml"));
//		fxmlloader.setController(this);
//		Parent menu = fxmlloader.load();
//		mainPane.getChildren().setAll(menu);
		
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("NewBillboard.fxml"));
		fxmlloader.setController(this);
		DialogPane dialoguePane = fxmlloader.load();
		
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.setDialogPane(dialoguePane);
		dialog.setTitle("New billboard");
		
//		Optional<ButtonType> clickedButton = dialog.showAndWait();
		dialog.showAndWait();
    }

    @FXML
    void dangerReports(ActionEvent event) {

    }

    @FXML
    void showBillboards(ActionEvent event) {

    }
    
    
}
