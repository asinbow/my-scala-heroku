package models

import play.api.db.DB
import play.api.Play.current

object Database {
  lazy val database = org.scalaquery.session.Database.forDataSource(DB.getDataSource())
}

