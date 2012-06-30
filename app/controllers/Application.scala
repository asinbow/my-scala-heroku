package controllers

import play.api.Play.current
import anorm._
import play.api._
import play.api.db._
import play.api.mvc._
import play.api.templates.Html

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
    var visitCount: Long = 0
    //DB.withConnection { implicit c =>
    //  visitCount = SQL("SELECT count FROM counters WHERE keyword='visit' LIMIT 1")().head[Long]("count")
    //  SQL("UPDATE counters SET count = count + 1 WHERE keyword='visit'").execute()
    //}
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
