
package projectss;

import java.io.IOException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafxapplication3.NodeQueue;
import javafxapplication3.Queue;
import static javafxapplication3.Queue.Sort1;
import javafxapplication3.SJF;
import nonpreemptive_sjf.nodeSJF;
import nonpreemptive_sjf.np_sjf;
import priority.node;

//import static javafxapplication3.Queue.Sort1;
import static javafxapplication3.Queue.clear;
import static javafxapplication3.Queue.ganttchart;
//import static javafxapplication3.Queue.findWaitingTime;
import static javafxapplication3.SJF.preemptive;


public class SecondSJFController implements Initializable {
	 int num=0;
     Queue q = new Queue();
     Queue preemptivechart = new Queue();                              //for preemptive gantt chart
     Queue Nonpreemptivechart = new Queue();              /////////////for nonpreemptive gantt chart
     double preemptiveResult;
     double NonpreemptiveResult;
     String s = " ";
    private Label lable;
     @FXML
    private Label labeltitle;
     @FXML
    private Label labelnumber;

    @FXML
    private TextField txtnumber;
    @FXML
    private Label labeltype;
     @FXML
    private Label result;
@FXML
    private Button btnexit;

    @FXML
    private Button btnhome;

    @FXML
    private TextField txtprocessid;
@FXML
    private ImageView image;
    @FXML
    private TextField txtarrivingtime;

    @FXML
    private TextField txtbursttime;

    @FXML
    private Button btnadd;

   @FXML
    private Button btnrun;
   
    @FXML
    private Button btnpreemptive;

    @FXML
    private Button btnnonpreemptive;
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
    	areachart.setCreateSymbols(false);
    	areachart.setVerticalGridLinesVisible(false);
    	areachart.setHorizontalGridLinesVisible(false);
    	areachart.setAnimated(false);
    	//areachart.autosize();
    	// areachart.applyCss();
    	
    	//adding to vbox to be visible 
    	vbox.getChildren().add(areachart);
    } 
    /*
    Map <String,XYChart.Series> fromNameGetSeries =new HashMap<String, XYChart.Series>();
   	Map <XYChart.Series,Boolean>checkSeriesIsAdded= new HashMap<XYChart.Series,Boolean>();
    
    */
    
    int np;
    np_sjf scheduler=new np_sjf();
    String MYnum;
    @FXML
    public void AddData(){
    	MYnum=txtnumber.getText();
        String id = txtprocessid.getText();
        Double at = Double.parseDouble(txtarrivingtime.getText());
        Double bt = Double.parseDouble(txtbursttime.getText());
        q.Enqueue(id, at, bt);
        txtprocessid.clear();
        txtarrivingtime.clear();
        txtbursttime.clear();
        np=Integer.parseInt(MYnum);
        scheduler.addProcess(id,at,bt);
        /*fromNameGetSeries.put(id, new XYChart.Series());
        checkSeriesIsAdded.put(fromNameGetSeries.get(id), false);*/
        

        
    }
    ArrayList<XYChart.Series> dataOnGantt=new ArrayList<XYChart.Series>(num);
    @FXML
    public void calc(){
    	
        
        if(s == "NonPreemptive")
        {
  
        	String name;
            Double  start=0.0;
            Double end=0.0;
            //Double GanttWidth=scheduler.getResutltQueue().getHead().getNodeArrivalTime();
            Double GanttWidth=0.0;
            int i=0;
            nodeSJF currP_sjf;
            int index=0;
            
            //create series objects with size np
            for(int j=0;j<np;j++)
            {
            	dataOnGantt.add(new XYChart.Series());
            }
            
            while(!scheduler.getResutltQueue().isEmpty())
            {
            	currP_sjf=scheduler.getResutltQueue().getHead();
    			
        		name=currP_sjf.getNodeProcessName();
    			start=currP_sjf.getNodeArrivalTime();
        		end=currP_sjf.getNodeBurstTime();
        		
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
        	
        	
        	
        	
        	
             result.setText("Average Waiting Time = " + Double.toString(scheduler.getAvgWaitingTime()));
        	
        	
        	
        	
        	
        	
        	
        	
        	
        }
        
         else
         {
        	 
        	 String name;
             Double  start=0.0;
             Double end=0.0;
             //Double GanttWidth=scheduler.getResutltQueue().getHead().getNodeArrivalTime();
             Double GanttWidth=0.0;
             int i=0;
             NodeQueue currP;
             int index=0;
        	 result.setText("Average Waiting Time = " + Double.toString(preemptiveResult));
        	 while(!preemptivechart.IsEmpty())
        	 {
        		//create series objects with size np
        	        for(int j=0;j<num;j++)
        	        {
        	        	dataOnGantt.add(new XYChart.Series());
        	        }
        		 name=preemptivechart.getFront().getPID();
        		 
        		 
        		 while(!preemptivechart.IsEmpty())
        	        {
        	        	currP=preemptivechart.getFront();
        				
        	    		name=currP.getPID();
        				start=currP.getAT();
        	    		end=currP.getBT();
        	    		
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
        	         	
        	         	
        	         	preemptivechart.Dequeue();
        	         	
        	         }
        	         GanttWidth+=end+1;
        	         Xaxis.setUpperBound(GanttWidth);
        	         Xaxis.setTickLength(1);
        	         Xaxis.setMinorTickLength(1);
        	        
        	         
        	             
        }
        	 
         }
        }
    @FXML
    public void NonPreemptive()
    { 
    	/*num=q.size();
        double[] wt1 = new double[q.size()];
        double[] ct1 = new double[q.size()];
       double avg;
         avg = Sort1(q,ct1,wt1);
         ganttchart( q, Nonpreemptivechart);
         NonpreemptiveResult = avg;*/
        s = "NonPreemptive";
    	scheduler.setN(np);
   	    scheduler.setN_procesess(np);
   	    scheduler.np_sjf_execute();
        
    }
    @FXML
    public void Preemptive(){
    	num=q.size();
        double []wt = new double[q.size()];
	double[]ct  = new double[q.size()];
	double[]rt  = new double[q.size()];
         preemptiveResult= SJF.preemptive(q,ct,rt,wt,preemptivechart);
	s = "Preemptive";
    }
    @FXML
    public void clear1()
    {
        clear(q);
        clear(preemptivechart);
        clear(Nonpreemptivechart);
        s = " ";
        result.setText("result");
        txtnumber.clear();
        scheduler=new np_sjf();
    	areachart.getData().removeAll(dataOnGantt);
        while(!dataOnGantt.isEmpty())
        {
        	dataOnGantt.remove(0);
        	
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
           Scene scene2 = new Scene(view2);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene2);
            window.show();
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
   
    
    
}

