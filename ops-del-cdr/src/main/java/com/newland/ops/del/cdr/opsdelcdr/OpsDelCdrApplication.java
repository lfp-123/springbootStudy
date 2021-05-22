package com.newland.ops.del.cdr.opsdelcdr;

import com.newland.ops.del.cdr.opsdelcdr.processor.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpsDelCdrApplication implements CommandLineRunner{
	@Autowired
	private Processor processor;
	public static void main(String[] args) {
		SpringApplication.run(OpsDelCdrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String operationId = System.getProperty("op_id");
		processor.process(operationId);
	}
}
