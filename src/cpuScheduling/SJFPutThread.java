package cpuScheduling;

import java.util.PriorityQueue;

public class SJFPutThread extends Thread {
	private Process[] processes;
	private PriorityQueue<Process> queue;
	public SJFPutThread(Process[] processes, PriorityQueue<Process> queue) {
		super();
		this.processes = processes;
		this.queue = queue;
	}

	@Override
	public void run() {
		for (int i = 0; i < processes.length; i++) {
			queue.add(processes[i]);
			System.out.println("I put process "+ i);
			if(i<processes.length-1)
				try {
					Thread.sleep(processes[i+1].getArrivalTime()-processes[i].getArrivalTime());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
}
