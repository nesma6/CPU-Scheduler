/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectss;
import java.io.IOException;

import static java.lang.Float.parseFloat;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import priority.*;

/**
 * FXML Controller class
 *
 * @author Mony's HP Probook
 */
public class SecondPriorityController implements Initializable {

    @FXML
    private TextField numberofprocesses; //you will use that function (numberofprocesses.getText()) to get what the user typed in this text field 
    @FXML
    private TextField processid;//you will use that function (processidgetText()) to get what the user typed in this text field 
    @FXML
    private TextField processbursttime;//you will use that function (processbursttime.getText()) to get what the user typed in  this text field 
    @FXML
    private TextField processarrivaltime;//you will use that function (processarivaltime.getText()) to get what the user typed in this text field 
    @FXML
    private TextField showtheaverage;//you will use that function (numberofprocesses.setText()) to show what you want in this text field 
    @FXML
    private TextField processpriority;//you will use that function (processpriority.getText()) to get what the user typed in this text field 
    @FXML
    private VBox vbox;


    NumberAxis Xaxis;
    NumberAxis Yaxis;
    public AreaChart<Number,Number> areachart;
    
    /**
     * Initializes the controller class.
     */
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
    	//areachart.setMaxHeight(50);
    	//System.out.println(areachart.getHeight());
    	//areachart.setMinHeight(40);
    	//areachart.setPrefHeight(50);
    	//areachart.setMaxHeight(40);
    	//areachart.setMaxSize(1000, 10);
    	areachart.setCreateSymbols(false);
    	areachart.setVerticalGridLinesVisible(false);
    	areachart.setHorizontalGridLinesVisible(false);
    	areachart.setAnimated(false);
    	//areachart.autosize();
    	// areachart.applyCss();
    	
    	//adding to vbox to be visible 
    	vbox.getChildren().add(areachart);
    }    
    int np;
    priorityScheduler scheduler=new priorityScheduler();
    /*Map <String,XYChart.Series> fromNameGetSeries =new HashMap<String, XYChart.Series>();
	Map <XYChart.Series,Boolean>checkSeriesIsAdded= new HashMap<XYChart.Series,Boolean>();*/
    
   //min,max,and close are functions to the screen you can ignore them
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
    private void Addprocess(ActionEvent event) {
        // here you will use the the function we decribed above
        String PID=processid.getText();           //now you have the process id in that variable
        String bt=processbursttime.getText();    //we have the burst time but as string so we will convert it later into float
        String at=processarrivaltime.getText();  //we have the arrival time but as string so we will convert it later into float
        String pr=processpriority.getText();   //we have the priority but as string so we will convert it later into integer
        String num=numberofprocesses.getText();
        np=Integer.parseInt(num);
        int   priorit=Integer.parseInt(pr);    //this function will convert the string into intger (now you have the priority as an int number )
        float arrT=parseFloat(at);             //this function will convert the string into float (now you have the arrival time as a float number)
        float ButT=parseFloat(bt);         //this function will convert the string into float (now you have the burst time as a float number)
        
        scheduler.addProcess(PID,arrT,ButT,priorit);
        scheduler.addProcessToProcessArr(PID,arrT,ButT,priorit);
        /*fromNameGetSeries.put(PID, new XYChart.Series());
        checkSeriesIsAdded.put(fromNameGetSeries.get(PID), false);*/
        
        
        processid.clear();             //we use this clear function so when the user press add process it clears what he has wrriten on the screen and the fields now is empty to write on them again
        processbursttime.clear();
        processarrivaltime.clear();
        processpriority.clear();
    }

     @FXML
    private void Preemptive(ActionEvent event) //this will be called and executed if the user presses preemptive(you should put your code here )
    {
    	 scheduler.setN(np);
    	 scheduler.setN_procesess(np);
    	 scheduler.pp_execute();
    }

    @FXML
    private void NonPreemptive(ActionEvent event) //this will be called and executed if the user presses nonpreemptive(you should put your code here )
    {
    	scheduler.setN(np);
   	    scheduler.setN_procesess(np);
   	    scheduler.pnp_execute();
    }
    
    ArrayList<XYChart.Series> dataOnGantt=new ArrayList<XYChart.Series>(np);
    
    @FXML
    private void showcontents(ActionEvent event)  //this will be called and executed when the user want to see the avg waiting time and the gantt chart 
    {
    	showtheaverage.setText(String.valueOf(scheduler.getAvgWaitingTime())); 
    	
    	String name;
        Double  start=0.0;
        Double end=0.0;
        //Double GanttWidth=scheduler.getResutltQueue().getHead().getNodeArrivalTime();
        Double GanttWidth=0.0;
        int i=0;
        node currP;
        int index=0;
        
        //create series objects with size np
        for(int j=0;j<np;j++)
        {
        	dataOnGantt.add(new XYChart.Series());
        }
        
        while(!scheduler.getResutltQueue().isEmpty())
        {
        	currP=scheduler.getResutltQueue().getHead();
			
    		name=currP.getNodeProcessName();
			start=currP.getNodeArrivalTime();
    		end=currP.getNodeBurstTime();
    		
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
         	
         	
         	scheduler.getResutltQueue().dequeue();
         	
         }
         GanttWidth+=end+1;
         Xaxis.setUpperBound(GanttWidth);
         Xaxis.setTickLength(1);
         Xaxis.setMinorTickLength(1);
        
         
     	 
    
         /*
         while(!scheduler.getResutltQueue().isEmpty())
         {
        	currP=scheduler.getResutltQueue().getHead();
        	name=currP.getNodeProcessName();
        	start=currP.getNodeArrivalTime();
     		end=currP.getNodeBurstTime();
         	dataOnGantt.add(new XYChart.Series());
         	dataOnGantt.get(i).setName(name);
         	dataOnGantt.get(i).getName();
         	dataOnGantt.get(i).getData().add(new XYChart.Data(start,0));
         	dataOnGantt.get(i).getData().add(new XYChart.Data(start,1));
         	dataOnGantt.get(i).getData().add(new XYChart.Data(end,1));
         	dataOnGantt.get(i).getData().add(new XYChart.Data(end,0));
         	scheduler.getResutltQueue().dequeue();
            i++;	
          
         }*/
         
         /*for(int j=0;j<i;j++)
         {
        	 if(!(areachart.getChildrenUnmodifiable().contains(dataOnGantt.get(j))))
        	 {areachart.getData().add(dataOnGantt.get(j)) ;}
        	
         } */
         
         
    	/*//ArrayList<XYChart.Series> dataOnGantt=new ArrayList<XYChart.Series>();
    	node currP;
    	String name;
    	XYChart.Series mySeries = new XYChart.Series();
    	Double start,end;
    	int i=0;
    	while(!scheduler.getResutltQueue().isEmpty())
    	{
    		currP=scheduler.getResutltQueue().getHead();
    		name=currP.getNodeProcessName();
    		mySeries=fromNameGetSeries.get(name);
    		if(!checkSeriesIsAdded.get(mySeries))
    		{
    			//not added
    			areachart.getData().add(mySeries);
    			checkSeriesIsAdded.put(mySeries, true);
    			//areachart.getData().add(dataOnGantt.get(i)) ;
    			
    		}
    		start=currP.getNodeArrivalTime();
    		end=currP.getNodeBurstTime();
    		mySeries.setName(name);
        	mySeries.getName();
    		//areachart.accessibleTextProperty();
    		//System.out.println(areachart.getLayoutBounds());
    		//System.out.println(mySeries.getName());
    		mySeries.getData().add(new XYChart.Data(start,0));
    		mySeries.getData().add(new XYChart.Data(start,1));
    		mySeries.getData().add(new XYChart.Data(end,1));
    		mySeries.getData().add(new XYChart.Data(end,0));
    		//dataOnGantt.add(mySeries);
    		scheduler.getResutltQueue().dequeue();
    		
    	}*/
    	//scheduler.showGanttChartContents();
    }

    @FXML
    private void back(ActionEvent event) throws IOException //this function used to back to the home screen
    {
            Parent view2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene2 = new Scene(view2);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene2);
            window.show();
    }

    @FXML
    private void newoperation(ActionEvent event) //this function will clear the last operation when the user press new operation and start a new one when the user press add process
    {
    	numberofprocesses.clear();
    	showtheaverage.clear();
    	scheduler=new priorityScheduler();
    	/*areachart.getData().removeAll(fromNameGetSeries.values());
    	areachart.getData().removeAll(checkSeriesIsAdded.keySet());
        areachart.applyCss();*/
    	areachart.getData().removeAll(dataOnGantt);
        while(!dataOnGantt.isEmpty())
        {
        	dataOnGantt.remove(0);
        	
        }
    }

   
    
}
