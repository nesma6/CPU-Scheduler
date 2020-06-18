package nonpreemptive_sjf;

import java.util.*;


class processSJF {
	private String processName;
	private double arrivalTime;
    private double burstTime;
	
	//CONSTRUCTOR 
	public processSJF(String processName,double arrivaltime,double burstTime) {
		this.processName=processName;
		this.arrivalTime=arrivaltime;
		this.burstTime = burstTime;
	}
	public processSJF()
	{ 
		this.processName="";
		this.arrivalTime=0;
		this.burstTime =0;
	}
	public String getProcessName()
	{
		return this.processName;
	}
    
	public void setProcessName(String processName)
	{
		this.processName=processName;
	}
	public double getArrivalTime()
	{
		return this.arrivalTime;
	}
	public void setArrivalTime(double time)
	{
		this.arrivalTime=time;
	}
	public double getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(double burstTime) {
		this.burstTime = burstTime;
	}

	
}
class sortByBurstTime implements Comparator<processSJF>  {
	public int compare(processSJF p1, processSJF p2)
	{
		if(p1.getBurstTime()==p2.getBurstTime()) 
    	{
    		if(p1.getArrivalTime()>p2.getArrivalTime()) {return 1;}
    		else if(p1.getArrivalTime()<p2.getArrivalTime()) {return -1;}
    		else {return 0;}
    	}
    	else if(p1.getBurstTime()>p2.getBurstTime()) {return 1;}
    	else {return -1;}
    }

}

class sortByArrivalTime implements Comparator<processSJF>  {
	public int compare(processSJF p1, processSJF p2) {
		if(p1.getArrivalTime()==p2.getArrivalTime()) 
		{
			if(p1.getBurstTime()>p2.getBurstTime()) {return 1;}
			else if(p1.getBurstTime()<p2.getBurstTime()) {return -1;}
			else {return 0;}				
		}
		else if(p1.getArrivalTime()>p2.getArrivalTime()) {return 1;}
		else {return -1;}
	}
}

