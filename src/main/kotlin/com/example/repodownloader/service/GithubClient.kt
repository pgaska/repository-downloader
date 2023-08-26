package com.example.repodownloader.service

import com.example.repodownloader.model.exception.UserNotFoundException
import com.example.repodownloader.model.github.BranchResponseDto
import com.example.repodownloader.model.github.RepoResponseDto
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class GithubClient {
    val webClient: WebClient = WebClient.create("https://api.github.com/")

    fun getRepositories(userName: String) : MutableList<RepoResponseDto>? =
        webClient.get()
            .uri("users/$userName/repos")
            .retrieve()
            .bodyToFlux(RepoResponseDto::class.java)
            .doOnError { throw UserNotFoundException("$userName not found") }
            .collectList()
            .block()

    fun getBranch(name: String, login: String) : MutableList<BranchResponseDto>? =
        webClient.get()
            .uri("repos/${login}/${name}/branches")
            .retrieve()
            .bodyToFlux(BranchResponseDto::class.java)
            .collectList()
            .block()
}