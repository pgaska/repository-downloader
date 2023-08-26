package com.example.repodownloader.service

import com.example.repodownloader.model.*
import com.example.repodownloader.model.exception.UserNotFoundException
import com.example.repodownloader.model.github.BranchResponseDto
import com.example.repodownloader.model.github.RepoResponseDto
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class RepositoryService {
    val webClient: WebClient = WebClient.create("https://api.github.com/")
    fun getUserRepositories(userName: String) : ResultDto {
        val repositories = getRepositories(userName)
        val results = repositories?.map {
            RepositoryDto(
                it.name,
                it.owner.login,
                getBranch(it.name, it.owner.login)?.map { branch -> BranchDto(branch.name, branch.commit.sha) }
            )
        }

        return ResultDto(results ?: emptyList())
    }

    private fun getRepositories(userName: String) : MutableList<RepoResponseDto>? =
        webClient.get()
            .uri("users/$userName/repos")
            .retrieve()
            .bodyToFlux(RepoResponseDto::class.java)
            .doOnError { throw UserNotFoundException("$userName not found") }
            .collectList()
            .block()

    private fun getBranch(name: String, login: String) : MutableList<BranchResponseDto>? =
        webClient.get()
            .uri("repos/${login}/${name}/branches")
            .retrieve()
            .bodyToFlux(BranchResponseDto::class.java)
            .collectList()
            .block()
}