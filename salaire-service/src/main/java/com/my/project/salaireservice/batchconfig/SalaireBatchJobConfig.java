package com.my.project.salaireservice.batchconfig;

import com.my.project.salaireservice.model.Salaire;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class SalaireBatchJobConfig {

    private final SalaireItemReader reader;
    private final SalaireWriter writer;

    public SalaireBatchJobConfig(SalaireItemReader reader, SalaireWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Bean
    public Job salaireCalculationJob(JobRepository jobRepository, Step salaireStep) {
        return new JobBuilder("salaireCalculationJob", jobRepository)
                .start(salaireStep)
                .build();
    }

    @Bean
    public Step salaireStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("salaireStep", jobRepository)
                .<Salaire, Salaire>chunk(1, transactionManager) // Process 10 records at a time
                .reader(reader)
                .writer(writer)
                .build();
    }
}

