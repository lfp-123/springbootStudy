package com.newland.boss.ops.scan;

import com.newland.boss.ops.scan.processor.CollectControllerTask;
import com.newland.boss.ops.scan.processor.ScanFileMqTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpsScanRbfileApplication {
    @Autowired
    private CollectControllerTask collectControllerTask;
    @Autowired
    private ScanFileMqTask scanFileMqTask;

	public static void main(String[] args) {
		SpringApplication.run(OpsScanRbfileApplication.class, args);
	}

}
