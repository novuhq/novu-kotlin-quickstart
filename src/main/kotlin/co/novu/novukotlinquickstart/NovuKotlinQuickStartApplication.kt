package co.novu.novukotlinquickstart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NovuKotlinQuickStartApplication

fun main(args: Array<String>) {
	runApplication<NovuKotlinQuickStartApplication>(*args)
}