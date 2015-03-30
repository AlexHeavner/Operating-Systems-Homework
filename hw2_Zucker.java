/*
This assignment must be worked on the Linux platform.  Submit your source code via GeorgiaView in the main body and in an attachment, hw2_UourLastName.cpp, by the due day. You assignment is to write a C/C++ or Java program, which expands the assignment #1. The added functions of the program must include:

Assign each process an unique process ID, and a random number generated by the generator serves not only as the priority but also as the total time (in milliseconds) that is needed to run the process (CPU burst). Also, change the range of random numbers to [1-50].
Create a waiting queue that based on the first come first serve policy. The capacity of the queue is 7. The output of the priority queue (long term-queue) becomes the input of the waiting queue.
Create a ready queue that can handle 5 processes at a time in the Round Robin fashion. Each process is assigned two millisecond time slice to run on CPU. When a process has used up its time slice one time and still needs more time to run will be put back to the ready queue, otherwise the process will be terminated and moved out of the system.
Print out all the process IDs in the priority queue when a new process is entered the queue.
Print out all the process IDs in the waiting queue when a new process entered or a process moved out of the queue.
Print out all the process IDs and their time left to be executed when a process is entering or leaving (put back to the waiting queue or exit) the ready queue.
The relationships among the priority queue, the waiting queue, and the ready queue are shown below:

You should let your program first generates 10 processes with 10 random numbers along with 10 different process IDs, and put these processes in the priority queue. Note that the capacity of the priority queue is 10. When the priority queue is full, the system starts to move process to the waiting queue until there are 7 processes in the waiting queue, then the system starts to move processes into the ready queue, while the random number generator continues generating numbers for the new processes, your program at the same time is assigning process ID to the new processes. The program will stop generate process when the total number of processes entered the system is 25. You may dump the output to a file so that it will be easier to examine the output.

Your program will stop when all the processes are finished. Submit your source code via WebCT Vista in the main body and in an attachment, hw2_UourLastName.cpp, by the due day.

The output should look somewhat like:

Step 0:

The new generated process is P10 with priority 5

The processes in the priority queue are: P5:1 P3:3 P2:2 P1:4 P7:5 P4:4 P8:7 P9:6 P10:5 P6:7

The processes in the waiting queue are:

The processes in the ready queue is

Step 1:

The new generated process is P11 with priority 8

The processes in the priority queue are: P2:2 P3:3 P4:4 P1:4 P7:5 P6:7 P8:7 P9:6 P10:5 P11:8

The processes in the waiting queue are: P5:1

The processes in the ready queue is

Step 2:

The new generated process is P12 with priority 7

The processes in the priority queue are: P3:3 P1:4 P4:4 P10:5 P7:5 P6:7 P8:7 P9:6 P11:8 P12:7

The processes in the waiting queue are: P5:1 P2:2

The processes in the ready queue is

Step 3:

The new generated process is P13 with priority 2

The processes in the priority queue are: P13:2 P1:4 P4:4 P9:6 P10:5 P6:7 P8:7 P12:7 P11:8 P7:5

The processes in the waiting queue are: P5:1 P2:2 P3:3

The processes in the ready queue is

………………..

………………..
*/
import java.util.*;
import java.io.*;
public class hw2_Zucker 
{
	public static class Process implements Comparator<Process>, Comparable<Process>
	{
		private static int process_count = 0; 
		public String process_id; 
		public int priority; 
		public int time_left;

		public Process()
		{
			process_count++;
			process_id = "P" + process_count;
			Random randomGenerator = new Random();
			priority = randomGenerator.nextInt(50)+1;
			time_left = priority;
		}

		public String toString()
		{
			return process_id + ":" + priority;
		}

		public String timeLeft()
		{
			return process_id + ":" + time_left;
		}

		//Override the compareTo method
		public int compareTo(Process p)
		{
			return this.priority - p.priority;
		}

		//Override the compare method to sort the priority_time
		public int compare(Process a, Process b)
		{
			return b.priority - a.priority;
		}
	}

	public static void main(String[] args) throws IOException
	{
		FileWriter fw = new FileWriter("hw2.txt");

		int process_count = 0;
		int step_count = 0;

		PriorityQueue<Process> priority_queue = new PriorityQueue<Process>();
		final int PRIORITY_QUEUE_CAPACITY = 10;

		LinkedList<Process> waiting_queue = new LinkedList<Process>();
		final int WAITING_QUEUE_CAPACITY = 7;

		LinkedList<Process> ready_queue = new LinkedList<Process>();
		final int READY_QUEUE_CAPACITY = 5;

		//Load 10 Processes into the PriorityQueue
		while(process_count < 10)
		{
			priority_queue.add( new Process() );
			process_count++;
		} 

		while(!(priority_queue.size() == 0 && waiting_queue.size() == 0 && ready_queue.size() == 0))
		{
			Process current_process = null;	
			if(priority_queue.size() < PRIORITY_QUEUE_CAPACITY && process_count < 25)
			{
				current_process = new Process();
				process_count++;				
				priority_queue.add( current_process );
			}
			else 
			{
				//Priority Queue is full, move processes to waiting queue
				if(waiting_queue.size() < WAITING_QUEUE_CAPACITY && priority_queue.size() != 0)
				{
					Process pq_poll = priority_queue.poll();
					if( pq_poll != null )
						waiting_queue.add( pq_poll );
				}
				else
				{
					//Waiting Queue is full, move processes to ready queue
					if(ready_queue.size() < READY_QUEUE_CAPACITY && waiting_queue.size() != 0)
					{
						Process wq_poll = waiting_queue.poll();
						if(wq_poll != null)
							ready_queue.add( wq_poll );
					}
					else 
					{
						//Ready Queue is full
						//Run the Processes in the ready queue
						Process running_process = ready_queue.poll();
						if(running_process != null)
						{
							running_process.time_left -= 2;
							if(running_process.time_left > 0)
								ready_queue.add(running_process);	
						}
					}
				}

			}

			fw.write("Step " + step_count++ + ":\n");
			if(current_process != null)
				fw.write("The new generated process is: " + current_process.process_id + " with priority " + current_process.priority + "\n");
			else 
				fw.write("The new generated process is:    with priority \n");
			fw.write("The processes in the priority queue are: " + printPriorityQueue(priority_queue) + "\n");
			fw.write("The processes in the waiting queue are: " + printWaitingQueue(waiting_queue) + "\n");
			fw.write("The processes in the ready queue are: " + printReadyQueue(ready_queue) + "\n");
		}

		fw.close();
	}

	public static String printReadyQueue(LinkedList<Process> ready_queue)
	{
		String ret = "";

		for(Process p : ready_queue)
			ret += p.timeLeft() + " ";

		return ret.trim();
	}

	public static String printWaitingQueue(LinkedList<Process> waiting_queue)
	{
		String ret = "";

		for(Process p : waiting_queue)
			ret += p.toString() + " ";

		return ret.trim();
	}

	public static String printPriorityQueue(PriorityQueue<Process> priority_queue)
	{
		String ret = "";

		for(Process p : priority_queue)
			ret += p.toString() + " ";

		return ret.trim();
	}
}
