package Forest
import Utils.Utils

object Entropy {

  def entropy(classSupport: Array[Int], total: Int): Double = {
    var entropy: Double = 0.0

    for (c <- classSupport) {
      val prob: Double = c / total

      if (prob != 0)
        entropy -= prob * Utils.log2(prob)
    }

    entropy
  }
}