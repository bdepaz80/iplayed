/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.players.boundary;

import be.depaz.iplayed.business.players.entity.Player;
import be.depaz.iplayed.business.logging.boundary.BoundaryLogger;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bdepaz
 */
@Stateless
@Interceptors(BoundaryLogger.class)
public class PlayerManager {
    
    @PersistenceContext
    EntityManager em;
    
    public Player findByUsername(String username) {
        return em.find(Player.class, username);
    }
    
    public List<Player> all() {
        return this.em.createNamedQuery(Player.findAll, Player.class)
                .getResultList();
    }
    
    public Player save(Player player) {
        em.merge(player);
        return player;
    }
    
    public void delete(String username) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
    
}
