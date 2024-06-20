package com.example.batch.poc.config;


import com.example.batch.poc.job.CustomerJobReader;
import com.example.batch.poc.job.CustomerJobWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class CustomerJobConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer())
                .flow(customerStepOne()).end().build();
    }

    @Bean
    public Step customerStepOne() {
        return stepBuilderFactory.get("orderStep1").<String, String> chunk(2)
                .reader(new CustomerJobReader())
                .writer(new CustomerJobWriter()).build();
    }
}
