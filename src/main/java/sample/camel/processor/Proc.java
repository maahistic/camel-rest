package sample.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class Proc implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("Processor called");
    }
}
