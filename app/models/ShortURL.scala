package models

import org.scalaquery.ql.extended.{ExtendedTable => Table}


case class ShortURL(hashval: String, full_url: Long)

object ShortURL extends Table[(String, String)]("short_urls") {

  def hashval  = column[String]("hashval", O PrimaryKey, O NotNull)
  def full_url = column[String]("full_url", O NotNull, O Default "")

  def * = hashval ~ full_url
}
