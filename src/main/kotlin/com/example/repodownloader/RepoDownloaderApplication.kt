package com.example.repodownloader

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RepoDownloaderApplication

fun main(args: Array<String>) {
	runApplication<RepoDownloaderApplication>(*args)
}
