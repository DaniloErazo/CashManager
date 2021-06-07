package thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.CashManager;
import ui.MainController;

public class ExportData extends Thread{
	
	private CashManager cashManager;

	private PrintWriter pWriter;
	
	public ExportData(CashManager ppal, MainController GUI, File filePath) throws FileNotFoundException {
		cashManager=ppal;

		pWriter = new PrintWriter(filePath);
	}
	
	public void run() {
		pWriter.println("Monto" + "," + "Categoría" + "," + "Fecha" + "," + "Descripción");
		
		try {
			cashManager.exportAllData(pWriter);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		Platform.runLater(new Thread() {
			
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Exporte exitoso");
				alert.setHeaderText(null);
				alert.setContentText("Todos los datos han sido exportados con écito");
				alert.showAndWait();
			}
		});
		
	}

}
