package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Billboard;
import model.InfrDep;

public class ControllerGUI {

	@FXML
	private Pane mainPane;
	
    @FXML
    private TextField dataReader;

//    @FXML
//    private ListView<Billboard> lvBillboards;
    
    @FXML
    private TableView<Billboard> tvBillboards;
    
    @FXML
    private TableColumn<Billboard, String> tcWidth;

    @FXML
    private TableColumn<Billboard, String> tcHeight;

    @FXML
    private TableColumn<Billboard, String> tcInUse;

    @FXML
    private TableColumn<Billboard, String> tcBrand;
    
    @FXML
    private TextField totalBillboards;
    
    private ObservableList<Billboard> observableList;
    
    private InfrDep department;
    
    public ControllerGUI () {
    	
    	department = new InfrDep();
    }

    
    public void startTableView() throws IOException {
    	
    	observableList = FXCollections.observableArrayList(department.getBillboards());
    	
    	tvBillboards.setItems(observableList);
    	tcWidth.setCellValueFactory(new PropertyValueFactory<Billboard, String>("width"));   
    	tcHeight.setCellValueFactory(new PropertyValueFactory<Billboard, String>("height"));
    	tcInUse.setCellValueFactory(new PropertyValueFactory<Billboard, String>("inUse"));
    	tcBrand.setCellValueFactory(new PropertyValueFactory<Billboard, String>("brand"));
    }
	
	@FXML
	void startMenu() throws IOException {
		
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Billboards-menu.fxml"));
    	fxmlloader.setController(this);
    	Parent menu = fxmlloader.load();
    	mainPane.getChildren().setAll(menu);
	}
	
	@FXML
    void close(ActionEvent event) {

		Platform.exit();
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
	void generateBillboard(ActionEvent event) throws IOException {

//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String line = br.readLine();

		String line = dataReader.getText();
		String [] data = line.split("\\++");
		Billboard aBillboard = new Billboard(data[0], data[1], data[2], data[3]);
		
		if(department.addBillboard(aBillboard)) {

			String header = "Billboard created";
			String message = "Billboard created successfully";
			showSuccessDialogue(header, message);
		}
			
		dataReader.setText(null);
	}

	@FXML
	void showBillboards(ActionEvent event) throws IOException {
		
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AllBillboards.fxml"));
		fxmlloader.setController(this);
		Parent allBillboards = fxmlloader.load();
		mainPane.getChildren().setAll(allBillboards);
		
		importData();
		
		String t = String.valueOf(department.getTotalBillboards());
		totalBillboards.setText(t);
	}
	
	@FXML
    void back(ActionEvent event) throws IOException {

		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Billboards-menu.fxml"));
    	fxmlloader.setController(this);
    	Parent menu = fxmlloader.load();
    	mainPane.getChildren().setAll(menu);
    }
	
    @FXML
    void dangerReports(ActionEvent event) {

    }
    
    public void importData() throws IOException {
    	
    	department.importData();
    	startTableView();
    }
    
    public void showSuccessDialogue(String header, String message) {

    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Contact Manager");
    	alert.setHeaderText(header);
    	alert.setContentText(message);
    	alert.showAndWait();
    }

    public void showWarningDialogue(String header, String message) {

    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle("Contact Manager");
    	alert.setHeaderText(header);
    	alert.setContentText(message);
    	alert.showAndWait();
    }
}
