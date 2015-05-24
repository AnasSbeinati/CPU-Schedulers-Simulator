package cpuScheduling;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Funs {
	static private void FIFS(Process processes[]) {
		Arrays.sort(processes, new ArrivingCompare());
		setResponseTime(processes);
	}

	static private void setResponseTime(Process[] processes) {
		int timeLine = processes[0].getArrivalTime();
		for (Process process : processes) {
			timeLine += process.getBurstTime();
			int compare = timeLine - process.getArrivalTime();
			if (compare > process.getBurstTime()) {
				process.setResponseTime(compare - process.getBurstTime());
			} else {
				process.setResponseTime(0);
			}
		}
	}

	static private double getAverResponseTime(Process[] processes) {
		int comu = 0;
		for (Process process : processes) {
			comu += process.getResponseTime();
		}
		return comu / processes.length;
	}

	static public void implementFCFS(Process[] processes) {
		FIFS(processes);
		double AverResponseTime = Funs.getAverResponseTime(processes);
		System.out.println("Process          Response Time");
		for (Process process : processes) {
			System.out.println(process.getName() + "          "
					+ process.getResponseTime());
		}
		System.out.println("Average Response Time  " + AverResponseTime);
	}

	static public void implementSJF(Process[] processes) {
		Arrays.sort(processes, new ArrivingCompare());
		processes = SJF(processes);
		setResponseTime(processes);
		double AverResponseTime = Funs.getAverResponseTime(processes);
		System.out.println("SJF\nProcess          Response Time");
		for (Process process : processes) {
			System.out.println(process.getName() + "          "
					+ process.getResponseTime());
		}
		System.out.println("Average Response Time  " + AverResponseTime);
	}

	static private Process[] SJF(Process[] processes) {
		ArrayList<Process> list = new ArrayList<Process>();
		ArrayList<Process> ganttChart = new ArrayList<Process>();
		int currentProcess = 0, timeLine = processes[currentProcess]
				.getArrivalTime();
		while ((currentProcess < processes.length) || (!list.isEmpty())) {
			if (list.isEmpty()) {
				Process process = processes[currentProcess];
				ganttChart.add(process);
				currentProcess++;
				// filling list
				timeLine += process.getBurstTime();
				while (currentProcess < processes.length) {
					if (processes[currentProcess].getArrivalTime() < timeLine) {
						list.add(processes[currentProcess]);
						currentProcess++;
					} else
						break;
				}
				Collections.sort(list, new ShortesJobCompare());
			} else {
				while (!list.isEmpty()) {
					Process process = list.get(0);
					list.remove(0);
					ganttChart.add(process);
					// filling list
					timeLine += process.getBurstTime();
					while (currentProcess < processes.length) {
						if (processes[currentProcess].getArrivalTime() < timeLine) {
							list.add(processes[currentProcess]);
							currentProcess++;
						} else
							break;
					}
					Collections.sort(list, new ShortesJobCompare());
				}
			}
		}
		Process[] tempProcesses = new Process[processes.length];
		for (int i = 0; i < tempProcesses.length; i++) {
			tempProcesses[i] = new Process();
			tempProcesses[i] = ganttChart.get(0);
			ganttChart.remove(0);

		}
		return tempProcesses;
	}

	static public void implementRR(Process[] processes, int quantum) {
		Arrays.sort(processes, new ArrivingCompare());
		processes = RR(processes, quantum);
		double AverResponseTime = Funs.getAverResponseTime(processes);
		System.out.println("Process          Response Time");
		for (Process process : processes) {
			System.out.println(process.getName() + "          "
					+ process.getResponseTime());
		}
		System.out.println("Average Response Time  " + AverResponseTime);
	}

	static public Process[] RR(Process[] processes, int quantum) {
		Queue<Process> queue = new LinkedList<Process>();
		ArrayList<Process> ganttChart = new ArrayList<Process>();
		int timeLine = processes[0].getArrivalTime();
		int numOfPr = 0, g = numOfPr;
		queue.add(processes[0]);
		numOfPr++;
		g = numOfPr;
		while ((!queue.isEmpty()) || (numOfPr < processes.length)) {
			Process process;
			if (!queue.isEmpty())
				process = queue.poll();
			else {
				process = processes[numOfPr++];
				g++;
			}
			if (process.getBurstTime() > quantum) {
				process.setBurstTime(process.getBurstTime() - quantum);
				Process tempProcess = new Process(process);
				ganttChart.add(tempProcess);
				timeLine += quantum;
				for (int i = g; i < processes.length; i++) {
					if (processes[i].getArrivalTime() <= timeLine) {
						queue.add(processes[i]);
						numOfPr++;
					} else
						break;
				}
				g = numOfPr;
				queue.add(process);
			} else {
				timeLine += process.getBurstTime();
				if (process.getBurstTime() == quantum)
					process.setBurstTime(0);
				else {
					process.setCons(process.getBurstTime());
					process.setBurstTime(-1);
				}
				Process tempProcess = new Process(process);
				ganttChart.add(tempProcess);
				for (int i = g; i < processes.length; i++) {
					if (processes[i].getArrivalTime() <= timeLine) {
						queue.add(processes[i]);
						numOfPr++;
					} else
						break;
				}
				g = numOfPr;
			}
		}
		// set response time
		ArrayList<Process> temp = new ArrayList<Process>();
		ganttChart.get(0).setResponseTime(0);
		temp.add(ganttChart.get(0));
		if (ganttChart.get(0).getBurstTime() < quantum) {
			if (ganttChart.get(0).getBurstTime() == 0)
				timeLine = ganttChart.get(0).getArrivalTime() + quantum;
			else if (ganttChart.get(0).getBurstTime() == -1)
				timeLine = ganttChart.get(0).getArrivalTime()
						+ ganttChart.get(0).getCons();
			else
				timeLine = ganttChart.get(0).getArrivalTime()
						+ ganttChart.get(0).getBurstTime();
		} else
			timeLine = ganttChart.get(0).getArrivalTime() + quantum;
		int j = 1;
		for (int i = 1; i < processes.length; i++) {
			while (j < ganttChart.size()) {
				if (!temp.contains(ganttChart.get(j))) {
					ganttChart.get(j).setResponseTime(timeLine);
					temp.add(ganttChart.get(j));
					if (ganttChart.get(j).getBurstTime() < quantum) {
						if (ganttChart.get(j).getBurstTime() == 0)
							timeLine += ganttChart.get(j).getArrivalTime()
									+ quantum;
						else if (ganttChart.get(j).getBurstTime() == -1)
							timeLine += ganttChart.get(j).getArrivalTime()
									+ ganttChart.get(j).getCons();
						else
							timeLine += ganttChart.get(j).getArrivalTime()
									+ ganttChart.get(j).getBurstTime();
					} else {
						timeLine += quantum;
					}
					j++;
					break;
				}
				if (ganttChart.get(j).getBurstTime() < quantum) {
					if (ganttChart.get(j).getBurstTime() == 0)
						timeLine += ganttChart.get(j).getArrivalTime()
								+ quantum;
					else if (ganttChart.get(j).getBurstTime() == -1)
						timeLine += ganttChart.get(j).getArrivalTime()
								+ ganttChart.get(j).getCons();
					else
						timeLine += ganttChart.get(j).getArrivalTime()
								+ ganttChart.get(j).getBurstTime();
				} else {
					timeLine += quantum;
				}
				j++;
			}
		}
		for (int i = 0; i < processes.length; i++) {
			processes[i].setResponseTime(temp.get(0).getResponseTime());
			temp.remove(0);
		}
		for (Process process2 : ganttChart) {
			System.out.print(process2.getName() + "  ");
		}
		System.out.println("-----------------------------------------------");
		return processes;
	}
}