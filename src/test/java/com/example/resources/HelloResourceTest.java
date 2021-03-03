package com.example.resources;

import com.example.TestExtension;
import com.example.TestUtil;
import jakarta.ws.rs.client.WebTarget;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestExtension.class)
class HelloResourceTest {

    private static WebTarget target;

    @BeforeAll
    static void beforeAll() {
        target = TestUtil.createTarget();
    }

    @Test
    void hello() {
        String msg = target.path("hello").request().get(String.class);
        assertEquals("hello", msg);
    }

}
