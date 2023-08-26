package com.example.repodownloader.service

import com.example.repodownloader.model.BranchDto
import com.example.repodownloader.model.RepositoryDto
import com.example.repodownloader.model.ResultDto
import org.springframework.stereotype.Service

@Service
class RepositoryService(
    val githubClient: GithubClient
) {
    fun getUserRepositories(userName: String) : ResultDto {
        val repositories = githubClient.getRepositories(userName)
        val results = repositories?.map {
            RepositoryDto(
                it.name,
                it.owner.login,
                githubClient.getBranch(it.name, it.owner.login)
                    ?.map { branch -> BranchDto(branch.name, branch.commit.sha) }
            )
        }

        return ResultDto(results ?: emptyList())
    }

}