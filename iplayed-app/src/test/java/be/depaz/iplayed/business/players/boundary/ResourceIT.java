/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.players.boundary;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import static com.airhacks.rulz.jaxrsclient.JAXRSClientProvider.buildWithURI;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author bdepaz
 */
public class ResourceIT {
    
    private static final List<Player> players = Arrays.asList(
            new Player("bdepaz", "Bert", "Depaz"),
            new Player("dfranssen", "Dirk", "Franssen"),
            new Player("ptruyers", "Peter", "Truyers"),
            new Player("jvdh", "Jimmy", "Vanderheyden"),
            new Player("kschepers", "Kris", "Schepers")
    );
    
    @Rule
    public JAXRSClientProvider provider = buildWithURI("http://localhost:8080/iplayed/resources");
    
    @Test
    public void crud() {
        for (Player p : players) {
            createPlayer(p.username, p.firstname, p.lastname);
        }
        
        String locationP1 = createPlayer(
                players.get(0).username, 
                players.get(0).firstname, 
                players.get(0).lastname);
        
        //Find the first player
        JsonObject foundP1 = this.provider.client()
                .target(locationP1)
                .request(MediaType.APPLICATION_JSON)
                .get(JsonObject.class);
        assertTrue(foundP1.getString("firstname").equals("Bert"));
        
        //Find all players
        Response findAllResponse = this.provider.target()
                .path("players").request().get();
        JsonArray allPlayers = findAllResponse.readEntity(JsonArray.class);
        assertFalse(allPlayers.isEmpty());
        for (int i=0; i<allPlayers.size(); i++) {
            JsonObject playerObject = allPlayers.getJsonObject(i);
        }
        
        String locationM1 = createMatch("bdepaz", "dfranssen");
        String locationM2 = createMatch("dfranssen", "ptruyers");
        String locationM3 = createMatch("ptruyers", "bdepaz");
        
        //Find the first match again
        JsonObject foundM1 = this.provider.client()
                .target(locationM1)
                .request(MediaType.APPLICATION_JSON)
                .get(JsonObject.class);
        System.out.println("Found : " + foundM1);
        assertTrue(foundM1.getJsonObject("player1").getString("username").equals("bdepaz"));
        
        createRandomMatch();
        createRandomMatch();
        createRandomMatch();
        createRandomMatch();
        createRandomMatch();
        createRandomMatch();
        createRandomMatch();
        createRandomMatch();
        
        //Find all matches
        Response findAllMatchesResponse = this.provider.target()
                .path("matches").request().get();
        JsonArray allMatches = findAllMatchesResponse.readEntity(JsonArray.class);
        assertFalse(allMatches.isEmpty());
        for (int i=0; i<allMatches.size(); i++) {
            JsonObject matchObject = allMatches.getJsonObject(i);
        }
        
        //Find all matches for player
        JsonArray playerMatches = this.provider.client()
                .target(locationP1)
                .path("matches")
                .request(MediaType.APPLICATION_JSON)
                .get(JsonArray.class);
        for (int i=0; i<playerMatches.size(); i++) {
            JsonObject matchObject = playerMatches.getJsonObject(i);
            assertTrue(
                    matchObject.getString("player1.username").equalsIgnoreCase("bdepaz") || 
                    matchObject.getString("player2.username").equalsIgnoreCase("bdepaz"));
        }
    }
    
    private String createRandomMatch() {
        Random rnd = new Random(System.currentTimeMillis());
        Player p1 = players.get(rnd.nextInt(players.size()));
        Player p2 = players.get(rnd.nextInt(players.size()));
        while (p2.equals(p1)) {
            p2 = players.get(rnd.nextInt(players.size()));
        }
        return createMatch(p1.username, p2.username);
    }
    
    private String createMatch(String player1, String player2) {
        JsonObjectBuilder playerBuilder = Json.createObjectBuilder();
        playerBuilder.add("username", player1);
        JsonObject p1 = playerBuilder.build();
        playerBuilder.add("username", player2);
        JsonObject p2 = playerBuilder.build();
        
        JsonObjectBuilder matchBuilder = Json.createObjectBuilder();
        JsonArrayBuilder setsBuilder = Json.createArrayBuilder();
        JsonObjectBuilder setBuilder = Json.createObjectBuilder();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
        Random rnd = new Random(System.currentTimeMillis());
        
        JsonObject matchToCreate  = matchBuilder
                .add("player1", p1)
                .add("player2", p2)
                .add("sets", setsBuilder
                        .add(setBuilder
                                .add("scorePlayer1", rnd.nextInt(12))
                                .add("scorePlayer2", rnd.nextInt(12)).build())
                        .add(setBuilder
                                .add("scorePlayer1", rnd.nextInt(12))
                                .add("scorePlayer2", rnd.nextInt(12)).build())
                        .add(setBuilder
                                .add("scorePlayer1", rnd.nextInt(12))
                                .add("scorePlayer2", rnd.nextInt(12)).build())
                        .build())
                .add("playedAt", sdf.format(new Date(System.currentTimeMillis())))
                .build();
        
        Response postResponse = this.provider.target().path("matches").request().
        post(Entity.json(matchToCreate));
        assertThat(postResponse.getStatus(), is(201));
        return postResponse.getHeaderString("Location");
    }
    
    private String createPlayer(String username, String firstname, String lastname) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonObject playerToCreate  = objectBuilder
                .add("username", username)
                .add("firstname", firstname)
                .add("lastname", lastname)
                .build();
        
        Response postResponse = this.provider
                .target()
                .path("players")
                .request()
                .post(Entity.json(playerToCreate));
        assertThat(postResponse.getStatus(), is(201));
        return postResponse.getHeaderString("Loc"
                + "ation");
    }
    
    private static class Player {
        String username;
        String firstname;
        String lastname;
        public Player(String username, String firstname, String lastname) {
            this.username = username;
            this.firstname = firstname;
            this.lastname = lastname;
        }
    }
    
}
