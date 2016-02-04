/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.matches.boundary;

import be.depaz.iplayed.business.matches.entity.Match;
import be.depaz.iplayed.business.logging.boundary.BoundaryLogger;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author bdepaz
 */
@Stateless
@Interceptors(BoundaryLogger.class)
public class MatchManager {
    
    @PersistenceContext
    EntityManager em;
    
    public Match findById(String id) {
        return em.find(Match.class, id);
    }
    
    public List<Match> all() {
        return this.em.createNamedQuery(Match.findAll, Match.class)
                .getResultList();
    }
    
    public List<Match> findMatchesForPlayer(String username) {
        Query query = this.em.createNamedQuery(Match.findForPlayer, Match.class);
        query.setParameter("playerId", username);
        return query.getResultList();
    }
    
    public Match add(Match match) {
        em.persist(match);
        return match;
    }
    
    public Match save(Match match) {
        em.merge(match);
        return match;
    }
    
    public void delete(String id) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
    
}
