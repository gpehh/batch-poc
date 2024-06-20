package com.example.batch.poc.config;

import com.example.batch.poc.job.EvenOddDecider;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job decisionJob() {
        return jobBuilderFactory.get("decisionJob")
                .start(decisionStep())
                .next(decision())
                .from(decision()).on("EVEN").to(evenStep())
                .from(decision()).on("ODD").to(oddStep())
                .end()
                .build();
    }

    @Bean
    public Step decisionStep() {
        return stepBuilderFactory.get("decisionStep")
                .tasklet((contribution, chunkContext) -> {
                    chunkContext.getStepContext().getStepExecution().getExecutionContext().putInt("number" ,3);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step evenStep() {
        return stepBuilderFactory.get("evenStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("EVEN Step");
                   BatchStatus status = chunkContext.getStepContext().getStepExecution().getStatus();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step oddStep() {
        return stepBuilderFactory.get("oddStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("ODD Step ");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public JobExecutionDecider decision() {
        return new EvenOddDecider();
    }
}
