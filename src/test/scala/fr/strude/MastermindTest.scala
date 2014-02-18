package fr.strude

import fr.strude.Mastermind._

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FunSuite

class MastermindTest extends FunSuite with ShouldMatchers {

    test("should return red given wrong colors") {
        val testCombinaison = List('Y', 'Y', 'Y', 'Y')
        checkCombinaison(testCombinaison) should be(List('R', 'R', 'R', 'R'))
    }

    test("should return orange for each not good positioned color") {
        val testCombinaison = List('B', 'Y', 'Y', 'Y')
        checkCombinaison(testCombinaison) should be(List('O', 'R', 'R', 'R'))
    }

    test("should return green for each well positioned color") {
        val testCombinaison = List('R', 'Y', 'Y', 'Y')
        checkCombinaison(testCombinaison) should be(List('G', 'R', 'R', 'R'))
    }

    test("should throw exception given wrong sized array") {
        val testCombinaison = List('Y', 'Y')

        val exception = evaluating {
            checkCombinaison(testCombinaison)
        } should produce[IllegalArgumentException]

        exception should have ('message ("Error : Malformed Combinaison"))
    }

    test("should throw exception given wrong colors") {
        val testCombinaison = List('K', 'Y', 'Y', 'Y')

        val exception = evaluating {
            checkCombinaison(testCombinaison)
        } should produce[IllegalArgumentException]

        exception should have ('message ("Error : Malformed Combinaison"))
    }

}
