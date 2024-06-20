package com.example.batch.poc.job;

import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

@Component
public class CustomerJobReader implements ItemReader<String> {

    private String[] messages = { "javainuse.com",
            "Welcome to Spring Batch Example",
            "We use H2 Database for this example",
            "We use Postgres Database for this example",
            "one","two","three","four"};

    private int count = 0;

    @Override
    public String read()  {

        if (count < messages.length) {
            System.out.println("Read data.."+ messages[count]);
            return messages[count++];
        } else {
            count = 0;
        }
        return null;
    }


}
