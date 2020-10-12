package controllers

import javax.inject._
import models.{ActionListItem, NewActionItem, Result}
import org.checkerframework.checker.units.qual.A
import play.api.libs.functional.syntax.toFunctionalBuilderOps
import play.api.libs.json.Json.writes
import play.api.libs.json._
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import scala.collection.mutable

@Singleton
class ActionListController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val actionList = new mutable.ListBuffer[ActionListItem]()

  implicit val todoListJson: OFormat[ActionListItem] = Json.format[ActionListItem]
  implicit val newTodoListJson: OFormat[NewActionItem] = Json.format[NewActionItem]
  implicit val result: OFormat[Result] = Json.format[Result]

  // curl localhost:9000/category/{category_name}
  def getByCategory(vcategoryName: String): Action[AnyContent] = Action {

    val foundItem = actionList.find(_.categoryName.contains(vcategoryName))

    foundItem match {
      case Some(item) => Ok(Json.toJson(item))
      case None => NotFound("Not found")
    }
  }


  // curl -v -d '{"binding": "Ctrl + Shift + K","description": "Push current branch to remote repository","action": "git.push"}' -H 'Content-Type: application/json' -X POST localhost:9000/add
  def addNewItem(): Action[AnyContent] = Action { implicit request =>
    val content = request.body
    val jsonObject = content.asJson

    val actionListItem: Option[NewActionItem] = jsonObject.flatMap(Json.fromJson[NewActionItem](_).asOpt)

    actionListItem match {
      case Some(newItem) =>
        val toBeAdded = ActionListItem(newItem.action.substring(0, newItem.action.indexOf('.')), newItem.action.substring(newItem.action.indexOf('.') + 1, newItem.action.length), newItem.binding)
        if (actionList.contains(toBeAdded)) {
          val res = Result(false)
          Created(Json.toJson(res))
        }
        else {
          actionList += toBeAdded
          val res = Result(true)
          Created(Json.toJson(res))
        }
    }

  }
}


