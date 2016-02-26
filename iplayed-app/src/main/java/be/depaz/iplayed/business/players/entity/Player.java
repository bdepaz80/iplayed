/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.players.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 *
 * @author bdepaz
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = Player.findAll, 
            query = "SELECT p FROM Player p"),
    @NamedQuery(
            name = Player.find, 
            query = "SELECT p FROM Player p WHERE p.username LIKE :queryString OR p.firstname LIKE :queryString OR p.lastname LIKE :queryString")    
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NoSql(dataFormat=DataFormatType.MAPPED)
public class Player implements Serializable {
    
    public static final String TYPE = "player";
    public static final String find = TYPE + ".find";
    public static final String findAll = TYPE + ".findAll";
    
    @Id
    @NotNull
    @Size(min = 2, max = 10)
    private String username;
    private String firstname;
    private String lastname;
    
    public Player() {
    }

    public Player(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Player{" + 
                "username=" + username + 
                ", firstname=" + firstname + 
                ", lastname=" + lastname + '}';
    }
    
}
