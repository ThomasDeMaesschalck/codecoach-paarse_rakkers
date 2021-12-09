package com.switchfully.codecoach.security.email;

import com.switchfully.codecoach.security.authentication.user.Event;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"test", "development"})
@Component
public class MockMessageSender implements MessageSender {

    @Override
    public void handle(Event event) {
        //todo Send your messages here...
    }
}
