import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Processor implements ActionListener{
	private int time_slice;
	private int time_count;
	private Timer timer;
	private Process current_process;
	
	public Processor(int time_slice)
	{
		//Sets time_slice from argument
		this.time_slice = time_slice;
		
		//creates new timer that will trigger every millisecond when started
		this.timer = new Timer(1, this);
	}
	
	//run(Process) does work on the process
	public Process run(Process process)
	{
		//Loads process
		this.current_process = process;
		
		//time_count used to manage number of cycles of work done on process
		this.time_count = 0;
		
		//starts the Processor timer
		this.timer.start();
		
		//busy work while timer is running
		while((this.time_count < this.time_slice) && (this.current_process.getTimeToRun() > 0))
		{
			System.out.print("");
		}
		//after time is up the timer is stopped and the process is returned
		timer.stop();
		return this.current_process;
	}

	public void actionPerformed(ActionEvent ae)
	{
		//Decrement process run time
		this.current_process.timeToRunDec();
		//increment time_count
		this.time_count++;
	}
	
	
}
