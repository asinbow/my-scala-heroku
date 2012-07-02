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

  def index = Action {
    val algorithms = Array(
      "SHA-1",
      "SHA-256",
      "SHA-384",
      "SHA-512",
      "MD2",
      "MD5"
    )
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
    Ok(views.html.index("Message Digest", visitCount + 1, algorithms, "http://weibo.com/u/1715376223"))
  }

  def message_digest = Action { request =>
    request.body.asJson match {
      case Some(json) => {
        val md = java.security.MessageDigest.getInstance((json\"algorithm").as[String])
        md.update((json\"text").as[String].getBytes)

        val mdvalue = org.apache.commons.codec.binary.Hex.encodeHex(md.digest())
        Ok(new String(mdvalue))
      }
      case None => {
        Ok("Invalid data")
      }
    }
  }
  
}
