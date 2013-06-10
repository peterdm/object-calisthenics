package list

import scala.collection.mutable.ArrayBuffer

/**
 * Common functionality shared among [Type]Lists
 */
class CommonList[T]  {
  def this(traversable: Traversable[T]) = {
    this()
    traversable.copyToBuffer(items)
  }

  protected val items = new ArrayBuffer[T]()

  def add(item: T): CommonList[T] = {
    items += item
    return this
  }

  def addAll(list: CommonList[T]): CommonList[T] = {
    items.++=(list.items)
    return this
  }

  def delete(item: T) { items -= item }

  def contains(item: T): Boolean = {
    items.contains(item)
  }

  def length: Int = {
    items.length
  }

  def filter(p: (T) => Boolean) = items.filter(p)
  def mapString(f: (T) => String): ArrayBuffer[String] = items.map(f)

  override def toString(): String = {
    items.mkString("\n")
  }


}
