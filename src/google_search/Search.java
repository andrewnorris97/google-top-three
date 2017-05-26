package google_search;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import org.jsoup.*;

public class Search {

	
	public static void main (String[] args){
		//Change this to change team searched
		//Make sure you use '+' in place of ' '
		String teamName = ("Toronto+Raptors");
		new Search(teamName);
	}
	
	public Search(String team){
		String[] players = PlayerNames(team);
		String [] topThree = TopThree(players);
		
		for(int i = 0; i < 3; i++){
			System.out.println(topThree[i]);
		}
	}
	
	private String [] TopThree(String[] players){
		
		String[] topThree = new String[3];
		int j = 0;
		//Add top 3 players to an array
		for (int i = 0; i < 6; i++){
			//Catch any special characters that cannot be recreated
			players[i] = players[i].replaceAll("[^a-zA-Z ]+", "?");
			if (players[i].indexOf('?') < 0){
				topThree[j] = players[i];
				j++;
			}
			if (j == 3){
				break;
			}
		}
		//return top 3 players
		return topThree;
	}
	
	private String [] PlayerNames(String team) {
		String player;
		String[] playerName;
		String[] players = new String[6];
		//Google search Input
		String url = "https://www.google.ca/search?q=";
		
		int i = 0;
		
	    try {
	    	// fetch the document over HTTP
	    	Document doc = Jsoup.connect(url + team + "+Roster").get();
	      
	    	// get all links in page
	    	Elements links = doc.select(".kltat");
	    	for (Element link : links) {
	    		//Take top 6 players from Google
				if(i < 6){
					player = link.text();
					players[i] = player;
				}
				i = i+1;
	    	}
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }

		return players;
	  }
	
}
