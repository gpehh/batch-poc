package com.example.batch.poc.job;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class CustomJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("Before job execution");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("After job execution");
        // Karar adımı (decision step) sonucuna göre işin adımının durumunu değiştir
        String decision = (String) jobExecution.getExecutionContext().get("decision");
        if ("EVEN".equals(decision)) {
            jobExecution.getStepExecutions().forEach(stepExecution -> {
                // Adımın durumunu değiştir
                stepExecution.setStatus(BatchStatus.FAILED);
            });
        }
    }
}
