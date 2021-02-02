# TPE3 - SIA

## Requisitos necesarios
* Java >= 1.8
* Maven >= 3.5.0

## Ejecutar el proyecto

```
> git clone https://github.com/nclozza/sia-tpe3.git TPE3
> cd TPE3
> mvn clean package
> java -jar target/project-1.0-SNAPSHOT.jar
```

## Parámetros de configuración
Los siguientes parámetros deben colocarse en formato json en el archivo config.json


* personaje: string
Nombre de los personajes
 
* fuerza: double
Multiplicador particular del tipo de personaje
 
* agilidad: double
Multiplicador particular del tipo de personaje
 
* pericia: double
Multiplicador particular del tipo de personaje
 
* resistencia: double
Multiplicador particular del tipo de personaje

* vida: double
Multiplicador particular del tipo de personaje
 
* datos: string
Path a los datos de los items
 
* generar_graficos: boolean
Mostrar o no los gráficos
 
 * repetir_poblacion_inicial: boolean
 Utilizar o no la misma poblacion
 
 * semilla_poblacion_inicial: int
 Semilla a utilizar para la poblacion
 
* poblacion: int
Cantidad N de individuos en la población
 
* metodo_cruce: string
Método de cruce ("un punto", "dos puntos", "uniforme" o "anular")
 
* probabilidad_cruce_uniforme: double
Probabilidad de que se realice un cruce uniforme [0, 1]
 
* metodo_corte: string
Método de corte ("maxima cantidad", "estructura", "contenido" o "optimo").
 
* optimo: double
Cota de corte para el método de corte "optimo"
 
* generaciones_a_verificar: int
Cantidad de generaciones previas a checkear en el método de corte por "contenido"
 
* porcentaje: double
Porcentaje utilizado en el método de corte por "estructura" [0, 1]
 
* generaciones: int
Cantidad de generaciones utilizadas para cortar por el método "maxima cantidad"
 
* metodo_seleccion_1: string
Método de selección 1 ("elite", "ruleta", "universal", "torneos deterministica" o "torneos probabilistica")
 
* seleccion1_usa_boltzman: boolean
Usar o no Boltzmann para la selección 1
 
* metodo_seleccion_2: string
Método de selección 2 ("elite", "ruleta", "universal", "torneos deterministica" o "torneos probabilistica")
 
* seleccion2_usa_boltzman: boolean
Usar o no Boltzmann para la selección 2
 
* metodo_seleccion_3: string
Método de selección 3 ("elite", "ruleta", "universal", "torneos deterministica" o "torneos probabilistica")
 
* seleccion3_usa_boltzman: boolean
Usar o no Boltzmann para la selección 3
 
* metodo_seleccion_4: string
Método de selección 4 ("elite", "ruleta", "universal", "torneos deterministica" o "torneos probabilistica")
 
* seleccion4_usa_boltzman: boolean
Usar o no Boltzmann para la selección 4
 
* metodo_reemplazo: string
Método de reemplazo ("reemplazo 1", "reemplazo 2" o "reemplazo 3")
 
* cantidad_de_reemplazo: int
Cantidad K a reemplazar en los métodos "reemplazo 2" y "reemplazo 3", 1 < K <= N
 
* porcentaje_de_personajes_torneos: double
Porcentaje que se tomará de la población para realizar los torneos (0, 1)
 
* A: double
Modificador A para los algoritmos de selección 1 y 2 [0, 1]
 
* B: double
Modificador B para los algoritmos de selección 3 y 4 [0, 1]
 
* metodo_mutacion: string
Método de mutación ("gen" o "multigen")
 
* probabilidad_de_mutacion: double
Probabilidad de mutación [0, 1]
 
* mutacion_uniforme: boolean
Usar mutación uniforme o no uniforme
 
* base_exponencial_mutacion_no_uniforme: double
Base j utilizada para la función de mutación no uniforme f(x) = j^x