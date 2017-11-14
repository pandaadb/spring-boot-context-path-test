package com.example.pdb.demo;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @LocalServerPort
    private int port;
    
    @Test
    public void test404DefaultInvoced() { 
        Response response = ClientBuilder.newClient().target("http://localhost:" + port).path("/mainstay/test_does_not_exist").request().get();
        Assert.assertThat(response.getStatus(), Is.is(404));
        
        String readEntity = response.readEntity(String.class);
        Assert.assertThat(readEntity.contains("Apache Tomcat/8.5.23"), Is.is(true));
    }
    
    @Test
    public void test404DefaultInvocedWithoutContextPath() { 
        Response response = ClientBuilder.newClient().target("http://localhost:" + port).path("/this_is_not_the_context/test_does_not_exist").request().get();
        Assert.assertThat(response.getStatus(), Is.is(404));
        
        String readEntity = response.readEntity(String.class);
        Assert.assertThat(readEntity.contains("Apache Tomcat/8.5.23"), Is.is(true));
    }
    
}
