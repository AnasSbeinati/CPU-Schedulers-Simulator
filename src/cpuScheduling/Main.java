package cpuScheduling;

import java.util.Scanner;

public class Main {
	static public void main(String args[]) {
		//Filling info of processes
		int numOfProcesses=0,quantum=0;
		System.out.print("Enter the number of processes: ");
		numOfProcesses=new Scanner(System.in).nextInt();
		System.out.print("Enter the Quantum of Round Robin: ");
		quantum=new Scanner(System.in).nextInt();
		if(numOfProcesses>0) {
			Process[] processes=new Process[numOfProcesses];
			System.out.println("Type your processes charactarstics");
			for (int i = 0; i < numOfProcesses; i++) {
				processes[i]=new Process();
				System.out.println("Type for pocess: "+i+"  name");
				processes[i].setName(new Scanner(System.in).nextLine().toString());
				System.out.println("Type for pocess: "+i+"  Arrival Time");
				processes[i].setArrivalTime(new Scanner(System.in).nextInt());
				System.out.println("Type for pocess: "+i+"  Burst Time");
				processes[i].setBurstTime(new Scanner(System.in).nextInt());
			}
			Funs.implementFCFS(processes);
			Funs.implementSJF(processes);
			Funs.implementRR(processes, quantum);
		}
		
	}
    
}
