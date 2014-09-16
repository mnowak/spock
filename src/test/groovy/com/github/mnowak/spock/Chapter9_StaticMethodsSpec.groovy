package com.github.mnowak.spock

import org.junit.Rule
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule
import spock.lang.Ignore
import spock.lang.Specification

@PrepareForTest([StaticCalculator.class])
class Chapter9_StaticMethodsSpec extends Specification {

    @Rule
    PowerMockRule powerMockRule = new PowerMockRule();


    def "should allow static methods stubbing"() {
        given:
            PowerMockito.mockStatic(StaticCalculator.class)
            Mockito.when(StaticCalculator.add(2, 3)).thenReturn(0)

        when:
            def sum = StaticCalculator.add(2, 3)
        then:
            sum == 0

    }


    def "should allow static methods verifying"() {
        given:
            PowerMockito.mockStatic(StaticCalculator.class)
            Mockito.when(StaticCalculator.add(2, 3)).thenReturn(6)

        when:
            def add = StaticCalculator.add(2, 3)

            // not in then because of boolean evaluation expression
            PowerMockito.verifyStatic();
            StaticCalculator.add(2, 3)

        then:
            add == 6

    }

    def "should allow static methods verifying in then block"() {
        given:
            PowerMockito.mockStatic(StaticCalculator.class)
            Mockito.when(StaticCalculator.add(2, 3)).thenReturn(6)

        when:
            def add = StaticCalculator.add(2, 3)

        then:
            add == 6
            verifyStaticCall()

    }

    def verifyStaticCall() {
        PowerMockito.verifyStatic();
        StaticCalculator.add(2, 3)

        return true
    }

}
