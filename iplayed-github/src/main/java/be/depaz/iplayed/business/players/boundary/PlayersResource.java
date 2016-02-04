/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.players.boundary;

import be.depaz.iplayed.business.matches.boundary.MatchManager;
import be.depaz.iplayed.business.players.entity.Player;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author bdepaz
 */
@Stateless
@Path("players")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PlayersResource {
    
    @Inject
    PlayerManager pm;
    
    @Inject
    MatchManager mm;
    
    @Path("{username}")
    public PlayerResource find(@PathParam("username") String username) {
        return new PlayerResource(username, pm, mm);
    }
    
    @GET
    public List<Player> all() {
        return pm.all();
    }
    
    @POST
    public Response save(@Valid Player player, @Context UriInfo info) {
        Player savedPlayer = pm.save(player);
        URI uri = info.getAbsolutePathBuilder()
                .path("/" + savedPlayer.getUsername())
                .build();
        return Response.created(uri).build();
    }
    
}
