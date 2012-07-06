package controllers

import java.sql.SQLException
//import anorm._
import play.api._
import play.api.db._
import play.api.mvc._
import play.api.Play.current
import play.api.templates.Html

import org.scalaquery.session.Database.threadLocalSession
import org.scalaquery.ql.extended.PostgresDriver.Implicit._

//import models.ShortURL
import models.Database.database

object ShortURL extends Controller {

  def index = Action {
    Ok(views.html.short_url("Short URL", Application.weiboid))
  }

  def short_url_generate = Action { request =>
    request.body.asJson match {
      case Some(json) => {
        val md = java.security.MessageDigest.getInstance("MD5")
        val full_url = (json\"full_url").as[String]
        md.update(full_url.getBytes)
        val mdvalue = org.apache.commons.codec.binary.Hex.encodeHex(md.digest())
        val hashval = new String(mdvalue)

        try {
          database withSession {
            models.ShortURL insert (hashval, full_url)
          }
        } catch {
          case e: SQLException => None
        }
        Ok(hashval)
      }
      case None => {
        Ok("Invalid data")
      }
    }
  }

  def short_url(hashval: String) = Action {
    try {
      val full_url = database withSession {
        (for(t <- models.ShortURL if t.hashval is hashval) yield t.full_url).take(1).first
      }
      Redirect(full_url)
    } catch {
      case e: NoSuchElementException => NotFound
    }
  }
  
}
