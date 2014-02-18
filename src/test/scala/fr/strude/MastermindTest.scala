package fr.strude

import fr.strude.Mastermind._

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite

class MastermindTest extends FunSuite with ShouldMatchers {

    test("should return red given a wrong color") {
        getResultColor('Y', 0, List('R', 'R', 'W', 'W')) should be('R')
    }

    test("should return orange given a not good positioned color") {
        getResultColor('W', 0, List('R', 'R', 'W', 'W')) should be('O')
    }

    test("should return green given a well positioned color") {
        getResultColor('R', 0, List('R', 'R', 'W', 'W')) should be('G')
    }

    test("should return a list of 'G'|'O'|'R' when comparing 2 combinaisons") {
        compareCombinaisons(List('R', 'W', 'B', 'O'), List('R', 'R', 'W', 'W')) should be(List('G', 'O', 'R', 'R'))
    }

    test("should return false given a not accepted color") {
        checkColorIntegrity(List('H', 'R', 'R', 'R')) should be(false)
    }

    test("should return false given a wrong sized array") {
        checkIntegrity(List('R')) should be(false)
    }

    //integration
    test("should throw exception given wrong sized array") {
        val testCombinaison = List('Y', 'Y')

        val exception = evaluating {
            checkCombinaison(testCombinaison)
        } should produce[IllegalArgumentException]

        exception should have ('message ("Error : Malformed Combinaison"))
    }

    //integration
    test("should return a list of results when testing a combinaison") {
        val testCombinaison = List('R', 'G', 'W', 'W')
        checkCombinaison(testCombinaison) should be(List('G', 'O', 'R', 'R'))
    }
}
