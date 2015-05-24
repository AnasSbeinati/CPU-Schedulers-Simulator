package cpuScheduling;

import java.util.PriorityQueue;

public class SJFRemoveThread extends Thread {
	private Process[] processes;
	private PriorityQueue<Process> queue;
	public SJFRemoveThread(Process[] processes, PriorityQueue<Process> queue) {
		super();
		this.processes = processes;
		this.queue = queue;
	}
	@Override
	public void run() {
		int timeLine=0;
		int numProcesses=0;
		while(!queue.isEmpty()) {
			Process process=queue.remove();
			System.out.println("I removed process "+ numProcesses);
			process.setResponseTime(timeLine);
			processes[numProcesses]=process;
			try {
				Thread.sleep(process.getBurstTime());
				timeLine+=process.getBurstTime();
				numProcesses++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
