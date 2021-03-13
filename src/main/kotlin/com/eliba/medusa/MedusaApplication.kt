package com.eliba.medusa

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MedusaApplication

fun main(args: Array<String>) {
	runApplication<MedusaApplication>(*args)
}
