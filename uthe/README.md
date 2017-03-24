# Uthe: Backend 



### Intakes

| VERB          | URL                | Description                     |
| ------------- | ------------------ | ------------------------------- |
| `GET`         | `/intakes`         | Returns an array of all intakes |
| `POST`        | `/intakes`         | Creates a new intake            |
| `PUT | PATCH` | `/intakes/[0-9]+`  | Updates an intake               |
| `DELETE`      | `/intakes/<[0-9]+` | Deletes an intake               |

**JSON Structure**

```json
{
  "description": "Generic description",
  "volume": 150, // Liters
  "time": 1337 // Seconds
}
```

