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

    @Ignore
    def "should allow static methods verifying"() {
        given:
            PowerMockito.mockStatic(StaticCalculator.class)

        when:
            def add = StaticCalculator.add(3, 7)
        then:
//            add == 10
            PowerMockito.verifyStatic();
            StaticCalculator.add(3, 7)

    }

}
