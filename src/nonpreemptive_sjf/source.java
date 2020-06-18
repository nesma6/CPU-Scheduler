package nonpreemptive_sjf;

import java.util.*;
public class source {

	public static void main(String[] args)
	{
		
		//user enters the scheduler type 
		Scanner fromKeyboard=new Scanner(System.in);
		System.out.print("please enter the no. of processes :");
		int N_procesess = fromKeyboard.nextInt();
		
		int N =N_procesess;
		np_sjf scheduler =new np_sjf(N);
		
		
		double arrivaltime,burstime,avgWaitingTime=0;int priority;String name="";
		for(int i=0;i<N_procesess;i++)
		{
			System.out.printf("process %d \n",i+1);
			System.out.println("---------------------");
			System.out.print("Enter process name: ");
			fromKeyboard.nextLine();
			name=fromKeyboard.nextLine();
			
			System.out.print("Enter process Arrival Time: ");
			arrivaltime=fromKeyboard.nextDouble();
			
			System.out.print("Enter process Burst Time: ");
			burstime=fromKeyboard.nextDouble();
			
			scheduler.addProcess(name, arrivaltime, burstime);
			
		}
		scheduler.np_sjf_execute();
		scheduler.showGanttChartContents();
		//System.out.println(scheduler.getResutltQueue().dequeue().getData().getArrivalTime());
		}
	}