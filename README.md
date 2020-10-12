# Scala RestAPI

Разработать небольшой REST API сервис на языке программирования Scala.

Сервис получает информацию о сочетаниях клавиш.

Команда запуска сервера
```
sbt run
```

Пример GET запроса (http://localhost:9000/category/git):
```
{
  "binding": "Ctrl + Shift + K",
  "description": "Push current branch to remote repository",
  "action": "git.push"
}
```
Пример запроса через командную строку или PowerShell:

```
curl localhost:9000/category/{category_name}
```
Пример ответа сервера:
```
{
  "categoryName":"git",
  "actionName":"push",
  "binding":"Ctrl + Shift + K"
}
```
Пример POST запроса (http://localhost:9000/add)
```
{
	"binding": "Ctrl + Shift + K",
	"description": "Push current branch to remote repository",
	"action": "git.push"
}
```
Пример ответа на POST запрос при удачном завершении (если не существует такое значение):
```
{
  "success": true
}
```
Пример ответа на POST запрос при неудачном завершении:
```
{
  "success": false
}
```
