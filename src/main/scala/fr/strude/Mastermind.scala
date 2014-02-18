package fr.strude

import scala.annotation.tailrec

object Mastermind {

    type Color = Char;
    
    val secretCombinaison: List[Color] = List('R', 'O', 'G', 'B')
    val COMBINAISON_SIZE: Int = secretCombinaison.length
    
    val acceptedColors: List[Color] = List('R', 'G', 'B', 'O', 'Y', 'W')
    // R=Red, G=Green, B=Blue, O=Orange, Y=Yellow, W=White

    // Get the result for a given color at a given position against the expected combinaison
    def getResultColor(color: Color, colorPosition: Int, expectedCombinaison: List[Color], actualPosition: Int = 0, result: Color = 'R'): Color = {

        expectedCombinaison match {
            case Nil => result

            case head::tail =>
                if (color.equals(head)) {

                    if(colorPosition.equals(actualPosition)) 'G'
                    else getResultColor(color, colorPosition, tail, actualPosition+1, 'O')

                } else getResultColor(color, colorPosition, tail, actualPosition+1, result)
        }
    }

    // Check each position of the tested combinaison
    def compareCombinaisons(combinaison: List[Color], expectedCombinaison: List[Color], pos: Int = 0): List[Color] = {
        combinaison match {
            case Nil => Nil
            case head::tail => getResultColor(head, pos, expectedCombinaison) :: compareCombinaisons(tail, expectedCombinaison, pos+1)
        }
    }

    @tailrec
    def checkColorIntegrity(combinaison: List[Color]): Boolean = {
        combinaison match {
            case Nil => true
            case head::tail => if(acceptedColors.contains(head)) checkColorIntegrity(tail)
                               else false
        }
    }

    def checkIntegrity(combinaison: List[Color]): Boolean = {
        combinaison.length == COMBINAISON_SIZE && checkColorIntegrity(combinaison)
    }

    def checkCombinaison(combinaison: List[Color]): List[Color] = {
        if(checkIntegrity(combinaison))
            compareCombinaisons(combinaison, secretCombinaison)
        else
            throw new IllegalArgumentException("Error : Malformed Combinaison")
    }

}
