package nonpreemptive_sjf;
public class linkedListSJF {
	private nodeSJF head;
	private nodeSJF tail;
	private int length;
	
	public linkedListSJF() {
		this.head =null;
		this.tail =null;
		this.length =0;
	}

	public nodeSJF getHead() {
		return head;
	}
	public nodeSJF getTail() {
		return tail;
	}
	
	public int getLength() {
		return length;
	}
	public void add_front(String Name,double Atime,double Btime)
	{
		nodeSJF mynode=new nodeSJF();
		mynode.setData(Name,Atime,Btime);
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
	public void add_back(String Name,double Atime,double Btime)
	{
		nodeSJF mynode=new nodeSJF();
		mynode.setData(Name,Atime,Btime);
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
			nodeSJF ptr=this.head;
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
	public void insert_at_index(processSJF p,int index)
	{
		
	}
	public void printList()
	{
		nodeSJF ptr=head;
		while(ptr!=null)
		{
			System.out.printf("%s start execution at =%f  and ended excecution at = %f \n",ptr.getNodeProcessName(),ptr.getData().getArrivalTime(),ptr.getData().getBurstTime());
			ptr=ptr.getNext();
		}
		System.out.println("----------------------------------------------------------------------------");
		
	}

	

}

