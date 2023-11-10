package mk.ukim.fk.mpipaud

class Door

open class Engine(val tarque: Double = 10.0, val power: Int?) {

    override fun toString(): String {
        return "Engine: tarque = $tarque , power = $power"
    }
}

open class DieselEngine(tarque: Double = 10.0, power: Int = 100):Engine(tarque=tarque, power = power){
    override fun toString(): String {
        return "Engine: tarque = ${super.toString()}"
    }
}

class Car()

fun Engine.powerToHorsePower():Double {
    return power?.times(1.36) ?: 0.0
}

fun List<Engine>.customSum():Int{
    return this.customSum()
}

fun customSum(engines: List<Engine>, t:(Int)->(Double)): Double{
    var sum = 0.0

    for(e in engines){
        sum+= t(e.power?:0)
    }
    return sum
}

fun main() {
    val engine1 = Engine(tarque = 10.0, power = 100);
    val engine2 = Engine(tarque = 11.0, power = 120);
    val engine3 = Engine(tarque = 12.0, power = 140);
    val dieselEngine = DieselEngine(tarque = 10.0, power = 100);

    var engines = mutableListOf<Engine>(engine1,engine2,engine3,dieselEngine)

    engines.add(Engine(tarque = 13.0, power = 160))

    println("Sum of engine power: ${customSum(engines){e -> e.toDouble()*10}}")
    println(engines.filter { e -> e.power !! > 100 }.map { e -> e.powerToHorsePower() }.sortedDescending() )
}