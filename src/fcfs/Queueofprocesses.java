/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Mony's HP Probook
 */
public class Queueofprocesses {
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
    public static void clear (Queueofprocesses Q)
    {
        Q.front=null;
    }
    public static Queueofprocesses Enqueue (Queueofprocesses Q,Process p) //to add element from the rear end
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
     public static Queueofprocesses Dequeue (Queueofprocesses Q) //to remove element from the front end
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
     public static Queueofprocesses CopyofQueue (Queueofprocesses Q)
     {

         Queueofprocesses N=Q;
         return N;
     }
     public static float Getfront(Queueofprocesses Q)
     {
         return Q.front.process.Arrivaltime;
     }
     public static int numberofpro (Queueofprocesses Q)
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
     public static void Print (Queueofprocesses Q)
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
 //to reduce time but will not use it nw
//     public static int dupileditems (Queueofprocesses Q,float at)
//     {
//         int count=0;
//         Node currentNode=Q.front;
//         Process P=new Process();
//         while (currentNode!=null)
//         {
//             if (currentNode.process.Arrivaltime==at)
//             {
//                 count++;
//             }
//         }
//     return count;
//     }
    public static boolean Isthere (Queueofprocesses Q, Process P)
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
    public static Process Getprocess(Queueofprocesses N,Queueofprocesses Q, float at)
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
    
     public static int minIndex(Queueofprocesses Q, 
                                     int sortIndex,int s,Queueofprocesses N) 
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
     
     public static void insertMinToRear(Queueofprocesses Q, 
                                             int min_index,int s,Queueofprocesses N) 
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
     public static Queueofprocesses sortQueue(Queueofprocesses Q,Queueofprocesses N,int s) 
    { 
    
        for(int i = 1; i <= s; i++) 
        { 
            int min_index = minIndex(Q,s-i,s,N); 
            insertMinToRear(Q, min_index,s,N); 
        } 
        return Q;
    } 
    public static void calculate_st (Queueofprocesses Q)
    {
        
        Node currentNode=Q.front;
        Node prevNode=null;
        float wait=currentNode.process.Arrivaltime;
        while (currentNode!=null)
        {
            
            currentNode.process.servicetime=wait;
            if (prevNode!=null)
            {
            if (currentNode.process.servicetime<prevNode.process.servicetime+prevNode.process.Bursttime)
            {
                currentNode.process.servicetime=prevNode.process.servicetime+prevNode.process.Bursttime;
            }
            }
            if (currentNode.process.servicetime<currentNode.process.Arrivaltime)
            {
             currentNode.process.servicetime=currentNode.process.Arrivaltime;
            }
            wait+=currentNode.process.Bursttime;
            prevNode=currentNode;
            currentNode=currentNode.next;
            
            
        }
    }
    public static void calculate_wt (Queueofprocesses Q)
    {
        
        Node currentNode=Q.front;
        while (currentNode!=null)
        {
            currentNode.process.waitingtime=currentNode.process.servicetime-currentNode.process.Arrivaltime;
            currentNode=currentNode.next;
        }
    }
    public static void calculate_et (Queueofprocesses Q)
    {
        
        Node currentNode=Q.front;
        while (currentNode!=null)
        {
            currentNode.process.endingtime=currentNode.process.servicetime+currentNode.process.Bursttime;
            currentNode=currentNode.next;
        }
    }
    public static float calculate_avg (Queueofprocesses Q,int numberofprocesses)
    {
        float avg=0;
        float sum=0;
        Node currentNode=Q.front;
        while (currentNode!=null)
        {
            sum+=currentNode.process.waitingtime;
            currentNode=currentNode.next;
        }
        avg=sum/numberofprocesses;
        return avg;
    }
        public static Queue Pname (Queueofprocesses Q)
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
        public static Queue Pser (Queueofprocesses Q)
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
        public static Queue Pend (Queueofprocesses Q)
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

}
