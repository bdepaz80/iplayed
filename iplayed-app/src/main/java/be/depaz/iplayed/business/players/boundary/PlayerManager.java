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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

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
        return this.em.find(Player.class, username);
    }

    public List<Player> find(String queryString) {
        
        //Solution native query
        //Ain't working: JPA error. 
        //Query q = em.createNativeQuery("db.PLAYER.find({\"username\": \"bdepaz\"})", Player.class);
        
        //Solution EJBQL
        //Still case sensitive, I have to find a way to get UPPER working...
        String query = String.format(
                "SELECT p FROM Player p "
                + "WHERE p.username LIKE '%%%1$s%%' "
                + "OR p.firstname LIKE '%%%1$s%%' "
                + "OR p.lastname LIKE '%%%1$s%%'", queryString);
        Query q = this.em.createQuery(query);
        
        //Solution "NamedQuery"
        //NamedQuery not working, wildcard being escaped. 
        //Query q = this.em.createNamedQuery(Player.find, Player.class);
        //q.setParameter("queryString", queryString);
        return q.getResultList();
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
