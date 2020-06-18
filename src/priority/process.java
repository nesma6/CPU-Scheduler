package priority;

import java.util.*;


class process {
	private String processName;
	private double arrivalTime;
    private double burstTime;
	private int priority_no;
	
	//CONSTRUCTOR 
	public process(String processName,double arrivaltime,double burstTime, int priority_no) {
		this.processName=processName;
		this.arrivalTime=arrivaltime;
		this.burstTime = burstTime;
		this.priority_no = priority_no;
	}
	public process()
	{ 
		this.processName="";
		this.arrivalTime=0;
		this.burstTime =0;
		this.priority_no =0;
	}
    /*public int compareTo(process p)
    {
    	if(this.arrivalTime==p.arrivalTime) 
    	{
    		if(this.priority_no>p.priority_no) {return 1;}
    		else if(this.priority_no<p.priority_no) {return -1;}
    		else 
    		{
    			if(this.burstTime>p.burstTime) {return 1;}
    			else if(this.burstTime<p.burstTime) {return -1;}
    			else {return 0;}
    		}		
    	}
    	else if(this.arrivalTime>p.arrivalTime) {return 1;}
    	else {return -1;}

    }*/
    	
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

	public int getPriority_no() {
		return priority_no;
	}

	public void setPriority_no(int priority_no) {
		this.priority_no = priority_no;
	}

	
}

class sortByPriority implements Comparator<process>  {
	public int compare(process p1, process p2)
	{
		if(p1.getPriority_no()==p2.getPriority_no()) 
    	{
    		if(p1.getArrivalTime()>p2.getArrivalTime()) {return 1;}
    		else if(p1.getArrivalTime()<p2.getArrivalTime()) {return -1;}
    		else 
    		{
    			if(p1.getBurstTime()>p2.getBurstTime()) {return 1;}
    			else if(p1.getBurstTime()<p2.getBurstTime()) {return -1;}
    			else {return 0;}
    		}		
    	}
    	else if(p1.getPriority_no()>p2.getPriority_no()) {return 1;}
    	else {return -1;}
    }

}
class sortByArrivalTime implements Comparator<process>  {
	public int compare(process p1, process p2) {
		if(p1.getArrivalTime()==p2.getArrivalTime()) 
		{
			if(p1.getPriority_no()>p2.getPriority_no()) {return 1;}
			else if(p1.getPriority_no()<p2.getPriority_no()) {return -1;}
			else 
			{
				if(p1.getBurstTime()>p2.getBurstTime()) {return 1;}
				else if(p1.getBurstTime()<p2.getBurstTime()) {return -1;}
				else {return 0;}
			}		
		}
		else if(p1.getArrivalTime()>p2.getArrivalTime()) {return 1;}
		else {return -1;}
	}
}

