package priority;
public class linkedList {
	private node head;
	private node tail;
	private int length;
	
	public linkedList() {
		this.head =null;
		this.tail =null;
		this.length =0;
	}

	public node getHead() {
		return head;
	}
	public node getTail() {
		return tail;
	}
	
	public int getLength() {
		return length;
	}
	public void add_front(String Name,double Atime,double Btime,int priority)
	{
		node mynode=new node();
		mynode.setData(Name,Atime,Btime, priority);
		this.length++;
		
		//empty list
		if(head==null)
		{
			head=mynode;
			tail=mynode;
		}
		else
		{
			mynode.setNext(head);
			head=mynode;
		}
	}
	public void add_back(String Name,double Atime,double Btime,int priority)
	{
		node mynode=new node();
		mynode.setData(Name,Atime,Btime, priority);
		this.length++;
		
		//empty list
		if(head==null)
		{
			head=mynode;
			tail=mynode;
		}
		else
		{
			tail.setNext(mynode);
			tail=mynode;
		}
		
	}
	public void remove_front()
	{
		if(head!=null)
		{
			//1 element in list
			if(head==tail)
			{
				head=head.getNext();
				tail=tail.getNext();
			}
			else
			{
				head=head.getNext();
			}
			this.length--;
			
		}
		
	}
	public void remove_back()
	{
		if(this.head!=null)
		{
			node ptr=this.head;
			//1 element in list
			if(this.head==this.tail)
			{
				this.head=this.head.getNext();
				this.tail=this.tail.getNext();
			}
			else 
			{
				while(ptr.getNext()!=this.tail)
				{
					ptr=ptr.getNext();
				}
				this.tail=ptr;
				ptr.setNext(null);
			}
			
			this.length--;
		}
	}
	public void insert_at_index(process p,int index)
	{
		
	}
	public void printList()
	{
		node ptr=head;
		while(ptr!=null)
		{
			System.out.printf("%s start execution at =%f  and ended excecution at = %f \n",ptr.getNodeProcessName(),ptr.getData().getArrivalTime(),ptr.getData().getBurstTime());
			ptr=ptr.getNext();
		}
		System.out.println("----------------------------------------------------------------------------");
		
	}

	

}

