package specialtopic.groupfive.tugoflogic.roomdb

import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim
import specialtopic.groupfive.tugoflogic.roomdb.entities.ReasonInPlay
import specialtopic.groupfive.tugoflogic.roomdb.entities.TugGame
import specialtopic.groupfive.tugoflogic.roomdb.entities.User
import java.util.*

/**
 * This class is only use for seeding data when user first install and open the app
 * The data is here only the sample data
 * Users can generate data when using the App
 * */

fun seedUsers(): List<User> {
    return listOf(
        User(
            userId = 0, fullName = "My Instructor", username = "philosopher",
            userType = "instructor", email = "inst@douglas.ca", password = "1234",
            studentClass = null, studentNumber = null, gamePlayed = 0
        ),
        User(
            userId = 0, fullName = "Jim Le", username = "len13",
            userType = "student", email = "jim@douglas.student.ca", password = "1234",
            studentClass = "CSIS-4280", studentNumber = "300296440", gamePlayed = 0
        ),
        User(
            userId = 0, fullName = "Chau Nguyen", username = "nguyenn42",
            userType = "student", email = "chau@douglas.student.ca", password = "1234",
            studentClass = "CSIS-4280", studentNumber = "300298475", gamePlayed = 0
        ),
        User(
            userId = 0, fullName = "Quy Tran", username = "tranp6",
            userType = "student", email = "quy@douglas.student.ca", password = "1234",
            studentClass = "CSIS-4280", studentNumber = "300303518", gamePlayed = 0
        ),
        User(
            userId = 0, fullName = "Travis Wu", username = "wuc23",
            userType = "student", email = "travis@douglas.student.ca", password = "1234",
            studentClass = "CSIS-4280", studentNumber = "300301018", gamePlayed = 0
        )
    )
}

fun seedGames(): List<TugGame> {
    return listOf(
        TugGame(
            gameId = 0,
            startTime = Date(2020, 10, 6, 15, 30),
            endTime = Date(2020, 10, 6, 18, 30),
            numOfPlayer = 5,
            isCurrent = true
        )
    )
}

fun seedMainClaim(): List<MainClaim> {
    return listOf(
        MainClaim(
            mainClaimId = 0, gameId = 1,
            statement = "The world is flat", numOfAgree = 0, numOfDisagree = 0
        ),
        MainClaim(
            mainClaimId = 0, gameId = 1,
            statement = "The world is not flat", numOfAgree = 0, numOfDisagree = 0
        ),
        MainClaim(
            mainClaimId = 0, gameId = 1,
            statement = "The earth is a sphere", numOfAgree = 0, numOfDisagree = 0
        ),
    )
}

fun seedRips(): List<ReasonInPlay> {
    return listOf(
        ReasonInPlay(
            ripId = 0,
            mainClaimId = 1,
            studentId = 2,
            reasonStatement = "In term of commercial and network",
            description = "This support for the idea of the main claim",
            logicSide = "agree"
        ),
        ReasonInPlay(
            ripId = 0,
            mainClaimId = 1,
            studentId = 3,
            reasonStatement = "The world is not flat enough. There are some boundary.",
            description = "This somewhat against the main claim",
            logicSide = "disagree"
        ),
        ReasonInPlay(
            ripId = 0,
            mainClaimId = 1,
            studentId = 4,
            reasonStatement = "Absolute not, the world is global",
            description = "This object the main claim",
            logicSide = "disagree"
        ),
        ReasonInPlay(
            ripId = 0,
            mainClaimId = 1,
            studentId = 5,
            reasonStatement = "In term of cooperation and working",
            description = "This support for the idea of the main claim",
            logicSide = "agree"
        ),
    )
}