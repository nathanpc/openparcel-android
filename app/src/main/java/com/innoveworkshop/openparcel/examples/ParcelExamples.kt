package com.innoveworkshop.openparcel.examples

import com.innoveworkshop.openparcel.model.Parcel

/**
 * List of example JSON strings that the server might return.
 */
val parcelJsonExamples: Array<String> = arrayOf(
    // Panasonic Lumix DMC-GF1 (DHL - Delivering)
    """
    {
      "accentColor": "#FFCC00",
      "cached": true,
      "carrier": {
        "id": "dhl",
        "name": "DHL"
      },
      "creationDate": null,
      "destination": {
        "addressLine": null,
        "city": null,
        "coords": {
          "lat": null,
          "lng": null
        },
        "country": "Portugal",
        "postalCode": null,
        "searchQuery": "Portugal",
        "state": null
      },
      "history": [
        {
          "description": null,
          "location": {
            "addressLine": null,
            "city": null,
            "coords": {
              "lat": null,
              "lng": null
            },
            "country": "Portugal",
            "postalCode": null,
            "searchQuery": "Portugal",
            "state": null
          },
          "status": {
            "data": {
              "description": "Being delivered"
            },
            "type": "delivering"
          },
          "timestamp": "2024-02-19T08:41:00.000Z",
          "title": "Being delivered"
        },
        {
          "description": null,
          "location": {
            "addressLine": null,
            "city": null,
            "coords": {
              "lat": null,
              "lng": null
            },
            "country": "Portugal",
            "postalCode": null,
            "searchQuery": "Portugal",
            "state": null
          },
          "status": null,
          "timestamp": "2024-02-19T07:29:00.000Z",
          "title": "Shipment processed at delivery depot"
        },
        {
          "description": null,
          "location": {
            "addressLine": null,
            "city": null,
            "coords": {
              "lat": null,
              "lng": null
            },
            "country": "Portugal",
            "postalCode": null,
            "searchQuery": "Portugal",
            "state": null
          },
          "status": null,
          "timestamp": "2024-02-19T04:13:00.000Z",
          "title": "Arrival in the destination country/destination area"
        },
        {
          "description": null,
          "location": {
            "addressLine": null,
            "city": null,
            "coords": {
              "lat": null,
              "lng": null
            },
            "country": "Germany",
            "postalCode": null,
            "searchQuery": "Germany",
            "state": null
          },
          "status": null,
          "timestamp": "2024-02-16T08:24:00.000Z",
          "title": "Export parcel center"
        },
        {
          "description": null,
          "location": {
            "addressLine": null,
            "city": null,
            "coords": {
              "lat": null,
              "lng": null
            },
            "country": "Germany",
            "postalCode": null,
            "searchQuery": "Germany",
            "state": null
          },
          "status": null,
          "timestamp": "2024-02-14T19:40:00.000Z",
          "title": "Parcel center of origin"
        },
        {
          "description": null,
          "location": {
            "addressLine": null,
            "city": null,
            "coords": {
              "lat": null,
              "lng": null
            },
            "country": "",
            "postalCode": null,
            "searchQuery": null,
            "state": null
          },
          "status": {
            "data": {
              "description": "Order data transmitted electronically",
              "timestamp": null
            },
            "type": "created"
          },
          "timestamp": "2024-02-14T15:31:00.000Z",
          "title": "Order data transmitted electronically"
        }
      ],
      "id": 8,
      "lastUpdated": "2024-02-21T14:30:37.763294+00:00",
      "name": "Panasonic Lumix DMC-GF1",
      "origin": {
        "addressLine": null,
        "city": null,
        "coords": {
          "lat": null,
          "lng": null
        },
        "country": "Germany",
        "postalCode": null,
        "searchQuery": "Germany",
        "state": null
      },
      "status": {
        "data": {
          "description": "Delivery successful",
          "to": null
        },
        "type": "delivered"
      },
      "trackingCode": "CA767344619DE",
      "trackingUrl": "https://www.dhl.com/us-en/home/tracking/tracking-parcel.html?submit=1&tracking-id=CA767344619DE"
    }
    """.trimIndent(),

    // Macbook Pro 2019 (Delivered)
    """
    {
      "accentColor": "#DE0024",
      "cached": false,
      "carrier": {
        "id": "ctt",
        "name": "CTT"
      },
      "creationDate": "2024-02-28T13:28:00.000Z",
      "destination": {
        "addressLine": null,
        "city": "Baixa Da Banheira",
        "coords": {
          "lat": null,
          "lng": null
        },
        "country": null,
        "postalCode": null,
        "searchQuery": "Baixa Da Banheira",
        "state": null
      },
      "history": [
        {
          "description": "O envio foi entregue. O processo de envio terminou.",
          "location": {
            "addressLine": "8819009 - (CO) C.O. PALMELA",
            "city": null,
            "coords": {
              "lat": null,
              "lng": null
            },
            "country": null,
            "postalCode": null,
            "searchQuery": "8819009 - (CO) C.O. PALMELA",
            "state": null
          },
          "status": {
            "data": {
              "description": "Entregue a: Nathan Campos Caixa Do Correio",
              "to": "Nathan Campos Caixa Do Correio"
            },
            "type": "delivered"
          },
          "timestamp": "2024-02-29T12:42:00.000Z",
          "title": "Entregue"
        },
        {
          "description": "O envio saiu para entrega. Será entregue durante o dia.",
          "location": {
            "addressLine": "8819009 - (CO) C.O. PALMELA",
            "city": null,
            "coords": {
              "lat": null,
              "lng": null
            },
            "country": null,
            "postalCode": null,
            "searchQuery": "8819009 - (CO) C.O. PALMELA",
            "state": null
          },
          "status": {
            "data": {
              "description": "O envio saiu para entrega. Será entregue durante o dia."
            },
            "type": "delivering"
          },
          "timestamp": "2024-02-29T09:13:00.000Z",
          "title": "Em entrega"
        },
        {
          "description": "Chegou ao centro operacional",
          "location": {
            "addressLine": "8819009 - (CO) C.O. PALMELA",
            "city": null,
            "coords": {
              "lat": null,
              "lng": null
            },
            "country": null,
            "postalCode": null,
            "searchQuery": "8819009 - (CO) C.O. PALMELA",
            "state": null
          },
          "status": null,
          "timestamp": "2024-02-29T05:30:00.000Z",
          "title": "Em trânsito"
        },
        {
          "description": "O envio foi aceite. O processo de envio foi iniciado.",
          "location": null,
          "status": {
            "data": {
              "description": "O envio foi aceite. O processo de envio foi iniciado."
            },
            "type": "posted"
          },
          "timestamp": "2024-02-28T23:59:00.000Z",
          "title": "Aceite"
        },
        {
          "description": "A informação sobre o envio foi recebida.",
          "location": null,
          "status": {
            "data": {
              "description": "A informação sobre o envio foi recebida.",
              "timestamp": "2024-02-28T13:28:00.000Z"
            },
            "type": "created"
          },
          "timestamp": "2024-02-28T13:54:00.000Z",
          "title": "Aguarda entrada nos CTT"
        },
        {
          "description": "A informação sobre o envio foi recebida.",
          "location": null,
          "status": {
            "data": {
              "description": "A informação sobre o envio foi recebida.",
              "timestamp": "2024-02-28T13:28:00.000Z"
            },
            "type": "created"
          },
          "timestamp": "2024-02-28T13:28:00.000Z",
          "title": "Aguarda entrada nos CTT"
        }
      ],
      "id": 13,
      "lastUpdated": "2024-03-03T10:40:35.857357+00:00",
      "name": "Macbook Pro (2019)",
      "origin": null,
      "status": {
        "data": {
          "description": "Entregue a: Nathan Campos Caixa Do Correio",
          "to": "Nathan Campos Caixa Do Correio"
        },
        "type": "delivered"
      },
      "trackingCode": "DW810271283PT",
      "trackingUrl": "https://appserver.ctt.pt/CustomerArea/PublicArea_Detail?ObjectCodeInput=DW810271283PT&SearchInput=DW810271283PT"
    }
    """.trimIndent()
)

/**
 * List of example parcels.
 */
val parcelExamples: List<Parcel> = List<Parcel>(parcelJsonExamples.size) {
    Parcel.fromJson(parcelJsonExamples[it])
}
