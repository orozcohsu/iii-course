import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.evaluation

object DT {
  def main(args: Array[String]) {
val conf = new SparkConf()                                                                                 
.setAppName("DT")                              			
    val sc = new SparkContext(conf)                                 
    val data = MLUtils.loadLibSVMFile(sc, "hdfs:///user/hdfs/DTree.txt")			

    val numClasses = 2 										
    val categoricalFeaturesInfo = Map[Int, Int]()					
    val impurity = "entropy"									
    val maxDepth = 5										
    val maxBins = 3											

    val model = DecisionTree.trainClassifier(data, numClasses, categoricalFeaturesInfo,impurity, maxDepth, maxBins)								
    println("topNode:",model.topNode)									

  }
}
