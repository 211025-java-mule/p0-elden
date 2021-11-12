package com.github.pbbz.dota;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class Server {
    Tomcat server = new Tomcat();
    Integer i = 0;
    public void setupServer(){
        server.setBaseDir(System.getProperty("java.io.tmpdir"));
        server.setPort(8080);
        server.getConnector();
        server.addContext("", null);
    }

    public void addProMatches(List<ProMatches> proList){
        server.addServlet("", "dotaServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                resp.getWriter().printf("Recent Professional Dota 2 Tournament Matches:%n%n");
                resp.getWriter().println("MatchID      Date     Tournament                          Radiant                      Dire");
                resp.getWriter().println("------------------------------------------------------------------------------------------");
                for (int i = 0; i < 10; i++){
                    Date timestamp = new Date((proList.get(i).getStartTime()) * 1000);
                    String time = new SimpleDateFormat("MM-dd").format(timestamp);
                    String leagueID = proList.get(i).getLeagueName();
                    String radiantName = proList.get(i).getRadiantName();
                    String direName = proList.get(i).getDireName();
                    resp.getWriter().printf(proList.get(i).getMatchID() + "   %-8s %-35s %-15s              %-15s %n", time, leagueID, radiantName, direName);
                }
            }
        }).addMapping("");
    }

    public void addMatchData(MatchData matchData, List<Hero> heroList){
        server.addServlet("", i.toString(), new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
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
                
                resp.getWriter().println("Match: " + matchData.getResult().getMatchID());
                resp.getWriter().printf(time);
                resp.getWriter().printf("%n%s vs %s%n%n", matchData.getResult().getRadiantName(), matchData.getResult().getDireName());
                if (matchData.getResult().getRadiantWinner()) resp.getWriter().printf("Radiant Wins!%n%nRadiant Team%25s%8s%10s%n", "Kills", "Deaths", "Assists");
                else resp.getWriter().printf("Dire Wins!%n%nRadiant Team%25s%8s%10s%n", "Kills", "Deaths", "Assists");
                resp.getWriter().println("--------------------------------------------------------");
                
                for (int i = 0; i < 5; i++){
                    resp.getWriter().printf("%-20s%15s%8s%10s%n", heroNames.get(i), matchData.getResult().getPlayers().get(i).getKills(),
                        matchData.getResult().getPlayers().get(i).getDeaths(), matchData.getResult().getPlayers().get(i).getAssists());
                }
                resp.getWriter().printf("%nDire Team%28s%8s%10s%n", "Kills", "Deaths", "Assists");
                resp.getWriter().println("--------------------------------------------------------");
                for (int i = 5; i < 10; i++){
                    resp.getWriter().printf("%-20s%15s%8s%10s%n", heroNames.get(i), matchData.getResult().getPlayers().get(i).getKills(),
                        matchData.getResult().getPlayers().get(i).getDeaths(), matchData.getResult().getPlayers().get(i).getAssists());
                }  
            }
        }).addMapping("/" + matchData.getResult().getMatchID());
        i++;
    }

    public void startServer() throws LifecycleException{
        server.start();
        server.getServer().await();
    }
}
