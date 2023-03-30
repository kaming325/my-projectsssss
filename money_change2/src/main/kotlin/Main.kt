import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonObject
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


        var todayDate: String = ""
            set(value) {
                val dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                val now = LocalDateTime.now();
                field = dtf.format(now);
            }
        var name: String = ""

        var url = "http://ec2-54-250-194-91.ap-northeast-1.compute.amazonaws.com:8080"

        data class Order(val totalAmt: Int, val paidAmt: Int, val change: Int);



        fun main(args: Array<String>) {
            greeting()
            home_page()

        }




        fun greeting() {
            println("It is a money change program")
            print("Please type your name here: ")
            name = readln()
            todayDate = ""
            println("Hello $name")
        }

        fun home_page() {
            var selection: Int = -1
            println("------------------------------")
            println("Home Page")

            do {
                println("what do you want to do? ")
                println(
                    "New order - 0; Get today payment record - 1; "
                            + "Close - 2"
                )
                print("Selection: ")
                var input = readln()

                try {
                    selection = Integer.parseInt(input)

                } catch (e: NumberFormatException) {
                    println("Wrong Input! Enter again please")
                }

                if (selection == 0) {
                    newOrder()
                } else if (selection == 1) {
                    getRecord()
                } else if (selection == 2) {
                    System.out.print("bye")
                    System.exit(0)

                }

            } while (selection != 0 && selection != 1 && selection != 2)
        }

        fun newOrder() {
            System.out.println("------------------------------");
            System.out.println("New order");
            System.out.print("Total amount: ");
            var totalAmt: Int;
            try {
                totalAmt = Integer.parseInt(readln())
                while (totalAmt < 0) {
                    System.out.println("Invalid amount!");
                    System.out.print("Total amount: ");
                    totalAmt = Integer.parseInt(readln())
                }
                if (totalAmt == 0) {
                    println("Now back to home page")
                    home_page()
                }

                System.out.print("Paid amount: ");
                var paidAmt: Int;
                paidAmt = Integer.parseInt(readln())
                while (paidAmt < totalAmt) {
                    System.out.println("Not Enough!");
                    System.out.print("Paid amount: ");
                    paidAmt = Integer.parseInt(readln())
                }

                val change = paidAmt - totalAmt;
                System.out.println("Payment success!");
                System.out.println("Change: $change");
                System.out.println("Save and next order - 0; Discard and next order - 1; back to home page - 2");

                var selection: Int;
                do {
                    print("Selection: ")
                    selection = Integer.parseInt(readln())
                    if (selection == 0) {
                        val order = Order(totalAmt, paidAmt, change)
                        uploadToApi(order)
                        newOrder()
                    } else if (selection == 1) {
                        newOrder()
                    } else if (selection == 2) {
                        home_page()
                    } else {
                        println("Invalid number")
                    }
                } while (selection != 0 && selection != 1 && selection != 2)

            } catch (e: NumberFormatException) {
                println("Wrong Input! Enter again please")
                newOrder()
            }

            home_page()
        }

        fun uploadToApi(order: Order) {
            val params = mapOf(
                "name" to name,
                "totalAmount" to order.totalAmt.toString(),
                "payment" to order.paidAmt.toString(),
                "changeAmount" to order.change.toString(),
                "date" to todayDate
            )  //to todayDate);
            val urlParams = params.map { (k, v) -> "${(k.utf8())}=${v.utf8()}" }
                .joinToString("&")
            val client = HttpClient.newBuilder().build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$url/moneyChange/order/insert?${urlParams}"))
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            println(response)
        }

        fun String.utf8(): String = URLEncoder.encode(this, "UTF-8")

        fun getRecord() {
            val params = mapOf("name" to name, "date" to todayDate);
            val urlParams = params.map { (k, v) -> "${(k.utf8())}=${v.utf8()}" }
                .joinToString("&")

            val client = HttpClient.newBuilder().build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create("$url/moneyChange/order/getOrder?${urlParams}"))
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            // println(response.body())

            val json_array = Json.decodeFromString<JsonArray>(response.body());
            var allAmount: Int = 0;
            var allPayment: Int = 0;
            var allChange: Int = 0;
            json_array.forEach {
                print("TimeStamp: ${it.jsonObject["_id"]?.jsonObject?.get("timestamp")} \t")
                print("User: ${it.jsonObject["username"]} \t")
                print("Amount: ${it.jsonObject["totalAmount"]} \t")
                print("Payment: ${it.jsonObject["payment"]} \t")
                print("Change: ${it.jsonObject["changeAmount"]} \t")
                print("Date: ${it.jsonObject["date"]} \n")

                allAmount += Integer.parseInt(it.jsonObject["totalAmount"].toString())
                allPayment += Integer.parseInt(it.jsonObject["payment"].toString())
                allChange += Integer.parseInt(it.jsonObject["changeAmount"].toString())
            }
            print("Total Amount: $allAmount \t")
            print("Total Payment: $allPayment \t")
            println("Total Change: $allChange")
            home_page()
        }

