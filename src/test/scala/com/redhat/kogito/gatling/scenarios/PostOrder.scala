/**
 *
 * This simulation is dependent on below kogito business service.
 * https://github.com/kiegroup/kogito-examples/tree/stable/process-springboot-example
 *
 * This simulation try to emulate below API call.
 * curl -d '{"approver" : "john", "order" : {"orderNumber" : "12345", "shipped" : false}}' -H "Content-Type: application/json" -X POST http://localhost:8080/orders
 *
 *http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/orders-resource/createResource_orders
 */
package com.redhat.kogito.gatling.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object PostOrder {

  val orderString = "{\"approver\" : \"john\", \"order\" : {\"orderNumber\" : \"12345\", \"shipped\" : false}}"

  val postOrderHttp = http("HTTP Post Order")
    .post("/orders")
    .body(StringBody(orderString)).asJson
    .check(status is 201)

  val postOrder = scenario(postOrderHttp.toString)
    .exec(postOrderHttp)
}