package com.example.repodownloader.model.github

import com.fasterxml.jackson.annotation.JsonProperty

data class RepoResponseDto(
    val name: String,
    val owner: OwnerDto,
    @JsonProperty("branches_url") val branchesUrl: String
)

data class OwnerDto(
    val login: String
)
