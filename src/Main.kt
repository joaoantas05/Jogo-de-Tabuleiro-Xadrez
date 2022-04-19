import kotlin.math.absoluteValue

val invalido = "Invalid response."

fun buildMenu() :String{
    return "1-> Start New Game;\n2-> Exit Game.\n"
}

fun buildBoard(numColumns: Int, numLines: Int, showLegend: Boolean = false, showPieces: Boolean = false, pieces: Array<Pair<String,String>?>): String{
    val esc: String = 27.toChar().toString()
    val end = "$esc[0m"
    var stringColumns = 'A'
    var countColumms = 1
    var countLines = 1
    var countBlueColumn = 0
    var countBlueLine = 1
    var board = ""
    var count = 0

    val startBlue = "$esc[30;44m"
    val startGrey = "$esc[30;47m"
    val startWhite = "$esc[30;30m"

    val espacoAzul = "$startBlue   $end"
    if (showLegend){

        board += espacoAzul
        do {
            board +="$startBlue $stringColumns $end"
            stringColumns++
            countBlueColumn++
        }while (countBlueColumn < numColumns)

        board += espacoAzul
        board +="\n"
    }

    do {

        if (showLegend){
            board += "$startBlue $countBlueLine $end"
        }
        countBlueLine++

        do {
            if ((countColumms % 2 == 0 && countLines % 2 == 0)||(countColumms % 2 != 0 && countLines % 2 != 0 ) ){
                if( showPieces) {
                    board += "$startWhite ${convertStringToUnicode(pieces[count]?.first ?: "", pieces[count]?.second ?: "")} $end"
                }else {
                    board += "$startWhite   $end"
                }
            }else if ((countColumms % 2 == 0 && countLines % 2 != 0) || (countColumms % 2 != 0 && countLines % 2 == 0 ) ){
                if(showPieces) {
                    board += "$startGrey ${convertStringToUnicode(pieces[count]?.first ?: "", pieces[count]?.second ?: "")} $end"
                }else {
                    board += "$startGrey   $end"
                }
            }
            count++
            countColumms++
        }while (countColumms <= numColumns)
        if (showLegend){
            board += espacoAzul
        }
        board += "\n"
        countLines++
        countColumms=1
    }while (countLines <= numLines)

    if (showLegend){
        countBlueLine=0
        do {
            board += espacoAzul
            countBlueLine++
        }while (countBlueLine< numColumns+2)
        board += "\n"
    }
    return (board)
}

fun createInitialBoard(numColumns: Int, numLines: Int): Array<Pair<String,String>?>{
    val arrayPieces: Array<Pair<String,String>?> = arrayOfNulls(numColumns*numLines)
    val wKnight: Pair<String, String> = Pair("P", "w")
    val wTower: Pair<String, String> = Pair("T", "w")
    val wHorse: Pair<String, String> = Pair("H", "w")
    val wBishop: Pair<String, String> = Pair("B", "w")
    val wQueen: Pair<String, String> = Pair("Q", "w")
    val wKing: Pair<String, String> = Pair("K", "w")
    val bKnight: Pair<String, String> = Pair("P", "b")
    val bTower: Pair<String, String> = Pair("T", "b")
    val bHorse: Pair<String, String> = Pair("H", "b")
    val bBishop: Pair<String, String> = Pair("B", "b")
    val bQueen: Pair<String, String> = Pair("Q", "b")
    val bKing: Pair<String, String> = Pair("K", "b")

    if (numColumns == 8 && numLines == 8){

        arrayPieces[0] = bTower
        arrayPieces[1] = bHorse
        arrayPieces[2] = bBishop
        arrayPieces[3] = bQueen
        arrayPieces[4] = bKing
        arrayPieces[5] = bBishop
        arrayPieces[6] = bHorse
        arrayPieces[7] = bTower

        var count = 8
        do {
            arrayPieces[count] = bKnight
            count++
        }while (count <= 15)

        count = 48
        do {
            arrayPieces[count] = wKnight
            count++
        }while (count <= 55)

        arrayPieces[56] = wTower
        arrayPieces[57] = wHorse
        arrayPieces[58] = wBishop
        arrayPieces[59] = wKing
        arrayPieces[60] = wQueen
        arrayPieces[61] = wBishop
        arrayPieces[62] = wHorse
        arrayPieces[63] = wTower

    }else if(numColumns == 7 && numLines == 7) {

        arrayPieces[0] = bTower
        arrayPieces[1] = bHorse
        arrayPieces[2] = bBishop
        arrayPieces[3] = bKing
        arrayPieces[4] = bBishop
        arrayPieces[5] = bHorse
        arrayPieces[6] = bTower

        var count = 7
        do {
            arrayPieces[count] = bKnight
            count++
        }while (count <= 13)

        count = 35
        do {
            arrayPieces[count] = wKnight
            count++
        }while (count <= 41)

        arrayPieces[42] = wTower
        arrayPieces[43] = wHorse
        arrayPieces[44] = wBishop
        arrayPieces[45] = wKing
        arrayPieces[46] = wBishop
        arrayPieces[47] = wHorse
        arrayPieces[48] = wTower

    }else if(numColumns == 6 && numLines == 7) {

        arrayPieces[0] = bTower
        arrayPieces[1] = bBishop
        arrayPieces[2] = bQueen
        arrayPieces[3] = bKing
        arrayPieces[4] = bBishop
        arrayPieces[5] = bHorse

        var count = 6
        do {
            arrayPieces[count] = bKnight
            count++
        }while (count <= 11)

        count = 30
        do {
            arrayPieces[count] = wKnight
            count++
        }while (count <= 35)

        arrayPieces[36] = wTower
        arrayPieces[37] = wBishop
        arrayPieces[38] = wKing
        arrayPieces[39] = wQueen
        arrayPieces[40] = wBishop
        arrayPieces[41] = wHorse

    }else if(numColumns == 6 && numLines == 6) {

        arrayPieces[0] = bHorse
        arrayPieces[1] = bBishop
        arrayPieces[2] = bQueen
        arrayPieces[3] = bKing
        arrayPieces[4] = bBishop
        arrayPieces[5] = bTower

        var count = 6
        do {
            arrayPieces[count] = bKnight
            count++
        }while (count <= 11)

        count = 24
        do{
            arrayPieces[count] = wKnight
            count++
        }while (count <= 29)

        arrayPieces[30] = wHorse
        arrayPieces[31] = wBishop
        arrayPieces[32] = wKing
        arrayPieces[33] = wQueen
        arrayPieces[34] = wBishop
        arrayPieces[35] = wTower

    }else if(numColumns == 4 && numLines == 4) {
        arrayPieces[2] = bTower
        arrayPieces[3] = bBishop
        arrayPieces[12] = wTower
        arrayPieces[13] = wQueen
    }else return emptyArray()

    return arrayPieces
}

fun createTotalPiecesAndTurn(numColumns: Int, numLines: Int): Array<Int?>{
    var numBrancas: Int
    var numPretas: Int
    val turnoAtual = 0

    if (numColumns == 8 && numLines == 8){
        numBrancas = 16
        numPretas = 16
    }else if (numColumns == 7 && numLines == 7){
        numBrancas = 14
        numPretas = 14
    }else if (numColumns == 6 && numLines == 6){
        numBrancas = 12
        numPretas = 12
    }else if (numColumns == 6 && numLines == 7){
        numBrancas = 12
        numPretas = 12
    }else if (numColumns == 4 && numLines == 4){
        numBrancas = 2
        numPretas = 2
    }else return emptyArray()

    return arrayOf(numBrancas, numPretas, turnoAtual)
}

fun convertStringToUnicode(piece: String, color: String): String{
    if (piece == "K" && color == "w"){
        return "♔"
    }else if (piece == "Q" && color == "w"){
        return "♕"
    }else if (piece == "B" && color == "w"){
        return "♗"
    }else if (piece == "T" && color == "w"){
        return "♖"
    }else if (piece == "H" && color == "w"){
        return "♘"
    }else if (piece == "P" && color == "w"){
        return "♙"
    }else if (piece == "K" && color == "b"){
        return "♚"
    }else if (piece == "Q" && color == "b"){
        return "♛"
    }else if (piece == "B" && color == "b"){
        return "♝"
    }else if (piece == "T" && color == "b"){
        return "♜"
    }else if (piece == "H" && color == "b"){
        return "♞"
    }else if (piece == "P" && color == "b"){
        return "♟"
    }else return (" ")
}

fun getCoordinates (readText: String?): Pair<Int, Int>?{
    var linhas  : Int
    var colunas : Int

    if (readText?.length==2){
        if (readText!![0].toInt()-48 in 1..8){
            linhas = readText!![0].toInt()-48
        }else return null

        if (readText[1].toUpperCase() == 'A' ){
            colunas = 1
        }else if (readText[1].toUpperCase() == 'B' ){
            colunas = 2
        }else if (readText[1].toUpperCase() == 'C' ){
            colunas = 3
        }else if (readText[1].toUpperCase() == 'D' ){
            colunas = 4
        }else if (readText[1].toUpperCase() == 'E' ){
            colunas = 5
        }else if (readText[1].toUpperCase() == 'F' ){
            colunas = 6
        }else if (readText[1].toUpperCase() == 'G' ){
            colunas = 7
        }else if (readText[1].toUpperCase() == 'H' ){
            colunas = 8
        }else return null

        return Pair(linhas, colunas)
    }else return null

}

fun checkRightPieceSelected(pieceColor: String, turn: Int): Boolean{
    return (turn % 2 == 0  && pieceColor == "w") || (turn % 2 == 1  && pieceColor == "b")
}

fun isCoordinateInsideChess (coord: Pair<Int, Int>,numColumns: Int,numLines: Int):Boolean{
    return coord.first in 1..numColumns && coord.second in 1..numLines
}

fun isValidTargetPiece(currentSelectedPiece: Pair<String, String>,currentCoord : Pair<Int, Int>, targetCoord : Pair<Int, Int>,
                       pieces : Array<Pair<String, String>?>, numColumns: Int, numLines: Int):Boolean{
    var valid=false
    if (currentSelectedPiece.first == "w"){
        if (currentSelectedPiece.second == "K"){
            valid = (isKingValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }else if (currentSelectedPiece.second == "Q"){
            valid = (isQueenValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }else if (currentSelectedPiece.second == "B"){
            valid = (isBishopValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }else if (currentSelectedPiece.second == "H"){
            valid = (isHorseValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }else if (currentSelectedPiece.second == "T"){
            valid = (isTowerValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }else if (currentSelectedPiece.second == "P"){
            valid = (isKnightValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }
    }else if (currentSelectedPiece.first == "w"){
        if (currentSelectedPiece.second == "K"){
            valid = (isKingValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }else if (currentSelectedPiece.second == "Q"){
            valid = (isQueenValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }else if (currentSelectedPiece.second == "B"){
            valid = (isBishopValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }else if (currentSelectedPiece.second == "H"){
            valid = (isHorseValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }else if (currentSelectedPiece.second == "T"){
            valid = (isTowerValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }else if (currentSelectedPiece.second == "P"){
            valid = (isKnightValid(currentCoord, targetCoord, pieces, numColumns, numLines))
        }
    }


    return true
}

fun movePiece( pieces : Array<Pair<String, String>?>, numColumns: Int, numLines: Int, currentCoord: Pair<Int, Int>,
               targetCoord: Pair<Int, Int>, totalPiecesAndTurn : Array<Int>): Boolean{

    val posicaoInicial = (currentCoord.first-1)* numColumns + (currentCoord.second-1)
    val posicaoFinal = (targetCoord.first - 1) * numColumns + targetCoord.second - 1
    if (isValidTargetPiece(pieces[posicaoInicial]?:Pair("",""), currentCoord, targetCoord, pieces, numColumns, numLines)) {
        if (pieces[posicaoInicial]?.second == "w") {
            totalPiecesAndTurn[2]++
            if (pieces[posicaoFinal]?.second == "b") {
                totalPiecesAndTurn[1]--
            }

        }else if (pieces[posicaoInicial]?.second == "b"){
            totalPiecesAndTurn[2]--
            if (pieces[posicaoFinal]?.second == "w"){
                totalPiecesAndTurn[0]--
            }
        }
        pieces[posicaoFinal] = pieces[posicaoInicial]
        pieces[posicaoInicial] = null
        return true
    }
    return false
}

fun startNewGame (whitePlayer: String, blackPlayer: String, pieces : Array<Pair<String, String>?>, totalPiecesAndTurn : Array<Int?>,
                  numColumns: Int,numLines: Int, showLegend: Boolean = false, showPieces: Boolean = false) {



    var escolhePeca = ""
    var currentPos: Pair<Int, Int>? = Pair(0,0)
    var targetPos: Pair<Int, Int>? = Pair(0,0)

    do {
        if (totalPiecesAndTurn[2] == 0) {
            do {
                println(buildBoard(numColumns,numLines, showLegend, showPieces, pieces))

                println("$whitePlayer, choose a piece (e.g 2D).\nMenu-> m;\n")
                escolhePeca = readLine().toString()
                currentPos = getCoordinates(escolhePeca)
                if (escolhePeca == "m"){
                    return
                }else{
                    if (!checkRightPieceSelected(pieces[numColumns*(currentPos?.second!!-1) +
                                currentPos?.first!! -1]?.second ?: "", totalPiecesAndTurn[2]!!)){
                        println(invalido)
                    }
                }

            }while (!checkRightPieceSelected(pieces[numColumns*(currentPos?.second!!-1) +
                        currentPos?.first!! -1]?.second ?: "", totalPiecesAndTurn[2]!!) && escolhePeca != "m")
            do {
                println("$whitePlayer, choose a target piece (e.g 2D).\nMenu-> m;\n")
                escolhePeca = readLine().toString()
                currentPos = getCoordinates(escolhePeca)
                if (!checkRightPieceSelected(pieces[numColumns*(currentPos?.second!!-1) +
                            currentPos?.first!! -1]?.second ?: "", totalPiecesAndTurn[2]!!)){
                    println(invalido)
                }else {

                    buildBoard(numColumns, numLines, showLegend, showPieces, createInitialBoard(numColumns, numLines))
                }
            }while (!checkRightPieceSelected(pieces[numColumns*(currentPos?.second!!-1) +
                        currentPos?.first!! -1]?.second ?: "", totalPiecesAndTurn[2]!!))
        }else if (totalPiecesAndTurn[2] == 1) {
            do{
                println("$blackPlayer, choose a piece (e.g 2D)\nMenu-> m;")
                escolhePeca = readLine().toString()
                currentPos = getCoordinates(escolhePeca)
                if (escolhePeca == "m"){
                    return
                }else{
                    if (!checkRightPieceSelected(pieces[numColumns*(currentPos?.second!!-1) +
                                currentPos?.first!! -1]?.second ?: "", totalPiecesAndTurn[2]!!)){
                        println(invalido)
                    }
                }

            }while (!checkRightPieceSelected(pieces[numColumns*(currentPos?.second!!-1) +
                        currentPos?.first!! -1]?.second ?: "", totalPiecesAndTurn[2]!!) && escolhePeca != "m")
            do {
                println("$blackPlayer, choose a target piece (e.g 2D)\nMenu-> m;")
                escolhePeca = readLine().toString()
                targetPos = getCoordinates(escolhePeca)
                if (!checkRightPieceSelected(pieces[numColumns*(targetPos?.second!!-1) +
                            targetPos?.first!! -1]?.second ?: "", totalPiecesAndTurn[2]!!)){
                    println(invalido)
                }else {
                    movePiece(pieces, numColumns, numLines, currentPos, targetPos, totalPiecesAndTurn as Array<Int>)
                    buildBoard(numColumns, numLines, showLegend, showPieces, createInitialBoard(numColumns, numLines))
                }
            }while (!checkRightPieceSelected(pieces[numColumns*(targetPos?.second!!-1) +
                        targetPos?.first!! -1]?.second ?: "", totalPiecesAndTurn[2]!!))
        }
    }while (escolhePeca != "m" || totalPiecesAndTurn[0] == 0 || totalPiecesAndTurn[1] == 0)
}
fun isHorseValid(currentCoord: Pair<Int, Int>,targetCoord : Pair<Int, Int>,pieces: Array<Pair<String, String>?>,
                 numColumns: Int, numLines: Int):Boolean{
    val currentLinhas = currentCoord.first
    val currentColunas = currentCoord.second
    val targetLinhas = targetCoord.first
    val targetColunas = targetCoord.second

    if (pieces[((targetCoord.first - 1) * numColumns) + targetCoord.second - 1] != null) {
        if (pieces[((currentCoord.first - 1) * numColumns) + currentCoord.second - 1]!!.second != pieces[((targetCoord.first - 1) * numColumns) + targetCoord.second - 1]!!.second) {
        } else {
            return false
        }
    }
    if (currentLinhas != targetLinhas - 1 || !(currentColunas + 2 == targetColunas || currentColunas - 2 == targetColunas)) {
        if (currentLinhas != targetLinhas + 1 || !(currentColunas + 2 == targetColunas || currentColunas - 2 == targetColunas)) {
            if (currentLinhas != targetLinhas - 2 || !(currentColunas + 1 == targetColunas || currentColunas - 1 == targetColunas)) {
                if (currentLinhas != targetLinhas + 2 || !(currentColunas + 1 == targetColunas || currentColunas - 1 == targetColunas)) {
                    return false
                }
                return true
            }
            return true
        }
        return true
    }
    return true
}
fun isKingValid(currentCoord: Pair<Int, Int>,targetCoord : Pair<Int, Int>,pieces: Array<Pair<String, String>?>,
                numColumns: Int, numLines: Int):Boolean{
    val currentColunas = currentCoord.first
    val currentLinhas = currentCoord.second
    val targetColunas = targetCoord.first
    val targetLinhas = targetCoord.second

    if (currentCoord.second <= targetCoord.second + 1 || currentCoord.second >= targetCoord.second + 1) {
        if (currentCoord.first > targetCoord.first + 1 && currentCoord.first < targetCoord.first + 1) {
            return false
        }
        if (pieces[((targetCoord.first - 1) * numColumns) + targetCoord.second - 1] == null) {
            return true
        }
        if (pieces[((targetCoord.first - 1) * numColumns) + targetCoord.second - 1]!!.second ==
            pieces[((currentCoord.first - 1) * numColumns) + currentCoord.second - 1]!!.second) {
            return false
        }
        return true
    }
    return false

}
fun isTowerValid(currentCoord: Pair<Int, Int>,targetCoord : Pair<Int, Int>,pieces: Array<Pair<String, String>?>,
                 numColumns: Int, numLines: Int):Boolean{
    val currentColunas = currentCoord.first
    val currentLinhas = currentCoord.second
    val targetColunas = targetCoord.first
    val targetLinhas = targetCoord.second

    return if (currentColunas == targetColunas || currentLinhas == targetLinhas
        && targetColunas>=0 && targetColunas<=numColumns && targetLinhas>=0 && targetLinhas<=numLines){
        pieces[numColumns*(targetColunas-1) + targetLinhas-1] == null || pieces[numColumns*(targetColunas-1) + targetLinhas-1]!!.second != pieces[numColumns*(currentColunas-1) + currentLinhas-1]!!.second
    }else false

}
fun isBishopValid(currentCoord: Pair<Int, Int>,targetCoord : Pair<Int, Int>,pieces:Array<Pair<String, String>?>,
                  numColumns: Int, numLines: Int):Boolean{
    val currentColunas = currentCoord.first
    val currentLinhas = currentCoord.second
    val targetColunas = targetCoord.first
    val targetLinhas = targetCoord.second

    return if ((currentColunas - targetColunas).absoluteValue == (currentLinhas - targetLinhas).absoluteValue && targetColunas>=0 && targetColunas<=numColumns
        && targetLinhas>=0 && targetLinhas<=numLines){
        pieces[numColumns*(targetColunas-1) + targetLinhas-1] == null || pieces[numColumns*(targetColunas-1) + targetLinhas-1]!!.second != pieces[numColumns*(currentColunas-1) + currentLinhas-1]!!.second
    }else false
}
fun isQueenValid(currentCoord: Pair<Int, Int>,targetCoord : Pair<Int, Int>,pieces: Array<Pair<String, String>?>,
                 numColumns: Int, numLines: Int):Boolean{
    val currentColunas = currentCoord.first
    val currentLinhas = currentCoord.second
    val targetColunas = targetCoord.first
    val targetLinhas = targetCoord.second

    return if (((currentColunas - targetColunas).absoluteValue == (currentLinhas - targetLinhas).absoluteValue && targetColunas>=0 && targetColunas<=numColumns )||
        (currentColunas == targetColunas || currentLinhas == targetLinhas)
        && targetLinhas>=0 && targetLinhas<=numLines){
        pieces[numColumns*(targetColunas-1) + targetLinhas-1] == null || pieces[numColumns*(targetColunas-1) + targetLinhas-1]!!.second != pieces[numColumns*(currentColunas-1) + currentLinhas-1]!!.second
    }else false

}
fun isKnightValid(currentCoord: Pair<Int, Int>,targetCoord : Pair<Int, Int>,pieces: Array<Pair<String, String>?>,
                  numColumns: Int, numLines: Int):Boolean{
    val currentColunas = currentCoord.first
    val currentLinhas = currentCoord.second
    val targetColunas = targetCoord.first
    val targetLinhas = targetCoord.second

    val posicaoFinal = ((targetCoord.first - 1) * numColumns) + targetCoord.second - 1

    if (pieces[((targetCoord.first - 1) * numColumns) + targetCoord.second - 1] == null || pieces[((targetCoord.first - 1) * numColumns) +
                targetCoord.second - 1]!!.second != pieces[((currentCoord.first - 1) * numColumns) + currentCoord.second - 1]!!.second) {
        if (currentCoord.second == targetCoord.second) {
            if (currentCoord.first != targetCoord.first - 1 && currentCoord.first != targetCoord.first + 1) {
                return false
            }
            return true
        }
        return false
    }
    return false
}

fun columnsIsValid(numColumns: String): Boolean{
    return checkIsNumber(numColumns)&&numColumns.toInt() == 4 || numColumns.toInt() == 6 || numColumns.toInt() == 7 || numColumns.toInt() == 8
}

fun linesIsValid(numColumns: String, numLines: String): Boolean{
    if (columnsIsValid(numColumns)){
        return numColumns.toInt() == 4 && numLines.toInt() == 4 || numColumns.toInt() == 6 && numLines.toInt() == 6 || numColumns.toInt() == 6 &&
                numLines.toInt() == 7 || numColumns.toInt() == 7 && numLines.toInt() == 7 || numColumns.toInt() == 8 && numLines.toInt() == 8
    }else return false
}

fun checkIsNumber(number: String): Boolean{
    return number in "0".."9"
}

fun checkName(number: String): Boolean{
    var contador = 0
    var espaco = 0

    do{
        if(number[contador] == ' '){
            espaco = contador
        }

        if (number[contador] in 'A'..'Z' && contador-1 != espaco && contador != 0){
            return false
        }
        contador++
    }while (contador < number.length)

    if(espaco == number.length -1){
        return false
    }else if (number[0] == ' '){
        return false
    }
    return number[0] in 'A'..'Z' && number[espaco + 1] in 'A'..'Z'
}

fun showChessLegendOrPieces(message: String): Boolean?{
    if(message== "Y"||message== "y"){
        return true
    }else if (message== "N"||message== "n"){
        return false
    }else{
        return null
    }
}

fun main(){
    println("Welcome to the Chess Board Game!")

    var player1Nome = ""
    var player2Nome = ""
    var columns = "0"
    var lines = "0"
    var legend :String
    var pieces :String

    do {

        println(buildMenu())

        val menu:Int? = readLine()?.toIntOrNull()
        if (menu == 1) {
            do {
                println("First player name?\n")
                player1Nome = readLine().toString()

                if (player1Nome.isEmpty() || !checkName(player1Nome)) {
                    println(invalido)

                }
            } while (player1Nome.isEmpty() || !checkName(player1Nome))
            do {
                println("Second player name?\n")
                player2Nome = readLine()!!

                if (player2Nome.isEmpty() || !checkName(player2Nome)) {
                    println(invalido)

                }
            } while (player2Nome.isEmpty() || !checkName(player2Nome))

            do {
                println("How many chess columns?\n")
                columns = readLine().toString()


                println("How many chess lines?\n")
                lines = readLine().toString()
                if (!linesIsValid(columns, lines)) {
                    println(invalido)
                }
            } while (!linesIsValid(columns, lines))

            do {
                println("Show legend (y/n)?\n")
                legend = readLine()!!.toString()
                if(showChessLegendOrPieces(legend) == null){
                    println(invalido)
                }
            }while (showChessLegendOrPieces(legend) == null)

            do {
                println("Show pieces (y/n)?\n")
                pieces = readLine()!!.toString()
                if(showChessLegendOrPieces(pieces) == null) {
                    println(invalido)
                }
            }while (showChessLegendOrPieces(pieces) == null)

            val confirmaLegenda = showChessLegendOrPieces(legend)?: false
            val confirmaPecas = showChessLegendOrPieces(pieces)?: false

            val arrayPieces = createInitialBoard(columns.toInt(), lines.toInt(),)


            startNewGame(player1Nome, player2Nome,  arrayPieces, createTotalPiecesAndTurn(columns.toInt(),
                lines.toInt()), columns.toInt(), lines.toInt(), confirmaLegenda, confirmaPecas)
        }
    } while (menu != 2)

}