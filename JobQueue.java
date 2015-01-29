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
			
			System.out.print("The numbers in the queue are ");
			for(int element: queue)
				System.out.print(element + " ");
			
			System.out.println();
		}
	}
}
