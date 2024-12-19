package Utils

object Utils {

  def intersection(array1: Array[Int], array2: Array[Int]): Int = {
    var c = 0
    var i = 0
    var j = 0

    val size1 = array1.length
    val size2 = array2.length

    var v1: Int = 0
    var v2: Int = 0

    while (i < size1 && j < size2) {
      v1 = array1(i)
      v2 = array2(j)

      if (v1 == v2) {
        c += 1
        i += 1
        j += 1
      } else if (v1 < v2)
        i += 1
      else
        j += 1
    }

    c
  }

  def randomPermutation(size: Int): Array[Int] = {
    val index = Array.tabulate(size)(i => i)

    var temp: Int = 0
    var j: Int = 0

    for (i <- 0 until size) {
      j = scala.util.Random.nextInt(0 until size).toInt

      temp = index(i)
      index(i) = index(j)
      index(j) = temp
    }

    index
  }

  def log2(num: Double): Double = {
    num match {
      case x if Math.abs(x) <= 1e-6 => 0
      case _ => x * (Math.log(x) / Math.log(2))
    }
  }

  def swap(array: Array[Int], i: Int, j: Int): Unit = {
    val aux = array(j)
    array(j) = array(i)
    array(i) = aux
  }
}
