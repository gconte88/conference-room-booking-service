<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Room</title>
    <!-- CSS -->
    <link href="http://localhost:8080/common.css" rel="stylesheet">

</head>
<body>
<div id="container">
    <h2>Set your reservation date and time</h2>
    <div id="reserve-room" class="center-text">
        <span>Reservation Date</span>
        <input type="text" class="datepicker" id="date_start"/>
        <span>Hour start</span>
        <input type="text" class="timepicker" id="time_start">
        to
        <input type="text" class="timepicker" id="time_end">
    </div>
    <div>
        <a class="btn btn-full-tiny center-text" href="javascript:history.back()">Go Back</a>

        <a class="btn btn-full-tiny center-text confirm" href="#">Confirm</a>
    </div>
</div>
</body>

<script
        src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
        crossorigin="anonymous"></script>
<script type="text/javascript" src="http://localhost:8080/js/picker.js"></script>
<script type="text/javascript" src="http://localhost:8080/js/picker.date.js"></script>
<script type="text/javascript" src="http://localhost:8080/js/picker.time.js"></script>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/default.css"/>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/default.date.css"/>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/default.time.css"/>

<script>
    $(document).ready(function () {

        var reservations;

        $.getJSON('http://localhost:8080/api/v1/booking/room/${room_id}/bookings', function (data) {
            reservations = data;
        });

        $('.datepicker').pickadate({
            'format': 'yyyy-mm-dd',
            min: new Date()
        });


        $('.timepicker').pickatime({
            'interval': 1,
            'min': new Date(),
            'format': 'h:ia'
        });

        $(document).on('change', '#date_start', function (e) {
            var selectedDate = $(this).val();
            var timesToDisable = [];

            $.each(reservations, function (i, item) {
                if (selectedDate === item.reserved_date) {
                    timesToDisable.push(item.reserved_minutes);
                }
            });

            refreshTimepicker(timesToDisable);

        });

        function refreshTimepicker(data) {
            var disables = [];
            $.each(data, function (i, item) {
                var fromArray = item[0].split(":");
                var toArray = item[1].split(":");

                var disableThis = {
                    "from": [
                        fromArray[0], fromArray[1],
                    ],
                    "to": [
                        toArray[0], toArray[1],]
                };

                console.log(disableThis);
                disables.push(disableThis);

            });
            var picker = $('.timepicker').pickatime().pickatime('picker');

            picker.set('disable', disables);
        }

        $(document).on('click', '.confirm', function () {

            var date_start = $('#date_start').val();
            var time_start = $('#time_start').val();
            var time_end = $('#time_end').val();
            var roomId = '${room_id}';

            var url = "http://localhost:8080/api/v1/booking/room/"+ roomId + "/book";

            $.ajax({
                url : url,
                type: "POST",
                contentType: "application/json",
                data: {
                    "start_hour": time_start,
                    "end_hour": time_end,
                    "date":date_start
                },
                success: function(response){
                    console.log("sucess");
                },
                error:function(response){
                    console.log("something went wrong");
                }
            });

            window.location = '/confirm';
        });
    });
</script>
</html>