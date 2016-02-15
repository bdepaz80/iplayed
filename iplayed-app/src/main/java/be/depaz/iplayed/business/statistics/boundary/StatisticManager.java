/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.statistics.boundary;

import be.depaz.iplayed.business.logging.boundary.BoundaryLogger;
import be.depaz.iplayed.business.matches.boundary.MatchManager;
import be.depaz.iplayed.business.matches.entity.Match;
import be.depaz.iplayed.business.matches.entity.Set;
import be.depaz.iplayed.business.players.boundary.PlayerManager;
import be.depaz.iplayed.business.players.entity.Player;
import be.depaz.iplayed.business.statistics.entity.Statistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

/**
 *
 * @author bdepaz
 */
@Stateless
@Interceptors(BoundaryLogger.class)
public class StatisticManager {
    
    @Inject
    MatchManager matchManager;
    
    @Inject
    PlayerManager playerManager;
    
    public Statistics getStatistics() {
        int totalMatches = 0;
        int totalSets = 0;
        int totalPoints = 0;
        int totalPlayers = 0;
        
        Map<Player, Integer> scoreboard = new HashMap<>();
        
        List<Match> matches = matchManager.all();
        totalMatches = matches.size();
        for (Match match : matches) {
            List<Set> sets = match.getSets();
            totalSets += sets.size();
            for (Set set : sets) {
                totalPoints += set.getScorePlayer1();
                totalPoints += set.getScorePlayer2();
            }
            
            System.out.println("Match.getWinner() = " + match.getWinner());
            if (match.getWinner() != null) {
                if (scoreboard.containsKey(match.getWinner())) {
                    int current = scoreboard.get(match.getWinner());
                    scoreboard.replace(match.getWinner(), ++current);
                } else {
                    scoreboard.put(match.getWinner(), 1);
                }
            }
        }
        
        List<Player> players = playerManager.all();
        totalPlayers = players.size();
        
        Statistics statistics = new Statistics();
        statistics.setTotalMatches(totalMatches);
        statistics.setTotalSets(totalSets);
        statistics.setTotalPoints(totalPoints);
        statistics.setTotalPlayers(totalPlayers);
        statistics.setPlayerWins(scoreboard);
        return statistics;
    }
    
}
