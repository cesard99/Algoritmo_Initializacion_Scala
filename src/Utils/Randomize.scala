package Utils

object Randomize {

  def setSeed(seed: Long): Unit = {
    org.core.Randomize.setSeed(seed)
  }

  def rand(): Double = {
    synchronized {
      org.core.Randomize.Rand()
    }
  }

  def randBoolean(): Boolean = {
    rand() > 0.5
  }

  def randInt(lb: Int, ub: Int): Int = {
    (lb + (ub - lb) * rand()).toInt
  }

  def randIntOpen(lb: Int, ub: Int): Int = {
    ((lb + 1) + (ub - (lb + 1)) * rand()).toInt
  }

  def randIntClosed(lb: Int, ub: Int): Int = {
    (lb + (ub + 1 - lb) * rand()).toInt
  }

  def randDouble(lb: Double, ub: Double): Double = {
    lb + (ub - lb) * rand()
  }

  def randDoubleOpen(lb: Double, ub: Double): Double = {
    lb + (ub - lb) * rand()
  }

  def randDoubleClosed(lb: Double, ub: Double): Double = {
    lb + (ub - lb) * rand()
  }
}
