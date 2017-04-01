<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Order List</title>
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

            <div class="col-md-12 welcome">LIST OF ORDERS</div>

            </div>
        </div>

        <div class="row" >
<div class="placeHolderForForm">
            <div class="col-md-1 ">

            </div>

                <div class="col-md-10 ">
                <div class="placeHolderForForm">
                 <table class="table table-hover" id="tableId">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>PersonId</th>
                                    <th>Date of order</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="order" items="${ORDERS}" varStatus="status">

                                        <td>${order.carOrderId}</td>
                                        <td>${order.person.getPersonId()}</td>
                                        <td>${order.dateOfOrder}</td>

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