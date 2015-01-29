/*This assignment must be worked on the Linux platform. Your assignment is to write a C/C++ or Java program that can handle 
jobs in a long-term queue of an operating system based on priorities. The functions of the program must include:

A random number generator that can generate numbers between 1 and 100. A number generated by the generator would be a 
priority of a job and will be put in the queue after it has been generated.

A priority queue with size ten to handle the priority mechanism. The highest priority job will be print out first. 
The info to print out is the random number that represents the priority of the job.

At first you should let your program generates at least 10 random numbers, and put these numbers in the priority queue. 
Thereafter, the priority queue starts to dispatch the highest priority number while the random number generator continues 
generating numbers. Note that the lower number has higher priority. Every time the highest priority number has been dispatched 
and a random number is generated your must print out these number along with the numbers in the queue. You deem that the highest 
priority number dispatching and the random number generating are happening at the same time. Your output should look somewhat 
like Figure 1 in the attachment or as follows:

The current dispatched number is 2
  The new coming number is 20
The numbers in the queue are 3 6 5 9 8 7 35 11 10 20
The current dispatched number is 3
  The new coming number is 1
The numbers in the queue are 1 5 7 9 6 20 35 11 10 8
The current dispatched number is 1
The new coming number is 90
The numbers in the queue are 5 6 7 9 8 20 35 11 10 90
           .......

Note that putting numbers in a sorted list as a queue is not acceptable because it is not efficient. You must implement the priority 
queue data structure in your assignment.

Your program will keep running until there are 30 random numbers generated. Save your source code in a file named 
"assign1_yourlastname.cpp" and submit it in an attachment via WebCT Vista (also copy and paste your source code in the main body).
*/
import java.util.Random;
import java.util.PriorityQueue;
import java.util.Iterator;

public class assign1_zucker
{
	public static void main(String[] args)
	{
		//how many random numbers to generate until the program terminates
		final int RANDOM_NUMBERS_TO_GENERATE = 30; 

		//keeps track of how many numbers have been demonstrated
		int numbersGenerated = 0;

		//Random number generator
		Random rand = new Random();

		//holds the random number
		int rand_num = 0; 

		//Creates Priority Queue of size 10 
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10);

		//inserts 10 random numbers into the priority queue
		while(numbersGenerated < 10)
		{
			//adds a random generated number from 0 to 100; including 0, excluding 100. Then adds 1 to the priority queue
			pq.add(rand.nextInt(100)+1);
			numbersGenerated++;
		}

		while(numbersGenerated < 30)
		{
			System.out.println("The current dispatched number is " + pq.poll());

			//Generates a random number from 0 to 100; including 0, excluding 100. Then adds 1 
			rand_num = rand.nextInt(100)+1;
			numbersGenerated++;

			System.out.println("\tThe new coming number is " + rand_num);

			//insert new random number into priority queue
			pq.add(rand_num);

			//print out the elements in the queue
			System.out.println("The numbers in the queue are " + priorityQueueToString(pq));
		}
	}

	public static String priorityQueueToString(PriorityQueue<Integer> pq)
	{
		//Iterate through priority queue
		Iterator it = pq.iterator();

		String str = "";

		while(it.hasNext())
			str += it.next() + " ";

		return str.trim();
	}
}