package hu.astron.webservice;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Stateless
@LocalBean
@Path("hellows")
public class HelloJAXRWebService {

    public HelloJAXRWebService() {

    }

    @GET
    @Produces("text/plain")
    public String sayHello() {
        return "Hello";
    }
}
