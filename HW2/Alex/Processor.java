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
		this.time_slice = time_slice;
		this.timer = new Timer(1, this);
	}
	
	public Process run(Process process)
	{
		this.current_process = process;
		this.time_count = 0;
		this.timer.start();
		
		while((this.time_count < this.time_slice) && (this.current_process.getTimeToRun() > 0))
		{
			System.out.print("");
		}
		timer.stop();
		return this.current_process;
	}

	public void actionPerformed(ActionEvent ae)
	{
		this.current_process.timeToRunDec();
		this.time_count++;
	}
	
	
}
