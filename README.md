# Repository Downloader
### Summary
The application fetches the information about GitHub user's repositories.<br>
The application uses Kotlin 1.8.2 and Spring Boot 3.1.3.
### Build
`./gradlew build`

### Run
`./gradlew run`

### Getting repository data
Use `/{userName}/repositories` endpoint to get repository data.<br>
Response example:
```
{
    "repositories": [
        {
            "name": "name",
            "login": "login",
            "branches": [
                {
                    "name": "main",
                    "lastCommit": "dd8b410cdb627ac230acde11f9f55e1f6f449000"
                }
            ]
        }
    ]
}
```