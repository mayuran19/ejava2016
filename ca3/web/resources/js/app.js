$(function () {
    var url = $("#socket-url").val();
    var socket = new WebSocket(url);
    socket.onopen = function () {
        
    }
    socket.onmessage = function (evt) {
        var data = JSON.parse(evt.data);
        var table = '<tr><td>' + data["title"] + '</td><td>' + data["category"] + '</td><td>' + data["content"] + '</td><td>' + data["createdAt"] + '</td></tr>';
        $('#tbl-notes > tbody').prepend( table );
    }
})