/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author bdepaz
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    private SimpleDateFormat dateFormat;

    public DateAdapter() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
    
    @Override
    public Date unmarshal(String v) throws Exception {
        Date d = dateFormat.parse(v);
        return d;
    }

    @Override
    public String marshal(Date v) throws Exception {
        String d = dateFormat.format(v);
        return d;
    }
    
}
