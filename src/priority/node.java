package priority;
public class node {
	private process data;
	private node next;
	
	//CONSTRUCTOR 
	public node(process data, node next) {
		this.data = data;
		this.next = next;
	}
	public node() {
		this.data=new process("",0,0,0);
		this.next = null;
	}
	
	public node(String name,double Atime,double Btime,int priority)
	{
		this.data.setProcessName(name);
		this.data.setArrivalTime(Atime);
		this.data.setBurstTime(Btime);
		this.data.setPriority_no(priority);
		this.next=null;
	}
	public node(String name,double Atime,double Btime,int priority,node next)
	{
		this.data.setProcessName(name);
		this.data.setArrivalTime(Atime);
		this.data.setBurstTime(Btime);
		this.data.setPriority_no(priority);
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
	public int getNodePriority()
	{
		return this.data.getPriority_no();
	}
	public process getData()
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
	
	public void setNodePriority(int priority)
	{
		this.data.setPriority_no(priority);
	}
	public void setData(String name,double Atime,double Btime,int priority)
	{
		this.setNodeProcessName(name);
		this.setNodeArrivalTime(Atime);
		this.setNodeBurstTime(Btime);
		this.setNodePriority(priority);
	}
	public node getNext() {
		return next;
	}
	public void setNext(node next) {
		this.next = next;
	} 
}

