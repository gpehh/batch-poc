package com.example.batch.poc.job;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerJobWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) throws Exception {

        for (String msg : items) {
            System.out.println("Writing the data " + msg);
        }

    }
}
