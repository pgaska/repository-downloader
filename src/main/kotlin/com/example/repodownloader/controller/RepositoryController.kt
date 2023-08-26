package com.example.repodownloader.controller

import com.example.repodownloader.model.ResultDto
import com.example.repodownloader.service.RepositoryService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RepositoryController(
    val repositoryService: RepositoryService
) {
    @GetMapping("/{userName}/repositories", produces=[MediaType.APPLICATION_JSON_VALUE])
    fun getUserRepositories(@PathVariable("userName") userName: String) : ResponseEntity<ResultDto> =
        ResponseEntity<ResultDto>(repositoryService.getUserRepositories(userName), HttpStatus.OK)
}