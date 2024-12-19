package Forest
class Node(interval: Interval) {
  private var leaf: Boolean = false
  private var children: Array[Node] = new Array(2)
  private var nodeInterval: Interval = interval

  def this() = this{
    this.leaf=true
  } 

  def getChildren: Array[Node] = children
  def addChild(index: Int, child: Node): Unit = children(index) = child
  def isLeaf: Boolean = leaf
  def getInterval: Interval = nodeInterval
}

