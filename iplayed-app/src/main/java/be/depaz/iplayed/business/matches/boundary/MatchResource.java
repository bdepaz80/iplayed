/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.matches.boundary;

import be.depaz.iplayed.business.matches.entity.Match;
import javax.ws.rs.GET;

/**
 *
 * @author bdepaz
 */
public class MatchResource {
    
    String id;
    MatchManager manager;

    public MatchResource(String id, MatchManager manager) {
        this.id = id;
        this.manager = manager;
    }
    
    @GET
    public Match find() {
        Match match = this.manager.findById(this.id);
        return match;
    }
    
}
