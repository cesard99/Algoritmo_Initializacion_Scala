package Repository
import Utils.* 


class MyDataset {
  protected var instanceSet: InstanceSet = _
  protected var attributes: List[MyAttribute] = _
  protected var instances: List[MyInstance] = _
  protected var nClasses: Int = _
  protected var nInputs: Int = _
  protected var nAttr: Int = _
  protected var classSupp: Array[Int] = _

  def MyDataset(dataset: MyDataset) = {
    this(dataset,dataset.getNumInstances())
    dataset.copyInstance(this)
  }

  def this(dataset: MyDataset, capacity: Int)= this{
    InstanceSet=dataset.instanceSet
    nClasses= dataset.nClasses
    nInputs=dataset.nInputs
    nAttr=dataset.nAttr
    attributes=dataset.attributes
    classSupp= List[nClasses]
    instances = new collection.mutable.ArrayList[Int](capacity)

  }
   def this(datasetFile : String, isTrain:capacity)=this{
    readSet(datasetFile,isTrain )
    readAttributes()
    readInstances()
   }


  

  private def readSet(file: String, isTrain: Boolean): Unit = {
    if (isTrain) Attributes.clearAll()

    try {
      instanceSet = new InstanceSet()
      instanceSet.readSet(file, isTrain)
    } catch {
      case e: Exception => System.exit(e.hashCode())
    }
  }

  private def readAttributes(): Unit = {
    nInputs = Attributes.getInputNumAttributes()
    nAttr = nInputs + Attributes.getOutputNumAttributes()

    attributes = (0 until nAttr).map(i => {
      val attr = Attributes.getInputAttribute(i)
      val newAttr = if (attr.getType() == Attribute.NOMINAL) {
        new MyAttribute(attr.getName(), new ArrayList(attr.getNominalValuesList()), i)
          .setRange(0, newAttr.numValues() - 1.0f)
      } else {
        new MyAttribute(attr.getName(), attr.getType(), i)
          .setRange(attr.getMinAttribute(), attr.getMaxAttribute())
      }
      newAttr
    })

    attributes(nInputs) = new MyAttribute(
      Attributes.getOutputAttribute(0).getName(),
      new ArrayList(Attributes.getOutputAttribute(0).getNominalValuesList()),
      nInputs
    ).setRange(0, newAttr.numValues() - 1.0f)

    nClasses = attributes(nInputs).numValues()
  }

  private def readInstances(): Unit = {
    val numInstances = instanceSet.getNumInstances()

    instances = (0 until numInstances).map(j => {
      val values = Array.fill[Double](getNumAttributes())(0.0)
      var classValue = 0
      
      (0 until nInputs).foreach(i => 
        values(i) = instanceSet.getInputNumericValue(j, i)
      )

      classValue = (instanceSet.getOutputNumericValue(j, 0)).toInt

      new MyInstance(values, classValue)
    })

    classSupp = Array.fill(nClasses)(0)
    instances.foreach(inst => 
      classSupp(inst.classValue()) += 1
    )
  }

  def copyInstance(dest: MyDataset): Unit = {
    dest.instances = dest.instances.map(new MyInstance(_))
  }

  def addInstance(inst: MyInstance): Unit = {
    instances = inst :: instances
    classSupp(inst.classValue()) += 1
  }

  def getAttribute(index: Int): MyAttribute = attributes(index)
  
  def getAttributes(): List[MyAttribute] = attributes
  
  def getClassIndex(): Int = nInputs
  
  def getClassesSupp(pos: Int): Int = classSupp(pos)
  
  def getClassesSupp(): Array[Int] = classSupp
  
  def getInstanceSet(): InstanceSet = instanceSet
  
  def getInstance(pos: Int): MyInstance = instances(pos)
  
  def getInstances(): List[MyInstance] = instances
  
  def isMissing(i: Int, v: Int): Boolean = getInstance(i).isMissing(v)
  
  def getNumClasses(): Int = nClasses
  
  def getNumInstances(): Int = instances.length
  
  def getNumAttributes(): Int = nAttr
  
  def getNumInputs(): Int = nInputs
  
  def getClassAttribute(): MyAttribute = attributes(nInputs)
  
  def trimToSize(): Unit = {
    instances = instances.take(instances.length) // Scala lists are immutable, so we create a new list
  }
  
  def sort(c: Comparator[MyInstance]): Unit = {
    instances.sortBy(inst => inst)
  }
  
  def sort(attr: Int, instanceIndexes: Array[Int]): Unit = {
    quickSort(attr, instanceIndexes, 0, instanceIndexes.length - 1)
  }

  private def quickSort(attr: Int, indexes: Array[Int], left: Int, right: Int): Unit = {
    val pivot = instances(indexes(left)).value(attr)
    var i = left
    var j = right

    while (i < j) {
      while (!DoubleCompare.greater(instances(indexes(i)).value(attr), pivot) && i < j)
        i += 1
      
      while (DoubleCompare.greater(instances(indexes(j)).value(attr), pivot))
        j -= 1

      if (i < j)
        Utils.swap(instanceIndexes, i, j)

      if (i == j) {
        i += 1
        j -= 1
      }
    }

    Utils.swap(instanceIndexes, left, j)

    if (left < j - 1)
      quickSort(attr, instanceIndexes, left, j - 1)

    if (j + 1 < right)
      quickSort(attr, instanceIndexes, j + 1, right)
  }

  def getClassSupp(instanceIndexes: Array[Int]): Array[Int] = {
    val classSupport = new Array(nClasses)(0)
    
    instanceIndexes.foreach(i => 
      classSupport(instances(i).classValue()) += 1
    )
    
    classSupport
  }
}