package models

case class ActionListItem(categoryName: String, actionName: String, binding: String)

case class NewActionItem(binding: String, description: String, action: String)

case class Result(success: Boolean)

