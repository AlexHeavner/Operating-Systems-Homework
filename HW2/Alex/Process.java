
public class Process {
	
	private int ID;
	private int time_to_run;
	
	//Used to assign new processes unique IDs
	private static int ID_count = 0;
	
	public Process(int time_to_run)
	{
		this.ID = ID_count;
		ID_count ++;
		
		this.time_to_run = time_to_run;
	}
	
	public void timeToRunDec()
	{
		this.time_to_run -- ;
	}
	
	public int getID(){return this.ID;}
	
	public int getTimeToRun(){return this.time_to_run;}

}
