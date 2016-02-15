/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.matches.entity;

import be.depaz.iplayed.DateAdapter;
import be.depaz.iplayed.business.players.entity.Player;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.Field;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 *
 * @author bdepaz
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = Match.findAll, 
            query = "SELECT m FROM Match m"),
    @NamedQuery(
            name = Match.findForPlayer, 
            query = "SELECT m FROM Match m WHERE m.player1Id = :playerId OR m.player2Id = :playerId")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoSql(dataFormat = DataFormatType.MAPPED)
public class Match implements Serializable {
    
    public static final String TYPE = "match";
    public static final String findAll          = TYPE + ".findAll"; 
    public static final String findForPlayer    = TYPE + ".findForPlayer"; 
    
    @Id
    @GeneratedValue
    @Field(name = "_id")
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "player1Id", insertable = false, updatable = false)
    private Player player1;
    private String player1Id;
    @ManyToOne
    @JoinColumn(name = "player2Id", insertable = false, updatable = false)
    private Player player2;
    private String player2Id;
    
    @XmlJavaTypeAdapter(value = DateAdapter.class, type = Date.class)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date playedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "winnerId", insertable = false, updatable = false)
    private Player winner;
    private String winnerId;
    
    @ElementCollection
    private List<Set> sets = new ArrayList<>();
    
    public Match() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
        this.player1Id = player1.getUsername();
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
        this.player2Id = player2.getUsername();
    }
    
    @PreUpdate
    @PrePersist
    void calculateWinner() {
        int p1 = 0;
        int p2 = 0;
        if (this.sets != null && !this.sets.isEmpty()) {
            for (Set set : this.sets) {
                if (set.getScorePlayer1() > set.getScorePlayer2()) {
                    p1++;
                } else if (set.getScorePlayer2() > set.getScorePlayer1()) {
                    p2++;
                }
            }            
        }
        if (p1 > p2) setWinner(this.player1);
        if (p2 > p1) setWinner(this.player2);
    }

    private void setWinner(Player winner) {
        this.winner = winner;
        this.winnerId = winner.getUsername();
    } 
    
    public Player getWinner() {
        return this.winner;
    }
    
    public Date getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Date playedAt) {
        this.playedAt = playedAt;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", player1=" + player1 + ", player2=" + player2 + ", playedAt=" + playedAt + ", winner=" + winner + ", sets=" + sets + '}';
    }

   
}
