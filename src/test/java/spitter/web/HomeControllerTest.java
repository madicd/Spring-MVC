package spitter.web;

import org.junit.Test;

import static org.junit.Assert.*;

public class HomeControllerTest {
    @Test
    public void testHomePage() throws Exception {
        HomeController controller = new HomeController();
        assertEquals("home", controller.home());
    }

}