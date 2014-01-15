package com.example.domain

import spray.json.DefaultJsonProtocol

/**
 * @author tomerb
 * date 1/5/14.
 */
trait Animal

case class Person(name: String, kind: String = "person") extends Animal


