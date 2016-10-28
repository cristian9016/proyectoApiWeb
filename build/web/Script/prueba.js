// Load the Visualization API and the controls package.
//google.load('visualization', '1.0', {'packages': ['controls', 'linechart', 'corechart']});
//google.charts.load('current', {packages: ['table']});
// Set a callback to run when the Google Visualization API is loaded.
google.load('visualization', '1.0', {'packages': ['controls', 'linechart', 'corechart', 'table']});
//google.charts.load('current', {packages: ['table']});
//google.setOnLoadCallback(add());
//google.setOnLoadCallback(upd());
//google.setOnLoadCallback(retrieveConsumer("76327102"));
//
//
//google.setOnLoadCallback(deleteConsumer());
google.setOnLoadCallback(retrieveConsumers);
google.charts.setOnLoadCallback(drawDatatable);


function drawDatatable() {
    //retrieve JSON data
    $.get("../Source Packages/com.apiweb.proyecto.entities.device/", function (rData, status) {
        //alert("Data: " + rData.toString() + "\nStatus: " + status);
        drawTable(rData);
    }, "json");
}

function drawTable(jsonData1) {
        var data = new google.visualization.DataTable();
        data.addColumn('numbre', 'type');
        data.addColumn('string', 'ip');
        data.addColumn('string', 'location');
        data.addRows(jsonData1.length);
        for (var pos = 0; pos < jsonData1.length; pos = pos + 1) {
            data.setCell(pos, 1, jsonData1[pos].type);
            data.setCell(pos, 2, jsonData1[pos].ip);
            data.setCell(pos, 3, jsonData1[pos].location);
    } 
        
        
        var table = new google.visualization.Table(document.getElementById('table_div'));
        table.draw(data, {showRowNumber: true, width: '100%', height: '100%'});
        
        
      }

function retrieveConsumers() {
    //retrieve JSON data
    $.get("../Source Packages/com.apiweb.proyecto.entities.device/", function (rData, status) {
        //alert("Data: " + rData.toString() + "\nStatus: " + status);
        drawDashboard(rData);
    }, "json");
}

function retrieveConsumer(id) {
    //retrieve a JSON data
    $.get("../Source Packages/com.apiweb.proyecto.entities.device/"+id+"/", function (rData, status) {
       // alert("Data: " + rData.toString() + "\nStatus: " + status);
        drawConsumer(rData);
    }, "json");
}

function del(){
            var iden =   document.getElementById("id4").value;
            deleteConsumer(iden);
}
function deleteConsumer(id) {
    
    //delete data
    $.ajax({
        url: '../Source Packages/com.apiweb.proyecto.entities.device/'+id,
        type: 'delete',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function () {
            alert('Delete completed');
        }
    });
}

function upd(){
    var id =   document.getElementById("id5").value;
    var name2 =   document.getElementById("id6").value;
    var quan2 =   document.getElementById("id7").value;
    var consum = '{"identification":"'+id+'","name":"'+name2+'","quantity":"'+quan2+'"}}';
    updateConsumer(id,consum);
}


function updateConsumer(id,consumer) {
    $.ajax({
        url: '../Source Packages/com.apiweb.proyecto.entities.device/'+id,
        type: 'put',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: consumer,
        success: function () {
            alert('put completed');
        }
    });
}


    function add(){
            var name =   document.getElementById("id2").value;
            var iden =   document.getElementById("id1").value;
            var quan =   document.getElementById("id3").value;
            var consum = '{"identification":"'+iden+'","name":"'+name+'","quantity":"'+quan+'"}}';
            addConsumer(consum);
    }
    
function addConsumer(consumer) {
    $.ajax({
        url: '../Source Packages/com.apiweb.proyecto.entities.device/',
        type: 'post',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: consumer,
        success: function () {
            alert('post completed');
        }
    });
}

function drawConsumer(jsonData) {
    alert("A consumer "+jsonData.type);
}

// instantiates a dashboard, a range slider and a pie chart,
// passes in the data and draws it.
function drawDashboard(jsonData) {
    
    // Create our data table out of JSON data loaded
    
    var data = new google.visualization.DataTable();
    
    
    data.addColumn('numbre', 'type');
    data.addColumn('string', 'ip');
    data.addColumn('string', 'location');
    data.addRows(jsonData.length);
    for (var pos = 0; pos < jsonData.length; pos = pos + 1) {
        data.setCell(pos, 1, jsonData[pos].type);
        data.setCell(pos, 2, jsonData[pos].ip);
        data.setCell(pos, 3, jsonData[pos].location);
    }  

    // Create a dashboard
    var dashboard = new google.visualization.Dashboard(document.getElementById('dashboard_div'));
    
    // Create a range slider, passing some options
    var donutRangeSlider = new google.visualization.ControlWrapper({
        'controlType': 'NumberRangeFilter',
        'containerId': 'filter_div',
        'options': {
            'filterColumnLabel': 'Quantity'
        }
    });

    // Create a pie chart, passing some options
    var pieChart = new google.visualization.ChartWrapper({
        'chartType': 'PieChart',
        'containerId': 'chart_div',
        'options': {
            'width': 400,
            'height': 300,
            'pieSliceText': 'value',
            'legend': 'right',
            'is3D': true
        }
    });
          
    // Establish dependencies, declaring that 'filter' drives 'pieChart',
    // so that the pie chart will only display entries that are let through
    // given the chosen slider range.
    dashboard.bind(donutRangeSlider, pieChart);
    
    

    // Draw the dashboard.
    dashboard.draw(data);
    
}