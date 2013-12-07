package com.forrest.ds
import breeze.plot._
import breeze.linalg._

/**
 * I just made this class for development purposes of whatever datascience
 * applications we decide to build... :)
 *
 * @author atrask
 *         12/7/13
 */
object Main {

  def main(args: Array[String]) {
    println("Hello, world!")
    example()
  }

  def example() {
    val f = Figure()
    val p = f.subplot(0)
    val x = linspace(0.0,1.0)
    p += plot(x, x :^ 2.0)
    p += plot(x, x :^ 3.0, '.')
    p.xlabel = "x axis"
    p.ylabel = "y axis"
    f.saveas("lines.png") // save current figure as a .png, eps and pdf also supported

    val p2 = f.subplot(2,1,1)
    val g = breeze.stats.distributions.Gaussian(0,1)
    p2 += hist(g.sample(100000),100)
    p2.title = "A normal distribution"
    f.saveas("subplots.png")
  }

}
