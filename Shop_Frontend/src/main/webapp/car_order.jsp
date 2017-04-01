<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CarOrder</title>
         <link rel="shortcut icon" href="/images/lotus.ico" type="image/x-icon">

            <link href="css/bootstrap.min.css" rel="stylesheet">
            <link href="mystyles.css" rel="stylesheet">

            <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

            <script src="js/bootstrap.min.js"></script>
    </head>

    <body>
      <header class="mainImage ">
      <div class="container-fluid" >
              <div class="row ">
                  <div class="top-line">

                  <div class="col-md-12 welcome">CAR ORDERS</div>

                  </div>
              </div>
              </div>
          <a href="saveOrder"><strong> SAVE ORDER</strong></a>
            <hr> <hr>
            <a href="cars"><strong> RETURN TO CARLIST</strong></a>
        <table class="table table-hover" id="tableId">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Model</th>
                    <th>Color</th>
                    <th>Type</th>
                    <th>Price</th>
                    <th>Year</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="carItem" items="${CAR_ORDER_REQ.items}">
                    <tr>
                        <td>${carItem.car.carId}</td>
                        <td>${carItem.car.model}</td>
                        <td>${carItem.car.color}</td>
                        <td>${carItem.car.type}</td>
                        <td>${carItem.price}</td>
                        <td>${carItem.car.yearOfProduction}</td>
                        <td>${carItem.count}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
         </header>
    </body>
</html>


