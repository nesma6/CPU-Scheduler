package priority;
public class queue {
	private linkedList mylist;

	public queue() {
		this.mylist = new linkedList();
	}
	public void enqueue(process p)
	{
		mylist.add_back(p.getProcessName(),p.getArrivalTime(),p.getBurstTime(), p.getPriority_no());
	}
	public void enqueue(String name,double Atime,double Btime, int priority)
	{
		mylist.add_back(name,Atime,Btime, priority);
	}
	public node dequeue()
	{
		node ptr=mylist.getHead();
		mylist.remove_front();
		return ptr;
	}
	public node getHead()
	{
		return mylist.getHead();
	}
	public node getTail()
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
