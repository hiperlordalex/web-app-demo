package demo.alex;

import demo.alex.controller.LoginController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {


	@Autowired
	private LoginController controller;

	@Test
	void contextLoads() {
		Assert.assertNotNull(controller);
	}

}
