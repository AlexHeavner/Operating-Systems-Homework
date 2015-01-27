import java.util.PriorityQueue;
import java.util.Random;

public class JobQueue 
{
	public static void main(String[] args)
	{
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(10);
		Random rand = new Random();
		
		for(int i = 0; i < 10; i++)
			queue.add(rand.nextInt(100)+1);
		
		int temp;
		for(int i = 0; i < 30; i++)
		{
			System.out.println("The current dispatched number is " + queue.poll());
			
			temp = rand.nextInt(100) + 1;
			System.out.println("The new coming number is " + temp);
			queue.add(temp);
			
			System.out.println(getContents(queue) + "\n");
		}
	}
	
	public static String getContents(PriorityQueue<Integer> queue)
	{
		Object[] queue_array =  queue.toArray();
		
		String return_string = "The numbers in the queue are ";
		
		for(int i = 0; i < queue_array.length; i++)
			return_string = return_string + queue_array[i] + " ";
		
		return return_string;
	}
}
