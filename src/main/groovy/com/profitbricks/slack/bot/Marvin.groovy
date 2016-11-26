package com.profitbricks.slack.bot

import com.profitbricks.slack.bot.conversations.GeneralTopics
import me.ramswaroop.jbot.core.slack.Bot
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackages = ["me.ramswaroop.jbot", "com.profitbricks.slack.getSlackBot"])
class Marvin extends Bot implements GeneralTopics {

    @Override
    String getSlackToken() { 'xoxb-42358107398-qMzIByY1nLumvGycSjtA4ZV6' }

    @Override
    Bot getSlackBot() { this }

    static void main(String[] args) {
        SpringApplication.run Marvin.class, args
    }
}