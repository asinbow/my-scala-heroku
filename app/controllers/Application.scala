package controllers

//import anorm._
import play.api._
import play.api.db._
import play.api.mvc._
import play.api.Play.current
import play.api.templates.Html

import org.scalaquery.session.Database.threadLocalSession
import org.scalaquery.ql.extended.PostgresDriver.Implicit._

import models.Counter
import models.Database.database

object Application extends Controller {

  def weiboid = "http://weibo.com/u/1715376223"

  def index = Action {
    //var visitCount: Long = 0
    //DB.withConnection { implicit c =>
    //  visitCount = SQL("SELECT count FROM counters WHERE keyword='visit' LIMIT 1")().head[Long]("count")
    //  SQL("UPDATE counters SET count = count + 1 WHERE keyword='visit'").execute()
    //}
    //Ok(views.html.index("Message Digest", visitCount + 1, algorithms, "http://weibo.com/u/1715376223"))

    val visitCount = database withSession {
      val query = (for(t <- Counter if t.keyword is "visit") yield t.count)
      val vc = query.first
      query.update(vc + 1) // FIXME not atomic operation
      vc
    }
    Ok(views.html.index("ASINBOW", visitCount + 1, weiboid))
  }
  
}
