# TangelaServices

To run application: mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active={$PROFILE}"

Profiles: Local, prod.

Endpoints
---------

## Startup

| Attributes     | Explanation                                                                                                 |
| -------------- | ----------------------------------------------------------------------------------------------------------- |
| angelId        | AngelList identification                                                                |
| name           | Startup name                                                         |
| angelUrl       | AngelList URL link to the related startup                                              |
| quality        | Quality related to the startup |
| createdAt      | Created date |
| updatedAt      | Updated date |
| logoUrl        | Logo URL link to the related startup |
| thumbUrl       | Thumblr URL link to the related startup |
| productDescription    | Product description to the startup |
| highConcept    | High concept to the startup |
| followersCount | Followers to the startup |
| companyUrl     | Company URL link to the related startup |
| twitterUrl     | Twitter URL link to the related startup |
| blogUrl        | Blog URL link to the related startup |
| videoUrl       | Video URL link to the related startup |


### GET /startups/

Receive a list of Startups.

| Parameter      | Explanation                                                                                                 |
| -------------- | ----------------------------------------------------------------------------------------------------------- |
| markets        | List of Markets (comma-separated)                                                                |
| locations      | List of Locations (comma-separated)                                                         |
| quality        | Quality |
| createdAt      | Startup with value bigger or equals than the specified value                                              |


` curl -X GET /startups/?markets=Testing,Technology&locations=Vienna,Manitoba&quantity=1&createdAt=2012-01-01 `

```json
{  
   "startups":[  
      {  
         "angelId":24507,
         "name":"Codeship",
         "angelUrl":"https://angel.co/codeship",
         "quality":1,
         "createdAt":"2013-02-12 00:00:00",
         "updatedAt":null,
         "logoUrl":null,
         "thumbUrl":null,
         "productDescription":null,
         "highConcept":null,
         "followersCount":null,
         "companyUrl":null,
         "twitterUrl":null,
         "blogUrl":null,
         "videoUrl":null,
         "rid":"#17:0",
         "version": 1,
         "class":"Startup"
      },
      {  
         "angelId":234071,
         "name":"Opvizor",
         "angelUrl":"https://angel.co/opvizor",
         "quality":1,
         "createdAt":"2012-02-05 00:00:00",
         "updatedAt":null,
         "logoUrl":null,
         "thumbUrl":null,
         "productDescription":null,
         "highConcept":null,
         "followersCount":null,
         "companyUrl":null,
         "twitterUrl":null,
         "blogUrl":null,
         "videoUrl":null,
         "rid":"#17:1",
         "version": 1,
         "class":"Startup"
      },
      {  
         "angelId":57273,
         "name":"Wikitude",
         "angelUrl":"https://angel.co/wikitude",
         "quality":1,
         "createdAt":"2012-08-15 00:00:00",
         "updatedAt":null,
         "logoUrl":null,
         "thumbUrl":null,
         "productDescription":null,
         "highConcept":null,
         "followersCount":null,
         "companyUrl":null,
         "twitterUrl":null,
         "blogUrl":null,
         "videoUrl":null,
         "rid":"#17:2",
         "version": 1,
         "class":"Startup"
      }
   ]
}
```

### GET /startups/{#angelId}

Return a Startup related to the angelId.

` curl -X GET /startups/24507 `

```json
{  
   "angelId":24507,
   "name":"Codeship",
   "angelUrl":"https://angel.co/codeship",
   "quality":1,
   "createdAt":"2013-02-12 00:00:00",
   "updatedAt":null,
   "logoUrl":null,
   "thumbUrl":null,
   "productDescription":null,
   "highConcept":null,
   "followersCount":null,
   "companyUrl":null,
   "twitterUrl":null,
   "blogUrl":null,
   "videoUrl":null,
   "rid":"#17:0",
   "version": 1,
   "class":"Startup"
}
```

### PUT /startups/{#angelId}

Update an existing Startup related to the angelId. After the update, the attribute version will be increase in 1 (one).

` curl -X PUT /startups/24507 `
```json
{  
   "angelId":24507,
   "name":"Codeship",
   "angelUrl":"https://angel.co/codeship",
   "quality":1,
   "createdAt":"2013-02-12 00:00:00",
   "updatedAt":null,
   "logoUrl":null,
   "thumbUrl":null,
   "productDescription":null,
   "highConcept":null,
   "followersCount":null,
   "companyUrl":null,
   "twitterUrl":null,
   "blogUrl":null,
   "videoUrl":null,
}
```

Response

```json
{  
   "angelId":24507,
   "name":"Codeship",
   "angelUrl":"https://angel.co/codeship",
   "quality":1,
   "createdAt":"2013-02-12 00:00:00",
   "updatedAt":null,
   "logoUrl":null,
   "thumbUrl":null,
   "productDescription":null,
   "highConcept":null,
   "followersCount":null,
   "companyUrl":null,
   "twitterUrl":null,
   "blogUrl":null,
   "videoUrl":null,
   "rid":"#17:0",
   "version": 2,
   "class":"Startup"
}
```

### POST /startups/

Insert a new Startup to database.

` curl -X POST /startups/ `
```json
{  
   "angelId":24507,
   "name":"Codeship",
   "angelUrl":"https://angel.co/codeship",
   "quality":1,
   "createdAt":"2013-02-12 00:00:00",
   "updatedAt":null,
   "logoUrl":null,
   "thumbUrl":null,
   "productDescription":null,
   "highConcept":null,
   "followersCount":null,
   "companyUrl":null,
   "twitterUrl":null,
   "blogUrl":null,
   "videoUrl":null,
}
```

Response

```json
{  
   "angelId":24507,
   "name":"Codeship",
   "angelUrl":"https://angel.co/codeship",
   "quality":1,
   "createdAt":"2013-02-12 00:00:00",
   "updatedAt":null,
   "logoUrl":null,
   "thumbUrl":null,
   "productDescription":null,
   "highConcept":null,
   "followersCount":null,
   "companyUrl":null,
   "twitterUrl":null,
   "blogUrl":null,
   "videoUrl":null,
   "rid":"#17:0",
   "version": 1,
   "class":"Startup"
}
```

### DELETE /startups/{#angelId}

Delete Startup related to angelId.

` curl -X DELETE /startups/24507 `

Empty response.

## User

| Attributes     | Explanation                                                                                                 |
| -------------- | ----------------------------------------------------------------------------------------------------------- |
| angelId        | AngelList identification                                                                |
| name           | User name                                                         |
| angelUrl       | AngelList URL link to the related user                                              |
| followersCount | Followers to the user |
| biography      | User biography |
| imageUrl       | Image URL link to the related startup |
| blogUrl        | Blog URL link to the related startup |
| onlineBioUrl   | Biography URL link to the related startup |
| twitterUrl     | Twitter URL link to the related startup |
| facebookUrl    | Facebook URL link to the related startup |
| linkedinUrl    | Linkedin URL link to the related startup |
| investor       | Indicates if the user is investor or not |

### GET /users/

Receive a list of Users related to the filter startups by parameters.

| Parameter      | Explanation                                                                                                 |
| -------------- | ----------------------------------------------------------------------------------------------------------- |
| markets        | List of Markets (comma-separated)                                                                |
| locations      | List of Locations (comma-separated)                                                         |
| quality        | Quality |
| createdAt      | Startup with value bigger or equals than the specified value                                              |

` curl -X GET /users/?markets=Testing,Translation&locations=Vienna,Heredia&quantity=1 `

```json
{  
   "users":[  
      {  
         "angelId":6000,
         "followersCount":null,
         "name":"Eduardo Fuentes",
         "angelUrl":"https://angel.co/eduardo-fuentes",
         "biography":null,
         "imageUrl":null,
         "blogUrl":null,
         "onlineBioUrl":null,
         "twitterUrl":null,
         "facebookUrl":null,
         "linkedinUrl":null,
         "investor":null,
         "rid":"#18:0",
         "version": 1,
         "class":"User"
      }
   ]
}
```

### GET /users/{#angelId}

Return an User related to the angelId.

` curl -X GET /users/6000 `

```json
{  
   "users":[  
      {  
         "angelId":6000,
         "followersCount":null,
         "name":"Eduardo Fuentes",
         "angelUrl":"https://angel.co/eduardo-fuentes",
         "biography":null,
         "imageUrl":null,
         "blogUrl":null,
         "onlineBioUrl":null,
         "twitterUrl":null,
         "facebookUrl":null,
         "linkedinUrl":null,
         "investor":null,
         "rid":"#18:0",
         "version": 1,
         "class":"User"
      }
   ]
}
```

### PUT /users/{#angelId}

Update an existing User related to the angelId. After the update, the attribute version will be increase in 1 (one).

` curl -X PUT /users/6000 `
```json
{  
   "angelId":6000,
    "followersCount":null,
    "name":"Eduardo Fuentes",
    "angelUrl":"https://angel.co/eduardo-fuentes",
    "biography":null,
    "imageUrl":null,
    "blogUrl":null,
    "onlineBioUrl":null,
    "twitterUrl":null,
    "facebookUrl":null,
    "linkedinUrl":null,
    "investor":null
}
```

Response

```json
{  
    "angelId":6000,
    "followersCount":null,
    "name":"Eduardo Fuentes",
    "angelUrl":"https://angel.co/eduardo-fuentes",
    "biography":null,
    "imageUrl":null,
    "blogUrl":null,
    "onlineBioUrl":null,
    "twitterUrl":null,
    "facebookUrl":null,
    "linkedinUrl":null,
    "investor":null,
    "rid":"#18:0",
    "version": 2,
    "class":"User"
}
```

### POST /users/

Insert a new User to database.

` curl -X POST /users/ `
```json
{  
   "angelId":6000,
    "followersCount":null,
    "name":"Eduardo Fuentes",
    "angelUrl":"https://angel.co/eduardo-fuentes",
    "biography":null,
    "imageUrl":null,
    "blogUrl":null,
    "onlineBioUrl":null,
    "twitterUrl":null,
    "facebookUrl":null,
    "linkedinUrl":null,
    "investor":null
}
```

Response

```json
{  
    "angelId":6000,
    "followersCount":null,
    "name":"Eduardo Fuentes",
    "angelUrl":"https://angel.co/eduardo-fuentes",
    "biography":null,
    "imageUrl":null,
    "blogUrl":null,
    "onlineBioUrl":null,
    "twitterUrl":null,
    "facebookUrl":null,
    "linkedinUrl":null,
    "investor":null,
    "rid":"#18:0",
    "version": 1,
    "class":"User"
}
```

### DELETE /users/{#angelId}

Delete User related to angelId.

` curl -X DELETE /users/6000 `

Empty response.

## Location

| Attributes     | Explanation                                                                                                 |
| -------------- | ----------------------------------------------------------------------------------------------------------- |
| angelId        | AngelList identification                                                                |
| name           | Market name                                                         |
| displayName    | Market displayed name |
| angelUrl       | AngelList URL link to the related market                                              |
| @class         | Indicates the Location type: Earth, Continent, Country and City |

### GET /locations/{#angelId}

Return a Location related to the angelId.

` curl -X GET /locations/1683 `

```json
{  
   "angelId":1683,
   "name":"South America",
   "displayName":"South America",
   "angelUrl":"https://angel.co/south-america",
   "rid":"#14:3",
   "version": 1,
   "class":"Location"
}
```

### PUT /locations/{#angelId}

Update an existing Location related to the angelId. After the update, the attribute version will be increase in 1 (one).

` curl -X PUT /locations/1683 `
```json
{  
   "angelId":1683,
   "name":"South America",
   "displayName":"South America",
   "angelUrl":"https://angel.co/south-america",
   "@class":"Continent"
}
```

Response

```json
{  
   "angelId":1683,
   "name":"South America",
   "displayName":"South America",
   "angelUrl":"https://angel.co/south-america",
   "rid":"#14:3",
   "version": 2,
   "class":"Location"
}
```

### POST /locations/

Insert a new Location to database.

` curl -X POST /locations/ `
```json
{  
   "angelId":1683,
   "name":"South America",
   "displayName":"South America",
   "angelUrl":"https://angel.co/south-america",
   "@class":"Continent"
}
```

Response

```json
{  
   "angelId":1683,
   "name":"South America",
   "displayName":"South America",
   "angelUrl":"https://angel.co/south-america",
   "rid":"#14:3",
   "version": 2,
   "class":"Location"
}
```

### DELETE /locations/{#angelId}

Delete Location related to angelId.

` curl -X DELETE /locations/1683 `

Empty response.

## Market

| Attributes     | Explanation                                                                                                 |
| -------------- | ----------------------------------------------------------------------------------------------------------- |
| angelId        | AngelList identification                                                                |
| name           | Market name                                                         |
| displayName    | Market displayed name |
| angelUrl       | AngelList URL link to the related market                                              |

### GET /markets/{#angelId}

Return a Market related to the angelId.

` curl -X GET /markets/4001 `

```json
{  
   "angelId":4001,
   "name":"Virtualization",
   "displayName":"Virtualization",
   "angelUrl":"https://angel.co/virtualization",
   "rid":"#12:1",
   "version": 1,
   "class":"Market"
}
```

### PUT /markets/{#angelId}

Update an existing Market related to the angelId. After the update, the attribute version will be increase in 1 (one).

` curl -X PUT /markets/4001 `
```json
{  
   "angelId":4001,
   "name":"Virtualization 2",
   "displayName":"Virtualization 2",
   "angelUrl":"https://angel.co/virtualization"
}
```

Response

```json
{  
   "angelId":4001,
   "name":"Virtualization 2",
   "displayName":"Virtualization 2",
   "angelUrl":"https://angel.co/virtualization",
   "rid":"#12:1",
   "version": 2,
   "class":"Market"
}
```

### POST /markets/

Insert a new Market to database.

` curl -X POST /markets/ `
```json
{  
   "angelId":4001,
   "name":"Virtualization 2",
   "displayName":"Virtualization 2",
   "angelUrl":"https://angel.co/virtualization",
}
```

Response

```json
{  
   "angelId":4001,
   "name":"Virtualization 2",
   "displayName":"Virtualization 2",
   "angelUrl":"https://angel.co/virtualization",
   "rid":"#12:1",
   "version": 1,
   "class":"Market"
}
```

### DELETE /markets/{#angelId}

Delete Market related to angelId.

` curl -X DELETE /markets/4001 `

Empty response.

## RoleIn and RoleInStartup

| Attributes     | Explanation                                                                                                 |
| -------------- | ----------------------------------------------------------------------------------------------------------- |
| angelId        | AngelList identification                                                                |
| role           | Role in the related startup                                                         |
| createdAt      | Creation date |
| endedAt        | Ended date                                              |
| confirmed      | If the relation confirmed                                            |

### GET /rolesIn/{#angelId}
### GET /rolesInStartup/{#angelId}

Return a RoleIn or RolesInStartup related to the angelId.

` curl -X GET /rolesIn/7000 `

```json
{  
   "angelId":7000,
   "role":"F",
   "createdAt":"2010-04-02 00:00:00",
   "endedAt":null,
   "confirmed":null,
   "in":{  
      "angelId":579844,
      "name":"Ace Global Hard Assets",
      "angelUrl":"https://angel.co/ace-global-hard-assets",
      "quality":1,
      "createdAt":"2010-04-02 00:00:00",
      "updatedAt":null,
      "logoUrl":null,
      "thumbUrl":null,
      "productDescription":null,
      "highConcept":null,
      "followersCount":null,
      "companyUrl":null,
      "twitterUrl":null,
      "blogUrl":null,
      "videoUrl":null,
      "rid":"#17:9",
      "version": 1
   },
   "rid":"#21:0",
   "version": 1,
   "class":"RoleIn"
}
```

### PUT /rolesIn/{#angelId}
### PUT /rolesInStartup/{#angelId}

Update an existing RoleIn or RoleInStartup related to the angelId. After the update, the attribute version will be increase in 1 (one).
At the moment is not possible update the realated Startup

` curl -X PUT /rolesIn/7000 `
```json
{  
   "angelId":7000,
   "role":"F",
   "createdAt":"2010-04-02 00:00:00",
   "endedAt":null,
   "confirmed":null
}
```

Response

```json
{  
   "angelId":7000,
   "role":"F",
   "createdAt":"2010-04-02 00:00:00",
   "endedAt":null,
   "confirmed":null,
   "in":{  
      "angelId":579844,
      "name":"Ace Global Hard Assets",
      "angelUrl":"https://angel.co/ace-global-hard-assets",
      "quality":1,
      "createdAt":"2010-04-02 00:00:00",
      "updatedAt":null,
      "logoUrl":null,
      "thumbUrl":null,
      "productDescription":null,
      "highConcept":null,
      "followersCount":null,
      "companyUrl":null,
      "twitterUrl":null,
      "blogUrl":null,
      "videoUrl":null,
      "rid":"#17:9",
      "version": 1
   },
   "rid":"#21:0",
   "version": 2,
   "class":"RoleIn"
}
```

### POST /rolesIn/
### POST /rolesInStartup/

Insert a new RoleIn or RoleInStartup to database.
At the momento is not possible set the realated startup.

` curl -X POST /rolesIn/ `
```json
{  
   "angelId":7000,
   "role":"F",
   "createdAt":"2010-04-02 00:00:00",
   "endedAt":null,
   "confirmed":null
}
```

Response

```json
{  
   "angelId":7000,
   "role":"F",
   "createdAt":"2010-04-02 00:00:00",
   "endedAt":null,
   "confirmed":null,
   "rid":"#21:0",
   "version": 1,
   "class":"RoleIn"
}
```

### DELETE /rolesIn/{#angelId}
### DELETE /rolesInStartup/{#angelId}

Delete Location related to angelId.

` curl -X DELETE /rolesIn/7000 `

Empty response.

## Round

| Attributes     | Explanation                                                                                                 |
| -------------- | ----------------------------------------------------------------------------------------------------------- |
| angelId        | AngelList identification                                                                |
| roundClosedAt  | Closed round date                                                         |
| name           | Round name |
| roundType      | Round type                                              |
| raised         | Round raised amount                                           |
| roundId        | Round identification                                        |
| roundSourceUrl | Round URL link                                           |
| participantName| Participant name                                          |
| participantType| Participant type                                       |
| participantId  | Participant identification                                         |

### GET /rounds/{#angelId}

Return a Rounds related to the angelId.

` curl -X GET /rounds/7500 `

```json
{  
   "angelId":7500,
   "roundClosedAt":"2010-04-02 00:00:00",
   "name":"Serie D",
   "roundType":"Serie D",
   "raised":"10000",
   "roundId":4536,
   "roundSourceUrl":"http://",
   "participantName":null,
   "participantType":null,
   "participantId":null,
   "rid":"#23:0",
   "version": 1,
   "class":"Round"
}
```

### PUT /rounds/{#angelId}

Update an existing Round related to the angelId. After the update, the attribute version will be increase in 1 (one).

` curl -X PUT /rounds/7500 `
```json
{  
   "angelId":7500,
   "roundClosedAt":"2010-04-02 00:00:00",
   "name":"Serie D",
   "roundType":"Serie D",
   "raised":"10000",
   "roundId":4536,
   "roundSourceUrl":"http://",
   "participantName":null,
   "participantType":null,
   "participantId":null
}
```

Response

```json
{  
   "angelId":7500,
   "roundClosedAt":"2010-04-02 00:00:00",
   "name":"Serie D",
   "roundType":"Serie D",
   "raised":"10000",
   "roundId":4536,
   "roundSourceUrl":"http://",
   "participantName":null,
   "participantType":null,
   "participantId":null,
   "rid":"#23:0",
   "version": 2,
   "class":"Round"
}
```

### POST /rounds/

Insert a new Round to database.

` curl -X POST /rounds/ `
```json
{  
   "angelId":7500,
   "roundClosedAt":"2010-04-02 00:00:00",
   "name":"Serie D",
   "roundType":"Serie D",
   "raised":"10000",
   "roundId":4536,
   "roundSourceUrl":"http://",
   "participantName":null,
   "participantType":null,
   "participantId":null
}
```

Response

```json
{  
   "angelId":7500,
   "roundClosedAt":"2010-04-02 00:00:00",
   "name":"Serie D",
   "roundType":"Serie D",
   "raised":"10000",
   "roundId":4536,
   "roundSourceUrl":"http://",
   "participantName":null,
   "participantType":null,
   "participantId":null,
   "rid":"#23:0",
   "version": 1,
   "class":"Round"
}
```

### DELETE /rounds/{#angelId}

Delete Round related to angelId.

` curl -X DELETE /rounds/7500 `

Empty response.