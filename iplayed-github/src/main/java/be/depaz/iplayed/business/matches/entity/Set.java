/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.matches.entity;

import javax.persistence.Embeddable;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.NoSql;

/**
 *
 * @author bdepaz
 */
@Embeddable
@NoSql(dataFormat = DataFormatType.MAPPED)
public class Set {
    
    private int scorePlayer1;
    private int scorePlayer2;

    public Set() {
    }
    
    public Set(int scorePlayer1, int scorePlayer2) {
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

    @Override
    public String toString() {
        return "Set{" + "scorePlayer1=" + scorePlayer1 + ", scorePlayer2=" + scorePlayer2 + '}';
    }

}
