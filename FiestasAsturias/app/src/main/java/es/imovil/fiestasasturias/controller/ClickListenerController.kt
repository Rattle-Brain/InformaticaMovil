package es.imovil.fiestasasturias.controller

/**
 * Controller de click en elemento de la lista de fiestas
 * Con la posicion se saca el nombre
 */
interface ClickListenerController {
    fun onItemClick(position: Int)
}