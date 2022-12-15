package signature

import java.io.File

/**
 * Reads Roman font file,
 * returns a list of lines to build a character
 * from a given character
 */
fun extractingRomanLetter(char: Char): MutableList<String> {
    val file = File("roman.txt")
    val letterList = mutableListOf<String>()
    if (file.exists()) {
        val lines = file.readLines()
        for (i in lines.indices) {
            val (ch, _) = lines[i].split(" ")
            if (ch == char.toString()) {
                for (j in i + 1..i + 10) {
                    letterList.add(lines[j])
                }
            }
        }
    } else {
        println("The file is not found")
    }
    return letterList
}

/**
 * Reads Medium font file,
 * returns a list of lines to build a character
 * from a given character
 */
fun extractingMediumLetter(char: Char): MutableList<String> {
    val file = File("medium.txt")
    val letterList = mutableListOf<String>()
    if (file.exists()) {
        val lines = file.readLines()
        for (i in lines.indices) {
            val (ch, _) = lines[i].split(" ")
            if (ch == char.toString()) {
                for (j in i + 1..i + 3) {
                    letterList.add(lines[j])
                }
            }
        }
    } else {
        println("The file is not found")
    }
    return letterList
}

/**
 * Builds the whole card from a given name and status
 */
fun displayTag(name: String, status: String) {
    // detecting which string is longer: name or status
    var nameLine = ""
    for (ch in name) {
        if (ch in 'a'..'z' || ch in 'A'..'Z') {
            val letter = extractingRomanLetter(ch)
            nameLine += letter[0]
        } else if (ch == ' ') {
            nameLine += "          "
        }
    }
    var statusLine = ""
    for (ch in status) {
        if (ch in 'a'..'z' || ch in 'A'..'Z') {
            val letter = extractingMediumLetter(ch)
            statusLine += letter[0]
        } else if (ch == ' ') {
            statusLine += "     "
        }
    }
    // card building for longer name
    if (nameLine.length > statusLine.length) {
        // top border building
        for (i in 0..nameLine.length + 7) {
            print("8")
        }
        println()
        // name strings building
        for (line in 0 until 10) {
            var cardLine = "88  "
            for (ch in name) {
                if (ch in 'a'..'z' || ch in 'A'..'Z') {
                    val letter = extractingRomanLetter(ch)
                    cardLine += letter[line]
                } else if (ch == ' ') {
                    cardLine += "          "
                }
            }
            println("$cardLine  88")
        }
        // status strings building
        for (line in 0 until 3) {
            // building empty space on the left
            var cardLine = "88  "
            val spaceCount = ((nameLine.length) - statusLine.length) / 2
            for (i in 1..spaceCount) {
                cardLine += " "
            }
            // building status text
            for (ch in status) {
                if (ch in 'a'..'z' || ch in 'A'..'Z') {
                    val letter = extractingMediumLetter(ch)
                    cardLine += letter[line]
                } else if (ch == ' ') {
                    cardLine += "     "
                }
            }
            // building empty space on the right
            for (i in  0..nameLine.length - cardLine.length + 3) {
                cardLine += " "
            }
            println("$cardLine  88")
        }
        // bottom border building
        for (i in 0..nameLine.length + 7) {
            print("8")
        }
        println()
    // card building for longer status
    } else {
        // top border building
        for (i in 0..statusLine.length + 7) {
            print("8")
        }
        println()
        // name strings building
        for (line in 0 until 10) {
            // building empty space on the left
            var cardLine = "88  "
            val spaceCount = ((statusLine.length) - nameLine.length) / 2
            for (i in 1..spaceCount) {
                cardLine += " "
            }
            // building name text
            for (ch in name) {
                if (ch in 'a'..'z' || ch in 'A'..'Z') {
                    val letter = extractingRomanLetter(ch)
                    cardLine += letter[line]
                } else if (ch == ' ') {
                    cardLine += "          "
                }
            }
            // building empty space on the right
            for (i in  0..statusLine.length - cardLine.length + 3) {
                cardLine += " "
            }
            println("$cardLine  88")
        }
        // status strings building
        for (line in 0 until 3) {
            var cardLine = "88  "
            for (ch in status) {
                if (ch in 'a'..'z' || ch in 'A'..'Z') {
                    val letter = extractingMediumLetter(ch)
                    cardLine += letter[line]
                } else if (ch == ' ') {
                    cardLine += "     "
                }
            }
            println("$cardLine  88")
        }
        // bottom border building
        for (i in 0..statusLine.length + 7) {
            print("8")
        }
    }
}

/**
 * Asks for a name and status for building a card
 */
fun nameTagApp() {
    print("Enter name and surname: ")
    val name = readln()
    print("Enter person's status: ")
    val status = readln()
    displayTag(name, status)
}

fun main() {
    nameTagApp()
}