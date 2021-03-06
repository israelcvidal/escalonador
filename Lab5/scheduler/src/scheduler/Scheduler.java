package scheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import algorithms.AlgorithmReturn;
import algorithms.Algorithms;
import algorithms.Process;
import tools.FileManager;
import tools.ProcessesFromFile;
import tools.Statistics;
public class Scheduler {

	public static void main(String[] args) {
		int timeQuantum = 2;
		String pathLog = "/Users/israelcvidal/git/scheduler/files/logs/RR-log";
//		String pathLog = "/home/local/LSBD/israel.vidal/git/scheduler/files/logs/";
		try {
			if(args.length < 3)
				throw new Exception("Quantidade de argumentos inválida");
			String path = args[0], algorithm = args[1], outputType = args[2], line;
			ArrayList<algorithms.Process> processes = new ArrayList<>();
			BufferedReader reader = new BufferedReader(new FileReader(path));
			int processId, arrivalTime, burstTime, priority;
			
			//skip first line from file
			reader.readLine();
			//creating an array with the processes found in the file
			while( (line = reader.readLine()) != null ){
				processId = ProcessesFromFile.getProcessId(line);
				arrivalTime = ProcessesFromFile.getArrivalTime(line);
				burstTime = ProcessesFromFile.getBurstTime(line);
				priority = ProcessesFromFile.getPriority(line);
				processes.add(new Process(processId, arrivalTime, burstTime, priority));
			}
			reader.close();
			
			switch (algorithm.toUpperCase()) {
			case "FCFS":
				if (Integer.valueOf(outputType) == 1){
					FileManager fakeWriter = new FileManager();
					AlgorithmReturn  algorithmReturn = Algorithms.fcfs(processes, fakeWriter);
					Statistics statistics = new Statistics(processes, algorithmReturn);
					System.out.println("FCFS:");
					statistics.calculate();
					System.out.println(statistics);
				}else if(Integer.valueOf(outputType) == 2){
					FileManager writer = new FileManager(pathLog + "FCFS-log");
					Algorithms.fcfs(processes, writer);
					writer.close();
					System.out.println("SCHEDULING LOG SAVED IN 'FCFS-log'");
				}else throw new Exception("Tipo de saída inválida. Tente 1 ou 2!");
				break;
			
			case "SJF":
				if (Integer.valueOf(outputType) == 1){
					FileManager fakeWriter = new FileManager();
					AlgorithmReturn algorithmReturn = Algorithms.sjf(processes, fakeWriter);
					Statistics statistics = new Statistics(processes, algorithmReturn);
					statistics.calculate();
					System.out.println("SJF:");
					System.out.println(statistics);
				}else if(Integer.valueOf(outputType) == 2){
					FileManager writer = new FileManager(pathLog + "SJF-log");
					Algorithms.sjf(processes, writer);
					writer.close();
					System.out.println("SCHEDULING LOG SAVED IN 'SJF-log'");

				}else throw new Exception("Tipo de saída inválida. Tente 1 ou 2!");
				break;
				
			case "SJFP":
				if (Integer.valueOf(outputType) == 1){
					FileManager fakeWriter = new FileManager();
					AlgorithmReturn algorithmReturn = Algorithms.sjfp(processes, fakeWriter);
					Statistics statistics = new Statistics(processes, algorithmReturn);
					System.out.println("SJFP:");
					statistics.calculate();
					System.out.println(statistics);
				}else if(Integer.valueOf(outputType) == 2){
					FileManager writer = new FileManager(pathLog + "SJFP-log");
					Algorithms.sjfp(processes, writer);
					writer.close();
					System.out.println("SCHEDULING LOG SAVED IN 'SJFP-log'");
				}else throw new Exception("Invalid output. Try 1 or 2!");
				break;
				
			case "PRIORITY":
				if (Integer.valueOf(outputType) == 1){
					FileManager fakeWriter = new FileManager();
					AlgorithmReturn algorithmReturn = Algorithms.priority(processes, fakeWriter);
					Statistics statistics = new Statistics(processes, algorithmReturn);
					statistics.calculate();
					System.out.println("PRIORITY:");
					System.out.println(statistics);
				}else if(Integer.valueOf(outputType) == 2){
					FileManager writer = new FileManager(pathLog + "PRIORITY-log");
					Algorithms.priority(processes, writer);
					writer.close();
					System.out.println("SCHEDULING LOG SAVED IN 'PRIORITY-log'");
				}else throw new Exception("Invalid output. Try 1 or 2!");
				break;
			
			case "PRIORITYP":
				if (Integer.valueOf(outputType) == 1){
					FileManager fakeWriter = new FileManager();
					AlgorithmReturn algorithmReturn = Algorithms.priorityp(processes, fakeWriter);
					Statistics statistics = new Statistics(processes, algorithmReturn);
					statistics.calculate();
					System.out.println("PRIORITYP:");
					System.out.println(statistics);
				}else if(Integer.valueOf(outputType) == 2){
					FileManager writer = new FileManager(pathLog + "PRIORITYP-log");
					Algorithms.priorityp(processes, writer);
					writer.close();
					System.out.println("SCHEDULING LOG SAVED IN 'PRIORITYP-log'");

				}else throw new Exception("Invalid output. Try 1 or 2!");
				break;
				
			case "RR":
				if (Integer.valueOf(outputType) == 1){
					FileManager fakeWriter = new FileManager();
					AlgorithmReturn algorithmReturn = Algorithms.rr(processes, timeQuantum, fakeWriter);
					Statistics statistics = new Statistics(processes, algorithmReturn);
					statistics.calculate();
					System.out.println("RR:");
					System.out.println(statistics);
				}else if(Integer.valueOf(outputType) == 2){
					FileManager writer = new FileManager(pathLog + "RR-log");
					Algorithms.rr(processes, timeQuantum, writer);
					writer.close();
					System.out.println("SCHEDULING LOG SAVED IN 'RR-log'");

				}else throw new Exception("Invalid output. Try 1 or 2!");
				break;
				
			default: throw new Exception("Invalid Algorithm!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	

}
