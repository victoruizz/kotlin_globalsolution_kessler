package br.com.fiap.kessler.navigation

object Routes {
    const val HOME = "home"
    const val CATALOG = "catalog"
    const val DETAIL = "detail/{debrisId}"
    const val CONFIRMATION = "confirmation/{debrisId}"

    fun detail(debrisId: String) = "detail/$debrisId"
    fun confirmation(debrisId: String) = "confirmation/$debrisId"
}
