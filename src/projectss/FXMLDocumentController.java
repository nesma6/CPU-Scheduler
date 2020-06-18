package projectss;

import java.io.IOException;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {
    
    double x,y;
    
    
    @FXML
    void draged(MouseEvent event) {
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
        }

    @FXML
    void pressed(MouseEvent event) {
        x=event.getSceneX();
        y=event.getSceneY();
    }
  
 
    @FXML private RadioButton FCFS;
    @FXML private RadioButton RR;
    @FXML private RadioButton SJF;
    @FXML private RadioButton Priority;
    @FXML private Label radiobuttonlabel;
    private ToggleGroup scheduler;
    @FXML private Button actionBtn;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        radiobuttonlabel.setText("");
        scheduler = new ToggleGroup();
        this.FCFS.setToggleGroup(scheduler);
        this.RR.setToggleGroup(scheduler);
        this.SJF.setToggleGroup(scheduler);
        this.Priority.setToggleGroup(scheduler);

    
    }    

    @FXML
    private void min(MouseEvent event) {
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
        
    }

    @FXML
    private void max(MouseEvent event) {
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setFullScreenExitHint(" ");
        stage.setFullScreen(true);
    }

    @FXML
    private void closed(MouseEvent event) {
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    public void radioButtonChanged()
    {
        if (this.scheduler.getSelectedToggle().equals(this.FCFS))
            radiobuttonlabel.setText("The selected scheduler is : FCFS");
        
        if (this.scheduler.getSelectedToggle().equals(this.RR))
            radiobuttonlabel.setText("The selected scheduler is : RR");
        
        if (this.scheduler.getSelectedToggle().equals(this.SJF))
            radiobuttonlabel.setText("The selected scheduler is : SJF");
        
        if (this.scheduler.getSelectedToggle().equals(this.Priority))
            radiobuttonlabel.setText("The selected scheduler is : Priority"); 
    }
    @FXML
    private void addScene(ActionEvent event)throws IOException{


        
        if (radiobuttonlabel.getText()=="The selected scheduler is : FCFS")
        {
           Parent view2 = FXMLLoader.load(getClass().getResource("secondfcfs.fxml"));
           Scene scene2 = new Scene(view2);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene2);
            window.show();
        }
       else if (radiobuttonlabel.getText()=="The selected scheduler is : SJF")
        {
            Parent view4 = FXMLLoader.load(getClass().getResource("SecondSJF.fxml"));
            Scene scene4 = new Scene(view4);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene4);
            window.show();
        
        }
        else if (radiobuttonlabel.getText()=="The selected scheduler is : RR")
        {
           Parent view4 = FXMLLoader.load(getClass().getResource("secondRR.fxml"));
           Scene scene4 = new Scene(view4);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene4);
            window.show();
        }
        else if (radiobuttonlabel.getText()=="The selected scheduler is : Priority")
        {
           Parent view3 = FXMLLoader.load(getClass().getResource("SecondPriority.fxml"));
           Scene scene3 = new Scene(view3);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene3);
            window.show();
        }
     
               
}
    
}