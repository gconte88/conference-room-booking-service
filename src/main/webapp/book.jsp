<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Book</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link href="${contextPath}/common.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<h2>Select room to book in</h2>
<div id="container">

</div>
<a class="btn btn-full-tiny center-text" href="javascript:history.back()">Go Back</a>

<!-- jQuery -->
<script>
    $(document).ready(function () {

        $.getJSON('/api/v1/booking/rooms/', function (data) {
            var trHTML = '';
            $.each(data, function (i, item) {
                trHTML +=
                    '<div>' +
                    '<a class="btn btn-full" href="${contextPath}/room/' + item.id + '">' +
                    'Room Name:' + item.name + ' Seats:' + item.seats + '</a>' +
                    '</div>';
            });
            $('#container').append(trHTML);
        });
    });
</script>
</body>
</html>