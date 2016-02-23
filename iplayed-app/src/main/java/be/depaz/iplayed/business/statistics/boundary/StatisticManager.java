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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
        statistics.setPlayerWins(sortByValue(scoreboard));
        return statistics;
    }

    private static Map<Player, Integer> sortByValue(Map<Player, Integer> unsortMap) {

        // Convert Map to List
        List<Map.Entry<Player, Integer>> list
                = new LinkedList<>(unsortMap.entrySet());

        // Sort list with comparator, to compare the Map values
        Collections.sort(list, (Map.Entry<Player, Integer> o1, Map.Entry<Player, Integer> o2) 
                -> (o2.getValue()).compareTo(o1.getValue()));

        // Convert sorted map back to a Map
        Map<Player, Integer> sortedMap = new LinkedHashMap<>();
        list.stream().forEach((entry) -> {
            sortedMap.put(entry.getKey(), entry.getValue());
        });
        return sortedMap;
    }

}
