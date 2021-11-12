package com.github.pbbz.dota;

import java.util.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.LifecycleException;
import org.springframework.core.io.ClassPathResource;

public class Dota {
    public static void main(String[] args) throws IOException, LifecycleException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dota", "dota", "dota");
        HeroPostgresRepository heroRepository = new HeroPostgresRepository(conn);
        List<Hero> heroList = getHeroInfo();
        heroRepository.create(heroList);
        ItemPostgresRepository itemRepository = new ItemPostgresRepository(conn);
        List<Item> itemList = getItemInfo();
        itemRepository.create(itemList);

        List<ProMatches> proList = getProMatches();
        displayProMatches(proList);
        
        Scanner scan = new Scanner(System.in);
        System.out.printf("%nEnter selection: ");
        int select = scan.nextInt();
        scan.close();

        String matchid = proList.get(select).getMatchID();
        System.out.printf("%n");

        MatchData matchData = getMatchResults(matchid);
        displayMatchResults(matchData, heroList);
        
        Server server = new Server();
        server.setupServer();
        server.addProMatches(proList);
        for (int i = 0; i < 10; i++){
            server.addMatchData(getMatchResults(proList.get(i).getMatchID()), heroList);
        }
        server.startServer();
    }

    public static List<Hero> getHeroInfo() throws IOException {
        return Arrays.asList(new ObjectMapper()
            .readValue(new ClassPathResource("hero.json").getFile(),Hero[].class));
    }

    public static List<Item> getItemInfo() throws IOException {
        return Arrays.asList(new ObjectMapper()
            .readValue(new ClassPathResource("items.json").getFile(),Item[].class));
    }

    public static List<ProMatches> getProMatches() throws IOException {
        URL url = new URL("https://api.opendota.com/api/proMatches");
        List<ProMatches> proList = new ArrayList<ProMatches>();

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent","java");
        InputStream response = connection.getInputStream();
        String body = new String(response.readAllBytes());
        proList = Arrays.asList(new ObjectMapper().readValue(body, ProMatches[].class));

        return proList;
    }

    public static MatchData getMatchResults(String matchid) throws IOException {
        String apikey = new String("3C6448132939EB4A18C209BE81D92659");
        URL url = new URL("https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?match_id=" 
            + matchid + "&key=" + apikey);
         
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent","java");
        InputStream response = connection.getInputStream();
        String body = new String(response.readAllBytes());
        MatchData matchData = new ObjectMapper().readValue(body, MatchData.class);

        return matchData;
    }

    public static void displayProMatches(List<ProMatches> proList) {
        System.out.printf("Recent Professional Dota 2 Tournament Matches:%n%n");
        System.out.println(" x    Date     Tournament                          Radiant                      Dire");
        System.out.println("------------------------------------------------------------------------------------------");
        for (int i = 0; i < 10; i++){
            Date timestamp = new Date((proList.get(i).getStartTime()) * 1000);
            String time = new SimpleDateFormat("MM-dd").format(timestamp);
            String leagueID = proList.get(i).getLeagueName();
            String radiantName = proList.get(i).getRadiantName();
            String direName = proList.get(i).getDireName();
            System.out.printf("(" + i + ")   %-8s %-35s %-15s              %-15s %n", time, leagueID, radiantName, direName);
        }
    }

    public static void displayMatchResults(MatchData matchData, List<Hero> heroList){
        ArrayList<String> source = new ArrayList<String>(10);
        ArrayList<String> heroNames = new ArrayList<String>(10);
        for (int i = 0; i < 10; i++){
            source.add(matchData.getResult().getPlayers().get(i).getID());
        }
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < heroList.size(); j++){
                if (heroList.get(j).getId().equals(source.get(i))){
                    heroNames.add(i, heroList.get(j).getName());
                }
            }
        }

        Date timestamp = new Date(matchData.getResult().getstartTime() * 1000);
        String time = new SimpleDateFormat("EEEEE MMMMM dd HH:mm").format(timestamp);

        System.out.println("Match: " + matchData.getResult().getMatchID());
        System.out.printf(time);
        System.out.printf("%n%s vs %s%n%n", matchData.getResult().getRadiantName(), matchData.getResult().getDireName());
        if (matchData.getResult().getRadiantWinner()) System.out.printf("Radiant Wins!%n%nRadiant Team%25s%8s%10s%n", "Kills", "Deaths", "Assists");
        else System.out.printf("Dire Wins!%n%nRadiant Team%25s%8s%10s%n", "Kills", "Deaths", "Assists");
        System.out.println("--------------------------------------------------------");

        for (int i = 0; i < 5; i++){
            System.out.printf("%-20s%15s%8s%10s%n", heroNames.get(i), matchData.getResult().getPlayers().get(i).getKills(),
                matchData.getResult().getPlayers().get(i).getDeaths(), matchData.getResult().getPlayers().get(i).getAssists());
        }
        System.out.printf("%nDire Team%28s%8s%10s%n", "Kills", "Deaths", "Assists");
        System.out.println("--------------------------------------------------------");
        for (int i = 5; i < 10; i++){
            System.out.printf("%-20s%15s%8s%10s%n", heroNames.get(i), matchData.getResult().getPlayers().get(i).getKills(),
                matchData.getResult().getPlayers().get(i).getDeaths(), matchData.getResult().getPlayers().get(i).getAssists());
        }
    }
}
