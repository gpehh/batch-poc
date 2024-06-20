package com.example.batch.poc.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

@Component
public class EvenOddDecider implements JobExecutionDecider {

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        int number = (int) stepExecution.getExecutionContext().get("number");
        if (number % 2 == 0) {
            jobExecution.setExitStatus(ExitStatus.FAILED);
            return FlowExecutionStatus.FAILED;
        } else {

            return new FlowExecutionStatus("ODD");
        }
    }
}