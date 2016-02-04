/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.statistics.boundary;

import be.depaz.iplayed.business.statistics.entity.Statistics;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author bdepaz
 */
@Stateless
@Path("statistics")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class StatisticsResource {
    
    @Inject
    StatisticManager sm;
    
    @GET
    public Statistics statistics() {
        return sm.getStatistics();
    }
    
}
