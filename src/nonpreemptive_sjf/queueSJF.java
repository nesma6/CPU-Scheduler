package nonpreemptive_sjf;
public class queueSJF {
	private linkedListSJF mylist;

	public queueSJF() {
		this.mylist = new linkedListSJF();
	}
	public void enqueue(processSJF p)
	{
		mylist.add_back(p.getProcessName(),p.getArrivalTime(),p.getBurstTime());
	}
	public void enqueue(String name,double Atime,double Btime)
	{
		mylist.add_back(name,Atime,Btime);
	}
	public nodeSJF dequeue()
	{
		nodeSJF ptr=mylist.getHead();
		mylist.remove_front();
		return ptr;
	}
	public nodeSJF getHead()
	{
		return mylist.getHead();
	}
	public nodeSJF getTail()
	{
		return mylist.getTail();
	}
	public boolean isEmpty()
	{
		return (mylist.getLength()==0)?true:false;
	}
	public int size()
	{
		return mylist.getLength();
	}
	public void printQueue()
	{
		mylist.printList();
	}
}
