package nonpreemptive_sjf;
public class nodeSJF {
	private processSJF data;
	private nodeSJF next;
	
	//CONSTRUCTOR 
	public nodeSJF(processSJF data, nodeSJF next) {
		this.data = data;
		this.next = next;
	}
	public nodeSJF() {
		this.data=new processSJF("",0,0);
		this.next = null;
	}
	
	public nodeSJF(String name,double Atime,double Btime)
	{
		this.data.setProcessName(name);
		this.data.setArrivalTime(Atime);
		this.data.setBurstTime(Btime);
		this.next=null;
	}
	public nodeSJF(String name,double Atime,double Btime,nodeSJF next)
	{
		this.data.setProcessName(name);
		this.data.setArrivalTime(Atime);
		this.data.setBurstTime(Btime);
		this.next=next;
	}
	public String getNodeProcessName()
	{
		return this.data.getProcessName();
	}
	public double getNodeArrivalTime()
	{
		return this.data.getArrivalTime();
	}
	public double getNodeBurstTime()
	{
		return this.data.getBurstTime();
	}
	public processSJF getData()
	{
		return data;
	}
	public void setNodeProcessName(String name)
	{
		this.data.setProcessName(name);
	}
	public void setNodeArrivalTime(double time)
	{
		this.data.setArrivalTime(time);
	}
	public void setNodeBurstTime(double time)
	{
		this.data.setBurstTime(time);
	}
	
	public void setData(String name,double Atime,double Btime)
	{
		this.setNodeProcessName(name);
		this.setNodeArrivalTime(Atime);
		this.setNodeBurstTime(Btime);
	}
	public nodeSJF getNext() {
		return next;
	}
	public void setNext(nodeSJF next) {
		this.next = next;
	} 
}

