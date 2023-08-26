package com.example.repodownloader.model.github

data class RepoResponseDto(
    val name: String,
    val owner: OwnerDto,
)

data class OwnerDto(
    val login: String
)
