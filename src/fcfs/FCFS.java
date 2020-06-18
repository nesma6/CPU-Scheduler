package fcfs;
import java.util.Scanner;
import static fcfs.Queueofprocesses.Enqueue;
import static fcfs.Queueofprocesses.Print;
import static fcfs.Queueofprocesses.calculate_avg;
import static fcfs.Queueofprocesses.calculate_et;
import static fcfs.Queueofprocesses.calculate_st;
import static fcfs.Queueofprocesses.calculate_wt;
import static fcfs.Queueofprocesses.sortQueue;
public class FCFS {
  /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        Queueofprocesses Q=new Queueofprocesses();
        Queueofprocesses N=new Queueofprocesses();
        int n;
        Scanner c = new Scanner(System.in);
        System.out.print("Enter Number of Processes: ");
        n=c.nextInt();
        
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
        calculate_st(Q); //calculates servicetime
        calculate_wt(Q); //calculates waitingtime
        calculate_et(Q);
        float avg=calculate_avg(Q,n);
        Print(Q); //print all information about each process
        System.out.println("Average waiting time is = " + avg);
    }
        
        
    }
    

