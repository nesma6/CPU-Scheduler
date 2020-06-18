package priority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class priorityScheduler {
	PriorityQueue<process> mypq=new PriorityQueue<process>(new sortByArrivalTime());
	ArrayList<process>processArr=new ArrayList<process>();
	queue resutltQueue =new queue();
	int N_procesess;
	int N=N_procesess;
	double avgWaitingTime;
	int order=0;
	
	public void set_pp(int n)
	{
		N=n;
		N_procesess=N;
		avgWaitingTime=0;
		processArr=new ArrayList<process>(n);
	}
	public void set_pnp(int no_of_processes ) 
	{
		N_procesess =no_of_processes;
		N =no_of_processes;
		avgWaitingTime=0;
	}
	
	public queue getResutltQueue() {
		return resutltQueue;
	}
	public int getN_procesess() {
		
		return N_procesess;
	}
	public void setN_procesess(int n_procesess) {
		avgWaitingTime=0;
		N_procesess = n_procesess;
	}
	public int getN() {
		return N;
	}
	public void setN(int n) {
		avgWaitingTime=0;
		N = n;
	}
	public double getAvgWaitingTime() {
		return avgWaitingTime;
	}
	public void setAvgWaitingTime(double avgWaitingTime) {

		this.avgWaitingTime = avgWaitingTime;
	}
	
	public void addProcess(String name,double arrivaltime,double burstime,int priority)
	{
		process p=new process(name,arrivaltime,burstime,priority);
		mypq.add(p);
	}

	public void addProcessToProcessArr(String name,double arrivaltime,double burstime,int priority)
	{
		process p=new process(name,arrivaltime,burstime,priority);
		processArr.add(order,p);
		order++;
	}

	public void pp_execute()
	{
		double t=0;
		int pq_running_size=0;
		//mypqrunning
		PriorityQueue<process> mypqrunning=new PriorityQueue<process>(new sortByPriority());
		
		
		//mapping the processName to its lastTimeAcessed and its remaining time
		Map<String, Double>lastTimeInstantAccessed=new HashMap<String, Double>();
		Map<String, Double>remainingBurst=new HashMap<String, Double>();
		process ptr=null;
		
		//initialize map to arrivaltTimes of each process
		for(int i=0;i<N_procesess;i++)
		{
			//ptr=processArr[i] in java
			ptr=processArr.get(i);
			lastTimeInstantAccessed.put(ptr.getProcessName(),ptr.getArrivalTime());
			remainingBurst.put(ptr.getProcessName(),ptr.getBurstTime());			
		}
		
		//first arrived process is executed 
		process currProcess=mypq.remove();
		process nextProcess=currProcess;
		
		//more than 1 process
		if(!mypq.isEmpty()) 
		{
			nextProcess=mypq.remove();
		}
		else
		{  //1 process
		    N_procesess--;
		}
		double executionTime=0;
		double currentTimeInstant=currProcess.getArrivalTime();
		double waitingTime=0;
		double timeEndGap=0;
		process runningProcess=new process();
		
		while(N_procesess>1)
		{
			//PREEMTION 
			if(((currProcess.getArrivalTime()+currProcess.getBurstTime())>(nextProcess.getArrivalTime()))&&(currProcess.getArrivalTime()<nextProcess.getArrivalTime()))
			{
				if((!mypqrunning.isEmpty())&&(mypqrunning.peek().getProcessName()!=currProcess.getProcessName()))
				{mypqrunning.add(currProcess);}
			
				if(mypqrunning.isEmpty()) 
				{
					mypqrunning.add(currProcess);
					pq_running_size++;
				}
				
				executionTime=nextProcess.getArrivalTime()-currProcess.getArrivalTime();
				if(executionTime<currProcess.getBurstTime())
				{
					//adding it it resultQueue
		            runningProcess.setProcessName(currProcess.getProcessName());
		            runningProcess.setArrivalTime(currentTimeInstant);
		            runningProcess.setBurstTime(currentTimeInstant+executionTime);
		            runningProcess.setPriority_no(currProcess.getPriority_no());
		            resutltQueue.enqueue(runningProcess);   
				}
				else
				{
					//adding it it resultQueue
		            runningProcess.setProcessName(currProcess.getProcessName());
		            runningProcess.setArrivalTime(currentTimeInstant);
		            runningProcess.setBurstTime(currentTimeInstant+remainingBurst.get(currProcess.getProcessName()));
		            runningProcess.setPriority_no(currProcess.getPriority_no());
		            resutltQueue.enqueue(runningProcess);
				}
				
				avgWaitingTime+=currentTimeInstant-lastTimeInstantAccessed.get(currProcess.getProcessName());
				currentTimeInstant+=executionTime;
				lastTimeInstantAccessed.put(currProcess.getProcessName(), currentTimeInstant);
				remainingBurst.put(currProcess.getProcessName(),(remainingBurst.get(currProcess.getProcessName())-executionTime));
				pq_running_size++;
	            
				//next interrupting process
				mypqrunning.add(nextProcess);
				pq_running_size++;
				
				//all are in mypqrunning
				if(mypq.isEmpty())
					{
						while(N_procesess>1)
						{
							avgWaitingTime+=(currentTimeInstant-lastTimeInstantAccessed.get(mypqrunning.peek().getProcessName()));
							
							runningProcess.setProcessName(mypqrunning.peek().getProcessName());
							runningProcess.setArrivalTime(currentTimeInstant);
							runningProcess.setBurstTime(currentTimeInstant+remainingBurst.get(mypqrunning.peek().getProcessName()));
							runningProcess.setPriority_no(currProcess.getPriority_no());
							resutltQueue.enqueue(runningProcess);   
							
					        currentTimeInstant+=remainingBurst.get(mypqrunning.peek().getProcessName());
					
							mypqrunning.remove();
							N_procesess--;
							pq_running_size--;
						}
						currProcess=mypqrunning.peek();
						mypqrunning.remove();
					}
				
					//we don't whether a coming process will interrupt or no
				else
					{
						//executionTime=mypq.peek().getArrivalTime()-mypqrunning.peek().getArrivalTime();
						//System.out.printf("%s is executed for %f \n",mypqrunning.peek().getProcessName(),executionTime);
                        currProcess=mypqrunning.peek();
						mypqrunning.remove();
						pq_running_size--;
						if(!mypq.isEmpty()) { nextProcess=mypq.remove();}							
						
					}

			}
			
			
			//there is a gap 
			else if((currProcess.getArrivalTime()+currProcess.getBurstTime())<(nextProcess.getArrivalTime()))
			{
				avgWaitingTime+=(currentTimeInstant-lastTimeInstantAccessed.get(currProcess.getProcessName()));
					
				//adding it it resultQueue
		        runningProcess.setProcessName(currProcess.getProcessName());
		        runningProcess.setArrivalTime(currentTimeInstant);
                runningProcess.setBurstTime(currentTimeInstant+remainingBurst.get(currProcess.getProcessName()));
	            runningProcess.setPriority_no(currProcess.getPriority_no());
	            resutltQueue.enqueue(runningProcess);
	                
				currentTimeInstant+=remainingBurst.get(currProcess.getProcessName());
				N_procesess--;
				
				//remove executed completely
				//mypqrunning.remove();
				timeEndGap=nextProcess.getArrivalTime();
				
				if(!mypqrunning.isEmpty()){currProcess=mypqrunning.peek();}
				// as long as mypqrunning processes takes till their finish of execution time less or equal than time end of the gap
				//make them execute completely without interruption 
				//taking into consideration that mypqrunning is not empty
				while((!mypqrunning.isEmpty())&&((currentTimeInstant+remainingBurst.get(currProcess.getProcessName()))<=timeEndGap))
				{
					//execute it completely 
					
					avgWaitingTime+=(currentTimeInstant-lastTimeInstantAccessed.get(currProcess.getProcessName()));
					
					//adding it it resultQueue
					runningProcess.setProcessName(currProcess.getProcessName());
					runningProcess.setArrivalTime(currentTimeInstant);
					runningProcess.setBurstTime(currentTimeInstant+remainingBurst.get(currProcess.getProcessName()));
					runningProcess.setPriority_no(currProcess.getPriority_no());
					resutltQueue.enqueue(runningProcess);
	                
					currentTimeInstant+=remainingBurst.get(currProcess.getProcessName());
					mypqrunning.remove();
					pq_running_size--;
					N_procesess--;
					
					if(!mypqrunning.isEmpty()){currProcess=mypqrunning.peek();}
				}
				if(mypqrunning.isEmpty())
				{
					if(mypq.isEmpty())
					{
						//remaining 1 process to be executed which is nextProcess
						currProcess=nextProcess;
					}
					else
					{
						//Doubt
						currProcess=nextProcess;
						mypqrunning.add(currProcess);
						nextProcess=mypq.remove();
						
					}
				}
				else
				{
					//currProcess=mypqrunning.peek();
					
					currProcess=nextProcess;
					nextProcess=mypqrunning.peek();
				}
			}
			//only here we save similar arrivaltimes into mypqrunning
			//nothing is executed so don't worry about waiting time here
			else if(currProcess.getArrivalTime()==nextProcess.getArrivalTime())
			{
				
				if((!mypqrunning.isEmpty())&&(mypqrunning.peek().getProcessName()!=currProcess.getProcessName()))
				{
					mypqrunning.add(currProcess);
					pq_running_size++;
				}
				if(mypqrunning.isEmpty()) 
				{
					mypqrunning.add(currProcess);
			        pq_running_size++;
				}
				
				//put all arrived same time processes in mypqrunning
				
				while((!mypq.isEmpty())&&(currProcess.getArrivalTime()==nextProcess.getArrivalTime()))
				{
					mypqrunning.add(nextProcess);
					pq_running_size++;
					if(!mypq.isEmpty())
					{
						nextProcess=mypq.peek();
						mypq.remove();
					}
				}
				//not very sure
				/*
				if(mypqrunning.peek().getProcessName()==currProcess.getProcessName())
				{
					mypqrunning.remove();
				}
				*/
				currProcess=mypqrunning.peek();
			}
			
			else
			{
				//process is executed without interruption	
			    if(N_procesess>1)
				{
			    	avgWaitingTime+=(currentTimeInstant-lastTimeInstantAccessed.get(currProcess.getProcessName()));
					
					//adding it it resultQueue
		            runningProcess.setProcessName(currProcess.getProcessName());
		            runningProcess.setArrivalTime(currentTimeInstant);
		            runningProcess.setBurstTime(currentTimeInstant+remainingBurst.get(currProcess.getProcessName()));
		            runningProcess.setPriority_no(currProcess.getPriority_no());
		            resutltQueue.enqueue(runningProcess);
	                
					currentTimeInstant+=remainingBurst.get(currProcess.getProcessName());
					
					//currProcess=nextProcess;
					
					// currProcess is executed completely so it doesn't need anymore to be in mypqrunning
					if(mypqrunning.peek().getProcessName()==currProcess.getProcessName())
					{
						mypqrunning.remove();
					}
					if(!mypqrunning.isEmpty()){currProcess=mypqrunning.peek();}
					else{currProcess=nextProcess;}
					
					N_procesess--;
					if(!mypq.isEmpty())
					{
						nextProcess=mypq.remove();
					}
					
				}
				
			}
			
		}
		if(currentTimeInstant<lastTimeInstantAccessed.get(currProcess.getProcessName()))
		{
			//adding it it resultQueue
		    runningProcess.setProcessName(currProcess.getProcessName());
			runningProcess.setArrivalTime(currProcess.getArrivalTime());
			runningProcess.setBurstTime(currProcess.getArrivalTime()+remainingBurst.get(currProcess.getProcessName()));
			runningProcess.setPriority_no(currProcess.getPriority_no());
			resutltQueue.enqueue(runningProcess); 
		}
		
		else
		{
			avgWaitingTime+=(currentTimeInstant-lastTimeInstantAccessed.get(currProcess.getProcessName()));
			//adding it it resultQueue
		    runningProcess.setProcessName(currProcess.getProcessName());
			runningProcess.setArrivalTime(currentTimeInstant);
			runningProcess.setBurstTime(currentTimeInstant+remainingBurst.get(currProcess.getProcessName()));
			runningProcess.setPriority_no(currProcess.getPriority_no());
			resutltQueue.enqueue(runningProcess); 
			
		}
		
		avgWaitingTime/=N;
			
	}

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
			else if((!mypq.isEmpty())&&(pq_running_size<=0)&&(temp.getArrivalTime()>t))
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
