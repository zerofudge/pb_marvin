package com.profitbricks.slack.bot.conversations

import me.ramswaroop.jbot.core.slack.Bot
import me.ramswaroop.jbot.core.slack.Controller
import me.ramswaroop.jbot.core.slack.models.Event
import me.ramswaroop.jbot.core.slack.models.Message
import org.springframework.web.socket.WebSocketSession

import static me.ramswaroop.jbot.core.slack.EventType.*

/**
 * conversation entry points
 *
 * Created by fudge on 26.11.16.
 */
trait GeneralTopics {

    abstract Bot getSlackBot()

    @SuppressWarnings("GroovyUnusedDeclaration")
    @Controller(events = [DIRECT_MENTION, DIRECT_MESSAGE])
    void directMessage(WebSocketSession session, Event event) {
        slackBot.reply session, event, new Message("Hi, I am ${slackService.currentUser.name}")
    }

    @SuppressWarnings("GroovyUnusedDeclaration")
    @Controller(pattern = "(help)", next = "needHelp")
    void startHelp(WebSocketSession session, Event event) {
        slackBot.with {
            startConversation event, 'needHelp'
            reply session, event, new Message('Need help?')
        }
    }

    @SuppressWarnings("GroovyUnusedDeclaration")
    @Controller(next = 'showTopics')
    void needHelp(WebSocketSession session, Event event) {
        slackBot.with {
            if (!isConversationOn(event)) return

            if (event.text =~ /(?i)yes/) {
                reply session, event, new Message('Topics coming up')
                nextConversation event
            } else {
                reply session, event, new Message('F.ing askhole!')
                stopConversation event
            }
        }
    }

    @SuppressWarnings("GroovyUnusedDeclaration")
    @Controller
    void showTopics(WebSocketSession session, Event event) {
        slackBot.with {
            if (!isConversationOn(event)) return

            reply session, event, new Message('this is the shizzle!!')
            stopConversation event
        }
    }
}