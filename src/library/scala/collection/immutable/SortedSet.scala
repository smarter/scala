package scala
package collection
package immutable

import scala.language.higherKinds

/** Base trait for sorted sets */
trait SortedSet[A]
  extends Set[A]
     with collection.SortedSet[A]
     with SortedSetOps[A, SortedSet, SortedSet[A]] {

  override def sortedIterableFactory: SortedIterableFactory[SortedIterableCC] = SortedSet
}

/**
  * @define coll immutable sorted set
  * @define Coll `immutable.SortedSet`
  */
trait SortedSetOps[A, +CC[X] <: SortedSet[X], +C <: SortedSetOps[A, CC, C]]
  extends SetOps[A, Set, C]
    with collection.SortedSetOps[A, Set, CC, C] {

  override def incl[A1 >: A](elem: A1): Set[A1] = ??? //ChampHashSet.empty[A1] ++ toIterable + elem
  def incl(elem: A): C
  override def + (elem: A): C = incl(elem)

  def concat(that: SortedSet[A]): C = fromSpecificIterable(new View.Concat(toIterable, that))
  @`inline` def union(that: SortedSet[A]): C = concat(that)
  @`inline` def | (that: SortedSet[A]): C = concat(that)
}

/**
  * $factoryInfo
  * @define coll immutable sorted set
  * @define Coll `immutable.SortedSet`
  */
@SerialVersionUID(3L)
object SortedSet extends SortedIterableFactory.Delegate[SortedSet](TreeSet)
