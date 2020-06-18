package projectss;

import java.util.*;
import javafx.scene.chart.NumberAxis; 
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.chart.AreaChart;
import fcfs.Queueofprocesses;
import static fcfs.Queueofprocesses.Enqueue;
import static fcfs.Queueofprocesses.Pend;
import static fcfs.Queueofprocesses.Pname;
import static fcfs.Queueofprocesses.Print;
import static fcfs.Queueofprocesses.Pser;
import static fcfs.Queueofprocesses.numberofpro;
import static fcfs.Queueofprocesses.calculate_avg;
import static fcfs.Queueofprocesses.calculate_et;
import static fcfs.Queueofprocesses.calculate_st;
import static fcfs.Queueofprocesses.calculate_wt;
import static fcfs.Queueofprocesses.clear;
import static fcfs.Queueofprocesses.sortQueue;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class SecondfcfsController implements Initializable {


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
    private VBox vbox;
    NumberAxis Xaxis;
    NumberAxis Yaxis;
    public AreaChart<Number,Number> areachart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
     Queueofprocesses Q=new Queueofprocesses();
     Queueofprocesses N=new Queueofprocesses();
     Queue<Float> Processesservicetime = new LinkedList<>();
     Queue<Float> Processesendingtime = new LinkedList<>();
     Queue<String> Processesnames = new LinkedList<>();
    
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
        String PID=ID.getText();
        String bt=BT.getText();
        String at=AT.getText();
        float arrT=parseFloat(at);
        float ButT=parseFloat(bt);
        fcfs.Process P=new fcfs.Process();
        P.settimes(PID, ButT, arrT);
        Enqueue(Q,P);
        Enqueue(N,P);
        Print(Q);
        float value=P.getServicetime();
        float value2=P.getEndingtime();
        ID.clear();
        BT.clear();
        AT.clear();
        
        
    }
    ArrayList<XYChart.Series> dataOnGantt=new ArrayList<XYChart.Series>();

   @FXML
    private void showcontents(ActionEvent event) {
       int n =numberofpro(Q);
        sortQueue(Q,N,n); 
        calculate_st(Q); 
        calculate_wt(Q); 
        calculate_et(Q); 
        Processesnames=Pname(Q);
        Processesservicetime=Pser(Q);
        Processesendingtime=Pend(Q);
        float avg=calculate_avg(Q,n);
        String average=String.valueOf(avg);
        show.setText(average);
        
        String name;
        float  start=(Processesservicetime.peek());
        float end=0;
        int i=0;
        float GanttWidth=0;
        while(!Processesnames.isEmpty())
        {
        	
        	dataOnGantt.add(new XYChart.Series());
        	dataOnGantt.get(i).setName(Processesnames.peek());
        	dataOnGantt.get(i).getName();
        	dataOnGantt.get(i).getData().add(new XYChart.Data(Processesservicetime.peek(),0));
        	dataOnGantt.get(i).getData().add(new XYChart.Data(Processesservicetime.peek(),1));
        	dataOnGantt.get(i).getData().add(new XYChart.Data(Processesendingtime.peek(),1));
        	dataOnGantt.get(i).getData().add(new XYChart.Data(Processesendingtime.peek(),0));
        	end=Processesendingtime.peek();
        	Processesnames.remove();
        	Processesservicetime.remove();
        	Processesendingtime.remove();
        	
            areachart.getData().add(dataOnGantt.get(i)) ;
            i++;
        	
        }
        GanttWidth+=(end+1);
        Xaxis.setUpperBound(GanttWidth);
        Xaxis.setTickLength(1);
        
        

    
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
        Processesnames.clear();
        Processesservicetime.clear();
        Processesendingtime.clear();
        Number.clear();
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
