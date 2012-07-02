package models

import org.scalaquery.ql.extended.{ExtendedTable => Table}


case class Counter(keyword: String, count: Long)

object Counter extends Table[(String, Long)]("counters") {

  def keyword = column[String]("keyword", O PrimaryKey, O NotNull)
  def count   = column[Long]("count", O NotNull, O Default 0)

  def * = keyword ~ count
}
