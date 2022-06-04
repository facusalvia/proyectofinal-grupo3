# Grupo 3
Integrantes<br>
<br>
-Contrera Brian Andrés<br>
-Leguizamón Agustín Manuel<br>
-Rodriguez Mariana<br>
-Romero Hernán Eduardo<br>
-Salvia Facundo Javier<br>
<br>
<br>
<br>



# US0001

## Alta de un nuevo hotel (Caso exitoso) 

`POST http://localhost:8080/api/v1/hotels/new`

<details>
<summary>Request Body</summary>
<pre>
{
	"hotelCode":"NUEVO-00012",
	"name":"Nuevo Hotel",
	"place":"La Plata",
	"roomType":"Cuadruple",
	"roomPrice":1000.0,
	"disponibilityDateFrom":"10/02/2022",
	"disponibilityDateTo":"20/05/2022",
	"isBooking":"false"
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Se agrego un nuevo hotel",
    "code": 201
}
</pre>
</details>

## Alta de un nuevo hotel (Caso fallido - ya existe un hotel con mismo codigo)

`POST (http://localhost:8080/api/v1/hotels/new`

<details>
<summary>Request Body</summary>
<pre>
{
	"hotelCode":"NUEVO-00012",
	"name":"Nuevo Hotel",
	"place":"La Plata",
	"roomType":"Cuadruple",
	"roomPrice":1000.0,
	"disponibilityDateFrom":"10/02/2022",
	"disponibilityDateTo":"20/05/2022",
	"isBooking":"false"
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Hotel already exists"
}
</pre>
</details>

## Obtener todos los hoteles

`GET http://localhost:8080/api/v1/hotels`

<details>
<summary>Response</summary>
<pre>
{
    "hotels": [
        {
            "id": 1,
            "hotelCode": "CH-0002",
            "name": "Cataratas Hotel",
            "place": "Puerto Iguazú",
            "roomType": "Single",
            "roomPrice": 6300.0,
            "disponibilityDateFrom": "10/04/2022",
            "disponibilityDateTo": "15/04/2022",
            "isBooking": false
        },
        {
            "id": 2,
            "hotelCode": "CH-0003",
            "name": "Cataratas Hotel",
            "place": "Puerto Iguazú",
            "roomType": "Single",
            "roomPrice": 6300.0,
            "disponibilityDateFrom": "15/04/2022",
            "disponibilityDateTo": "20/04/2022",
            "isBooking": false
        },
        {
            "id": 3,
            "hotelCode": "HB-001",
            "name": "Hotel Bristol",
            "place": "Buenos Aires",
            "roomType": "Single",
            "roomPrice": 5435.0,
            "disponibilityDateFrom": "10/02/2022",
            "disponibilityDateTo": "19/03/2022",
            "isBooking": false
        },
        {
            "id": 4,
            "hotelCode": "BH-002",
            "name": "Hotel Bristol 2",
            "place": "Buenos Aires",
            "roomType": "Doble",
            "roomPrice": 7200.0,
            "disponibilityDateFrom": "08/02/2022",
            "disponibilityDateTo": "17/04/2022",
            "isBooking": false
        },
        {
            "id": 5,
            "hotelCode": "SH-002",
            "name": "Sheraton",
            "place": "Tucuman",
            "roomType": "Doble",
            "roomPrice": 5790.0,
            "disponibilityDateFrom": "17/04/2022",
            "disponibilityDateTo": "23/05/2022",
            "isBooking": false
        },
        {
            "id": 6,
            "hotelCode": "NUEVO-00012",
            "name": "Nuevo Hotel",
            "place": "La Plata",
            "roomType": "Cuadruple",
            "roomPrice": 1000.0,
            "disponibilityDateFrom": "10/02/2022",
            "disponibilityDateTo": "20/05/2022",
            "isBooking": false
        }
    ]
}
</pre>
</details>

## Actualizar un hotel (Caso exitoso)

`PUT http://localhost:8080/api/v1/hotels/edit?hotelCode=NUEVO-00012`

PARAMS
hotelCode: NUEVO-00012

<details>
<summary>Request Body</summary>
<pre>
{
	"hotelCode":"NUEVO-00012",
	"name":"Nuevo Hotel",
	"place":"Bogota",
	"roomType":"Cuadruple",
	"roomPrice":3000.0,
	"disponibilityDateFrom":"10/02/2022",
	"disponibilityDateTo":"20/03/2022",
	"isBooking":"false"
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Se modifico hotel correctamente",
    "code": 200
}
</pre>
</details>

## Actualizar un hotel (Caso fallido - no existe el código de hotel)

`PUT http://localhost:8080/api/v1/hotels/edit?hotelCode=NOEXISTE-0001`

PARAMS
hotelCode: NOEXISTE-0001

<details>
<summary>Request Body</summary>
<pre>
{
	"hotelCode":"NOEXISTE-0001",
	"name":"Nuevo Hotel",
	"place":"Bogota",
	"roomType":"Cuadruple",
	"roomPrice":3000.0,
	"disponibilityDateFrom":"10/02/2022",
	"disponibilityDateTo":"20/03/2022",
	"isBooking":"false"
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Hotel does not exists"
}
</pre>
</details>


## Obtener hoteles de acuerdo a fechas de disponibilidad y destino

`GET http://localhost:8080/api/v1/hotels?dateFrom=10/02/2022&dateTo=19/03/2022&destination=Buenos Aires`

PARAMS
dateFrom: 10/02/2022
dateTo: 19/03/2022
destination: Buenos Aires

<details>
<summary>Response</summary>
<pre>
{
    "hotels": [
        {
            "id": 3,
            "hotelCode": "HB-001",
            "name": "Hotel Bristol",
            "place": "Buenos Aires",
            "roomType": "Single",
            "roomPrice": 5435.0,
            "disponibilityDateFrom": "10/02/2022",
            "disponibilityDateTo": "19/03/2022",
            "isBooking": false
        },
        {
            "id": 4,
            "hotelCode": "BH-002",
            "name": "Hotel Bristol 2",
            "place": "Buenos Aires",
            "roomType": "Doble",
            "roomPrice": 7200.0,
            "disponibilityDateFrom": "08/02/2022",
            "disponibilityDateTo": "17/04/2022",
            "isBooking": false
        }
    ]
}
</pre>
</details>



## Eliminar un hotel (Caso exitoso)

`DELETE http://localhost:8080/api/v1/hotels/delete?hotelCode=NUEVO-00012`

PARAMS
hotelCode: NUEVO-00012


<details>
<summary>Response</summary>
<pre>
{
    "message": "Se eliminó el hotel correctamente",
    "code": 200
}
</pre>
</details>

## Eliminar un hotel (Caso fallido - no existe hotel con el código ingresado)

`DELETE http://localhost:8080/api/v1/hotels/delete?hotelCode=NOEXISTE-0001`

PARAMS
hotelCode: NOEXISTE-0001


<details>
<summary>Response</summary>
<pre>
{
    "message": "Hotel does not exists"
}
</pre>
</details>

## Eliminar un hotel (Caso fallido - el hotel posee reservas asociadas y no puede eliminarse)

`DELETE http://localhost:8080/api/v1/hotels/delete?hotelCode=CH-0002`

PARAMS
hotelCode: CH-0002


<details>
<summary>Response</summary>
<pre>
{
    "message": Hotel can't unsubscribe because it has current reservations"
}
</pre>
</details>


## Alta de una reserva de hotel

`POST http://localhost:8080/api/v1/flight-reservation/new`

<details>
<summary>Request Body</summary>
<pre>
{
    "username":"juan",
    "flightReservationDTO":{
        "goingDate":"2022-02-10",
         "returnDate":"2022-02-15",
         "origin" : "Lugano 1 y 2",
         "destination" : "Pompeya",
        "flightNumber" : "NUEVO-1233",
        "seats":1,
      "seatType" : "Economy",
      "people":[{
          "dni":"380592223",
          "name":"team",
          "lastname":"algo",
          "birthDate":"25/06/1994",
          "mail":"team@algo.com"
      }],
      "paymentMethod":{
          "type":"credit",
          "number":"34343434335",
          "dues":6
      }
    }
       
   
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Reserva de hotel dada de alta correctamente",
    "code": 200
}
</pre>
</details>


## Actualizar una reserva de hotel

`PUT http://localhost:8080/api/v1/hotel-booking/edit?id=1`

PARAMS
id: 1

<details>
<summary>Request Body</summary>
<pre>
{
    "username": "agustin1",
    "booking": {
        "dateFrom": "16/04/2022",
        "dateTo": "17/04/2022",
        "hotelCode": "CH-0002",
        "destination": "Puerto Iguazú",
        "peopleAmount": 1,
        "roomType": "Single",
        "people": [
            {
                "dni": 109238123,
                "name": "marco",
                "mail": "marco@gmail.com",
                "lastname": "Azul",
                "birthDate": "13/02/1987"
            }
        ],
        "paymentMethod":{
            "type": "DEBIT",
            "number": "7567 5465",
            "dues": 0
        }
    }
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "La reserva ha sido modificada correctamente",
    "code": 200
}
</pre>
</details>

## Obtener todas las reservas de hotel

`GET http://localhost:8080/api/v1/hotel-booking/`

<details>
<summary>Response</summary>
<pre>
{
    "hotel_bookings": [
        {
            "username": "agustin1",
            "booking": {
                "dateFrom": "16/04/2022",
                "dateTo": "17/04/2022",
                "destination": "Puerto Iguazú",
                "hotelCode": "CH-0002",
                "peopleAmount": 1,
                "roomType": "Single",
                "people": [
                    {
                        "dni": "109238123",
                        "name": "marco",
                        "lastname": "Azul",
                        "birthDate": "13/02/1987",
                        "mail": "marco@gmail.com"
                    }
                ],
                "paymentMethod": {
                    "type": "DEBIT",
                    "number": "7567 5465",
                    "dues": 0
                }
            }
        }
    ]
}
</pre>
</details>

## Eliminar una reserva de hotel (Caso exitoso)

`DELETE http://localhost:8080/api/v1/hotel-booking/delete?idReservation=2`

PARAMS
idRservation: 2

<details>
<summary>Response</summary>
<pre>
{
    "message": "La reserva ha sido eliminada correctamente",
    "code": 200
}
</pre>
</details>

## Eliminar una reserva de hotel (Caso fallido - no existe la reserva de hotel buscada)

`DELETE http://localhost:8080/api/v1/hotel-booking/delete?idReservation=100`

PARAMS
idRservation: 100

<details>
<summary>Response</summary>
<pre>
{
    "message": "No existe reserva de hotel"
}
</pre>
</details>


---

## Alta nuevo vuelo (Caso exitoso)

`POST http://localhost:8080/api/v1/flights/new`


<details>
<summary>Request Body</summary>
<pre>
{
    "flightNumber" : "NUEVO-1233",
    "origin" : "Lugano 1 y 2",
    "destiny" : "Pompeya",
    "seatType" : "Economy",
    "pricePerPerson" : 2500,
    "dateFrom" : "2022-02-10",
    "dateTo" : "2022-02-15"
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Se agrego un nuevo vuelo",
    "code": 201
}
</pre>
</details>



## Alta nuevo vuelo (Caso fallido - ya existe un vuelo con el número indicado)

`POST http://localhost:8080/api/v1/flights/new`

<details>
<summary>Request Body</summary>
<pre>
{
    "flightNumber" : "NUEVO-1233",
    "origin" : "Lugano 1 y 2",
    "destiny" : "Pompeya",
    "seatType" : "Economy",
    "pricePerPerson" : 2500,
    "dateFrom" : "2022-02-10",
    "dateTo" : "2022-02-15"
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Flight already exist"
}
</pre>
</details>


## Obtener todos los vuelos

`GET localhost:8080/api/v1/flights`

<details>
<summary>Response</summary>
<pre>
{
    "flightListResponseDTO": [
        {
            "flightNumber": "NUEVO-1233",
            "dateFrom": "2022-02-10",
            "dateTo": "2022-02-15",
            "pricePerPerson": 2500.0,
            "origin": "Lugano 1 y 2",
            "destiny": "Pompeya",
            "seatType": "Economy"
        }
    ]
}
</pre>
</details>

## Actualizar un vuelo (Caso exitoso)

`PUT localhost:8080/api/v1/flights/edit?flightNumber=NUEVO-1233`

PARAMS
flightNumber: NUEVO-1233

<details>
<summary>Request Body</summary>
<pre>
{
    "flightNumber" : "NUEVO-1233",
    "origin" : "Lugano 1",
    "destiny" : "Pompeya",
    "seatType" : "Economy",
    "pricePerPerson" : 2500,
    "dateFrom" : "2022-02-10",
    "dateTo" : "2022-02-15"
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Se modifico correctamente",
    "code": 200
}
</pre>
</details>

## Actualizar un vuelo (Caso fallido - no existe el vuelo buscado)

`PUT localhost:8080/api/v1/flights/edit?flightNumber=NOEXISTE-1233`

PARAMS
flightNumber: NOEXISTE-1233

<details>
<summary>Request Body</summary>
<pre>
{
    "flightNumber" : "NOEXISTE-1233",
    "origin" : "Lugano 1",
    "destiny" : "Pompeya",
    "seatType" : "Economy",
    "pricePerPerson" : 2500,
    "dateFrom" : "2022-02-10",
    "dateTo" : "2022-02-15"
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Flight does not exist"
}
</pre>
</details>

## Eliminar vuelo

`DELETE http://localhost:8080/api/v1/flight/delete?flightNumber=NUEVO-1233`

PARAMS
flightNumber: NUEVO-1233

<details>
<summary>Response</summary>
<pre>
{
    "message": "Vuelo dada de baja correctamente",
    "code": 200
}
</pre>
</details>


## Obtener todos los vuelos por fecha y destino

`GET localhost:8080/api/v1/flights?dateFrom=10/02/2022&dateTo=15/02/2022&origin=lugano 1&destiny=Pompeya`

PARAMS
dateFrom: 10/02/2022
dateTo: 15/02/2022
origin: lugano 1
destinity: Pompeya

<details>
<summary>Response</summary>
<pre>

</pre>
</details>

## Alta nueva reserva de vuelo

`POST http://localhost:8080/api/v1/flight-reservation/new`

<details>
<summary>Request Body</summary>
<pre>
{
    "username":"juan",
    "flightReservationDTO":{
        "goingDate":"2022-02-10",
         "returnDate":"2022-02-15",
         "origin" : "Lugano 1 y 2",
         "destination" : "Pompeya",
        "flightNumber" : "NUEVO-1233",
        "seats":1,
      "seatType" : "Economy",
      "people":[{
          "dni":"380592223",
          "name":"team",
          "lastname":"algo",
          "birthDate":"25/06/1994",
          "mail":"team@algo.com"
      }],
      "paymentMethod":{
          "type":"credit",
          "number":"34343434335",
          "dues":6
      }
    }
       
   
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Se agrego una nueva reserva",
    "code": 201
}
</pre>
</details>

## Actualizar reserva de vuelo

`PUT http://localhost:8080/api/v1/flight-reservation/edit?id=1`

PARAMS
id: 1

<details>
<summary>Request Body</summary>
<pre>
{
    "username":"team",
    "flightReservationDTO":{
        "goingDate":"2022-02-10",
         "returnDate":"2022-02-15",
         "origin" : "Lugano 1 y 2",
         "destination" : "Pompeya",
        "flightNumber" : "NUEVO-1233",
        "seat":1,
      "seatType" : "Economy",
      "people":[{
          "dni":"380592223",
          "name":"team",
          "lastname":"otra cosa",
          "birthDate":"25/06/1994",
          "mail":"cambioMail@algo.com"
      }],
      "paymentMethod":{
          "type":"credit",
          "number":"34343434335",
          "dues":6
      }
    }
       
   
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Se modifico correctamente",
    "code": 200
}
</pre>
</details>

## Eliminar reserva de vuelo

`DELETE http://localhost:8080/api/v1/flight-reservation/delete?id=1`

PARAMS
id: 1

<details>
<summary>Response</summary>
<pre>
{
    "message": "Reserva de vuelo dada de baja correctamente",
    "code": 200
}
</pre>
</details>


# US0002

## Alta de paquete turístico

`POST http://localhost:8080/api/v1/touristicpackage/new`

<details>
<summary>Request Body</summary>
<pre>
{
   "packageNumber": 1234,
   "name": "paquete",
   "creation_date": "12/05/2010",
   "client_id": 1,
   "bookings": [2,3],
    "reservations": []
}
</pre>
</details>


<details>
<summary>Response</summary>
<pre>
{
    "message": "Paquete Turístico dado de alta correctamente",
    "code": 200
}
</pre>
</details>


## Obtener todos los paquetes turísticos

`GET http://localhost:8080/api/v1/touristicpackage/`

<details>
<summary>Response</summary>
<pre>
{
    "touristicPackages": [
        {
            "touristicPackageInfoResponseDTO": {
                "id": 1,
                "packageNumber": 1234,
                "name": "paquete",
                "creation_date": "12/05/2010",
                "client_id": 1
            },
            "bookings": [
                1,
                2
            ],
            "reservations": []
        }
    ]
}
</pre>
</details>

## Eliminar paquete turístico

`DELETE http://localhost:8080/api/v1/touristicpackage/delete?packageNumber=1234`

PARAMS
packageNumber: 1234

<details>
<summary>Response</summary>
<pre>
{
    "message": "Paquete Turístico dado de baja correctamente",
    "code": 200
}
</pre>
</details>


# US0003

## Obtener ganancias diarias

`GET http://localhost:8080/api/v1/dailyIncome?date=01/06/2022`

PARAMS
date: 02/06/2022

<details>
<summary>Response</summary>
<pre>
{
    "date": "02/06/2022",
    "total_income": 85910.0
}
</pre>
</details>

## Obtener ganancias mensuales

`GET http://localhost:8080/api/v1/monthlyIncome?month=06&year=2022`

PARAMS
month: 06
year: 2022

<details>
<summary>Response</summary>
<pre>
{
    "month": 6,
    "year": 2022,
    "total_income": 85910.0
}
</pre>
</details>


# US0004

## Top 3 de Clientes

`GET http://localhost:8080/api/v1/clients/top-3`

<details>
<summary>Response</summary>
<pre>
{
    "clients": [
   { "top_number": int,
     "year": int,
     "booking_quantity": int,
     "total_amount": Double,
     "client_id": int,
     "client_name": String,
     "client_lastname": String
   },
  { "top_number": int,
     "year": int,
     "booking_quantity": int,
     "total_amount": Double,
     "client_id": int,
     "client_name": String,
     "client_lastname": String
   },
  { "top_number": int,
     "year": int,
     "booking_quantity": int,
     "total_amount": Double,
     "client_id": int,
     "client_name": String,
     "client_lastname": String
   }
]
    
}
</pre>
</details>




