/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.statistics.entity;

import be.depaz.iplayed.business.players.entity.Player;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bdepaz
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Statistics {
 
    private int totalMatches;
    private int totalSets;
    private int totalPoints;
    private int totalPlayers;
    
    private Map<Player, Integer> playerWins;

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public int getTotalSets() {
        return totalSets;
    }

    public void setTotalSets(int totalSets) {
        this.totalSets = totalSets;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    public Map<Player, Integer> getPlayerWins() {
        return playerWins;
    }

    public void setPlayerWins(Map<Player, Integer> playerWins) {
        this.playerWins = playerWins;
    }
    
}
