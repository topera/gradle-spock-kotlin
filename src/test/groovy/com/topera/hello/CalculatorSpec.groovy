package com.topera.hello

import spock.lang.Specification

/**
 * Created by topera on 23/07/17.
 */
class CalculatorSpec extends Specification {

    def "test"() {
        when: "a and b are 1"
        def a = 1
        def b = 1

        then: "the sum is 2"
        Calculator.sum(a, b) == 3 // wrong test on purpose!
    }

}
