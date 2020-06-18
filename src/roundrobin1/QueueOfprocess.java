
package roundrobin1;
import java.util.LinkedList;
import java.util.Queue;


public class QueueOfprocess {
    Node front;
    Node rear;
    static class Node 
    {
        Process process;
        Node next;
        Node(Process P)
        {
            process=P;
            next=null;
        }
    }
    public static QueueOfprocess Enqueue (QueueOfprocess Q,Process p) //to add element from the rear end
    {
        Node newNode=new Node(p);
        if (Q.front==null)
        {
            Q.front=newNode;
            Q.rear=newNode;
        }
        else 
        {
          Node last=Q.rear;
          last.next=newNode;
          Q.rear=newNode;
        }
        return Q;
    }
     public static QueueOfprocess Dequeue (QueueOfprocess Q) //to remove element from the front end
    {
        Node Temp=Q.front;
        if (Temp==null)
        {
            System.out.println("Cannot Dequeue Empty Queue!");
            return Q;
        }
        Q.front=Temp.next;
        return Q;
    }
     public static float Getfront(QueueOfprocess Q)
     {
         return Q.front.process.Arrivaltime;
     }
     public static int numberofpro (QueueOfprocess Q)
     {
         int length=0;
         Node currentNode=Q.front;
         while (currentNode!=null)
        {
            length++;
            currentNode=currentNode.next;
        }
         return length;
     }
     public static boolean Isthere (QueueOfprocess Q, Process P)
    {
        Node currentNode=Q.front;
        while (currentNode!=null)
        {
            if (currentNode.process==P)
            {
                return true;
            }
            currentNode=currentNode.next;
        }
        return false;
    }
     public static Process Getprocess(QueueOfprocess N,QueueOfprocess Q, float at)
     {
         Node currentNode=N.front;
         Process P= new Process();
        while (currentNode!=null)
        {
            
            if (currentNode.process.Arrivaltime==at)
            {
                P=currentNode.process;
                if (Isthere(Q,P))
                {
                    currentNode=currentNode.next;
                    continue;
                }
                else break;
            }
            
         
         currentNode=currentNode.next;
        }
        return P;
     }
     public static int minIndex(QueueOfprocess Q, 
                                     int sortIndex,int s,QueueOfprocess N) 
    {
 
    int min_index = -1; 
    float min_value = Float.MAX_VALUE; 
    for (int i = 0; i < s; i++) 
    { 
        float current = Q.Getfront(Q); 
        Process p;
        // This is dequeue() in Java STL 
        Q.Dequeue(Q); 
        p=Getprocess(N,Q,current);
        // we add the condition i <= sortIndex 
        // because we don't want to traverse 
        // on the sorted part of the queue, 
        // which is the right part. 
        if (current <= min_value && i <= sortIndex) 
        { 
            min_index = i; 
            min_value = current; 
        } 
        Q.Enqueue(Q,p);  
    } 
    return min_index; 
    }
     public static void insertMinToRear(QueueOfprocess Q, 
                                             int min_index,int s,QueueOfprocess N) 
     { 
        Process L=new Process();
        float min_value = 0;   
        for (int i = 0; i < s; i++) 
        { 
        float current = Q.Getfront(Q);
        Q.Dequeue(Q); 
        if (i != min_index) 
        {
            L=Getprocess(N,Q,current);
            Q.Enqueue(Q,L); 
        }
        else
            min_value = current; 
        } 
        Process P=new Process();
        P=Getprocess(N,Q,min_value);
        Q.Enqueue(Q,P); 
    } 
     public static QueueOfprocess sortQueue(QueueOfprocess Q,QueueOfprocess N,int s) 
    { 
    
        for(int i = 1; i <= s; i++) 
        { 
            int min_index = minIndex(Q,s-i,s,N); 
            insertMinToRear(Q, min_index,s,N); 
        } 
        return Q;
    } 
      public static void Print (QueueOfprocess Q)
    {
        Node currentNode=Q.front;
        System.out.println("Process Name"+"   "+"Bursttime"+"   "+"Arrivetime"+"   "+"Servicetime"+"   "+"waitingtime"+"    "+"endingtime");
        while (currentNode!=null)
        {
         
         System.out.print("      "+currentNode.process.processname + " " );
         System.out.print("        "+currentNode.process.Bursttime + " " );
         System.out.print("        "+currentNode.process.Arrivaltime + " " );
         System.out.print("        "+currentNode.process.servicetime + " " );
         System.out.print("           "+currentNode.process.waitingtime + " " );
         System.out.println("              "+currentNode.process.endingtime + " " );
         
         currentNode=currentNode.next;
        }
    }
      
           
     public static void SetToEnd(QueueOfprocess Q){
        if (Q.front == Q.rear)
            return;
        
        Node temp = Q.front;
        Q.front = Q.front.next;
        Q.rear.next = temp;
        Q.rear= temp;
        Q.rear.next=null;
        temp = null;
    }
     


      
public static float RR(QueueOfprocess Q, int q,QueueOfprocess _output)
{
      Queue<String> processnames = new LinkedList<>();
   // System.out.println(Q);
    QueueOfprocess ReadyQueue = new QueueOfprocess();
     QueueOfprocess output = _output;
//    System.out.println(ReadyQueue);
//    System.out.println(q);
    float  t=0;     //time now 
    float sum = 0;
    float waiting=0;
    Node currentNode=Q.front;
    t=currentNode.process.Arrivaltime;
//    System.out.println(t);
    boolean flag =true;
    float running_time = 0;
    ReadyQueue= Enqueue(ReadyQueue,currentNode.process);
    
    while(true)
    {
    if(ReadyQueue.front!=null)
    {
        flag=true;
    if(ReadyQueue.front.process.remburst>q)
    {
        running_time=q; 
       t=t+running_time;
        ReadyQueue.front.process.remburst=ReadyQueue.front.process.remburst-q;
    }
    else 
    {
         running_time = ReadyQueue.front.process.remburst;
         t=t+running_time;
         ReadyQueue.front.process.remburst=0;
    }
    }
    else
    {
        flag=false;
        t++;
    }
    while( currentNode.next!=null ) {  
        if(currentNode.next.process.Arrivaltime <= t){
        ReadyQueue= Enqueue(ReadyQueue,currentNode.next.process);
        }
        else
            break;
        currentNode = currentNode.next;
        }
    processnames.add(ReadyQueue.front.process.processname);
     
    if(flag)
    {
    if(ReadyQueue.front.process.remburst == 0 ){
        waiting+= t- ReadyQueue.front.process.Arrivaltime - ReadyQueue.front.process.Bursttime;
        output= Enqueue(output,new Process(ReadyQueue.front.process.processname,t-running_time,t));
        Dequeue(ReadyQueue);
    }
    else{
        output= Enqueue(output,new Process(ReadyQueue.front.process.processname,t-running_time,t));
        SetToEnd(ReadyQueue);
    }
    }
    if(ReadyQueue.front == null&&currentNode==Q.rear){
        break;
    }
    }
    
    int n = numberofpro(Q);
  //  System.out.println(n);
    float avr = waiting/n;
    
   // System.out.println(avr);
    
    return avr;

}
     public static Queue Pname (QueueOfprocess Q)
    {
        Queue<String> q = new LinkedList<>();
        Node currentNode=Q.front;
         while (currentNode!=null)
         {
            q.add( currentNode.process.processname);
            currentNode=currentNode.next;
         }
        return q;
    }
    
        public static Queue Pser (QueueOfprocess Q)
        {
        Queue<Float> q = new LinkedList<>();
        Node currentNode=Q.front;
         while (currentNode!=null)
         {
            q.add( currentNode.process.servicetime);
            currentNode=currentNode.next;
         }
        return q;
    }
        public static Queue Pend (QueueOfprocess Q)
    {
        Queue<Float> q = new LinkedList<>();
        Node currentNode=Q.front;
         while (currentNode!=null)
         {
            q.add( currentNode.process.endingtime);
            currentNode=currentNode.next;
         }
        return q;
    }
    public static void clear (QueueOfprocess Q)
    {
        Q.front=null;
    }
      


}
