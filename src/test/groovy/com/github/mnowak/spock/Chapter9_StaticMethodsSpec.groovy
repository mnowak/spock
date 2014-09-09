package com.github.mnowak.spock

import org.junit.Rule
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule
import spock.lang.Specification

@PrepareForTest([StaticCalculator.class])
class Chapter9_StaticMethodsSpec extends Specification {

    @Rule
    PowerMockRule powerMockRule = new PowerMockRule();


    def "should allow static methods stubbing"() {
        given:
        PowerMockito.mockStatic(StaticCalculator.class)

        when:
        Mockito.when(StaticCalculator.add(2, 3)).thenReturn(0)

        then:
        StaticCalculator.add(2,3,) == 0

    }

    // TODO: verifying static methods


}
