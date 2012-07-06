package controllers

//import anorm._
import play.api._
import play.api.db._
import play.api.mvc._
import play.api.Play.current
import play.api.templates.Html

import org.scalaquery.session.Database.threadLocalSession
import org.scalaquery.ql.extended.PostgresDriver.Implicit._

import models.Database.database

object MessageDigest extends Controller {

  def index = Action {
    val algorithms = Array(
      "SHA-1",
      "SHA-256",
      "SHA-384",
      "SHA-512",
      "MD2",
      "MD5"
    )
    Ok(views.html.message_digest("Message Digest", algorithms, Application.weiboid))
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
