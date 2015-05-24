package cpuScheduling;

public class Process {
	String name;
	private int arrivalTime;
	private int burstTime;
	private int responseTime;
	private int cons;
	public int getCons() {
		return cons;
	}
	public void setCons(int cons) {
		this.cons = cons;
	}
	public Process(Process process) {
		name=process.getName();
		arrivalTime=process.getArrivalTime();
	    burstTime=process.getBurstTime();
	    responseTime=process.getResponseTime();
	    cons=process.getCons();
	}
	public Process() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getBurstTime() {
		return burstTime;
	}
	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}
	
}
