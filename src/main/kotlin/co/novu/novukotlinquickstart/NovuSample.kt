package co.novu.novukotlinquickstart

import co.novu.Novu
import co.novu.dto.request.CreateTopicRequest
import co.novu.dto.request.SubscriberRequest
import co.novu.dto.request.TriggerEventRequest
import co.novu.dto.request.UpdateSubscriberRequest
import co.novu.dto.response.ResponseWrapper
import co.novu.dto.response.SubscriberList
import co.novu.dto.response.SubscriberResponse
import co.novu.extensions.addSubscribers
import co.novu.extensions.createSubscriber
import co.novu.extensions.createTopic
import co.novu.extensions.removeSubscriber
import co.novu.extensions.updateSubscriber
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class NovuSample {

    suspend fun createSubscriber(): ResponseWrapper<SubscriberResponse>? {
        val novu = Novu(apiKey = "API_KEY")
        val subscriberRequest = SubscriberRequest(
            email = "email@sample.com",
            firstName = "John",//optional
            lastName = "Doe",//optional
            phone = "123456789",//optional
            avatar = "sample-avatar",//optional
            subscriberId = "12345"//optional
        )
        return CoroutineScope(Dispatchers.IO).async {
            novu.createSubscriber(subscriberRequest)
        }.await()
    }

    suspend fun updateSubscriber(): ResponseWrapper<SubscriberResponse>? {
        val novu = Novu(apiKey = "API_KEY")
        val subscriberRequest = UpdateSubscriberRequest(
            email = "email@sample.com",
            firstName = "John",//optional
            lastName = "Doe",//optional
            phone = "123456789",//optional
            avatar = "sample-avatar"//optional
        )
        val subscriberId = "123"
        return CoroutineScope(Dispatchers.IO).async {
            novu.updateSubscriber(subscriberId, subscriberRequest)
        }.await()
    }

    suspend fun triggerNotification(): Any? {
        val novu = Novu(apiKey = "API_KEY")
        val triggerEventRequest = TriggerEventRequest(
            name = "test",
            to = SubscriberRequest(
                subscriberId = "12345",
                email = "email_@sample.com",
                firstName = "John",
                lastName = "Doe"
            ),
            payload = mapOf("customVariables" to "Hello"),
            transactionId = "transactionId"
        )

        return CoroutineScope(Dispatchers.IO).async {
            novu.trigger(triggerEventRequest)
        }.await()
    }

    suspend fun createTopic(): Any? {
        val novu = Novu(apiKey = "API_KEY")
        val createTopicRequest = CreateTopicRequest(
            key = "key",
            name = "name"
        )
        return CoroutineScope(Dispatchers.IO).async {
            novu.createTopic(createTopicRequest)
        }.await()
    }

    suspend fun addSubscriberToTopic(): Any? {
        val novu = Novu(apiKey = "API_KEY")
        val topicKey = "key"
        val requestBody = SubscriberList(listOf("name"))
        return CoroutineScope(Dispatchers.IO).async {
            novu.addSubscribers(topicKey, requestBody)
        }.await()
    }

    suspend fun removeSubscriberFromTopic(): Any? {
        val novu = Novu(apiKey = "API_KEY")
        val topicKey = "key"
        val requestBody = SubscriberList(listOf("name"))
        return CoroutineScope(Dispatchers.IO).async {
            novu.removeSubscriber(topicKey, requestBody)
        }.await()
    }
}