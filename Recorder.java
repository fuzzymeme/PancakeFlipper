package pancakeflipperrevisited;

import java.util.LinkedList;
import java.util.List;

public class Recorder {

	private static Recorder instance = new Recorder(); 
	private List<String> messages = new LinkedList<String>();

	private Recorder(){}

	public static Recorder getInstance(){
		return instance;
	}
	
	public static void clearMessages(){
		instance.messages.clear();
	}
	
	public static void recordMessage(String message){
		instance.messages.add(message);
	}
	
	public void record(String message){
		messages.add(message);
	}
	
	public static void showMessages(){
		
		for(String message: instance.messages){
			System.out.println("-" + message);
		}
	}
}
