package com.profitbricks.slack.bot

import me.ramswaroop.jbot.core.slack.Bot
import me.ramswaroop.jbot.core.slack.Controller
import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.socket.WebSocketSession

import static me.ramswaroop.jbot.core.slack.EventType.*

@SpringBootApplication(scanBasePackages = ["me.ramswaroop.jbot", "com.profitbricks.slack.bot"])
class Marvin extends Bot {

    @Override
    String getSlackToken() {
        'xoxb-42358107398-QceksHvabngkn2BE1elM0Lqv'
    }

    @Override
    Bot getSlackBot() {
        this
    }

    // bot controls

    @SuppressWarnings("GroovyUnusedDeclaration")
    @Controller(events = [DIRECT_MENTION, DIRECT_MESSAGE])
    void directMessage(WebSocketSession session, Event event) {
        reply session, event, new Message("Hi, I am ${slackService.currentUser.name}")
    }

    // main

    static void main(String[] args) {
        SpringApplication.run Marvin.class, args
    }
}