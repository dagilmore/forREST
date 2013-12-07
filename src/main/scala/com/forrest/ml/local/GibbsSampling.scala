package com.forrest.ds.algorithms

import cern.jet.random.engine.DRand
import cern.jet.random.Normal
import cern.jet.random.Gamma

import Math.sqrt
import java.util.Date
import breeze.plot._
import breeze.linalg._

/**
 * Gibbs sampling referencing from:
 * http://www.cs.umd.edu/~hardisty/papers/gsfu.pdf
 *
 * @author atrask
 *         12/7/13
 */
object GibbsSampling {



  def main(args: Array[String]) {

    val f = Figure()
    val p = f.subplot(2)


    val g = breeze.stats.distributions.Gaussian(0,1)
    p += hist(g.sample(100000),100)

    val N=1000
    val thin=1000
    val rngEngine=new DRand(new Date)
    val rngN=new Normal(0.0,1.0,rngEngine)
    val rngG=new Gamma(1.0,1.0,rngEngine)
    var x=0.0
    var y=0.0
    println("Iter x y")
    for (i <- 0 until N) {
      for (j <- 0 until thin) {
        x=rngG.nextDouble(3.0,y*y+4)
        y=rngN.nextDouble(1.0/(x+1),1.0/sqrt(2*x+2))
      }
      println(i+" "+x+" "+y)
    }
  }


}
