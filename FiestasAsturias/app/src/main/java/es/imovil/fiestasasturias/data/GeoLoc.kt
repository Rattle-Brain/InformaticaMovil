package es.imovil.fiestasasturias.data

import java.io.Serializable

/**
 * Clase para la geolocalizacion de la fiesta
 */
class GeoLoc(
    var fNombre: String,
    var latitud: Double,
    var longitud: Double
): Serializable