/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

/**
 *
 * @author PoWeR
 */
public class Queue {
    public NodeQueue front ;    //head of queue (head)     //Dequeue operation
	public NodeQueue end; //rare of queue(tail)  //Enqueue operation
	public NodeQueue getFront()
	{
		return this.front;
	}
	
	public Queue(){
		front = null;
		end = null;
		}
	//public Queue(sting p,int ar)
	public  void Enqueue(String p,Double at,Double d) {
		NodeQueue temp = new NodeQueue(p,at, d);   //make new node
		if(IsEmpty()) {  //queue is empty 
			this.front =this.end= temp;
		}
		else 
			{
			this.end.next = temp;
		    this.end = temp;}
		}
	public void  Dequeue()
	{
		if (IsEmpty())return ;
	  else 
		{//NodeQueue temp1 = front;
		//int x = front.data;
		this.front  = this.front.next;
	
		}
		}
	public Boolean IsEmpty()
	{
	  return (this.front == null);	
	}
	
	public String  front()
	{
		if (IsEmpty())return "-1";
		else
			return this.front.pid;     //return arrival time 
	}
	public Integer size()
	{ if(IsEmpty()) return 0;
		Integer counter =1;
		NodeQueue index = this.front;
		while(index != this.end) {
			index = index.next;
			counter++;
		}
		return counter;
		
	}
        public static void clear(Queue q)
	{
		NodeQueue cur = q.front;
		while(cur != null)
		{
			q.Dequeue();
			cur = cur.next;
		}
	}
	
	
	
	
	/*public int min_index(int sortedIndex)
	{
		int minindex =-1;
	    int minvalue = Integer.MAX_VALUE;
		for(int i=0;i<this.size();i++)
		{
			int current = this.front();
			this.Dequeue();
			if(current<=minvalue &&  i <= sortedIndex)
			{minindex = i;
			 minvalue = current;
			}
			this.Enqueue(current);
		}
		return minindex;
	}
	public int MinIndex(int sortedIndex)
	{
		int minIndex = 0;
		int current = this.front();
		Node Next = this.front.next;
		for(int i=1;i<this.size();i++)
		{
			if(Next.data < current && i <= sortedIndex) {
				minIndex  =i;
				
			current = Next.data;
			Next = Next.next;}
		}
		return minIndex;
	}
	public void insertMinTo_Rear(int minindex)
	{
		int minvalue =0;
		for(int i = 0 ;i<this.size();i++)
		{
			int current = this.front();
			this.Dequeue();
			if(i != minindex)
			this.Enqueue(current);
			else
				minvalue = current;
		}
		this.Enqueue( minvalue );
	}
	
	
	public void sort_Queue()
	{ for(int i =1;i<=this.size();i++) {
		int minindex = this.MinIndex(this.size()-i);
		this.insertMinTo_Rear(minindex);}
		
	}*/
	public static void swap( NodeQueue d1, NodeQueue d2)
	{
		final Double  temp = d1.data;
		d1.data = d2.data;
		d2.data = temp;
		final Double temp2 = d1.bt;
		d1.bt = d2.bt;
		d2.bt  = temp2;
		final String temp3 = d1.pid;
		d1.pid = d2.pid;
		d2.pid = temp3;
	}

	//////////////////////////////////////////////////////Sort according to burst time && arriving time  ////////////////////////
        public static double Sort1(Queue q,double ct[],double wt[]) {
		NodeQueue first = q.front;                   //current =arr[i];//next = arr[j];
		  NodeQueue sec = q.front;
		  double tbt = 0;
		  NodeQueue c = q.front;
		  int k=0;
		  for(int i=0;i<q.size();i++)
		  { tbt+=c.bt;
		    c = c.next;
	      }
		  // sort ccording to arriving time
		  for(int i=0;i<q.size()-1;i++)
		  {     sec = first.next;
			  for(int j=1;j<q.size();j++)
			  {
				  if(sec.data < first.data)
				  {
					  swap(sec,first);
				  }
				  if(sec!=q.end)
				  sec = sec.next; }
			  first = first.next;
			  }
		                  //current =arr[i];//next = arr[j];
		  NodeQueue first1 = q.front;
		  NodeQueue min = first1;  
		  int complete =0;
		  int n=1;
		  int error =0;
		  int s = q.size();
		  double t=min.data;            //as min first element in queue
		  while(t<tbt) {
			 NodeQueue sec1 = min.next;
			 
			  for(int j=1;j<s;j++)
			  {     
				  if(sec1.bt<min.bt && sec1.data<=t)
				  {
					  min = sec1;                      //to select min burst time and arriving time 
					  //swap(min,s);
					 // s = s.next;
					  //error =1;
				  }
				  if(sec1!=q.end)
				  sec1 = sec1.next;
			  }
			  complete++;
			  //System.out.println(min.pid);
			  t += min.bt;
			  ct[k] = t;
			  wt[k] = ct[k]-min.data-min.bt;
			  k++;
			 swap(min,first1);
			// t += first.bt;
			 // System.out.println(first1.pid);
			  if(first1!=q.end)
			first1 = first1.next;
			 if(min.next!=q.end) {
			 min =first1;
			 sec1 = min.next;}
			 
			// System.out.println(s);
			 s--;}
		  double twt =0;
		  for(int i=0;i<q.size();i++)
			  twt+=wt[i];
		  double avg = twt/q.size();
		  return avg;
		  }
        
        
        
	/*public static void Sort1(Queue q) {
		NodeQueue first = q.front;                   //current =arr[i];//next = arr[j];
		  NodeQueue sec = q.front.next;
		   for(Integer it=0;it<q.size()-1;it++)
			{ NodeQueue min = first;
				while(sec != q.end.next) {
					if(sec.bt <min.bt ||(sec.bt == min.bt && sec.data<min.data)) 
						min = sec;
					sec = sec.next;
                  }
				swap(min, first);				
				 first = first.next;
				 sec = first.next;}
				}*/
			

	/////////////////////////////////////////////Sort////////////////////////////////////////	
	
	//////////////for  gantt chart ////////////////
        
        public static void ganttchart(Queue q,Queue gantt)
	{   //double twt =0;
		//wt[0] = 0;             // waiting time of first process
	 /* Node curr = q.front;
	  wt[0] =curr.data;
	    for(int i=1;i<q.size();i++)
	    {
	    	wt[i] = curr.bt + wt[i-1];
	    	
	    	curr = curr.next;
	    }
	    for(int i=0;i<q.size();i++)
	    {
	    	twt +=wt[i];
	    }*/
	    NodeQueue cur = q.front;
	    double j =cur.data;
		double end =cur.data;
		for(int i=0;i<q.size();i++)
		{    String p  = cur.pid;
		     double start = j;
		      end += cur.bt;
		     j  += cur.bt;
		     cur = cur.next;
		     gantt.Enqueue(p, start, end);
		     }
	    	    
	  }
        
        
        
		/*public static Double findWaitingTime(Queue q,Double wt[],Queue gantt)
	   {   Double twt =0.0;
		wt[0] = 0.0;             // waiting time of first process
	  NodeQueue curr = q.front;
	    for(Integer i=1;i<q.size();i++)
	    {
	    	wt[i] = curr.bt + wt[i-1];
	    	curr = curr.next;
	    }
	    for(Integer i=0;i<q.size();i++)
	    {
	    	twt +=wt[i];
	    }
            
            NodeQueue cur = q.front;
	    double j =0;
		double end =0;
		for(int i=0;i<q.size();i++)
		{    String p  = cur.pid;
		     double start = j;
		      end += cur.bt;
		     j  += cur.bt;
		     cur = cur.next;
		     gantt.Enqueue(p, start, end);
		     }
	    return twt;
	    
	  }*/
                public static void completiontime(Queue q,Double wt[],Double comp[])
	{ 
		NodeQueue curr = q.front;
		
		for(Integer i=0;i<q.size();i++)
		{
			comp[i] = curr.bt  +wt[i];
			curr = curr.next;
		}
	}
	

    
}
