package com.my.project.salaireservice.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salaires")
public class BatchController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job salaireCalculationJob;

    @GetMapping("/calculate")
    public String runBatch() {
        try {
            // Generate unique parameters to avoid conflicts
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("timestamp", System.currentTimeMillis()) // Unique parameter
                    .toJobParameters();

            jobLauncher.run(salaireCalculationJob, jobParameters);
            return "Salary calculation batch job executed successfully.";
        } catch (Exception e) {
            return "Failed to execute salary calculation batch job: " + e.getMessage();
        }
    }
}
