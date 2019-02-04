Create a simple REST service which will return details of given Github repository. Details should
include:
? full name of repository
? description of repository
? git clone url
? number of stargazers
? date of creation formatted in requesters local date format
The API of the service should look as follows:

GET /repositories/{owner}/{repository­name}

{

"fullName": "...",

"description": "...",

"cloneUrl": "...",

"stars": 0,

"createdAt": "..."

}

GitHub API reference can be found at: https://developer.github.com/v3/
Service should be coded in Java. We expect to find a set of end­to­end tests that can be run
using build tool of your choice (Gradle or Maven preferred).