import java.io.*;
import java.util.*;

public class kattisquest {
	public static void main(String[] args){
		// Initilisation 
		Scanner sc = new Scanner(System.in);
        Comparator<Quest> quest_compare = new Comparator<Quest>(){
        	// Compare by energy first, then gold if they are tied
        	public int compare(Quest q1, Quest q2){ 
        		if (q1.energy == q2.energy){return q1.gold - q2.gold;}
        		else {return q1.energy - q2.energy;}
        	}
        };
        int n = sc.nextInt(); // Number of commands
        TreeMap<Quest, Integer> treemap = new TreeMap<Quest, Integer>(quest_compare); // Keys: Quests, Value = count

        // Main Loop
        for (int i = 0; i<n; i++){
        	String command = sc.next();
        	if (command.equals("add")){
        		int energy = sc.nextInt();
        		int gold = sc.nextInt();
        		Quest quest = new Quest(energy, gold);
        		if (treemap.containsKey(quest)){ // If treemap already exists, just increment count
        			int count = treemap.get(quest) + 1;
        			treemap.put(quest, count); // No need to remove, will replace if same key is used
        		}
        		else {treemap.put(quest,1);} // Create new quest with count = 1
        	}
        	else if (command.equals("query")){
        		int total_energy = sc.nextInt();
        		long total_gold = 0;
        		while (total_energy > 0 && treemap.floorKey(new Quest(total_energy, 100001)) != null){ // While you have energy and treemap is not empty
        			Quest best_quest = treemap.floorKey(new Quest(total_energy, 100001));
        			total_energy -= best_quest.energy;
        			total_gold += best_quest.gold;
        			if (treemap.get(best_quest) == 1){treemap.remove(best_quest);} // Remove it if count is 1
        			else{ // Else just decrement the counter
        				int count = treemap.get(best_quest) - 1;
        				treemap.put(best_quest, count);
        			}	
        		}
        		System.out.println(total_gold);
        	}
        }
	}
}

class Quest{
	int energy;
	int gold; 

	public Quest(int energy, int gold){
		this.energy = energy;
		this.gold = gold;
	}
}