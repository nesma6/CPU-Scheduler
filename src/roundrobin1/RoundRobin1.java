/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundrobin1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import static roundrobin1.QueueOfprocess.Enqueue;
import static roundrobin1.QueueOfprocess.Pend;
import static roundrobin1.QueueOfprocess.Pname;
import static roundrobin1.QueueOfprocess.Print;
import static roundrobin1.QueueOfprocess.Pser;
import static roundrobin1.QueueOfprocess.RR;
import static roundrobin1.QueueOfprocess.sortQueue;


public class RoundRobin1 {

   
    public static void main(String[] args) {
        QueueOfprocess Q=new QueueOfprocess();
        QueueOfprocess N=new QueueOfprocess();
        int n , q;
        Scanner c = new Scanner(System.in);
        System.out.print("Enter Number of Processes: ");
        n=c.nextInt();
        System.out.print("Enter The quantum: ");
        q=c.nextInt();
        
        for (int i=0;i<n;i++)
        {
            Scanner o = new Scanner(System.in);
            Process P=new Process();
            String pn;
            System.out.print("Enter Process Name: ");
            pn= o.nextLine();
            System.out.print("Enter "+ pn + " Burst Time: " ); 
            float bt=o.nextFloat();
            System.out.print("Enter " + pn + " Arrival Time: " ); 
            float at=o.nextFloat();
            P.settimes(pn,bt,at);
            Enqueue(Q,P);
            Enqueue(N,P);
        }
        sortQueue(Q,N,n); //sorting the pocesses according to its arrive time
      //  Print(Q);
      Queue<String> names = new LinkedList<>();
      Queue<Float> StartTime= new LinkedList<>();
      Queue<Float> EndTime= new LinkedList<>();

       QueueOfprocess output = new QueueOfprocess();
        float processnames = RR(Q ,q ,output);
        
//        Print(output);
        names = Pname(output);
        StartTime = Pser(output);
        EndTime = Pend(output);
       System.out.println(processnames);
        System.out.println(names);
        System.out.println(StartTime);
        System.out.println(EndTime);
    }
    
}
