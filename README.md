## JWT (Json Web Token) authentication (Spring boot 2 + Angular 6)

#### This Project uses JWT to secure the REST endpoints.

* Requirements
   - NodeJS + Maven

* Build
   - Run "mvn clean install"

* Backend
  -  Spring boot 2 : Security + Web + Data Rest + H2 + jjwt

* Frontend
  -  Angular 6 : @auth0/angular-jwt + @swimlane/ngx-datatable


#### The Following are the REST end points available in the example.

```
POST /api/login 
- Generates the JWT token based on the JSON sent
- Its a POST method which expects the JSON: {"username" : "alice","password" : "password"}
```

```
POST /api/register
- Create new user with role "USER"
- Its a POST method which expects the JSON: {"username" : "new user", "password" : "password", "confirmPassword" : "password"}
```

```
GET /api/tasks
- Get list of tasks
- Requires a JWT Token with Header key - "Authorization" and value - "Bearer <JWT_Token>"
- Requires "USER" or "ADMIN" roles
```

```
POST /api/tasks
- Add a task
- Requires a JWT Token with Header key - "Authorization" and value - "Bearer <JWT_Token>"
- Requires "ADMIN" role
```

####  Default users
| User  |  Password    | Roles        | Permissions           |
| ----- | ------------ | ------------ | --------------------- |
| alice |  password    | ADMIN + USER | read and create tasks |
| bob   |  password    | USER         | read tasks            |

#### Screenshots
##### Login page

![Alt text](assets/login.png?raw=true "Login page")

##### Registration page

![Alt text](assets/register.png?raw=true "Registration page")

##### List of tasks
![Alt text](assets/tasks.png?raw=true "List of tasks")

##### Add a task
![Alt text](assets/add%20task.png?raw=true "Add Task")
