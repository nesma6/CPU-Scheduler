/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectss;



import static roundrobin1.QueueOfprocess.clear;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import roundrobin1.QueueOfprocess;
import static roundrobin1.QueueOfprocess.Enqueue;
import static roundrobin1.QueueOfprocess.Pend;
import static roundrobin1.QueueOfprocess.Pname;
import static roundrobin1.QueueOfprocess.Pser;
import static roundrobin1.QueueOfprocess.sortQueue;


/**
 * FXML Controller class
 *
 * @author Mony's HP Probook
 */
public class SecondRRController implements Initializable {

    /**
     * Initializes the controller class.
     */

    double x,y;
    @FXML
    private TextField Number;
    @FXML
    private TextField ID;
    @FXML
    private TextField BT;
    @FXML
    private TextField AT;
    @FXML
    private TextField show;
    @FXML
    private TextField quantum;
    @FXML
    private VBox vbox;
    NumberAxis Xaxis;
    NumberAxis Yaxis;
    public AreaChart<Number,Number> areachart;
    int num1=0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //x axis
    	Xaxis=new NumberAxis(0,10,1);
    	//Xaxis.setMinorTickCount(0);
    	Xaxis.setMinorTickVisible(true);
    	//Xaxis.setMinorTickCount(1);
    	Xaxis.setMinorTickLength(1);
    	//Xaxis.autosize();
    	Xaxis.setAnimated(false);
    	//Xaxis.setTickUnit(0.1);
    	//Xaxis.setAutoRanging(true);
    	Xaxis.setTickLabelsVisible(true);
    	Xaxis.setTickMarkVisible(true);
    	//y axis
    	Yaxis=new NumberAxis(0,1,0);
    	Yaxis.setMinorTickCount(0);
    	Yaxis.setTickLabelsVisible(false);
    	Yaxis.setTickMarkVisible(false);
    	Yaxis.setAnimated(false);
    	//areaChat
    	areachart=new AreaChart(Xaxis,Yaxis);
    	//areachart.setTitle("GANTT CHART");
    	areachart.setCreateSymbols(false);
    	areachart.setVerticalGridLinesVisible(false);
    	areachart.setHorizontalGridLinesVisible(false);
    	areachart.setAnimated(false);
    	//areachart.autosize();
    	// areachart.applyCss();
    	
    	//adding to vbox to be visible 
    	vbox.getChildren().add(areachart);
    }    
    
        QueueOfprocess Q=new QueueOfprocess();
        QueueOfprocess N=new QueueOfprocess();
        
        Queue<String> names = new LinkedList<>();
        Queue<Float> StartTime= new LinkedList<>();
        Queue<Float> EndTime= new LinkedList<>();
        QueueOfprocess output = new QueueOfprocess();
        String q ;
        int qua;
    
   
    
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

    @FXML
    void Addprocess()
    {   
        q = quantum.getText();
        qua=Integer.parseInt(q);
        String n=Number.getText();
        String PID=ID.getText();
        String bt=BT.getText();
        String at=AT.getText();
        
        float arrT=parseFloat(at);
        float ButT=parseFloat(bt);
        
        roundrobin1.Process P=new roundrobin1.Process();
        
        P.settimes(PID,ButT,arrT);
        Enqueue(Q,P);
        Enqueue(N,P);
        
   //     float value=P.getServicetime();
   //     float value2=P.getEndingtime();
       
        
        ID.clear();
        BT.clear();
        AT.clear();
        
        
    }
    ArrayList<XYChart.Series> dataOnGantt=new ArrayList<XYChart.Series>(num1);
    @FXML
    private void showcontents(ActionEvent event) {

        int num = QueueOfprocess.numberofpro(Q);
        sortQueue(Q,N,num); 
        QueueOfprocess output = new QueueOfprocess();
        float avr = roundrobin1.QueueOfprocess.RR(Q ,qua ,output);
        String average=String.valueOf(avr);
        names = Pname(output);
        StartTime = Pser(output);
        EndTime = Pend(output);
        show.setText(average);
        String name;
        float  start=0;
        float end=0;
        
        Double GanttWidth=0.0;
        int i=0;
        int index=0;
        
        //create series objects with size np
        for(int j=0;j<num;j++)
        {
        	dataOnGantt.add(new XYChart.Series());
        }
         while(!names.isEmpty())
        {
        	name=names.peek();
			start=StartTime.peek();
    		end=EndTime.peek();
        
        
      //search for required index
		for(int k=0;k<i;k++)
		{
			if(dataOnGantt.get(k).getName()==name) 
			{
				
				index=k;
				break;
			}
			else
			{
				index=i;
			}
		}
		
		if(dataOnGantt.get(index).getChart()==null)
		{
			//not added before to GANTT
			//Don't need to know index
			
                dataOnGantt.get(index).setName(name);
         	dataOnGantt.get(index).getName();
         	areachart.getData().add(dataOnGantt.get(index));
   
         	i++;
         	
		}
        dataOnGantt.get(index).getData().add(new XYChart.Data(start,0));
     	dataOnGantt.get(index).getData().add(new XYChart.Data(start,1));
     	dataOnGantt.get(index).getData().add(new XYChart.Data(end,1));
     	dataOnGantt.get(index).getData().add(new XYChart.Data(end,0));
     	
     	
     	names.remove();
     	StartTime.remove();
     	EndTime.remove();
      }
        GanttWidth+=end+1;
        Xaxis.setUpperBound(GanttWidth);
        Xaxis.setTickLength(1);
        Xaxis.setMinorTickLength(1);
        
    }
    @FXML
    private void back(ActionEvent event)throws IOException {
           Parent view2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
           Scene scene2 = new Scene(view2);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene2);
            window.show();
    }

    @FXML
    private void newoperation(ActionEvent event) {
        
       
        names.clear();
        StartTime.clear();
        EndTime.clear();
        Number.clear();
        quantum.clear();
        clear(output);
        clear(Q);
        clear(N);
        show.clear();
        areachart.getData().removeAll(dataOnGantt);
        while(!dataOnGantt.isEmpty())
        {
        	dataOnGantt.remove(0);
        	
        }
    }

}
