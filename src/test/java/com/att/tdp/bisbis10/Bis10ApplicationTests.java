package com.att.tdp.bisbis10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.att.tdp.bisbis10.datagenerator.DataCreator;

@SpringBootTest
class Bis10ApplicationTests {
	private final DataCreator dataCreator;

	public Bis10ApplicationTests(DataCreator dataCreator) {
		this.dataCreator = dataCreator;
	}

	@Test
	void contextLoads() {
	}

}
