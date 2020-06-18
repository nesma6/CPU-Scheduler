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
public class NodeQueue {
    public Double data;       // data = arriving time 
	public Double bt;
	
	public String pid;
	 public NodeQueue next;
	
	 public String getPID()
	 {
		 return this.pid;
	 }
	 public Double getBT()
	 {
		 return this.bt;
	 }
	 public Double getAT()
	 {
		 return this.data;
	 }
	public NodeQueue(String id , Double d,Double BT)
	{   pid = id;
	    bt =BT;
		data =d;
		next = null;
		}

	public NodeQueue() {
		data  = 0.0;
		bt =0.0;
		pid = " ";
		next = null;
	}
	

	

    
}
