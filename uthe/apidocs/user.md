# User

**URL**: `/user`

| VERB   | URL          | RETURNS  | Description                            |
| ------ | ------------ | -------- | -------------------------------------- |
| `POST` | `login`      | **bool** | Checks if a user can login             |
| `GET`  | `statistics` | **json** | Returns data for statistics app screen |
|        |              |          |                                        |

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

