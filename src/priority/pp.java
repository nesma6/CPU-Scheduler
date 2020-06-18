package priority;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class pp
{
	PriorityQueue<process> mypq=new PriorityQueue<process>(new sortByArrivalTime());
	ArrayList<process>processArr=new ArrayList<process>();
	queue resutltQueue =new queue();
	int N_procesess;
	int N=N_procesess;
	double avgWaitingTime;
	int order=0;
	
	public pp()
	{
		N=0;
		N_procesess=0;
		avgWaitingTime=0;
	}
	public pp(int n)
	{
		N=n;
		N_procesess=N;
		avgWaitingTime=0;
		processArr=new ArrayList<process>(n);
	}
	
	public queue getResutltQueue() {
		return resutltQueue;
	}
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
	public double getAvgWaitingTime() {
		return avgWaitingTime;
	}
	public void setAvgWaitingTime(double avgWaitingTime) {
		this.avgWaitingTime = avgWaitingTime;
	}
	
	// add process to PP object
	public void addProcess(String name,double arrivaltime,double burstime,int priority)
	{
		process p=new process(name,arrivaltime,burstime,priority);
		mypq.add(p);
	}
	
	// add process to processArr
	public void addProcessToProcessArr(String name,double arrivaltime,double burstime,int priority)
	{
		process p=new process(name,arrivaltime,burstime,priority);
		processArr.add(order,p);
		order++;
	}

	public void execute_pp()
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
		process runningProcess=new process();
		
		while(N_procesess>1)
		{
			//PREEMTION 
			if(((currProcess.getArrivalTime()+currProcess.getBurstTime())>(nextProcess.getArrivalTime()))&&(currProcess.getArrivalTime()<nextProcess.getArrivalTime()))
			{
				if((!mypqrunning.isEmpty())&&(mypqrunning.peek().getProcessName()!=currProcess.getProcessName())){mypqrunning.add(currProcess);}
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
				
				waitingTime+=currentTimeInstant-lastTimeInstantAccessed.get(currProcess.getProcessName());
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
							waitingTime+=(currentTimeInstant-lastTimeInstantAccessed.get(mypqrunning.peek().getProcessName()));
							
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
						if(!mypq.isEmpty()) { nextProcess=mypq.remove();	}							
						
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
				currProcess=mypqrunning.peek();
			}
			
			else
			{
				//process is executed without interruption	
			    if(N_procesess>1)
				{
					waitingTime+=(currentTimeInstant-lastTimeInstantAccessed.get(currProcess.getProcessName()));
					
					//adding it it resultQueue
		            runningProcess.setProcessName(currProcess.getProcessName());
		            runningProcess.setArrivalTime(currentTimeInstant);
		            runningProcess.setBurstTime(currentTimeInstant+remainingBurst.get(currProcess.getProcessName()));
		            runningProcess.setPriority_no(currProcess.getPriority_no());
		            resutltQueue.enqueue(runningProcess);
	                
					currentTimeInstant+=remainingBurst.get(currProcess.getProcessName());
					currProcess=nextProcess;
					N_procesess--;
					if(!mypq.isEmpty())
					{
						nextProcess=mypq.remove();
					}
					
				}
				
			}
			
		}
		waitingTime+=(currentTimeInstant-lastTimeInstantAccessed.get(currProcess.getProcessName()));
			
		//adding it it resultQueue
	    runningProcess.setProcessName(currProcess.getProcessName());
		runningProcess.setArrivalTime(currentTimeInstant);
		runningProcess.setBurstTime(currentTimeInstant+remainingBurst.get(currProcess.getProcessName()));
		runningProcess.setPriority_no(currProcess.getPriority_no());
		resutltQueue.enqueue(runningProcess); 
		waitingTime/=N;
			
	}
	public void showGanttChartContents()
	{
		resutltQueue.printQueue();
	}


}

