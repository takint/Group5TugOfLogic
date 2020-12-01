package specialtopic.groupfive.tugoflogic.instructor.ui.gameroom

import specialtopic.groupfive.tugoflogic.roomdb.entities.MainClaim

interface IMainClaim {
    fun updateMainClaimStatus(mainClaim: MainClaim, newStatus: Boolean)
    fun setCurrentMainClaim(mainClaim: MainClaim)
}