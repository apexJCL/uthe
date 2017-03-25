# User

**URL**: `/user`

| VERB   | URL          | RETURNS  | Description                            |
| ------ | ------------ | -------- | -------------------------------------- |
| `POST` | `login`      | **bool** | Checks if a user can login             |
| `GET`  | `statistics` | **json** | Returns data for statistics app screen |
| `POST` | `signup`     | **json** | Registers a new user                   |

### Login

```json
{
  "username": "johndoe",
  "password": "password"
}

// or

{
  "username": "johndoe@mail.com",
  "password": "password"
}
```



### Statistics	

```json
{
  "username":"apexJCL",
  "first_name":"José Carlos",
  "last_name":"López Gaona",
  "allowed_liters":1994,
  "consumed_liters":0,
  "remaining_liters":1994
}
```



### Register

```json
// Request body
{
  "username": "john",
  "password": "doe",
  "email": "john@doe.com",
  "userdata": { // Required
    "first_name": "John",
    "last_name": "Doe",
  }
}

// Response body

{
  "user_id": 9, // If there are errors, this won't be set
  "has_errors": false,
  "errors": ""
}
```

