/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.players.boundary;

import be.depaz.iplayed.business.matches.boundary.MatchManager;
import be.depaz.iplayed.business.matches.boundary.MatchesResource;
import be.depaz.iplayed.business.matches.entity.Match;
import be.depaz.iplayed.business.players.entity.Player;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author bdepaz
 */
public class PlayerResource {
    
    String username;
    PlayerManager pm;
    MatchManager mm;

    public PlayerResource(String username, PlayerManager pm, MatchManager mm) {
        this.username = username;
        this.pm = pm;
        this.mm = mm;
    }
    
    @GET
    public Player find() {
        System.out.println("find(" + username + ")");
        return pm.findByUsername(username);
    }
    
    @GET
    @Path("matches")
    public List<Match> findPlayerMatches() {
        return mm.findMatchesForPlayer(username);
    }
    
    @PUT
    public Player save(Player player) {
        player.setUsername(this.username);
        return pm.save(player);
    }
    
    @DELETE
    public void delete() {
        pm.delete(this.username);
    }
    
}
