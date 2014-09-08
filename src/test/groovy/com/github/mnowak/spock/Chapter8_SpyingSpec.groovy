package com.github.mnowak.spock

import spock.lang.Specification

class Chapter8_SpyingSpec extends Specification {
    def "should allow verifying spied methods"() {
        given:
        def emailService = Spy(EmailServiceImpl)
        def receiver = Mock(Person)

        when:
        emailService.send(receiver, "Hi there!")

        then:
        1 * emailService.send(*_)
        1 * receiver.receiveMessage(_)


    }

    def "should allow changing spied methods behaviour"() {
        given:
        def emailService = Spy(EmailServiceImpl)
        def receiver = Mock(Person)

        when:
        def result = emailService.send(receiver, "Hi there!")

        then:
        result
        1 * emailService.send(*_) >> true
        0 * receiver.receiveMessage(_)
    }

    def "should allow calling real method and doing something more"() {
        given:
        def emailService = Spy(EmailServiceImpl)
        def receiver = Mock(Person)

        when:
        def result = emailService.send(receiver, "Hi there!")

        then:
        !result
        1 * emailService.send(*_) >> {callRealMethod(); false}
        1 * receiver.receiveMessage(_)
    }
}
