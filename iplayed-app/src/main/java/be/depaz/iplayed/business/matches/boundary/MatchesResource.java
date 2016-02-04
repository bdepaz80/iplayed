/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.matches.boundary;

import be.depaz.iplayed.business.matches.entity.Match;
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
@Path("matches")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MatchesResource {
    
    @Inject
    MatchManager mm;
    
    @Path("{matchId}")
    public MatchResource find(@PathParam("matchId") String matchId) {
        return new MatchResource(matchId, mm);
    }
    
    @GET
    public List<Match> all() {
        return mm.all();
    }
    
    @POST
    public Response save(@Valid Match match, @Context UriInfo info) {
        Match savedMatch = mm.add(match);
        URI uri = info.getAbsolutePathBuilder()
                .path("/" + savedMatch.getId())
                .build();
        return Response.created(uri).build();
    }
    
}
