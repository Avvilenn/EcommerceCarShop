<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Car List</title>
    <link rel="shortcut icon" href="images/lotus.ico" type="image/x-icon">

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

            <div class="col-md-12 welcome">LIST OF CARS</div>

            </div>
        </div>
        <div class="row">
         <div class="mylinks">
          <div class="col-md-4"></div>
          <div class="col-md-4">
           <div class="mainlinks">

                          <a href="getCar"><p class="colorLink">ADD CAR</p></a>

                      </div>
                      <hr>
                      <hr>

                       <div class="mainlinks">

                                                <a href="viewOrders"><p class="colorLink">VIEW YOUR ORDERS</p></a>

                                            </div>
                     <hr>
                     <hr>
          </div>
          <div class="col-md-4"></div>
        </div>
        </div>
        <div class="row" >
<div class="placeHolderForForm">
            <div class="col-md-2 ">

            </div>

                <div class="col-md-9 ">
                <div class="placeHolderForForm">
                 <table class="table table-hover" id="tableId">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Model</th>
                                    <th>Color</th>
                                    <th>Type</th>
                                    <th>Price</th>
                                    <th>Year</th>
                                    <th>Delete</th>
                                    <th><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Add to basket</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="car" items="${CARS}" varStatus="status">
                                    <c:url var="editUrl" value="getCar">
                                        <c:param name="carId" value="${car.carId}"/>
                                    </c:url>
                                    <c:url var="deleteUrl" value="deleteCar">
                                        <c:param name="carId" value="${car.carId}"/>
                                    </c:url>
                                    <c:url var="putUrl" value="putCar">
                                          <c:param name="car_id" value="${car.carId}"/>
                                    </c:url>

                                        <td><a href="${editUrl}">${car.carId}</a></td>
                                        <td>${car.model}</td>
                                        <td>${car.color}</td>
                                        <td>${car.type}</td>
                                        <td>${car.price}</td>
                                        <td>${car.yearOfProduction}</td>
                                        <td><a href="${deleteUrl}">Delete</a></td>
                                        <td><a href="${putUrl}"><span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>Add to basket</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        </div>





                </div>
                <div class="col-md-1 ">


                </div>
                </div>
        </div>
    </div>
  </header>



    </body>

</html>


