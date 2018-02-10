package com.topera.hello


import spock.lang.Specification

/**
 * Created by topera on 10/02/18.
 */
class MainSpec extends Specification {

    def "test"() {
        expect:
        "Hi" == new Main().test()
    }

}
