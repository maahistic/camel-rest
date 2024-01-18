package sample.camel;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class ServiceBean {
	
    @Value("#{'${app.list}'.split(';')}")
    private List<String> queueConfigsIn;

    public User response(User user) {
        user.setName(user.getName().toUpperCase());
        return user;
    }
    
   public String hello(String name) {
	   		System.out.println(queueConfigsIn.get(0).isEmpty());
	   		System.out.println(queueConfigsIn.stream().anyMatch(x -> x.equalsIgnoreCase("A")));
           return "Hello "+name + " THE TIME in AUS is " +  LocalDateTime.now();
} }