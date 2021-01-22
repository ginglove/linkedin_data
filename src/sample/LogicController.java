package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import automationPackage.Commons.commons_excel;
import sample.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogicController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private TextField txtTotalConnections;
    @FXML
    private TextField totalProcessData;
    @FXML
    private Button btnGetLatest;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage=(Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void getLatestInformationOnAction(ActionEvent event)
    {
        commons_excel commons =new commons_excel();
        Controller control=new Controller();
        String filePath= control.getTxtFilePath();
        String fileSheet=control.getTxtFileSheetName();
        try {
            int totalRows=commons.getTotalRowOfExcel(filePath,fileSheet);
            totalProcessData.setText(String.valueOf(totalRows));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
