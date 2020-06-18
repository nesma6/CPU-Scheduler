package javafxapplication3;

import java.util.Scanner;

public class SJF {
	
	/*public static double preemptive(Queue q,double ct[],double rt[],double wt[])	         //sort queue in preemptive SJF scheduling
	{
		double t =1;
		double tbt  =0;
		Node curr = q.front;
		//find total burst time;
		for(int i=0;i<q.size();i++)
		{   rt[i] = curr.bt;                        //initialize remaining time with burst time of the process
			tbt += curr.bt;
			curr = curr.next;
			}
		
		//Arrays.sort(rt) ;
		//int minindex = 0;   //minindex of remaining time
		// completion time equal t when remaining time of the process equal 0
		
		rt[0]=rt[0]-1;
		double minm =Double.MAX_VALUE;
		int shortest =0;
		Boolean check = false;
		int complete=0;
		while(complete!=q.size())
		{   //int min1 = min(tbt,at,rt); 
		    //System.out.println(min1);
		Node cur = q.front;//min1 = minimum remaining time process
		for(int i=0;i<q.size();i++)            //to find minimum remaining time from existing process
		{
		if( (cur.data <= t) && rt[i]>0  && rt[i]<minm)
		{
			minm =  rt[i];
			shortest = i;
			check = true;
		}
		}
		if(check == false)
			t++;
			continue;
			}
		rt[shortest]--;
		minm = rt[shortest];
		if(minm==0)
			minm = Double.MAX_VALUE;
		if(rt[shortest]==0) {
			complete++;
		check = false;
		double finishtime = t+1;
		wt[shortest] = finishtime - c.bt - c.data;;
			
				{
					rt[i]--;
					if(rt[i]==0)  ct[i] = t+1;
				}
		else
				cur = cur.next;
				}
			t++; 	
		}
		Node c = q.front;
		for(int i=0;i<q.size();i++)
		{ System.out.println(ct[i]);
			wt[i] = ct[i]-c.data-c.bt;
			c = c.next;
			
		}
		 double tot = 0;
		for(int i=0;i<q.size();i++)
		{
			tot += wt[i];
		}
		double avg= tot/q.size();
		return avg;
		
		
	}}*/
	
public static double preemptive(Queue q,double ct[],double rt[],double wt[],Queue gantt)
{
	int complete =0 , shortest =0;
	Boolean check = false;
	//double t =0;
	double minm =Double.MAX_VALUE;
	double tbt =0;
	double[] at= new double [q.size()];
	double [] bt = new double [q.size()];
	double mint =q.front.data;
	NodeQueue curr = q.front;
	for(int i=0;i<q.size();i++)
	{  if(curr.data<mint)
	{
		mint = curr.data;
	}
		rt[i] = curr.bt;                        //initialize remaining time with burst time of the process
		at[i] = curr.data;
		bt[i] = curr.bt;
	tbt += curr.bt;
		curr = curr.next;
		}
	NodeQueue m  =q.front;
	for(int i=1;i<q.size();i++)
	{
		if(m.data<mint)
		{
			mint = curr.data;
		}
		m = m.next;
	}
	//System.out.println(mint);
	
    String [] d = new String [(int)tbt];
    String [] z = new String [(int)tbt];
    
    
    int h =0;
    int k=0;
    double t = mint;
	while(complete!=q.size())
	{  
		NodeQueue cur  = q.front;
		NodeQueue c = q.front;
	    for(int i=0;i<q.size();i++)
	    {
	    	if((cur.data<=t) && (rt[i]>0) && (rt[i] < minm))
	    	{   
	    		minm= rt[i];
	    		shortest = i;
	    		check = true;
	    		d[h] = cur.pid;
	    		h++;
	    		}
	    	//System.out.println(d[h-1]);
	    	//System.out.println(shortest);
	    	//c = cur;
	    	if(cur!=q.end) 
	    		cur = cur.next;
	    	}
	    	    	//System.out.println(t);
	               
	    	    	z[k]=d[h-1];
	    	    	k++;
	               

	    //System.out.println(c.pid);
	    if(check == false)
	    {
	    	t = t +1;
	    	continue;
	    }
	    //System.out.println(min);
	    rt[shortest] =rt[shortest]-1;
	    minm = rt[shortest];
	    if(minm ==0) minm= Double.MAX_VALUE;
	    if(rt[shortest]==0) { complete++;
	    check = false;
	    ct[shortest] = t+1;   //finish time 
	    //System.out.println(ct[shortest]);
	    //System.out.println(ct[shortest]);
	    wt[shortest] = ct[shortest] - at[shortest]-bt[shortest];
	    if(wt[shortest]<0)
	    	wt[shortest] =0;}
	    t = t+1;
}
	
		double start =mint;
		double end =0;
				if(q.size() ==1) {
			gantt.Enqueue(z[0], start, start+z.length);
		}
		else {
	for(int i=0;i<z.length-1;i++)
		{  
		
	if(z[i]!=z[i+1]) {
			end = i+mint+1;
	gantt.Enqueue(z[i], start, end);
	start = end;}
			if(i==z.length-2) 
		gantt.Enqueue(z[i], start,(double) z.length+mint);
		
		//System.out.println(z[i]);
	}}

	
	
	double twt =0;
	for(int i=0;i<q.size();i++)
		{twt+=wt[i];}
	double avg = twt/q.size();
	
	return avg;
	
}

}