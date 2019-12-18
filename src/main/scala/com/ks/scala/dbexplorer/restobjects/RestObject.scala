package com.ks.scala.dbexplorer.restobjects

import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport
import io.circe.generic.AutoDerivation

trait RestObject extends AutoDerivation with ErrorAccumulatingCirceSupport
