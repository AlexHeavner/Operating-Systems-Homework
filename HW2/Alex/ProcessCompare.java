import java.util.Comparator;


public class ProcessCompare implements Comparator{
	@Override
	//Process comparator method to be used in priority queue
	public int compare(Object p1, Object p2)
	{
		if(((Process) p1).getTimeToRun() > ((Process) p2).getTimeToRun())
		{
			return 1;
		}
		if(((Process) p1).getTimeToRun() < ((Process) p2).getTimeToRun())
		{
			return -1;
		}
		
		return 0;
	}
}
