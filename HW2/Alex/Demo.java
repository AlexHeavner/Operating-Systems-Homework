import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;


public class Demo {
	public static void main(String[] args) throws IOException
	{
		//Define Constants
		final int TIME_SLICE = 2;
		final int READY_MAX = 5;
		final int WAITING_MAX = 7;
		final int PRIORITY_MAX = 10;
		
		//create IO object
		PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		
		//Creates processor object 
		Processor processor = new Processor(TIME_SLICE);
		
		//Creates random object to be used in process creation
		Random rand = new Random();
		
		//Create ready and waiting queues
		Queue<Process> ready = new LinkedList<Process>();
		Queue<Process> waiting = new LinkedList<Process>();
		
		//Create comparator for priority queue and the priority queue
		Comparator<Process> compare = new ProcessCompare();
		PriorityQueue<Process> priority = new PriorityQueue<Process>(PRIORITY_MAX, compare);
		
		//create list to hold finished processes
		LinkedList <Process> finished = new LinkedList<Process>();
		
		//Load Priority Queue
		for(int i = 0 ; i <= 10; i++)
		{
			priority.add(new Process(rand.nextInt(50) + 1));
		}
		
		//Start main running loop
		int step = 0;
		for(int process_count = 0; process_count < 25;)
		{
			//Write out step
			writer.println("Step " + step + ":");
			step++;
			
			if(priority.size() < PRIORITY_MAX)
			{	
				Process priority_helper = new Process(rand.nextInt(50) + 1);
				priority.add(priority_helper);
				process_count++;
				writer.println("The new generated process is P"+priority_helper.getID()+" with priority "+priority_helper.getTimeToRun());
			}
			
			if(waiting.size() < WAITING_MAX && priority.size() > 0)
			{
				waiting.add(priority.poll());
			}
			
			if(ready.size() < READY_MAX && waiting.size() > 0)
			{
				ready.add(waiting.poll());
			}
			
			if(ready.size() > 0)
			{
				Process helper = processor.run(ready.poll());
				
				if(helper.getTimeToRun() == 0)
				{
					finished.add(helper);
				}
				else
				{
					ready.add(helper);
				}
			}
			//Write contents of priority queue
			writer.print("The processes in the priority queue are: ");
			for(Process element: priority)
			{
				writer.print("P"+element.getID()+":"+element.getTimeToRun()+" ");
			}
			writer.println();
			
			//Write contents of waiting queue
			writer.print("The processes in the waiting queue are: ");
			for(Process element: waiting)
			{
				writer.print("P"+element.getID()+":"+element.getTimeToRun()+" ");
			}
			writer.println();
			
			//Write contents of ready queue
			writer.print("The processes in the ready queue are: ");
			for(Process element: ready)
			{
				writer.print("P"+element.getID()+":"+element.getTimeToRun()+" ");
			}
			writer.println("\n");
			
		}
		
		writer.close();
		System.out.println("Done.");
	}
}
