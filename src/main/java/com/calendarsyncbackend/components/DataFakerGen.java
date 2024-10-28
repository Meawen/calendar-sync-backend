package com.calendarsyncbackend.components;

import com.calendarsyncbackend.models.Event;
import com.calendarsyncbackend.models.User;
import com.calendarsyncbackend.repositories.EventRepository;
import com.calendarsyncbackend.repositories.UserRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.stream.IntStream;

@Component
public class DataFakerGen implements CommandLineRunner {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    private final Faker faker = new Faker();

    @Value("${dataloader.enabled:true}")
    private boolean enabled;

    @Autowired
    public DataFakerGen(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!enabled) {
            return;
        }

        IntStream.range(1, 20).forEach(i -> {
            User user = new User();
            user.setUsername(faker.internet().domainWord());
            user.setName(faker.name().fullName());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(faker.internet().password());
            user.setRole(faker.number().numberBetween(1, 3));
            userRepository.save(user);
            System.out.println("Saved User: " + user.getUsername() + " with email: " + user.getEmail());
        });

        IntStream.range(1, 10).forEach(i -> {
            Event event = new Event();
            event.setName(faker.esports().event());
            event.setDescription(faker.lorem().sentence());
            event.setDatetimeFrom(Instant.now().minusSeconds(faker.number().numberBetween(3600, 2592000)));
            event.setDatetimeTo(event.getDatetimeFrom().plusSeconds(faker.number().numberBetween(3600, 86400)));
            eventRepository.save(event);
            System.out.println("Saved Event: " + event.getName() + " from: " + event.getDatetimeFrom() + " to: " + event.getDatetimeTo());
        });



    }
}
