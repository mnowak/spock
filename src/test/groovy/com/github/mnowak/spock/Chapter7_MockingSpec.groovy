package com.github.mnowak.spock

import spock.lang.Specification


/**
 * Nice, yet powerful DSL for mocking your collaborators.
 */
class Chapter7_MockingSpec extends Specification {

    def "should allow verifying a simple interaction"() {
        given:
            def mocked = Mock(EmailService)
            def person = new Person("Bob")

        when:
            mocked.send(person, "Hello!")

        then:
            1 * mocked.send(person, "Hello!")
            0 * mocked.send(person, "not executed")

    }

    def "only interactions within the 'when' block should be considered"() {
        given:
            def mocked = Mock(EmailService)
            def person = new Person("Bob")
            mocked.send(person, "Hello!")

        when:
            mocked.send(person, "Hello!")

        then:
            1 * mocked.send(person, "Hello!")
    }

    def "should allow verifying an interaction with argument constraints"() {
        given:
            def mocked = Mock(EmailService)
            def person = new Person("Bob")

        when:
            mocked.send(person, "Hello!")
            mocked.send(person, "not a bad message")
            mocked.send(person, "# message")

        then:
            1 * mocked.send(_ as Person, "Hello!")
            1 * mocked.send(_, !"bad message")
            1 * mocked.send(!null, { it.startsWith("#") })
    }

    def "should allow verifying an interaction not caring about its arguments"() {
        given:
            def mocked = Mock(EmailService)
            def person = new Person("Bob")

        when:
            mocked.send(person, "Hello!")

        then:
            1 * mocked.send(*_)
    }

    def "should allow strict mocking"() {
        given:
            def mock = Mock(EmailService)
            def secondMock = Mock(EmailService)

        when:
            mock.send(new Person("Johny"), "Hi Johny")
            // uncomment the following line to raise error
            // secondMock.send(new Person("Jack"), "Hello Jack")

        then:
            1 * mock.send(*_)
            0 * _  // zero interactions on any other mock

    }


    def "should allow imposing the order of invocations"() {
        given:
            def mocked = Mock(EmailService)
            def person = new Person("Bob")

        when:
            mocked.send(person, "Hello!")
            mocked.send(person, "Bye!")

        then:
            1 * mocked.send(person, "Hello!")

        then:
            1 * mocked.send(person, "Bye!")
    }

    def "should allow veryfing nested method calls"() {
        given:
            def service = new EmailServiceImpl()
            def person = Mock(Person)
        when:
            service.send(person, "<3")
        then:
            1 * person.receiveMessage("<3")
    }


    def "should allow capturing arguments"() {
        given:
            def mocked = Mock(EmailService)
            def person = new Person("Bob")
            def capturedName

        when:
            mocked.send(person, "Hello!")

        then:
            1 * mocked.send(person, "Hello!") >> { Person p, message ->
                capturedName = p.getName();
            }

            capturedName == "Bob"

    }

    def "should allow inline argument verification"() {
        given:
            def mocked = Mock(EmailService)
            def person = new Person("Bob")

        when:
            mocked.send(person, "Hello!")

        then:
            1 * mocked.send(_, _) >> { Person p, String message -> message.length() > 0 }

    }


    def "should allow mixing mocking and stubbing (you shouldn't usually need this)"() {
        given:
            def mocked = Mock(DataProvider)

        when:
            def data = mocked.pagedData(0, 5)

        then:
            data == ["A", "B"]
            1 * mocked.pagedData(0, 5) >> ["A", "B"]
    }
}
