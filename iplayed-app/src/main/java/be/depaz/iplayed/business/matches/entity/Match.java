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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    
    private String player1Id;
    @ManyToOne
    @JoinColumn(name = "player1Id", insertable = false, updatable = false)
    private Player player1;
    private String player2Id;
    @ManyToOne
    @JoinColumn(name = "player2Id", insertable = false, updatable = false)
    private Player player2;
    
    @XmlJavaTypeAdapter(value = DateAdapter.class, type = Date.class)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date playedAt;

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

    public String getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(String player1Id) {
        this.player1Id = player1Id;
    }

    public String getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(String player2Id) {
        this.player2Id = player2Id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    
    @Transient
    public Player getWinner() {
        int p1 = 0;
        int p2 = 0;
        for (Set set : this.sets) {
            if (set.getScorePlayer1() > set.getScorePlayer2()) {
                p1++;
            } else if (set.getScorePlayer2() > set.getScorePlayer1()) {
                p2++;
            }
        }
        if (p1 > p2) return this.player1;
        if (p2 > p1) return this.player2;
        return null;
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
        return "Match{" + "id=" + id + ", player1Id=" + player1Id + ", player2Id=" + player2Id + ", playedAt=" + playedAt + ", sets=" + sets + '}';
    }
   
}