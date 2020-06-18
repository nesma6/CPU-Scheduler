package priority;
import java.util.*;
public class pnp {
	
	PriorityQueue<process> mypq=new PriorityQueue<process>(new sortByArrivalTime());
	queue resutltQueue =new queue();
	int N_procesess;
	int N=N_procesess;
	double avgWaitingTime=0;
	
	public queue getResutltQueue() {
		return resutltQueue;
	}
	
	public pnp() 
	{
		N_procesess=0;
		N=0;
		avgWaitingTime=0;
	}
	public pnp(int no_of_processes) 
	{
		N_procesess =no_of_processes;
		N =no_of_processes;
		avgWaitingTime=0;
	}
	
	//avgWaitingTime
	public double getAvgWaitingTime() {
		return avgWaitingTime;
	}
	
	public void setAvgWaitingTime(double avgWaitingTime) {
		this.avgWaitingTime = avgWaitingTime;
	}
	
	//no of processes
	public int getN_procesess() {
		return N_procesess;
	}
	public void setN_procesess(int n_procesess) {
		N_procesess = n_procesess;
	}
	public int getN() {
		return N;
	}
	public void setN(int n) {
		N = n;
	}
	 
	// add process to pnp object
	public void addProcess(String name,double arrivaltime,double burstime,int priority)
	{
		process p=new process(name,arrivaltime,burstime,priority);
		mypq.add(p);
	}
	
	//pnp execution
	public void pnp_execute()
	{
		double t=0;
		double timeInstant=0;
		int pq_running_size=0;
		PriorityQueue<process> mypqrunning=new PriorityQueue<process>(new sortByPriority());
		
		//first time instant 
		t+=(mypq.peek().getBurstTime()+mypq.peek().getArrivalTime());
		timeInstant=t;
		
		process runningProcess=new process();
		runningProcess.setProcessName(mypq.peek().getProcessName());
		runningProcess.setArrivalTime(mypq.peek().getArrivalTime());
		runningProcess.setBurstTime(t);
		runningProcess.setPriority_no(mypq.peek().getPriority_no());
		resutltQueue.enqueue(runningProcess);
		
		mypq.remove();
		N_procesess--;
		
		process temp=null;
		while(N_procesess>0)
		{
			if(!mypq.isEmpty()) {temp=mypq.peek();}
			
			//moving to be executed processes into mypqrunning 
			if((!mypq.isEmpty())&&(temp.getArrivalTime()<=t))
			{
				while((!mypq.isEmpty())&&((pq_running_size<N_procesess)&&(temp.getArrivalTime()<=t)))
				{
					mypqrunning.add(temp);
					pq_running_size++;
					mypq.remove();
					if(!mypq.isEmpty()) {temp=mypq.peek();}
				}
			}
			// there is a gap
			else if((!mypq.isEmpty())&&(pq_running_size==0)&&(temp.getArrivalTime()>t))
			{
				t=mypq.peek().getArrivalTime();
				mypqrunning.add(mypq.peek());
				mypq.remove();
			}
			avgWaitingTime+=(t-mypqrunning.peek().getArrivalTime());
			runningProcess.setProcessName(mypqrunning.peek().getProcessName());
			runningProcess.setArrivalTime(t);
			runningProcess.setBurstTime(t+mypqrunning.peek().getBurstTime());
			runningProcess.setPriority_no(mypqrunning.peek().getPriority_no());
			resutltQueue.enqueue(runningProcess);
			t+=mypqrunning.peek().getBurstTime();
			timeInstant=t;
			N_procesess--;
			pq_running_size--;
			mypqrunning.remove();
		}
		avgWaitingTime/=N;
	}
	public void showGanttChartContents()
	{
		resutltQueue.printQueue();
	}

}
